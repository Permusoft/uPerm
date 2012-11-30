/*
 * Class: uPermSlice
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



public class uPermSlice extends uPermType {

   private uPermList thePie;
   private int left, right;
   private boolean leftIsGood, rightIsGood;

   private boolean isMultivalued;


   public uPermSlice (uPermList L) {
      super (uPermTypeType.SLICE);
      this.thePie = L;
      this.left = this.right = 0;
      this.leftIsGood = this.rightIsGood = false;
      this.isMultivalued = false;
   }


   public void setLeft (uPermType l) {
      if (l.type != uPermTypeType.INTEGER)
         throw new uPermIllegalArgumentException
            ("cannot index a slice with " + l.type + ", must be integer");
      this.left = ((uPermInteger)l).valueOf();
      this.leftIsGood = true;
   }


   public void setMultivalued() {
      this.isMultivalued = true;
   }


   public void setRight (uPermType r) {
      if (r.type != uPermTypeType.INTEGER)
         throw new uPermIllegalArgumentException
            ("cannot index a slice with " + r.type + ", must be integer");
      this.right = ((uPermInteger)r).valueOf();
      this.rightIsGood = true;
   }


   // TODO: fix the problem that list[1] evaluates to a list, not a scalar...
   //       but list[1:1] should be a list!
   public uPermType valueOf () {
      uPermList list = new uPermList();
      int l, r;

      l = (this.leftIsGood && this.left >= 0) ? this.left : 0;
      r = (this.rightIsGood && this.right < this.thePie.list.size()) ? this.right : this.thePie.list.size() - 1;
      this.isMultivalued = this.isMultivalued || !this.leftIsGood || this.rightIsGood; // simplification of (!mv iff l && !r)

      if (this.isMultivalued)
         for (int i = l; i <= r; i++)
            list.list.add (this.thePie.list.elementAt(i));
      else
         return this.thePie.list.elementAt(l);
   
      return list;
   }


   public String toString () {
      return new String (this.thePie + "[" + this.left + ":" + this.right + "] "
                         //+ "lgood: " + this.leftIsGood + ", rgood: " + this.rightIsGood + ", isMV: " + this.isMultivalued
                        );
   }

}
