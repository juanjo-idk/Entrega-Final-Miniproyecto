package modelo;

public class Autor {

    private String nombre;
    private String fechaNacimiento;
    private String paisNacimiento;
    private boolean vive;

    // Constructor completo
    public Autor(String nombre, String fechaNacimiento, String paisNacimiento, boolean vive) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.paisNacimiento = paisNacimiento;
        this.vive = vive;
    }

    // Constructor simplificado (si no se tienen todos los datos)
    public Autor(String nombre, String paisNacimiento, String colombia, String no) {
        this(nombre, "Desconocida", paisNacimiento, true); // Reutilizamos el constructor completo
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public boolean isVive() {
        return vive;
    }
}
