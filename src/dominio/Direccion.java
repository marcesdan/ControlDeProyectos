/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package dominio;

import static java.util.Objects.isNull;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author marces
 */
@Entity
public class Direccion implements Serializable {

    private String calle, altura, barrio;

    //<editor-fold defaultstate="collapsed" desc="Getters and Seters">
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Withs">
    public Direccion withCalle(String calle) {
        setCalle(calle);
        return this;
    }

    public Direccion withAltura(String altura) {
        setAltura(altura);
        return this;
    }

    public Direccion withBarrio(String barrio) {
        setBarrio(barrio);
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

        if (!isNull(calle)) {
            aux = aux + calle;
        }

        if (!isNull(altura)) {
            aux = aux + " " + altura;
        }

        if (!isNull(barrio)) {
            aux = aux + ", " + barrio;
        }

        return aux;
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
        if (!(object instanceof Direccion)) {
            return false;
        }
        Direccion other = (Direccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    //</editor-fold>
}
