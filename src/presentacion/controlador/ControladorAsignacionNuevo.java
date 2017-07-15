/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package presentacion.controlador;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import presentacion.vista.info.InfoAsignacion;
import presentacion.vista.Main;
import presentacion.vista.Vista;
import presentacion.vista.VistaHija;
import presentacion.vista.VistaPadre;
import dao.DaoFactory;
import dao.AsignacionDao;
import dominio.Empleado;
import dominio.Proyecto;
import dominio.Asignacion;
import dominio.DominioFactory;

/**
 *
 * @author marces
 */
public class ControladorAsignacionNuevo implements ControladorHijo {

    private VistaPadre vistaAsignacion;
    private VistaHija vistaNuevaAsignacion;
    private Long id;
    private final AsignacionDao asignacionDao;

    public ControladorAsignacionNuevo() {
        DaoFactory factory = new DaoFactory();
        asignacionDao = factory.crearAsignacionDao();
    }

    @Override
    public void setVista(Vista vista) {
        this.vistaNuevaAsignacion = (VistaHija) vista;
    }

    @Override
    public void setVistaPadre(Vista vista) {
        this.vistaAsignacion = (VistaPadre) vista;
    }

    public void guardarAsignacion(InfoAsignacion infoAsignacion) {

        id = infoAsignacion.getId();
        
        // Se captura una excepcion de validación de campos
        try {
        
            if (isNull(id)) {

                Asignacion asignacion = new DominioFactory().crearAsignacion();
                setearDatos(infoAsignacion, asignacion);
                asignacionDao.create(asignacion);

            } else {

                Asignacion asignacion = asignacionDao.read(infoAsignacion.getId());
                setearDatos(infoAsignacion, asignacion);

                // Por el momento solo puede cambiar la funcion
                asignacionDao.update(asignacion);
            }

            vistaNuevaAsignacion.mostrarMensaje("Asignación guardada exitosamente");
            vistaAsignacion.actualizar();
            Main.getInstance().cerrarDialogAux();
            
        } catch (IllegalArgumentException ex) {
            
            vistaNuevaAsignacion.mostrarMensaje(ex.getMessage());
        }
    }

    private void setearDatos(InfoAsignacion info, Asignacion asignacion)
            throws IllegalArgumentException {
    
        asignacion.withEmpleado((Empleado) info.getEmpleado())  // info aloja una ref. a empleado
                  .withProyecto((Proyecto) info.getProyecto()) // info aloja una ref. a proyecto
                  .withPuesto(info.getPuesto())
                  .withHoras(validarHoras(info.getHoras()))
                  .withPago(validarPago(
                          (Proyecto) info.getProyecto(), info.getPago()));
    }
    
     /** La vista indica al controlador que cambió el item elegido del
     * comboBoxModel, y requiere los respectivos datos actualizados: en este
     * caso el presupuesto del proyecto elegido (un textField).
     * @param info objeto con la información del proyecto que manejan las vistas.
     * @param selectedItem la proyecto elegido en el ComboBox
     */
    public void cambiaElProyecto(InfoAsignacion info, Object selectedItem) {
        
        Proyecto proyecto = (Proyecto) selectedItem;
        info.withPresupuestoDisponible(proyecto.getPresupuestoDisponible().toString());
    }
    
    //<editor-fold defaultstate="collapsed" desc="validación">
    private Integer validarHoras(String value) throws IllegalArgumentException {
        
        // Es opcional...
        if (!isNull(value)) {
            
            checkArgument(isNumeric(value),
                    "El campo horas debe ser un número válido");
            
            return Integer.parseInt(value);
        } // Ausencia del atributo
        else {
            return null;
        }
    }
    
    private Integer validarPago(Proyecto proyecto, String value) throws IllegalArgumentException {
        
        Integer aux = validarHoras(value); // Se aplica la misma regla.
        
        checkArgument(proyecto.presupuestoSuficiente(aux), 
                "No hay presupuesto disponible para pagarle al empleado");
        
        return aux;
    }
//</editor-fold>
}