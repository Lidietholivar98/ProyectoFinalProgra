package Controladores;

public class LoginController {

    private UtilsController metodos = new UtilsController();

    public boolean loginLocal() {
        String usuarioCorrecto = "admin";
        String contrasenaCorrecta = "admin123";

        String usuario = metodos.entradaTexto("Ingrese su usuario:", "Inicio de sesión");
        String contrasena = metodos.entradaTexto("Ingrese su contraseña:", "Inicio de sesión");

        if (usuario == null || contrasena == null) {
            metodos.mensajeError("Usuario o contraseña incorrectos");
            System.exit(0); // Finaliza el programa si el usuario presiona "Cancelar" o no ingresa nada
        }

        if (usuario.equals(usuarioCorrecto) && contrasena.equals(contrasenaCorrecta)) {
            return true;
        } else {
            metodos.mensajeError("Usuario o contraseña incorrectos");
            System.exit(0); // Finaliza el programa si las credenciales ingresadas son incorrectas
        }

        return false;
    }
}
