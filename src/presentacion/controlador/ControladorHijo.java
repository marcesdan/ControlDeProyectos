/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.controlador;

import presentacion.vista.Main;
import presentacion.vista.Vista;
import presentacion.vista.VistaHija;
import presentacion.vista.VistaPadre;
import presentacion.vista.info.Info;

/**
 *
 * @author marces
 */
public abstract class ControladorHijo implements Controlador{
    
    protected VistaPadre vistaPadre;
    protected VistaHija vistaHija;
    protected boolean camposValidos;
    protected boolean esModificacion;
    
    @Override
    public void setVista(Vista vista) {
        this.vistaHija = (VistaHija) vista;
    }

    /**
     * Setea una referencia a la vista padre, la cual es necesaria para
     * actualizar el listado luego de un Create o un Update (CRUD).
     * @param vista
     */
    public void setVistaPadre(Vista vista) {
        this.vistaPadre = (VistaPadre) vista;
    }
    
    /**
     * Muestra un mensade de éxito en pantalla, cierra la vista hija 
     * y actualiza el listado en la vista padre.
     */
    protected void finalizarOperacion() {
        // Mostramos un mensaje por pantalla.
        vistaHija.mostrarMensaje("Operación realizada exitosamente");
        // Actualizamos el listado con el nuevo registro o su modificación.
        vistaPadre.actualizarListado();
        // Cerramos la vista hija.
        Main.getInstance().cerrarDialogAux();
    }
    
    /**
     * Invocado por las vistas hijas para guardar (persistir), ya sea un nuevo 
     * registro o una modificación del mismo (operaciones Create y Update (CRUD))
     * @param info objeto con la información del registro a persistir 
     * (Parameter Object Refactoring).
     */
    public abstract void guardarRegistro(Info info);
    
    protected abstract void setearCampos(Info info, Object entidad);
}
