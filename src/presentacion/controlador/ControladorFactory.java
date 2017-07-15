/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package presentacion.controlador;

/**
 *
 * @author marces
 */
public class ControladorFactory {
    
    public ControladorPadre crearControladorEmpleado() {
        return new ControladorEmpleado();
    }
    
    public ControladorPadre crearControladorProyecto() {
        return new ControladorProyecto();
    }
    
    public ControladorPadre crearControladorAsignacion() {
        return new ControladorAsignacion();
    }
    
    public ControladorHijo crearControladorEmpleadoNuevo() {
        return new ControladorEmpleadoNuevo();
    }
    
    public ControladorHijo crearControladorProyectoNuevo() {
        return new ControladorProyectoNuevo();
    }
    
    public ControladorHijo crearControladorAsignacionNueva() {
        return new ControladorAsignacionNueva();
    }
}
