import "dpfib.p";

print dpfib(5);
print dpfib(10);
print dpfib(99);

for (i=1; i<100; i=i+1) {
   if (dpfib(i) < 0) {
      print i;
      break;
      print "inside the if, after the break";
      $:control;
   }
   print "inside the for, after the if";
   $:control;
}
