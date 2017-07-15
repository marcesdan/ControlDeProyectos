/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package presentacion.controlador;

import presentacion.vista.Vista;

/**
 *
 * @author marces
 */
public interface ControladorHijo extends Controlador {

    public void setVistaPadre(Vista vista);
}
