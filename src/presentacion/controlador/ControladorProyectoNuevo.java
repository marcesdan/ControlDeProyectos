/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.controlador;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import java.time.LocalDate;
import presentacion.vista.info.InfoProyecto;
import presentacion.vista.info.Info;
import dominio.Proyecto;
import dominio.DominioFactory;
import dao.DaoFactory;
import dao.ProyectoDao;

/**
 *
 * @author marces
 */
public class ControladorProyectoNuevo extends ControladorHijo {

    @Override
    public void guardarRegistro(Info info) {
        
        InfoProyecto infoProyecto = (InfoProyecto) info;
        ProyectoDao proyectoDao = new DaoFactory().crearProyectoDao();
        
        // El id determina si es un alta o una modificación (Create o Update)
        Long id = infoProyecto.getId();
        if (isNull(id)) {
            
            // Nueva instancia de Proyecto
            Proyecto proyecto =  new DominioFactory().crearProyecto();
            // Llamamos a los set de la clase Proyecto
            setearCampos(infoProyecto, proyecto);
            
            if (camposValidos) {
                // Persistimos en la BD el proyecto
                proyectoDao.create(proyecto);
            }

        } else { // De lo contrario se requiere modificar un registro (update)

            // Buscamos el proyecto por id y nos quedamos con la referencia.
            Proyecto proyecto = proyectoDao.read(infoProyecto.getId());
            // Llamamos a los set de la clase Proyecto. 
            setearCampos(infoProyecto, proyecto);
            
            if (camposValidos) {
                // Se modifica (persiste) en la BD el proyecto (Update)
                proyectoDao.update(proyecto);
             }
        }
    }

    @Override
    protected void setearCampos(Info parameterObject, Object entidad){
        
         // ...
        InfoProyecto info = (InfoProyecto) parameterObject;
        Proyecto proyecto = (Proyecto) entidad;
        
        // Se captura una excepcion de validación de campos
        try {
            proyecto.withDescripcion(info.getDescripcion())
                .withContratista(info.getContratista())
                .withFechaInicio(info.getFechaInicio())
                .withFechaEstimada(info.getFechaEstimada())
                .withPresupuesto(validarPresupuesto(info.getPresupuesto()));
        
            // La fecha de inicio debe ser anterior a la fecha estimada.
            validarFecha(info.getFechaInicio(), info.getFechaEstimada());

            camposValidos = true;
        
        } catch(IllegalArgumentException ex) {  
            camposValidos = false;
            vistaHija.mostrarMensaje("Error: "
                        + "el proyecto no pudo ser guardado.\n\n"
                        + ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc=" Validaciones ">
    public Integer validarPresupuesto(String value) throws IllegalArgumentException {
        
        // Debe ser un numero valido, y es obligatorio (not null)
        checkArgument(isNumeric(value),
                "El campo presupuesto debe ser un número válido");

        return Integer.parseInt(value);
    } // Ausencia del atributo


    public void validarFecha(LocalDate inicio, LocalDate estimada) 
            throws IllegalArgumentException {
        
        // Es opcional...
        if (!isNull(estimada)) {
            
            checkArgument(inicio.isBefore(estimada), 
                    "La fecha estimada debe ser posterior a la fecha de inicio");
        }
    }
    // </editor-fold> 
}
