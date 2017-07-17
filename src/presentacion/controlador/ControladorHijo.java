/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.controlador;

import presentacion.vista.Vista;
import presentacion.vista.info.Info;

/**
 *
 * @author marces
 */
public interface ControladorHijo extends Controlador{
    
    /**
     * Setea una referencia a la vista padre, la cual es necesaria para
     * actualizar el listado, luego de un Create o un Update (CRUD).
     * @param vista
     */
    public void setVistaPadre(Vista vista);
    
    /**
     * Invocado por las vistas hijas para guardar (persistir), ya sea un nuevo 
     * registro o una modificación del mismo (operaciones Create y Update (CRUD))
     * @param info objeto con la información del registro a persistir 
     * (Parameter Object Refactoring).
     */
    public void guardarRegistro(Info info);
}
