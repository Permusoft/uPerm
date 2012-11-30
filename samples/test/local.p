n = 100;

print n;

{
   local n = 1000;

   print n;


   {
      n = (1 2 3 4 5);
      print n;

   }


   print n;

}

print n;



fun f {
   n = "dynamic scope";
}


f();

print n;
