package Countries;

class Italia extends Pais {
   private double bonusFascista;
   
   public Italia(String nombre, int vidas, int misiles, int tecnologia) {
       super(nombre, vidas, misiles, tecnologia);
       this.bonusFascista = 1.15;
   }
   
   @Override
   public void habilidadNacional() {
       System.out.println(getNombre() + " utiliza su habilidad nacional: Influencia Fascista");
       System.out.println("Efecto: Aumenta la capacidad de producción en un " + ((bonusFascista - 1) * 100) + "%");
   }
   
   @Override
   public void completarDesarrollo() {
       super.completarDesarrollo();
       
       int bonusMisiles = (int)(2 * bonusFascista);
       System.out.println("Influencia Fascista otorga " + bonusMisiles + " misiles adicionales");
   }
   
   @Override
   public String generarHabilidadTecnologica() {
       int nivel = getTecnologia();
       switch (nivel) {
           case 4: return "Bombarderos Estratégicos";
           case 5: return "Defensa Costera Mediterránea";
           case 6: return "Propaganda Tecnológica";
           default: return "Avance Militar Italiano " + nivel;
       }
   }
}