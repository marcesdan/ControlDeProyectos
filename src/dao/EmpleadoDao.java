/*
/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package dao;

import dominio.Empleado;
/**
 *
 * @author marces
 */
public interface EmpleadoDao extends GenericDao<Long, Empleado> {
    
    /** Consulta sql que nos devuelve el cliente bajo un DNI
     * @param dni documento del cliente. Es clave candidata (Unique)
     * @return instancia del cliente bajo ese DNI
     */
    public Empleado buscarPorDni(int dni);
}
