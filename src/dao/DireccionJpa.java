/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
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