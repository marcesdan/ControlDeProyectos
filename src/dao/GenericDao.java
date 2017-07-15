/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
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
