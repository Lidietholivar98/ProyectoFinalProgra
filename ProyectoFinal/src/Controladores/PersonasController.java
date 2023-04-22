package Controladores;

//Implementar los métodos abstractos de la interface.
import static Controladores.MenuController.menuAdministracion;
import Interfaces.CrudInterfaces;
import Modelo.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

//Usamos implements
//Implementar los métodos abstractos
public class PersonasController implements CrudInterfaces {

    //Implementa la clase MetodosController (Copia)
    private UtilsController metodos = new UtilsController();
    private static List<Persona> personas = new ArrayList();

    public void CargarDatos() {
        Persona p1 = new Persona("111111111", "Nombre Uno", "55555555", "test1@gmail.com");
        Persona p2 = new Persona("222222222", "Nombre Dos", "88888888", "test2@gmail.com");
        Persona p3 = new Persona("333333333", "Nombre Tres", "99999999", "tes3@hotmail.com");
        personas.add(p1);
        personas.add(p2);
        personas.add(p3);
    }
    
    @Override
    public void Registrar() {
        String numeroId = "";
        String nombre = "";
        String numeroTelefono= "";
        String email = "";
        
        Boolean identificadorValido = false;
        while(!identificadorValido){
            try{
                numeroId = JOptionPane.showInputDialog("Ingrese el número de identificación: ");
                if(numeroId.isEmpty()){
                    metodos.mensajeAlerta("Debe ingresar una identificación válida");
                }
                else if(existeIdentificador(numeroId)){
                    metodos.mensajeAlerta("Esta identificación ya se encuentra registrada");
                }
                else{
                    identificadorValido = true;
                }
            } catch(Exception e){
                metodos.mensajeAlerta("Debe ingresar una identificación válida");
            }
        }

        Boolean nombreValido = false;
        while (!nombreValido) {
            nombre = JOptionPane.showInputDialog("Ingrese el nombre: ");
            if (nombre.isEmpty()) {
                metodos.mensajeAlerta("Debe ingresar un nombre válido");
            } else {
                nombreValido = true;
            }
        }
        
        Boolean numeroTelefonoValido = false;
        while (!numeroTelefonoValido) {
            numeroTelefono = JOptionPane.showInputDialog("Ingrese el número de teléfono: ");
            if (numeroTelefono.isEmpty()) {
                metodos.mensajeAlerta("Debe ingresar un número de teléfono válido");
            } else {
                numeroTelefonoValido = true;
            }
        }
        
        Boolean esCorreoValido = false;
        while (!esCorreoValido){
            email = JOptionPane.showInputDialog("Ingrese el correo electrónico: ");
            esCorreoValido = metodos.esCorreoValido(email);
            if(!esCorreoValido){
                metodos.mensajeAlerta("Debe ingresar un correo válido");
            }
        }

        Persona persona = new Persona(numeroId, nombre, numeroTelefono, email);
        
        String msg = "Identificación: " + persona.getNumeroIdentificacion() 
                   + "\nNombre: " + persona.getNombre()
                   + "\nNúmero teléfono: " + persona.getNumeroTelefono()
                   + "\nCorreo electrónico: " + persona.getEmail();
        String titulo = "Validación de datos";
        int resp = metodos.mensajeConfirmacionSIoNo(msg, titulo);
        
        if (resp == JOptionPane.YES_NO_OPTION) {
            personas.add(persona);
        }
    }
    
    public void Ver() {
        
    }

    @Override
    public void Editar() {
        String identificacion = "";
        int indexPersona = -1;
        try {
            identificacion = JOptionPane.showInputDialog("Ingrese el número de identificación: ");
            indexPersona = buscarIndicePorId(identificacion);

            if (indexPersona != -1) {
                String numeroIdentificacion = personas.get(indexPersona).getNumeroIdentificacion();
                String nombre = personas.get(indexPersona).getNombre();
                String numeroTelefono = personas.get(indexPersona).getNumeroTelefono();
                String email = personas.get(indexPersona).getEmail();

                numeroIdentificacion = JOptionPane.showInputDialog("El nuevo número de identificación es: ", numeroIdentificacion);
                nombre = JOptionPane.showInputDialog("El nuevo nombre es: ", nombre);
                numeroTelefono = JOptionPane.showInputDialog("El nuevo número de teléfono es : ", numeroTelefono);
                email = JOptionPane.showInputDialog("El nuevo correo electrónico es: ", email);

                personas.get(indexPersona).setNumeroIdentificacion(numeroIdentificacion);//TODO: verificar si esto es valido
                personas.get(indexPersona).setNombre(nombre);
                personas.get(indexPersona).setNumeroTelefono(numeroTelefono);
                personas.get(indexPersona).setEmail(email);
                
                metodos.mensajeInformacion("Modificación realizada con éxito", "Modificación persona");

            } else {
                metodos.mensajeAlerta(String.format("El número de identificación %s no se encuentra registrado", identificacion));
            }
        } catch (Exception e) {
            metodos.mensajeAlerta("Hubo un error en la modificación de la persona");
        }
    }
    
       @Override
    public void Eliminar() {
        String identificacion;
        try {
            identificacion = JOptionPane.showInputDialog("Ingrese el número de identificación de la persona a eliminar: ");
            int indexPersona = buscarIndicePorId(identificacion);
            if (indexPersona != -1) {
                int resp = metodos.mensajeConfirmacionSIoNo("¿Está seguro que desea eliminar la persona?", "Confirmación");
                if (resp == JOptionPane.YES_OPTION) {
                    personas.remove(indexPersona);
                    metodos.mensajeInformacion("La persona ha sido eliminada correctamente", "Eliminación exitosa");
                }
            } else {
                metodos.mensajeAlerta(String.format("La persona con identificación %s no se encuentra registrada", identificacion));
            }
        } catch (Exception e) {
            metodos.mensajeAlerta("Hubo un error al eliminar la persona");
        }
    }
    
    public void menuPersonas() {
        String[] opciones = {"Registrar", "Consultar", "Modificar", "Eliminar", "Volver"};
        int opcion = -1;
        while (opcion != opciones.length - 1) {
            opcion = metodos.menuBotones("Seleccione una opción", "Personas", opciones, "Volver");
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

    @Override
    public void Anular() {

    }    

    @Override
    public void Informe() {

    }
    
    public int buscarIndicePorId(String identificador){
        int indexPersona = -1;
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getNumeroIdentificacion().equals(identificador)) {
                indexPersona = i;
                break;
            }
        }
        return indexPersona;
    }
    
    public Persona buscarPorId(String identificador){
        Persona persona = null;
        for (Persona p : personas) {
            if (p.getNumeroIdentificacion().equals(identificador)) {
                persona = p;
                break;
            }
        }

        return persona;
    }
    
    public Persona buscarPorNombre(String nombre) {
        Persona persona = new Persona();
        for (Persona p : personas) {
            if (p.getNombre().equals(nombre)) {
                persona = p;
                break;
            }
        }

        return persona;
    }
    
    public Boolean existeIdentificador(String identificador) {
        boolean existe = false;
        for (Persona persona : personas) {
            if (persona.getNumeroIdentificacion().equals(identificador)) {
                existe = true;
                break;
            }
        }

        return existe;
    }

    @Override
    public void Consultar() {
        int indexPersona = -1;
        String identificacion;
        try {
            identificacion = JOptionPane.showInputDialog("Ingrese el número de identificación: ");
            indexPersona = buscarIndicePorId(identificacion);

            if (indexPersona != -1) {
                String info = "";
                String numeroId = personas.get(indexPersona).getNumeroIdentificacion();
                String nombre = personas.get(indexPersona).getNombre();
                String numeroTelefono = personas.get(indexPersona).getNumeroTelefono();
                String email = personas.get(indexPersona).getEmail();

                info = info + ("El número de identificación de la persona es: " + numeroId
                        + "\nEl nombre es: " + nombre
                        + "\nEl número de teléfono es: " + numeroTelefono
                        + "\nEl correo electrónico es: " + email + "\n\n");

                metodos.mensajeInformacion(info, "Información de la persona");
            } else {
                metodos.mensajeAlerta(String.format("El número de identificación %s no se encuentra registrado", identificacion));
            }
        } catch (Exception e) {
            metodos.mensajeAlerta("Hubo un error en la búsqueda de la persona");
        }
    }

    @Override
    public void Buscar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
