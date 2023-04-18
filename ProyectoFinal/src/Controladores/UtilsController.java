package Controladores;

import static Main.Inicio.menuPrincipal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class UtilsController {

    public static UtilsController metodos = new UtilsController();
    public static VehiculoController vehiculos = new VehiculoController();
    public static TicketesController tickete = new TicketesController();
    public static ChoferesController chofer = new ChoferesController();
    public static ViajesController viaje = new ViajesController();
    public static UsuarioController usuario = new UsuarioController();
    public static PersonasController personas = new PersonasController();

    public static void main(String[] args) {
        menuAdministracion();
        menuVentas();

    }

    public void mensajeInformacion(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mensajeInformacion(String msg, String titulo) {
        JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public void mensajeAlerta(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Alerta!", JOptionPane.WARNING_MESSAGE);
    }

    public int mensajeConfirmacionSIoNo(String msg, String titulo) {
        int respuesta;
        return JOptionPane.showConfirmDialog(null, msg, titulo, JOptionPane.YES_NO_OPTION);
    }

    public int menuBotones(String msg, String titulo, String opciones[], String valorDefecto) {
        int opcion = JOptionPane.showOptionDialog(null, msg, titulo, 0,
                JOptionPane.QUESTION_MESSAGE, null, opciones, valorDefecto);
        return opcion;
    }

    public Boolean esCorreoValido(String correoAValidar) {
        String expresionRegular = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern patron = Pattern.compile(expresionRegular);
        Matcher coincidencia = patron.matcher(correoAValidar);
        return coincidencia.matches();
    }

    public static void menuAdministracion() {
        String[] opciones = {"Personas", "Usuarios", "Choferes", "Vehiculos", "Volver"};
        int opcion = -1;
        while (opcion != opciones.length - 1) {
            opcion = metodos.menuBotones("Seleccione una opción", "Administrativo", opciones, "Volver");
            switch (opcion) {
                case 0:
                    personas.menuPersonas();
                    break;
                case 1:
                    usuario.menuUsuarios();
                    break;
                case 2:
                    chofer.menuChoferes();
                    break;
                case 3:
                    vehiculos.menuVehiculo();
                    break;
                case 4:
                    menuPrincipal();
                    break;
            }
        }
    }

    public static void menuVentas() {
        String[] opciones = {"Viajes", "Ticketes", "Volver"};
        int opcion = -1;
        while (opcion != opciones.length - 1) {
            opcion = metodos.menuBotones("Seleccione una opción", "Ventas", opciones, "Volver");
            switch (opcion) {
                case 0:
                    viaje.menuViaje();
                    break;
                case 1:
                    tickete.menuTicketes();
                    break;
                case 2:
                    menuPrincipal();
                    break;
            }
        }

    }

}
