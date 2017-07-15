/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author marces
 * @param <K> clave primaria de la entidad.
 * @param <E> entidad.
 */
public abstract class JpaDao<K, E> implements GenericDao<K, E> {

    protected Class<E> entityClass;

    public JpaDao() {
        
        ParameterizedType genericSuperclass
                = (ParameterizedType) getClass()
                        .getGenericSuperclass();

        this.entityClass = (Class<E>) genericSuperclass
                .getActualTypeArguments()[1];
    }

    protected EntityManager getEntityManager() {
        
        EntityManagerFactory emf 
                = Persistence.createEntityManagerFactory("ProyectosPU");
        
        return emf.createEntityManager();
    }

    @Override
    public E create(E entity) {

        EntityManager em = getEntityManager();
        em.getTransaction().begin();

        try {
            em.persist(entity);
            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
        }

        em.close();
        return entity;
    }

    @Override
    public E read(K id) {
        return (E) getEntityManager().find(entityClass, id);
    }
    
    @Override
    public E update(E entity) {

        EntityManager em = getEntityManager();
        em.getTransaction().begin();

        try {
            em.merge(entity);
            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
        }

        em.close();
        return entity;
    }

    @Override
    public void delete(E entity) {
        
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        
        try {   
            entity = em.merge(entity);
            em.remove(entity);
            
            em.getTransaction().commit();      

        } catch (Exception e) {

            em.getTransaction().rollback();
        }

        em.close();
    }
    
    /**
     *
     * @return cantidad de filas de la tabla
     */
    @Override
    public Long getCount() {   
        return (Long) getEntityManager()
                .createQuery("select count(s) from "
                        + entityClass.getName() + " s").getSingleResult();
    }
    
    /**
     *
     * @return lista de todas las filas de la tabla
     */
    @Override
    public List<E> getAll(){
        
        Query consulta = getEntityManager().createQuery("select s from " 
                + entityClass.getName() + " s");
        
        return (List<E>) consulta.getResultList();
    }
    
    /**
     * 
     * @return lista de todas las filas de la tabla ordenadas
     * según un criterio.
     */
    @Override
    public abstract List<E> getAllOrd();
}
