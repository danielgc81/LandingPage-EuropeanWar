public class Polonia extends Pais {
    public Polonia(String nombre, int vida, int misiles, double defensas) {
        super(nombre, vida, misiles, defensas, new PoloniaHabilidadEspecial());
    }

    @Override
    public void aplicarHabilidadPasiva() {
        if (getTurnosDesdeInicio() % 5 == 0) {
            curar(15); // Cura 15 de vida cada 5 turnos
        }
    }
}