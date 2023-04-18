/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import static Main.Inicio.menuPrincipal;

public class MenuController {

    public static UtilsController metodos = new UtilsController();
    public static VehiculosController vehiculos = new VehiculosController();
    public static TiquetesController tiquete = new TiquetesController();
    public static ChoferesController chofer = new ChoferesController();
    public static ViajesController viaje = new ViajesController();
    public static UsuariosController usuario = new UsuariosController();
    public static PersonasController personas = new PersonasController();

    public static void menuAdministracion() {
        String[] opciones = {"Personas", "Usuarios", "Choferes", "Vehículos", "Volver"};
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
                    vehiculos.menuVehiculos();
                    break;
                case 4:
                    menuPrincipal();
                    break;
            }
        }
    }

    public static void menuVentas() {
        String[] opciones = {"Viajes", "Tiquetes", "Volver"};
        int opcion = -1;
        while (opcion != opciones.length - 1) {
            opcion = metodos.menuBotones("Seleccione una opción", "Ventas", opciones, "Volver");
            switch (opcion) {
                case 0:
                    viaje.menuViajes();
                    break;
                case 1:
                    tiquete.menuTiquetes();
                    break;
                case 2:
                    menuPrincipal();
                    break;
            }
        }
    }
}
