
a=[0,1,2,3,4,5];


print "The list:";
print a;


print;
print;
print "Positive indices:";
print "a[] => " + a[];
print "a[:] => " + a[:];
print "a[2] => " + a[2];
print "a[2:2] => " + a[2:2];
print "a[3:] => " + a[3:];
print "a[:3] => " + a[:3];
print "a[1:1] => " + a[1:1];
print "a[1:4] => " + a[1:4];
print "a[4:2] => " + a[4:2];


print;
print;
print "Negative indices:";
print "a[-2] => " + a[-2];
print "a[-3:] => " + a[-3:];
print "a[:-2] => " + a[:-2];
print "a[-2:-2] => " + a[-2:-2];
print "a[2:-3] => " + a[2:-3];
print "a[-3:1] => " + a[-3:1];
print "a[-2:-1] => " + a[-2:-1];


print; print;
print "now try assignment";

a[0] = 5;
a[1] = 4;
a[2] = 3;
a[3] = 2;
a[4] = 1;
a[5] = 0;

print a;
