public class PortugalHabilidadEspecial implements HabilidadEspecial {
    private int cooldown = 0;

    @Override
    public void activar(Pais paisAtacante, Pais paisAtacado) {
        paisAtacante.incrementarBonoAtaque(1.5); // Aumenta el daño de los misiles en 50%
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