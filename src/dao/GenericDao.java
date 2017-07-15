/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package dao;

import java.util.List;

/**
 *
 * @author marces
 * @param <K> Clave primaria.
 * @param <E> Entidad.
 */
public interface GenericDao<K, E> {

    public E create(E entity);

    public E read(K id);

    public E update(E entity);

    public void delete(E entity);
    
    /** Nos devuelve la cantidad de filas de una tabla
     * @return la cantidad de filas de una tabla (puede ser cero).
     */
    public Long getCount();
    
    /** Nos devuelve todas las filas
     * @return una lista genérica para la entidad en cuestion.
     */
    public List<E> getAll();
    
    /** Nos devuelve todas las filas ordenadas según un criterio de la entidad.
     * @return una lista ordenada genérica para la entidad en cuestion.
     */
    public List<E> getAllOrd();
}
