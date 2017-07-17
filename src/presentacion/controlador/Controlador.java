/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.controlador;

import presentacion.vista.Vista;

/**
 *
 * @author marces
 */
public interface Controlador {

    /**
     * Todo controlador (tanto padre como hijo) tiene asociada una vista.
     * (y viceversa).
     * 
     * @param vista la vista asignada al controlador
     */
    public void setVista(Vista vista);
}
