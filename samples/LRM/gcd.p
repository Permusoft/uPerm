fun gcd {
   $ If you rename your arguments, it's a good idea to declare them as local. '
   $ Here they must be declared locally, because this function is recursive
   local m = args[0];
   local n = args[1];
   if (m%n == 0) return n;
   return gcd (n, m%n);
}

print gcd(377, 2436);
print gcd(13, 101);
