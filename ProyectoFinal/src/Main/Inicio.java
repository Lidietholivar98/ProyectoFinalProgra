package Main;

import Controladores.UtilsController;
import Controladores.PersonasController;
import Controladores.VehiculoController;
import javax.swing.JOptionPane;

public class Inicio {
    public static UtilsController metodos = new UtilsController();

    
    public static void main(String[] args) {
        menuPrincipal();
    }
    
    public static void menuPrincipal(){        
        String[] opciones = {"Administracion","Ventas","Salir"};
        int opcion= -1;
        while (opcion!=opciones.length-1) {
            opcion = metodos.menuBotones("Seleccione una opción", "Reservaciones de Viaje", opciones, "Salir");
            switch (opcion) {
                case 0:   
                    UtilsController.menuAdministracion();
                    break;
                case 1: 
                    UtilsController.menuVentas();
                    break;
                case 2:
                    int salir = metodos.mensajeConfirmacionSIoNo("¿Realmente desea salir?", "Atención");
                    if (salir == JOptionPane.YES_NO_OPTION){
                        metodos.mensajeInformacion("Gracias por utilizar nuestro sistema");
                        System.exit(0);
                    }
                    else{
                        opcion= -1;
                    }
            }
        }
    }
}
