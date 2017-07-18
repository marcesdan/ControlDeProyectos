/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.vista.info;

/**
 * Clase utilizada para implementar un "Parameter Object Refactoring".
 * Sirve de intermediaria entre las clases de dominio y las vistas (y los
 * controladores)
 * 
 * @author marces
 */
public abstract class Info {
    
     protected Long id;
     
     public Long getId() {
        return id;
    }
}
