/*
 * Class: uperm
 * Author: Gregory Kip
 *
 * The main entry point to the uPerm interpreter. 
 * Your CLASSPATH must include ~/bin/antlr.jar and ~/bin/uperm.jar.
 * Invoke with 'java uperm <filename>.p'.
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
import antlr.debug.misc.*;

class uperm {
   public static void main (String[] args) {

      boolean displayAST = false;

      try {

         FileInputStream file;
         DataInputStream Data;

         if (args.length == 0) {
            file = null;
            Data = new DataInputStream (System.in);
         } else if (args.length == 1) {
            file = new FileInputStream (args[0]);
            Data = new DataInputStream (file);
         } else {
            displayAST = true;
            file = new FileInputStream (args[0]);
            Data = new DataInputStream (file);
         }

         uPermLexer L = new uPermLexer (new DataInputStream(Data));
         uPermParser P = new uPermParser (L);

         P.file();

         antlr.CommonAST ast = (antlr.CommonAST) P.getAST();

         if (displayAST) {
            System.out.println (ast.toStringTree());
            ASTFrame frame = new ASTFrame ("AST", ast);
            frame.setVisible (true);
         }

         uPermWalker walker = new uPermWalker ();

         try {
            walker.program (ast);
         } catch (NullPointerException NPE) {
            System.err.println (NPE.getMessage());
            //NPE.printStackTrace();
         } catch (Exception E) {
            System.err.println (E.getMessage());
            //E.printStackTrace();
         }

      } catch (Exception E) {
         System.out.println ("Exception: " + E);
      }

   }

}
