/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.vista;

import presentacion.vista.info.Info;

/**
 *
 * @author marces
 */
public interface VistaHija extends Vista {
    
    /**
     * Invocado por los controladores cuando se desea realizar una modificación
     * en particular (Update). Esto permite reutilizar las vistas de altas
     * (Create), llenando los campos de texto con los datos del objeto a
     * modificar (podrían ser varios objetos).
     * 
     * @param campos objeto con la información de los campos de texto a llenar.
     * (Parameter Object Refactoring).
     */
    public void setCamposDeTexto(Info campos);
}
