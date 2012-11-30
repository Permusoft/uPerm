$$$$$$$$$$$$$$$$$$$$$$$$$$
$ parses list args[0]
$ for element args[1]
$ returns true if it exists
$$$$$$$$$$$$$$$$$$$$$$$$$$

fun search {
   local list = args[0];
   local p = args[1];
   local i = 0;

   for (i=0; i<(sizeof list); i=i+1) {
      if (list[i] == p) return true;
   }

   return false; 
}
