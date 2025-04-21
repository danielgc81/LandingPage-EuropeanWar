package Countries;

class Portugal extends Pais {
   private double bonusAtlantico;
   
   public Portugal(String nombre, int vidas, int misiles, int tecnologia) {
       super(nombre, vidas, misiles, tecnologia);
       this.bonusAtlantico = 1.3;
   }
   
   @Override
   public void habilidadNacional() {
       System.out.println(getNombre() + " utiliza su habilidad nacional: Alianza Atlántica");
       System.out.println("Efecto: Aumenta relaciones diplomáticas y defensas en un " + ((bonusAtlantico - 1) * 100) + "%");
   }
   
   @Override
   public void completarDesarrollo() {
       super.completarDesarrollo();
       
       int bonusVidas = (int)(10 * bonusAtlantico);
       System.out.println("Alianza Atlántica otorga " + bonusVidas + " vidas adicionales");
       incrementarVidas(bonusVidas);
   }
   
   @Override
   public String generarHabilidadTecnologica() {
       int nivel = getTecnologia();
       switch (nivel) {
           case 3: return "Red de Puertos Comerciales";
           case 4: return "Colonias Ultramarinas";
           case 5: return "Intercambio Tecnológico";
           default: return "Tecnología Marítima Portuguesa " + nivel;
       }
   }
}