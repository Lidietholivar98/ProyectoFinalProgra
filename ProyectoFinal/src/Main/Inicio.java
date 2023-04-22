package Main;

import Controladores.ChoferesController;
import Controladores.MenuController;
import Controladores.UtilsController;
import Controladores.VehiculosController;
import Controladores.ViajesController;
import javax.swing.JOptionPane;

public class Inicio {

    private static UtilsController metodos = new UtilsController();
    private static MenuController menu = new MenuController();
    private static ViajesController viajes = new ViajesController();
    private static VehiculosController vehiculos = new VehiculosController();
    private static ChoferesController choferes = new ChoferesController();

    public static void main(String[] args) {
        choferes.CargarDatos();
        vehiculos.CargarDatos();
        viajes.CargarDatos();
        menuPrincipal();
    }

    public static void menuPrincipal() {
        String[] opciones = {"Administracion", "Ventas", "Salir"};
        int opcion = -1;
        while (opcion != opciones.length - 1) {
            opcion = metodos.menuBotones("Seleccione una opción", "Reservaciones de Viaje", opciones, "Salir");
            switch (opcion) {
                case 0:
                    menu.menuAdministracion();
                    break;
                case 1:
                    menu.menuVentas();
                    break;
                case 2:
                    int salir = metodos.mensajeConfirmacionSIoNo("¿Realmente desea salir?", "Atención");
                    if (salir == JOptionPane.YES_NO_OPTION) {
                        metodos.mensajeInformacion("Gracias por utilizar nuestro sistema");
                        System.exit(0);
                    } else {
                        opcion = -1;
                    }
            }
        }
    }
}
