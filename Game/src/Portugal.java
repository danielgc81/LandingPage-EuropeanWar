public class Portugal extends Pais {
    public Portugal(String nombre, int vida, int misiles, double defensas) {
        super(nombre, vida, misiles, defensas, new PortugalHabilidadEspecial());
    }

    @Override
    public void aplicarHabilidadPasiva() {
        if (getTurnosDesdeInicio() % 3 == 0) {
            incrementarBonoAtaque(1.1); // Aumenta el daño de los misiles en 10% cada 3 turnos
        }
    }
}