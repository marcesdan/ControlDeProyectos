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
public class ControladorAsignacionNueva extends ControladorHijo {
    
    @Override
    public void guardarRegistro(Info info) {
        
        InfoAsignacion infoAsignacion = (InfoAsignacion) info;
        DaoFactory daoFactory = new DaoFactory();
        
        // El id determina si es un alta o una modificación (Create o Update)
        Long id = infoAsignacion.getId();
        
        esModificacion = !isNull(id);
        if (!esModificacion) { // Entonces se requiere crear un registro (Create)

            // Nueva instancia de Asignacion
            Asignacion asignacion = new DominioFactory().crearAsignacion();
            // Llamamos a los set de la clase Asignacion. 
            setearCampos(infoAsignacion, asignacion);
            
            if (camposValidos){
                // Persistimos en la BD la asignacion.
                daoFactory.crearAsignacionDao().create(asignacion);
                // Actualizamos las listas con la referencia hacia Asignacion.
                asignacion.getEmpleado().addAsignacion(asignacion);
                asignacion.getProyecto().addAsignacion(asignacion);
                // Actualizamos en la BD tanto el empleado como el proyecto.
                daoFactory.crearEmpleadoDao().update(asignacion.getEmpleado());
                daoFactory.crearProyectoDao().update(asignacion.getProyecto());
                
                finalizarOperacion();
            }

        } else { // De lo contrario se requiere modificar un registro (update)

            // Buscamos la asignación por id y nos quedamos con la referencia.
            Asignacion asignacion = daoFactory
                    .crearAsignacionDao()
                    .read(id);
            // Llamamos a los set de la clase Asignacion.
            setearCampos(infoAsignacion, asignacion);
            
            if (camposValidos) {
                // Se modifica (persiste) en la BD la asignacion (Update)
                daoFactory.crearAsignacionDao().update(asignacion);
                finalizarOperacion();
            }
        }
    }
    
    @Override
    protected void setearCampos(Info parameterObject, Object entidad) {
        // ...
        InfoAsignacion info = (InfoAsignacion) parameterObject;
        Asignacion asignacion = (Asignacion) entidad;
        
        // Se captura una excepcion de validación de campos
        try { 
            asignacion.withEmpleado((Empleado) info.getEmpleado()) // info aloja una ref. a empleado
                    .withProyecto((Proyecto) info.getProyecto()) // info aloja una ref. a proyecto
                    .withPuesto(info.getPuesto())
                    .withHoras(validarHoras(info.getHoras()))
                    .withPago(validarPago(
                            (Proyecto) info.getProyecto(), 
                            info.getPago(),
                            asignacion.getPago() ));
            
            camposValidos = true;
            
        } catch (IllegalArgumentException ex) {
            
            camposValidos = false;
            // Mostramos un mensaje con el motivo de la excepción.
            vistaHija.mostrarMensaje(ex.getMessage());
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

    private Integer validarPago(Proyecto proyecto, String value, Integer pagoAnterior) 
            throws IllegalArgumentException {

        // Es opcional...
        if (!isNull(value)) {
        
            // Verificamos que sea un número
            checkArgument(isNumeric(value),
                    "El campo horas debe ser un número válido");

            Integer nuevoPago = Integer.parseInt(value);
            
            if (!esModificacion) { // Si la operación es un alta
                // Verificamos que sea suficiente el presupuesto 
                checkArgument(proyecto.presupuestoSuficiente(nuevoPago),
                    "No hay presupuesto disponible para pagarle al empleado");
            }
             
            /** Pero si es una modificación y se está modificando el pago tal que
             * el nuevo pago es mayor que el pago anterior, se debe determinar 
             * si es suficiente el presupuesto calculando la diferencia
             * entre el pago que tenía antes con el nuevo pago por validar
             */ 
            else if (nuevoPago > pagoAnterior) { // Si son distintos.  
                checkArgument(
                        proyecto.presupuestoSuficiente(nuevoPago - pagoAnterior),
                        "No hay presupuesto disponible para pagarle al empleado");
            }
            
            return nuevoPago;
        }
        
        else return null;
    }
//</editor-fold>

}
