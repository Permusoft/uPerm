$ dynamic programming version of fibonacci recursive function
$ well, it's not REAL dynamic programming, because it's recursive...

fib = [-1, 0, 1];

$ the poor man's malloc() '
for (i = 3; i<100; i=i+1) fib = fib + [-1];

$ fib[1] and fib[2] are now correct fibonacci numbers
$ the rest of fib is just filler

fun dpfib {
   local n = args[0];
   if (n >= 100) return -1;
   if (fib[n] == -1) fib[n] = dpfib(n-1) + dpfib(n-2);
   return fib[n];
}
