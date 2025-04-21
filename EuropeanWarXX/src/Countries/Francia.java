package Countries;

class Francia extends Pais {
   
   public Francia(String nombre, int vidas, int misiles, int tecnologia) {
       super(nombre, vidas, misiles, tecnologia);
       this.bonusDiplomatico = 1.2;
   }
   
   @Override
   public void habilidadNacional() {
       System.out.println(getNombre() + " utiliza su habilidad nacional: Diplomacia Eficaz");
       System.out.println("Efecto: Reduce el daño recibido en un " + ((bonusDiplomatico - 1) * 100) + "%");
   }
   
   @Override
        
   public void decrementarVidas(int cantidad) {
        int danoReducido = (int) (cantidad * (1 - (bonusDiplomatico - 1)));
        System.out.println("Diplomacia Eficaz reduce el daño de " + cantidad + " a " + danoReducido);
        super.decrementarVidas(danoReducido);
    }
   
   
   @Override
   public String generarHabilidadTecnologica() {
       int nivel = getTecnologia();
       switch (nivel) {
           case 4: return "Misiles de Precisión Francesa";
           case 5: return "Red de Espionaje Internacional";
           case 6: return "Programa Nuclear Defensivo";
           default: return "Avance Tecnológico Francés " + nivel;
       }
   }
}