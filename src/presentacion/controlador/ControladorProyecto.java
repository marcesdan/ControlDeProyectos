/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.controlador;

import static presentacion.vista.info.InfoProyecto.crearInfoProyecto;
import dao.DaoFactory;
import dao.ProyectoDao;
import dominio.Asignacion;
import dominio.Empleado;
import dominio.Proyecto;
import java.util.Iterator;
import presentacion.vista.Main;
import presentacion.modelo.ATableModel;
import presentacion.factory.ProyectoFactory;

/**
 *
 * @author marces
 */
public class ControladorProyecto extends ControladorPadre {
    
    @Override
    public void nuevo() {
        
        cargar(new ProyectoFactory());
        Main.getInstance().mostrarPanelEnDialog(vistaHija, ""
                    + "Nuevo proyecto");
    }
    
    @Override
    public void modificar(ATableModel model, int fila) {
        
        // Si se se seleccionó una fila...
        if (fila != -1 ) {
            cargar(new ProyectoFactory());
            
            // Nos quedamos con el campo id de fila seleccionada en la tabla
            Long id = model.getId(fila);
            
            // Realizamos la busqueda
            ProyectoDao dao = new DaoFactory().crearProyectoDao();
            Proyecto proyecto = dao.read(id); 
            
            vistaHija.setCamposDeTexto(crearInfoProyecto()
                    .withId(proyecto.getId()) // Tambien disponemos de "id"...
                    .withDescripcion(proyecto.getDescripcion())
                    .withContratista(proyecto.getContratista())
                    .withPresupuesto(proyecto.getPresupuesto().toString())
                    .withFechaInicio(proyecto.getFechaInicioLocalDate())
                    .withFechaEstimada(proyecto.getFechaEstimadaLocalDate())
            );
            
            Main.getInstance().mostrarPanelEnDialog(vistaHija, ""
                    + "Modifición de proyecto");
        }
        
        else vistaPadre.mostrarMensaje(""
                + "Primero debe seleccionar una fila de la tabla");
    }   
    
    @Override
    public void eliminar(ATableModel model, int fila) {
        
        if (fila != -1 ) {
            
            if (vistaPadre.confirmacionBorrado()) {
                // Nos quedamos con el id y la descripcion del proyecto a borrar
                Long id = model.getId(fila);
                String descripcion = (String) model.getValueAt(fila, 0);
                // Buscamos el proyecto
                ProyectoDao proyectoDao = new DaoFactory().crearProyectoDao();
                Proyecto proyecto = proyectoDao.read(id);
                // Se elimina el proyecto de la BD
                proyectoDao.delete(proyecto);
                
                /**
                 * Además de eliminar el proyecto, hay que eliminar con él todas 
                 * sus asignaciones. Con lo cual es necesario actualizar la lista 
                 * de cada uno de los empleados que participaban en ese proyecto
                 */
                Iterator<Asignacion> i = proyecto.getAsignaciones().iterator();
                while (i.hasNext()) {
                    Asignacion asignacion = i.next();
                    // Obtenemos el empleado
                    Empleado empleado = asignacion.getEmpleado();
                    // Eliminamos la asignación del proyecto a borrar
                    empleado.removeAsignacion(asignacion);
                    // Actualizamos
                    new DaoFactory().crearEmpleadoDao().update(empleado);
                    // Y se elimina el elemento de la lista
                    i.remove();
                }
                
                vistaPadre.actualizarListado();
                vistaPadre.mostrarMensaje("El proyecto "+descripcion+""
                        + " fue borrado exitosamente");
            }
        }
        
        else vistaPadre.mostrarMensaje(""
                + "Primero debe seleccionar una fila de la tabla");
    }
}
