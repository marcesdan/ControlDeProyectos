/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package presentacion.controlador;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static presentacion.vista.info.InfoAsignacion.crearInfoAsignacion;
import dao.DaoFactory;
import dominio.Empleado;
import dominio.Proyecto;
import dominio.Asignacion;
import presentacion.vista.Main;
import presentacion.vista.Vista;
import presentacion.vista.VistaHija;
import presentacion.vista.VistaPadre;
import presentacion.factory.AbstractFactoryCompleta;
import presentacion.factory.EmpleadoFactory;
import presentacion.modelo.ATableModel;
import static presentacion.vista.info.InfoEmpleado.crearInfoEmpleado;
import dao.AsignacionDao;
import presentacion.factory.AsignacionFactory;
import dao.EmpleadoDao;

/**
 *
 * @author marces
 */
public class ControladorAsignacion implements Controlador {

    private VistaPadre vistaPadre;
    private VistaHija vistaHija;
    private ControladorHijo controladorHijo;
    private final AsignacionDao asignacionDao;
    private final EmpleadoDao empleadoDao;

    public ControladorAsignacion() {
        DaoFactory factory = new DaoFactory();
        asignacionDao = factory.crearAsignacionDao();
        empleadoDao = factory.crearEmpleadoDao();
    }
    
    @Override
    public void setVista(Vista vista) {
        this.vistaPadre = (VistaPadre) vista;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Alta">
    /** Crea una nueva asignacion. Si el empleado elegido no esta en la BD,
     * se lo registra, y ahí recién se crea la asignacion. */
    public void nuevaAsignacion() {
        
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
            vistaHija.setDatos(crearInfoEmpleado().withDocumento(stDni));
            
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
        vistaHija.setDatos(crearInfoAsignacion()
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
    
    //<editor-fold defaultstate="collapsed" desc="Modificación">
    public void modificarAsignacion(ATableModel model, int fila) {
        // Si se se seleccionó una fila...
        if (fila != -1) {
            
            // Mostramos en pantalla el panel para modificar el asignacion
            cargar(new AsignacionFactory());
            
            // Nos quedamos con el campo id de fila seleccionada en la tabla
            Long id = model.getId(fila);
            
            // Realizamos la busqueda
            Asignacion asignacion = asignacionDao.read(id);
            vistaHija.setDatos(crearInfoAsignacion()
                    .withId(asignacion.getId()) // Tambien disponemos de "id"...
                    .withEmpleado(asignacion.getEmpleado())
                    .withProyecto(asignacion.getProyecto())
                    .withHoras(asignacion.getHoras().toString())
                    .withPago(asignacion.getPago().toString())
                    .withPuesto(asignacion.getPuesto())
            );
            
            Main.getInstance().mostrarPanelEnDialog(vistaHija, ""
                    + "Modifición de asignacion");
        }
        
        else vistaPadre.mostrarMensaje(""
                + "Primero debe seleccionar una fila de la tabla");
        
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Baja">
    public void eliminarAsignacion(ATableModel model, int fila) {
        
        if (fila != -1) {
            // Si el usuario confirma el borrado...
            if (vistaPadre.confirmacionBorrado()) {
                
                Long id = model.getId(fila); // Obtenemos su id del modelo
                String nombreEmpleado = (String) model.getValueAt(fila, 0); // y su DNI
                asignacionDao.delete(asignacionDao.read(id)); // Leemos
                
                vistaPadre.actualizar();
                vistaPadre.mostrarMensaje("La asignación de " + nombreEmpleado + ""
                        + " fue borrada exitosamente");
            }
        }
        else vistaPadre.mostrarMensaje(""
                + "Primero debe seleccionar una fila de la tabla");
    }
    
//</editor-fold>
    
    /** Se setean las vistas y los controladores mutuamente. */
    private void cargar(AbstractFactoryCompleta factory) {
        controladorHijo = factory.crearControladorHijo();
        vistaHija = factory.crearVistaHija();
        controladorHijo.setVista(vistaHija);
        controladorHijo.setVistaPadre(vistaPadre);
        vistaHija.setControlador(controladorHijo);
    }
}