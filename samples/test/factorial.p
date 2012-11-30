fun factorial {
   local n = 0;
   local product = 1;

   for (n=args[0]; n>0; n=n-1) {
      product = product * n;
   }

   return product;
}


print factorial (7);

