fun f {
   if ((sizeof args % 2) == 0) return sizeof args;
   else {
      $ make sure the return value makes it out of the block
      $ this should be fine, but at one point it was a problem
      return ("ODD " + sizeof args);
   }
}

print (f(1,2,3,4,5));
print f([1,2,3,4,5,6]);
print f((1 2 3 4 5) * (5 4 6 3), "Arg2");


break;

fun f2 {
   print "f2(): ENTER";

   local n = args[0];
   print "called with " + n;
   if (n <= 1) {
      print "recursion bottomed out... returning " + 1;
      return 1;
   } else {
      print "recursing left with n == " + n + ", (n-1) == " + (n-1);
      n1 = f2(n-1);
      print "recursing right with n == " + n + ", (n-2) == " + (n-2);
      n2 = f2(n-2);
      print "returning " + n1 + ", " + n2;

      return n1 + n2;
   }

   print "f2(): EXIT";
}


fun fib {
   local n = args[0];
   if (n == 1) return 0;
   if (n == 2) return 1;
   return fib(n-1) + fib(n-2);
}

print f2(8);
print fib(8);

