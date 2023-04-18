package Modelo;

import java.util.ArrayList;
import java.util.Date;

public class Tickets {

    private static final double IVA = 0.13; // Porcentaje del IVA
    
    private String idViaje;
    private int cantidad;
    private double precioVenta;
    private Date fechaVenta;
    private double ventaBruta;
    private double totalIVA;
    private double totalVenta;

    public Tickets() {
    }

    public Tickets(String idViaje, int cantidad, double precioVenta, Date fechaVenta) {
        this.idViaje = idViaje;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.fechaVenta = fechaVenta;
        this.ventaBruta = cantidad * precioVenta;
        this.totalIVA = ventaBruta * IVA;
        this.totalVenta = ventaBruta + totalIVA;
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

