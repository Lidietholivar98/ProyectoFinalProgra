package Modelo;

public class Chofer {

    private String numeroIdentificacion;
    private String vencimientoLicencia;

    public Chofer() {
    }

    public Chofer(String identificacion, String licencia) {
        this.numeroIdentificacion = identificacion;
        this.vencimientoLicencia = licencia;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getVencimientoLicencia() {
        return vencimientoLicencia;
    }

    public void setVencimientoLicencia(String vencimientoLicencia) {
        this.vencimientoLicencia = vencimientoLicencia;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("**** Información del chofer****");
        sb.append("\nIdentificación: ").append(numeroIdentificacion);
        sb.append("\nLicencia: ").append(vencimientoLicencia);
        sb.append("\n--------\n");
        return sb.toString();
    }

}
