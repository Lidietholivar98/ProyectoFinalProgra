package Controladores;

import Controladores.UtilsController;
import static Controladores.UtilsController.menuAdministracion;
import Interfaces.CrudInterfaces;
import Modelo.Chofer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ChoferesController implements CrudInterfaces{
        
    UtilsController metodos = new UtilsController();
    private static List<Chofer> choferes = new ArrayList();

    public void CargarDatos() {
        Chofer p1 = new Chofer("111111111", "04/12/2024");
        Chofer p2 = new Chofer("222222222", "08/09/2026");
        Chofer p3 = new Chofer("333333333", "23/03/2027");
        choferes.add(p1);
        choferes.add(p2);
        choferes.add(p3);
    }
   
    @Override
    public void Crear() {
        String numeroId = "";
        String fechaLicencia = "";

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

        Boolean fechaLicenciaValido = false;
        while (!fechaLicenciaValido) {
            fechaLicencia = JOptionPane.showInputDialog("Ingrese la fecha de expiracion de la licencia: ");
            if (fechaLicencia.isEmpty()) {
                metodos.mensajeAlerta("Debe ingresar una fecha válida");
            } else {
                fechaLicenciaValido = true;
            }
        }

        Chofer chofer = new Chofer(numeroId, fechaLicencia);

        String msg = "Identificación: " + chofer.getNumeroIdentificacion()
                + "\nLicencia: " + chofer.getVencimientoLicencia();

        String titulo = "Validación de datos";
        int resp = metodos.mensajeConfirmacionSIoNo(msg, titulo);

        if (resp == JOptionPane.YES_NO_OPTION) {
            choferes.add(chofer);
        }
    }
    
    public void Ver() {
        int indexChofer = -1;
        String identificacion;
        try {
            identificacion = JOptionPane.showInputDialog("Ingrese el número de identificación: ");
            indexChofer = buscarIndicePorId(identificacion);

            if (indexChofer != -1) {
                String info = "";
                String numeroId = choferes.get(indexChofer).getNumeroIdentificacion();
                String vencimientoLicencia = choferes.get(indexChofer).getVencimientoLicencia();

                info = info + ("El número de identificación de la chofer es: " + numeroId
                        + "\nLa licencia expira el: " + vencimientoLicencia + "\n\n");

                metodos.mensajeInformacion(info, "Información de la chofer");
            } else {
                metodos.mensajeAlerta(String.format("El número de identificación %s no se encuentra registrado", identificacion));
            }
        } catch (Exception e) {
            metodos.mensajeAlerta("Hubo un error en la búsqueda de la chofer");
        }
    }

    @Override
    public void Modificar() {
        String identificacion = "";
        int indexChofer = -1;
        try {
            identificacion = JOptionPane.showInputDialog("Ingrese el número de identificación: ");
            indexChofer = buscarIndicePorId(identificacion);

            if (indexChofer != -1) {
                String numeroIdentificacion = choferes.get(indexChofer).getNumeroIdentificacion();
                String vencimientoLicencia = choferes.get(indexChofer).getVencimientoLicencia();

                numeroIdentificacion = JOptionPane.showInputDialog("El nuevo número de identificación es: ", numeroIdentificacion);
                vencimientoLicencia = JOptionPane.showInputDialog("La nueva fecha de expiracion: ", vencimientoLicencia);

                choferes.get(indexChofer).setNumeroIdentificacion(numeroIdentificacion);//TODO: verificar si esto es valido
                choferes.get(indexChofer).setVencimientoLicencia(vencimientoLicencia);

                metodos.mensajeInformacion("Modificación realizada con éxito", "Modificación chofer");

            } else {
                metodos.mensajeAlerta(String.format("El número de identificación %s no se encuentra registrado", identificacion));
            }
        } catch (Exception e) {
            metodos.mensajeAlerta("Hubo un error en la modificación de la chofer");
        }
    }
    
    @Override
    public void Eliminar() {
        String identificacion = "";
        int indexChofer = -1;

        try {
            identificacion = JOptionPane.showInputDialog("Ingrese el número de identificación: ");
            indexChofer = buscarIndicePorId(identificacion);

            if (indexChofer != -1) {
                Chofer chofer = choferes.get(indexChofer);
                int opcion = metodos.mensajeConfirmacionSIoNo(chofer.toString(), "¿Desea eliminar este chofer?");

                if (opcion == JOptionPane.YES_OPTION) {
                    choferes.remove(indexChofer);
                    metodos.mensajeInformacion("El chofer se eliminó correctamente", "Eliminación de chofer");
                }
            } else {
                metodos.mensajeAlerta(String.format("El número de identificación %s no se encuentra registrado", identificacion));
            }
        } catch (Exception e) {
            metodos.mensajeAlerta("Hubo un error en la eliminación del chofer");
        }
    }

    
    
    
    public void menuChoferes() {
        String[] opciones = {"Registrar", "Consultar", "Modificar", "Eliminar", "Volver"};
        int opcion = -1;
        while (opcion != opciones.length - 1) {
            opcion = metodos.menuBotones("Seleccione una opción", "Choferes", opciones, "Volver");
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
    
    public int buscarIndicePorId(String identificador){
        int indexChofer = -1;
        for (int i = 0; i < choferes.size(); i++) {
            if (choferes.get(i).getNumeroIdentificacion().equals(identificador)) {
                indexChofer = i;
                break;
            }
        }
        return indexChofer;
    }
    
    public Chofer buscarPorId(String identificador) {
        Chofer chofer = new Chofer();
        for (Chofer p : choferes) {
            if (p.getNumeroIdentificacion().equals(identificador)) {
                chofer = p;
                break;
            }
        }

        return chofer;
    }

    public Boolean existeIdentificador(String identificador) {
        boolean existe = false;
        for (Chofer chofer : choferes) {
            if (chofer.getNumeroIdentificacion().equals(identificador)) {
                existe = true;
                break;
            }
        }

        return existe;
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