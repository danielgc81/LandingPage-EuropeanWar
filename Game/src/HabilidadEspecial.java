public interface HabilidadEspecial {
    void activar(Pais paisAtacante, Pais paisAtacado);
    int getCooldown();
    void setCooldown(int cooldown);
}