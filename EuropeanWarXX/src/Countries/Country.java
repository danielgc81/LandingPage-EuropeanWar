package Countries;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import CountryArmy.Tank;
import Factories.TankFactory;

public class Country {
   protected String name;
   protected LinkedList<Tank> tanks;
   protected int soldiers;
   protected int planes;
   protected long money;

   public Country(String name, long money, int soldiers, int planes) {
      this.name = name;
      this.tanks = new LinkedList<>();
      this.soldiers = soldiers;
      this.planes = planes;
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
}
