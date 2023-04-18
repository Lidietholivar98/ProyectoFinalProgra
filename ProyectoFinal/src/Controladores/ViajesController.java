package Controladores;

import Interfaces.CrudInterfaces;
import static Controladores.UtilsController.menuAdministracion;
import Modelo.Viajes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ViajesController implements CrudInterfaces {

    UtilsController metodos = new UtilsController();
    private static List<Viajes> viajes = new ArrayList();

    public void CargarDatos() {
        Viajes v1 = new Viaje("123", "Bogotá", "Medellín", "Avión");
        Viajes v2 = new viajes("456", "Cali", "Barranquilla", "Autobús");
        Viajes v3 = new viajes("789", "Medellín", "Bogotá", "Tren");
        viajes.add(v1);
        viajes.add(v2);
        viajes.add(v3);
    }

    @Override
    public void Crear() {
        String id = "";
        String origen = "";
        String destino = "";
        String medioTransporte = "";

        Boolean idValido = false;
        while (!idValido) {
            id = JOptionPane.showInputDialog("Ingrese el ID del viaje: ");
            if (id.isEmpty()) {
                metodos.mensajeAlerta("Debe ingresar un ID válido");
            } else if (existeId(id)) {
                metodos.mensajeAlerta("Este ID ya se encuentra registrado");
            } else {
                idValido = true;
            }
        }

        Boolean origenValido = false;
        while (!origenValido) {
            origen = JOptionPane.showInputDialog("Ingrese el lugar de origen: ");
            if (origen.isEmpty()) {
                metodos.mensajeAlerta("Debe ingresar un lugar de origen válido");
            } else {
                origenValido = true;
            }
        }

        Boolean destinoValido = false;
        while (!destinoValido) {
            destino = JOptionPane.showInputDialog("Ingrese el lugar de destino: ");
            if (destino.isEmpty()) {
                metodos.mensajeAlerta("Debe ingresar un lugar de destino válido");
            } else {
                destinoValido = true;
            }
        }

        Boolean medioTransporteValido = false;
        while (!medioTransporteValido) {
            medioTransporte = JOptionPane.showInputDialog("Ingrese el medio de transporte: ");
            if (medioTransporte.isEmpty()) {
                metodos.mensajeAlerta("Debe ingresar un medio de transporte válido");
            } else {
                medioTransporteValido = true;
            }
        }

        Viajes nuevoViaje = new Viajes(id, origen, destino, medioTransporte);
        viajes.add(nuevoViaje);

        metodos.mensajeInformacion("Viaje creado exitosamente", "Creación de viaje");
    }

    @Override
    public void Ver() {
        String mensaje = "Lista de Viajes: \n\n";
        for (Viaje v : viajes) {
            mensaje += "ID: " + v.getId() + "\nOrigen: " + v.getOrigen() + "\nDestino: " + v.getDestino() + "\nMedio de transporte: " + v.getMedioTransporte() + "\n\n";
        }
        metodos.mensajeInformacion(mensaje, "Lista de viajes");
    }

    @Override
    public void Modificar() {
        String idViaje = JOptionPane.showInputDialog("Ingrese el ID del viaje a modificar: ");
        int index = buscarIndicePorId(idViaje);
        if (index == -1) {
            metodos.mensajeAlerta("El ID ingresado no existe");
            return;
        }

        Viaje viaje = viajes.get(index);
        String nuevoOrigen = JOptionPane.showInputDialog("Ingrese el nuevo lugar de origen: ");
        String nuevoDestino = JOptionPane.showInputDialog("Ingrese el nuevo lugar de destino: ");
        String nuevoMedioTransporte = JOptionPane.showInputDialog("Ingrese el nuevo medio de transporte: ");

        viaje.setOrigen(nuevoOrigen);
        viaje.setDestino(nuevoDestino);
        viaje.setMedioTransporte(nuevoMedioTransporte);

        metodos.mensajeInformacion("Viaje modificado exitosamente", "Modificación de viaje");
    }

    @Override
    public void Eliminar() {
        String idViaje = JOptionPane.showInputDialog("Ingrese el ID del viaje a eliminar: ");
        int index = buscarIndicePorId(idViaje);
        if (index == -1) {
            metodos.mensajeAlerta("El ID ingresado no existe");
            return;
        }

        int respuesta = metodos.mensajeConfirmacionSIoNo("¿Está seguro que desea eliminar el viaje?", "Eliminar viaje");
        if (respuesta == JOptionPane.YES_OPTION) {
            viajes.remove(index);
            metodos.mensajeInformacion("Viaje eliminado exitosamente", "Eliminación de viaje");
        }
    }

    public void menuViaje() {
        String[] opciones = {"Crear", "Ver", "Modificar", "Eliminar", "Volver"};
        int opcion = -1;
        while (opcion != opciones.length - 1) {
            opcion = metodos.menuBotones("Seleccione una opción", "Viajes", opciones, "Volver");
            switch (opcion) {
                case 0:
                    Crear();
                    break;
                case 1:
                    Ver();
                    break;
                case 2:
                    Modificar();
                    break;
                case 3:
                    Eliminar();
                    break;
                case 4:
                    menuAdministracion();
                    break;
            }
        }
    }

    @Override
    public void Anular() {

    }

    @Override
    public void Informe() {

    }

// Métodos auxiliares
    public int buscarIndicePorId(String id) {
        for (int i = 0; i < viajes.size(); i++) {
            if (viajes.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public boolean existeId(String id) {
        for (Viaje v : viajes) {
            if (v.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void Validar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Consultar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Buscar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
