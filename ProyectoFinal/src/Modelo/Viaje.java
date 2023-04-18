package Modelo;

import java.time.LocalDate;

public class Viaje {

    private int idViaje;
    private String numeroPlaca;
    private String idChofer;
    private LocalDate fechaViaje;
    private String destino;
    private int capacidadPasajeros;//TODO validar contra capacidad del bus
    private double precioTiquete;
    private static int nuevoNumeroViaje;

    public Viaje() {
    }

    public Viaje(String numeroPlaca, String idChofer, LocalDate fechaViaje, String destino, int capacidadPasajeros, double precioTiquete) {
        setNuevoNumeroViaje();
        this.idViaje = nuevoNumeroViaje;
        this.numeroPlaca = numeroPlaca;
        this.idChofer = idChofer;
        this.fechaViaje = fechaViaje;
        this.destino = destino;
        this.capacidadPasajeros = capacidadPasajeros;
        this.precioTiquete = precioTiquete;
    }
    
    public static void setNuevoNumeroViaje() {
        nuevoNumeroViaje++;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
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

    public LocalDate getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(LocalDate fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public double getPrecioTiquete() {
        return precioTiquete;
    }

    public void setPrecioTiquete(double precioTiquete) {
        this.precioTiquete = precioTiquete;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("**** Información del viaje ****");
        sb.append("\nID del Viaje: ").append(idViaje);
        sb.append("\nNúmero de Placa: ").append(numeroPlaca);
        sb.append("\nID del Chofer: ").append(idChofer);
        sb.append("\nFecha del Viaje: ")
                .append(fechaViaje.getDayOfMonth())
                .append('/')
                .append(fechaViaje.getMonth())
                .append('/')
                .append(fechaViaje.getYear());
        sb.append("\nDestino: ").append(destino);
        sb.append("\nCapacidad de pasajeros: ").append(capacidadPasajeros);
        sb.append("\nPrecio del tiquete: ").append(precioTiquete);
        sb.append("\n--------\n");
        return sb.toString();
    }
}
