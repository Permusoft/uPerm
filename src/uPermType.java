/*
 * Class: uPermType
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

class uPermType {

   public final uPermTypeType type;

   public uPermType () {
      this.type = uPermTypeType.UNDEFINED;
   }

   public uPermType (uPermTypeType type) {
      this.type = type;
   }


   public uPermType touPermString () {
      throw new uPermIllegalOperationException
         ("cannot coerce " + this.type + " to string");
   }


   public String toString () {
      throw new uPermIllegalOperationException
          ("untyped objects have no string representation");
   }


   public final uPermString typename () {
      return new uPermString (this.type.toString());
   }


   public uPermType and (uPermType x) {
      throw new uPermIllegalOperationException
          ("Illegal binary operation:\n   " + this.toString() + " '&&' " + x.type);
   }


   public uPermType or (uPermType x) {
      throw new uPermIllegalOperationException
          ("Illegal binary operation:\n   " + this.toString() + " '||' " + x.type);
   }


   public uPermType not () {
      throw new uPermIllegalOperationException
          ("Illegal unary operation:\n   '!' " + this.toString());
   }


   public uPermType mod (uPermType x) {
      throw new uPermIllegalOperationException
          ("Illegal binary operation:\n   " + this.toString() + " '%' " + x.type);
   }


   public uPermType lt (uPermType x) {
      throw new uPermIllegalOperationException
          ("Illegal binary operation:\n   " + this.toString() + " '<' " + x.type);
   }


   public uPermType le (uPermType x) {
      throw new uPermIllegalOperationException
          ("Illegal binary operation:\n   " + this.toString() + " '<=' " + x.type);
   }


   public uPermType gt (uPermType x) {
      throw new uPermIllegalOperationException
          ("Illegal binary operation:\n   " + this.toString() + " '>' " + x.type);
   }


   public uPermType ge (uPermType x) {
      throw new uPermIllegalOperationException
          ("Illegal binary operation:\n   " + this.toString() + " '>=' " + x.type);
   }


   public uPermType sizeof () {
      throw new uPermIllegalOperationException
          ("Illegal builtin operation:\n   sizeof " + this.toString());
   }


   public uPermType plus (uPermType x) {
      throw new uPermIllegalOperationException
          ("Illegal binary operation:\n   " + this.toString() + " '+' " + x.type);
   }


/*
   public uPermType addToThis (uPermType x) {
      throw new uPermIllegalOperationException
          ("Illegal binary operation:\n   " + this.toString() + " '+=' " + x.type);
   }
*/


   public uPermType minus (uPermType x) {
      throw new uPermIllegalOperationException
          ("Illegal binary operation:\n   " + this.toString() + " '-' " + x.type);
   }


/*
   public uPermType subtractFromThis (uPermType x) {
      throw new uPermIllegalOperationException
          ("Illegal binary operation:\n   " + this.toString() + " '-=' " + x.type);
   }
*/


   public uPermType multiply (uPermType x) {
      throw new uPermIllegalOperationException
          ("Illegal binary operation:\n   " + this.toString() + " '*' " + x.type);
   }


/*
   public uPermType multiplyThisBy (uPermType x) {
      throw new uPermIllegalOperationException
          ("Illegal binary operation:\n   " + this.toString() + " '*=' " + x.type);
   }
*/


   public uPermType divide (uPermType x) {
      throw new uPermIllegalOperationException
          ("Illegal binary operation:\n   " + this.toString() + " '/' " + x.type);
   }


/*
   public uPermType divideThisBy (uPermType x) {
      throw new uPermIllegalOperationException
          ("Illegal binary operation:\n   " + this.toString() + " '/=' " + x.type);
   }
*/


   public uPermType append (uPermType x) {
      throw new uPermIllegalOperationException
          ("Illegal binary operation:\n   " + this.toString() + " 'append' " + x.type);
   }


   public uPermType setSlice (uPermType x) {
      throw new uPermIllegalOperationException
          ("Illegal binary operation:\n   " + this.toString() + " 'setSlice' " + x.type);
   }


   public uPermType equal (uPermType x) {
      throw new uPermIllegalOperationException
          ("Illegal binary operation:\n   " + this.toString() + " '==' " + x.type);
   }


   public uPermType notequal (uPermType x) {
      throw new uPermIllegalOperationException
          ("Illegal binary operation:\n   " + this.toString() + " '!=' " + x.type);
   }


/*
   public uPermType power (uPermType x) {
      throw new uPermIllegalOperationException
          ("Illegal binary operation:\n   " + this.toString() + " '**' " + x.type);
   }
*/


   public uPermType multInverse () {
      throw new uPermIllegalOperationException
          ("Illegal unary operation:\n   '~' " + this.toString());
   }


   public uPermType addInverse () {
      throw new uPermIllegalOperationException
          ("Illegal unary operation:\n   '-' " + this.toString());
   }


   public void clear() {
      throw new uPermIllegalOperationException
          ("Illegal clear() invocation:\n   " + this.toString());
   }

}
