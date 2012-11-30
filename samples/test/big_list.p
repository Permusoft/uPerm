l=[];
p=();
g=(1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20);

for (i=1; i<=10000; i=i+1) {
   p = p * g;
   l = l + [p];
}

print l;
print sizeof l;
