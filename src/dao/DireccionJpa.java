/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package dao;

import dominio.Direccion;
import java.util.List;

/**
 *
 * @author marces
 */
public class DireccionJpa extends JpaDao<Long, Direccion> 
    implements DireccionDao {
    
    public DireccionJpa() {
        super();
    }

    @Override
    public List<Direccion> getAllOrd() {
        return (List<Direccion>) getEntityManager()
                .createQuery("select s from "+ entityClass.getName() + ""
                        + " s order by s.calle asc, s.numero asc")
                .getResultList();
    }
}