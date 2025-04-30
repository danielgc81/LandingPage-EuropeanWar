public class Espana extends Pais {
    public Espana(String nombre, int vida, int misiles, double defensas) {
        super(nombre, vida, misiles, defensas, new EspanaHabilidadEspecial());
    }

    @Override
    public void aplicarHabilidadPasiva() {
        if (getTurnosDesdeInicio() % 5 == 0) {
            curar(10); // Cura 10 de vida cada 5 turnos
        }
    }
}