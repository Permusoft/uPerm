/*
 * Class: uPermPermutation
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


class uPermPermutation extends uPermType {

   private Permutation val;

   public uPermPermutation () {
      super (uPermTypeType.PERMUTATION);
      this.val = new Permutation();
   }


/*
   public uPermPermutation (int size) {
      super (uPermTypeType.PERMUTATION);
      this.val = new Permutation (size);
   }
*/


   public uPermPermutation (Permutation P) {
      super (uPermTypeType.PERMUTATION);

      if (!P.isWellFormed())
         throw new uPermIllformedPermutationException (P + " does not represent the point " + P.unrepresentedInt());

      this.val = P;
   }


   public final uPermType touPermString () {
      return new uPermString (this.val.toCyclicProductFormString());
   }


   public final String toString () {
      return this.val.toCyclicProductFormString();
   }


   public Permutation valueOf() {
      return this.val;
   }


   // The size of a permutation is the largest number it acts upon.
   public uPermType sizeof () {
      return new uPermInteger (this.val.supremum());
   }


   public void attach (int i) {
      this.val.attach (i);
   }


   public void attach (uPermType i) {
      if (i.type != uPermTypeType.INTEGER)
         throw new IllegalArgumentException ("permutation elements must be integers");
      attach (((uPermInteger)i).valueOf());
   }


   public void complete () {
      this.val.complete ();
      if (!this.val.isWellFormed())
         throw new uPermIllformedPermutationException (this.val + " does not represent " + this.val.unrepresentedInt());
   }


   public void setImage (int of, int to) {
      this.val.setImage (of, to);
   }


   public void setImage (int of, uPermType to) { 
      if (to.type != uPermTypeType.INTEGER)
         throw new IllegalArgumentException ("permutation points must be integers");
      setImage (of, ((uPermInteger)to).valueOf());
   }


   public uPermType imageOf (uPermType i) {
      if (i.type != uPermTypeType.INTEGER)
         throw new IllegalArgumentException ("permutation points must be integers");
      return new uPermInteger (this.val.imageOf (((uPermInteger)i).valueOf()));
   }


   public uPermType plus (uPermType x) {
      if (x.type != uPermTypeType.STRING)
         throw new uPermIllegalArgumentException ("+ expected STRING, found " + x.type);
      return new uPermString (this.toString() + x.toString());
   }


   // TODO:
   //   Add the ability to multiply a string by a permutationm with the result of a scrambled string.
   //   String x Permutation -> String
   //   The string must be at least as long as the permutation.
   public uPermType multiply (uPermType x) {
      if (x.type != uPermTypeType.PERMUTATION)
         throw new uPermIllegalArgumentException ("* expected PERMUTATION, found " + x.type);
      return new uPermPermutation (Permutation.multiply (this.val, ((uPermPermutation)x).valueOf()));
   }


/*
   // TODO:
   //   Add the ability to multiply a string by a permutationm with the result of a scrambled string.
   //   String x Permutation -> String
   //   The string must be at least as long as the permutation.
   public void multiplyThisBy (uPermType x) {
      if (x.type != uPermTypeType.PERMUTATION)
         throw new uPermIllegalArgumentException ("*= expected PERMUTATION, found " + x.type);
      this.val.multiply (((uPermPermutation)x).valueOf());
   }
*/


   // TODO:
   //   Add the ability to multiply a string by a permutationm with the result of a scrambled string.
   //   String x Permutation -> String
   //   The string must be at least as long as the permutation.
   public uPermType divide (uPermType x) {
      if (x.type != uPermTypeType.PERMUTATION)
         throw new uPermIllegalArgumentException ("/ expected PERMUTATION, found " + x.type);
      return new uPermPermutation (Permutation.multiply (this.val, ((uPermPermutation)x).valueOf().inverse()));
   }


   public uPermType multInverse () {
      return new uPermPermutation (this.val.inverse());
   }


/*
   // TODO:
   //   Add the ability to multiply a string by a permutationm with the result of a scrambled string.
   //   String x Permutation -> String
   //   The string must be at least as long as the permutation.
   public void divideThisBy (uPermType x) {
      if (x.type != uPermTypeType.PERMUTATION)
         throw new uPermIllegalArgumentException ("/= expected PERMUTATION, found " + x.type);
      this.val.multiply (((uPermPermutation)x).valueOf().inverse());
   }
*/


   public uPermType equal (uPermType x) {
      if (x.type != uPermTypeType.PERMUTATION) return new uPermBoolean (false);
      return new uPermBoolean (this.val.equals (((uPermPermutation)x).valueOf()));
   }


   public uPermType notequal (uPermType x) {
      if (x.type != uPermTypeType.PERMUTATION) return new uPermBoolean (true);
      return new uPermBoolean (!this.val.equals(((uPermPermutation)x).valueOf()));
   }

}
