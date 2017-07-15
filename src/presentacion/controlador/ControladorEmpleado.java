/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package presentacion.controlador;

import dao.DaoFactory;
import dominio.Empleado;
import presentacion.factory.EmpleadoFactory;
import presentacion.vista.Main;
import presentacion.vista.Vista;
import presentacion.vista.VistaHija;
import presentacion.vista.VistaPadre;
import presentacion.modelo.ATableModel;
import static presentacion.vista.info.InfoEmpleado.crearInfoEmpleado;
import dao.EmpleadoDao;
import presentacion.factory.AbstractFactory;

/**
 *
 * @author marces
 */
public class ControladorEmpleado implements ControladorPadre {
    
    private VistaPadre vistaPadre;
    private VistaHija vistaHija;
    private ControladorHijo controladorHijo;
    
    @Override
    public void setVista(Vista vista) {
        this.vistaPadre = (VistaPadre) vista;
    }
   
    @Override
    public void nuevo() {
        cargar();
        Main.getInstance().mostrarPanelEnDialog(vistaHija, ""
                    + "Nuevo empleado");
    }
    
    @Override
    public void modificar(ATableModel model, int fila) {
        
        // Si se se seleccionó una fila...
        if (fila != -1 ) {
            cargar();
            
            // Nos quedamos con el campo id de fila seleccionada en la tabla
            Long id = model.getId(fila);
            
            // Realizamos la busqueda
            EmpleadoDao dao = new DaoFactory().crearEmpleadoDao();
            Empleado empleado = dao.read(id); 
            
            vistaHija.setDatos(crearInfoEmpleado()
                    .withId(empleado.getId()) // Tambien disponemos de "id"...
                    .withApellido(empleado.getApellido())
                    .withNombre(empleado.getNombre())
                    .withDocumento(empleado.getDocumento().toString())
                    .withNacimiento(empleado.getNacimientoLocalDate())
                    .withCalle(empleado.getDireccion().getCalle())
                    .withAltura(empleado.getDireccion().getAltura())
                    .withBarrio(empleado.getDireccion().getBarrio())
                    .withTelefono(empleado.getContacto().getTelefono())
                    .withCelular(empleado.getContacto().getCelular())
                    .withEmail(empleado.getContacto().getEmail())
            );
            
            Main.getInstance().mostrarPanelEnDialog(vistaHija, ""
                    + "Modifición de empleado");
        }
        
        else vistaPadre.mostrarMensaje(""
                + "Primero debe seleccionar una fila de la tabla");
    }   
    
    @Override
    public void eliminar(ATableModel model, int fila) {
        
        if (fila != -1 ) {
            
            if (vistaPadre.confirmacionBorrado()) {
                
                Long id = model.getId(fila);
                String apellido = (String) model.getValueAt(fila, 1);
               
                EmpleadoDao dao = new DaoFactory().crearEmpleadoDao();
                dao.delete(dao.read(id));
                
                vistaPadre.actualizar();
                vistaPadre.mostrarMensaje("El empleado "+apellido+""
                        + " fue borrado exitosamente");
            }
        }
        
        else vistaPadre.mostrarMensaje(""
                + "Primero debe seleccionar una fila de la tabla");
    }
    
    private void cargar() {
        AbstractFactory factory = new EmpleadoFactory();
        controladorHijo = factory.crearControladorHijo();
        vistaHija = factory.crearVistaHija();
        controladorHijo.setVista(vistaHija);
        controladorHijo.setVistaPadre(vistaPadre);
        vistaHija.setControlador(controladorHijo);
    }
}
