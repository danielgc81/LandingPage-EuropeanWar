import java.util.ArrayList;
import java.util.List;

public class Partida {
    private List<Pais> paises;
    private Pais jugador;
    private int ronda;
    private List<List<String>> logsPorRonda;

    public Partida() {
        paises = new ArrayList<>();
        ronda = 1;
        logsPorRonda = new ArrayList<>();
        logsPorRonda.add(new ArrayList<>());
    }

    public void agregarPais(Pais pais) {
        paises.add(pais);
    }

    public void setJugador(Pais jugador) {
        this.jugador = jugador;
    }

    public Pais getJugador() {
        return jugador;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public int getRonda() {
        return ronda;
    }

    public void siguienteRonda() {
        ronda++;
        logsPorRonda.add(new ArrayList<>());
        for (Pais pais : paises) {
            pais.reiniciarMisiles();
            pais.incrementarTurnos();
        }
    }

    public void agregarLog(String log) {
        logsPorRonda.get(ronda - 1).add(log);
    }

    public String getLogRondaAnterior() {
        if (ronda <= 1) {
            return "";
        }
        List<String> eventosRondaAnterior = logsPorRonda.get(ronda - 2);
        return String.join("\n", eventosRondaAnterior);
    }

    public boolean hayGanador() {
        int paisesVivos = 0;
        for (Pais pais : paises) {
            if (pais.getVida() > 0) {
                paisesVivos++;
            }
        }
        return paisesVivos <= 1;
    }

    public Pais getGanador() {
        for (Pais pais : paises) {
            if (pais.getVida() > 0) {
                return pais;
            }
        }
        return null;
    }
}