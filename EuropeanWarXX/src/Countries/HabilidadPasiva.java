package Countries;

class HabilidadPasiva {
    private String nombre;
    private String descripcion;
    private String efecto;
    
    public HabilidadPasiva(String nombre, String descripcion, String efecto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.efecto = efecto;
    }
    
    public void aplicarEfecto(Pais pais) {
        System.out.println("Aplicando habilidad pasiva: " + nombre + " a " + pais.getNombre());
    }
}