/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
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
