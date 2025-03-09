package GameApp;
import Countries.Country;
import CountryArmy.Tank;

public class EuropeanWarXX {
   public static void main(String[] args) {
      Country germany = new Country("Alemania", 100000000, 10000, 100);
      germany.buyTanks(5);
      for (Tank tank : germany.getTanks()) {
         System.out.println(tank);
      }
   }
}
