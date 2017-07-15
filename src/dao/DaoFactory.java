/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
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
