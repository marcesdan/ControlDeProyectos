/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.vista.info;

/**
 * Clase utilizada para implementar un "Parameter Object Refactoring".
 * Sirve de intermediaria entre las clases de dominio y las vistas (y los
 * controladores)
 * 
 * Además, se toma como medida que los atributos del tipo cadena de texto 
 * (varchar) que son opcionales (nullables), se almacenan en la BD como
 * valores nulos, en lugar de cadenas vacias ( "" ). Es por eso que se
 * utiliza el método "emptyToNull" en los métodos "with".
 * 
 * @author marces
 */
public class InfoAsignacion extends Info {
    
   
    private String pago, puesto, horas, presupuestoDisponible;
    private Object proyecto, empleado;
    
    
    public static InfoAsignacion crearInfoAsignacion(){
        return new InfoAsignacion();
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
     * Metodo sobrecargado para setear el objeto de dominio. Si pago
     * es una cadena vacía, se setea pago con la cadena "0"
     * @param pago atributo opcional en la BD
     * @return this (fluent interface).
     */
    public InfoAsignacion withPago(String pago) {
        this.pago = emptyToCero(pago);
        return this;
    }
    
    /**
     * Metodo sobrecargado para setear la vista. Si pago es 0, se setea
     * en el campo de texto una cadena vacía (no 0).
     * @param pago atributo opcional.
     * @return this (fluent interface).
     */
    public InfoAsignacion withPago(Integer pago) {
        this.pago = ceroToEmpty(pago);
        return this;
    }

    /**
     * Metodo sobrecargado para setear el objeto de dominio. Si pago
     * es una cadena vacía, se setea pago con la cadena "0".
     * @param horas 
     * @return this (fluent interface).
     */
    public InfoAsignacion withHoras(String horas) {
        this.horas = emptyToCero(horas);
        return this;
    }
    
    /**
     * Metodo sobrecargado para setear la vista. Si value es 0, se setea
     * en el campo de texto una cadena vacía (no 0).
     * @param value atributo opcional en la BD
     * @return this (fluent interface).
     */
    public InfoAsignacion withHoras(Integer value) {
        this.horas = ceroToEmpty(value);
        return this;
    }
}
