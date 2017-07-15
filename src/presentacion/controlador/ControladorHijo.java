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
    
    public void setVistaPadre(Vista vista);
    
    public void crearNuevoRegistro(Info info);
}
