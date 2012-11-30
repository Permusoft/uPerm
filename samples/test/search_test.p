import "search.p";

print;
l=[true, 100, "hello", (1 2 3 4 5), [1,2,3,4,5]];
print "searching " + l;

print "-------------------------------------------------";
print "false -> " + search (l, false);
print "false -> " + search (l, 101);
print "false -> " + search (l,"hello ");
print "false -> " + search (l, (1 3) * (2 4));
print "false -> " + search (l, [1, 2, 3, 4]);
print;
print "true -> " + search (l, true);
print "true -> " + search (l, 100);
print "true -> " + search (l,"hello");
print "true -> " + search (l, (1 2 3 4 5));
print "true -> " + search (l, [1, 2, 3, 4, 5]);
print "-------------------------------------------------";


