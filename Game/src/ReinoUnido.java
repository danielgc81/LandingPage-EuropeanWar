public class ReinoUnido extends Pais {
    public ReinoUnido(String nombre, int vida, int misiles, double defensas) {
        super(nombre, vida, misiles, defensas, new ReinoUnidoHabilidadEspecial());
    }

    @Override
    public void aplicarHabilidadPasiva() {
        if (getTurnosDesdeInicio() % 3 == 0) {
            if (getDefensas() < 0.6) {
                double incremento = Math.min(0.1, 0.6 - getDefensas());
                incrementarDefensas(incremento);
            }
        }
    }
}