/*
 * Class: uPermBoolean
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


class uPermBoolean extends uPermType {

   private boolean val;

   public uPermBoolean () {
      super (uPermTypeType.BOOLEAN);
      this.val = false;
   }

   public uPermBoolean (boolean val) {
      super (uPermTypeType.BOOLEAN);
      this.val = val;
   }


   public uPermBoolean (String s) {
      super (uPermTypeType.BOOLEAN);
      this.val = Boolean.parseBoolean (s);
   }


   public uPermType touPermString () {
      if (this.val) return new uPermString("true");
      return new uPermString("false");
   }


   public String toString () {
      return Boolean.toString (this.val);
   }


   public boolean value (uPermType x) {
      if (x.type != uPermTypeType.BOOLEAN)
         throw new uPermIllegalArgumentException ("value expects BOOLEAN, found " + x.type);
      return ((uPermBoolean)x).val;
   }


   public uPermType sizeof () {
      return new uPermInteger (1);
   }


   public boolean valueOf () {
      return this.val;
   }


   public uPermType and (uPermType x) {
      if (x.type != uPermTypeType.BOOLEAN)
         throw new uPermIllegalArgumentException ("&& expects BOOLEAN, found " + x.type);

      return new uPermBoolean (this.val && value(x));
   }


/*
   public uPermType andEq (uPermType x) {
      if (x.type != uPermTypeType.BOOLEAN)
         throw new uPermIllegalArgumentException ("&= expects BOOLEAN, found " + x.type);
      this.val = this.val && value(x);
   }
*/


   public uPermType or (uPermType x) {
      if (x.type != uPermTypeType.BOOLEAN)
         throw new uPermIllegalArgumentException ("|| expects BOOLEAN, found " + x.type);

      return new uPermBoolean (this.val || value(x));
   }


/*
   public uPermType xor  (uPermType x) {
      if (x.type != uPermTypeType.BOOLEAN)
         throw new uPermIllegalArgumentException ("|= expects BOOLEAN, found " + x.type);
      return new uPermBoolean (this.val ^ value(x));
   }
*/


   public uPermType not () {
      return new uPermBoolean (!this.val);
   }


   public uPermType plus (uPermType x) {
      if (x.type != uPermTypeType.STRING)
         throw new uPermIllegalOperationException ("cannot coerce BOOLEAN to " + x.type);
      return new uPermString (this.toString() + x.toString());
   }


   public uPermType equal (uPermType x) {
      if (x.type != uPermTypeType.BOOLEAN) return new uPermBoolean (false);
      return new uPermBoolean (this.val == value(x));
   }


   public uPermType notequal (uPermType x) {
      if (x.type != uPermTypeType.BOOLEAN) return new uPermBoolean (true);
      return new uPermBoolean (this.val != value(x));
   }

}
