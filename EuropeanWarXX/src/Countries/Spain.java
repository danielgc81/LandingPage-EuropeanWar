package Countries;
class Espana extends Pais {
   private double bonusDefensaIberica;
   
   public Espana(String nombre, int vidas, int misiles, int tecnologia) {
       super(nombre, vidas, misiles, tecnologia);
       this.bonusDefensaIberica = 1.35;
   }
   
   @Override
   public void habilidadNacional() {
       System.out.println(getNombre() + " utiliza su habilidad nacional: Defensa Ibérica");
       System.out.println("Efecto: Aumenta la capacidad defensiva en un " + ((bonusDefensaIberica - 1) * 100) + "%");
   }
   
   @Override
   public void decrementarVidas(int cantidad) {
      
       int danoReducido = (int)(cantidad / bonusDefensaIberica);
       System.out.println("Defensa Ibérica reduce el daño de " + cantidad + " a " + danoReducido);
       super.decrementarVidas(danoReducido);
   }
   
   @Override
   public String generarHabilidadTecnologica() {
       int nivel = getTecnologia();
       switch (nivel) {
           case 3: return "Guerrilla Tecnológica";
           case 4: return "Defensa Costera Avanzada";
           case 5: return "Alianza Estratégica";
           default: return "Avance Defensivo Español " + nivel;
       }
   }
}