/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.vista;

import presentacion.controlador.Controlador;

/**
 *
 * @author marces
 */
public interface Vista {
    
    public void setControlador(Controlador controlador);
    
    public void mostrarMensaje(String st);
}
