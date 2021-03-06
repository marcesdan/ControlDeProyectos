/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.vista.panel;

import static java.util.Objects.isNull;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import presentacion.vista.info.InfoAsignacion;
import presentacion.controlador.Controlador;
import presentacion.controlador.ControladorAsignacionNueva;
import presentacion.modelo.ProyectoComboBoxModel;
import presentacion.vista.Main;
import presentacion.vista.VistaHija;
import presentacion.vista.info.Info;

/**
 *
 * @author marces
 */
public class PanelAsignacionNueva
        extends javax.swing.JPanel implements VistaHija {

    private ControladorAsignacionNueva controlador;
    private InfoAsignacion info;
    private ValidadorDeCampos validador;
    
    public PanelAsignacionNueva() {
        initComponents();
        localInit();
    }
    
    private void localInit() {

        // Le pasamos los textFields que no deben estar vacíos.
        validador = new ValidadorDeCampos(txtPuesto);

        /* Le pasamos el botón que estará habilitado solo si dichos
        campos no estan vacíos */
        validador.setBoton(btnGuardar);
        validador.setFlag(false);
        
        txtEmpleado.setEditable(false);
        txtPresupuesto.setEditable(false);
        
        /* Para actuar en respuesta a los cambios de función
            * en el ProyectoComboBoxModel*/
        this.comboBoxProyecto.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                // Produce eventos de selección y deselección (solo queremos uno).
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    controlador.cambiaElProyecto(
                            info, comboBoxProyecto.getModel().getSelectedItem());

                    /* Se eligió un proyecto, entonces la condición externa
                        se pone en true (debe elegirse un proyecto para que el
                        boton guardar se habilite */
                    validador.setFlag(true);
                    btnGuardar.setEnabled(validador.isCamposIngresados());

                    // Actualizamos los campos que dependen del comboBoxProyecto.
                    txtPresupuesto.setText(info.getPresupuestoDisponible());
                }
            }
        });

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPago = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        comboBoxProyecto = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtEmpleado = new javax.swing.JTextField();
        txtHoras = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPuesto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPresupuesto = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la asignación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        jLabel1.setText("Empleado");

        jLabel2.setText("Proyecto");

        comboBoxProyecto.setModel(new ProyectoComboBoxModel());

        jLabel3.setText("Pago");

        jLabel4.setText("Horas");

        jLabel5.setText("Puesto (*)");

        jLabel7.setText("Presupuesto disponible");

        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(true);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtPuesto)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtPago, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtHoras)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(comboBoxProyecto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(btnGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVolver))
                            .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addGap(9, 9, 9)
                .addComponent(comboBoxProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPago, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnVolver))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Overrides">   
    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = (ControladorAsignacionNueva) controlador;
    }

    @Override
    public void setCamposDeTexto(Info campos) {

        info = (InfoAsignacion) campos;

        // Seteamos los campos. Los nulos quedarán vacíos.
        txtEmpleado.setText(info.getEmpleado().toString());
        txtPago.setText(info.getPago());
        txtPresupuesto.setText(info.getPresupuestoDisponible());
        txtHoras.setText(info.getHoras());
        txtPuesto.setText(info.getPuesto());
        txtPresupuesto.setText(info.getPresupuestoDisponible());
        comboBoxProyecto.getModel().setSelectedItem(info.getProyecto());

        
        if (!isNull(info.getId())) { // Si es una modificación     
            // Los datos obligatorios ya estan cargados.
            btnGuardar.setEnabled(true);    
            // No se utiliza el comboBox, por ende solo importan los textFields.
            validador.setFlag(true);  
            comboBoxProyecto.setEnabled(false);
        }
    }
    
    //</editor-fold>  

    // <editor-fold defaultstate="collapsed" desc="Manejo de eventos">  
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        Main.getInstance().cerrarDialogAux();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        controlador.guardarRegistro(info
                .withProyecto(comboBoxProyecto.getModel().getSelectedItem())
                .withPago(txtPago.getText())
                .withHoras(txtHoras.getText())
                .withPuesto(txtPuesto.getText()));
    }//GEN-LAST:event_btnGuardarActionPerformed
    //</editor-fold>  

    // <editor-fold defaultstate="collapsed" desc="Variables generadas por el editor">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> comboBoxProyecto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField txtEmpleado;
    private javax.swing.JTextField txtHoras;
    private javax.swing.JTextField txtPago;
    private javax.swing.JTextField txtPresupuesto;
    private javax.swing.JTextField txtPuesto;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>  
}
