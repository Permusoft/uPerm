b=true;
i=100;
s="hello";
p=(1 2 3 4 5);
l=[1,2,3,4,5];

if (b) print "TRUE";

if (i < 10) {
   i = 10;
   print "10 > " + i + " ??????";
}

if (s == "hello world") {
   print "BAD";
} else {
   print "hello to you";
}

if (p*p*p*p*p != ()) print "The universe is having a bad day";
else print "all is well";

if (l[2:4] == [3,4,5]) print "Good!";
else {
   print "BAD";
   print l[2:4];
}

