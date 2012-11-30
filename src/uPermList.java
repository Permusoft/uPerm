/*
 * Class: uPermList
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

import java.util.*;

public class uPermList extends uPermType {

   protected Vector <uPermType> list;

   private final static String lbrack;
   private final static String comma;
   private final static String rbrack;


   static {
      lbrack = new String ("[");
      comma = new String (", ");
      rbrack = new String ("]");
   }


   public uPermList () {
      super (uPermTypeType.LIST);
      this.list = new Vector <uPermType> ();
   }


   // Concatenation
   public uPermType plus (uPermType x) {
      // Coerce to uPermString if necessary
      if (x.type == uPermTypeType.STRING) {
         return new uPermString (this.toString() + x.toString());
      }

      if (x.type != uPermTypeType.LIST)
         throw new uPermIllegalArgumentException
            ("list concatenation: arguments must be lists");

      uPermList ret = new uPermList ();

      for (int i = 0; i < this.list.size(); i++)
         ret.list.add (this.list.elementAt(i));

      for (int i = 0; i < ((uPermList)x).list.size(); i++)
         ret.list.add (((uPermList)x).list.elementAt(i));

      return ret;
   }


   public uPermType append (uPermType x) {
      this.list.add (x);
      return this;
   }



   // Multiply by a permutation on the right
/*
   public uPermType multiply (uPermType x) {
      if (!(x instanceof uPermPermutation))
         throw new uPermIllegalArgumentException
            ("attemtped multiplication by " + x.type + ": must be permutations");
      return null;

   }
*/


/*
   public uPermType setSlice (uPermType x) {
      if (!(x instanceof uPermSlice))
         throw new uPermIllegalArgumentException
            ("list concatenation: arguments must be lists");

      return xxx;
   }
*/


   public uPermType equal (uPermType x) {
      if (x.type != uPermTypeType.LIST) return new uPermBoolean (false);

      final uPermList l = (uPermList) x;

      if (this.list.size() != l.list.size()) return new uPermBoolean (false);

      for (int i = 0; i < this.list.size(); i++) {
         // mom always told me if I made expressions like this they would stick
         if (((uPermBoolean)this.list.elementAt(i).notequal(l.list.elementAt(i))).valueOf()) return new uPermBoolean (false);
      }

      return new uPermBoolean (true);
   }


   public uPermType notequal (uPermType x) {
      final boolean val = ! ((uPermBoolean)this.equal(x)).valueOf();
      return new uPermBoolean (val);
   }


   public uPermType elementAt (uPermType i) {
      if (i == null) throw new uPermIllegalArgumentException ("null argument");
      if (i.type != uPermTypeType.INTEGER)
         throw new uPermIllegalArgumentException ("lists are indexed with integers");
      return this.list.elementAt(((uPermInteger)i).valueOf());
   }


   public void setElement (uPermType element, uPermType index) {
      if (index == null) throw new uPermIllegalArgumentException ("null argument");
      if (index.type != uPermTypeType.INTEGER)
         throw new uPermIllegalArgumentException ("lists are indexed with integers");
      this.list.setElementAt (element, ((uPermInteger)index).valueOf());
   }


   // Returns the length of this list
   public uPermType sizeof () {
      return new uPermInteger (this.list.size());
   }


   public void clear() {
      this.list.clear();
   }


   public uPermString touPermString () {
      return new uPermString (toString());
   }


   // This method is one of the few in uPerm that must be reentrant,
   // to handle the possibility of nested lists.
   public String toString () {
      final StringBuilder listStringBuilder = new StringBuilder();
      uPermType listElement = null;
      int start, i;

      // Clear the StringBuilder.
      listStringBuilder.delete (0, listStringBuilder.length());

      listStringBuilder.append (this.lbrack);

      for (i = 0; i < this.list.size(); i++) {
         listElement = list.elementAt(i);
         if (listElement.type == uPermTypeType.STRING)
            listStringBuilder.append (((uPermString)listElement).toQuotedString());
         else
            listStringBuilder.append (listElement.toString());

         if (i < this.list.size() - 1) listStringBuilder.append (this.comma);
      }

      listStringBuilder.append (this.rbrack);

      return listStringBuilder.toString();
   }

}
