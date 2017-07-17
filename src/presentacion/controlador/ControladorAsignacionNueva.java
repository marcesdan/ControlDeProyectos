/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
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
import dominio.Empleado;
import dominio.Proyecto;
import dominio.Asignacion;
import dominio.DominioFactory;
import presentacion.vista.info.Info;

/**
 *
 * @author marces
 */
public class ControladorAsignacionNueva implements ControladorHijo {

    private VistaPadre vistaAsignacion;
    private VistaHija vistaNuevaAsignacion;
    private Long id;

    @Override
    public void setVista(Vista vista) {
        this.vistaNuevaAsignacion = (VistaHija) vista;
    }

    @Override
    public void setVistaPadre(Vista vista) {
        this.vistaAsignacion = (VistaPadre) vista;
    }

    @Override
    public void guardarRegistro(Info info) {
       
        InfoAsignacion infoAsignacion = (InfoAsignacion) info;
        DaoFactory daoFactory = new DaoFactory();
        
        // El id determina si es un alta o una modificación (Create o Update)
        id = infoAsignacion.getId();
        if (isNull(id)) { // Entonces se requiere crear un registro (Create)

            // Nueva instancia de Asignacion
            Asignacion asignacion = new DominioFactory().crearAsignacion();
            // Llamamos a los set de la clase Asignacion. 
            setearCampos(infoAsignacion, asignacion);
            // Persistimos en la BD la asignacion.
            daoFactory.crearAsignacionDao().create(asignacion);
            // Actualizamos las listas con la referencia hacia Asignacion.
            asignacion.getEmpleado().addAsignacion(asignacion);
            asignacion.getProyecto().addAsignacion(asignacion);
            // Actualizamos en la BD tanto el empleado como el proyecto.
            daoFactory.crearEmpleadoDao().update(asignacion.getEmpleado());
            daoFactory.crearProyectoDao().update(asignacion.getProyecto());

        } else { // De lo contrario se requiere modificar un registro (update)

            // Buscamos la asignación por id y nos quedamos con la referencia.
            Asignacion asignacion = daoFactory
                    .crearAsignacionDao()
                    .read(id);
            // Llamamos a los set de la clase Asignacion.
            setearCampos(infoAsignacion, asignacion);
            // Se modifica (persiste) en la BD la asignacion (Update)
            daoFactory.crearAsignacionDao().update(asignacion);
        }
        vistaNuevaAsignacion.mostrarMensaje("Asignación guardada exitosamente");
        // Actualizamos el listado con el nuevo registro o su modificación.
        vistaAsignacion.actualizar();
        // Cerramos la vista hija.
        Main.getInstance().cerrarDialogAux();
    }

    private void setearCampos(InfoAsignacion info, Asignacion asignacion) {
        // Se captura una excepcion de validación de campos
        try { 
            asignacion.withEmpleado((Empleado) info.getEmpleado()) // info aloja una ref. a empleado
                    .withProyecto((Proyecto) info.getProyecto()) // info aloja una ref. a proyecto
                    .withPuesto(info.getPuesto())
                    .withHoras(validarHoras(info.getHoras()))
                    .withPago(validarPago(
                            (Proyecto) info.getProyecto(), 
                            info.getPago()));
            
        } catch (IllegalArgumentException ex) {
            // Mostramos un mensaje con el motivo de la excepción.
            vistaNuevaAsignacion.mostrarMensaje(ex.getMessage());
        }
    }

    /**
     * La vista indica al controlador que cambió el item elegido del
     * comboBoxModel, y requiere los respectivos datos actualizados: en este
     * caso el presupuesto del proyecto elegido (un textField).
     *
     * @param info objeto con la información del proyecto que manejan las
     * vistas.
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

            // Verificamos que sea un número
            checkArgument(isNumeric(value),
                    "El campo horas debe ser un número válido");

            return Integer.parseInt(value);
        } // Ausencia del atributo
        else {
            return null;
        }
    }

    private Integer validarPago(Proyecto proyecto, String value) throws IllegalArgumentException {

        // Es opcional...
        if (!isNull(value)) {
        
            // Verificamos que sea un número
            checkArgument(isNumeric(value),
                    "El campo horas debe ser un número válido");

            Integer aux = Integer.parseInt(value);

            // Verificamos que sea suficiente el presupuesto 
            checkArgument(proyecto.presupuestoSuficiente(aux),
                    "No hay presupuesto disponible para pagarle al empleado");

            return aux;
        }
        
        else return null;
    }
//</editor-fold>
}
