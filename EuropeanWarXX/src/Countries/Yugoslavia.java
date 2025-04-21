package Countries;


class Yugoslavia extends Pais {
   private double bonusPartisano;
   
   public Yugoslavia(String nombre, int vidas, int misiles, int tecnologia) {
       super(nombre, vidas, misiles, tecnologia);
       this.bonusPartisano = 1.45;
   }
   
   @Override
   public void habilidadNacional() {
       System.out.println(getNombre() + " utiliza su habilidad nacional: Resistencia Partisana");
       System.out.println("Efecto: Aumenta la capacidad de contraataque en un " + ((bonusPartisano - 1) * 100) + "%");
   }
   
   @Override
   public void decrementarVidas(int cantidad) {
       super.decrementarVidas(cantidad);

       if (cantidad > 0) {
           int contraataque = (int)(cantidad * (bonusPartisano - 1));
           System.out.println("Resistencia Partisana genera contraataque de " + contraataque + " puntos de daño");
       }
   }
   
   @Override
   public String generarHabilidadTecnologica() {
       int nivel = getTecnologia();
       switch (nivel) {
           case 3: return "Guerrillas Organizadas";
           case 4: return "Industria de Armamento Subterránea";
           case 5: return "Red de Inteligencia Secreta";
           default: return "Táctica Partisana " + nivel;
       }
   }
}