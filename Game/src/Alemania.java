public class Alemania extends Pais {
    public Alemania(String nombre, int vida, int misiles, double defensas) {
        super(nombre, vida, misiles, defensas, new AlemaniaHabilidadEspecial());
    }

    @Override
    public void aplicarHabilidadPasiva() {
        if (getTurnosDesdeInicio() % 3 == 0) {
            incrementarDefensas(0.15); // Aumenta las defensas en 15% cada 3 turnos
        }
    }
}