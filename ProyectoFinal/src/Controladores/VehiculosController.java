package Controladores;

import static Controladores.MenuController.menuAdministracion;
import Interfaces.CrudInterfaces;
import Modelo.Vehiculo;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class VehiculosController implements CrudInterfaces {

    private UtilsController metodos = new UtilsController();
    private static ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    
    public void CargarDatos() {//(String numeroPlaca, String marca, String estilo, int modelo, int capacidadPasajeros)
        Vehiculo v1 = new Vehiculo("ABC123", "Suzuki", "Vitara", 2020, 4);
        Vehiculo v2 = new Vehiculo("ABC456", "Suzuki", "Grand Vitara", 2020, 20);
        Vehiculo v3 = new Vehiculo("ABC789", "Suzuki", "Ciaz", 2020, 65);
        vehiculos.add(v1);
        vehiculos.add(v2);
        vehiculos.add(v3);
    }

    @Override
    public void Registrar() {
        String numeroPlaca = "";
        String marca = "";
        String estilo = "";
        int modelo = 0;
        int capacidadPasajeros = 0;

        numeroPlaca = JOptionPane.showInputDialog("Ingrese el número de placa: ");
        marca = JOptionPane.showInputDialog("Ingrese la marca: ");
        estilo = JOptionPane.showInputDialog("Ingrese el estilo: ");
        modelo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el modelo (año): "));
        capacidadPasajeros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad de pasajeros: "));

        Vehiculo vehiculo = new Vehiculo(numeroPlaca, marca, estilo, modelo, capacidadPasajeros);
        vehiculos.add(vehiculo);
    }

    public void Ver() {
        String placa = JOptionPane.showInputDialog("Ingrese el número de placa: ");
        Vehiculo vehiculo = buscarPorPlaca(placa);

        if (vehiculo != null) {
            JOptionPane.showMessageDialog(null, vehiculo.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Vehículo no encontrado");
        }
    }

    @Override
    public void Editar() {
        String placa = JOptionPane.showInputDialog("Ingrese el número de placa: ");
        Vehiculo vehiculo = buscarPorPlaca(placa);

        if (vehiculo != null) {
            vehiculo.setMarca(JOptionPane.showInputDialog("Ingrese la nueva marca: ", vehiculo.getMarca()));
            vehiculo.setEstilo(JOptionPane.showInputDialog("Ingrese el nuevo estilo: ", vehiculo.getEstilo()));
            vehiculo.setModelo(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo modelo (año): ", vehiculo.getModelo())));
            vehiculo.setCapacidadPasajeros(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva capacidad de pasajeros: ", vehiculo.getCapacidadPasajeros())));
        } else {
            JOptionPane.showMessageDialog(null, "Vehículo no encontrado");
        }
    }

    @Override
    public void Eliminar() {
        String placa = JOptionPane.showInputDialog("Ingrese el número de placa: ");
        Vehiculo vehiculo = buscarPorPlaca(placa);

        if (vehiculo != null) {
            vehiculos.remove(vehiculo);
            JOptionPane.showMessageDialog(null, "Vehículo eliminado");
        } else {
            JOptionPane.showMessageDialog(null, "Vehículo no encontrado");
        }
    }

    public void menuVehiculos() {
        String[] opciones = {"Registrar", "Consultar", "Modificar", "Eliminar", "Volver"};
        int opcion = -1;
        while (opcion != opciones.length - 1) {
            opcion = metodos.menuBotones("Seleccione una opción", "Vehiculo", opciones, "Volver");
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void Informe() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Vehiculo buscarPorPlaca(String placa) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getNumeroPlaca().equalsIgnoreCase(placa)) {
                return vehiculo;
            }
        }
        return null;
    }
    
    public int obtenerCapacidadPasajerosPorPlaca(String placa){
        int capacidad = -1;
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getNumeroPlaca().equalsIgnoreCase(placa)) {
                capacidad = vehiculo.getCapacidadPasajeros();
                break;
            }
        }
        return capacidad;
    }

    @Override
    public void Consultar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void Buscar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
