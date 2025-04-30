public class Francia extends Pais {
    public Francia(String nombre, int vida, int misiles, double defensas) {
        super(nombre, vida, misiles, defensas, new FranciaHabilidadEspecial());
    }

    @Override
    public void aplicarHabilidadPasiva() {
        if (getTurnosDesdeInicio() % 3 == 0) {
            incrementarDefensas(0.05); // Aumenta las defensas en 5% cada 3 turnos
        }
    }
}