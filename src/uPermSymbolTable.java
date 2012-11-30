/*
 * Class: uPermSymbolTable
 * Author: Gregory Kip
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

import java.util.*;

public class uPermSymbolTable {

   private String name;
   private HashMap <String, uPermType> table;
   private uPermSymbolTable parent;
   //private uPermExecutionContext executionContext;

   private static String arrow;

   static {
      arrow = new String (" -> ");
   }

   public uPermSymbolTable () {
      table = new HashMap <String, uPermType> ();
      parent = null;
      //executionContext = uPermExecutionContext.BLOCK;
      name = null;
   }


   public uPermSymbolTable (uPermSymbolTable parent) {
      if (parent == null)
         throw new uPermIllegalArgumentException ("null parent symbol table");
      table = new HashMap <String, uPermType> ();
      this.parent = parent;
      name = null;
   }


   public uPermSymbolTable (uPermSymbolTable parent, String fname) {
      if (parent == null)
         throw new uPermIllegalArgumentException ("null parent symbol table");
      table = new HashMap <String, uPermType> ();
      this.parent = parent;
      name = fname;
   }


   private HashMap <String, uPermType> tableContaining (String s) {
      if (table.containsKey(s)) return table;
      if (parent != null) return parent.tableContaining (s);
      return null;
   }


   public uPermType bind (String s, uPermType x) {
      HashMap <String, uPermType> target;
      target = tableContaining (s);
      if (target == null)
         table.put (s,x);
      else {
         uPermType f = target.get (s);
/* this is fine
         // Make sure we don't overwrite an existing function
         if (f.type == uPermTypeType.FUNCTION)
            throw new uPermIllegalArgumentException
               (s + " already defined as a function");
*/
         target.put (s,x);
      }
      return x;
   }


   public uPermType bindLocal (String s, uPermType x) {
      return table.put (s,x);
   }


   public uPermType removeSymbol (String s) {
      return table.remove (s);
   }


   public void clear() {
      if (table != null) table.clear();
   }


   // Clean house
   public void clearAll() {
      name = null;
      parent = null;
      if (table != null) table.clear();
      table = null;
   }


   public Boolean containsSymbol (String s) {
      if (s == null)
         throw new uPermIllegalArgumentException ("null argument");
      return table.containsKey (s);
   }


   public uPermSymbolTable parent() {
      return parent;
   }


   public uPermType valueOf (String s) {
      if (s == null)
         throw new uPermIllegalArgumentException ("null argument");
      uPermType r = null;

      if (table.containsKey(s)) {
         r = table.get (s);
      } else if (parent != null) {
         r = parent.valueOf(s);
      }

      if (r == null)
         throw new uPermRuntimeException ("symbol " + s + " does not exist");

      return r;
   }


   public int size() {
      return table.size();
   }


/*
   public uPermExecutionContext executionContext () {
      return executionContext;
   }
*/


   public String maximallyToString () {
      if (parent == null) {
         return arrow + table.toString();
      } else {
         return parent.maximallyToString() + arrow + table.toString();
      }
   }


   public String toString() {
      return table.toString();
   }

}
