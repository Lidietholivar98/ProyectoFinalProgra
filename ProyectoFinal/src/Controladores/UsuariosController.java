package Controladores;

import static Controladores.MenuController.menuAdministracion;
import Interfaces.CrudInterfaces;
import Modelo.Usuario;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuariosController implements CrudInterfaces {

    private UtilsController metodos = new UtilsController();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    public void CargarDatos() {
        Usuario u1 = new Usuario("111111111", "user1", "pass1");
        Usuario u2 = new Usuario("222222222", "user2", "pass2");
        Usuario u3 = new Usuario("333333333", "user3", "pass3");
        usuarios.add(u1);
        usuarios.add(u2);
        usuarios.add(u3);
    }

    @Override
    public void Registrar() {
        String numeroId = "";
        String codigoUsuario = "";
        String claveAcceso = "";

        Boolean identificadorValido = false;
        while (!identificadorValido) {
            try {
                numeroId = JOptionPane.showInputDialog("Ingrese el número de identificación: ");
                if (numeroId.isEmpty()) {
                    metodos.mensajeAlerta("Debe ingresar una identificación válida");
                } else if (existeIdentificador(numeroId)) {
                    metodos.mensajeAlerta("Esta identificación ya se encuentra registrada");
                } else {
                    identificadorValido = true;
                }
            } catch (Exception e) {
                metodos.mensajeAlerta("Debe ingresar una identificación válida");
            }
        }

        Boolean codigoUsuarioValido = false;
        while (!codigoUsuarioValido) {
            codigoUsuario = JOptionPane.showInputDialog("Ingrese el código de usuario: ");
            if (codigoUsuario.isEmpty()) {
                metodos.mensajeAlerta("Debe ingresar un código de usuario válido");
            } else {
                codigoUsuarioValido = true;
            }
        }

        Boolean claveAccesoValida = false;
        while (!claveAccesoValida) {
            claveAcceso = JOptionPane.showInputDialog("Ingrese la clave de acceso: ");
            if (claveAcceso.isEmpty()) {
                metodos.mensajeAlerta("Debe ingresar una clave de acceso válida");
            } else {
                claveAccesoValida = true;
            }
        }

        Usuario usuario = new Usuario(numeroId, codigoUsuario, claveAcceso);

        String msg = "Identificación: " + usuario.getNumeroIdentificacion()
                + "\nCódigo de usuario: " + usuario.getCodigoUsuario()
                + "\nClave de acceso: " + usuario.getClaveAcceso();

        String titulo = "Validación de datos";
        int resp = metodos.mensajeConfirmacionSIoNo(msg, titulo);

        if (resp == JOptionPane.YES_NO_OPTION) {
            usuarios.add(usuario);
        }
    }

    @Override
    public void Consultar() {
        int indexUsuario = -1;
        String identificacion;
        try {
            identificacion = JOptionPane.showInputDialog("Ingrese el número de identificación: ");
            indexUsuario = buscarIndicePorId(identificacion);

            if (indexUsuario != -1) {
                String info = "";
                String numeroId = usuarios.get(indexUsuario).getNumeroIdentificacion();
                String codigoUsuario = usuarios.get(indexUsuario).getCodigoUsuario();
                String claveAcceso = usuarios.get(indexUsuario).getClaveAcceso();

                info = info + ("El número de identificación del usuario es: " + numeroId
                        + "\nEl código de usuario es: " + codigoUsuario
                        + "\nLa clave de acceso es: " + claveAcceso + "\n\n");

                metodos.mensajeInformacion(info, "Información del usuario");
            } else {
                metodos.mensajeAlerta(String.format("El usuario con número de identificación %s no se encuentra registrado", identificacion));
            }
        } catch (Exception e) {
            metodos.mensajeAlerta("Hubo un error en la búsqueda del usuario");
        }
    }

    public void Ver() {

    }

    @Override
    public void Editar() {
        String identificacion = "";
        int indexUsuario = -1;
        try {
            identificacion = JOptionPane.showInputDialog("Ingrese el número de identificación: ");
            indexUsuario = buscarIndicePorId(identificacion);

            if (indexUsuario != -1) {
                String numeroIdentificacion = usuarios.get(indexUsuario).getNumeroIdentificacion();
                String codigoUsuario = usuarios.get(indexUsuario).getCodigoUsuario();
                String claveAcceso = usuarios.get(indexUsuario).getClaveAcceso();

                numeroIdentificacion = JOptionPane.showInputDialog("El nuevo número de identificación es: ", numeroIdentificacion);
                codigoUsuario = JOptionPane.showInputDialog("El nuevo código de usuario es: ", codigoUsuario);
                claveAcceso = JOptionPane.showInputDialog("La nueva clave de acceso es: ", claveAcceso);

                usuarios.get(indexUsuario).setNumeroIdentificacion(numeroIdentificacion);
                usuarios.get(indexUsuario).setCodigoUsuario(codigoUsuario);
                usuarios.get(indexUsuario).setClaveAcceso(claveAcceso);

                metodos.mensajeInformacion("Modificación realizada con éxito", "Modificación usuario");

            } else {
                metodos.mensajeAlerta(String.format("El número de identificación %s no se encuentra registrado", identificacion));
            }
        } catch (Exception e) {
            metodos.mensajeAlerta("Hubo un error en la modificación del usuario");
        }
    }

    @Override
    public void Eliminar() {
        String identificacion = "";
        int indexUsuario = -1;

        try {
            identificacion = JOptionPane.showInputDialog("Ingrese el número de identificación: ");
            indexUsuario = buscarIndicePorId(identificacion);

            if (indexUsuario != -1) {
                Usuario usuario = usuarios.get(indexUsuario);
                int opcion = metodos.mensajeConfirmacionSIoNo(usuario.toString(), "¿Desea eliminar este usuario?");

                if (opcion == JOptionPane.YES_OPTION) {
                    usuarios.remove(indexUsuario);
                    metodos.mensajeInformacion("El usuario se eliminó correctamente", "Eliminación de usuario");
                }
            } else {
                metodos.mensajeAlerta(String.format("El número de identificación %s no se encuentra registrado", identificacion));
            }
        } catch (Exception e) {
            metodos.mensajeAlerta("Hubo un error en la eliminación del usuario");
        }
    }

    public void menuUsuarios() {
        String[] opciones = {"Registrar", "Consultar", "Modificar", "Eliminar", "Volver"};
        int opcion = -1;
        while (opcion != opciones.length - 1) {
            opcion = metodos.menuBotones("Seleccione una opción", "Usuarios", opciones, "Volver");
            switch (opcion) {
                case 0:
                    Registrar();
                    break;
                case 1:
                    Consultar();
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

    public int buscarIndicePorId(String identificador) {
        int indexUsuario = -1;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNumeroIdentificacion().equals(identificador)) {
                indexUsuario = i;
                break;
            }
        }
        return indexUsuario;
    }

    public Usuario buscarPorId(String identificador) {
        Usuario usuario = null;
        for (Usuario u : usuarios) {
            if (u.getNumeroIdentificacion().equals(identificador)) {
                usuario = u;
                break;
            }
        }

        return usuario;
    }

    public Boolean existeIdentificador(String identificador) {
        boolean existe = false;
        for (Usuario usuario : usuarios) {
            if (usuario.getNumeroIdentificacion().equals(identificador)) {
                existe = true;
                break;
            }
        }

        return existe;
    }

    @Override
    public void Informe() {

    }
}
