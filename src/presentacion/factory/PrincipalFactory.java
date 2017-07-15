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
import presentacion.controlador.ControladorPrincipal;
import presentacion.vista.FramePrincipal;
import presentacion.vista.Vista;

/**
 *
 * @author marces
 */
public class PrincipalFactory {
    
    public Vista crearVista() {
        return new FramePrincipal();
    }
    
    public Controlador crearControlador() {
        return new ControladorPrincipal();
    }
}