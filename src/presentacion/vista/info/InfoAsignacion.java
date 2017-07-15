/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.vista.info;

import static com.google.common.base.Strings.emptyToNull;
import static java.util.Objects.isNull;

/**
 *
 * @author marces
 */
public class InfoAsignacion extends Info {
    
    private Long id;
    private String pago, puesto, horas, presupuestoDisponible;
    private Object proyecto, empleado;
    
    
    public static InfoAsignacion crearInfoAsignacion(){
        return new InfoAsignacion();
    }
    
    public Long getId() {
        return id;
    }
    
    public Object getProyecto() {
        return proyecto;
    }
    
    public Object getEmpleado() {
        return empleado;
    }
    
    public String getPuesto() {
        return puesto;
    }
    
    public String getPago() {
        return pago;
    }
    
    public String getHoras() {
        return horas;
    }
    
    public String getPresupuestoDisponible(){
        return presupuestoDisponible;
    }
    
    public InfoAsignacion withId(Long id) {
        this.id = id;
        return this;
    }
    
    public InfoAsignacion withEmpleado(Object empleado) {
        this.empleado = empleado;
        return this;
    }

    public InfoAsignacion withPuesto(String puesto) {
        this.puesto = puesto;
        return this;
    }
    
    public InfoAsignacion withProyecto(Object proyecto) {
        this.proyecto = proyecto;
        return this;
    }
    
    public InfoAsignacion withPresupuestoDisponible(String value) {
        this.presupuestoDisponible = value;
        return this;
    }
    
    /**
     * Metodo sobrecargado para setear el objeto de dominio
     * @param pago 
     * @return this (fluent interface).
     */
    public InfoAsignacion withPago(String pago) {
        this.pago = emptyToNull(pago);
        return this;
    }
    
    /**
     * Metodo sobrecargado para setear la vista.
     * @param pago atributo opcional y por tanto "nullable".
     * @return this (fluent interface).
     */
    public InfoAsignacion withPago(Integer pago) {
        
        if (isNull(pago)) this.pago = null;
        
        /**Si era null hubiese lanzado una excepción.
         * Sin embargo, el atributo es opcional y por tanto es probable
         * que el mismo sea nulo. En ese caso la vista mostrara el campo vacío.
         */
        else this.pago = pago.toString();
        
        return this;
    }

    /**
     * Metodo sobrecargado para setear el objeto de dominio
     * @param horas 
     * @return this (fluent interface).
     */
    public InfoAsignacion withHoras(String horas) {
        this.horas = emptyToNull(horas);
        return this;
    }
    
    /**
     * Metodo sobrecargado para setear la vista.
     * @param value atributo opcional y por tanto "nullable".
     * @return this (fluent interface).
     */
    public InfoAsignacion withHoras(Integer value) {
        
        if (isNull(value)) this.horas = null;
        
        /**Si era null hubiese lanzado una excepción.
         * Sin embargo, el atributo es opcional y por tanto es probable
         * que el mismo sea nulo. En ese caso la vista mostrara el campo vacío.
         */
        else this.horas = value.toString();
        
        return this;
    }
}
