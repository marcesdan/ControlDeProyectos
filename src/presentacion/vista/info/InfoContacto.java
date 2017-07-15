/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package presentacion.vista.info;

/**
 *
 * @author marces
 */
class InfoContacto {
  
    private String  celular, telefono, email;
    
    public String getTelefono() {
        return telefono;
    }
    
    public String getCelular() {
        return celular;
    }
    
    public String getEmail() {
        return email;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public InfoContacto withCelular(String celular) {
        this.celular = celular;
        return this;
    }
    
    public InfoContacto withTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }
    
    public InfoContacto withEmail(String email) {
        this.email = email;
        return this;
    }
}
