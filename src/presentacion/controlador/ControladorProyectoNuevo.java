/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package presentacion.controlador;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import java.time.LocalDate;
import presentacion.vista.info.InfoProyecto;
import presentacion.vista.Main;
import presentacion.vista.Vista;
import presentacion.vista.VistaHija;
import presentacion.vista.VistaPadre;
import presentacion.vista.info.Info;
import dominio.Proyecto;
import dominio.DominioFactory;
import dao.DaoFactory;
import dao.ProyectoDao;

/**
 *
 * @author marces
 */
public class ControladorProyectoNuevo implements ControladorHijo {

    private VistaPadre vistaProyecto;
    private VistaHija vistaNuevoProyecto;
    private final ProyectoDao proyectoDao;
    private Long id;

    public ControladorProyectoNuevo() {
        proyectoDao = new DaoFactory().crearProyectoDao();
    }

    @Override
    public void setVista(Vista vista) {
        this.vistaNuevoProyecto = (VistaHija) vista;
    }

    @Override
    public void setVistaPadre(Vista vista) {
        this.vistaProyecto = (VistaPadre) vista;
    }

    @Override
    public void crearNuevoRegistro(Info info) {
        
        InfoProyecto infoProyecto = (InfoProyecto) info;
        
        try {
            
            id = infoProyecto.getId();
            if (isNull(id)) {

                DominioFactory factory = new DominioFactory();
                Proyecto proyecto = factory.crearProyecto();
                setearDatos(infoProyecto, proyecto);

                proyectoDao.create(proyecto);

            } else {

                Proyecto proyecto = proyectoDao.read(infoProyecto.getId());
                setearDatos(infoProyecto, proyecto);

                proyectoDao.update(proyecto);
            }
            vistaNuevoProyecto.mostrarMensaje("Proyecto guardado exitosamente");
            vistaProyecto.actualizar();
            Main.getInstance().cerrarDialogAux();

        } catch (IllegalArgumentException ex) {

            vistaNuevoProyecto.mostrarMensaje("Error: "
                    + "el proyecto no pudo ser guardado.\n\n"
                    + ex.getMessage());
        }
    }

    private void setearDatos(InfoProyecto info, Proyecto proyecto)
            throws IllegalArgumentException {

        proyecto.withDescripcion(info.getDescripcion())
                .withContratista(info.getContratista())
                .withtFechaInicio(info.getFechaInicio())
                .withFechaEstimada(info.getFechaEstimada())
                .withPresupuesto(validarPresupuesto(info.getPresupuesto()));
        
        validarFecha(info.getFechaInicio(), info.getFechaEstimada());
    }

    // <editor-fold defaultstate="collapsed" desc=" Validaciones ">
    public Integer validarPresupuesto(String value) throws IllegalArgumentException {

        checkArgument(isNumeric(value),
                "El campo presupuesto debe ser un número válido");

        return Integer.parseInt(value);
    } // Ausencia del atributo


    public void validarFecha(LocalDate inicio, LocalDate estimada) 
            throws IllegalArgumentException {
        
        // Es opcional...
        if (!isNull(estimada)) {
            
            checkArgument(inicio.isBefore(estimada), 
                    "La fecha estimada debe ser posterior a la fecha de inicio");
        }
    }
    // </editor-fold> 
}
