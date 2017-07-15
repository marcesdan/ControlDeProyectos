/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.vista.info;

/**
 *
 * @author marces
 */
class InfoDireccion {

    private String calle, altura, barrio;

    public String getCalle() {
        return calle;
    }

    public String getAltura() {
        return altura;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }
    
    public InfoDireccion withCalle(String calle) {
        this.calle = calle;
        return this;
    }

    public InfoDireccion withAltura(String altura) {
        this.altura = altura;
        return this;
    }

    public InfoDireccion withBarrio(String barrio) {
        this.barrio = barrio;
        return this;
    }
}
