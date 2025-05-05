import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Partida {
    private int ronda;
    private Pais jugador;
    private List<Pais> paises;
    private StringBuilder logRondaAnterior;
    Map<Pais, Boolean> misilesGastados;

    public Partida() {
        this.ronda = 1;
        this.paises = new ArrayList<>();
        this.logRondaAnterior = new StringBuilder();
        this.misilesGastados = new HashMap<>();
    }

    public int getRonda() {
        return ronda;
    }

    public void setRonda(int ronda) {
        this.ronda = ronda;
    }

    public Pais getJugador() {
        return jugador;
    }

    public void setJugador(Pais jugador) {
        this.jugador = jugador;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public void agregarPais(Pais pais) {
        paises.add(pais);
        misilesGastados.put(pais, false);
    }

    public void marcarMisilesGastados(Pais pais) {
        misilesGastados.put(pais, true);
    }

    public boolean todosMisilesGastados() {
        for (Pais pais : paises) {
            if (pais.getVida() > 0 && !misilesGastados.getOrDefault(pais, false)) {
                return false;
            }
        }
        return true;
    }

    public void siguienteRonda() {
        logRondaAnterior.setLength(0);
        for (Pais pais : paises) {
            misilesGastados.put(pais, false);
        }
        ronda++;
    }

    public String getLogRondaAnterior() {
        return logRondaAnterior.toString();
    }

    public void agregarLog(String log) {
        logRondaAnterior.append(log).append("\n");
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