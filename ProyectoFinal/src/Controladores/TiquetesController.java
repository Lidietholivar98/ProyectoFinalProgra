package Controladores;

import static Controladores.MenuController.menuVentas;
import Interfaces.CrudInterfaces;
import Modelo.Tiquete;
import Modelo.Vehiculo;
import Modelo.Viaje;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TiquetesController implements CrudInterfaces {

    private UtilsController metodos = new UtilsController();
    private ViajesController viajes = new ViajesController();
    private VehiculosController vehiculos = new VehiculosController();
    private static List<Tiquete> ventas = new ArrayList();

    public void menuTiquetes() {
        String[] opciones = {"Registrar", "Anular", "Consultar", "Informe", "Volver"};
        int opcion = -1;
        while (opcion != opciones.length - 1) {
            opcion = metodos.menuBotones("Seleccione una opción", "Tiquetes", opciones, "Volver");
            switch (opcion) {
                case 0:
                    Registrar();
                    break;
                case 1:
                    Anular();
                    break;
                case 2:
                    Consultar();
                    break;
                case 3:
                    Informe();
                    break;
                case 4:
                    menuVentas();
                    break;
            }
        }
    }

    @Override
    public void Registrar() {
        String idViajeStr = "";
        int idViaje = -1;
        String cantidadStr = "";
        int cantidad = -1;

        Boolean idViajeValido = false;
        while (!idViajeValido) {
            idViajeStr = JOptionPane.showInputDialog("Ingrese el número de viaje: ");
            if (!metodos.esEntero(idViajeStr)) {
                metodos.mensajeAlerta("Debe ingresar un número entero");
            } else {
                idViaje = Integer.parseInt(idViajeStr);
                if (idViaje <= 0) {
                    metodos.mensajeAlerta("Debe ingresar un número de viaje válido");
                } else {
                    idViajeValido = true;
                }
            }
        }

        int espaciosDisponibles = viajes.obtenerEspaciosDisponibles(idViaje);
        Boolean cantidadValida = false;
        while (!cantidadValida) {
            cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad de tiquetes a vender: ");
            if (!metodos.esEntero(cantidadStr)) {
                metodos.mensajeAlerta("Debe ingresar un número entero");
            } else {
                cantidad = Integer.parseInt(cantidadStr);
                Boolean hayDisponibilidad = cantidad <= espaciosDisponibles;
                if (cantidad <= 0) {
                    metodos.mensajeAlerta("Debe ingresar una cantidad de tiquetes válida");
                } else if (!hayDisponibilidad) {
                    metodos.mensajeAlerta("No hay disponibilidad de espacios para esa cantidad de tiquetes");
                } else {
                    cantidadValida = true;
                }
            }
        }

        double precioVenta = viajes.obtenerPrecioVentaDelTiquete(idViaje);
        LocalDate fechaVenta = LocalDate.now();
        Tiquete tiquete = new Tiquete(idViaje, cantidad, precioVenta, fechaVenta);

        String mensaje = "Número de viaje: " + tiquete.getIdViaje()
                + "\nCantidad de tiquetes: " + tiquete.getCantidad()
                + "\nPrecio de venta: " + tiquete.getPrecioVenta()
                + "\nFecha de venta: " + tiquete.getFechaVenta().getDayOfMonth()
                + '/'
                + tiquete.getFechaVenta().getMonth()
                + '/'
                + tiquete.getFechaVenta().getYear();
        String titulo = "Validación de datos";
        int resp = metodos.mensajeConfirmacionSIoNo(mensaje, titulo);

        if (resp == JOptionPane.YES_NO_OPTION) {
            ventas.add(tiquete);
            viajes.venderEspacioDisponible(idViaje, cantidad);
        }
    }

    @Override
    public void Informe() {
        String informe = "";
        Viaje viaje = new Viaje();
        Vehiculo vehiculo = new Vehiculo();

        for (Tiquete tiquete : ventas) {
            if(!tiquete.estaAnulado()){
                viaje = viajes.buscarPorNumeroDeViaje(tiquete.getIdViaje());
                vehiculo = vehiculos.buscarPorPlaca(viaje.getNumeroPlaca());
                informe = informe 
                    + "Venta #: " + tiquete.getIdVenta()
                    + "\nViaje #: " + tiquete.getIdViaje()
                    + "\nCapacidad del autobus: " + vehiculo.getCapacidadPasajeros()
                    + "\nTiquetes vendidos: " + tiquete.getCantidad()
                    + "\nDisponibilidad de espacios: " + viajes.obtenerEspaciosDisponibles(viaje.getIdViaje())
                    + "\n**********\n";
            }
        }
        metodos.mensajeInformacion(informe, "Informe de ventas");
    }

    @Override
    public void Consultar() {
        String idVentaStr = "";
        int idVenta = -1;
        Tiquete tiquete = null;

        Boolean idVentaValido = false;
        while (!idVentaValido) {
            idVentaStr = JOptionPane.showInputDialog("Ingrese el número de venta:");
            if (!metodos.esEntero(idVentaStr)) {
                metodos.mensajeAlerta("Debe ingresar un número entero");
            } else {
                idVenta = Integer.parseInt(idVentaStr);
                if (idVenta <= 0) {
                    metodos.mensajeAlerta("Debe ingresar una venta válida");
                } else {
                    idVentaValido = true;
                }
            }
        }
        tiquete = buscarPorIdVenta(idVenta);
        if (tiquete != null) {
            if(!tiquete.estaAnulado())
                metodos.mensajeInformacion(tiquete.toString(), "Información de la venta");
            else
                metodos.mensajeInformacion("** Venta anulada\n" + tiquete.toString(), "Información de la venta");
        } else {
            metodos.mensajeAlerta(String.format("El número de venta %s no se encuentra registrado", idVenta));
        }
    }

    public void Anular() {
        String idVentaStr = "";
        int idVenta = -1;
        Tiquete tiquete = null;

        Boolean idVentaValido = false;
        while (!idVentaValido) {
            idVentaStr = JOptionPane.showInputDialog("Ingrese el número de venta que quiere anular:");
            if (!metodos.esEntero(idVentaStr)) {
                metodos.mensajeAlerta("Debe ingresar un número entero");
            } else {
                idVenta = Integer.parseInt(idVentaStr);
                if (idVenta <= 0) {
                    metodos.mensajeAlerta("Debe ingresar una venta válida");
                } else {
                    idVentaValido = true;
                }
            }
        }

        tiquete = buscarPorIdVenta(idVenta);
        if (tiquete != null) {
            int opcion = metodos.mensajeConfirmacionSIoNo(tiquete.toString(), "¿Desea anular la venta?");
            if (opcion == JOptionPane.YES_NO_OPTION) {
                viajes.anularEspacioVendido(idVenta, tiquete.getCantidad());
                tiquete.anular();
                metodos.mensajeInformacion("Venta anulada correctamente");
            }
        } else {
            metodos.mensajeAlerta(String.format("El número de venta %s no se encuentra registrado", idVenta));
        }
    }

    public Tiquete buscarPorIdVenta(int idVenta) {
        Tiquete tiquete = null;
        for (Tiquete v : ventas) {
            if (v.getIdVenta() == idVenta) {
                tiquete = v;
                break;
            }
        }
        return tiquete;
    }

    @Override
    public void Editar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
