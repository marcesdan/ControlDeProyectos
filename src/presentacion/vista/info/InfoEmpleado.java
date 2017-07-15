/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package presentacion.vista.info;

import static com.google.common.base.Strings.emptyToNull;
import java.time.LocalDate;

/**
 *
 * @author marces
 */
public class InfoEmpleado extends Info {

    private Long id;
    private String documento, apellido, nombre;
    private LocalDate nacimiento;
    
    private final InfoContacto infoContacto;
    private final InfoDireccion infoDireccion;

    public static InfoEmpleado crearInfoEmpleado() {
        return new InfoEmpleado();
    }
    
    private InfoEmpleado() {
        infoContacto = new InfoContacto();
        infoDireccion = new InfoDireccion();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    public Long getId() {
        return id;
    }
    
    public String getDocumento() {
        return documento;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public LocalDate getNacimiento() {
        return nacimiento;
    }
    
    public String getTelefono() {
        return infoContacto.getTelefono();
    }
    
    public String getCelular() {
        return infoContacto.getCelular();
    }
    
    public String getEmail() {
        return infoContacto.getEmail();
    }
    
    public String getCalle() {
        return infoDireccion.getCalle();
    }
    
    public String getAltura() {
        return infoDireccion.getAltura();
    }
    
    public String getBarrio() {
        return infoDireccion.getBarrio();
    }
    
    
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Withs">
    public InfoEmpleado withId(Long id) {
        this.id = id;
        return this;
    }
    
    public InfoEmpleado withDocumento(String documento) {
        this.documento = documento;
        return this;
    }
    
    public InfoEmpleado withApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }
    
    public InfoEmpleado withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
    
    public InfoEmpleado withNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
        return this;
    }
    
    public InfoEmpleado withCelular(String celular) {
        this.infoContacto.setCelular(celular);
        return this;
    }
    
    public InfoEmpleado withTelefono(String telefono) {
        this.infoContacto.setTelefono(emptyToNull(telefono));
        return this;
    }
    
    public InfoEmpleado withEmail(String email) {
        this.infoContacto.setEmail(email);
        return this;
    }
    
    public InfoEmpleado withCalle(String calle) {
        this.infoDireccion.setCalle(emptyToNull(calle));
        return this;
    }

    public InfoEmpleado withAltura(String altura) {
        this.infoDireccion.setAltura(emptyToNull(altura));
        return this;
    }

    public InfoEmpleado withBarrio(String barrio) {
        this.infoDireccion.setBarrio(emptyToNull(barrio));
        return this;
    }
//</editor-fold>
}
