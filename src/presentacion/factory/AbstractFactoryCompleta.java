/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package presentacion.factory;

import presentacion.controlador.Controlador;
import presentacion.controlador.ControladorHijo;
import presentacion.vista.VistaHija;
import presentacion.vista.VistaPadre;

/**
 *
 * @author marces
 */
public interface AbstractFactoryCompleta {
    
    public VistaPadre crearVista();
    public Controlador crearControlador();
    public VistaHija crearVistaHija();   
    public ControladorHijo crearControladorHijo();
    
}
