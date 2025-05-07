public class Combate {
    public void atacar(Pais atacante, Pais atacado, int misilesAtaque, int misilesDefensa) {
        int dano = (int) (misilesAtaque * atacante.getBonoAtaque());
        int defensa = (int) (misilesDefensa / 8.0);
        int danoFinal = Math.max(0, dano - defensa);
        atacado.decrementarVida(danoFinal);
    }
}