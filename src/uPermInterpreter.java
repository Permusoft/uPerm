/*
 * Class: uPermInterpreter
 * Author: Gregory Kip (gk2131)
 */

/*
 * Copyright (c) 2008 Permusoft Corporation. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 * 
 * 1) Redistributions of source code must retain the above copyright notice, this list of conditions and
 * the following disclaimer.
 *
 * 2) Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 * and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.io.*;

import antlr.collections.AST;
import antlr.TokenStreamException;
import antlr.RecognitionException;


public class uPermInterpreter
       implements uPermWalkerTokenTypes { // we need BLOCK

   private uPermSymbolTable root, curr;

   private uPermWalker walker;
   private uPermControl control;

   private uPermType returnVal;

   private final static String argss;


   // this will save a nanosecond from time
   // to time when executing functions
   static {
      argss = new String ("args");
   }



   private uPermInterpreter () {}

   public uPermInterpreter (uPermWalker w) {
      root = curr = new uPermSymbolTable();
      walker = w;
      control = uPermControl.OPEN;
      returnVal = new uPermType();
      //System.out.println ("root = curr = " + root);
   }



   //-----------------------------------------------------------------------
   // Flow control
   //-----------------------------------------------------------------------

   public uPermControl constrainExecution () {
      control = uPermControl.CONSTRAINED;
      return control;
   }


   public uPermControl loopConstrainExecution () {
      control = uPermControl.LOOP_CONSTRAINED;
      return control;
   }


   public uPermControl returnConstrainExecution () {
      control = uPermControl.RETURN_CONSTRAINED;
      return control;
   }


   // Set control to OPEN.
   // Return the previous control value.
   public uPermControl releaseExecution () {
      final uPermControl c = control;
      control = uPermControl.OPEN;
      return c;
   }


   public boolean executionIsOpen() {
      return control == uPermControl.OPEN;
   }


   public uPermControl control() {
      return control;
   }


   //---------------------------------------------------------------------------------
   // Symbol table
   //-----------------------------------------------------------------------

   public void pushSymbolTable () {
      curr = new uPermSymbolTable (curr);
   }


   public void popSymbolTable() {
      if (curr == null)
         throw new uPermRuntimeException ("cannot pop NULL symbol table!");
      if (curr == root)
         throw new uPermRuntimeException ("cannot pop root symbol table");

      final uPermSymbolTable popped = curr;
      curr = curr.parent();
      popped.clearAll();
   }


   public uPermSymbolTable currentSymbolTable() {
      return curr;
   }


   public uPermType bind (String s, uPermType x) {
      return curr.bind (s,x);
   }


   public uPermType bindLocal (String s, uPermType x) {
      return curr.bindLocal (s,x);
   }


   public uPermType bindFunction (String s, AST body) {
      return curr.bind (s, new uPermFunction (s, body));
   }


   public uPermType valueOf (String s) {
      uPermType r;
      r = curr.valueOf (s);
      if (r == null)
         throw new uPermRuntimeException ("symbol " + s + " does not exist");
      return r;
   }


   public void assign (String id, uPermType a, uPermType b) {
      if (id == null)
         throw new uPermIllegalArgumentException ("null argument");
      if (a == null)
         throw new uPermIllegalArgumentException ("null argument");

      if (b == null) bind (id, a);
      else {
         // assignment is to a "slice", so a is the index and b the object
         uPermType x = valueOf (id);
         if (x.type != uPermTypeType.LIST)
            throw new uPermRuntimeException (id + " is not a list");
         ((uPermList)x).setElement (b, a);
      }
   }


   public String currentSymbolTableString() {
      return curr.toString();
   }


   //---------------------------------------------------------------------------------
   // The Executive
   //---------------------------------------------------------------------------------

   public final void enterBlock () {
      pushSymbolTable();
   }


   public final uPermControl exitBlock () {
      popSymbolTable();
      return releaseExecution();
   }


   public final uPermControl execute (AST statement) throws RecognitionException {
      if (statement == null)
         throw new uPermIllegalArgumentException ("null statement");

      if (control == uPermControl.OPEN) return walker.statement (statement);
      return control;
   }


   // breaks or continues inside if statements affect its enclosing block
   public final void executeIfStatement (AST condition, AST action, AST alternative)
      throws RecognitionException {

      if (condition == null)
         throw new uPermIllegalArgumentException ("null condition");
      if (action == null)
         throw new uPermIllegalArgumentException ("null action");

      final uPermType cond = walker.expression (condition);

      if (cond.type != uPermTypeType.BOOLEAN)
         throw new uPermRuntimeException ("condition must be boolean");

      if (((uPermBoolean)cond).valueOf()) {
         control = walker.statement (action);
      } else if (alternative != null) {
         control = walker.statement (alternative);
      }
   }


   public final void executeForLoop (AST init, AST fcond, AST iter, AST fstmt)
      throws RecognitionException {

      if (init == null)
         throw new uPermIllegalArgumentException ("null initializer");
      if (fcond == null)
         throw new uPermIllegalArgumentException ("null condition");
      if (iter == null)
         throw new uPermIllegalArgumentException ("null iterator");
      if (fstmt == null)
         throw new uPermIllegalArgumentException ("null statement");

      uPermType condition = null;

      // we push a new symbol table to contain the loop variables.
      // we do want the programmer to have access to variables
      // outside of this block, so general dynamic binding semantics
      // apply rather than strict local binding.
      pushSymbolTable();

      walker.statement (init);

      while (true) {
         condition = walker.expression (fcond);
         if (condition.type != uPermTypeType.BOOLEAN)
            throw new uPermRuntimeException ("for condition must be boolean");

         if (!((uPermBoolean)condition).valueOf()) break;

         // do it
         control = walker.statement (fstmt);

         if (control == uPermControl.LOOP_CONSTRAINED) releaseExecution();
         if (control == uPermControl.CONSTRAINED) break; 
         if (control == uPermControl.RETURN_CONSTRAINED) break; 

         walker.statement (iter); // control cannot change -- iter is assignment or function call
      }

      popSymbolTable();

      if (control != uPermControl.RETURN_CONSTRAINED) releaseExecution();
   }


   public final void executeWhileLoop (AST condn, AST stmt) throws RecognitionException {
      if (condn == null)
         throw new uPermIllegalArgumentException ("null argument");
      if (stmt == null)
         throw new uPermIllegalArgumentException ("null argument");

      uPermType condition = null;

      while (true) {
         condition = walker.expression (condn);

         if (condition.type != uPermTypeType.BOOLEAN)
            throw new uPermRuntimeException ("while condition must be boolean");
         
         if (!((uPermBoolean)condition).valueOf()) break;

         control = walker.statement (stmt);

         if (control == uPermControl.LOOP_CONSTRAINED) releaseExecution();
         if (control == uPermControl.CONSTRAINED) break; 
         if (control == uPermControl.RETURN_CONSTRAINED) break; 
      }

      if (control != uPermControl.RETURN_CONSTRAINED) releaseExecution();
      //return releaseExecution();
   }


   // this function also handles permutation image evaluation
   public final uPermType executeFunctionCall (String fname, AST args)
      throws RecognitionException {

      final uPermType f = valueOf (fname);

      if (f.type == uPermTypeType.FUNCTION) return invoke ((uPermFunction) f, args);
      else if (f.type == uPermTypeType.PERMUTATION) {
         final int n = args.getNumberOfChildren();
         if (n != 1) // permutations are called on exactly one argument
           throw new uPermRuntimeException
              ("permutation " + fname + " called with != 1 arguments");
         else {
            // EXPR_LIST returns a list, we just want the int
            final AST e = args.getFirstChild();
            return ((uPermPermutation)f).imageOf(walker.expression(e));
         }
      } else {
         throw new uPermRuntimeException
           (fname + " is not a function or permutation");
      }

   }


   // this function always leaves control OPEN for this object
   private uPermType invoke (uPermFunction f, AST argument_list)
      throws RecognitionException {

      if (f == null)
         throw new uPermIllegalArgumentException ("null function");
      if (argument_list == null)
         throw new uPermIllegalArgumentException ("null parameter list");

      final uPermSymbolTable popped;
      uPermList args = null;

      // Evaluate arguments before creating the new environment
      args = (uPermList) walker.expression (argument_list);

      curr = new uPermSymbolTable (curr);
      curr.bindLocal (argss, args);

      // do it
      f.execute (walker);

      // clean up the symbol table
      popped = curr;
      curr = curr.parent();
      popped.clearAll();

      control = uPermControl.OPEN;

      releaseExecution();

      // We don't want to introduce nulls or untyped uPermTypes into the program
      if (returnVal == null) return new uPermBoolean (true);
      return returnVal;
   }


   public uPermControl setReturnValue (uPermType v) {
      returnVal = v;
      return returnConstrainExecution();
   }


   public void printExpression (uPermType val) {
      if (val == null) System.out.println();
      else System.out.println (val);
   }


   // parse and walk a program file specified by the user
   // this always results in OPEN control for this object and always returns OPEN
   public void importFile (uPermType fname) throws RecognitionException {
      if (fname == null) throw new uPermIllegalArgumentException ("null argument");
      if (fname.type != uPermTypeType.STRING)
         throw new uPermIllegalArgumentException ("argument must be string");

      try {
         final String filename = ((uPermString)fname).valueOf();
         final FileInputStream file = new FileInputStream (filename);
         final DataInputStream Data = new DataInputStream (file);
         final uPermLexer L = new uPermLexer (new DataInputStream(Data));
         final uPermParser P = new uPermParser (L);
         P.file();
         antlr.CommonAST ast = (antlr.CommonAST) P.getAST();
         walker.program(ast);
      } catch (FileNotFoundException E) {
         //E.printStackTrace();
         abortExecution (E.getMessage());
      } catch (TokenStreamException TSE) {
         //TSE.printStackTrace();
         abortExecution (TSE.getMessage());
      }

      releaseExecution();
   }


   public void abortExecution (String msg) {
      System.err.println (msg);
      System.exit(-1);
   }

}
