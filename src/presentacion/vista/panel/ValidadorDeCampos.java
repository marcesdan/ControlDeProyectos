/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.vista.panel;

import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author marces
 */
public class ValidadorDeCampos implements DocumentListener {
    
    // Mantiene los campos de texto a validar.
    private final List<JTextField> monitorDeCampos;
    
    // Mantiene el botón que queremos habilitar/deshabilitar.
    private JButton boton;
    
    // Si todos los campos requeridos fueron ingresados
    private boolean camposIngresados;
    
    /** Condición externa que determina si se habilita o no el botón
     * Por ejemplo: un checkBox, un radioButton, etc. 
     * Arranca en true por si no es necesario (solo son los textField) */
    private boolean flag = true;

    /**
     * @param campos JTextFields recibidos como un array mediante "Varargs"
     * Crea un nuevo validador de campos
     */
    public ValidadorDeCampos(JTextField... campos) {
        
        monitorDeCampos = Arrays.asList(campos);
        monitorDeCampos.forEach((campo) -> {   
            // Les dice a los campos que van a ser "escuchados" por él.
            campo.getDocument().addDocumentListener(this);
        });
    }
    
    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    /**
     * @param boton boton que queremos habilitar/deshabilitar
     */
    public void setBoton(JButton boton) {
        this.boton = boton;
    }
    
    public boolean isCamposIngresados() {
        return camposIngresados;
    }
    
    public void setCamposIngresados(boolean camposIngresados) {
        this.camposIngresados = camposIngresados;
    }
    
    public boolean isFlag() {
        return flag;
    }
    
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="código del listener">
    @Override
    public void insertUpdate(DocumentEvent e) {
        change();
    }
    
    @Override
    public void removeUpdate(DocumentEvent e) {
        change();
    }
    
    @Override
    public void changedUpdate(DocumentEvent e) {
        change();
    }
    
    private void change(){
        
        camposIngresados = true;
        for (JTextField campo : monitorDeCampos) {
            // Quita los espacios y pregunta si esta vacío.
            if (campo.getText().trim().isEmpty()) {
                camposIngresados = false;
                break;
            }
        }
        
        boton.setEnabled(camposIngresados && flag);
    }
//</editor-fold>
}

