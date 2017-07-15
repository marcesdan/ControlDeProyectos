/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package presentacion.controlador;

import static presentacion.vista.info.InfoProyecto.crearInfoProyecto;
import dao.DaoFactory;
import dominio.Proyecto;
import presentacion.vista.Main;
import presentacion.vista.Vista;
import presentacion.vista.VistaHija;
import presentacion.vista.VistaPadre;
import presentacion.factory.AbstractFactoryCompleta;
import presentacion.modelo.ATableModel;
import dao.ProyectoDao;
import presentacion.factory.ProyectoFactory;

/**
 *
 * @author marces
 */
public class ControladorProyecto implements Controlador {
    
    private VistaPadre vistaPadre;
    private VistaHija vistaHija;
    private ControladorHijo controladorHijo;
    
    @Override
    public void setVista(Vista vista) {
        this.vistaPadre = (VistaPadre) vista;
    }
    
    public void nuevoProyecto() {
        cargar();
        Main.getInstance().mostrarPanelEnDialog(vistaHija, ""
                    + "Nuevo proyecto");
    }
    
    public void modificarProyecto(ATableModel model, int fila) {
        
        // Si se se seleccionó una fila...
        if (fila != -1 ) {
            cargar();
            
            // Nos quedamos con el campo id de fila seleccionada en la tabla
            Long id = model.getId(fila);
            
            // Realizamos la busqueda
            ProyectoDao dao = new DaoFactory().crearProyectoDao();
            Proyecto proyecto = dao.read(id); 
            
            vistaHija.setDatos(crearInfoProyecto()
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
    
    public void eliminarProyecto(ATableModel model, int fila) {
        
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
    
    private void cargar() {
        
        AbstractFactoryCompleta factory = new ProyectoFactory();
        controladorHijo = factory.crearControladorHijo();
        vistaHija = factory.crearVistaHija();
        controladorHijo.setVista(vistaHija);
        controladorHijo.setVistaPadre(vistaPadre);
        vistaHija.setControlador(controladorHijo);
    }
}
