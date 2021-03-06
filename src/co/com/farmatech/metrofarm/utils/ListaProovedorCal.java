/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.utils;

import co.com.farmatech.metrofarm.cmd.CmdCalibracion;
import co.com.farmatech.metrofarm.views.Calibracion;
import co.com.farmatech.metrofarm.views.MantenimientoEquipo;
import co.com.farmatech.metrofarm.views.MantenimientoInstrumento;
import com.co.farmatech.metrofarm.dto.DtoProveedor;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Bryan
 */
public class ListaProovedorCal extends javax.swing.JFrame {

    String completo;
    CmdCalibracion cmdProvider;
    JFrame frame;

    /**
     * Creates new form ListaProovedor
     */
    public ListaProovedorCal(JFrame frameReceived) {
        initComponents();
        DefaultListModel lm = new DefaultListModel();
        cmdProvider = new CmdCalibracion();
        frame = frameReceived;
        ArrayList<DtoProveedor> providers = cmdProvider.findProviders(new DtoProveedor());

        if (providers != null && !providers.isEmpty()) {
            for (DtoProveedor dtoProveedor : providers) {
                Object objeto = dtoProveedor.getNit() + "-" + dtoProveedor.getNombre();
                lm.addElement(objeto);
            }
        } else {
            JOptionPane.showMessageDialog(null, "La lista de proovedores esta vacia");
            this.dispose();
        }


        ListaProovedores.setModel(lm);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAceptar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaProovedores = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Proovedores");
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(0, 0, 410, 300));
        setMinimumSize(new java.awt.Dimension(410, 300));
        setUndecorated(true);
        setResizable(false);
        setShape(getMaximizedBounds());
        getContentPane().setLayout(null);

        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/com/farmatech/metrofarm/resources/imagenes/proovedor.gif"))); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAceptar);
        btnAceptar.setBounds(290, 250, 100, 25);

        jLabel1.setText("Seleccione los proovedores que intervinieron en el mantenimiento.");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(80, 40, 330, 14);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/com/farmatech/metrofarm/resources/imagenes/eliminar.gif"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar);
        btnCancelar.setBounds(150, 250, 100, 25);

        ListaProovedores.setToolTipText("Mantenga presionado CTRL para escoger varios proovedores");
        jScrollPane1.setViewportView(ListaProovedores);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(150, 90, 240, 130);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/com/farmatech/metrofarm/resources/imagenes/formufondo.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 410, 300);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        Object[] seleccionados = ListaProovedores.getSelectedValues();
        int lim = seleccionados.length;
        completo = "";

        if (lim != 0) { // 3.1
            for (int i = 0; i < lim; i++) {  //3.2
                completo += seleccionados[i].toString() + "\n";
            }
            if (frame instanceof Calibracion) {
                ((Calibracion) frame).getTxaProovedores().append(completo);
                ((Calibracion) frame).getTxaProovedores().setBackground(new java.awt.Color(204, 255, 204));
            }
            if(frame instanceof MantenimientoEquipo){
                ((MantenimientoEquipo) frame).getTxaProovedores().append(completo);
                ((MantenimientoEquipo) frame).getTxaProovedores().setBackground(new java.awt.Color(204, 255, 204));
            }
            if(frame instanceof MantenimientoInstrumento){
                ((MantenimientoInstrumento) frame).getTxaProovedores().append(completo);
                ((MantenimientoInstrumento) frame).getTxaProovedores().setBackground(new java.awt.Color(204, 255, 204));
            }
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un proovedor");
        }

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
        if (frame instanceof Calibracion) {
            ((Calibracion) frame).getTxaProovedores().setBackground(Color.white);
        }

    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList ListaProovedores;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
