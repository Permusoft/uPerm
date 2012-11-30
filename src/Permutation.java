/*
 * Class: Permutation
 * Author: Gregory Kip (gk2131)
 *
 * Copyright (C) 2008 Permusoft Corporation.
 * All rights reserved.
 */

/*
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 * 
 * 1) Redistributions of source code must retain the above copyright notice, this list of conditions and
 * the following disclaimer.
 *
 * 2) Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 * and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3) Neither the name of Permusoft Corporation nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written permission.
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

import java.lang.StringBuilder;

public final class Permutation {

   private int degree;   // Used to mimic behavior inside the symmetric group of order degree
   private int supremum; // The largest integer actually acted upon by this permutation

   private int seed; // The first integer to be mapped.
   private int prev; // The most recently mapped integer.
   private boolean isFinal; // Can we call attach() to this?
   private boolean zeroIsMapped;

   // We call a Permutation object ill-formed iff some integer n with 0 < n <= size is not
   // represented in P. Such a mapping is not a bijection and therefore not a permutation.
   // This variable contains the smallest such unrepresented number if this object is
   // ill-formed, -1 otherwise.
   private int unrepresentedInt;

   final private static int arraySize;
   final private static int defaultDegree;
   private static Permutation buffer; // A buffer for permutation operations.
   private static StringBuilder cyclicProductFormStringBuilder;
   private static StringBuilder imageFormStringBuilder;

   protected int [] P; // The permutation in image form. P[0] is initialized but not used

   static {
      arraySize = 101; // We work within the symmetric group of order 100
      defaultDegree = 10; // Arbitrary
      buffer = new Permutation ();
      cyclicProductFormStringBuilder = new StringBuilder();
      imageFormStringBuilder = new StringBuilder();
   }


   public Permutation () {
      this (defaultDegree);
   }


   public Permutation (int degree) {
      if (degree <= 0 || degree >= arraySize) {
         this.degree = defaultDegree; // Instead of an exception
      } else {
         this.degree = degree;
      }

      supremum = -1;
      seed = prev = -1;
      isFinal = false;
      zeroIsMapped = false;
      unrepresentedInt = -1;

      P = new int [arraySize]; // We index from 1
      for (int i = 0; i < arraySize; i++) P[i] = i; // Initialize P to the identity permutation.
   }


   // Intended for internal creation of new Permutation objects using this
   // class's static Permutation buffer, and for deep copies generally.
   // image must have length arraySize
   private Permutation (int [] image, int degree) {
      if (image.length != arraySize)
         throw new IllegalArgumentException ("invalid image length");
   
      if (degree <= 0 || degree >= arraySize) {
         this.degree = defaultDegree; // Instead of the exception
      } else {
         this.degree = degree;
      }

      supremum = -1;
      seed = prev = -1;
      isFinal = true;
      unrepresentedInt = -1;

      P = new int [arraySize];

      System.arraycopy (image, 0, P, 0, arraySize);
      zeroIsMapped = P[0] != 0;

      for (int i = 1; i < arraySize; i++) {
         supremum = (P[i] > supremum && P[i] != i) ? P[i] : supremum;
      }

   }


   // Copy constructor
   // Returns a new Permutation identical to A, but which is finalized.
   // If A is null or not consistent, raise an exception.
   public Permutation (Permutation A) {
      if (A == null) throw new IllegalArgumentException ("null argument");
      if (!A.isWellFormed()) throw new uPermIllformedPermutationException ("ill-formed permutation argument");

      System.arraycopy (P, 0, A.P, 0, arraySize);

      supremum = A.supremum();
      degree = A.degree();

      seed = prev = -1;
      isFinal = true;
      zeroIsMapped = P[0] != 0;
      unrepresentedInt = -1;
   }


   public Permutation deepCopy () {
      return new Permutation (P, degree);
   }


   final public boolean isSeeded () {
      return (0 < seed && seed < arraySize);
   }


   // The following two functions, attach(int) and complete() are
   // used for adding integers to the permutation one at a time.
   //
   // Exmaple:
   //  The permutation (1 2 3) is created with the following code:
   //    Permutation P = new Permutation();
   //    P.attach (1);
   //    P.attach (2);
   //    P.attach (3);
   //    P.complete();
   //
   // If this permutation has not yet been seeded, set seed = i.
   // Otherwise, set P[prev] = i and prev = i.
   public void attach (int i) {
      if (isFinal) throw new RuntimeException ("cannot attach() to a finalized permutation");

      if (i < 0 || i >= arraySize) {
         throw new IllegalArgumentException("integer " + Integer.toString(i) + " out of bounds");
      }

      if (seed < 0) seed = prev = i;
      else {
         setImage (prev, i);
         prev = i;
      }

      zeroIsMapped = P[0] != 0;
   }


   // Sets P[prev] = seed.
   // Used for completing the cycle of mappings after parsing a PERMUTATION node in the AST.
   // IMPORTANT NOTE: this permutation is NOT made immutable by a call to complete(), though
   // that wouldn't be a bad idea.
   public void complete () {
      setImage (prev, seed);
      finalize();
   }


   public void finalize () {
      isFinal = true;
   }


   // Update the mapping. Set P[of] = to, both positive.
   // Update the size of the array and the supremum, if necessary.
   //
   // IMPORTANT NOTE: calls to this function should NOT be made
   //  in conjunction with calls to attach() or complete().
   //  This function does NOT modify the seed or prev variables.
   public void setImage (int of, int to) {
      if (isFinal) throw new RuntimeException ("cannot setImage() into a finalized permutation.");

      if (of < 0 || of >= arraySize || to < 0 || to >= arraySize) {
         throw new IllegalArgumentException
            ("cannot set image " + Integer.toString(of) + " -> " + Integer.toString(to));
      }

      degree = (of > degree) ? of : degree;
      degree = (to > degree) ? to : degree;
      
      supremum = (of > supremum) ? of : supremum;
      supremum = (to > supremum) ? to : supremum;

      P[of] = to;

      zeroIsMapped = P[0] != 0;
   }


   // This function lies a litte... it throws an exception if i <= 0...
   // but not if i > degree. In that case it just returns i... these
   // semantics could be a little cleaner
   public int imageOf (int i) {
      if (i < 0) throw new IllegalArgumentException ("negative argument");
      return (i < degree) ? P[i] : i;
   }


   // We compute the product this*Q and store the result in this.
   // Note that this is on the left and Q the right.
   // If this or Q is ill-formed, or if Q is null, we throw an exception.
   // We use the static Permutation buffer to compute the product.
   public void multiply (Permutation Q) {

      if (Q == null) throw new IllegalArgumentException ("null argument");

      if (!Q.isWellFormed() || !this.isWellFormed())
         throw new uPermIllformedPermutationException ("ill-formed argument");

      supremum = -1;

      // Accumulate the product into buffer. Determine the supremum along the way.
      for (int i = 0; i < arraySize; i++) {
         buffer.P[i] = Q.P[this.P[i]];
         supremum = (buffer.P[i] > supremum && buffer.P[i] != i) ? buffer.P[i] : supremum;
      }

      System.arraycopy (P, 0, buffer.P, 0, arraySize);
      degree = (degree < Q.degree()) ? Q.degree : degree;
      isFinal = true;
      zeroIsMapped = P[0] != 0;
   }


   // We compute the product A*B using the static permutation buffer of this class,
   // and return a new Permutation object containing the product.
   // If A or B is null, or either is ill-formed, we throw an exception
   public static Permutation multiply (Permutation A, Permutation B) {

      final int degA, degB, degAB;

      if (A == null || B == null) throw new IllegalArgumentException ("null argument");

      if (!A.isWellFormed() || !B.isWellFormed())
         throw new uPermIllformedPermutationException ("ill-formed argument");

      degA = A.degree();
      degB = B.degree();
      degAB = (degA < degB) ? degB : degA;

      for (int i = 0; i < arraySize; i++) buffer.P[i] = B.P[A.P[i]];

      buffer.setSupremum();
      buffer.setDegree (degAB);

      return buffer.deepCopy();
   }


   /* TODO
   public Permutation power (int n) { }
   public static Permutation power (Permutation P, int n) { }
   */

   
   // Invert this permutation in place. The degree and supremum do not change.
   public void invert () {
      for (int i = 0; i < arraySize; i++) buffer.P[this.P[i]] = i;
      System.arraycopy (P, 0, buffer.P, 0, arraySize);
      isFinal = true;
   }


   // Return the inverse of this as a new permutation object.
   public Permutation inverse () {
      for (int i = 0; i < arraySize; i++) buffer.P[this.P[i]] = i;
      buffer.setSupremum();
      buffer.setDegree(degree);
      return buffer.deepCopy();
   }


   // We return a new permutation that is the inverse of Q
   // If Q is null or ill-formed, we throw an exception.
   public static Permutation inverseOf (Permutation Q) {

      if (Q == null) throw new IllegalArgumentException ("null argument");

      if (!Q.isWellFormed())
         throw new uPermIllformedPermutationException ("ill-formed argument");

      for (int i = 0; i < arraySize; i++) buffer.P[Q.P[i]] = i;

      buffer.setSupremum();
      buffer.setDegree (Q.degree());

      return buffer.deepCopy();
   }


   // We search the permutation and determine its supremum.
   public void setSupremum() {
      supremum = -1;
      for (int i = 0; i < arraySize; i++) {
         supremum = (P[i] > supremum && P[i] != P[i]) ? P[i] : supremum;
      }
   }


   // Set this object's degree field.
   // If d < 1 or d >= arraySize, throw an IllegalArgumentException.
   // If d < supremum, we set degree = supremum.
   public void setDegree (int d) {
      if (d < 0 || d >= arraySize)
         // TODO: add reference to LRM
         throw new IllegalArgumentException ("argument out of bounds.");
      degree = (d < supremum) ? supremum : degree;
   }


   // We return true if the image forms of this and Q are identical.
   // If Q is null or ill-formed, we throw an exception.
   public boolean equals (Permutation Q) {
  
      if (Q == null) throw new IllegalArgumentException ("null argument");

      if (!Q.isWellFormed())
         throw new uPermIllformedPermutationException ("ill-formed argument");

      for (int i = 0; i < arraySize; i++) if (P[i] != Q.P[i]) return false;

      return true;
   }


   // Return true if the image forms of A and B are identical.
   // If A or B is null, or either is ill-formed, we throw an exception.
   public static boolean areEqual (Permutation A, Permutation B) {
  
      if (A == null || B == null) throw new IllegalArgumentException ("null argument");

      if (!A.isWellFormed() || !B.isWellFormed())
         throw new uPermIllformedPermutationException ("ill-formed argument");

      for (int i = 0; i <= A.degree(); i++) if (A.P[i] != B.P[i]) return false;

      return true;
   }


   // Return true if this is the identity permutation, false otherwise.
   public boolean isIdentity () {
      for (int i = 0; i < arraySize; i++) if (P[i] != i) return false;
      return true;
   }


   public boolean isFinal() {
      return isFinal;
   }


   // Return true if the underlying image representation is consistent,
   // i.e. if every integer in {1 .. degree} is represented in the permutation.
   public boolean isWellFormed() {
      boolean isWellFormed = true;
      boolean [] isRepresented = new boolean [arraySize];
      
      int i;
      for (i = 0; i < arraySize; i++) isRepresented[i] = false; // Initialize.
      for (i = 0; i < arraySize; i++) isRepresented[P[i]] = true;
      for (i = 0; i < arraySize; i++) isWellFormed &= isRepresented[i];

      // find the lowest integer not represented in P
      for (i = 0; i < arraySize; i++) if (!isRepresented[i]) unrepresentedInt = i;
      
      return isWellFormed;
   }


   public int unrepresentedInt() {
      return unrepresentedInt;
   }


   public int degree() {
      return degree;
   }


   public int supremum() {
      return supremum;
   }


   // Return a string with the full arraySize image of P
   public String fullPermutationImageString() {
      imageFormStringBuilder.delete (0, imageFormStringBuilder.length());

      imageFormStringBuilder.append("\\");

      for (int i = 0; i < arraySize; i++) {
         if (i != degree) imageFormStringBuilder.append (P[i] + " ");
         else imageFormStringBuilder.append (P[i]);
      }

      imageFormStringBuilder.append("\\");

      return imageFormStringBuilder.toString();
   }


   public String toString() {
      return toImageFormString();
   }


   public String toImageFormString() {
      int i;

      imageFormStringBuilder.delete (0, imageFormStringBuilder.length());

      imageFormStringBuilder.append("\\");

      for (i = (zeroIsMapped) ? 0 : 1; i <= supremum; i++) {
         if (i != supremum) imageFormStringBuilder.append (P[i] + " ");
         else imageFormStringBuilder.append (P[i]);
      }

      imageFormStringBuilder.append("\\");

      return imageFormStringBuilder.toString();
   }


   public String toDoubleImageFormString() {
      int i;

      imageFormStringBuilder.delete (0, imageFormStringBuilder.length());

      imageFormStringBuilder.append("\\");

      for (i = (zeroIsMapped) ? 0 : 1; i <= supremum; i++) {
         imageFormStringBuilder.append (i + " ");
      }

      imageFormStringBuilder.append("\\\n");

      imageFormStringBuilder.append("\\");

      for (i = (zeroIsMapped) ? 0 : 1; i <= supremum; i++) {
         if (i != supremum) imageFormStringBuilder.append (P[i] + " ");
         else imageFormStringBuilder.append (P[i]);
      }

      imageFormStringBuilder.append("\\");

      return imageFormStringBuilder.toString();
   }


   public String toCyclicProductFormString () {
      int start, i;
      
      cyclicProductFormStringBuilder.delete (0, cyclicProductFormStringBuilder.length());


      // Is this permutation the identity?
      if (this.isIdentity()) {
         cyclicProductFormStringBuilder.append ("()");
         return cyclicProductFormStringBuilder.toString();
      }

      // Create and initialize an array "visited" of booleans,
      // where visited[i] == true indicates that this algorithm
      // has visited item i. At the end of the algorithm, all
      // items will have been visited and possibly printed.
      // If the permutation is not correct, this function will loop infinitely.
      boolean[] visited = new boolean[arraySize];
      for (i = 0; i < arraySize; i++) visited[i] = false;
      
      for (start = 0; start < arraySize; start++) {

         if (!visited[start]) {
            boolean printThisCycle = P[start] != start;
            
            if (printThisCycle) cyclicProductFormStringBuilder.append ("(");
            
            i = start;

            do {
               if (printThisCycle) {
                  if (P[i] == start) cyclicProductFormStringBuilder.append (i);
                  else cyclicProductFormStringBuilder.append (i + " ");
               }

               visited[i] = true;
               i = P[i];
            } while (i != start);
            
            if (printThisCycle) cyclicProductFormStringBuilder.append (") ");
         }
      }

      return cyclicProductFormStringBuilder.toString();
   }

}
