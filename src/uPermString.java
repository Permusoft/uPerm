/*
 * Class: uPermString
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

class uPermString extends uPermType {
   String val;

   public uPermString () {
      super (uPermTypeType.STRING);
      this.val = new String();
   }

   public uPermString (String s) {
      super (uPermTypeType.STRING);
      this.val = s;
   }

   public uPermString (uPermType x) {
      this.val = x.toString();
   }


   public uPermType touPermString () {
      return this;
   }

   public String toString () {
      return this.val;
   }


   public String toQuotedString() {
      return "'" + this.val + "'";
   }


   protected String value (uPermType x) {
      if (x.type != uPermTypeType.STRING)
        throw new uPermIllegalArgumentException ("value expected STRING, found " + x.type);
      return ((uPermString)x).val;
   }


   protected String valueOf () {
      return this.val;
   }


   public uPermType lt (uPermType x) {
      if (x.type != uPermTypeType.STRING)
        throw new uPermIllegalArgumentException ("< expected STRING, found " + x.type);
      return new uPermBoolean (this.val.compareTo (value(x)) < 0);
   }


   public uPermType le (uPermType x) {
      if (x.type != uPermTypeType.STRING)
        throw new uPermIllegalArgumentException ("<= expected STRING, found " + x.type);
      return new uPermBoolean (this.val.compareTo (value(x)) <= 0);
   }


   public uPermType gt (uPermType x) {
      if (x.type != uPermTypeType.STRING)
        throw new uPermIllegalArgumentException ("> expected STRING, found " + x.type);
      return new uPermBoolean (this.val.compareTo (value(x)) > 0);
   }


   public uPermType ge (uPermType x) {
      if (x.type != uPermTypeType.STRING)
        throw new uPermIllegalArgumentException (">= expected STRING, found " + x.type);
      return new uPermBoolean (this.val.compareTo (value(x)) >= 0);
   }


   public uPermType sizeof () {
      return new uPermInteger (this.val.length());
   }


   public uPermType plus (uPermType x) {
      String S = new String (this.val);
      if (x.type == uPermTypeType.STRING) {
         return new uPermString (S.concat (value(x)));
      } else  {
         return new uPermString (S.concat (x.toString()));
      }
   }


/*
   public uPermType addToThis (uPermType x) {
      if (x.type != uPermTypeType.STRING)
        throw new uPermIllegalArgumentException ("+= expected STRING, found " + x.type);
      this.val.concat (value(x));
   }
*/


   public uPermType equal (uPermType x) {
      if (x.type != uPermTypeType.STRING) return new uPermBoolean (false);
      return new uPermBoolean (this.val.equals(value(x)));
   }


   public uPermType notequal (uPermType x) {
      if (x.type != uPermTypeType.STRING) return new uPermBoolean (true);
      return new uPermBoolean (!this.val.equals(value(x)));
   }

}
