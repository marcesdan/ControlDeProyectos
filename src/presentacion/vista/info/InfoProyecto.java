/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.vista.info;

import java.time.LocalDate;

/**
 * Clase utilizada para implementar un "Parameter Object Refactoring".
 * Sirve de intermediaria entre las clases de dominio y las vistas (y los
 * controladores)
 * 
 * Además, se toma como medida que los atributos del tipo cadena de texto 
 * (varchar) que son opcionales (nullables), se almacenan en la BD como
 * valores nulos, en lugar de cadenas vacias ( "" ). Es por eso que se
 * utiliza el método "emptyToNull" en los métodos "with".
 * 
 * @author marces
 */
public class InfoProyecto extends Info {
    
   
    private String presupuesto, descripcion, contratista;
    private LocalDate fechaInicio, fechaEstimada;
    
    public static InfoProyecto crearInfoProyecto(){
        return new InfoProyecto();
    }

    public String getPresupuesto() {
        return presupuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public String getContratista() {
        return contratista;
    }
    
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    
     public LocalDate getFechaEstimada() {
        return fechaEstimada;
    }
    
    public InfoProyecto withId(Long id) {
        this.id = id;
        return this;
    }

    public InfoProyecto withPresupuesto(String presupuesto) {
        this.presupuesto = presupuesto;
        return this;
    }

    public InfoProyecto withDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }
    
    public InfoProyecto withContratista(String contratista) {
        this.contratista = contratista;
        return this;
    }
    
    public InfoProyecto withFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }
    
     public InfoProyecto withFechaEstimada(LocalDate fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
        return this;
    }
}
