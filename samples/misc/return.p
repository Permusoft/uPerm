fun list {
   return args;
}

print list (list (1, true, "three", (4 5), [6, 7, 8, [9, 10], 11]), list (100));

fun emptyReturn {
   return;
}

print emptyReturn();
