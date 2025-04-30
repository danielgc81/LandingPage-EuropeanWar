public class Yugoslavia extends Pais {
    public Yugoslavia(String nombre, int vida, int misiles, double defensas) {
        super(nombre, vida, misiles, defensas, new YugoslaviaHabilidadEspecial());
    }

    @Override
    public void aplicarHabilidadPasiva() {
        if (getTurnosDesdeInicio() % 5 == 0) {
            curar(5); // Cura 5 de vida cada 5 turnos
        }
    }
}