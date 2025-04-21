package Countries;

public class Country {
   protected String name;
   protected int defenseMisils;
   protected int attackMisils;

   protected final int USED_MISILS = 50;

   public Country () {}

   public Country (String name) {
      this.name = name;
   }

   public void setDefenseMisils (int usedDefenseMisils) {
      this.defenseMisils = usedDefenseMisils;
   }

   public int getDefenseMisils () {
      return defenseMisils;
   }

   public void setAttackMisils (int usedAttackMisils) {
      this.attackMisils = usedAttackMisils;
   }

   public int getAttackMisils () {
      return attackMisils;
   }

   public boolean checkMisilsRule () {
      if (!(getAttackMisils() + getDefenseMisils() == USED_MISILS)) {
         return false;
      }
      return true;
   }
}
