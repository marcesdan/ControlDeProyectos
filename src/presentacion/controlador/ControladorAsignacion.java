/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.controlador;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static presentacion.vista.info.InfoAsignacion.crearInfoAsignacion;
import static presentacion.vista.info.InfoEmpleado.crearInfoEmpleado;
import dao.DaoFactory;
import dominio.Empleado;
import dominio.Asignacion;
import presentacion.vista.Main;
import presentacion.factory.EmpleadoFactory;
import presentacion.modelo.ATableModel;
import dao.AsignacionDao;
import presentacion.factory.AsignacionFactory;
import dao.EmpleadoDao;
import dao.ProyectoDao;

/**
 *
 * @author marces
 */
public class ControladorAsignacion extends ControladorPadre {
    
    private final AsignacionDao asignacionDao;
    private final EmpleadoDao empleadoDao;
    private final ProyectoDao proyectoDao;

    public ControladorAsignacion() {
        DaoFactory factory = new DaoFactory();
        asignacionDao = factory.crearAsignacionDao();
        empleadoDao = factory.crearEmpleadoDao();
        proyectoDao = factory.crearProyectoDao();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Alta">
    
    /** Crea una nueva asignacion. Si el empleado elegido no esta en la BD,
     * se lo registra, y ahí recién se crea la asignacion. */
    @Override
    public void nuevo() {
        
        // Mostramos una entrada de texto para que ingrese el DNI.
        String stDni = Main.getInstance()
                .mostrarDialogInput("Ingrese el documento del empleado");
        
        // Si el DNI es invalido volvemos al panel principal
        if (!validarDni(stDni)) return;
        
        Integer intDni = Integer.parseInt(stDni);
        Empleado empleado = empleadoDao.buscarPorDni(intDni); // Hacemos la busqueda
        
        // Si existia un empleado bajo ese DNI, nos vamos a la venta del asignacion.
        if (!isNull(empleado)) cargarNuevaAsignacion(empleado);
        
        else {  // Si NO existe un empleado bajo ese DNI, creamos el empleado.
            cargar(new EmpleadoFactory());
            
            // Sino el usuario escribiría el DNI dos veces :)
            vistaHija.setCamposDeTexto(crearInfoEmpleado().withDocumento(stDni));
            
            Main.getInstance().mostrarPanelEnDialog(vistaHija, ""
                    + "Nuevo empleado");
            
            // Si realmente se creó el empleado bajo ese DNI
            empleado = empleadoDao.buscarPorDni(intDni);
            if (!isNull(empleado)) cargarNuevaAsignacion(empleado);
        }
    }
    
    private void cargarNuevaAsignacion(Empleado empleado){
        // Se setean las vistas y controladores correspondientes
        cargar(new AsignacionFactory());
        
        // Cargamos los datos del empleado elegido en pantalla
        // (Para que no lo escriba dos veces...)
        vistaHija.setCamposDeTexto(crearInfoAsignacion()
                .withEmpleado(empleado)
        );
        
        Main.getInstance().mostrarPanelEnDialog(vistaHija, ""
                + "Nueva asignación");
    }
    
    private boolean validarDni(String stDni) {
        
        if (isNullOrEmpty(stDni)) return false;
        
        try {
            // Reglas de validación
            checkArgument(isNumeric(stDni),
                    "El campo documento debe ser un número");
            
            Integer intDni = Integer.parseInt(stDni);
            checkArgument(intDni > 1000000 && intDni < 100000000,
                    "Verifique el rango del documento");
            
            return true;
            
        } catch (IllegalArgumentException ex) {
            vistaPadre.mostrarMensaje(ex.getMessage());
            return false;
        }
    }
    
    //</editor-fold>

    @Override
    public void modificar(ATableModel model, int fila) {
        // Si se se seleccionó una fila...
        if (fila != -1) {
            // Mostramos en pantalla el panel para modificar el asignacion
            cargar(new AsignacionFactory());
            // Nos quedamos con el campo id de fila seleccionada en la tabla
            Long id = model.getId(fila);
            // Realizamos la busqueda
            Asignacion asignacion = asignacionDao.read(id);
            vistaHija.setCamposDeTexto(crearInfoAsignacion()
                    .withId(asignacion.getId()) // Tambien disponemos de "id"...
                    .withEmpleado(asignacion.getEmpleado())
                    .withProyecto(asignacion.getProyecto())
                    .withHoras(asignacion.getHoras().toString())
                    .withPago(asignacion.getPago().toString())
                    .withPuesto(asignacion.getPuesto())
                    .withPresupuestoDisponible(asignacion
                            .getProyecto()
                            .getPresupuestoDisponible()
                            .toString())
            );
            
            Main.getInstance().mostrarPanelEnDialog(vistaHija, ""
                    + "Modifición de asignacion");
        }
        
        else vistaPadre.mostrarMensaje(""
                + "Primero debe seleccionar una fila de la tabla");
        
    }
    
    @Override
    public void eliminar(ATableModel model, int fila) {
        
        if (fila != -1) {
            // Si el usuario confirma el borrado...
            if (vistaPadre.confirmacionBorrado()) {
                
                Long id = model.getId(fila); // Obtenemos su id del modelo
                
                // (1) Buscamos la asignación
                Asignacion asignacion = asignacionDao.read(id); 
                // La eliminamos
                asignacionDao.delete(asignacion);
                // Actualizamos las listas
                asignacion.getEmpleado().removeAsignacion(asignacion);
                asignacion.getProyecto().removeAsignacion(asignacion);
                // Y por último actualizamos la base de datos
                empleadoDao.update(asignacion.getEmpleado());
                proyectoDao.update(asignacion.getProyecto());
                
                // Desde (1) hasta acá, debería haber sido una transacción (atómica)
                
                String nombreEmpleado = (String) model.getValueAt(fila, 0);
                vistaPadre.actualizarListado();
                vistaPadre.mostrarMensaje("La asignación de " + nombreEmpleado + ""
                        + " fue borrada exitosamente");
            }
        }
        else vistaPadre.mostrarMensaje(""
                + "Primero debe seleccionar una fila de la tabla");
    }
}