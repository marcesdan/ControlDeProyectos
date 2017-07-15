/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
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
import java.util.LinkedList;
import java.util.List;
import javax.lang.model.type.NullType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author marces
 */
@Entity
public class Empleado implements Serializable {
    
    @Column(unique = true, nullable = false)
    private Integer documento;
    
    @Column(nullable = false)
    private String apellido, nombre;
    
    @Column(nullable = false)
    private Date nacimiento;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Direccion direccion;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Contacto contacto;
    
    @OneToMany(mappedBy = "empleado",cascade = CascadeType.ALL)    
    private final List<Asignacion> asignaciones;
    
    public Empleado() {
        asignaciones = new ArrayList<>();
        direccion = new Direccion();
        contacto = new Contacto();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Responsabilidades">
    public boolean addAsignacion(Asignacion asignacion) {
        
        // comprobamos que la asignación corresponde a nuestro proyecto
        if (asignacion.getEmpleado() == this)
            return asignaciones.add(asignacion);
        
        else return false;
    }
    
    public boolean removeAsignacion(Asignacion asignacion) {
        return asignaciones.remove(asignacion);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters y Setters">
    
    public LocalDate getNacimientoLocalDate() {
        return new LocalDateConverter()
                .convertToLocalDate(nacimiento);
    }

    public void setNacimientoLocalDate(LocalDate nacimiento) {
        this.nacimiento = new LocalDateConverter()
                .convertToSqlDate(nacimiento);
    }
    
    public List<Asignacion> getAsignaciones() {
        return asignaciones;
    }
    
    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public Integer getDocumento() {
        return documento;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }
    
    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto= contacto;
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Withs">
    public Empleado withNombre(String nombre) {
        setNombre(nombre);
        return this;
    }
    
    public Empleado withApellido(String apellido) {
        setApellido(apellido);
        return this;
    }
    
    public Empleado withDocumento(Integer dni) {
        setDocumento(dni);
        return this;
    }

    public Empleado withNacimiento(LocalDate nacimiento) {
        setNacimientoLocalDate(nacimiento);
        return this;
    }
    
    public Empleado withTelefono(String telefono) {
        contacto.setTelefono(telefono);
        return this;
    }
    
    public Empleado withCelular(String celular) {
        contacto.setCelular(celular);
        return this;
    }
    
    public Empleado withEmail(String email) {
        contacto.setEmail(email);
        return this;
    }
    
    public Empleado withCalle(String calle) {
        direccion.setCalle(calle);
        return this;
    }
    
    public Empleado withAltura(String altura) {
        direccion.setAltura(altura);
        return this;
    }
    
    public Empleado withBarrio(String barrio) {
        direccion.setBarrio(barrio);
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
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return apellido + ", " + nombre; 
    }
    
//</editor-fold> 
}
