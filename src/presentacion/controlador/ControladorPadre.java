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
    
    /**
     * Invocado por una vista padre para crear un nuevo registro. Este método 
     * finaliza presentando su vista hija (aunque pueden ser varias), la cual 
     * permitira ingresar por teclado los datos correspondientes.
     * 
     * Da soporte a la
     */
    public void nuevo();
    
    /**
     * Invocado por una vista padre para modificar el registro correspondiente
     * a la fila seleccionada por el usuario en la tabla. Este método finaliza
     * presentando su vista hija (aunque pueden ser varias), la cual permitira
     * ingresar por teclado los datos correspondientes.
     * 
     * @param model el modelo de la tabla
     * @param fila la fila seleccionada por el usuario (el registro a modificar)
     */
    public void modificar(ATableModel model, int fila);
    
    /**
     * Invocado por una vista padre para eliminar el registro correspondiente
     * a la fila seleccionada por el usuario en la tabla.
     * @param model el modelo de la tabla
     * @param fila la fila seleccionada por el usuario (el registro a eliminar)
     */
    public void eliminar(ATableModel model, int fila);
}
