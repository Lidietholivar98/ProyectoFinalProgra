package Modelo;

public class Viajes {

    private String idViaje;
    private String numeroPlaca;
    private String idChofer;
    private String fechaViaje;
    private String destino;
    private String capacidadPasajeros;
    private String precioTiquete;

    public Viajes() {
    }

    public Viajes(String idViaje, String numeroPlaca, String idChofer, String fechaViaje, String destino, String capacidadPasajeros, String precioTiquete) {
        this.idViaje = idViaje;
        this.numeroPlaca = numeroPlaca;
        this.idChofer = idChofer;
        this.fechaViaje = fechaViaje;
        this.destino = destino;
        this.capacidadPasajeros = capacidadPasajeros;
        this.precioTiquete = precioTiquete;
    }

    public String getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(String idViaje) {
        this.idViaje = idViaje;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public String getIdChofer() {
        return idChofer;
    }

    public void setIdChofer(String idChofer) {
        this.idChofer = idChofer;
    }

    public String getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(String fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(String capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public String getPrecioTiquete() {
        return precioTiquete;
    }

    public void setPrecioTiquete(String precioTiquete) {
        this.precioTiquete = precioTiquete;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("**** Información del viaje ****");
        sb.append("\nID del Viaje: ").append(idViaje);
        sb.append("\nNúmero de Placa: ").append(numeroPlaca);
        sb.append("\nID del Chofer: ").append(idChofer);
        sb.append("\nFecha del Viaje: ").append(fechaViaje);
        sb.append("\nDestino: ").append(destino);
        sb.append("\nCapacidad de pasajeros: ").append(capacidadPasajeros);
        sb.append("\nPrecio del tiquete: ").append(precioTiquete);
        sb.append("\n--------\n");
        return sb.toString();
    }
}
