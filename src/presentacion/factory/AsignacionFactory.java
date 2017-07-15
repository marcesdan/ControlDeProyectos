/**
 *
 * Trabajo Integrador: Control de Proyectos.
 * Programación y Diseño Orientado a Objetos.
 *
 * Alumno: Mariano César D'Angelo .
 *
 */
package presentacion.factory;

import presentacion.controlador.Controlador;
import presentacion.controlador.ControladorHijo;
import presentacion.controlador.ControladorAsignacion;
import presentacion.controlador.ControladorAsignacionNuevo;
import presentacion.vista.panel.PanelAsignacion;
import presentacion.vista.panel.PanelAsignacionNueva;
import presentacion.vista.VistaHija;
import presentacion.vista.VistaPadre;
import presentacion.vista.panel.PanelFactory;

/**
 *
 * @author marces
 */
public class AsignacionFactory implements AbstractFactoryCompleta{
    
    PanelFactory factory = new PanelFactory();
    
    @Override
    public VistaPadre crearVista() {
        return factory.crearPanelAsignacion();
    }

    @Override
    public Controlador crearControlador() {
        return new ControladorAsignacion();
    }

    @Override
    public VistaHija crearVistaHija() {
        return factory.crearPanelAsignacionNueva();
    }

    @Override
    public ControladorHijo crearControladorHijo() {
        return new ControladorAsignacionNuevo();
    }
}