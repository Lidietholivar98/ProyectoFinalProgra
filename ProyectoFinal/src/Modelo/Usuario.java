package Modelo;

public class Usuario {

    private String numeroIdentificacion;
    private String codigoUsuario;
    private String claveAcceso;

    public Usuario() {
    }

    public Usuario(String identificacion, String codigo, String clave) {
        this.numeroIdentificacion = identificacion;
        this.codigoUsuario = codigo;
        this.claveAcceso = clave;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("**** Información del usuario ****");
        sb.append("\nID de persona: ").append(numeroIdentificacion);
        sb.append("\nCódigo de usuario: ").append(codigoUsuario);
        sb.append("\nClave de acceso: ").append(claveAcceso);
        sb.append("\n--------\n");
        return sb.toString();
    }
}
