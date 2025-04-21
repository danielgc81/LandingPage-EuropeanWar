package Countries;

import java.util.List;
import java.util.ArrayList;

abstract class Pais {
    private String nombre;
    private int vidas;
    private int misiles;
    private int tecnologia;
    private List<String> habilidadesTecnologicas;
    private boolean enDesarrollo;
    private int turnosDesarrollo;
    
    public Pais(String nombre, int vidas, int misiles, int tecnologia) {
        this.nombre = nombre;
        this.vidas = vidas;
        this.misiles = misiles;
        this.tecnologia = tecnologia;
        this.habilidadesTecnologicas = new ArrayList<>();
        this.enDesarrollo = false;
        this.turnosDesarrollo = 0;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public int getVidas() {
        return vidas;
    }
    
    public int getMisiles() {
        return misiles;
    }
    
    public int getTecnologia() {
        return tecnologia;
    }
    
    public void incrementarVidas(int cantidad) {
        this.vidas += cantidad;
    }
    
    public void decrementarVidas(int cantidad) {
        this.vidas -= cantidad;
        if (this.vidas < 0) {
            this.vidas = 0;
        }
    }
    
    public void iniciarDesarrolloTecnologico(int turnos) {
        this.enDesarrollo = true;
        this.turnosDesarrollo = turnos;
        System.out.println(nombre + " ha iniciado desarrollo tecnológico por " + turnos + " turnos.");
    }
    
    public void avanzarDesarrollo() {
        if (enDesarrollo) {
            turnosDesarrollo--;
            System.out.println(nombre + " avanza en su desarrollo tecnológico. Turnos restantes: " + turnosDesarrollo);
            
            if (turnosDesarrollo <= 0) {
                completarDesarrollo();
            }
        }
    }
    
    public void completarDesarrollo() {
        tecnologia++;
        enDesarrollo = false;
        System.out.println(nombre + " ha completado su desarrollo tecnológico. Nuevo nivel: " + tecnologia);
        adquirirHabilidadTecnologica();
    }
    
    public void adquirirHabilidadTecnologica() {
        String nuevaHabilidad = generarHabilidadTecnologica();
        if (nuevaHabilidad != null) {
            habilidadesTecnologicas.add(nuevaHabilidad);
            System.out.println(nombre + " ha adquirido una nueva habilidad tecnológica: " + nuevaHabilidad);
        }
    }
    
    public abstract String generarHabilidadTecnologica();
    
    public abstract void habilidadNacional();
    
    public void usarHabilidadTecnologica(int indice, Pais objetivo) {
        if (indice >= 0 && indice < habilidadesTecnologicas.size()) {
            String habilidad = habilidadesTecnologicas.get(indice);
            System.out.println(nombre + " utiliza habilidad tecnológica " + habilidad + " contra " + objetivo.getNombre());
            aplicarEfectoTecnologico(habilidad, objetivo);
        } else {
            System.out.println("Habilidad tecnológica no disponible");
        }
    }
    
    protected void aplicarEfectoTecnologico(String habilidad, Pais objetivo) {
        if (habilidad.contains("Misiles")) {
            objetivo.decrementarVidas(10 * tecnologia);
            System.out.println("Ataque tecnológico causa " + (10 * tecnologia) + " puntos de daño");
        } else if (habilidad.contains("Defensa")) {
            this.incrementarVidas(5 * tecnologia);
            System.out.println("Mejora defensiva añade " + (5 * tecnologia) + " puntos de vida");
        } else if (habilidad.contains("Espionaje")) {
            System.out.println("Se revela información del enemigo: " + 
                "Vidas: " + objetivo.getVidas() + 
                ", Misiles: " + objetivo.getMisiles() + 
                ", Tecnología: " + objetivo.getTecnologia());
        }
    }
    
    public List<String> getHabilidadesTecnologicas() {
        return habilidadesTecnologicas;
    }

}