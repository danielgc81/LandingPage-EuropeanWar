import javax.swing.*;

public abstract class Pais {
    private String nombre;
    private int vida;
    private int misiles;
    private double defensas; // Porcentaje de reducción de daño (0.0 a 1.0)
    private HabilidadEspecial habilidadEspecial;
    private ImageIcon imagen;
    private int misilesAtaque;
    private int misilesDefensa;
    private int turnosDesdeInicio;
    private double bonoAtaque; // Multiplicador temporal para el daño de los misiles
    private int duracionBonoAtaque; // Duración restante del bono de ataque
    private double bonoDefensa; // Incremento temporal para las defensas
    private int duracionBonoDefensa; // Duración restante del bono de defensa

    public Pais(String nombre, int vida, int misiles, double defensas, HabilidadEspecial habilidadEspecial) {
        this.nombre = nombre;
        this.vida = vida;
        this.misiles = misiles;
        this.defensas = defensas;
        this.habilidadEspecial = habilidadEspecial;
        this.imagen = new ImageIcon("Recursos/" + nombre.toLowerCase() + ".png");
        this.misilesAtaque = 0;
        this.misilesDefensa = 0;
        this.turnosDesdeInicio = 0;
        this.bonoAtaque = 1.0; // Sin bono inicial
        this.duracionBonoAtaque = 0;
        this.bonoDefensa = 0.0; // Sin bono inicial
        this.duracionBonoDefensa = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getMisiles() {
        return misiles;
    }

    public double getDefensas() {
        return defensas + bonoDefensa;
    }

    public void incrementarDefensas(double incremento) {
        this.defensas += incremento;
        if (this.defensas > 1.0) this.defensas = 1.0; // Límite máximo de 100%
        System.out.println(nombre + " aumenta sus defensas a " + (defensas * 100) + "%");
    }

    public void curar(int cantidad) {
        this.vida += cantidad;
        System.out.println(nombre + " cura " + cantidad + " de vida. Vida actual: " + vida);
    }

    public void incrementarBonoAtaque(double bono) {
        this.bonoAtaque = bono;
        this.duracionBonoAtaque = 1; // Dura 1 turno
        System.out.println(nombre + " aumenta el daño de sus misiles en " + ((bono - 1) * 100) + "% durante 1 turno.");
    }

    public void incrementarBonoDefensa(double bono) {
        this.bonoDefensa = bono;
        this.duracionBonoDefensa = 1; // Dura 1 turno
        System.out.println(nombre + " aumenta sus defensas en " + (bono * 100) + "% durante 1 turno.");
    }

    public double getBonoAtaque() {
        return bonoAtaque;
    }

    public void reducirDuracionBonos() {
        if (duracionBonoAtaque > 0) {
            duracionBonoAtaque--;
            if (duracionBonoAtaque == 0) {
                bonoAtaque = 1.0;
                System.out.println("El bono de ataque de " + nombre + " ha expirado.");
            }
        }
        if (duracionBonoDefensa > 0) {
            duracionBonoDefensa--;
            if (duracionBonoDefensa == 0) {
                bonoDefensa = 0.0;
                System.out.println("El bono de defensa de " + nombre + " ha expirado.");
            }
        }
    }

    public void decrementarVida(int cantidad) {
        int danoReducido = (int) (cantidad * (1 - getDefensas()));
        vida -= danoReducido;
        if (vida < 0) vida = 0;
        System.out.println(nombre + " pierde " + danoReducido + " vidas (después de defensas). Vidas actuales: " + vida);
    }

    public HabilidadEspecial getHabilidadEspecial() {
        return habilidadEspecial;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public int getMisilesAtaque() {
        return misilesAtaque;
    }

    public void setMisilesAtaque(int misilesAtaque) {
        this.misilesAtaque = misilesAtaque;
    }

    public int getMisilesDefensa() {
        return misilesDefensa;
    }

    public void setMisilesDefensa(int misilesDefensa) {
        this.misilesDefensa = misilesDefensa;
    }

    public void reiniciarMisiles() {
        this.misilesAtaque = 0;
        this.misilesDefensa = 0;
    }

    public void incrementarTurnos() {
        this.turnosDesdeInicio++;
    }

    public int getTurnosDesdeInicio() {
        return turnosDesdeInicio;
    }

    public abstract void aplicarHabilidadPasiva();
}