/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package dominio;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author marces
 */

public class LocalDateConverter {
    
    public Date convertToSqlDate(LocalDate attribute) {
        
        if (attribute != null) {
            return Date.valueOf(attribute);
        } else {
            return null;
        }
    }
    
    public LocalDate convertToLocalDate(Date dbData) {
        
        if (dbData != null) {
            return dbData.toLocalDate();
        } else {
            return null;
        }
    }

}
