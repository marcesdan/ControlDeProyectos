/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.controlador;

import presentacion.factory.AbstractFactory;
import presentacion.modelo.ATableModel;
import presentacion.vista.Vista;
import presentacion.vista.VistaHija;
import presentacion.vista.VistaPadre;

/**
 *
 * @author marces
 */
public abstract class ControladorPadre implements Controlador {
    
    protected VistaPadre vistaPadre;
    protected VistaHija vistaHija;
    protected ControladorHijo controladorHijo;
    
    @Override
    public void setVista(Vista vista) {
        this.vistaPadre = (VistaPadre) vista;
    }
    
     /** Se setean las vistas y los controladores mutuamente.
      * @param factory 
      */
    protected void cargar(AbstractFactory factory) {
        controladorHijo = factory.crearControladorHijo();
        vistaHija = factory.crearVistaHija();
        controladorHijo.setVista(vistaHija);
        controladorHijo.setVistaPadre(vistaPadre);
        vistaHija.setControlador(controladorHijo);
    }
    
    /**
     * Invocado por una vista padre para crear un nuevo registro. Este método 
     * finaliza presentando su vista hija (aunque pueden ser varias), la cual 
     * permitira ingresar por teclado los datos correspondientes.
     * 
     * Da soporte a la
     */
    public abstract void nuevo();
    
    /**
     * Invocado por una vista padre para modificar el registro correspondiente
     * a la fila seleccionada por el usuario en la tabla. Este método finaliza
     * presentando su vista hija (aunque pueden ser varias), la cual permitira
     * ingresar por teclado los datos correspondientes.
     * 
     * @param model el modelo de la tabla
     * @param fila la fila seleccionada por el usuario (el registro a modificar)
     */
    public abstract void modificar(ATableModel model, int fila);
    
    /**
     * Invocado por una vista padre para eliminar el registro correspondiente
     * a la fila seleccionada por el usuario en la tabla.
     * @param model el modelo de la tabla
     * @param fila la fila seleccionada por el usuario (el registro a eliminar)
     */
    public abstract void eliminar(ATableModel model, int fila);
}
