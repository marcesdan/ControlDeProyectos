/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package dao;

import dominio.Asignacion;
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
