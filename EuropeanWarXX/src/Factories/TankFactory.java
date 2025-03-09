package Factories;

import CountryArmy.Tank;


public class TankFactory {
   public static Tank createTank (String name) {
      if (name.equals("Tiger I")) {
         return new Tank("Tiger I", 50000, 80, 20);
      } else if (name.equals("Panther")) {
         return new Tank("Panther", 200000, 165, 80);
      } else if (name.equals("Sherman M4")) {
         return new Tank("Sherman M4", 150000, 145, 65);
      } else if (name.equals("T-34")) {
         return new Tank("T-34", 75000, 95, 30);
      } else if (name.equals("KV-2")) {
         return new Tank("KV-2", 105000, 100, 75);
      } else {
         return null;
      }
   }
}
