import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maquina {
    private Random random;

    public Maquina() {
        random = new Random();
    }

    public void jugarTurno(List<Pais> paises, Combate combate, List<String> logs) {
        // Lista de países vivos (excluyendo al atacante en cada turno)
        List<Pais> paisesVivos;

        for (Pais atacante : paises) {
            if (atacante.getVida() <= 0) {
                continue; // Saltar si el país está muerto
            }

            // Crear lista de posibles objetivos (todos los países vivos excepto el atacante)
            paisesVivos = new ArrayList<>();
            for (Pais posibleObjetivo : paises) {
                if (posibleObjetivo.getVida() > 0 && posibleObjetivo != atacante) {
                    paisesVivos.add(posibleObjetivo);
                }
            }

            if (paisesVivos.isEmpty()) {
                continue; // No hay objetivos para atacar
            }

            // Distribuir los 50 misiles del atacante
            int totalMisiles = 50;
            int misilesAtaque = random.nextInt(totalMisiles + 1); // Entre 0 y 50
            int misilesDefensa = totalMisiles - misilesAtaque; // El resto para defensa
            atacante.setMisilesAtaque(misilesAtaque);
            atacante.setMisilesDefensa(misilesDefensa);

            // Seleccionar un objetivo aleatorio entre los países vivos
            Pais objetivo = paisesVivos.get(random.nextInt(paisesVivos.size()));
            int vidaObjetivoAntes = objetivo.getVida();
            combate.atacar(atacante, objetivo, misilesAtaque, misilesDefensa);
            int danoCausado = vidaObjetivoAntes - objetivo.getVida();
            logs.add(atacante.getNombre() + " atacó a " + objetivo.getNombre() + " con " + misilesAtaque + " misiles, causando " + danoCausado + " de daño.");
        }
    }
}