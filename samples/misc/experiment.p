
$ mathematical permutations are functions
fun pi {
   if (args[0] == 1) return 3;
   if (args[0] == 2) return 4;
   if (args[0] == 3) return 2;
   if (args[0] == 4) return 5;
   if (args[0] == 5) return 1;
}
print pi(2);
print;


$ the image form is simple
$ we can overwrite a function with a permutation value
pi = \ pi(1) pi(2) pi(3) pi(4) pi(5) \;

print pi;
print pi(2);
print;

$ or rebuild it using cyclic notation
$ now the pi()s are evaluated as permutations
pi = ( 1
       pi(1)
       pi(pi(1))
       pi(pi(pi(1)))
       pi(pi(pi(pi(1))))
     );

print pi;
print pi(2);
print;

