/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.vista.info;

import com.google.common.base.Strings;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Clase utilizada para implementar un "Parameter Object Refactoring".
 * Sirve de intermediaria entre las clases de dominio y las vistas (y los
 * controladores)
 * 
 * @author marces
 */
public abstract class Info {
    
    protected Long id;
     
    public Long getId() {
        return id;
    }
    
    /**
     * Llamado por la vista para convertir un string vacío, correspondiente a un
     * campo de texto de un número, en 0 (el campo es opcional, "nullable").
     * @param str la cadena de texto 
     * @return un string "0" si str está vacío, o str en otro caso. 
     */
    protected String emptyToCero(String str) {
        
        if (isEmpty(str.trim())) 
            return "0";
        
        else return str;
    }
    
    /**
     * Llamado por el controlador para setear la vista en caso de una
     * modificación.
     * 
     * @param num campo opcional perteneciente a un objeto del dominio.
     * @return un string vacío si num == 0, o num.toString() en otro caso. 
     */
    protected String ceroToEmpty(Integer num) {
        
        if (num == 0) 
            return "";
        
        else return num.toString();
    }
}
