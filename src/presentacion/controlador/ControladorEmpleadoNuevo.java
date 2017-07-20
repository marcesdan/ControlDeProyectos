/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.controlador;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static com.google.common.base.Preconditions.checkArgument;
import dominio.Empleado;
import dominio.DominioFactory;
import dao.DaoFactory;
import dao.EmpleadoDao;
import presentacion.vista.info.InfoEmpleado;
import presentacion.vista.info.Info;

/**
 *
 * @author marces
 */
public class ControladorEmpleadoNuevo extends ControladorHijo {
    
    private final EmpleadoDao empleadoDao;
    private Long id;

    public ControladorEmpleadoNuevo() {
        empleadoDao = new DaoFactory().crearEmpleadoDao();
    }

    @Override
    public void guardarRegistro(Info info) {

        InfoEmpleado infoEmpleado = (InfoEmpleado) info;

        // El id determina si es un alta o una modificación (Create o Update)
        id = infoEmpleado.getId();
        esModificacion = !isNull(id);
        if (!esModificacion) { // Entonces se requiere crear un registro (Create)

            // Nueva instancia de Empleado
            Empleado empleado = new DominioFactory().crearEmpleado();
            // Llamamos a los set de la clase Empleado. 
            setearCampos(infoEmpleado, empleado);
            
            if (camposValidos) {
                // Persistimos en la BD el empleado.
                empleadoDao.create(empleado);
                finalizarOperacion();
            }
            
        } else { // De lo contrario se requiere modificar un registro (update)

            // Buscamos la el empleado por id y nos quedamos con la referencia.
            Empleado empleado = empleadoDao.read(id);
            // Llamamos a los set de la clase Empleado. 
            setearCampos(infoEmpleado, empleado);
            
            if (camposValidos) {
                // Se modifica (persiste) en la BD el empleado (Update)
                empleadoDao.update(empleado);
                finalizarOperacion();
            }
        }
    }

    @Override
    protected void setearCampos(Info parameterObject, Object entidad) {
        // ...
        InfoEmpleado info = (InfoEmpleado) parameterObject;
        Empleado empleado = (Empleado) entidad;
        
        // Se captura una excepcion de validación de campos
        try {
            empleado.withApellido(validarNombres(info.getApellido()))
                    .withNombre(validarNombres(info.getNombre()))
                    .withDocumento(validarDocumento(info.getDocumento()))
                    .withNacimiento(info.getNacimiento())
                    .withCalle(info.getCalle())
                    .withAltura(info.getAltura())
                    .withBarrio(info.getBarrio())
                    .withTelefono(info.getTelefono())
                    .withCelular(info.getCelular())
                    .withEmail(info.getEmail());
            
            camposValidos = true;

        } catch (IllegalArgumentException ex) {
            
            camposValidos = false;
            // Mostramos un mensaje con el motivo de la excepción.
            vistaHija.mostrarMensaje("Error: "
                    + "el empleado no pudo ser guardado.\n\n"
                    + ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc=" Validaciones ">
    public String validarNombres(String value)
            throws IllegalArgumentException {

        // Reglas de validación
        checkArgument(value.matches("^[\\p{L} .'-]+$"),
                "Verifique que el nombre y el apellido sean correctos");

        return value;
    }

    public Integer validarDocumento(String value)
            throws IllegalArgumentException {

        // Reglas de validación
        checkArgument(isNumeric(value),
                "El campo documento debe ser un número");

        Integer aux = Integer.parseInt(value);
        checkArgument(aux > 1000000 && aux < 100000000,
                "Verifique el rango del documento");

        // Si es un create
        if (!esModificacion) {
            checkArgument(isNull(empleadoDao.buscarPorDni(aux)),
                    "Ya existe un empleado con ese documento");
        }
        
        /** Pero si es un update hay que comprobar además el id debido a que
         *  se desconoce si el campo documento fue o no modificado.
         */  
        else {
            Empleado empleado = empleadoDao.buscarPorDni(aux);
            /* Si es null, entonces lo modifico por uno que no existe, pero si 
             * no es null y los IDs son distintos, el usuario modificó el 
             * documento por uno que ya existe en la BD. (short-circuit)*/
            checkArgument(isNull(empleado) || id.equals(empleado.getId()),
                    "Ya existe un empleado con ese documento");
        }

        return aux;
    }
    // </editor-fold> 
}
