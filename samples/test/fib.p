fun f2 {
   if (args[0] == 1) return 0;
   if (args[0] == 2) return 1;
   return f2(args[0]-1) + f2(args[0]-2);
}

print f2(8);
