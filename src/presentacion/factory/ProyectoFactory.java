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
import presentacion.controlador.ControladorProyecto;
import presentacion.controlador.ControladorHijo;
import presentacion.controlador.ControladorProyectoNuevo;
import presentacion.vista.VistaHija;
import presentacion.vista.VistaPadre;
import presentacion.vista.panel.PanelFactory;

/**
 *
 * @author marces
 */
public class ProyectoFactory implements AbstractFactoryCompleta{
    
    PanelFactory factory = new PanelFactory();
    
    @Override
    public VistaPadre crearVista() {
        return factory.crearPanelProyecto();
    }

    @Override
    public Controlador crearControlador() {
        return new ControladorProyecto();
            
    }

    @Override
    public VistaHija crearVistaHija() {
        return factory.crearPanelProyectoNuevo();
    }

    @Override
    public ControladorHijo crearControladorHijo() {
        return new ControladorProyectoNuevo();
    }
}