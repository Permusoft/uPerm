outer = "outer";

print outer;

{

   L1 = "L1";
   print L1;


   {
      L2 = "L2";
      print L2;
      break;
      print L2;
   }

   print L1;

}

outer = "done";

print outer;
