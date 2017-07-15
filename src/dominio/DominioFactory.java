/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package dominio;

/**
 *
 * @author marces
 */
public class DominioFactory {
    
    public Empleado crearEmpleado() {
        return new Empleado();
    }
    
    public Proyecto crearProyecto() {
        return new Proyecto();
    }
    
    public Asignacion crearAsignacion() {
        return new Asignacion();
    }
    
    public Direccion crearDireccion() {
        return new Direccion();
    }
    
    public Contacto crearContacto() {
        return new Contacto();
    }
}
