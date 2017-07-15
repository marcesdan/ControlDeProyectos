/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
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
