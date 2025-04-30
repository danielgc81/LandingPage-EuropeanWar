public class EspanaHabilidadEspecial implements HabilidadEspecial {
    private int cooldown = 0;

    @Override
    public void activar(Pais paisAtacante, Pais paisAtacado) {
        paisAtacante.incrementarBonoDefensa(0.3); // Aumenta las defensas en 30%
        cooldown = 3;
    }

    @Override
    public int getCooldown() {
        return cooldown;
    }

    @Override
    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }
}