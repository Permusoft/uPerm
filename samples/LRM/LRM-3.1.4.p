

print;
print "permutation examples from section 3.1.4 of the LRM";
print "permutations are always printed in cyclic form -- it is most succinct";
print;

$ permutations are functions
fun pi {
   if (args[0] == 1) return 3;
   if (args[0] == 2) return 4;
   if (args[0] == 3) return 2;
   if (args[0] == 4) return 5;
   if (args[0] == 5) return 1;
}

print "permutations are functions: pi(2) -> " + pi(2);
print;


$ the image form is simple
$ right sides are execd first, in their entirety
$ we can overwrite a function with a value
$    /  1     2     3     4     5    \ ;
pi = \ pi(1) pi(2) pi(3) pi(4) pi(5) \;

print "pi -> " + pi;
print "pi(2) -> " + pi(2);
print;

$ or rebuild it using cyclic notation
$ now the pi()s are evaluated as permutations
pi = ( 1
       pi(1)
       pi(pi(1))
       pi(pi(pi(1)))
       pi(pi(pi(pi(1))))
     );

print "pi -> " + pi;
print "pi(2) -> " + pi(2);
print;

print "\3 4 2 5 1\ * \4 3 2 1 5\ == \2 1 3 5 4\ -> " +
      (\3 4 2 5 1\ * \4 3 2 1 5\ == \2 1 3 5 4\);
print;

$ pi' from the LRM'
pi2 = \4 3 2 1 5\;

print "pi' -> " + pi2;
print "pi * pi' -> " + (pi * pi2);
print;

pi = (1 2) * (4 5);
print "" + pi + " * " + ~pi + " == " + () + " -> " +
        (  pi     *     ~pi     ==     ());
print;


$ the syntax could be a little more powerful and allow ~pi(2)
pi_inv = ~pi;
print "~pi(2) -> " + pi_inv(2);
print;


$ uncomment the code below to see the ramifications of
$ introducting an ill-formed permutation

$ ill-formed cycle literals will be caught while parsing
$pi = (1 2 1);

$ ill-formed images are not caught upon construction...
pi = \1 2 4 1\;

$ ...only upon use.
$ an error here, or...
$pi = pi * pi;

$ ...upon printing, an infinite loop as the interpreter
$ builds the string. actually this will die after the JVM
$ runs out of heap space, one character at a time
$print "infinite loop...";
$print pi;

