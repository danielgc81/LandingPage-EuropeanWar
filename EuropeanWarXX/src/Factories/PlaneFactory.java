package Factories;

import CountryArmy.Plane;

public class PlaneFactory {
   public static Plane createPlane (String name) {
      if (name.equals("Heinkel He 111")) {
         return new Plane("Heinkel He 111", 200000, 100, 30);
      } else if (name.equals("Avro Lancaster")) {
         return new Plane("Avro Lancaster", 600000, 230, 100);
      } else if (name.equals("B24-Liberator")) {
         return new Plane("B24-Liberator", 450000, 170, 80);
      } else if (name.equals("Boeing B-17")) {
         return new Plane("Boeing B-17", 300000, 125, 50);
      } else {
         return null;
      }
   }
}
