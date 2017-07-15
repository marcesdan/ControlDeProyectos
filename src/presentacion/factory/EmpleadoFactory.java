/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
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
public class EmpleadoFactory extends AbstractFactory{
    
    @Override
    public VistaPadre crearVista() {
        return panelFactory.crearPanelEmpleado();
    }

    @Override
    public ControladorPadre crearControlador() {
        return controladorFactory.crearControladorEmpleado();
    }

    @Override
    public VistaHija crearVistaHija() {
        return panelFactory.crearPanelNuevoEmpleado();
    }

    @Override
    public ControladorHijo crearControladorHijo() {
        return controladorFactory.crearControladorEmpleadoNuevo();
    }
}