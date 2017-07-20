/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.controlador;

import static presentacion.vista.info.InfoEmpleado.crearInfoEmpleado;
import dao.DaoFactory;
import dominio.Empleado;
import presentacion.factory.EmpleadoFactory;
import presentacion.vista.Main;
import presentacion.modelo.ATableModel;
import dao.EmpleadoDao;
import dominio.Asignacion;
import dominio.Proyecto;
import java.util.Iterator;

/**
 *
 * @author marces
 */
public class ControladorEmpleado extends ControladorPadre {
   
    @Override
    public void nuevo() {
        // Se setean las vistas y los controladores mutuamente.
        cargar(new EmpleadoFactory());
        // Y se muestra la vista hija en pantalla
        Main.getInstance().mostrarPanelEnDialog(vistaHija, ""
                    + "Nuevo empleado");
    }
    
    @Override
    public void modificar(ATableModel model, int fila) {
        
        if (fila != -1 ) { // Si se se seleccionó una fila...
            
            // Se setean las vistas y los controladores mutuamente.
            cargar(new EmpleadoFactory());
            // Nos quedamos con el campo id de fila seleccionada en la tabla
            Long id = model.getId(fila);
            
            // Realizamos la busqueda y nos quedamos con el empleado a modificar.
            Empleado empleado = new DaoFactory()
                    .crearEmpleadoDao()
                    .read(id); 
            
            /* 
            * Preparamos los datos del empleado a modificar para mostrarlos en
            * pantalla. Para ello, se setean los campos de infoEmpleado (Strings).
            * (Parameter Object Refactoring).
            */
            vistaHija.setCamposDeTexto(crearInfoEmpleado()
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
            
            // Mostramos la vista hija con los datos del empleado a modificar.
            Main.getInstance().mostrarPanelEnDialog(vistaHija, ""
                    + "Modifición de empleado");
        }
        
        // De lo contrario, no se selecciono ningún empleado (fila) para modificar.
        else vistaPadre.mostrarMensaje(""
                + "Primero debe seleccionar una fila de la tabla");
    }   
    
    @Override
    public void eliminar(ATableModel model, int fila) {
        // Si se se seleccionó una fila...
        if (fila != -1 ) {
            // Se requiere una confirmación
            if (vistaPadre.confirmacionBorrado()) {
                
                Long id = model.getId(fila);
                String nombre = (String) model.getValueAt(fila, 1);
                
                // Buscamos al empleado
                EmpleadoDao empleadoDao = new DaoFactory().crearEmpleadoDao();
                Empleado empleado = empleadoDao.read(id);
                // Se elimina el empleado de la BD
                empleadoDao.delete(empleado);
                
                /**
                 * Además de eliminar al empleado, hay que eliminar con él 
                 * todas sus asignaciones. Con lo cual es necesario actualizar 
                 * la lista de cada uno de los proyectos en los cuales 
                 * participaba el empleado.
                 */
                Iterator<Asignacion> i = empleado.getAsignaciones().iterator();
                while (i.hasNext()) {
                    Asignacion asignacion = i.next();
                    // Obtenemos el proyecto
                    Proyecto proyecto = asignacion.getProyecto();
                    // Eliminamos la asignación del empleado a borrar
                    proyecto.removeAsignacion(asignacion);
                    // Actualizamos
                    new DaoFactory().crearProyectoDao().update(proyecto);
                    // Y se elimina el elemento de la lista
                    i.remove();
                }
                
                // Se actualiza el listado en pantalla.
                vistaPadre.actualizarListado();
                vistaPadre.mostrarMensaje("El empleado "+nombre+""
                        + " fue borrado exitosamente");
            }
        }
        else vistaPadre.mostrarMensaje(""
                + "Primero debe seleccionar una fila de la tabla");
    }
}
