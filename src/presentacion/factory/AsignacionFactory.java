/**
 *
 * Trabajo Integrador: Control de Proyectos.
 * Programación y Diseño Orientado a Objetos.
 *
 * Alumno: Mariano César D'Angelo .
 *
 */
package presentacion.factory;

import presentacion.controlador.ControladorHijo;
import presentacion.controlador.ControladorPadre;
import presentacion.vista.VistaHija;
import presentacion.vista.VistaPadre;

/**
 *
 * @author marces
 */
public class AsignacionFactory extends AbstractFactory{
    
    @Override
    public VistaPadre crearVista() {
        return panelFactory.crearPanelAsignacion();
    }

    @Override
    public ControladorPadre crearControlador() {
        return controladorFactory.crearControladorAsignacion();
    }

    @Override
    public VistaHija crearVistaHija() {
        return panelFactory.crearPanelAsignacionNueva();
    }

    @Override
    public ControladorHijo crearControladorHijo() {
        return controladorFactory.crearControladorAsignacionNueva();
    }
}