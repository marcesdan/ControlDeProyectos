/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import presentacion.controlador.Controlador;

/**
 *
 * @author marces
 */
public interface Vista {
    
    /**
     * Toda vista (tanto padre como hija) tiene asociada un controlador.
     * (y viceversa).
     * 
     * @param controlador el controlador asignado a la vista.
     */
    public void setControlador(Controlador controlador);
    
    /**
     * Método invocado por el controlador para mostrar al usuario un mensaje 
     * breve en pantalla. Como un mensaje de éxito de operación; o uno de error, 
     * en el caso de ingresos de datos inválidos o que violan alguna restriccion.
     * 
     * @param st el texto que se mostrara en pantalla
     */
    public default void mostrarMensaje(String st){
        
        JOptionPane.showMessageDialog(new JFrame(), st);
    }
}
