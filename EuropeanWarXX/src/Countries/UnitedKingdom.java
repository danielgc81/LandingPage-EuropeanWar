package Countries;


class ReinoUnido extends Pais {
   private double bonusNaval;
   
   public ReinoUnido(String nombre, int vidas, int misiles, int tecnologia) {
       super(nombre, vidas, misiles, tecnologia);
       this.bonusNaval = 1.25;
   }
   
   @Override
   public void habilidadNacional() {
       System.out.println(getNombre() + " utiliza su habilidad nacional: Dominio de los Mares");
       System.out.println("Efecto: Aumenta la defensa y ataque naval en un " + ((bonusNaval - 1) * 100) + "%");
   }
   
   @Override
   public int getTecnologia() {
       
       return (int)(super.getTecnologia() * bonusNaval);
   }
   
   @Override
   public String generarHabilidadTecnologica() {
       int nivel = getTecnologia();
       switch (nivel) {
           case 5: return "Radar Avanzado Británico";
           case 6: return "Flota de Portaaviones";
           case 7: return "Espionaje MI6";
           default: return "Tecnología Naval Británica " + nivel;
       }
   }
}