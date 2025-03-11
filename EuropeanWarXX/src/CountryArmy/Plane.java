package CountryArmy;

public class Plane {
   private String name;
   private int cost;
   private int attackDamage;
   private int defense;

   public Plane(String name, int cost, int attackDamage, int defense) {
      this.name = name;
      this.cost = cost;
      this.attackDamage = attackDamage;
      this.defense = defense;
   }

   public String getName() {
      return name;
   }

   public int getCost() {
      return cost;
   }

   public int getAttackDamage() {
      return attackDamage;
   }

   public int getDefense() {
      return defense;
   }

   public String toString() {
      return this.name + ", " + this.cost + "$" + ", " + this.attackDamage + " de ataque" + ", " + this.defense + " de defensa";
   }
}
