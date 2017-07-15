/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package presentacion.factory;

import presentacion.controlador.Controlador;
import presentacion.controlador.ControladorEmpleado;
import presentacion.controlador.ControladorHijo;
import presentacion.controlador.ControladorEmpleadoNuevo;
import presentacion.vista.VistaHija;
import presentacion.vista.VistaPadre;
import presentacion.vista.panel.PanelFactory;

/**
 *
 * @author marces
 */
public class EmpleadoFactory implements AbstractFactoryCompleta{
    
    PanelFactory factory = new PanelFactory();
    
    @Override
    public VistaPadre crearVista() {
        return factory.crearPanelEmpleado();
    }

    @Override
    public Controlador crearControlador() {
        return new ControladorEmpleado();
    }

    @Override
    public VistaHija crearVistaHija() {
        return factory.crearPanelNuevoEmpleado();
    }

    @Override
    public ControladorHijo crearControladorHijo() {
        return new ControladorEmpleadoNuevo();
    }
}