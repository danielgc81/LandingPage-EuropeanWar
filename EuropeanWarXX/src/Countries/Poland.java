package Countries;

class Polonia extends Pais {
   private double bonusResistencia;
   
   public Polonia(String nombre, int vidas, int misiles, int tecnologia) {
       super(nombre, vidas, misiles, tecnologia);
       this.bonusResistencia = 1.4;
   }
   
   @Override
   public void habilidadNacional() {
       System.out.println(getNombre() + " utiliza su habilidad nacional: Resistencia Estoica");
       System.out.println("Efecto: Aumenta la capacidad de supervivencia en un " + ((bonusResistencia - 1) * 100) + "%");
   }
   
   @Override
   public int getVidas() {
       // Bonus de supervivencia por resistencia estoica
       return (int)(super.getVidas() * bonusResistencia);
   }
   
   @Override
   public String generarHabilidadTecnologica() {
       int nivel = getTecnologia();
       switch (nivel) {
           case 4: return "Defensa Anti-Tanque";
           case 5: return "Red de Resistencia Subterránea";
           case 6: return "Comunicaciones Cifradas";
           default: return "Tecnología de Supervivencia Polaca " + nivel;
       }
   }
}