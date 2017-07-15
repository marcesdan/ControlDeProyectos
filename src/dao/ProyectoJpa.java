/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package dao;

import dominio.Proyecto;
import java.util.List;

/**
 *
 * @author marces
 */
public class ProyectoJpa extends JpaDao<Long, Proyecto> 
    implements ProyectoDao {
    
    public ProyectoJpa() {
        super();
    }
  
    @Override
    public List<Proyecto> getAllOrd() {
        
        return (List<Proyecto>) getEntityManager()
                .createQuery("select s from "+ entityClass.getName() + ""
                        + " s order by s.fechaInicio asc")
                .getResultList();
    }
}
