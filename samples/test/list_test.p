
a=[];
b=[1,2,3,4,5];
c=[true,false];
d=["Hello", "list!"];
e=[(1 2 3) * (4 5 6) * (2 5), (3 4) * (2 5) / (3 6)];
f=[(1), (), (1 + 1), 9 * (1 + 4), "Hello, " + "list!"];
g=[[[[[1]]]]];
h=[[1,2,[4,5],"Nesting",[(1 2 4) * (4 3)],[[[[1,2,3]]]]],"end"];
j = a+b+c+d+e+f+g+h;

print "a=" + a;
print "b=" + b;
print "c=" + c;
print "d=" + d;
print "e=" + e;
print "f=" + f;
print "g=" + g;
print "h=" + h;
print "j=" + j;


$print [1, 2, 3] + 4;

print "-------------------------------------";
print "Equality testing: ";
print;

print "should be true";
print [1,2,3,4,"five",false,(1 2 3 4 5)] == [1,2,3,4,"five",false,(1 2 3 4 5)];

print "should be false";
print [] == [1];

print "should be false";
print [1] == [];

print "should be false";
print [1] == [2];

print "should be false";
print [1, [2, 3]] == [1, [3, 2]];

print "should be true";
print [1, [2, 3]] == [1, [2, 3]];


