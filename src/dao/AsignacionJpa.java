/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package dao;

import dominio.Asignacion;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author marces
 */
public class AsignacionJpa
        extends JpaDao<Long, Asignacion>
        implements AsignacionDao {

    public AsignacionJpa() {
        super();
    }

    @Override
    public List<Asignacion> getAllOrd() {
        
       return (List<Asignacion>) getEntityManager()
                .createQuery("select t from "+ entityClass.getName() + ""
                        + " t order by t.fechaIngreso asc")
                .getResultList();
    }
}
