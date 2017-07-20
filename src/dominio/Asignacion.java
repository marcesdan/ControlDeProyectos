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
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author marces
 */

@Entity

//Un mismo empleado no podrá estar asignado más de una vez al mismo proyeto.
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"EMPLEADO_ID" , "PROYECTO_ID"})
})

public class Asignacion implements Serializable {

    @ManyToOne
    @JoinColumn(name = "EMPLEADO_ID", nullable = false)
    private Empleado empleado;
    
    @ManyToOne
    @JoinColumn(name = "PROYECTO_ID", nullable = false)
    private Proyecto proyecto;
    
    @Column(nullable = false)
    private String puesto;
    
    @Column(nullable = false)
    private Date fechaIngreso;
    
    private Integer pago, horas;
    
    public Asignacion() {
        pago = 0;
        horas = 0;
        
        // Convierte LocalDate.now() en un sql.Date
        fechaIngreso = 
                new LocalDateConverter().convertToSqlDate(LocalDate.now());
    }
    //<editor-fold defaultstate="collapsed" desc="Getters y Setters">
    
    public LocalDate getFechaIngresoLocalDate() {
        return new LocalDateConverter().convertToLocalDate(fechaIngreso);
    }

    public void setFechaIngresoLocalDate(LocalDate fechaIngreso) {
        this.fechaIngreso = new LocalDateConverter()
                .convertToSqlDate(fechaIngreso);
    }
    
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Integer getPago() {
        return pago;
    }

    public void setPago(Integer pago) {
        this.pago = pago;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Withs">
    public Asignacion withEmpleado(Empleado empleado) {
        setEmpleado(empleado);
        return this;
    }
    
    public Asignacion withProyecto(Proyecto proyecto) {
        setProyecto(proyecto);
        return this;
    }
    
    public Asignacion withPago(Integer pago) {
        setPago(pago);
        return this;
    }
    
    public Asignacion withHoras(Integer horas) {
        setHoras(horas);
        return this;
    }
    
    public Asignacion withPuesto(String puesto) {
        setPuesto(puesto);
        return this;
    }
    
    public Asignacion withFechaIngreso(LocalDate fechaIngreso) {
        setFechaIngresoLocalDate(fechaIngreso);
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
    public String toString() {
        return empleado+ ", " + proyecto;
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
        if (!(object instanceof Asignacion)) {
            return false;
        }
        Asignacion other = (Asignacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    //</editor-fold>
}
