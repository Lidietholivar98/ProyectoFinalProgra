package Modelo;

public class Persona {

    private String numeroIdentificacion;
    private String nombre;
    private String numeroTelefono;
    private String email;

    public Persona() {
    }

    public Persona(String identificacion, String nombre, String numeroTelefono, String email) {
        this.numeroIdentificacion = identificacion;
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
        this.email = email;

    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String identificacion) {
        this.numeroIdentificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("**** Información de la persona ****");
        sb.append("\nIdentificación: ").append(numeroIdentificacion);
        sb.append("\nNombre: ").append(nombre);
        sb.append("\nTeléfono: ").append(numeroTelefono);
        sb.append("\nCorreo electrónico: ").append(email);
        sb.append("\n--------\n");
        return sb.toString();
    }
}
