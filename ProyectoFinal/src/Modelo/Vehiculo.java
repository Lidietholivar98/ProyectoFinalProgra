package Modelo;

public class Vehiculo {

    private String numeroPlaca;
    private String marca;
    private String estilo;
    private int modelo;
    private int capacidadPasajeros;

    public Vehiculo() {
    }

    public Vehiculo(String numeroPlaca, String marca, String estilo, int modelo, int capacidadPasajeros) {
        this.numeroPlaca = numeroPlaca;
        this.marca = marca;
        this.estilo = estilo;
        this.modelo = modelo;
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("**** Información del vehículo ****");
        sb.append("\nNúmero de Placa: ").append(numeroPlaca);
        sb.append("\nMarca: ").append(marca);
        sb.append("\nEstilo: ").append(estilo);
        sb.append("\nModelo: ").append(modelo);
        sb.append("\nCapacidad de pasajeros: ").append(capacidadPasajeros);
        sb.append("\n--------\n");
        return sb.toString();
    }
}
