/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package presentacion.controlador;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static com.google.common.base.Preconditions.checkArgument;
import dominio.Empleado;
import dominio.Direccion;
import dominio.DominioFactory;
import dao.DaoFactory;
import presentacion.vista.info.InfoEmpleado;
import presentacion.vista.Main;
import presentacion.vista.Vista;
import presentacion.vista.VistaHija;
import presentacion.vista.VistaPadre;
import dao.EmpleadoDao;

/**
 *
 * @author marces
 */
public class ControladorEmpleadoNuevo implements ControladorHijo {

    private VistaPadre vistaEmpleado;
    private VistaHija vistaNuevoEmpleado;
    private EmpleadoDao empleadoDao;
    private Long id;

    public ControladorEmpleadoNuevo() {
        DaoFactory factory = new DaoFactory();
        empleadoDao = factory.crearEmpleadoDao();
    }

    @Override
    public void setVista(Vista vista) {
        this.vistaNuevoEmpleado = (VistaHija) vista;
    }

    @Override
    public void setVistaPadre(Vista vista) {
        this.vistaEmpleado = (VistaPadre) vista;
    }

    public void guardarEmpleado(InfoEmpleado infoEmpleado) {

        try {
            
            id = infoEmpleado.getId();
            if (isNull(id)) {
                
                Empleado empleado = new DominioFactory().crearEmpleado();
                setearDatos(infoEmpleado, empleado);   
                
                empleadoDao.create(empleado);
                
            } else {
                
                Empleado empleado = empleadoDao.read(infoEmpleado.getId());
                setearDatos(infoEmpleado, empleado);
                
                empleadoDao.update(empleado);
            }

            vistaNuevoEmpleado.mostrarMensaje("Empleado guardado exitosamente");
            vistaEmpleado.actualizar();
            Main.getInstance().cerrarDialogAux();        

        } catch (IllegalArgumentException ex) {

            vistaNuevoEmpleado.mostrarMensaje("Error: "
                    + "el empleado no pudo ser guardado.\n\n"
                    + ex.getMessage());
        }
    }

    private void setearDatos(InfoEmpleado info, Empleado empleado)
            throws IllegalArgumentException {

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
    }

    // <editor-fold defaultstate="collapsed" desc=" Validaciones ">
    public String validarNombres(String value) throws IllegalArgumentException {

        // Reglas de validación
        checkArgument(value.matches("^[\\p{L} .'-]+$"),
                "Verifique que el nombre y el apellido sean correctos");
        
        return value;
    }

    public Integer validarDocumento(String value) throws IllegalArgumentException {

        // Reglas de validación
        checkArgument(isNumeric(value),
                "El campo documento debe ser un número");
        
        Integer aux = Integer.parseInt(value);
        checkArgument(aux > 1000000 && aux < 100000000, 
                "Verifique el rango del documento");

        // Si es un create
        if (isNull(id))
            checkArgument(isNull(empleadoDao.buscarPorDni(aux)),
                    "Ya existe un empleado con ese documento");
        
        /* Pero si es un update hay que comprobar además el id debido a la
        * insertidumbre de desconocer si el campo dni fue o no modificado. */
        else {
            Empleado empleado = empleadoDao.buscarPorDni(aux);
            
            /* Si es null, entonces lo modifico por uno que no existe, pero si 
             * no es null y los IDs son distintos, el usuario modificó el 
             * documento por uno que ya existe en la BD. (short-circuit)*/
            checkArgument(isNull(empleado) || empleado.getId() == id,
                    "Ya existe un empleado con ese documento");
        }

        return aux;
    }
    // </editor-fold> 
}
