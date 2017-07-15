/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
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
