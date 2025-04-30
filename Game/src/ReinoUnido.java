public class ReinoUnido extends Pais {
    public ReinoUnido(String nombre, int vida, int misiles, double defensas) {
        super(nombre, vida, misiles, defensas, new ReinoUnidoHabilidadEspecial());
    }

    @Override
    public void aplicarHabilidadPasiva() {
        if (getTurnosDesdeInicio() % 3 == 0) {
            incrementarDefensas(0.1); // Aumenta las defensas en 10% cada 3 turnos
        }
    }
}