fun f {
   print args[0];
   print "";
}

f(100);
f(300);
f(123123123);
f((1 2 3 4 5) * (4 5 3 2 1));
f("hello, world!");
f([1,2,3,4,"list"]);
f(true);
f(false);
f(10<30);

$:symtab
