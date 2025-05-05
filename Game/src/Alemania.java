public class Alemania extends Pais {
    public Alemania(String nombre, int vida, int misiles, double defensas) {
        super(nombre, vida, misiles, defensas, new AlemaniaHabilidadEspecial());
    }

    @Override
    public void aplicarHabilidadPasiva() {
        if (getTurnosDesdeInicio() % 3 == 0) {
            if (getDefensas() < 0.6) {
                double incremento = Math.min(0.15, 0.6 - getDefensas());
                incrementarDefensas(incremento);
            }
        }
    }
}