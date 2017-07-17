/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
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
        /*  Ordena por la fecha de inicio del proyecto. 
            Los más antigüos van primero */
        
        return (List<Proyecto>) getEntityManager()
                .createQuery("select s from "+ entityClass.getName() + ""
                        + " s order by s.fechaInicio asc")
                .getResultList();
    }
}
