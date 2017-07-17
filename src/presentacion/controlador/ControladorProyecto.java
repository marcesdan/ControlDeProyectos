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
import dominio.Proyecto;
import presentacion.vista.Main;
import presentacion.vista.Vista;
import presentacion.vista.VistaHija;
import presentacion.vista.VistaPadre;
import presentacion.modelo.ATableModel;
import dao.ProyectoDao;
import presentacion.factory.ProyectoFactory;
import presentacion.factory.AbstractFactory;

/**
 *
 * @author marces
 */
public class ControladorProyecto implements ControladorPadre {
    
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
                    + "Nuevo proyecto");
    }
    
    @Override
    public void modificar(ATableModel model, int fila) {
        
        // Si se se seleccionó una fila...
        if (fila != -1 ) {
            cargar();
            
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
                
                Long id = model.getId(fila);
                String apellido = (String) model.getValueAt(fila, 0);
               
                ProyectoDao dao = new DaoFactory().crearProyectoDao();
                dao.delete(dao.read(id));
                
                vistaPadre.actualizar();
                vistaPadre.mostrarMensaje("El proyecto "+apellido+""
                        + " fue borrado exitosamente");
            }
        }
        
        else vistaPadre.mostrarMensaje(""
                + "Primero debe seleccionar una fila de la tabla");
    }
    
    /** Se setean las vistas y los controladores mutuamente. */
    private void cargar() {    
        AbstractFactory factory = new ProyectoFactory();
        controladorHijo = factory.crearControladorHijo();
        vistaHija = factory.crearVistaHija();
        controladorHijo.setVista(vistaHija);
        controladorHijo.setVistaPadre(vistaPadre);
        vistaHija.setControlador(controladorHijo);
    }
}
