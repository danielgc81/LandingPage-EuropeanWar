package Countries;
class Alemania extends Pais {
   private double bonusAtaque;
   
   public Alemania(String nombre, int vidas, int misiles, int tecnologia) {
       super(nombre, vidas, misiles, tecnologia);
       this.bonusAtaque = 1.3;
   }
   
   @Override
   public void habilidadNacional() {
       System.out.println(getNombre() + " utiliza su habilidad nacional: Blitzkrieg");
       System.out.println("Efecto: Aumenta el daño de ataque en un " + ((bonusAtaque - 1) * 100) + "%");
   }
   
   @Override
   public int getMisiles() {
  
       return (int)(super.getMisiles() * bonusAtaque);
   }
   
   @Override
   public String generarHabilidadTecnologica() {
       int nivel = getTecnologia();
       switch (nivel) {
           case 6: return "Misiles V2 Mejorados";
           case 7: return "Tanques Avanzados Tiger II";
           case 8: return "Sistema de Inteligencia Enigma+";
           default: return "Ingeniería Alemana de Nivel " + nivel;
       }
   }
}