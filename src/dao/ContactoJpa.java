/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package dao;

import dominio.Contacto;
import java.util.List;

/**
 *
 * @author marces
 */
public class ContactoJpa extends JpaDao<Long, Contacto> 
    implements ContactoDao {
    
    public ContactoJpa() {
        super();
    }

    @Override
    public List<Contacto> getAllOrd() {
        return (List<Contacto>) getEntityManager()
                .createQuery("select s from "+ entityClass.getName() + ""
                        + " s order by s.calle asc, s.numero asc")
                .getResultList();
    }
}