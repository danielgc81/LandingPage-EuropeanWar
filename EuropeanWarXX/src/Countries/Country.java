package Countries;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import CountryArmy.Tank;
import CountryArmy.Plane;
import Factories.PlaneFactory;
import Factories.TankFactory;

public class Country {
   protected String name;
   protected LinkedList<Tank> tanks;
   protected int soldiers;
   protected LinkedList<Plane> planes;
   protected long money;

   public Country(String name, long money, int soldiers) {
      this.name = name;
      this.tanks = new LinkedList<>();
      this.soldiers = soldiers;
      this.planes = new LinkedList<>();
      this.money = money;
   }

   public LinkedList<Tank> getTanks() {
      return tanks;
   }

   private String selectModelTank () {
      Scanner sc = new Scanner(System.in);
      ArrayList<String> nameTanks = new ArrayList<>();
      nameTanks.add("Tiger I");
      nameTanks.add("Panther");
      nameTanks.add("Sherman M4");
      nameTanks.add("T-34");
      nameTanks.add("KV-2");
      nameTanks.forEach(System.out::println);

      boolean nameFound;
      String nameTank;
      do {
         System.out.println("Selecciona el modelo de tanque: ");
         nameTank = sc.nextLine();
         nameFound = false;
         if (nameTanks.contains(nameTank)) {
            nameFound = true;
         } else {
            System.out.println("Modelo de tanque no encontrado");
         }
      } while (!nameFound);
      return nameTank;
   }

   public void buyTanks (int amountTanks) {
      String name = selectModelTank();
      int cost = amountTanks * TankFactory.createTank(name).getCost();
      if (this.money > cost) {
         this.money -= cost;
         for (int i = 0; i < amountTanks; i++) {
            this.tanks.add(TankFactory.createTank(name));
         }
      } else {
         System.out.println("No tienes suficiente dinero para comprar " + amountTanks + " tanques " + name);
      }
   }

   public LinkedList<Plane> getPlanes() {
      return planes;
   }

   private String selectModelPlane () {
      Scanner sc = new Scanner(System.in);
      ArrayList<String> namePlanes = new ArrayList<>();
      namePlanes.add("Heinkel He 111");
      namePlanes.add("Avro Lancaster");
      namePlanes.add("B24-Liberator");
      namePlanes.add("Boeing B-17");
      namePlanes.forEach(System.out::println);

      boolean nameFound;
      String namePlane;
      do {
         System.out.println("Selecciona el modelo de avión: ");
         namePlane = sc.nextLine();
         nameFound = false;
         if (namePlanes.contains(namePlane)) {
            nameFound = true;
         } else {
            System.out.println("Modelo de avión no encontrado");
         }
      } while (!nameFound);
      return namePlane;
   }

   public void buyPlanes (int amountPlanes) {
      String name = selectModelPlane();
      int cost = amountPlanes * PlaneFactory.createPlane(name).getCost();
      if (this.money > cost) {
         this.money -= cost;
         for (int i = 0; i < amountPlanes; i++) {
            this.planes.add(PlaneFactory.createPlane(name));
         }
      } else {
         System.out.println("No tienes suficiente dinero para comprar " + amountPlanes + " aviones " + name);
      }
   }
}
