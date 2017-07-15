/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package dominio;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Como EclipseLink no deja manipular columnas del tipo LocalDate, realizamos
 * conversiones de extremo a extremo (LocalDate a sql.Date y viceversa).
 * 
 * @author marces
 */
public class LocalDateConverter {
    
    /**
     * Convierte un LocalDate a un sql.Date.
     * @param attribute LocalDate a convertir.
     * @return un sql.Date convertido. Si attribute es null, se retorna null.
     */
    public Date convertToSqlDate(LocalDate attribute) {
        
        if (attribute != null) {
            return Date.valueOf(attribute);
        } else {
            return null;
        }
    }
    
    /**
     * Convierte un sql.Date a un LocalDate
     * @param dbData sql.Date a convertir.
     * @return un LocalDate convertido. Si dbData es null, se retorna null.
     */
    public LocalDate convertToLocalDate(Date dbData) {
        
        if (dbData != null) {
            return dbData.toLocalDate();
        } else {
            return null;
        }
    }

}
