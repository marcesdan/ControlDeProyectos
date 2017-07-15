/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package dominio;

import static java.util.Objects.isNull;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author marces
 */
@Entity
public class Contacto implements Serializable {

    @Column(nullable = false)
    private String celular, email;

    private String telefono;
    
    //<editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefonoFijo) {
        this.telefono = telefonoFijo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Withs">
    public Contacto withTelefono(String telefonoFijo) {
        this.telefono = telefonoFijo;
        return this;
    }

    public Contacto withCelular(String celular) {
        this.celular = celular;
        return this;
    }

    public Contacto withEmail(String email) {
        this.email = email;
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

        String aux = "";
        
        // Es opcional
        if (!isNull(telefono)) {
            aux = telefono + " - ";
        }
        
        return aux + celular + " - " + email;
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
        if (!(object instanceof Contacto)) {
            return false;
        }
        Contacto other = (Contacto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    //</editor-fold>
}
