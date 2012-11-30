$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
$ program: for.p
$ author: Gregory Kip
$
$ notes: test of for loop functionality
$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

print "VERY simple -- no BLOCK";
for (i = 1; i<2; i=i+1) print i;


print;
print "Testing basic for loop functionality... zero to nine";
for (i=0; i<10; i=i+1) {
   print i;
}


print;
print "Testing with permutation functinality...";
for (p=(1 2) * (1 2 3 4 5); p!=(); p = p*p) {
   print p;
}


print;
print "Testing continue and break... no 3 in second column";
for (x=0; x<5; x=x+1) {
   for (y=1; y<5; y=y+1) {
      if ((y%3)==0) continue;
      if (x == 3) break;
      print x + ", " + y;
   }
}


print;
print "This is the real test... this loop should break on 3...";
print "if the program loops, the test fails...";
print "good on you, Turing and Cantor, for anticipating the need for Ctrl-c";
for (i=0; true; i=i+1) {
   if (i==3) break;
   print i;
}

print;
$print "error here -- i is not defined";
$print i;
