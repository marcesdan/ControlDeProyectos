/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package dominio;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;

/**
 *
 * @author marces
 */
@Entity
public class Proyecto implements Serializable {

    @Column(nullable = false)
    private Integer presupuesto;
    
    @Column(nullable = false)
    private String descripcion, contratista;
    
    @Column(nullable = false)
    private Date fechaInicio;
    private Date fechaEstimada;
    
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    private final List<Asignacion> asignaciones; 
    
    public Proyecto() {
        asignaciones = new ArrayList<>();
    }  
    
    //<editor-fold defaultstate="collapsed" desc="Responsabilidades">
    /** 
     * 
     * @param monto costo que deberá afrontar el proyecto
     * @return si hay presupuesto disponible para ese monto
     */
    public boolean presupuestoSuficiente(Integer monto) {
        Integer presupuestoDisponible = getPresupuestoDisponible();
        return presupuesto >= presupuestoDisponible + monto;
    }
    
    public Integer getPresupuestoDisponible() {
        
        Integer aux = 0;
        for (Asignacion asignacion : asignaciones)
            aux += asignacion.getPago();
        
        return presupuesto - aux;
    }
    
    public boolean addAsignacion(Asignacion asignacion) {
        
        // comprobamos que la asignación corresponde a nuestro proyecto
        if (asignacion.getProyecto() == this)
            return asignaciones.add(asignacion);
        
        else return false;
    }
    
    public boolean removeAsignacion(Asignacion asignacion) {
        
        return asignaciones.remove(asignacion);
    }  
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public List<Asignacion> getAsignaciones() {
        return asignaciones;
    }
    
    public LocalDate getFechaInicioLocalDate() {
        return new LocalDateConverter()
                .convertToLocalDate(fechaInicio);
    }
    
    public void setFechaInicioLocalDate(LocalDate fechaInicio) {
        this.fechaInicio = new LocalDateConverter()
                .convertToSqlDate(fechaInicio);
    }
    
    public LocalDate getFechaEstimadaLocalDate() {
        return new LocalDateConverter()
                .convertToLocalDate(fechaEstimada);
    }
    
    public void setFechaEstimadaLocalDate(LocalDate fechaEstimada) {
        this.fechaEstimada = new LocalDateConverter()
                .convertToSqlDate(fechaEstimada);
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaEstimada() {
        return fechaEstimada;
    }

    public void setFechaEstimada(Date fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
    }
    
    
    public Integer getPresupuesto() {
        return presupuesto;
    }
    
    public void setPresupuesto(Integer presupuesto) {
        this.presupuesto = presupuesto;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getContratista() {
        return contratista;
    }
    
    public void setContratista(String contratista) {
        this.contratista = contratista;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Withs">
    public Proyecto withPresupuesto(Integer presupuesto) {
        setPresupuesto(presupuesto);
        return this;
    }
    
    public Proyecto withDescripcion(String descripcion) {
        setDescripcion(descripcion);
        return this;
    }
    
    
    public Proyecto withContratista(String contratista) {
        setContratista(contratista);
        return this;
    }
    
    public Proyecto withFechaInicio(LocalDate fechaInicio) {
        setFechaInicioLocalDate(fechaInicio);
        return this;
    }
    
    public Proyecto withFechaEstimada(LocalDate fechaEstimada) {
        setFechaEstimadaLocalDate(fechaEstimada);
        return this;
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Código generado">
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return descripcion + ", " + contratista; 
    }
    
//</editor-fold> 
}
