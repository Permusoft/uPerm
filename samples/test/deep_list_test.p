########################################################################
# program: deep_list_test.p
# author: Gregory Kip
#
# Create a big deep list to test recursive list functionality.
########################################################################
l=["DEEP"];

for (i = 1; i <= 100000; i=i+1) l = l + [l];

print l;
print sizeof l;
