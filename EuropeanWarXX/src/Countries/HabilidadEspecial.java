package Countries;

class HabilidadEspecial {
    private String nombre;
    private String descripcion;
    private String efecto;
    
    public HabilidadEspecial(String nombre, String descripcion, String efecto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.efecto = efecto;
    }
    
    public void activarHabilidad(Pais objetivo) {
        System.out.println("Activando habilidad especial: " + nombre + " contra " + objetivo.getNombre());
    }
}