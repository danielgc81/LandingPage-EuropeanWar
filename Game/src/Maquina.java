import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maquina {
    private Random random;

    public Maquina() {
        random = new Random();
    }

    public void jugarTurno(List<Pais> paises, Combate combate, List<String> logs, Partida partida) {
        List<Pais> paisesVivos;

        for (Pais atacante : paises) {
            if (atacante.getVida() <= 0 || partida.getJugador() == atacante
                    || partida.misilesGastados.getOrDefault(atacante, false)) {
                continue;
            }

            paisesVivos = new ArrayList<>();
            for (Pais posibleObjetivo : paises) {
                if (posibleObjetivo.getVida() > 0 && posibleObjetivo != atacante) {
                    paisesVivos.add(posibleObjetivo);
                }
            }

            if (paisesVivos.isEmpty()) {
                continue;
            }

            int misilesDefensa = random.nextInt(26);
            int misilesAtaque = 50 - (2 * misilesDefensa);
            atacante.setMisilesAtaque(misilesAtaque);
            atacante.setMisilesDefensa(misilesDefensa);

            Pais objetivo = paisesVivos.get(random.nextInt(paisesVivos.size()));
            int vidaObjetivoAntes = objetivo.getVida();
            combate.atacar(atacante, objetivo, misilesAtaque, misilesDefensa);
            int danoCausado = vidaObjetivoAntes - objetivo.getVida();
            logs.add(atacante.getNombre() + " atacó a " + objetivo.getNombre() + " con " + misilesAtaque
                    + " misiles, causando " + danoCausado + " de daño.");

            partida.marcarMisilesGastados(atacante);
        }
    }
}