package Controladores;

import static Controladores.MenuController.menuAdministracion;
import Interfaces.CrudInterfaces;
import Modelo.Viaje;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ViajesController implements CrudInterfaces {

    private UtilsController metodos = new UtilsController();
    private static List<Viaje> viajes = new ArrayList();
    private VehiculosController controladorVehiculos = new VehiculosController();

    public void CargarDatos() {
        //Viaje(String idViaje, String numeroPlaca, String idChofer, String fechaViaje, String destino, int capacidadPasajeros, String precioTiquete)
//        Viajes v1 = new Viajes("123", "Bogotá", "Medellín", "Avión");
//        Viajes v2 = new viajes("456", "Cali", "Barranquilla", "Autobús");
//        Viajes v3 = new viajes("789", "Medellín", "Bogotá", "Tren");
//        viajes.add(v1);
//        viajes.add(v2);
//        viajes.add(v3);
    }

    @Override
    public void Registrar() {
        String numeroPlaca = "";
        String idChofer = "";
        LocalDate fechaViaje = LocalDate.now();
        String destino = "";
        String precioTiqueteStr = "";
        double precioTiquete = 0.0;

        Boolean placaValida = false;
        while (!placaValida) {
            numeroPlaca = JOptionPane.showInputDialog("Ingrese el número de placa: ");
            if (numeroPlaca.isEmpty()) {
                metodos.mensajeAlerta("Debe ingresar un número de placa válido");
            } else if (existePlaca(numeroPlaca)) {
                metodos.mensajeAlerta("Esta placa ya posee un viaje programado");
            } else {
                placaValida = true;
            }
        }

        Boolean choferValido = false;
        while (!choferValido) {
            idChofer = JOptionPane.showInputDialog("Ingrese el identificador de chofer: ");
            if (idChofer.isEmpty()) {
                metodos.mensajeAlerta("Debe ingresar un identificador de chofer válido");
            } else {
                choferValido = true;
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
        
        int capacidadPasajeros = controladorVehiculos.obtenerCapacidadPasajerosPorPlaca(numeroPlaca);

        Boolean precioTiqueteValido = false;
        while (!precioTiqueteValido) {
            precioTiqueteStr = JOptionPane.showInputDialog("Ingrese el precio del tiquete: ");
            if(!esDouble(precioTiqueteStr)){
                metodos.mensajeAlerta("Debe ingresar un precio válido");
            }
            else{
                precioTiquete = Double.parseDouble(precioTiqueteStr);
                if(precioTiquete <= 0){
                    metodos.mensajeAlerta("Debe ingresar un precio superior a cero");
                }
                else{
                    precioTiqueteValido = true;
                }
            }
        }
        
        Viaje nuevoViaje = new Viaje(numeroPlaca, idChofer, fechaViaje, destino, capacidadPasajeros, precioTiquete);
        viajes.add(nuevoViaje);

        metodos.mensajeInformacion("Viaje creado exitosamente", "Creación de viaje");
    }

    public void Ver() {//TODO: Trabajar este metodo, ya que no se el proposito del mismo
//        String mensaje = "Lista de Viajes: \n\n";
//        for (Viaje v : viajes) {
//            mensaje += "ID: " + v.getId() + "\nOrigen: " + v.getOrigen() + "\nDestino: " + v.getDestino() + "\nMedio de transporte: " + v.getMedioTransporte() + "\n\n";
//        }
//        metodos.mensajeInformacion(mensaje, "Lista de viajes");
    }

    @Override
    public void Editar() {//TODO: arreglar este metodo para que se hagan los respectivos SET
        Boolean idViajeValido = false;
        String idViajeStr = "";
        int idViaje = -1;
        while(!idViajeValido){
            idViajeStr = JOptionPane.showInputDialog("Ingrese el identificador del viaje: ");
            if(!esEntero(idViajeStr)){
                metodos.mensajeAlerta("Debe ingresar un número entero");
            }
            else{
                idViaje = Integer.parseInt(idViajeStr);
                if (idViaje <= 0) {
                    metodos.mensajeAlerta("Debe ingresar un identificador de viaje válido");
                } else {
                    idViajeValido = true;
                }                
            }
        }
        
        int index = buscarIndicePorId(idViaje);
        if (index != -1) {
            Viaje viaje = viajes.get(index);
            String nuevoOrigen = JOptionPane.showInputDialog("Ingrese el nuevo lugar de origen: ");
            String nuevoDestino = JOptionPane.showInputDialog("Ingrese el nuevo lugar de destino: ");
            String nuevoMedioTransporte = JOptionPane.showInputDialog("Ingrese el nuevo medio de transporte: ");

            viaje.setDestino(nuevoDestino);

            metodos.mensajeInformacion("Viaje modificado exitosamente", "Modificación de viaje");
        }
        else{
            metodos.mensajeAlerta("El identificador ingresado no se encuentra registrado");
        }
    }

    @Override
    public void Eliminar() {
        Boolean idViajeValido = false;
        String idViajeStr = "";
        int idViaje = -1;
        while(!idViajeValido){
            idViajeStr = JOptionPane.showInputDialog("Ingrese el identificador del viaje: ");
            if(!esEntero(idViajeStr)){
                metodos.mensajeAlerta("Debe ingresar un número entero");
            }
            else{
                idViaje = Integer.parseInt(idViajeStr);
                if (idViaje <= 0) {
                    metodos.mensajeAlerta("Debe ingresar un identificador de viaje válido");
                } else {
                    idViajeValido = true;
                }                
            }
        }
        
        int index = buscarIndicePorId(idViaje);
        if (index != -1) {
            int respuesta = metodos.mensajeConfirmacionSIoNo("¿Está seguro que desea eliminar el viaje?", "Eliminar viaje");
            if (respuesta == JOptionPane.YES_OPTION) {
                viajes.remove(index);
                metodos.mensajeInformacion("Viaje eliminado exitosamente", "Eliminación de viaje");
            }
        }
        else{
            metodos.mensajeAlerta("El identificador ingresado no se encuentra registrado");
        }
    }

    public void menuViajes() {
        String[] opciones = {"Crear", "Ver", "Modificar", "Eliminar", "Volver"};
        int opcion = -1;
        while (opcion != opciones.length - 1) {
            opcion = metodos.menuBotones("Seleccione una opción", "Viajes", opciones, "Volver");
            switch (opcion) {
                case 0:
                    Registrar();
                    break;
                case 1:
                    Ver();
                    break;
                case 2:
                    Editar();
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
    public int buscarIndicePorId(int id) {
        int indice = -1;
        for (int i = 0; i < viajes.size(); i++) {
            if (viajes.get(i).getIdViaje() == id) {
                indice = i;
                break;
            }
        }
        return indice;
    }

    public boolean existePlaca(String numeroPlaca) {
        for (Viaje v : viajes) {
            if (v.getNumeroPlaca().equals(numeroPlaca)) {
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
    
    public boolean esDouble(String texto) {
        double valor;
        try {
            valor = Double.parseDouble(texto);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    
    public boolean esEntero(String texto) {
        int valor;
        try {
            valor = Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
