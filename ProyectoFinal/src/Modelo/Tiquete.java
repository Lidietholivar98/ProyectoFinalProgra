package Modelo;

import java.util.Date;

public class Tiquete {

    private static final double IVA = 0.13; // Porcentaje del IVA
    private String idViaje;
    private int cantidad;
    private double precioVenta;//TODO se valida contra el viaje
    private Date fechaVenta;
    private double totalVentaBruta;
    private double totalIVA;
    private double totalVenta;

    public Tiquete() {
    }

    public Tiquete(String idViaje, int cantidad, double precioVenta, Date fechaVenta) {
        this.idViaje = idViaje;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.fechaVenta = fechaVenta;
        this.totalVentaBruta = cantidad * precioVenta;
        this.totalIVA = totalVentaBruta * IVA;
        this.totalVenta = totalVentaBruta + totalIVA;
    }

    public String getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(String idViaje) {
        this.idViaje = idViaje;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
}
