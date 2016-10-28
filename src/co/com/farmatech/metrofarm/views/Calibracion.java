/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.views;

import co.com.farmatech.metrofarm.cmd.CmdCalibracion;
import co.com.farmatech.metrofarm.cmd.CmdEquipment;
import co.com.farmatech.metrofarm.utils.Formu_Fondo;
import co.com.farmatech.metrofarm.utils.ListaProovedorCal;
import co.com.farmatech.metrofarm.utils.PanelPDf;
import co.com.farmatech.metrofarm.utils.PanelPaginador;
import co.com.farmatech.metrofarm.utils.TablaEquipos;
import co.com.farmatech.metrofarm.utils.UtilFarmatech;
import co.com.farmatech.metrofarm.utils.buttons.BotonesMenu;
import co.com.farmatech.metrofarm.views.notification.ThreadNotification;
import co.janker.dto.objresponse.ObjectResponse;
import co.jankins.psf.common.enums.Estados;
import co.jankins.psf.common.enums.Frecuencias;
import co.jankins.psf.common.global.GlobalUtils;
import com.co.farmatech.metrofarm.dto.DtoCalibracion;
import com.co.farmatech.metrofarm.dto.DtoEquipo;
import com.co.farmatech.metrofarm.dto.PageData;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import ventana.bloqueo.VentanaBloqueo;

/**
 *
 * @author Softcaribbean
 */
public class Calibracion extends JFrame implements ActionListener, MouseListener {

    JTable TablaDatos;
    Formu_Fondo PanelGeneral1;
    ImageIcon Icofondo;
    JLabel lbldivi, lblexac, lblvalmin, lblvalmax, lblrangcal, lblrantra, mostraequipos, lblAdjuntar, lblCancelar, lblVer;
    JTextField txtdiv, txtexac, txtvalmin, txtvalmax, txtrangcal, txtrantra, txtprecio;
    public JTextArea txaProovedores;
    public JLabel lblfoto;
    JPanel PanelFoto, Panelinfo, Panelinfo2;
    public JTextField txtcodInv, txtCodmet, txtnumse, txtnom, txtUbicac, txtEstado, txtModelo, txtMarca, txtCliente, txtCal;
    JLabel lblCodinv, lblCodmet, lblNumserie, lblnom, lblubica, lblEstado, lblModelo, lblMarca, lblCliente, lblCal, lblPrecio, LblHistorial;
    private Object[][] DetallesIdentificacion = new Object[0][0];
    private Object[] ColumnasIdentificacion = {"Codigo de Calibracion", "Código Inventario", "Código Metrologia", "Número de Serie", "Nombre", "Clasificación", "Cliente", "Estado", "Fecha", "Proxima calibración", "Rango Calibración", "Rango de Trabajo", "Objeto"};
    private DefaultTableModel dataModel = new DefaultTableModel(DetallesIdentificacion, ColumnasIdentificacion);
    JScrollPane PanelDatos;
    String ruta = "co/com/farmatech/metrofarm/resources/imagenes/";
    public JButton btnGuardar = new JButton("Guardar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "guardar.gif")));
    JButton btnProovedores = new JButton("Proovedores", new ImageIcon(getClass().getClassLoader().getResource(ruta + "proovedor.gif")));
    public JButton btnBuscar = new JButton("Buscar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "buscar.gif")));
    public JButton btnNuevo = new JButton("Nuevo", new ImageIcon(getClass().getClassLoader().getResource(ruta + "nuevo.gif")));
    public JButton btnSalir = new JButton("Salir", new ImageIcon(getClass().getClassLoader().getResource(ruta + "volver.gif")));
    public JButton btnEliminar = new JButton("Eliminar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "eliminar.gif")));
    public JButton btnActualizar = new JButton("Actualizar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "actualizar.gif")));
    public JButton btnBuscarInv = new JButton("Filtar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "buscar.gif")));
    public JButton btnBuscaridMetr = new JButton("Filtar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "buscar.gif")));
    public JButton btnBuscarSerie = new JButton("Filtrar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "buscar.gif")));
    public BotonesMenu btnsacartabla = new BotonesMenu(new ImageIcon(getClass().getClassLoader().getResource(ruta + "table.png")), new ImageIcon(getClass().getClassLoader().getResource(ruta + "table2.png")));
    JButton btnCalVencidos = new JButton("Calibraciones Vencidos", new ImageIcon(getClass().getClassLoader().getResource(ruta + "Vencido.gif")));
    JButton btnCalDelMes = new JButton("Calibraciones del mes", new ImageIcon(getClass().getClassLoader().getResource(ruta + "Mes.png")));
    String nombre, Ruta;
    JFileChooser elegir = new JFileChooser();
    File Archivo;
    UtilFarmatech util = new UtilFarmatech();
    JFrame frame;
    CmdCalibracion cmdCalibration = new CmdCalibracion();
    CmdEquipment cmdEquipment = new CmdEquipment();
    VentanaBloqueo ventana = new VentanaBloqueo();
    public DtoEquipo equipoSeleccionado;
    MenuPrincipal menu;
    PanelPaginador panelPaginador;
    PageData pageData;

    public Calibracion(MenuPrincipal menu) {

        super("Calibraciòn METROFARM -v1");

        ((JPanel) getContentPane()).setOpaque(false);  // para que muestre la imagen
        this.setSize(1366, 768);
        this.setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);
        setBackground(Color.white);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(0);

        frame = this;
        this.menu = menu;
        
        panelPaginador = new PanelPaginador();
        panelPaginador.setBounds(1000, 670, 300, 29);

        pageData = panelPaginador.getPageData();
        pageData.setRowsPerPage(5);

        setIconImage(new ImageIcon(getClass().getClassLoader().getResource(ruta + "FARMA.png")).getImage());
        //setLocationRelativeTo(null);
        Font TLetra = new Font("Arial", Font.PLAIN, 12);
        Font Advertencia = new Font("Arial", Font.PLAIN, 15);

        Icofondo = new ImageIcon(getClass().getClassLoader().getResource(ruta + "fondomenu.png"));
        Ruta = "";

        LblHistorial = new JLabel("Historial de calibraciones");
        LblHistorial.setBounds(50, 340, 400, 20);
        LblHistorial.setFont(new Font("Arial", Font.ITALIC, 15));
        LblHistorial.setForeground(Color.blue);
        LblHistorial.setVisible(true);

        Panelinfo = new JPanel();
        Panelinfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(Integer.parseInt("015289", 16))), "Datos del equipo"));
        Panelinfo.setBackground(getBackground());
        Panelinfo.setForeground(new Color(Integer.parseInt("EF2F24", 16)));
        Panelinfo.reshape(380, 10, 450, 310);
        Panelinfo.setLayout(null);
        Panelinfo.setVisible(true);

        Panelinfo2 = new JPanel();
        Panelinfo2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(Integer.parseInt("015289", 16))), "Caracteristcas de calibraciòn"));
        Panelinfo2.setBackground(getBackground());
        Panelinfo2.setForeground(new Color(Integer.parseInt("EF2F24", 16)));
        Panelinfo2.reshape(850, 10, 450, 310);
        Panelinfo2.setLayout(null);
        Panelinfo2.setVisible(true);

        PanelGeneral1 = new Formu_Fondo(Icofondo);
        PanelGeneral1.setBorder(BorderFactory.createTitledBorder(""));
        PanelGeneral1.setBackground(Color.black);
        PanelGeneral1.setForeground(Color.red);
        PanelGeneral1.reshape(0, 0, 1366, 768);
        PanelGeneral1.setLayout(null);

        PanelGeneral1.setVisible(true);
        this.getContentPane().add(PanelGeneral1);

        TablaDatos = new JTable(dataModel) {
            @Override
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        PanelDatos = new JScrollPane(TablaDatos);
        TablaDatos.setSize(3000, 200);
        TablaDatos.getTableHeader().setReorderingAllowed(false);
        TablaDatos.getColumnModel().getColumn(0).setMinWidth(140);
        TablaDatos.getColumnModel().getColumn(1).setMinWidth(140);
        TablaDatos.getColumnModel().getColumn(2).setMinWidth(130);
        TablaDatos.getColumnModel().getColumn(3).setMinWidth(130);
        TablaDatos.getColumnModel().getColumn(4).setMinWidth(130);
        TablaDatos.getColumnModel().getColumn(5).setMinWidth(180);
        TablaDatos.getColumnModel().getColumn(6).setMinWidth(180);
        TablaDatos.getColumnModel().getColumn(7).setMinWidth(170);
        TablaDatos.getColumnModel().getColumn(8).setMinWidth(100);
        TablaDatos.getColumnModel().getColumn(9).setMinWidth(150);
        TablaDatos.getColumnModel().getColumn(10).setMinWidth(150);
        TablaDatos.getColumnModel().getColumn(11).setMinWidth(150);
        ocultarColumna("Objeto");

        PanelDatos.setBounds(50, 360, 1250, 200);
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(dataModel);
        TablaDatos.setRowSorter(elQueOrdena);
        TablaDatos.addMouseListener(this);
        TablaDatos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        PanelDatos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        lblAdjuntar = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(ruta + "adjuntar.png")));
        lblAdjuntar.setBackground(Color.black);
        lblAdjuntar.setToolTipText("cargue el pdf de calibracion del equipo");
        lblAdjuntar.setBorder(BorderFactory.createEmptyBorder());
        lblAdjuntar.setBackground(getBackground());
        lblAdjuntar.reshape(100, 600, 150, 64);
        lblAdjuntar.setLayout(null);
        lblAdjuntar.addMouseListener(this);
        lblAdjuntar.setVisible(true);

        lblVer = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(ruta + "leer.png")));
        lblVer.setBackground(Color.black);
        lblVer.setToolTipText("Visualice el documento de Calibración asociado a este equipo");
        lblVer.setBorder(BorderFactory.createEmptyBorder());
        lblVer.setBackground(getBackground());
        lblVer.reshape(250, 600, 180, 64);
        lblVer.setLayout(null);
        lblVer.addMouseListener(this);
        lblVer.setVisible(false);

        lblCancelar = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(ruta + "cancelarcarga.png")));
        lblCancelar.setBackground(Color.black);
        lblCancelar.setToolTipText("cargue el pdf de calibracion del equipo");
        lblCancelar.setBorder(BorderFactory.createEmptyBorder());
        lblCancelar.setBackground(Color.white);
        lblCancelar.reshape(100, 600, 150, 64);
        lblCancelar.setLayout(null);
        lblCancelar.setVisible(false);
        lblCancelar.addMouseListener(this);

        lblfoto = new JLabel();
        lblfoto.setBackground(Color.black);
        lblfoto.setBorder(BorderFactory.createEmptyBorder());
        lblfoto.setOpaque(true);
        lblfoto.setBackground(Color.white);
        lblfoto.reshape(25, 25, 250, 250);
        lblfoto.setLayout(null);

        PanelFoto = new JPanel();
        PanelFoto.setBorder(BorderFactory.createTitledBorder("Foto de equipo"));
        PanelFoto.setForeground(Color.red);
        PanelFoto.reshape(50, 20, 300, 300);
        PanelFoto.setLayout(null);
        PanelFoto.add(lblfoto);

        mostraequipos = new JLabel("Mostrar equipos");
        mostraequipos.setBounds(630, 585, 200, 20);
        mostraequipos.setFont(TLetra);
        mostraequipos.setForeground(Color.black);
        mostraequipos.setVisible(true);

        btnProovedores.setBounds(15, 225, 120, 25);
        btnProovedores.addActionListener(this);
        btnProovedores.setVisible(true);

        btnNuevo.setBounds(400, 680, 100, 25);
        btnNuevo.addActionListener(this);
        btnNuevo.setVisible(true);

        btnGuardar.setBounds(500, 680, 100, 25);
        btnGuardar.addActionListener(this);
        btnGuardar.setVisible(true);

        btnActualizar.setBounds(600, 680, 100, 25);
        btnActualizar.addActionListener(this);
        btnActualizar.setVisible(true);

        btnEliminar.setBounds(700, 680, 100, 25);
        btnEliminar.addActionListener(this);
        btnEliminar.setVisible(true);

        btnSalir.setBounds(800, 680, 100, 25);
        btnSalir.addActionListener(this);
        btnSalir.setVisible(true);

        btnBuscar.setBounds(320, 8, 100, 25);
        btnBuscar.addActionListener(this);
        btnBuscar.setVisible(true);

        btnBuscarInv.setBounds(320, 34, 100, 25);
        btnBuscarInv.addActionListener(this);
        btnBuscarInv.setVisible(true);

        btnBuscaridMetr.setBounds(320, 64, 100, 25);
        btnBuscaridMetr.addActionListener(this);
        btnBuscaridMetr.setVisible(true);

        btnBuscarSerie.setBounds(320, 94, 100, 25);
        btnBuscarSerie.addActionListener(this);
        btnBuscarSerie.setVisible(true);

        btnCalVencidos.setBounds(5, 680, 180, 40);
        btnCalVencidos.addActionListener(this);
        btnCalVencidos.setVisible(true);

        btnCalDelMes.setBounds(190, 680, 200, 40);
        btnCalDelMes.addActionListener(this);
        btnCalDelMes.setVisible(true);

        btnsacartabla.setBounds(650, 600, 50, 50);
        btnsacartabla.addActionListener(this);
        btnsacartabla.setVisible(true);

        lblCal = new JLabel("* Código de calibración");
        lblCal.setBounds(30, 10, 200, 20);
        lblCal.setFont(TLetra);
        lblCal.setForeground(Color.black);
        lblCal.setVisible(true);

        txtCal = new JTextField("");
        txtCal.setBounds(200, 10, 100, 20);
        txtCal.setFont(TLetra);
        txtCal.setForeground(Color.black);
        txtCal.setVisible(true);
        txtCal.setEditable(false);

        lblCodinv = new JLabel("* Còdigo inventario");
        lblCodinv.setBounds(30, 35, 200, 20);
        lblCodinv.setFont(TLetra);
        lblCodinv.setForeground(Color.black);
        lblCodinv.setVisible(true);

        txtcodInv = new JTextField("");
        txtcodInv.setBounds(200, 35, 100, 20);
        txtcodInv.setFont(TLetra);
        txtcodInv.setForeground(Color.black);
        txtcodInv.setVisible(true);
        txtcodInv.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                util.anularTildes(e);
                int limite = 11;
                char caracter = e.getKeyChar();
                if (txtcodInv.getText().length() == limite) {
                    e.consume();
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Este campo solo puede tener como maximo 11 caracteres", "METROFARM", JOptionPane.ERROR_MESSAGE);
                }

                if ((caracter == KeyEvent.VK_ENTER)) {
                    ventana.bloquear(frame);
                    SwingWorker worker;
                    worker = new SwingWorker() {
                        @Override
                        protected Object doInBackground() throws Exception {
                            if (txtcodInv.getText().trim().equals("")) {
                                JOptionPane.showMessageDialog(null, "Debes ingresar el código de inventario del equipo a buscar", "METROFARM", JOptionPane.ERROR_MESSAGE);
                            } else {
                                DtoEquipo equipo = getEquipment();
                                if (equipo != null) {
                                    if (equipo.getFrecCalibracion().equals(Frecuencias.NOAPLICA.getCode())) {
                                        JOptionPane.showMessageDialog(null, "Este equipo no esta habilitado para calibraciones", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                        txtcodInv.setText("");
                                        txtCodmet.setText("");
                                        txtnumse.setText("");
                                        ventana.desbloquear();
                                        return null;
                                    }
                                    if (equipo.getCodigoMet().equals("") || equipo.getMarca().equals("") || equipo.getModelo().equals("") || equipo.getUbicacion().equals("")) {
                                        JOptionPane.showMessageDialog(null, "El equipo seleccionado se encuentra entre los equipos pendientes a registrar", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                        ventana.desbloquear();
                                        return null;
                                    }
                                    txtcodInv.setBackground(new java.awt.Color(204, 255, 204));
                                    txtcodInv.setText(equipo.getCodigoInv());
                                    txtcodInv.setEditable(false);
                                    txtCodmet.setText(equipo.getCodigoInv());
                                    txtCodmet.setBackground(new java.awt.Color(204, 255, 204));
                                    txtCodmet.setEditable(false);
                                    txtnumse.setText(equipo.getSerie());
                                    txtnumse.setBackground(new java.awt.Color(204, 255, 204));
                                    txtnumse.setEditable(false);
                                    txtnom.setText(equipo.getNombre());
                                    txtnom.setBackground(new java.awt.Color(204, 255, 204));
                                    txtUbicac.setText(equipo.getUbicacion());
                                    txtUbicac.setBackground(new java.awt.Color(204, 255, 204));
                                    txtMarca.setText(equipo.getMarca());
                                    txtMarca.setBackground(new java.awt.Color(204, 255, 204));
                                    txtModelo.setText(equipo.getModelo());
                                    txtModelo.setBackground(new java.awt.Color(204, 255, 204));
                                    txtEstado.setText(Estados.getByCode(equipo.getEstado()).getDescription());
                                    txtEstado.setBackground(new java.awt.Color(204, 255, 204));
                                    txtCliente.setText(equipo.getCliente());
                                    txtCliente.setBackground(new java.awt.Color(204, 255, 204));

                                    ImageIcon imagen = new ImageIcon(Base64.decodeBase64(equipo.getFoto()));
                                    imagen = AjustarUmagen(imagen);
                                    lblfoto.setIcon(imagen);
                                    equipoSeleccionado = equipo;
                                } else {
                                    JOptionPane.showMessageDialog(null, "No existe un equipo registrado con este código de inventario", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                    txtcodInv.setText("");
                                    txtCodmet.setText("");
                                    txtnumse.setText("");
                                    ventana.desbloquear();
                                    return null;
                                }
                            }
                            ventana.desbloquear();
                            return null;
                        }
                    };
                    worker.execute();

                }
            }
        });

        lblCodmet = new JLabel("* Còdigo de metrología");
        lblCodmet.setBounds(30, 65, 200, 20);
        lblCodmet.setFont(TLetra);
        lblCodmet.setForeground(Color.black);
        lblCodmet.setVisible(true);

        txtCodmet = new JTextField("");
        txtCodmet.setBounds(200, 65, 100, 20);
        txtCodmet.setFont(TLetra);
        txtCodmet.setForeground(Color.black);
        txtCodmet.setVisible(true);
        txtCodmet.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                util.anularTildes(e);
                int limite = 11;
                char caracter = e.getKeyChar();
                if (txtCodmet.getText().length() == limite) {
                    e.consume();
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Este campo solo puede tener como maximo 11 caracteres", "METROFARM", JOptionPane.ERROR_MESSAGE);
                }

                if ((caracter == KeyEvent.VK_ENTER)) {
                    ventana.bloquear(frame);
                    SwingWorker worker;
                    worker = new SwingWorker() {
                        @Override
                        protected Object doInBackground() throws Exception {
                            if (txtCodmet.getText().trim().equals("")) {
                                JOptionPane.showMessageDialog(null, "Debes ingresar el código de metrología del equipo a buscar", "METROFARM", JOptionPane.ERROR_MESSAGE);
                            } else {
                                DtoEquipo equipo = getEquipment();
                                if (equipo != null) {
                                    if (equipo.getFrecCalibracion().equals(Frecuencias.NOAPLICA.getCode())) {
                                        JOptionPane.showMessageDialog(null, "Este equipo no esta habilitado para calibraciones", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                        txtcodInv.setText("");
                                        txtCodmet.setText("");
                                        txtnumse.setText("");
                                        ventana.desbloquear();
                                        return null;
                                    }
                                    if (equipo.getCodigoMet().equals("") || equipo.getMarca().equals("") || equipo.getModelo().equals("") || equipo.getUbicacion().equals("")) {
                                        JOptionPane.showMessageDialog(null, "El equipo seleccionado se encuentra entre los equipos pendientes a registrar", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                        ventana.desbloquear();
                                        return null;
                                    }
                                    txtcodInv.setBackground(new java.awt.Color(204, 255, 204));
                                    txtcodInv.setText(equipo.getCodigoInv());
                                    txtcodInv.setEditable(false);
                                    txtCodmet.setText(equipo.getCodigoInv());
                                    txtCodmet.setBackground(new java.awt.Color(204, 255, 204));
                                    txtCodmet.setEditable(false);
                                    txtnumse.setText(equipo.getSerie());
                                    txtnumse.setBackground(new java.awt.Color(204, 255, 204));
                                    txtnumse.setEditable(false);
                                    txtnom.setText(equipo.getNombre());
                                    txtnom.setBackground(new java.awt.Color(204, 255, 204));
                                    txtUbicac.setText(equipo.getUbicacion());
                                    txtUbicac.setBackground(new java.awt.Color(204, 255, 204));
                                    txtMarca.setText(equipo.getMarca());
                                    txtMarca.setBackground(new java.awt.Color(204, 255, 204));
                                    txtModelo.setText(equipo.getModelo());
                                    txtModelo.setBackground(new java.awt.Color(204, 255, 204));
                                    txtEstado.setText(Estados.getByCode(equipo.getEstado()).getDescription());
                                    txtEstado.setBackground(new java.awt.Color(204, 255, 204));
                                    txtCliente.setText(equipo.getCliente());
                                    txtCliente.setBackground(new java.awt.Color(204, 255, 204));

                                    ImageIcon imagen = new ImageIcon(Base64.decodeBase64(equipo.getFoto()));
                                    imagen = AjustarUmagen(imagen);
                                    lblfoto.setIcon(imagen);
                                    equipoSeleccionado = equipo;
                                } else {
                                    JOptionPane.showMessageDialog(null, "No existe un equipo registrado con este código de metrologia", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                    txtcodInv.setText("");
                                    txtCodmet.setText("");
                                    txtnumse.setText("");
                                    ventana.desbloquear();
                                    return null;
                                }
                            }
                            ventana.desbloquear();
                            return null;
                        }
                    };
                    worker.execute();

                }
            }
        });

        lblNumserie = new JLabel("* Nùmero de serie");
        lblNumserie.setBounds(30, 95, 200, 20);
        lblNumserie.setFont(TLetra);
        lblNumserie.setForeground(Color.black);
        lblNumserie.setVisible(true);

        txtnumse = new JTextField("");
        txtnumse.setBounds(200, 95, 100, 20);
        txtnumse.setFont(TLetra);
        txtnumse.setForeground(Color.black);
        txtnumse.setVisible(true);
        txtnumse.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int limite = 11;
                util.anularTildes(e);
                char caracter = e.getKeyChar();
                if (txtnumse.getText().length() == limite) {
                    e.consume();
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Este campo solo puede tener como maximo 11 caracteres", "METROFARM", JOptionPane.ERROR_MESSAGE);
                }

                if ((caracter == KeyEvent.VK_ENTER)) {
                    ventana.bloquear(frame);
                    SwingWorker worker;
                    worker = new SwingWorker() {
                        @Override
                        protected Object doInBackground() throws Exception {
                            if (txtnumse.getText().trim().equals("")) {
                                JOptionPane.showMessageDialog(null, "Debes ingresar el número de serie del equipo a buscar", "METROFARM", JOptionPane.ERROR_MESSAGE);
                            } else {
                                DtoEquipo equipo = getEquipment();
                                if (equipo != null) {
                                    if (equipo.getFrecCalibracion().equals(Frecuencias.NOAPLICA.getCode())) {
                                        JOptionPane.showMessageDialog(null, "Este equipo no esta habilitado para calibraciones", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                        txtcodInv.setText("");
                                        txtCodmet.setText("");
                                        txtnumse.setText("");
                                        ventana.desbloquear();
                                        return null;
                                    }
                                    if (equipo.getCodigoMet().equals("") || equipo.getMarca().equals("") || equipo.getModelo().equals("") || equipo.getUbicacion().equals("")) {
                                        JOptionPane.showMessageDialog(null, "El equipo seleccionado se encuentra entre los equipos pendientes a registrar", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                        ventana.desbloquear();
                                        return null;
                                    }
                                    txtcodInv.setBackground(new java.awt.Color(204, 255, 204));
                                    txtcodInv.setText(equipo.getCodigoInv());
                                    txtcodInv.setEditable(false);
                                    txtCodmet.setText(equipo.getCodigoInv());
                                    txtCodmet.setBackground(new java.awt.Color(204, 255, 204));
                                    txtCodmet.setEditable(false);
                                    txtnumse.setText(equipo.getSerie());
                                    txtnumse.setBackground(new java.awt.Color(204, 255, 204));
                                    txtnumse.setEditable(false);
                                    txtnom.setText(equipo.getNombre());
                                    txtnom.setBackground(new java.awt.Color(204, 255, 204));
                                    txtUbicac.setText(equipo.getUbicacion());
                                    txtUbicac.setBackground(new java.awt.Color(204, 255, 204));
                                    txtMarca.setText(equipo.getMarca());
                                    txtMarca.setBackground(new java.awt.Color(204, 255, 204));
                                    txtModelo.setText(equipo.getModelo());
                                    txtModelo.setBackground(new java.awt.Color(204, 255, 204));
                                    txtEstado.setText(Estados.getByCode(equipo.getEstado()).getDescription());
                                    txtEstado.setBackground(new java.awt.Color(204, 255, 204));
                                    txtCliente.setText(equipo.getCliente());
                                    txtCliente.setBackground(new java.awt.Color(204, 255, 204));

                                    ImageIcon imagen = new ImageIcon(Base64.decodeBase64(equipo.getFoto()));
                                    imagen = AjustarUmagen(imagen);
                                    lblfoto.setIcon(imagen);
                                    equipoSeleccionado = equipo;

                                } else {
                                    JOptionPane.showMessageDialog(null, "No existe un equipo registrado con este código número de serie", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                    txtcodInv.setText("");
                                    txtCodmet.setText("");
                                    txtnumse.setText("");
                                    ventana.desbloquear();
                                    return null;
                                }
                            }
                            ventana.desbloquear();
                            return null;
                        }
                    };
                    worker.execute();

                }
            }
        });

        lblnom = new JLabel("* Nombre del equipo");
        lblnom.setBounds(30, 125, 200, 20);
        lblnom.setFont(TLetra);
        lblnom.setForeground(Color.black);
        lblnom.setVisible(true);

        txtnom = new JTextField("");
        txtnom.setBounds(200, 125, 200, 20);
        txtnom.setFont(TLetra);
        txtnom.setForeground(Color.black);
        txtnom.setVisible(true);
        txtnom.setEditable(false);

        lblubica = new JLabel("* Ubicaciòn");
        lblubica.setBounds(30, 155, 200, 20);
        lblubica.setFont(TLetra);
        lblubica.setForeground(Color.black);
        lblubica.setVisible(true);

        txtUbicac = new JTextField("");
        txtUbicac.setBounds(200, 155, 200, 20);
        txtUbicac.setFont(TLetra);
        txtUbicac.setForeground(Color.black);
        txtUbicac.setVisible(true);
        txtUbicac.setEditable(false);

        lblMarca = new JLabel("* Marca");
        lblMarca.setBounds(30, 185, 200, 20);
        lblMarca.setFont(TLetra);
        lblMarca.setForeground(Color.black);
        lblMarca.setVisible(true);

        txtMarca = new JTextField("");
        txtMarca.setBounds(200, 185, 200, 20);
        txtMarca.setFont(TLetra);
        txtMarca.setForeground(Color.black);
        txtMarca.setVisible(true);
        txtMarca.setEditable(false);

        lblModelo = new JLabel("* Modelo");
        lblModelo.setBounds(30, 215, 200, 20);
        lblModelo.setFont(TLetra);
        lblModelo.setForeground(Color.black);
        lblModelo.setVisible(true);

        txtModelo = new JTextField("");
        txtModelo.setBounds(200, 215, 200, 20);
        txtModelo.setFont(TLetra);
        txtModelo.setForeground(Color.black);
        txtModelo.setVisible(true);
        txtModelo.setEditable(false);

        lblEstado = new JLabel("* Estado");
        lblEstado.setBounds(30, 245, 200, 20);
        lblEstado.setFont(TLetra);
        lblEstado.setForeground(Color.black);
        lblEstado.setVisible(true);

        txtEstado = new JTextField("");
        txtEstado.setBounds(200, 245, 200, 20);
        txtEstado.setFont(TLetra);
        txtEstado.setForeground(Color.black);
        txtEstado.setVisible(true);
        txtEstado.setEditable(false);

        lblCliente = new JLabel("* Cliente");
        lblCliente.setBounds(30, 275, 200, 20);
        lblCliente.setFont(TLetra);
        lblCliente.setForeground(Color.black);
        lblCliente.setVisible(true);

        txtCliente = new JTextField("");
        txtCliente.setBounds(200, 275, 200, 20);
        txtCliente.setFont(TLetra);
        txtCliente.setForeground(Color.black);
        txtCliente.setVisible(true);
        txtCliente.setEditable(false);

        ////////////////////////////////////////
        lbldivi = new JLabel("* Divisiòn de escala");
        lbldivi.setBounds(30, 15, 200, 20);
        lbldivi.setFont(TLetra);
        lbldivi.setForeground(Color.black);
        lbldivi.setVisible(true);

        txtdiv = new JTextField("");
        txtdiv.setBounds(200, 15, 200, 20);
        txtdiv.setFont(TLetra);
        txtdiv.setForeground(Color.black);
        txtdiv.setVisible(true);
        txtdiv.setEditable(true);
        txtdiv.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                UtilFarmatech.controlarLongitudText(e, txtdiv, 10);
            }
        });

        lblexac = new JLabel("* Exactitud");
        lblexac.setBounds(30, 45, 200, 20);
        lblexac.setFont(TLetra);
        lblexac.setForeground(Color.black);
        lblexac.setVisible(true);

        txtexac = new JTextField("");
        txtexac.setBounds(200, 45, 200, 20);
        txtexac.setFont(TLetra);
        txtexac.setForeground(Color.black);
        txtexac.setVisible(true);
        txtexac.setEditable(true);
        txtexac.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                UtilFarmatech.controlarLongitudText(e, txtexac, 10);
            }
        });

        lblvalmin = new JLabel("* Valor minimo");
        lblvalmin.setBounds(30, 75, 200, 20);
        lblvalmin.setFont(TLetra);
        lblvalmin.setForeground(Color.black);
        lblvalmin.setVisible(true);

        txtvalmin = new JTextField("");
        txtvalmin.setBounds(200, 75, 200, 20);
        txtvalmin.setFont(TLetra);
        txtvalmin.setForeground(Color.black);
        txtvalmin.setVisible(true);
        txtvalmin.setEditable(true);
        txtvalmin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                UtilFarmatech.controlarLongitudText(e, txtvalmin, 10);
            }
        });

        lblvalmax = new JLabel("* Valor màximo");
        lblvalmax.setBounds(30, 105, 200, 20);
        lblvalmax.setFont(TLetra);
        lblvalmax.setForeground(Color.black);
        lblvalmax.setVisible(true);

        txtvalmax = new JTextField("");
        txtvalmax.setBounds(200, 105, 200, 20);
        txtvalmax.setFont(TLetra);
        txtvalmax.setForeground(Color.black);
        txtvalmax.setVisible(true);
        txtvalmax.setEditable(true);

        txtvalmax.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                UtilFarmatech.controlarLongitudText(e, txtvalmax, 10);
            }
        });

        lblrangcal = new JLabel("* Rango de calibraciòn");
        lblrangcal.setBounds(30, 135, 200, 20);
        lblrangcal.setFont(TLetra);
        lblrangcal.setForeground(Color.black);
        lblrangcal.setVisible(true);

        txtrangcal = new JTextField("");
        txtrangcal.setBounds(200, 135, 200, 20);
        txtrangcal.setFont(TLetra);
        txtrangcal.setForeground(Color.black);
        txtrangcal.setVisible(true);
        txtrangcal.setEditable(true);

        txtrangcal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                UtilFarmatech.controlarLongitudText(e, txtrangcal, 10);
            }
        });

        lblrantra = new JLabel("* Rango de trabajo");
        lblrantra.setBounds(30, 165, 200, 20);
        lblrantra.setFont(TLetra);
        lblrantra.setForeground(Color.black);
        lblrantra.setVisible(true);

        txtrantra = new JTextField("");
        txtrantra.setBounds(200, 165, 200, 20);
        txtrantra.setFont(TLetra);
        txtrantra.setForeground(Color.black);
        txtrantra.setVisible(true);
        txtrantra.setEditable(true);
        txtrantra.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                UtilFarmatech.controlarLongitudText(e, txtrantra, 10);
            }
        });

        lblPrecio = new JLabel("* Gasto total                       COL$");
        lblPrecio.setBounds(30, 195, 200, 20);
        lblPrecio.setFont(TLetra);
        lblPrecio.setForeground(Color.black);
        lblPrecio.setVisible(true);

        txtprecio = new JTextField("");
        txtprecio.setText("0");
        txtprecio.setBounds(200, 195, 200, 20);
        txtprecio.setFont(TLetra);
        txtprecio.setForeground(Color.black);
        txtprecio.setVisible(true);
        txtprecio.setEditable(true);
        txtprecio.addMouseListener(this);
        txtprecio.addKeyListener(new KeyAdapter() {
            //Validacion Telefono
            @Override
            public void keyTyped(KeyEvent e) {
                boolean coma = false;
                char caracter = e.getKeyChar();
                if (((caracter < '0')
                        || (caracter > '9'))
                        && (caracter != KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    e.consume();
                    JOptionPane.showMessageDialog(null, "Solo se permiten números", "METROFARM", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        txtprecio.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                txtprecio.setText(util.makeGroupingNumber(txtprecio.getText()));
            }
        });

        txaProovedores = new JTextArea("");
        txaProovedores.setBounds(150, 225, 280, 80);
        txaProovedores.setEditable(false);
        txaProovedores.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txaProovedores.setBackground(Color.white);
        txaProovedores.setFont(TLetra);
        txaProovedores.setForeground(Color.BLACK);
        txaProovedores.setVisible(true);
        txaProovedores.setLineWrap(true);//Provocar salto de linea cortando palabra
        txaProovedores.setWrapStyleWord(true);//Provocar salto de linea despues del espacio

        PanelGeneral1.add(Panelinfo2);
        PanelGeneral1.add(Panelinfo);
        PanelGeneral1.add(PanelFoto);
        PanelGeneral1.add(btnGuardar);
        PanelGeneral1.add(btnNuevo);
        PanelGeneral1.add(btnSalir);
        PanelGeneral1.add(btnEliminar);
        PanelGeneral1.add(btnActualizar);
        PanelGeneral1.add(btnCalDelMes);
        PanelGeneral1.add(btnCalVencidos);
        PanelGeneral1.add(PanelDatos);
        PanelGeneral1.add(btnsacartabla);
        PanelGeneral1.add(mostraequipos);
        PanelGeneral1.add(lblAdjuntar);
        PanelGeneral1.add(lblCancelar);
        PanelGeneral1.add(lblVer);
        PanelGeneral1.add(LblHistorial);
        PanelGeneral1.add(panelPaginador);

        Panelinfo.add(lblCal);
        Panelinfo.add(txtCal);
        Panelinfo.add(btnBuscar);
        Panelinfo.add(btnBuscarInv);
        Panelinfo.add(btnBuscaridMetr);
        Panelinfo.add(btnBuscarSerie);
        Panelinfo.add(lblCodinv);
        Panelinfo.add(lblCodmet);
        Panelinfo.add(lblNumserie);
        Panelinfo.add(lblnom);
        Panelinfo.add(lblubica);
        Panelinfo.add(lblMarca);
        Panelinfo.add(lblModelo);
        Panelinfo.add(lblEstado);
        Panelinfo.add(lblCliente);
        Panelinfo.add(txtcodInv);
        Panelinfo.add(txtCodmet);
        Panelinfo.add(txtnumse);
        Panelinfo.add(txtnom);
        Panelinfo.add(txtUbicac);
        Panelinfo.add(txtMarca);
        Panelinfo.add(txtModelo);
        Panelinfo.add(txtEstado);
        Panelinfo.add(txtCliente);

        Panelinfo2.add(lbldivi);
        Panelinfo2.add(txtdiv);
        Panelinfo2.add(lblexac);
        Panelinfo2.add(txtexac);
        Panelinfo2.add(lblvalmin);
        Panelinfo2.add(txtvalmin);
        Panelinfo2.add(lblvalmax);
        Panelinfo2.add(txtvalmax);
        Panelinfo2.add(lblrangcal);
        Panelinfo2.add(txtrangcal);
        Panelinfo2.add(lblrantra);
        Panelinfo2.add(txtrantra);
        Panelinfo2.add(btnProovedores);
        Panelinfo2.add(txaProovedores);
        Panelinfo2.add(lblPrecio);
        Panelinfo2.add(txtprecio);
        
        panelPaginador.getBtnBack().addActionListener(this);
        panelPaginador.getBtnBackward().addActionListener(this);
        panelPaginador.getBtnNext().addActionListener(this);
        panelPaginador.getBtnForward().addActionListener(this);

        pageData.setFirstQuery(true);
        ventana.bloquear(this);
        SwingWorker worker;
        worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                pageData.setCurrentPage(1);
                vaciar();
                llenarDatos(pageData);
                ventana.desbloquear();
                return null;
            }
        };
        worker.execute();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSalir) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea salir?", "METROFARM", JOptionPane.YES_NO_OPTION);
            switch (respuesta) {
                case JOptionPane.YES_OPTION:
                    this.dispose();
                    break;
                case JOptionPane.NO_OPTION:
                    break;
            }
        }

        if (e.getSource() == btnNuevo) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    Archivo = null;
                    Ruta = "";
                    txtCal.setText("");
                    lblAdjuntar.setVisible(true);
                    lblCancelar.setVisible(false);
                    lblVer.setVisible(false);
                    txtcodInv.setText("");
                    txtcodInv.setBackground(Color.WHITE);
                    txtcodInv.setEditable(true);
                    txtCodmet.setText("");
                    txtCodmet.setBackground(Color.WHITE);
                    txtCodmet.setEditable(true);
                    txtMarca.setText("");
                    txtMarca.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                    txtnumse.setText("");
                    txtnumse.setBackground(Color.WHITE);
                    txtnumse.setEditable(true);
                    txtnom.setText("");
                    txtnom.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                    txtUbicac.setText("");
                    txtUbicac.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                    txtCliente.setText("");
                    txtCliente.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                    txtModelo.setText("");
                    txtModelo.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                    txtEstado.setText("");
                    txtEstado.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                    lblfoto.setIcon(null);
                    txtdiv.setText("");
                    txtexac.setText("");
                    txtvalmin.setText("");
                    txtvalmax.setText("");
                    txtrangcal.setText("");
                    txtrantra.setText("");
                    txaProovedores.setText("");
                    txaProovedores.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                    txtprecio.setText("0");
                    TablaDatos.setBackground(Color.WHITE);
                    btnGuardar.setEnabled(true);
                    panelPaginador.enablePageButtons();
                    pageData.setNumRows(0);
                    vaciar();
                    llenarDatos(pageData);
                    repaint();
                    ventana.desbloquear();
                    equipoSeleccionado = null;
                    return null;
                }
            };
            worker.execute();
        }

        if (e.getSource() == btnGuardar) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    String proveedor, div, exac, valmin, valmax,
                            rangcal, rangtra;

                    BigDecimal precio;
                    div = txtdiv.getText();
                    exac = txtexac.getText();
                    valmin = txtvalmin.getText();
                    valmax = txtvalmax.getText();
                    rangcal = txtrangcal.getText();
                    rangtra = txtrantra.getText();
                    proveedor = txaProovedores.getText();


                    if (!"".equals(Ruta)) {
                        Archivo = new File(Ruta);
                    }

                    boolean valido = true;

                    if (txtcodInv.getText().equals("")) {
                        valido = false;
                    }
                    if (txtCodmet.getText().equals("")) {
                        valido = false;
                    }
                    if (txtnumse.getText().equals("")) {
                        valido = false;
                    }

                    if (txtnom.getText().equals("")) {
                        valido = false;
                    }

                    if (txtUbicac.getText().equals("")) {
                        valido = false;
                    }

                    if (txtMarca.getText().equals("")) {
                        valido = false;
                    }
                    if (txtModelo.getText().equals("")) {
                        valido = false;
                    }
                    if (txtEstado.getText().equals("")) {
                        valido = false;
                    }
                    if (txtCliente.getText().equals("")) {
                        valido = false;
                    }
                    if (div.equals("")) {
                        valido =
                                false;
                    }
                    if (exac.equals("")) {
                        valido = false;
                    }
                    if (valmin.equals("")) {
                        valido = false;
                    }
                    if (valmax.equals("")) {
                        valido = false;
                    }
                    if (rangcal.equals("")) {
                        valido = false;
                    }
                    if (rangtra.equals("")) {
                        valido = false;
                    }
                    if (txtprecio.getText().equals("") || txtprecio.getText().equals("0")) {
                        precio = new BigDecimal(0.0);
                    } else {
                        precio = new BigDecimal(txtprecio.getText().replace(",", "").replace("'", ""));
                    }

                    if (valido == true) {

                        Date fechaActual = new Date();
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(fechaActual);
                        cal.add(Calendar.MONTH, equipoSeleccionado.getFrecCalibracion().intValue()); //Adding 1 month to current date
                        Date fechaProxima = cal.getTime();



                        if (Archivo == null) {
                            JOptionPane.showMessageDialog(null, "Es obligatorio adjuntar un documento a esta calibración", "METROFARM", JOptionPane.INFORMATION_MESSAGE);

                        } else {
                            DtoCalibracion calibation = new DtoCalibracion();
                            calibation.setEquipo(equipoSeleccionado);
                            calibation.setDivision(div);
                            calibation.setExactitud(exac);
                            calibation.setFechaRegistro(fechaActual);
                            calibation.setPrecio(precio);
                            calibation.setProveedor(proveedor);
                            calibation.setProximaCalibracion(fechaProxima);
                            calibation.setRangCalibracion(rangcal);
                            calibation.setRangTrabajo(rangtra);
                            calibation.setValMaximo(valmax);
                            calibation.setValMininmo(valmin);
                            calibation.setArchivo(FileUtils.readFileToByteArray(Archivo));

                            boolean calibracionRegister = cmdCalibration.createCalibrationAndSchedule(calibation);

                            if (calibracionRegister) {
                                Archivo = null;
                                equipoSeleccionado = null;
                                Ruta = "";
                                lblAdjuntar.setVisible(true);
                                lblCancelar.setVisible(false);
                                lblVer.setVisible(false);
                                txtcodInv.setText("");
                                txtcodInv.setBackground(Color.WHITE);
                                txtcodInv.setEditable(true);
                                txtCodmet.setText("");
                                txtCodmet.setBackground(Color.WHITE);
                                txtCodmet.setEditable(true);
                                txtMarca.setText("");
                                txtMarca.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                txtnumse.setText("");
                                txtnumse.setBackground(Color.WHITE);
                                txtnumse.setEditable(true);
                                txtnom.setText("");
                                txtnom.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                txtUbicac.setText("");
                                txtUbicac.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                txtCliente.setText("");
                                txtCliente.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                txtModelo.setText("");
                                txtModelo.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                txtEstado.setText("");
                                txtEstado.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                lblfoto.setIcon(null);
                                txtdiv.setText("");
                                txtexac.setText("");
                                txtvalmin.setText("");
                                txtvalmax.setText("");
                                txtrangcal.setText("");
                                txtrantra.setText("");
                                txaProovedores.setText("");
                                txaProovedores.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                txtprecio.setText("0");
                                JOptionPane.showMessageDialog(null, " Calibracion ingresada",
                                        "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                                panelPaginador.enablePageButtons();
                                pageData.setNumRows(0);
                                vaciar();
                                llenarDatos(pageData);
                                updateNotifications();

                            } else {
                                JOptionPane.showMessageDialog(null, " No fue posible ingresar la nueva calibración.", "METROFARM", JOptionPane.ERROR_MESSAGE);

                            }

                        }

                    } else {
                        JOptionPane.showMessageDialog(null, " Los campos marcados con * son obligatorios.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();
        }

        if (e.getSource() == btnActualizar) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    DtoCalibracion selected = new DtoCalibracion();
                    String codigocal, proveedor, div, exac, valmin, valmax,
                            rangcal, rangtra;
                    codigocal = txtCal.getText().trim();
                    BigDecimal precio = new BigDecimal(0.0);
                    div = txtdiv.getText();
                    exac = txtexac.getText();
                    valmin = txtvalmin.getText();
                    valmax = txtvalmax.getText();
                    rangcal = txtrangcal.getText();
                    rangtra = txtrantra.getText();
                    proveedor = txaProovedores.getText();

                    if (!Ruta.equals("")) {
                        Archivo = new File(Ruta);
                    } else {
                        if (TablaDatos.getSelectedRow() > -1) {
                            selected = ((DtoCalibracion) TablaDatos.getValueAt(TablaDatos.getSelectedRow(), 12));

                        }
                    }

                    if (txtCal.getText().equals("")) {
                        JOptionPane.showMessageDialog(null,
                                " Debe seleccionar una calibración valida.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                    } else {

                        boolean valido = true;
                        if (txtCal.getText().equals("")) {
                            valido = false;
                        }
                        if (txtcodInv.getText().equals("")) {
                            valido = false;
                        }
                        if (txtCodmet.getText().equals("")) {
                            valido = false;
                        }
                        if (txtnumse.getText().equals("")) {
                            valido = false;
                        }

                        if (txtnom.getText().equals("")) {
                            valido = false;
                        }

                        if (txtUbicac.getText().equals("")) {
                            valido = false;
                        }

                        if (txtMarca.getText().equals("")) {
                            valido = false;
                        }
                        if (txtModelo.getText().equals("")) {
                            valido = false;
                        }
                        if (txtEstado.getText().equals("")) {
                            valido = false;
                        }
                        if (txtCliente.getText().equals("")) {
                            valido = false;
                        }
                        if (div.equals("")) {
                            valido = false;
                        }
                        if (exac.equals("")) {
                            valido = false;
                        }
                        if (valmin.equals("")) {
                            valido = false;
                        }
                        if (valmax.equals("")) {
                            valido = false;
                        }
                        if (rangcal.equals("")) {
                            valido = false;
                        }
                        if (rangtra.equals("")) {
                            valido = false;
                        }
                        if (txtprecio.getText().equals("") || txtprecio.getText().equals("0")) {
                            precio = new BigDecimal(0.0);
                        } else {
                            precio = new BigDecimal(txtprecio.getText().replace(",", "").replace("'", ""));
                        }

                        if (valido == true) {

                            DtoCalibracion calibation = new DtoCalibracion();
                            calibation.setCodigoCalibracion(codigocal);
                            calibation.setDivision(div);
                            calibation.setExactitud(exac);
                            calibation.setPrecio(precio);
                            calibation.setProveedor(proveedor);
                            calibation.setRangCalibracion(rangcal);
                            calibation.setRangTrabajo(rangtra);
                            calibation.setValMaximo(valmax);
                            calibation.setValMininmo(valmin);
                            if (Archivo != null) {
                                calibation.setArchivo(FileUtils.readFileToByteArray(Archivo));
                            } else {
                                calibation.setArchivo(selected.getArchivo());
                            }


                            boolean calibracionRegister = cmdCalibration.updateCalibration(calibation);

                            if (calibracionRegister == false) {
                                JOptionPane.showMessageDialog(null,
                                        " No fue posible actualizar la información de la calibración.", "METROFARM", JOptionPane.ERROR_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, " Calibración actualizado", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                                Ruta = "";
                                Archivo = null;
                                Ruta = "";
                                txtCal.setText("");
                                lblAdjuntar.setVisible(true);
                                lblCancelar.setVisible(false);
                                lblVer.setVisible(false);
                                txtcodInv.setText("");
                                txtcodInv.setBackground(Color.WHITE);
                                txtcodInv.setEditable(true);
                                txtCodmet.setText("");
                                txtCodmet.setBackground(Color.WHITE);
                                txtCodmet.setEditable(true);
                                txtMarca.setText("");
                                txtMarca.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                txtnumse.setText("");
                                txtnumse.setBackground(Color.WHITE);
                                txtnumse.setEditable(true);
                                txtnom.setText("");
                                txtnom.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                txtUbicac.setText("");
                                txtUbicac.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                txtCliente.setText("");
                                txtCliente.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                txtModelo.setText("");
                                txtModelo.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                txtEstado.setText("");
                                txtEstado.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                lblfoto.setIcon(null);
                                txtdiv.setText("");
                                txtexac.setText("");
                                txtvalmin.setText("");
                                txtvalmax.setText("");
                                txtrangcal.setText("");
                                txtrantra.setText("");
                                txaProovedores.setText("");
                                txaProovedores.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                txtprecio.setText("0");
                                panelPaginador.enablePageButtons();
                                pageData.setNumRows(0);
                                vaciar();
                                llenarDatos(pageData);

                            }
                        } else {
                            JOptionPane.showMessageDialog(null, " Los campos marcados con * son obligatorios.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();
        }

        if (e.getSource() == btnBuscar) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    String Codigo;
                    Codigo = txtCal.getText();
                    if (Codigo.trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, " Debes ingresar un número de calibración válido.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        DtoCalibracion calibracion = new DtoCalibracion();
                        calibracion.setCodigoCalibracion(Codigo);
                        ArrayList<DtoCalibracion> calibraciones = cmdCalibration.findCalibrations(calibracion);
                        if (!calibraciones.isEmpty()) {
                            vaciar();
                            setDataModel(getCalibracion(calibraciones, getDataModel()));
                        } else {
                            JOptionPane.showMessageDialog(null, " No existe una calibración registrada con este código.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();
        }

        if (e.getSource() == btnEliminar) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    if (TablaDatos.getSelectedRow() > -1) {

                        DtoCalibracion calibracionSeleccionada = (DtoCalibracion) TablaDatos.getValueAt(TablaDatos.getSelectedRow(), 12);
                        if (calibracionSeleccionada.getFechaEjecutada() != null) {
                            JOptionPane.showMessageDialog(null, " No se puede eliminar la calibración de este equipo, ya que se han ingresado calibraciones posteriores.\n"
                                    + "Código de inventario :" + calibracionSeleccionada.getEquipo().getCodigoInv()
                                    + "Nombre del equipo    :" + calibracionSeleccionada.getEquipo().getNombre()
                                    + "Fecha próxima calibración programada:" + GlobalUtils.getDateFormatted(calibracionSeleccionada.getProximaCalibracion())
                                    + "Fecha ejecución:" + GlobalUtils.getDateFormatted(calibracionSeleccionada.getFechaEjecutada())
                                    + "\n NOTA:Recuerde que solo es posible eliminar la última calibración ejecutada para un equipo,\n y esta acción afectará el cumplimiento de ejecución de la calibración anterior.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);

                        } else {
                            int respuesta = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar la calibración", "METROFARM",
                                    JOptionPane.YES_NO_OPTION);
                            switch (respuesta) {
                                case JOptionPane.YES_OPTION:

                                    boolean result = cmdCalibration.deleteCalibration(calibracionSeleccionada);
                                    if (result == false) {
                                        JOptionPane.showMessageDialog(null, " No se pudo eliminar la calibración.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        Ruta = "";
                                        lblAdjuntar.setVisible(true);
                                        lblCancelar.setVisible(false);
                                        lblVer.setVisible(false);
                                        txtCal.setText("");
                                        txtcodInv.setText("");
                                        txtcodInv.setBackground(Color.WHITE);
                                        txtcodInv.setEditable(true);
                                        txtCodmet.setText("");
                                        txtCodmet.setBackground(Color.WHITE);
                                        txtCodmet.setEditable(true);
                                        txtMarca.setText("");
                                        txtMarca.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                        txtnumse.setText("");
                                        txtnumse.setBackground(Color.WHITE);
                                        txtnumse.setEditable(true);
                                        txtnom.setText("");
                                        txtnom.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                        txtUbicac.setText("");
                                        txtUbicac.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                        txtCliente.setText("");
                                        txtCliente.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                        txtModelo.setText("");
                                        txtModelo.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                        txtEstado.setText("");
                                        txtEstado.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                        lblfoto.setIcon(null);
                                        txtdiv.setText("");
                                        txtexac.setText("");
                                        txtvalmin.setText("");
                                        txtvalmax.setText("");
                                        txtrangcal.setText("");
                                        txtrantra.setText("");
                                        txaProovedores.setText("");
                                        txaProovedores.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                        txtprecio.setText("0");
                                        JOptionPane.showMessageDialog(null, " Calibración eliminada", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                                        panelPaginador.enablePageButtons();
                                        vaciar();
                                        llenarDatos(pageData);
                                        
                                    }

                                case JOptionPane.NO_OPTION:
                                    break;
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Recuerda que debes seleccionar la calibración a eliminar",
                                "METROFARM", JOptionPane.ERROR_MESSAGE);
                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();
        }

        if (e.getSource() == btnBuscarInv) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    String codigoinv;
                    codigoinv = txtcodInv.getText();
                    if (codigoinv.trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, " Debe digitar el código de inventario del equipo que desea buscar.");
                    } else {
                        DtoEquipo equipo = new DtoEquipo();
                        equipo.setCodigoInv(codigoinv);
                        ObjectResponse response = cmdCalibration.findCalibrations(equipo,null);
                        ArrayList<DtoCalibracion> calibraciones=(ArrayList<DtoCalibracion>)response.getRecords();
                         if (calibraciones==null || !calibraciones.isEmpty()) {
                            vaciar();
                            setDataModel(getCalibracion(calibraciones, getDataModel()));
                        } else {
                            JOptionPane.showMessageDialog(null, " No se encontraron datos con la informacion ingresada.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    ventana.desbloquear();
                    return null;
                }
            };

            worker.execute();
        }

        if (e.getSource() == btnBuscaridMetr) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    String codigomet;
                    codigomet = txtCodmet.getText();
                    if (codigomet.trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, " Debe digitar el código de metrología del equipo que desea buscar.");
                    } else {
                        DtoEquipo equipo = new DtoEquipo();
                        equipo.setCodigoMet(codigomet);
                        ObjectResponse response = cmdCalibration.findCalibrations(equipo,null);
                        ArrayList<DtoCalibracion> calibraciones=(ArrayList<DtoCalibracion>)response.getRecords();
                         if (calibraciones==null || !calibraciones.isEmpty()) {
                            vaciar();
                            setDataModel(getCalibracion(calibraciones, getDataModel()));
                        } else {
                            JOptionPane.showMessageDialog(null, " No se encontraron datos con la informacion ingresada.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    ventana.desbloquear();
                    return null;
                }
            };

            worker.execute();
        }

        if (e.getSource() == btnBuscarSerie) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    String serie;
                    serie = txtnumse.getText();
                    if (serie.trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, " Debe digitar el número de serie del equipo que desea buscar.");
                    } else {
                        DtoEquipo equipo = new DtoEquipo();
                        equipo.setSerie(serie);
                        ObjectResponse response = cmdCalibration.findCalibrations(equipo,null);
                        ArrayList<DtoCalibracion> calibraciones=(ArrayList<DtoCalibracion>)response.getRecords();
                         if (calibraciones==null || !calibraciones.isEmpty()) {
                            vaciar();
                            setDataModel(getCalibracion(calibraciones, getDataModel()));
                        } else {
                            JOptionPane.showMessageDialog(null, " No se encontraron datos con la informacion ingresada.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();
        }

        if (e.getSource() == btnProovedores) {
            txaProovedores.setText("");
            new ListaProovedorCal(this).setVisible(true);
            this.repaint();
        }

        if (e.getSource() == btnsacartabla) {
            TablaEquipos tab = new TablaEquipos(this);
            tab.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

        if (e.getSource() == btnCalDelMes) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    ArrayList<DtoCalibracion> calibrationsMonth = cmdCalibration.findCalibrationsMonthScheduled(new DtoEquipo());

                    if (calibrationsMonth != null && !calibrationsMonth.isEmpty()) {
                        vaciar();
                        setDataModel(getCalibracion(calibrationsMonth, getDataModel()));
                        TablaDatos.setBackground(Color.yellow);
                    } else {
                        JOptionPane.showMessageDialog(null, " No se encontraron calibraciones para este mes.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();
        }

        if (e.getSource() == btnCalVencidos) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    ArrayList<DtoCalibracion> calibrationsMonthExpired = cmdCalibration.findCalibrationsMonthExpired(new DtoEquipo());

                    if (calibrationsMonthExpired != null && !calibrationsMonthExpired.isEmpty()) {
                        vaciar();
                        setDataModel(getCalibracion(calibrationsMonthExpired, getDataModel()));
                        TablaDatos.setBackground(Color.red);
                    } else {
                        JOptionPane.showMessageDialog(null, " No se encontraron calibraciones vencidas.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();

        }
        
                if (e.getSource() == panelPaginador.getBtnNext()) {
            ventana.bloquear(this);
            SwingWorker worker;
            pageData.setFirstQuery(true);
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    pageData.nextPage();
                    if (pageData.getMaxPages() == pageData.getCurrentPage()) {
                        panelPaginador.getBtnForward().setEnabled(false);
                        panelPaginador.getBtnNext().setEnabled(false);
                    }
                    vaciar();
                    llenarDatos(pageData);
                    panelPaginador.setPagText(pageData.getCurrentPage() + " de " + pageData.getMaxPages());
                    if (pageData.getMaxPages() > 1) {
                        panelPaginador.getBtnBackward().setEnabled(true);
                        panelPaginador.getBtnBack().setEnabled(true);
                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();
        }

        if (e.getSource() == panelPaginador.getBtnBack()) {
            ventana.bloquear(this);
            SwingWorker worker;
            pageData.setFirstQuery(true);
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    pageData.previousPage();
                    if (pageData.getCurrentPage() == 1) {
                        panelPaginador.getBtnBackward().setEnabled(false);
                        panelPaginador.getBtnBack().setEnabled(false);
                    }
                    vaciar();
                    llenarDatos(pageData);
                    panelPaginador.setPagText(pageData.getCurrentPage() + " de " + pageData.getMaxPages());
                    if (pageData.getMaxPages() > 1) {
                        panelPaginador.getBtnForward().setEnabled(true);
                        panelPaginador.getBtnNext().setEnabled(true);
                    }

                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();

        }

        if (e.getSource() == panelPaginador.getBtnBackward()) {
            ventana.bloquear(this);
            SwingWorker worker;
            pageData.setFirstQuery(true);
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    pageData.setCurrentPage(1);
                    panelPaginador.getBtnBackward().setEnabled(false);
                    panelPaginador.getBtnBack().setEnabled(false);
                    vaciar();
                    llenarDatos(pageData);
                    panelPaginador.setPagText(pageData.getCurrentPage() + " de " + pageData.getMaxPages());
                    if (pageData.getMaxPages() > 1) {
                        panelPaginador.getBtnForward().setEnabled(true);
                        panelPaginador.getBtnNext().setEnabled(true);
                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();
        }

        if (e.getSource() == panelPaginador.getBtnForward()) {
            ventana.bloquear(this);
            SwingWorker worker;
            pageData.setFirstQuery(true);
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    pageData.setCurrentPage(pageData.getMaxPages());
                    panelPaginador.getBtnForward().setEnabled(false);
                    panelPaginador.getBtnNext().setEnabled(false);
                    vaciar();
                    llenarDatos(pageData);
                    panelPaginador.setPagText(pageData.getCurrentPage() + " de " + pageData.getMaxPages());
                    if (pageData.getMaxPages() > 1) {
                        panelPaginador.getBtnBackward().setEnabled(true);
                        panelPaginador.getBtnBack().setEnabled(true);
                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();
        }
        
    }

    public void llenarDatos(PageData pageData) {
        ObjectResponse response = cmdCalibration.findCalibrations(new DtoEquipo(),pageData);
        ArrayList<DtoCalibracion> calibraciones = (ArrayList<DtoCalibracion>)response.getRecords();
        this.pageData = response.getpageResponse();
        setDataModel(getCalibracion(calibraciones, getDataModel()));
        TablaDatos.setBackground(Color.white);
    }

    public void vaciar() {
        TablaDatos.setBackground(Color.white);
        int i =
                getDataModel().getRowCount();
        while (i > 0) {
            getDataModel().removeRow(0);
            i--;
        }
    }

    public void setDataModel(DefaultTableModel dataModel) {
        this.dataModel =
                dataModel;
    }

    public DefaultTableModel getDataModel() {
        return dataModel;
    }

    public ImageIcon AjustarUmagen(ImageIcon icon) {
        ImageIcon adaptada;

        if (icon.getIconWidth() > 90) {
            adaptada = new ImageIcon(icon.getImage().
                    getScaledInstance(250, -1, Image.SCALE_DEFAULT));

        } else {
            adaptada = icon;
        }
        return adaptada;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == TablaDatos) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    int fila;
                    try {
                        fila = TablaDatos.getSelectedRow();
                        TablaDatos.setSelectionBackground(new Color(Integer.parseInt("015289", 16)));
                        if (fila > -1) {
                            btnGuardar.setEnabled(false);
                            DtoCalibracion calibracionSeleccionada = (DtoCalibracion) TablaDatos.getValueAt(fila, 12);
                            try {
                                if (calibracionSeleccionada.getRangTrabajo() != null && calibracionSeleccionada.getRangTrabajo().isEmpty()
                                        && calibracionSeleccionada.getRangCalibracion() != null && calibracionSeleccionada.getRangCalibracion().isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Este equipo fue registrado desde primeras calibraciones.\n Por favor actualice la información adicional de la calibración.", "MetroFarm", JOptionPane.WARNING_MESSAGE);
                                }
                                txtCal.setText(calibracionSeleccionada.getCodigoCalibracion());
                                txtCal.setEditable(false);
                                txtcodInv.setText(calibracionSeleccionada.getEquipo().getCodigoInv());
                                txtcodInv.setEditable(false);
                                txtCodmet.setText(calibracionSeleccionada.getEquipo().getCodigoMet());
                                txtCodmet.setEditable(false);
                                txtnumse.setText(calibracionSeleccionada.getEquipo().getSerie());
                                txtnumse.setEditable(false);
                                txtnom.setText(calibracionSeleccionada.getEquipo().getNombre());
                                txtUbicac.setText(calibracionSeleccionada.getEquipo().getUbicacion());
                                txtMarca.setText(calibracionSeleccionada.getEquipo().getMarca());
                                txtModelo.setText(calibracionSeleccionada.getEquipo().getModelo());
                                txtEstado.setText(Estados.getByCode(calibracionSeleccionada.getEquipo().getEstado()).getDescription());
                                txtCliente.setText(calibracionSeleccionada.getEquipo().getCliente());
                                txtdiv.setText(calibracionSeleccionada.getDivision());
                                txtexac.setText(calibracionSeleccionada.getExactitud());
                                txtvalmax.setText(calibracionSeleccionada.getValMaximo());
                                txtvalmin.setText(calibracionSeleccionada.getValMininmo());
                                txtrangcal.setText(calibracionSeleccionada.getRangCalibracion());
                                txtrantra.setText(calibracionSeleccionada.getRangTrabajo());
                                txaProovedores.setText(calibracionSeleccionada.getProveedor());
                                txtprecio.setText(util.makeGroupingNumber(calibracionSeleccionada.getPrecio().toString()));

                                if (calibracionSeleccionada.getArchivo() != null && calibracionSeleccionada.getArchivo().length > 0) {
                                    lblVer.setVisible(true);
                                    createTemporalFile(calibracionSeleccionada.getArchivo());
                                } else {
                                    lblVer.setVisible(false);
                                    Archivo = null;
                                }

                                lblCancelar.setVisible(false);
                                lblAdjuntar.setVisible(true);
                                Ruta = "";

                                ImageIcon imagen = new ImageIcon(Base64.decodeBase64(calibracionSeleccionada.getEquipo().getFoto()));
                                imagen = AjustarUmagen(imagen);
                                lblfoto.setIcon(imagen);

                            } catch (Exception exe) {
                                lblfoto.setIcon(null);
                                JOptionPane.showMessageDialog(null, "Hubo un error :" + exe.getMessage(),
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Hubo un error :" + ex.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();

        }

        if (e.getSource() == lblAdjuntar) {

            this.Ruta = "";
            FileNameExtensionFilter filtrer = new FileNameExtensionFilter("Archivos pdf", "pdf");
            elegir.setFileFilter(filtrer);
            elegir.setMultiSelectionEnabled(false);
            elegir.setAcceptAllFileFilterUsed(false);
            int returnVal = elegir.showDialog(Calibracion.this, "Cargar");

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file =
                        elegir.getSelectedFile();
                String direccion = file.getPath();
                this.Ruta =
                        direccion;

                JOptionPane.showMessageDialog(null, "usted ha cargado "
                        + file.getName());
                lblAdjuntar.setVisible(false);
                lblCancelar.setVisible(true);

            } else {
                System.out.println("Carga cancelada por el usuario");
            }

        }
        if (e.getSource() == lblCancelar) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    Ruta = "";
                    int respuesta = JOptionPane.showConfirmDialog(null, "Esta seguro que desea cancelar la carga de este archivo", "METROFARM", JOptionPane.YES_NO_OPTION);
                    switch (respuesta) {
                        case JOptionPane.YES_OPTION:
                            lblCancelar.setVisible(false);
                            lblAdjuntar.setVisible(true);
                            break;
                        case JOptionPane.NO_OPTION:
                            break;

                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();
        }
        if (e.getSource() == lblVer) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    ventana.desbloquear();
                    PanelPDf panelPDf = new PanelPDf(Archivo);
                    panelPDf.toFront();
                    return null;
                }
            };
            worker.execute();

        }
        if (e.getSource() == txtprecio) {
            if (txtprecio.getText().equals("0")) {
                txtprecio.setText("");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource()
                == lblAdjuntar) {
            lblAdjuntar.setOpaque(false);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource()
                == txtprecio) {
            if (txtprecio.getText().equals("")) {
                txtprecio.setText("0");
            }
        }
    }

    public DtoEquipo getEquipment() {
        DtoEquipo equipoFind = new DtoEquipo();
        equipoFind.setCodigoInv(!txtcodInv.getText().trim().equals("") ? txtcodInv.getText().trim() : null);
        equipoFind.setCodigoMet(!txtCodmet.getText().trim().equals("") ? txtCodmet.getText().trim() : null);
        equipoFind.setSerie(!txtnumse.getText().trim().equals("") ? txtnumse.getText().trim() : null);

        ObjectResponse response = cmdEquipment.findEquipments(equipoFind,null);
        ArrayList<DtoEquipo> equipments=(ArrayList<DtoEquipo>)response.getRecords();

        if (equipments != null && !equipments.isEmpty()) {
            return equipments.get(0);
        } else {
            return null;
        }

    }

    public DefaultTableModel getCalibracion(ArrayList<DtoCalibracion> calibraciones, DefaultTableModel model) {
        if (calibraciones.isEmpty()) {
            return null;
        }
        for (DtoCalibracion c : calibraciones) {

            Object nuevo[] = {c.getCodigoCalibracion(), c.getEquipo().getCodigoInv(), c.getEquipo().getCodigoMet(), c.getEquipo().getSerie(), c.getEquipo().getNombre(), c.getEquipo().getUbicacion(), c.getEquipo().getCliente(), Estados.getByCode(c.getEquipo().getEstado()), GlobalUtils.getDateFormatted(c.getFechaRegistro()), GlobalUtils.getDateFormatted(c.getProximaCalibracion()), c.getRangCalibracion(), c.getRangTrabajo(), c};
            model.addRow(nuevo);
        }
        return model;
    }

    public final void ocultarColumna(String column) {
        TableColumn tc = TablaDatos.getColumn(column); //Obtienes la columna
        tc.setPreferredWidth(0); //Y le das un tamaño
        tc.setMinWidth(0);
        tc.setMaxWidth(0);
    }

    public JTextArea getTxaProovedores() {
        return txaProovedores;
    }

    public void createTemporalFile(byte[] array) {
        FileInputStream fileInputStream = null;

        try {
            Archivo = File.createTempFile("PdfCalibation", ".pdf");
            try (FileOutputStream fileOuputStream = new FileOutputStream(Archivo)) {
                fileOuputStream.write(array);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateNotifications() {
        Thread updateNotification = new Thread(new ThreadNotification(menu));
        updateNotification.start();
    }
}
