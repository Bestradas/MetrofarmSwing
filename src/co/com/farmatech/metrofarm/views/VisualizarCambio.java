/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.views;

import co.com.farmatech.metrofarm.utils.Fondo_Registro;
import co.com.farmatech.metrofarm.utils.Formu_Fondo;
import co.com.farmatech.metrofarm.utils.Imprimir_JPanel;
import co.com.farmatech.metrofarm.utils.buttons.BotonesMenu;
import co.jankins.psf.common.global.GlobalUtils;
import com.co.farmatech.metrofarm.dto.DtoRegistroCambios;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author bestrada
 */
public class VisualizarCambio extends JFrame implements ActionListener {

    ImageIcon Icofondo;
    ImageIcon Icofondo2;
    Formu_Fondo PanelGeneral1;
    Fondo_Registro PanelGeneral2;
    JScrollPane scrollPane;
    JLabel txtcual, txtsolic_cam, txtArea, txtOtroDoc, txtJustificacion, txtOpNumero, txtTamañoTeorico, txtnumlote;
    ButtonGroup cbg, cbgPrioridad, cbgTipoDoc, cbogClasifCambio, cbgTipoCambio;
    JRadioButton proceso, material, sac, provee, equipo, otro, Normal, Urgente, Poe, formato, Instruccion, Especificacion, OtrotipoDoc, Permanente, Temporal, Critico, Menor, Intermedio;
    JLabel lblFecha, lblFecha2, lblsolicitado, lblEquipo, lblCodigo;
    JTextArea txaSituacionActual, txaSituacionPropuesta, txaRazonCambio;
    String prod;
    String cordigpro;
    public JButton btnGuardar;
    private JFileChooser elegir;
    BotonesMenu Imprimir;
    String ruta = "co/com/farmatech/metrofarm/resources/imagenes/";

    public VisualizarCambio(DtoRegistroCambios Cambio) {


        super("Registro de cambios METROFARM -v1");

        ((JPanel) getContentPane()).setOpaque(false);  // para que muestre la imagen
        this.setSize(1366, 768);
        this.setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);
        setBackground(Color.white);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(1);

        btnGuardar = new JButton("Descargar", new ImageIcon(getClass().getClassLoader().getResource(ruta+"guardar.gif")));
        
        Imprimir = new BotonesMenu(new ImageIcon(getClass().getClassLoader().getResource(ruta+"Print1.png")), new ImageIcon(getClass().getClassLoader().getResource(ruta+"Print2.png")));
        Imprimir.setBounds(600, 1450, 32, 32);
        Imprimir.setToolTipText("Imprimir registro");
        Imprimir.setVisible(true);
        Imprimir.addActionListener(this);
        Imprimir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setIconImage(new ImageIcon(getClass().getClassLoader().getResource(ruta+"FARMA.png")).getImage());
        //setLocationRelativeTo(null);
        Font TLetra = new Font("Arial", Font.PLAIN, 17);
        Font fecha = new Font("Arial", Font.PLAIN, 18);
        Font boton = new Font("Century Gothic", Font.PLAIN, 18);


        elegir = new JFileChooser();
        FileNameExtensionFilter filtrer = new FileNameExtensionFilter("Archivos pdf", "pdf");
        elegir.setFileFilter(filtrer);
        elegir.setMultiSelectionEnabled(false);
        elegir.setAcceptAllFileFilterUsed(false);

        Icofondo = new ImageIcon(getClass().getClassLoader().getResource(ruta+"textura.png"));
        Icofondo2 = new ImageIcon(getClass().getClassLoader().getResource(ruta+"registro fondo2.png"));

        btnGuardar.setBounds(390, 1450, 200, 25);
        btnGuardar.setVisible(true);
        btnGuardar.setFont(boton);
        btnGuardar.addActionListener(this);


        lblFecha = new JLabel(GlobalUtils.getDateFormatted(new Date()));
        lblFecha.setBounds(830, 85, 200, 20);
        lblFecha.setFont(fecha);
        lblFecha.setForeground(Color.black);
        lblFecha.setVisible(true);

        lblFecha2 = new JLabel(GlobalUtils.getDateFormatted(Cambio.getFecha()));
        lblFecha2.setBounds(500, 240, 200, 20);
        lblFecha2.setFont(fecha);
        lblFecha2.setForeground(Color.black);
        lblFecha2.setVisible(true);

        txtsolic_cam = new JLabel(Cambio.getCodigo());
        txtsolic_cam.setBounds(285, 80, 230, 31);
        txtsolic_cam.setFont(fecha);
        txtsolic_cam.setForeground(Color.black);
        txtsolic_cam.setHorizontalAlignment(JTextField.CENTER);
        txtsolic_cam.setVisible(true);

        cbg = new ButtonGroup();


        proceso = new JRadioButton("proceso", false);
        proceso.setBounds(240, 123, 20, 30);
        proceso.setVisible(true);
        proceso.setBackground(getBackground());
        proceso.setForeground(Color.white);
        proceso.setEnabled(false);
        if (Cambio.getAplicado().equals("proceso")) {
            proceso.setSelected(true);
        }

        material = new JRadioButton("material", false);
        material.setBounds(480, 123, 20, 30);
        material.setVisible(true);
        material.setBackground(getBackground());
        material.setForeground(Color.white);
        material.setEnabled(false);
        if (Cambio.getAplicado().equals("material")) {
            material.setSelected(true);
        }

        sac = new JRadioButton("sac", false);
        sac.setBounds(580, 123, 20, 30);
        sac.setVisible(true);
        sac.setBackground(getBackground());
        sac.setForeground(Color.white);
        sac.setEnabled(false);
        if (Cambio.getAplicado().equals("sac")) {
            sac.setSelected(true);
        }

        provee = new JRadioButton("provee", false);
        provee.setBounds(240, 148, 20, 30);
        provee.setVisible(true);
        provee.setBackground(getBackground());
        provee.setForeground(Color.white);
        provee.setEnabled(false);
        if (Cambio.getAplicado().equals("provee")) {
            provee.setSelected(true);
        }

        equipo = new JRadioButton("equipo", false);
        equipo.setBounds(480, 148, 20, 30);
        equipo.setVisible(true);
        equipo.setBackground(getBackground());
        equipo.setForeground(Color.white);
        equipo.setEnabled(false);
        if (Cambio.getAplicado().equals("equipo")) {
            equipo.setSelected(true);
        }

        otro = new JRadioButton("otro", false);
        otro.setBounds(580, 148, 20, 30);
        otro.setVisible(true);
        otro.setBackground(getBackground());
        otro.setForeground(Color.white);
        otro.setEnabled(false);
        if (Cambio.getAplicado().equals("otro")) {
            otro.setSelected(true);
        }

        txtcual = new JLabel("");
        txtcual.setBounds(731, 148, 295, 26);
        txtcual.setFont(fecha);
        txtcual.setForeground(Color.black);
        if (Cambio.getAplicado().equals("otro")) {
            txtcual.setVisible(true);
            txtcual.setText(Cambio.getCual());
        } else {
            txtcual.setVisible(false);
        }

        cbg.add(proceso);
        cbg.add(material);
        cbg.add(sac);
        cbg.add(provee);
        cbg.add(equipo);
        cbg.add(otro);


        lblsolicitado = new JLabel();
        lblsolicitado.setText(Cambio.getSolicitado());
        lblsolicitado.setForeground(Color.black);
        lblsolicitado.setFont(fecha);
        lblsolicitado.setBounds(145, 240, 300, 20);

        txtArea = new JLabel(Cambio.getArea());
        txtArea.setBounds(731, 240, 295, 26);
        txtArea.setFont(fecha);
        txtArea.setForeground(Color.black);
        txtArea.setVisible(true);


        cbgPrioridad = new ButtonGroup();

        Normal = new JRadioButton("Normal", false);
        Normal.setBounds(90, 300, 20, 20);
        Normal.setVisible(true);
        Normal.setBackground(getBackground());
        Normal.setForeground(Color.white);
        Normal.setEnabled(false);
        if (Cambio.getPrioridad().equals("Normal")) {
            Normal.setSelected(true);
        }

        Urgente = new JRadioButton("Urgente", false);
        Urgente.setBounds(220, 300, 20, 20);
        Urgente.setVisible(true);
        Urgente.setBackground(getBackground());
        Urgente.setForeground(Color.white);
        Urgente.setEnabled(false);
        if (Cambio.getPrioridad().equals("Urgente")) {
            Urgente.setSelected(true);
        }

        cbgPrioridad.add(Normal);
        cbgPrioridad.add(Urgente);

        cbgTipoDoc = new ButtonGroup();

        Poe = new JRadioButton("Poe", true);
        Poe.setBounds(290, 300, 20, 20);
        Poe.setVisible(true);
        Poe.setBackground(getBackground());
        Poe.setForeground(Color.white);
        Poe.setEnabled(false);
        if (Cambio.getTipodoc().equals("Poe")) {
            Poe.setSelected(true);
        }


        formato = new JRadioButton("formato", false);
        formato.setBounds(460, 300, 20, 20);
        formato.setVisible(true);
        formato.setBackground(getBackground());
        formato.setForeground(Color.white);
        formato.setEnabled(false);
        if (Cambio.getTipodoc().equals("formato")) {
            formato.setSelected(true);
        }




        Instruccion = new JRadioButton("Instruccion", false);
        Instruccion.setBounds(610, 300, 20, 20);
        Instruccion.setVisible(true);
        Instruccion.setBackground(getBackground());
        Instruccion.setForeground(Color.white);
        Instruccion.setEnabled(false);
        if (Cambio.getTipodoc().equals("Instruccion")) {
            Instruccion.setSelected(true);
        }



        Especificacion = new JRadioButton("Especificacion", false);
        Especificacion.setBounds(790, 300, 20, 20);
        Especificacion.setVisible(true);
        Especificacion.setBackground(getBackground());
        Especificacion.setForeground(Color.white);
        Especificacion.setEnabled(false);
        if (Cambio.getTipodoc().equals("Especificacion")) {
            Especificacion.setSelected(true);
        }



        OtrotipoDoc = new JRadioButton("OtrotipoDoc", false);
        OtrotipoDoc.setBounds(905, 300, 20, 20);
        OtrotipoDoc.setVisible(true);
        OtrotipoDoc.setBackground(getBackground());
        OtrotipoDoc.setForeground(getBackground());
        OtrotipoDoc.setEnabled(false);
        if (Cambio.getTipodoc().equals("OtrotipoDoc")) {
            OtrotipoDoc.setSelected(true);
        }

        cbgTipoDoc.add(Poe);
        cbgTipoDoc.add(formato);
        cbgTipoDoc.add(Instruccion);
        cbgTipoDoc.add(Especificacion);
        cbgTipoDoc.add(OtrotipoDoc);


        txtOtroDoc = new JLabel("");
        txtOtroDoc.setBounds(935, 300, 80, 26);
        txtOtroDoc.setFont(fecha);
        txtOtroDoc.setForeground(Color.black);

        if (Cambio.getTipodoc().equals("OtrotipoDoc")) {
            txtOtroDoc.setVisible(true);
            txtOtroDoc.setText(Cambio.getOtro());

        } else {
            txtOtroDoc.setVisible(false);
        }



        txaSituacionActual = new JTextArea();
        txaSituacionActual.setBounds(20, 362, 1010, 80);
        txaSituacionActual.setToolTipText("Describa la situación actual de equipo/instrumento");
        txaSituacionActual.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txaSituacionActual.setBackground(getBackground());
        txaSituacionActual.setFont(TLetra);
        txaSituacionActual.setForeground(Color.BLACK);
        txaSituacionActual.setBackground(new Color(255, 255, 229));
        txaSituacionActual.setVisible(true);
        txaSituacionActual.setLineWrap(true);//Provocar salto de linea cortando palabra
        txaSituacionActual.setWrapStyleWord(true);//Provocar salto de linea despues del espacio
        txaSituacionActual.setText(Cambio.getSituactual());
        txaSituacionActual.setEditable(false);

        txaSituacionPropuesta = new JTextArea();
        txaSituacionPropuesta.setBounds(20, 470, 1010, 110);
        txaSituacionPropuesta.setToolTipText("Describa la situación propuesta para el equipo/instrumento");
        txaSituacionPropuesta.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txaSituacionPropuesta.setBackground(getBackground());
        txaSituacionPropuesta.setFont(TLetra);
        txaSituacionPropuesta.setForeground(Color.BLACK);
        txaSituacionPropuesta.setBackground(new Color(255, 255, 229));
        txaSituacionPropuesta.setVisible(true);
        txaSituacionPropuesta.setLineWrap(true);//Provocar salto de linea cortando palabra
        txaSituacionPropuesta.setWrapStyleWord(true);//Provocar salto de linea despues del espacio 
        txaSituacionPropuesta.setText(Cambio.getSitupropuesta());
        txaSituacionPropuesta.setEditable(false);

        txaRazonCambio = new JTextArea();
        txaRazonCambio.setBounds(20, 605, 1010, 80);
        txaRazonCambio.setToolTipText("Describa la razón o motivo del cambio");
        txaRazonCambio.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txaRazonCambio.setBackground(getBackground());
        txaRazonCambio.setFont(TLetra);
        txaRazonCambio.setForeground(Color.BLACK);
        txaRazonCambio.setBackground(new Color(255, 255, 229));
        txaRazonCambio.setVisible(true);
        txaRazonCambio.setLineWrap(true);//Provocar salto de linea cortando palabra
        txaRazonCambio.setWrapStyleWord(true);//Provocar salto de linea despues del espacio
        txaRazonCambio.setText(Cambio.getRazoncambio());
        txaRazonCambio.setEditable(false);

        cbogClasifCambio = new ButtonGroup();

        Permanente = new JRadioButton("Permanente", true);
        Permanente.setBounds(525, 690, 20, 10);
        Permanente.setVisible(true);
        Permanente.setBackground(getBackground());
        Permanente.setForeground(getBackground());
        Permanente.setEnabled(false);
        if (Cambio.getClasifcambio().equals("Permanente")) {
            Permanente.setSelected(true);
        }


        Temporal = new JRadioButton("Temporal", false);
        Temporal.setBounds(860, 690, 20, 10);
        Temporal.setVisible(true);
        Temporal.setBackground(getBackground());
        Temporal.setForeground(getBackground());
        Temporal.setEnabled(false);
        if (Cambio.getClasifcambio().equals("Temporal")) {
            Temporal.setSelected(true);
        }

        cbogClasifCambio.add(Permanente);
        cbogClasifCambio.add(Temporal);



        cbgTipoCambio = new ButtonGroup();

        Critico = new JRadioButton("Critico", true);
        Critico.setBounds(260, 713, 20, 10);
        Critico.setVisible(true);
        Critico.setBackground(getBackground());
        Critico.setForeground(getBackground());
        Critico.setEnabled(false);
        if (Cambio.getClasifcambio().equals("Critico")) {
            Critico.setSelected(true);
        }



        Menor = new JRadioButton("Menor", false);
        Menor.setBounds(485, 713, 20, 10);
        Menor.setVisible(true);
        Menor.setBackground(getBackground());
        Menor.setForeground(getBackground());
        Menor.setEnabled(false);
        if (Cambio.getClasifcambio().equals("Menor")) {
            Menor.setSelected(true);
        }


        Intermedio = new JRadioButton("Intermedio", false);
        Intermedio.setBounds(802, 713, 20, 10);
        Intermedio.setVisible(true);
        Intermedio.setBackground(getBackground());
        Intermedio.setForeground(getBackground());
        Intermedio.setEnabled(false);
        if (Cambio.getClasifcambio().equals("Intermedio")) {
            Intermedio.setSelected(true);
        }

        cbgTipoCambio.add(Critico);
        cbgTipoCambio.add(Menor);
        cbgTipoCambio.add(Intermedio);


        lblEquipo = new JLabel();
        lblEquipo.setText(Cambio.getEquipoRegistro().getNombre());
        lblEquipo.setForeground(Color.black);
        lblEquipo.setFont(fecha);
        lblEquipo.setBounds(280, 740, 300, 20);

        lblCodigo = new JLabel();
        lblCodigo.setText(Cambio.getEquipoRegistro().getCodigoInv());
        lblCodigo.setForeground(Color.black);
        lblCodigo.setFont(fecha);
        lblCodigo.setBounds(110, 782, 300, 20);

        txtnumlote = new JLabel(Cambio.getLote());
        txtnumlote.setBounds(600, 773, 420, 26);
        txtnumlote.setFont(fecha);
        txtnumlote.setForeground(Color.black);
        txtnumlote.setVisible(true);

        txtOpNumero = new JLabel(Cambio.getOp());
        txtOpNumero.setBounds(90, 815, 485, 26);
        txtOpNumero.setFont(fecha);
        txtOpNumero.setForeground(Color.black);
        txtOpNumero.setVisible(true);

        txtTamañoTeorico = new JLabel(Cambio.getTamno());
        txtTamañoTeorico.setBounds(780, 815, 240, 26);
        txtTamañoTeorico.setFont(fecha);
        txtTamañoTeorico.setForeground(Color.black);
        txtTamañoTeorico.setVisible(true);

        txtJustificacion = new JLabel(Cambio.getJustificacion());
        txtJustificacion.setBounds(280, 860, 740, 26);
        txtJustificacion.setFont(fecha);
        txtJustificacion.setToolTipText("Describa una justificación económica en caso de que aplique");
        txtJustificacion.setForeground(Color.black);
        txtJustificacion.setVisible(true);

        PanelGeneral2 = new Fondo_Registro(Icofondo2);
        PanelGeneral2.setBorder(BorderFactory.createTitledBorder(""));
        //PanelGeneral1.add(fondo);
        PanelGeneral2.setBackground(Color.WHITE);
        PanelGeneral2.setForeground(Color.red);
        PanelGeneral2.setBounds(0, 0, 1050, 1421);
        PanelGeneral2.setLayout(null);
        PanelGeneral2.setPreferredSize(new Dimension(1050, 1500));

        scrollPane = new JScrollPane(PanelGeneral2);
        scrollPane.setViewportView(PanelGeneral2);
        scrollPane.setBounds(155, 0, 1050, 758);
        scrollPane.getViewport().setView(PanelGeneral2);


        PanelGeneral1 = new Formu_Fondo(Icofondo);
        PanelGeneral1.setBorder(BorderFactory.createTitledBorder(""));
        //PanelGeneral1.add(fondo);
        PanelGeneral1.setBackground(Color.black);
        PanelGeneral1.setForeground(Color.red);
        PanelGeneral1.reshape(0, 0, 1366, 768);
        PanelGeneral1.setLayout(null);

        PanelGeneral1.setVisible(true);
        this.getContentPane().add(PanelGeneral1);

        PanelGeneral2.add(btnGuardar);
        PanelGeneral2.add(Imprimir);
        PanelGeneral2.add(proceso);
        PanelGeneral2.add(material);
        PanelGeneral2.add(sac);
        PanelGeneral2.add(provee);
        PanelGeneral2.add(equipo);
        PanelGeneral2.add(otro);
        PanelGeneral2.add(txtcual);
        PanelGeneral2.add(lblFecha);
        PanelGeneral2.add(txtsolic_cam);
        PanelGeneral2.add(lblsolicitado);
        PanelGeneral2.add(lblFecha2);
        PanelGeneral2.add(txtArea);
        PanelGeneral2.add(Normal);
        PanelGeneral2.add(Urgente);
        PanelGeneral2.add(Poe);
        PanelGeneral2.add(formato);
        PanelGeneral2.add(Instruccion);
        PanelGeneral2.add(Especificacion);
        PanelGeneral2.add(OtrotipoDoc);
        PanelGeneral2.add(txtOtroDoc);
        PanelGeneral2.add(txaSituacionActual);
        PanelGeneral2.add(txaSituacionPropuesta);
        PanelGeneral2.add(txaRazonCambio);
        PanelGeneral2.add(Permanente);
        PanelGeneral2.add(Temporal);
        PanelGeneral2.add(Critico);
        PanelGeneral2.add(Menor);
        PanelGeneral2.add(Intermedio);
        PanelGeneral2.add(lblEquipo);
        PanelGeneral2.add(lblCodigo);
        PanelGeneral2.add(txtJustificacion);
        PanelGeneral2.add(txtnumlote);
        PanelGeneral2.add(txtOpNumero);
        PanelGeneral2.add(txtTamañoTeorico);

        PanelGeneral1.add(scrollPane);





        this.repaint();

    }

    public BufferedImage createImage(JPanel panel) {

        int w = panel.getWidth();
        int h = 1421;

        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.paintAll(g);
        return bi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {

            proceso.setEnabled(true);
            provee.setEnabled(true);
            material.setEnabled(true);
            equipo.setEnabled(true);
            sac.setEnabled(true);
            otro.setEnabled(true);
            Normal.setEnabled(true);
            Urgente.setEnabled(true);
            Poe.setEnabled(true);
            formato.setEnabled(true);
            Instruccion.setEnabled(true);
            Especificacion.setEnabled(true);
            otro.setEnabled(true);
            Permanente.setEnabled(true);
            Temporal.setEnabled(true);
            Critico.setEnabled(true);
            Menor.setEnabled(true);
            Intermedio.setEnabled(true);


            BufferedImage bufimagen = createImage(PanelGeneral2);

            int returnVal = elegir.showDialog(this, "Guardar");
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    String Ruta = String.valueOf(elegir.getSelectedFile().getPath() + ".png");
                    File destino = new File(Ruta);

                    ImageIO.write(bufimagen, "png", destino);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Hubo un error :" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                System.out.println("Carga cancelada por el usuario");
            }

            JOptionPane.showMessageDialog(null, "El registro de cambios fue descargado", "MetroFarm", JOptionPane.INFORMATION_MESSAGE);
            proceso.setEnabled(false);
            provee.setEnabled(false);
            material.setEnabled(false);
            equipo.setEnabled(false);
            sac.setEnabled(false);
            otro.setEnabled(false);
            Normal.setEnabled(false);
            Urgente.setEnabled(false);
            Poe.setEnabled(false);
            formato.setEnabled(false);
            Instruccion.setEnabled(false);
            Especificacion.setEnabled(false);
            otro.setEnabled(false);
            Permanente.setEnabled(false);
            Temporal.setEnabled(false);
            Critico.setEnabled(false);
            Menor.setEnabled(false);
            Intermedio.setEnabled(false);
            repaint();

        }

        if (e.getSource() == Imprimir) {

            int respuesta = JOptionPane.showConfirmDialog(null, "Esta seguro que desea imprimir el documento", "MetroFarm", JOptionPane.YES_NO_OPTION);
            switch (respuesta) {
                case JOptionPane.YES_OPTION:                  
                    Imprimir_JPanel im = new Imprimir_JPanel(PanelGeneral2);
                    break;
                case JOptionPane.NO_OPTION:
                    break;
            }

        }
    }

}
