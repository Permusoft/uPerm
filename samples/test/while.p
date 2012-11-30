i=0;

print;
print "output should be 1 2 4 5 7 8";
while (true) {
   i = i + 1;
   if ((i % 3) == 0) continue;
   if (i == 10) break;
   print i;
}
