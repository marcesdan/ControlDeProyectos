/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.controlador;

import presentacion.modelo.ATableModel;

/**
 *
 * @author marces
 */
public interface ControladorPadre extends Controlador{
    
    public void nuevo();
    
    public void modificar(ATableModel model, int fila);
    
    public void eliminar(ATableModel model, int fila);
}
