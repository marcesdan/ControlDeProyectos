/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.controlador;

/**
 *
 * @author marces
 */
public class ControladorFactory {
    
    public ControladorPrincipal crearControladorPrincipal() {
        return new ControladorPrincipal();
    }
    
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
