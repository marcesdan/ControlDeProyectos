/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.controlador;

import presentacion.factory.EmpleadoFactory;
import presentacion.factory.ProyectoFactory;
import presentacion.vista.Main;
import presentacion.vista.Vista;
import presentacion.vista.VistaPadre;
import presentacion.factory.AsignacionFactory;
import presentacion.factory.AbstractFactory;

/**
 * Clase encargada de controlar la vista principal donde se encuentra la barra
 * de menú que llamará a las vistas padre.
 *
 * @author marces
 */
public class ControladorPrincipal implements Controlador {

    private AbstractFactory factory;
    private Vista vistaPrincipal;
    private VistaPadre vistaPadre;
    private Controlador controlador;

    @Override
    public void setVista(Vista vista) {
        this.vistaPrincipal = vista;
    }

    /**
     * Se muestra la vista de los clientes y se le asigna su controlador
     */
    public void mostrarEmpleados() {
        factory = new EmpleadoFactory();
        cargar(factory);
    }

    /**
     * Se muestra la vista de las funciones y se le asigna su controlador
     */
    public void mostrarProyectos() {
        factory = new ProyectoFactory();
        cargar(factory);
    }

    /**
     * Se muestra la vista de los tickets y se le asigna su controlador
     */
    public void mostrarAsignaciones() {  
        factory = new AsignacionFactory();
        cargar(factory);
    }

    /** 
     * Se setean las vistas y los controladores mutuamente. 
     */
    private void cargar(AbstractFactory factory) {
        controlador = factory.crearControlador();
        vistaPadre = factory.crearVista();
        controlador.setVista(vistaPadre);
        vistaPadre.setControlador(controlador);
        Main.getInstance().mostrarPanelEnFrame(vistaPadre);
    }
}
