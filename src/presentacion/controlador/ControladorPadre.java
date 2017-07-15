/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
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
