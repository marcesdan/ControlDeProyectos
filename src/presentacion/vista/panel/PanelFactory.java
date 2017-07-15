/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package presentacion.vista.panel;

import presentacion.vista.VistaHija;
import presentacion.vista.VistaPadre;

/**
 *
 * @author marces
 */
public class PanelFactory {

    public VistaPadre crearPanelEmpleado() {
        return new PanelEmpleado();
    }
    
    public VistaHija crearPanelNuevoEmpleado() {
        return new PanelEmpleadoNuevo();
    }
    
    public VistaPadre crearPanelProyecto() {
        return new PanelProyecto();
    }

    public VistaHija crearPanelProyectoNuevo() {
        return new PanelProyectoNuevo();
    }

    public VistaPadre crearPanelAsignacion() {
        return new PanelAsignacion();
    }

    public VistaHija crearPanelAsignacionNueva() {
        return new PanelAsignacionNueva();
    }
}
