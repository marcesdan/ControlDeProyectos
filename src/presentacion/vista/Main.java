/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.vista;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import presentacion.controlador.ControladorFactory;
import presentacion.controlador.ControladorPrincipal;

/**
 *
 * @author marces
 */

public class Main implements Runnable{
    
    public static final Main INSTANCE = new Main();
    private JFrame frame;
    private JDialog dialogAux;
    
    public static Main getInstance(){
        return INSTANCE;
    }
    
    private Main(){}
    
    /**
     * Llamado por los controladores para colocar un panel en la vista hija 
     * @param panel el panel a mostrar en en la ventana hija
     * @param titulo el titulo a colocar en la ventana hija
     */
    public void mostrarPanelEnDialog(Vista panel, String titulo) {
        dialogAux.setContentPane((JPanel) panel);
        dialogAux.pack();
        dialogAux.setTitle(titulo);
        dialogAux.setVisible(true);
    }
    
    /**
     * Llamado por los controladores para colocar un panel en la vista padre
     * @param panel el panel a mostrar en la ventana padre
     */
    public void mostrarPanelEnFrame(VistaPadre panel) {
        frame.setContentPane((JPanel) panel);
        frame.pack();
    }
    
    /**
     * Llamado por los controladores para un unico ingreso de teclado
     * por parte del usuario. Por ej: un DNI.
     * @param st el mensaje con la pregunta que se le hace al usuario
     * @return lo que el usuario ingresó.
     */
    public String mostrarDialogInput(String st) {
        return JOptionPane.showInputDialog(frame, st);
    }
    
    public JDialog getDialogAux() {
        return dialogAux;
    }
    
    /**
     * Cierra la vista hija, volviendo a tomar el control al vista padre.
     */
    public void cerrarDialogAux() {
        dialogAux.dispose();
    }

    public JFrame getFrame() {
        return frame;
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(getInstance());
    }
    
    @Override
    public void run() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        // Llamamos a los new
        ControladorPrincipal controlador = 
                new ControladorFactory().crearControladorPrincipal();
        frame = new FramePrincipal(); 
        Vista vista = (Vista) frame;
        
        // Seteamos las referencias mutuamente
        controlador.setVista((Vista) frame);
        vista.setControlador(controlador);
        
        // El panel con el que arrancamos.
        controlador.mostrarProyectos();
        
        dialogAux = new DialogAux(frame, true);
    }
}
