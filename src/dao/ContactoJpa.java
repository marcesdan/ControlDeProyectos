/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
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