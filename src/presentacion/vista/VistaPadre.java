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

/**
 *
 * @author marces
 */
public interface VistaPadre extends Vista{
    
    /**
     * Método invocado por un controlador con el fin de actualizar el listado
     * de registros (más precisamente la fila de la tabla afectada) luego de 
     * finalizar una operación (alta, baja o modificación).
     */
    public void actualizarListado();
    
    /**
     * Muestra al usuario una ventana de confirmación de borrado (si o no).
     * @return la decisión del usuario (si borrar el registro o no)
     */
    public default boolean confirmacionBorrado() {  
        
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(new JFrame(), ""
                + "¿Seguro desea borrar el ítem seleccionado?", "Confirmación", dialogButton);
        
        return dialogResult == 0;
    }
}
