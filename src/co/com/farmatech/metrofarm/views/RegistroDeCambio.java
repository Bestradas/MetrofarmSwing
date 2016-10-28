/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.views;

import co.com.farmatech.metrofarm.cmd.CmdChangeRegister;
import co.com.farmatech.metrofarm.utils.Fondo_Registro;
import co.com.farmatech.metrofarm.utils.Formu_Fondo;
import co.jankins.psf.common.global.GlobalUtils;
import com.co.farmatech.metrofarm.dto.DtoEquipo;
import com.co.farmatech.metrofarm.dto.DtoRegistroCambios;
import com.co.farmatech.metrofarm.dto.DtoUsuario;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import ventana.bloqueo.VentanaBloqueo;

/**
 *
 * @author bestrada
 */
public class RegistroDeCambio extends JFrame implements ActionListener, MouseListener {

    ImageIcon Icofondo;
    ImageIcon Icofondo2;
    Formu_Fondo PanelGeneral1;
    Fondo_Registro PanelGeneral2;
    JScrollPane scrollPane;
    String ruta = "co/com/farmatech/metrofarm/resources/imagenes/";
    public JButton btnGuardar = new JButton("Guardar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "guardar.gif")));
    JTextField txtcual, txtsolic_cam, txtArea, txtOtroDoc, txtJustificacion, txtOpNumero, txtTamañoTeorico, txtnumlote;
    Checkbox proceso, material, sac, provee, equipo, otro, Normal, Urgente, Poe, formato, Instruccion, Especificacion, OtrotipoDoc, Permanente, Temporal, Critico, Menor, Intermedio;
    CheckboxGroup cbg, cbgPrioridad, cbgTipoDoc, cbogClasifCambio, cbgTipoCambio;
    JLabel lblFecha, lblFecha2, lblsolicitado, lblEquipo, lblCodigo;
    JTextArea txaSituacionActual, txaSituacionPropuesta, txaRazonCambio;
    DtoUsuario usuarioLogeado;
    DtoEquipo equipoRegistro;
    CmdChangeRegister cmdChangeRegister;
    private final VentanaBloqueo ventana = new VentanaBloqueo();

    public RegistroDeCambio(DtoEquipo equipoRegistro) {


        super("Registro de cambios METROFARM -v1");
        this.usuarioLogeado = GlobalUtils.getContext().getLoginUser();
        this.equipoRegistro = equipoRegistro;
        cmdChangeRegister = new CmdChangeRegister();
        ((JPanel) getContentPane()).setOpaque(false);  // para que muestre la imagen
        this.setSize(1366, 768);
        this.setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);
        setBackground(Color.white);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(1);

        setIconImage(new ImageIcon(getClass().getClassLoader().getResource(ruta + "FARMA.png")).getImage());
        //setLocationRelativeTo(null);
        Font TLetra = new Font("Arial", Font.PLAIN, 17);
        Font fecha = new Font("Arial", Font.PLAIN, 18);
        Font boton = new Font("Century Gothic", Font.PLAIN, 18);


        Icofondo = new ImageIcon(getClass().getClassLoader().getResource(ruta + "textura.png"));
        Icofondo2 = new ImageIcon(getClass().getClassLoader().getResource(ruta + "registro fondo2.png"));

        btnGuardar.setBounds(350, 1450, 200, 25);
        btnGuardar.setVisible(true);
        btnGuardar.setFont(boton);
        btnGuardar.addActionListener(this);

        Date now = new Date(System.currentTimeMillis());
        lblFecha = new JLabel(GlobalUtils.getDateFormatted(now));
        lblFecha.setBounds(830, 85, 200, 20);
        lblFecha.setFont(fecha);
        lblFecha.setForeground(new Color(11, 101, 154));
        lblFecha.setVisible(true);

        lblFecha2 = new JLabel(GlobalUtils.getDateFormatted(now));
        lblFecha2.setBounds(500, 240, 200, 20);
        lblFecha2.setFont(fecha);
        lblFecha2.setForeground(new Color(11, 101, 154));
        lblFecha2.setVisible(true);

        txtsolic_cam = new JTextField("");
        txtsolic_cam.setBounds(285, 80, 230, 31);
        txtsolic_cam.setFont(fecha);
        txtsolic_cam.setForeground(Color.red);
        txtsolic_cam.setHorizontalAlignment(JTextField.CENTER);
        txtsolic_cam.setVisible(true);

        cbg = new CheckboxGroup();
        proceso = new Checkbox("proceso", cbg, true);
        proceso.setBounds(240, 123, 30, 30);
        proceso.setVisible(true);
        proceso.setBackground(getBackground());
        proceso.setForeground(getBackground());
        proceso.addMouseListener(this);

        material = new Checkbox("material", cbg, false);
        material.setBounds(480, 123, 30, 30);
        material.setVisible(true);
        material.setBackground(getBackground());
        material.setForeground(getBackground());
        material.addMouseListener(this);

        sac = new Checkbox("sac", cbg, false);
        sac.setBounds(580, 123, 30, 30);
        sac.setVisible(true);
        sac.setBackground(getBackground());
        sac.setForeground(getBackground());
        sac.addMouseListener(this);

        provee = new Checkbox("provee", cbg, false);
        provee.setBounds(240, 148, 30, 30);
        provee.setVisible(true);
        provee.setBackground(getBackground());
        provee.setForeground(getBackground());
        provee.addMouseListener(this);

        equipo = new Checkbox("equipo", cbg, false);
        equipo.setBounds(480, 148, 30, 30);
        equipo.setVisible(true);
        equipo.setBackground(getBackground());
        equipo.setForeground(getBackground());
        equipo.addMouseListener(this);

        otro = new Checkbox("otro", cbg, false);
        otro.setBounds(580, 148, 30, 30);
        otro.setVisible(true);
        otro.setBackground(getBackground());
        otro.setForeground(getBackground());
        otro.addMouseListener(this);

        txtcual = new JTextField("");
        txtcual.setBounds(731, 148, 295, 26);
        txtcual.setFont(fecha);
        txtcual.setForeground(Color.black);
        txtcual.setVisible(false);


        lblsolicitado = new JLabel("");
        lblsolicitado.setText(usuarioLogeado.getNombres() + " " + usuarioLogeado.getApellidos());
        lblsolicitado.setForeground(Color.black);
        lblsolicitado.setFont(fecha);
        lblsolicitado.setBounds(145, 240, 300, 20);

        txtArea = new JTextField("");
        txtArea.setBounds(731, 240, 295, 26);
        txtArea.setFont(fecha);
        txtArea.setForeground(Color.black);
        txtArea.setVisible(true);


        cbgPrioridad = new CheckboxGroup();

        Normal = new Checkbox("Normal", cbgPrioridad, true);
        Normal.setBounds(90, 300, 20, 20);
        Normal.setVisible(true);
        Normal.setBackground(getBackground());
        Normal.setForeground(getBackground());
        Normal.addMouseListener(this);

        Urgente = new Checkbox("Urgente", cbgPrioridad, false);
        Urgente.setBounds(220, 300, 20, 20);
        Urgente.setVisible(true);
        Urgente.setBackground(getBackground());
        Urgente.setForeground(getBackground());
        Urgente.addMouseListener(this);

        cbgTipoDoc = new CheckboxGroup();

        Poe = new Checkbox("Poe", cbgTipoDoc, true);
        Poe.setBounds(290, 300, 20, 20);
        Poe.setVisible(true);
        Poe.setBackground(getBackground());
        Poe.setForeground(getBackground());
        Poe.addMouseListener(this);

        formato = new Checkbox("formato", cbgTipoDoc, false);
        formato.setBounds(460, 300, 20, 20);
        formato.setVisible(true);
        formato.setBackground(getBackground());
        formato.setForeground(getBackground());
        formato.addMouseListener(this);

        Instruccion = new Checkbox("Instruccion", cbgTipoDoc, false);
        Instruccion.setBounds(610, 300, 20, 20);
        Instruccion.setVisible(true);
        Instruccion.setBackground(getBackground());
        Instruccion.setForeground(getBackground());
        Instruccion.addMouseListener(this);

        Especificacion = new Checkbox("Especificacion", cbgTipoDoc, false);
        Especificacion.setBounds(790, 300, 20, 20);
        Especificacion.setVisible(true);
        Especificacion.setBackground(getBackground());
        Especificacion.setForeground(getBackground());
        Especificacion.addMouseListener(this);

        OtrotipoDoc = new Checkbox("OtrotipoDoc", cbgTipoDoc, false);
        OtrotipoDoc.setBounds(905, 300, 20, 20);
        OtrotipoDoc.setVisible(true);
        OtrotipoDoc.setBackground(getBackground());
        OtrotipoDoc.setForeground(getBackground());
        OtrotipoDoc.addMouseListener(this);

        txtOtroDoc = new JTextField("");
        txtOtroDoc.setBounds(935, 300, 80, 26);
        txtOtroDoc.setFont(fecha);
        txtOtroDoc.setForeground(Color.black);
        txtOtroDoc.setVisible(false);

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

        cbogClasifCambio = new CheckboxGroup();

        Permanente = new Checkbox("Permanente", cbogClasifCambio, true);
        Permanente.setBounds(525, 690, 20, 10);
        Permanente.setVisible(true);
        Permanente.setBackground(getBackground());
        Permanente.setForeground(getBackground());
        Permanente.addMouseListener(this);

        Temporal = new Checkbox("Temporal", cbogClasifCambio, false);
        Temporal.setBounds(860, 690, 20, 10);
        Temporal.setVisible(true);
        Temporal.setBackground(getBackground());
        Temporal.setForeground(getBackground());
        Temporal.addMouseListener(this);


        cbgTipoCambio = new CheckboxGroup();

        Critico = new Checkbox("Critico", cbgTipoCambio, true);
        Critico.setBounds(260, 713, 20, 10);
        Critico.setVisible(true);
        Critico.setBackground(getBackground());
        Critico.setForeground(getBackground());
        Critico.addMouseListener(this);

        Menor = new Checkbox("Menor", cbgTipoCambio, false);
        Menor.setBounds(485, 713, 20, 10);
        Menor.setVisible(true);
        Menor.setBackground(getBackground());
        Menor.setForeground(getBackground());
        Menor.addMouseListener(this);

        Intermedio = new Checkbox("Intermedio", cbgTipoCambio, false);
        Intermedio.setBounds(802, 713, 20, 10);
        Intermedio.setVisible(true);
        Intermedio.setBackground(getBackground());
        Intermedio.setForeground(getBackground());
        Intermedio.addMouseListener(this);

        lblEquipo = new JLabel("");
        lblEquipo.setText(equipoRegistro.getNombre());
        lblEquipo.setForeground(Color.black);
        lblEquipo.setFont(fecha);
        lblEquipo.setBounds(280, 740, 300, 20);

        lblCodigo = new JLabel();
        lblCodigo.setText(equipoRegistro.getCodigoInv());
        lblCodigo.setForeground(Color.black);
        lblCodigo.setFont(fecha);
        lblCodigo.setBounds(110, 782, 300, 20);

        txtnumlote = new JTextField("");
        txtnumlote.setBounds(600, 773, 420, 26);
        txtnumlote.setFont(fecha);
        txtnumlote.setForeground(Color.black);
        txtnumlote.setVisible(true);

        txtOpNumero = new JTextField("");
        txtOpNumero.setBounds(90, 815, 485, 26);
        txtOpNumero.setFont(fecha);
        txtOpNumero.setForeground(Color.black);
        txtOpNumero.setVisible(true);

        txtTamañoTeorico = new JTextField("");
        txtTamañoTeorico.setBounds(780, 815, 240, 26);
        txtTamañoTeorico.setFont(fecha);
        txtTamañoTeorico.setForeground(Color.black);
        txtTamañoTeorico.setVisible(true);

        txtJustificacion = new JTextField("");
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

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == btnGuardar) {
            ventana.bloquear(this);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {

                    DtoRegistroCambios registroCambios = new DtoRegistroCambios();

                    registroCambios.setEquipoRegistro(equipoRegistro);
                    registroCambios.setCodigo(txtsolic_cam.getText());
                    registroCambios.setAplicado(cbg.getSelectedCheckbox().getLabel());
                    registroCambios.setCual(txtcual.getText());
                    registroCambios.setSolicitado(usuarioLogeado.getCodigo());
                    registroCambios.setArea(txtArea.getText());
                    registroCambios.setPrioridad(cbgPrioridad.getSelectedCheckbox().getLabel());
                    registroCambios.setTipodoc(cbgTipoDoc.getSelectedCheckbox().getLabel());
                    registroCambios.setOtro(txtOtroDoc.getText());
                    registroCambios.setSituactual(txaSituacionActual.getText());
                    registroCambios.setSitupropuesta(txaSituacionPropuesta.getText());
                    registroCambios.setRazoncambio(txaRazonCambio.getText());
                    registroCambios.setClasifcambio(cbogClasifCambio.getSelectedCheckbox().getLabel());
                    registroCambios.setTipocambio(cbgTipoCambio.getSelectedCheckbox().getLabel());
                    registroCambios.setLote(txtnumlote.getText());
                    registroCambios.setOp(txtOpNumero.getText());
                    registroCambios.setTamno(txtTamañoTeorico.getText());
                    registroCambios.setJustificacion(txtJustificacion.getText());

                    boolean valido = true;

                    if (registroCambios.getCodigo()
                            .equals("")) {
                        valido = false;
                    }

                    if (registroCambios.getArea()
                            .equals("")) {
                        valido = false;
                    }

                    if (registroCambios.getSituactual()
                            .equals("")) {
                        valido = false;
                    }

                    if (registroCambios.getSitupropuesta()
                            .equals("")) {
                        valido = false;
                    }

                    if (registroCambios.getRazoncambio()
                            .equals("")) {
                        valido = false;
                    }

                    if (registroCambios.getLote()
                            .equals("")) {
                        valido = false;
                    }

                    if (registroCambios.getOp()
                            .equals("")) {
                        valido = false;
                    }

                    if (registroCambios.getJustificacion()
                            .equals("")) {
                        valido = false;
                    }
                    if (valido
                            == true) {

                        boolean result = cmdChangeRegister.createChangeRegister(registroCambios);

                        if (result == false) {
                            JOptionPane.showMessageDialog(null, " No fue posible ingresar el nuevo regitro de cambios.");
                        } else {
                            JOptionPane.showMessageDialog(null, " Registro Ingresado");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, " Los campos marcados con * son obligatorios.");
                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();
        }

    }
    
        @Override
        public void mouseClicked
        (java.awt.event.MouseEvent e
        
            
            ) {

        if (e.getSource() == otro) {
                txtcual.setVisible(true);
                txtcual.setBackground(new Color(255, 255, 229));
            }
            if (e.getSource() == proceso) {
                txtcual.setVisible(false);
            }
            if (e.getSource() == material) {
                txtcual.setVisible(false);
            }
            if (e.getSource() == sac) {
                txtcual.setVisible(false);
            }
            if (e.getSource() == provee) {
                txtcual.setVisible(false);
            }
            if (e.getSource() == equipo) {
                txtcual.setVisible(false);
            }



            if (e.getSource() == OtrotipoDoc) {
                txtOtroDoc.setVisible(true);
                txtOtroDoc.setBackground(new Color(255, 255, 229));
            }
            if (e.getSource() == Poe) {
                txtOtroDoc.setVisible(false);
            }
            if (e.getSource() == formato) {
                txtOtroDoc.setVisible(false);
            }
            if (e.getSource() == Instruccion) {
                txtOtroDoc.setVisible(false);
            }
            if (e.getSource() == Especificacion) {
                txtOtroDoc.setVisible(false);
            }



        }

        @Override
        public void mousePressed
        (java.awt.event.MouseEvent e) {
    }

    @Override
        public void mouseReleased
        (java.awt.event.MouseEvent e) {
    }

    @Override
        public void mouseEntered
        (java.awt.event.MouseEvent e) {
    }

    @Override
        public void mouseExited
        (java.awt.event.MouseEvent e) {
    }
}
