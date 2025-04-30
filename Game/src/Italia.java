public class Italia extends Pais {
    public Italia(String nombre, int vida, int misiles, double defensas) {
        super(nombre, vida, misiles, defensas, new ItaliaHabilidadEspecial());
    }

    @Override
    public void aplicarHabilidadPasiva() {
        if (getTurnosDesdeInicio() % 3 == 0) {
            incrementarBonoAtaque(1.15); // Aumenta el daño de los misiles en 15% cada 3 turnos
        }
    }
}