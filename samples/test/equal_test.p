q=false;
i=100;
s="hello";
p=(1 2 3 4 5);
l=[q,i,s,p];

print "Compare like types -----------------";
print "false -> " + (q == true);
print "true -> " + (i == 100);
print "false -> " + (s == "hello ");
print "true -> " + (p == (3 4 5 1 2));
print "false -> " + (l == [q, i, s, ()]);
print "------------------------------------";

print;
print "Compare different types. All false.";
print "------------------------------------";
print q == i;
print q == s;
print q == p;
print q == p(3);
print q == l;
print q == l[1:3];
print i == q;
print s == q;
print s == q;
print p == q;
print p(3) == q;
print l == q;
print l[2:4] == q;
print i == s;
print i == p;
print i == l;
print i == l[0:3];
print s == i;
print p == i;
print l == i;
print s == p;
print s == l;
print s == l[1:3];
print p == s;
print l == s;
print l[1:3] == s;
print "------------------------------------";


