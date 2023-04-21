package Modelo;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class Tiquete {

    private int idVenta;
    private static final double IVA = 0.13; // Porcentaje del IVA
    private int idViaje;
    private int cantidad;
    private double precioVenta;//TODO se valida contra el viaje
    private LocalDate fechaVenta;
    private double totalVentaBruta;
    private double totalIVA;
    private double totalVenta;
    private static int nuevoNumeroVenta;

    public Tiquete() {
    }

    public Tiquete(int idViaje, int cantidad, double precioVenta, LocalDate fechaVenta) {
        setNuevoNumeroVenta();
        this.idVenta = nuevoNumeroVenta;
        this.idViaje = idViaje;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.fechaVenta = fechaVenta;
        this.totalVentaBruta = cantidad * precioVenta;
        this.totalIVA = totalVentaBruta * IVA;
        this.totalVenta = totalVentaBruta + totalIVA;
    }
    
    public static void setNuevoNumeroVenta() {
        nuevoNumeroVenta++;
    }
    
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
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

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CR"));
        sb.append("**** Información de la venta # ").append(idVenta).append("****");
        sb.append("\nNúmero de viaje: ").append(idViaje);
        sb.append("\nCantidad de tiquetes vendidos: ").append(cantidad);
        sb.append("\nFecha de venta: ")
                .append(fechaVenta.getDayOfMonth())
                .append('/')
                .append(fechaVenta.getMonth())
                .append('/')
                .append(fechaVenta.getYear());
        sb.append("\nPrecio de venta: ").append(formatoMoneda.format(precioVenta));
        sb.append("\nTotal de IVA: ").append(formatoMoneda.format(totalIVA));
        sb.append("\nTotal de venta con IVA: ").append(formatoMoneda.format(totalVenta));
        sb.append("\n\n");

        return sb.toString();
    }
}
