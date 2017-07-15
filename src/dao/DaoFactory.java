/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package dao;

/**
 *
 * @author marces
 */
public class DaoFactory {


    public ProyectoDao crearProyectoDao() {
        return new ProyectoJpa();
    }

    public DireccionDao crearDireccionDao() {
        return new DireccionJpa();
    }
    
    public ContactoDao crearContactoDao() {
        return new ContactoJpa();
    }
    
    public EmpleadoDao crearEmpleadoDao() {
        return new EmpleadoJpa();
    }

    public AsignacionDao crearAsignacionDao() {
        return new AsignacionJpa();
    }
}
