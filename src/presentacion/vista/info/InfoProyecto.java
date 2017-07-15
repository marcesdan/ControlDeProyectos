/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package presentacion.vista.info;

import java.time.LocalDate;

/**
 *
 * @author marces
 */
public class InfoProyecto extends Info {
    
    private Long id;
    private String presupuesto, descripcion, contratista;
    private LocalDate fechaInicio, fechaEstimada;
    
    public static InfoProyecto crearInfoProyecto(){
        return new InfoProyecto();
    }
    
    public Long getId() {
        return id;
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
