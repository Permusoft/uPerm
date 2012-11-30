/*
 * Class: uPermInteger
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


class uPermInteger extends uPermType {
   protected int val;

   public uPermInteger () {
      super(uPermTypeType.INTEGER);
   }


   public uPermInteger (int val) {
      super(uPermTypeType.INTEGER);
      this.val = val;
   }


   public uPermInteger (String s) {
      super(uPermTypeType.INTEGER);
      this.val = Integer.parseInt(s);
   }


   public uPermType touPermString () {
      return new uPermString (Integer.toString(this.val));
   }


   public String toString () {
      return Integer.toString (this.val);
   }


   private int value (uPermType x) {
      if (x.type != uPermTypeType.INTEGER)
         throw new uPermIllegalArgumentException ("value expected INTEGER, found " + x.type);
      return ((uPermInteger)x).val;
   }


   public int valueOf () {
      return this.val;
   }


   public uPermType sizeof () {
      return new uPermInteger (1);
   }


   public uPermType mod (uPermType x) {
      if (x.type != uPermTypeType.INTEGER)
         throw new uPermIllegalArgumentException ("% expected INTEGER, found " + x.type);
      return new uPermInteger (this.val % value(x));
   }


   public uPermType lt (uPermType x) {
      if (x.type != uPermTypeType.INTEGER)
         throw new uPermIllegalArgumentException ("< expected INTEGER, found " + x.type);
      return new uPermBoolean (this.val < value(x));
   }


   public uPermType le (uPermType x) {
      if (x.type != uPermTypeType.INTEGER)
         throw new uPermIllegalArgumentException ("<= expected INTEGER, found " + x.type);
      return new uPermBoolean (this.val <= value(x));
   }


   public uPermType gt (uPermType x) {
      if (x.type != uPermTypeType.INTEGER)
         throw new uPermIllegalArgumentException ("> expected INTEGER, found " + x.type);
      return new uPermBoolean (this.val > value(x));
   }


   public uPermType ge (uPermType x) {
      if (x.type != uPermTypeType.INTEGER)
         throw new uPermIllegalArgumentException (">= expected INTEGER, found " + x.type);
      return new uPermBoolean (this.val >= value(x));
   }


   public uPermType plus (uPermType x) {
      if (x.type == uPermTypeType.INTEGER) {
         return new uPermInteger (this.val + value(x));
      } else if (x.type == uPermTypeType.STRING) {
         return new uPermString (this.toString() + x.toString());
      }

      throw new IllegalArgumentException ("+ expected INTEGER or STRING, found " + x.type);
   }


/*
   public uPermType plusEq (uPermType x) {
      if (x.type != uPermTypeType.INTEGER)
         throw new uPermIllegalArgumentException ("+= expected INTEGER, found " + x.type);
      return this.val = this.val + value(x);
   }
*/


   public uPermType minus (uPermType x) {
      if (x.type != uPermTypeType.INTEGER)
         throw new uPermIllegalArgumentException ("- expected INTEGER, found " + x.type);
      return new uPermInteger (this.val - value(x));
   }


/*
   public uPermType minusEq (uPermType x) {
      if (x.type != uPermTypeType.INTEGER)
         throw new uPermIllegalArgumentException ("-= expected INTEGER, found " + x.type);
      return this.val = this.val - value(x);
   }
*/


   public uPermType multiply (uPermType x) {
      if (x.type != uPermTypeType.INTEGER)
         throw new uPermIllegalArgumentException ("* expected INTEGER, found " + x.type);
      return new uPermInteger (this.val * value(x));
   }


/*
   public uPermType multiplyEq (uPermType x) {
      if (x.type != uPermTypeType.INTEGER)
         throw new uPermIllegalArgumentException ("*= expected INTEGER, found " + x.type);
      return this.val = this.val * value(x);
   }
*/


   public uPermType divide (uPermType x) {
      if (x.type != uPermTypeType.INTEGER)
         throw new uPermIllegalArgumentException ("/ expected INTEGER, found " + x.type);
      return new uPermInteger (this.val / value(x));
   }


/*
   public uPermType divideEq (uPermType x) {
      if (x.type != uPermTypeType.INTEGER)
         throw new uPermIllegalArgumentException ("/= expected INTEGER, found " + x.type);
      return this.val = this.val / value(x);
   }
*/


   public uPermType equal (uPermType x) {
      if (x.type != uPermTypeType.INTEGER) return new uPermBoolean (false);
      return new uPermBoolean (this.val == value(x));
   }


   public uPermType notequal (uPermType x) {
      if (x.type != uPermTypeType.INTEGER) return new uPermBoolean (true);
      return new uPermBoolean (this.val != value(x));
   }


/*
   public uPermType power (uPermType x) {
      yadda
   }
*/

   public uPermType addInverse () {
      return new uPermInteger (-this.val);
   }

}
