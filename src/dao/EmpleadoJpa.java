/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package dao;

import dominio.Empleado;
import java.util.List;

/**
 *
 * @author marces
 */
public class EmpleadoJpa extends JpaDao<Long, Empleado>
        implements EmpleadoDao {

    public EmpleadoJpa() {
        super();
    }

    @Override
    public List<Empleado> getAllOrd() {
        
        return (List<Empleado>) getEntityManager()
                .createQuery("select s from "+ entityClass.getName() + ""
                        + " s order by s.apellido asc, s.nombre asc")
                .getResultList(); 
    }

    @Override
    public Empleado buscarPorDni(int documento) {
        
        List<Empleado> result = getEntityManager()
                .createQuery("SELECT t FROM "+ entityClass.getName()+" t where t.documento = :d")
                .setParameter("d", documento)
                .getResultList();
          
        if (result.isEmpty()) return null;
        
        else return result.get(0);
    }
}
