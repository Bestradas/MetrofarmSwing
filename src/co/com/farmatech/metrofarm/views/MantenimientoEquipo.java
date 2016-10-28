/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.views;

import co.com.farmatech.metrofarm.cmd.CmdEquipment;
import co.com.farmatech.metrofarm.cmd.CmdMaintenance;
import co.com.farmatech.metrofarm.utils.Formu_Fondo;
import co.com.farmatech.metrofarm.utils.ListaProovedorCal;
import co.com.farmatech.metrofarm.utils.PanelPaginador;
import co.com.farmatech.metrofarm.utils.TablaEquipos;
import co.com.farmatech.metrofarm.utils.UtilFarmatech;
import co.com.farmatech.metrofarm.utils.buttons.BotonesMenu;
import co.com.farmatech.metrofarm.views.notification.ThreadNotification;
import co.janker.dto.objresponse.ObjectResponse;
import co.jankins.psf.common.enums.Frecuencias;
import co.jankins.psf.common.enums.TipoEquipo;
import co.jankins.psf.common.enums.TipoMantenimiento;
import co.jankins.psf.common.global.GlobalUtils;
import com.co.farmatech.metrofarm.dto.DtoEquipo;
import com.co.farmatech.metrofarm.dto.DtoMantenimiento;
import com.co.farmatech.metrofarm.dto.PageData;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.apache.commons.codec.binary.Base64;
import ventana.bloqueo.VentanaBloqueo;

/**
 *
 * @author bestrada
 */
public class MantenimientoEquipo extends JFrame implements ActionListener, MouseListener {

    private Object[][] DetallesMantenimiento = new Object[0][0];
    private Object[] ColumnasMantenimiento = {"Código de Mantenimiento", "Código Inventario", "Código Metrologia", "Modelo del equipo", "Número de Serie", "Nombre del equipo", "Clasificación del equipo", "Tipo de Mantenimiento", "Responsable del mantenimiento", "Recibido por", "Fecha de registro", "Próximo Mantenimiento", "Objeto"};
    private DefaultTableModel dataModel = new DefaultTableModel(DetallesMantenimiento, ColumnasMantenimiento);
    public JLabel lblNumForm, lblcodInv, lblidMetrologia, lblNumSerie, lblNombre, lblMarca, lblClasificacion, lblTipoMant, lblResponsable, lblRecivido, lblAnomalia, lblLabores, lblObservaciones, lblProximoMant, lblFoto, lblModelo, lblRegistrado, LblPrecio, LblHistorial;
    public JTextField txtNumForm, txtcodInv, txtidMetrologia, txtNumSerie, txtNombre, txtMarca, txtModelo, txtUbicacion, txtResponsable, txtRecivido, txtResultado, txtRegistrado, txtPrecio;
    public JTextArea txaAnomalia, txaLabores, txaObservaciones;
    public JTextArea txaProovedores;
    JComboBox cboTipoMant;
    public static JTable TablaDatos;
    JScrollPane PanelDatos, scrollPane;
    Font TLetra = new Font("Arial", Font.PLAIN, 12);
    Font Advertencia = new Font("Arial", Font.PLAIN, 15);
    String ruta = "co/com/farmatech/metrofarm/resources/imagenes/";
    public JButton btnNuevo = new JButton("Nuevo", new ImageIcon(getClass().getClassLoader().getResource(ruta + "nuevo.gif")));
    public JButton btnGuardar = new JButton("Guardar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "guardar.gif")));
    JButton btnActualizar = new JButton("Actualizar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "actualizar.gif")));
    JButton btnBuscar = new JButton("Buscar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "buscar.gif")));
    JButton btnBuscarInv = new JButton("Filtrar Mantenimientos", new ImageIcon(getClass().getClassLoader().getResource(ruta + "buscar.gif")));
    JButton btnBuscaridMetr = new JButton("Filtrar Mantenimientos", new ImageIcon(getClass().getClassLoader().getResource(ruta + "buscar.gif")));
    JButton btnBuscarSerie = new JButton("Filtrar Mantenimientos", new ImageIcon(getClass().getClassLoader().getResource(ruta + "buscar.gif")));
    JButton btnSalir = new JButton("Salir", new ImageIcon(getClass().getClassLoader().getResource(ruta + "volver.gif")));
    JButton btnProovedores = new JButton("Seleccionar Proovedores", new ImageIcon(getClass().getClassLoader().getResource(ruta + "proovedor.gif")));
    JButton btnMantVencidos = new JButton("Mantenimientos Vencidos", new ImageIcon(getClass().getClassLoader().getResource(ruta + "Vencido.gif")));
    JButton btnMantDelMes = new JButton("Mantenimientos de este mes", new ImageIcon(getClass().getClassLoader().getResource(ruta + "Mes.png")));
    JPanel Panelinfo, Panelpermiso, Panelopcion, PanelFoto;
    ImageIcon Icofondo;
    Formu_Fondo PanelGeneral1;
    UtilFarmatech util = new UtilFarmatech();
    private final VentanaBloqueo ventana = new VentanaBloqueo();
    JFrame frame;
    CmdEquipment cmdEquipment = new CmdEquipment();
    public DtoEquipo equipoSeleccionado;
    CmdMaintenance cmdMaintenance;
    MenuPrincipal menu;
    public BotonesMenu btnsacartabla = new BotonesMenu(new ImageIcon(getClass().getClassLoader().getResource(ruta + "table.png")), new ImageIcon(getClass().getClassLoader().getResource(ruta + "table2.png")));
    PanelPaginador panelPaginador;
    PageData pageData;

    public MantenimientoEquipo(MenuPrincipal menu) {
        super("Mantenimiento general METROFARM -v1");

        ((JPanel) getContentPane()).setOpaque(false);  // para que muestre la imagen
        this.setSize(1366, 768);
        this.setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);
        setBackground(Color.white);
        setVisible(true);
        setLayout(null);
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource(ruta + "FARMA.png")).getImage());
        setDefaultCloseOperation(0);

        frame = this;
        cmdMaintenance = new CmdMaintenance();

        this.menu = menu;


        panelPaginador = new PanelPaginador();
        panelPaginador.setBounds(1000, 670, 300, 29);

        pageData = panelPaginador.getPageData();
        pageData.setRowsPerPage(10);

        Icofondo = new ImageIcon(getClass().getClassLoader().getResource(ruta + "fondomenu.png"));
        PanelGeneral1 = new Formu_Fondo(Icofondo);
        PanelGeneral1.setBorder(BorderFactory.createTitledBorder(""));
        PanelGeneral1.setBackground(Color.black);
        PanelGeneral1.setForeground(Color.red);
        PanelGeneral1.reshape(0, 0, 1366, 768);
        PanelGeneral1.setLayout(null);

        LblHistorial = new JLabel("Historial de mantenimientos generales");
        LblHistorial.setBounds(50, 430, 400, 20);
        LblHistorial.setFont(new Font("Arial", Font.ITALIC, 15));
        LblHistorial.setForeground(Color.blue);
        LblHistorial.setVisible(true);

        lblFoto = new JLabel();
        lblFoto.setBackground(Color.black);
        lblFoto.setBorder(BorderFactory.createEmptyBorder());
        lblFoto.setOpaque(true);
        lblFoto.setBackground(Color.white);
        lblFoto.reshape(15, 15, 250, 250);
        lblFoto.setLayout(null);

        Panelinfo = new JPanel();
        Panelinfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(Integer.parseInt("015289", 16))), "Datos básicos del Equipo", WIDTH, PROPERTIES, TLetra, new Color(Integer.parseInt("EF2F24", 16))));
        Panelinfo.setBackground(getBackground());
        Panelinfo.setForeground(new Color(Integer.parseInt("EF2F24", 16)));
        Panelinfo.reshape(10, 10, 900, 250);
        Panelinfo.setLayout(null);

        PanelFoto = new JPanel();
        PanelFoto.setBorder(BorderFactory.createTitledBorder("Foto de equipo"));
        //PanelGeneral1.add(fondo);
        PanelFoto.setForeground(Color.red);
        PanelFoto.reshape(950, 10, 280, 280);
        PanelFoto.setLayout(null);
        PanelFoto.add(lblFoto);

        lblNumForm = new JLabel("* Número de mantenimiento");
        lblNumForm.setBounds(30, 10, 200, 20);
        lblNumForm.setFont(TLetra);
        lblNumForm.setForeground(Color.black);
        lblNumForm.setVisible(true);

        txtNumForm = new JTextField("");
        txtNumForm.setBounds(200, 10, 100, 20);
        txtNumForm.setToolTipText("Ingrese el número de formulario a registrar");
        txtNumForm.setFont(TLetra);
        txtNumForm.setForeground(Color.black);
        txtNumForm.setVisible(true);
        txtNumForm.setEditable(false);

        btnBuscar.setBounds(310, 10, 100, 25);
        btnBuscar.setVisible(true);

        JLabel mostraequipos = new JLabel("Mostrar equipos");
        mostraequipos.setBounds(1250, 340, 200, 20);
        mostraequipos.setFont(TLetra);
        mostraequipos.setForeground(Color.black);
        mostraequipos.setVisible(true);

        btnsacartabla.setBounds(1260, 370, 50, 50);
        btnsacartabla.addActionListener(this);
        btnsacartabla.setVisible(true);

        lblcodInv = new JLabel("* Código de inventario");
        lblcodInv.setBounds(30, 40, 150, 20);
        lblcodInv.setFont(TLetra);
        lblcodInv.setForeground(Color.black);
        lblcodInv.setVisible(true);

        txtcodInv = new JTextField("");
        txtcodInv.setBounds(200, 40, 100, 20);
        txtcodInv.setToolTipText("presione la tecla ENTER despues de haber ingresado el codigo de inventario");
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
                    JOptionPane.showMessageDialog(null, "Este campo solo puede tener como maximo 11 carácteres", "METROFARM", JOptionPane.ERROR_MESSAGE);
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
                                    if (equipo.getFrecMantenimiento().equals(Frecuencias.NOAPLICA.getCode())) {
                                        JOptionPane.showMessageDialog(null, "Este equipo no esta habilitado para Mantenimientos", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                        txtcodInv.setText("");
                                        txtidMetrologia.setText("");
                                        txtNumSerie.setText("");
                                        ventana.desbloquear();
                                        return null;
                                    }
                                    if (equipo.getCodigoMet().equals("") || equipo.getMarca().equals("") || equipo.getModelo().equals("") || equipo.getUbicacion().equals("")) {
                                        JOptionPane.showMessageDialog(null, "El equipo seleccionado se encuentra entre los equipos pendientes a registrar", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                        ventana.desbloquear();
                                        return null;
                                    }
                                    if (TipoEquipo.INSTRUMENTO.getCode().equals(equipo.getTipo())) {
                                        JOptionPane.showMessageDialog(null, "El equipo seleccionado es un instrumento, solo se permite administrar equipos en los mantenimientos generales.", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                        ventana.desbloquear();
                                        return null;
                                    }
                                    txtcodInv.setBackground(new java.awt.Color(204, 255, 204));
                                    txtcodInv.setText(equipo.getCodigoInv());
                                    txtcodInv.setEditable(false);
                                    txtidMetrologia.setText(equipo.getCodigoInv());
                                    txtidMetrologia.setBackground(new java.awt.Color(204, 255, 204));
                                    txtidMetrologia.setEditable(false);
                                    txtNumSerie.setText(equipo.getSerie());
                                    txtNumSerie.setBackground(new java.awt.Color(204, 255, 204));
                                    txtNumSerie.setEditable(false);
                                    txtNombre.setText(equipo.getNombre());
                                    txtNombre.setBackground(new java.awt.Color(204, 255, 204));
                                    txtUbicacion.setText(equipo.getUbicacion());
                                    txtUbicacion.setBackground(new java.awt.Color(204, 255, 204));
                                    txtMarca.setText(equipo.getMarca());
                                    txtMarca.setBackground(new java.awt.Color(204, 255, 204));
                                    txtModelo.setText(equipo.getModelo());
                                    txtModelo.setBackground(new java.awt.Color(204, 255, 204));

                                    ImageIcon imagen = new ImageIcon(Base64.decodeBase64(equipo.getFoto()));
                                    imagen = AjustarUmagen(imagen);
                                    lblFoto.setIcon(imagen);
                                    equipoSeleccionado = equipo;
                                } else {
                                    JOptionPane.showMessageDialog(null, "No existe un equipo registrado con este código de inventario", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                    txtcodInv.setText("");
                                    txtidMetrologia.setText("");
                                    txtNumSerie.setText("");
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

        btnBuscarInv.setBounds(310, 40, 170, 25);
        btnBuscarInv.setVisible(true);

        lblidMetrologia = new JLabel("* Identificación de metrología");

        lblidMetrologia.setBounds(30, 70, 200, 20);
        lblidMetrologia.setFont(TLetra);

        lblidMetrologia.setForeground(Color.black);

        lblidMetrologia.setVisible(
                true);

        txtidMetrologia = new JTextField("");

        txtidMetrologia.setBounds(200, 70, 100, 20);
        txtidMetrologia.setToolTipText("presione la tecla ENTER despues de haber ingresado el código de metrología");
        txtidMetrologia.setFont(TLetra);

        txtidMetrologia.setForeground(Color.black);

        txtidMetrologia.setVisible(true);
        txtidMetrologia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                util.anularTildes(e);
                int limite = 11;
                char caracter = e.getKeyChar();
                if (txtidMetrologia.getText().length() == limite) {
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
                            if (txtidMetrologia.getText().trim().equals("")) {
                                JOptionPane.showMessageDialog(null, "Debes ingresar el código de metrología del equipo a buscar", "METROFARM", JOptionPane.ERROR_MESSAGE);
                            } else {
                                DtoEquipo equipo = getEquipment();
                                if (equipo != null) {
                                    if (equipo.getFrecMantenimiento().equals(Frecuencias.NOAPLICA.getCode())) {
                                        JOptionPane.showMessageDialog(null, "Este equipo no esta habilitado para Mantenimientos", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                        txtcodInv.setText("");
                                        txtidMetrologia.setText("");
                                        txtNumSerie.setText("");
                                        ventana.desbloquear();
                                        return null;
                                    }
                                    if (equipo.getCodigoMet().equals("") || equipo.getMarca().equals("") || equipo.getModelo().equals("") || equipo.getUbicacion().equals("")) {
                                        JOptionPane.showMessageDialog(null, "El equipo seleccionado se encuentra entre los equipos pendientes a registrar", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                        ventana.desbloquear();
                                        return null;
                                    }
                                    if (TipoEquipo.INSTRUMENTO.getCode().equals(equipo.getTipo())) {
                                        JOptionPane.showMessageDialog(null, "El equipo seleccionado es un instrumento, solo se permite administrar equipos en los mantenimientos generales.", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                        ventana.desbloquear();
                                        return null;
                                    }
                                    txtcodInv.setBackground(new java.awt.Color(204, 255, 204));
                                    txtcodInv.setText(equipo.getCodigoInv());
                                    txtcodInv.setEditable(false);
                                    txtidMetrologia.setText(equipo.getCodigoInv());
                                    txtidMetrologia.setBackground(new java.awt.Color(204, 255, 204));
                                    txtidMetrologia.setEditable(false);
                                    txtNumSerie.setText(equipo.getSerie());
                                    txtNumSerie.setBackground(new java.awt.Color(204, 255, 204));
                                    txtNumSerie.setEditable(false);
                                    txtNombre.setText(equipo.getNombre());
                                    txtNombre.setBackground(new java.awt.Color(204, 255, 204));
                                    txtUbicacion.setText(equipo.getUbicacion());
                                    txtUbicacion.setBackground(new java.awt.Color(204, 255, 204));
                                    txtMarca.setText(equipo.getMarca());
                                    txtMarca.setBackground(new java.awt.Color(204, 255, 204));
                                    txtModelo.setText(equipo.getModelo());
                                    txtModelo.setBackground(new java.awt.Color(204, 255, 204));

                                    ImageIcon imagen = new ImageIcon(Base64.decodeBase64(equipo.getFoto()));
                                    imagen = AjustarUmagen(imagen);
                                    lblFoto.setIcon(imagen);
                                    equipoSeleccionado = equipo;
                                } else {
                                    JOptionPane.showMessageDialog(null, "No existe un equipo registrado con este código de metrología", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                    txtcodInv.setText("");
                                    txtidMetrologia.setText("");
                                    txtNumSerie.setText("");
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

        btnBuscaridMetr.setBounds(
                310, 70, 170, 25);
        btnBuscaridMetr.setVisible(
                true);

        lblNumSerie = new JLabel("* Número de serie");

        lblNumSerie.setBounds(
                30, 100, 200, 20);
        lblNumSerie.setFont(TLetra);

        lblNumSerie.setForeground(Color.black);

        lblNumSerie.setVisible(
                true);

        txtNumSerie = new JTextField("");

        txtNumSerie.setBounds(
                200, 100, 100, 20);
        txtNumSerie.setToolTipText(
                "presione la tecla ENTER despues de haber ingresado el número de serie");
        txtNumSerie.setFont(TLetra);

        txtNumSerie.setForeground(Color.black);

        txtNumSerie.setVisible(
                true);
        txtNumSerie.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                util.anularTildes(e);
                int limite = 11;
                char caracter = e.getKeyChar();
                if (txtNumSerie.getText().length() == limite) {
                    e.consume();
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Este campo solo puede tener como maximo 11 caracteres", "METROFARM", JOptionPane.ERROR_MESSAGE);
                }

                if ((caracter == KeyEvent.VK_ENTER)) {
                    SwingWorker worker;
                    worker = new SwingWorker() {
                        @Override
                        protected Object doInBackground() throws Exception {
                            if (txtNumSerie.getText().trim().equals("")) {
                                JOptionPane.showMessageDialog(null, "Debes ingresar el número de serie del equipo a buscar", "METROFARM", JOptionPane.ERROR_MESSAGE);
                            } else {
                                DtoEquipo equipo = getEquipment();
                                if (equipo != null) {
                                    if (equipo.getFrecMantenimiento().equals(Frecuencias.NOAPLICA.getCode())) {
                                        JOptionPane.showMessageDialog(null, "Este equipo no esta habilitado para Mantenimientos", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                        txtcodInv.setText("");
                                        txtidMetrologia.setText("");
                                        txtNumSerie.setText("");
                                        ventana.desbloquear();
                                        return null;
                                    }
                                    if (equipo.getCodigoMet().equals("") || equipo.getMarca().equals("") || equipo.getModelo().equals("") || equipo.getUbicacion().equals("")) {
                                        JOptionPane.showMessageDialog(null, "El equipo seleccionado se encuentra entre los equipos pendientes a registrar", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                        ventana.desbloquear();
                                        return null;
                                    }
                                    if (TipoEquipo.INSTRUMENTO.getCode().equals(equipo.getTipo())) {
                                        JOptionPane.showMessageDialog(null, "El equipo seleccionado es un instrumento, solo se permite administrar equipos en los mantenimientos generales.", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                        ventana.desbloquear();
                                        return null;
                                    }
                                    txtcodInv.setBackground(new java.awt.Color(204, 255, 204));
                                    txtcodInv.setText(equipo.getCodigoInv());
                                    txtcodInv.setEditable(false);
                                    txtidMetrologia.setText(equipo.getCodigoInv());
                                    txtidMetrologia.setBackground(new java.awt.Color(204, 255, 204));
                                    txtidMetrologia.setEditable(false);
                                    txtNumSerie.setText(equipo.getSerie());
                                    txtNumSerie.setBackground(new java.awt.Color(204, 255, 204));
                                    txtNumSerie.setEditable(false);
                                    txtNombre.setText(equipo.getNombre());
                                    txtNombre.setBackground(new java.awt.Color(204, 255, 204));
                                    txtUbicacion.setText(equipo.getUbicacion());
                                    txtUbicacion.setBackground(new java.awt.Color(204, 255, 204));
                                    txtMarca.setText(equipo.getMarca());
                                    txtMarca.setBackground(new java.awt.Color(204, 255, 204));
                                    txtModelo.setText(equipo.getModelo());
                                    txtModelo.setBackground(new java.awt.Color(204, 255, 204));

                                    ImageIcon imagen = new ImageIcon(Base64.decodeBase64(equipo.getFoto()));
                                    imagen = AjustarUmagen(imagen);
                                    lblFoto.setIcon(imagen);
                                    equipoSeleccionado = equipo;
                                } else {
                                    JOptionPane.showMessageDialog(null, "No existe un equipo registrado con este número de serie", "METROFARM", JOptionPane.ERROR_MESSAGE);
                                    txtcodInv.setText("");
                                    txtidMetrologia.setText("");
                                    txtNumSerie.setText("");
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

        btnBuscarSerie.setBounds(310, 100, 170, 25);
        btnBuscarSerie.setVisible(true);

        lblNombre = new JLabel("* Nombre del equipo");

        lblNombre.setBounds(30, 130, 200, 20);
        lblNombre.setFont(TLetra);

        lblNombre.setForeground(Color.black);

        lblNombre.setVisible(true);

        txtNombre = new JTextField("");

        txtNombre.setBounds(200, 130, 200, 20);
        txtNombre.setFont(TLetra);

        txtNombre.setForeground(Color.black);

        txtNombre.setEditable(false);
        txtNombre.setVisible(true);

        lblMarca = new JLabel("* Marca");

        lblMarca.setBounds(30, 160, 200, 20);
        lblMarca.setFont(TLetra);

        lblMarca.setForeground(Color.black);

        lblMarca.setVisible(true);

        txtMarca = new JTextField("");

        txtMarca.setBounds(200, 160, 170, 20);
        txtMarca.setFont(TLetra);

        txtMarca.setForeground(Color.black);

        txtMarca.setEditable(false);
        txtMarca.setVisible(true);

        lblModelo = new JLabel("* Modelo");

        lblModelo.setBounds(30, 190, 200, 20);
        lblModelo.setFont(TLetra);

        lblModelo.setForeground(Color.black);

        lblModelo.setVisible(true);

        txtModelo = new JTextField("");

        txtModelo.setBounds(200, 190, 170, 20);
        txtModelo.setFont(TLetra);

        txtModelo.setForeground(Color.black);

        txtModelo.setEditable(false);
        txtModelo.setVisible(true);

        lblClasificacion = new JLabel("* Ubicación");

        lblClasificacion.setBounds(500, 70, 200, 20);
        lblClasificacion.setFont(TLetra);

        lblClasificacion.setForeground(Color.black);

        lblClasificacion.setVisible(true);

        txtUbicacion = new JTextField("");

        txtUbicacion.setBounds(670, 70, 150, 20);
        txtUbicacion.setFont(TLetra);

        txtUbicacion.setForeground(Color.black);

        txtUbicacion.setEditable(false);
        txtUbicacion.setVisible(true);

        lblTipoMant = new JLabel("* Tipo de mantenimiento");

        lblTipoMant.setBounds(500, 100, 200, 20);
        lblTipoMant.setFont(TLetra);

        lblTipoMant.setForeground(Color.black);

        lblTipoMant.setVisible(true);

        cboTipoMant = new JComboBox();

        cboTipoMant.addItem("");
        cboTipoMant.addItem("Correctivo");
        cboTipoMant.addItem("Preventivo");
        cboTipoMant.addItem("Programado");
        cboTipoMant.setBounds(670, 100, 150, 20);
        cboTipoMant.setFont(TLetra);

        cboTipoMant.setForeground(Color.black);

        cboTipoMant.setVisible(true);

        lblResponsable = new JLabel("* Responsable");

        lblResponsable.setBounds(500, 130, 200, 20);
        lblResponsable.setFont(TLetra);

        lblResponsable.setForeground(Color.black);

        lblResponsable.setVisible(true);

        txtResponsable = new JTextField("");

        txtResponsable.setBounds(670, 130, 200, 20);
        txtResponsable.setFont(TLetra);

        txtResponsable.setForeground(Color.black);

        txtResponsable.setVisible(true);
        txtResponsable.addKeyListener(new KeyAdapter() {
            //Validacion Nombre
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isLetter(c) //si es letra
                        || c == ' ' //o un espacio
                        || c == 8 //o backspace
                        )) {
                    getToolkit().beep();
                    e.consume();
                    JOptionPane.showMessageDialog(null, "No se permiten Números en este campo", "METROFARM", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        lblRecivido = new JLabel("* Recibido por");
        lblRecivido.setBounds(500, 160, 200, 20);
        lblRecivido.setFont(TLetra);
        lblRecivido.setForeground(Color.black);
        lblRecivido.setVisible(true);

        txtRecivido = new JTextField("");
        txtRecivido.setBounds(670, 160, 200, 20);
        txtRecivido.setFont(TLetra);
        txtRecivido.setForeground(Color.black);
        txtRecivido.setVisible(true);
        txtRecivido.addKeyListener(new KeyAdapter() {
            //Validacion Nombre
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isLetter(c) //si es letra
                        || c == ' ' //o un espacio
                        || c == 8 //o backspace
                        )) {
                    getToolkit().beep();
                    e.consume();
                    JOptionPane.showMessageDialog(null, "No se permiten números en este campo", "METROFARM", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        lblRegistrado = new JLabel("* Registrado por");
        lblRegistrado.setBounds(500, 190, 200, 20);
        lblRegistrado.setFont(TLetra);
        lblRegistrado.setForeground(Color.black);
        lblRegistrado.setVisible(true);

        txtRegistrado = new JTextField("");
        txtRegistrado.setBounds(670, 190, 200, 20);
        txtRegistrado.setFont(TLetra);
        txtRegistrado.setForeground(Color.black);
        txtRegistrado.setEditable(false);
        txtRegistrado.setText(GlobalUtils.getContext().getLoginUser().getNombres() + " " + GlobalUtils.getContext().getLoginUser().getApellidos());
        txtRegistrado.setVisible(true);

        LblPrecio = new JLabel("Gasto total                       COL$");
        LblPrecio.setBounds(500, 220, 200, 20);
        LblPrecio.setFont(TLetra);
        LblPrecio.setForeground(Color.black);
        LblPrecio.setVisible(true);

        txtPrecio = new JTextField("");
        txtPrecio.setText("0");
        txtPrecio.setBounds(670, 220, 200, 20);
        txtPrecio.setFont(TLetra);
        txtPrecio.setForeground(Color.black);
        txtPrecio.setVisible(true);
        txtPrecio.addMouseListener(this);
        txtPrecio.addKeyListener(new KeyAdapter() {
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

        txtPrecio.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                txtPrecio.setText(util.makeGroupingNumber(txtPrecio.getText()));
            }
        });

        lblAnomalia = new JLabel(" DIAGNÓSTICO");
        lblAnomalia.setBounds(60, 270, 200, 20);
        lblAnomalia.setFont(TLetra);
        lblAnomalia.setForeground(Color.black);
        lblAnomalia.setVisible(true);

        txaAnomalia = new JTextArea();
        txaAnomalia.setBounds(10, 320, 300, 100);
        txaAnomalia.setEditable(true);
        txaAnomalia.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txaAnomalia.setToolTipText("Ingrese las anomalias presentadas durante el mantenimiento");
        txaAnomalia.setBackground(Color.white);
        txaAnomalia.setFont(TLetra);
        txaAnomalia.setForeground(Color.BLACK);
        txaAnomalia.setVisible(true);
        txaAnomalia.setLineWrap(true);//Provocar salto de linea cortando palabra
        txaAnomalia.setWrapStyleWord(true);//Provocar salto de linea despues del espacio
        txaAnomalia.setColumns(20);
        txaAnomalia.setRows(5);

        lblLabores = new JLabel(" LABORES REALIZADAS");
        lblLabores.setBounds(370, 270, 200, 20);
        lblLabores.setFont(TLetra);
        lblLabores.setForeground(Color.black);
        lblLabores.setVisible(true);

        txaLabores = new JTextArea();
        txaLabores.setBounds(320, 320, 300, 100);
        txaLabores.setEditable(true);
        txaLabores.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txaLabores.setToolTipText("Ingrese las labores realizadas en este mantenimiento");
        txaLabores.setBackground(Color.white);
        txaLabores.setFont(TLetra);
        txaLabores.setForeground(Color.BLACK);
        txaLabores.setVisible(true);
        txaLabores.setLineWrap(true);//Provocar salto de linea cortando palabra
        txaLabores.setWrapStyleWord(true);//Provocar salto de linea despues del espacio
        txaLabores.setColumns(20);
        txaLabores.setRows(5);

        lblObservaciones = new JLabel(" OBSERVACIONES");
        lblObservaciones.setBounds(680, 270, 200, 20);
        lblObservaciones.setFont(TLetra);
        lblObservaciones.setForeground(Color.black);
        lblObservaciones.setVisible(true);

        txaObservaciones = new JTextArea();
        txaObservaciones.setBounds(630, 320, 300, 100);
        txaObservaciones.setEditable(true);
        txaObservaciones.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txaObservaciones.setBackground(Color.white);
        txaObservaciones.setFont(TLetra);
        txaObservaciones.setForeground(Color.BLACK);
        txaObservaciones.setToolTipText("Ingrese las observaciones pertinentes");
        txaObservaciones.setVisible(true);
        txaObservaciones.setLineWrap(true);//Provocar salto de linea cortando palabra
        txaObservaciones.setWrapStyleWord(true);//Provocar salto de linea despues del espacio
        txaObservaciones.setColumns(20);
        txaObservaciones.setRows(5);

        txaProovedores = new JTextArea();
        txaProovedores.setBounds(950, 340, 280, 100);
        txaProovedores.setEditable(false);
        txaProovedores.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txaProovedores.setBackground(Color.white);
        txaProovedores.setFont(TLetra);
        txaProovedores.setForeground(Color.BLACK);
        txaProovedores.setVisible(true);
        txaProovedores.setLineWrap(true);//Provocar salto de linea cortando palabra
        txaProovedores.setWrapStyleWord(true);//Provocar salto de linea despues del espacio

        btnProovedores.setBounds(1000, 300, 200, 25);
        btnProovedores.setVisible(true);

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
        TablaDatos.getColumnModel().getColumn(12).setMinWidth(170);

        PanelDatos.setBounds(50, 450, 1250, 200);
        TablaDatos.addMouseListener(this);
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(dataModel);
        TablaDatos.setRowSorter(elQueOrdena);
        TablaDatos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        PanelDatos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        ocultarColumna("Objeto");

        btnNuevo.setBounds(400, 680, 100, 25);
        btnNuevo.setEnabled(true);
        btnGuardar.setBounds(500, 680, 100, 25);
        btnActualizar.setBounds(600, 680, 100, 25);
        btnSalir.setBounds(700, 680, 100, 25);
        btnMantVencidos.setBounds(5, 680, 180, 40);
        btnMantDelMes.setBounds(190, 680, 200, 40);

        btnNuevo.setVisible(true);
        btnGuardar.setVisible(true);
        btnActualizar.setVisible(true);
        btnBuscar.setVisible(true);
        btnMantVencidos.setVisible(true);
        btnSalir.setVisible(true);

        Panelinfo.add(btnBuscarInv);
        Panelinfo.add(btnBuscaridMetr);
        Panelinfo.add(btnBuscarSerie);
        Panelinfo.add(btnBuscar);
        Panelinfo.add(lblNumForm);
        Panelinfo.add(txtNumForm);
        Panelinfo.add(lblcodInv);
        Panelinfo.add(txtcodInv);
        Panelinfo.add(lblidMetrologia);
        Panelinfo.add(txtidMetrologia);
        Panelinfo.add(lblNumSerie);
        Panelinfo.add(txtNumSerie);
        Panelinfo.add(lblNombre);
        Panelinfo.add(txtNombre);
        Panelinfo.add(lblMarca);
        Panelinfo.add(txtMarca);
        Panelinfo.add(lblModelo);
        Panelinfo.add(txtModelo);
        Panelinfo.add(lblClasificacion);
        Panelinfo.add(txtUbicacion);
        Panelinfo.add(lblTipoMant);
        Panelinfo.add(cboTipoMant);
        Panelinfo.add(lblResponsable);
        Panelinfo.add(txtResponsable);
        Panelinfo.add(lblRecivido);
        Panelinfo.add(txtRecivido);
        Panelinfo.add(lblRegistrado);
        Panelinfo.add(txtRegistrado);
        Panelinfo.add(LblPrecio);
        Panelinfo.add(txtPrecio);

        PanelGeneral1.add(PanelFoto);
        PanelGeneral1.add(Panelinfo);
        PanelGeneral1.add(lblAnomalia);
        PanelGeneral1.add(txaAnomalia);
        PanelGeneral1.add(lblLabores);
        PanelGeneral1.add(txaLabores);
        PanelGeneral1.add(lblObservaciones);
        PanelGeneral1.add(txaObservaciones);
        PanelGeneral1.add(txaProovedores);
        PanelGeneral1.add(PanelDatos);
        PanelGeneral1.add(btnActualizar);
        PanelGeneral1.add(btnNuevo);
        PanelGeneral1.add(btnGuardar);
        PanelGeneral1.add(btnMantVencidos);
        PanelGeneral1.add(btnMantDelMes);
        PanelGeneral1.add(btnSalir);
        PanelGeneral1.add(btnProovedores);
        PanelGeneral1.add(LblHistorial);
        PanelGeneral1.add(btnsacartabla);
        PanelGeneral1.add(mostraequipos);
        PanelGeneral1.add(panelPaginador);

        PanelGeneral1.setVisible(true);
        this.getContentPane().add(PanelGeneral1);

        btnSalir.addActionListener(this);
        btnNuevo.addActionListener(this);
        btnGuardar.addActionListener(this);
        btnActualizar.addActionListener(this);
        btnProovedores.addActionListener(this);
        btnBuscar.addActionListener(this);
        btnBuscarInv.addActionListener(this);
        btnBuscaridMetr.addActionListener(this);
        btnBuscarSerie.addActionListener(this);
        btnMantDelMes.addActionListener(this);
        btnMantVencidos.addActionListener(this);
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
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea Salir?", "METROFARM", JOptionPane.YES_NO_OPTION);
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
                    limpiarCampos();
                    panelPaginador.enablePageButtons();
                    vaciar();
                    llenarDatos(pageData);
                    repaint();
                    ventana.desbloquear();
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
                    String NumFom, Codinv, CodMetr, Serie, NombreEquipo, Marca, Modelo, Ubicacion, TipoMant, Responsable, Recivido, Registrado, Anomalias, Labores, Observaciones, Proovedores, Fecha, Hora, Proximo;


                    BigDecimal Precio;
                    NumFom = txtNumForm.getText();
                    Codinv = txtcodInv.getText();
                    CodMetr = txtidMetrologia.getText();
                    Marca = txtMarca.getText();
                    Modelo = txtModelo.getText();
                    Serie = txtNumSerie.getText();
                    NombreEquipo = txtNombre.getText();
                    Ubicacion = txtUbicacion.getText();
                    TipoMant = cboTipoMant.getSelectedItem().toString();
                    Responsable = txtResponsable.getText();
                    Recivido = txtRecivido.getText();
                    Registrado = txtRegistrado.getText();
                    Anomalias = txaAnomalia.getText();
                    Labores = txaLabores.getText();
                    Observaciones = txaObservaciones.getText();
                    Proovedores = txaProovedores.getText();


                    boolean valido = true;
                    if (Codinv.equals("")) {
                        valido = false;
                    }
                    if (CodMetr.equals("")) {
                        valido = false;
                    }

                    if (Serie.equals("")) {
                        valido = false;
                    }

                    if (Marca.equals("")) {
                        valido = false;
                    }

                    if (NombreEquipo.equals("")) {
                        valido = false;
                    }
                    if (Modelo.equals("")) {
                        valido = false;
                    }
                    if (Ubicacion.equals("")) {
                        valido = false;
                    }
                    if (TipoMant.equals("")) {
                        valido = false;
                    }
                    if (Responsable.equals("")) {
                        valido = false;
                    }
                    if (Recivido.equals("")) {
                        valido = false;
                    }
                    if (Registrado.equals("")) {
                        valido = false;
                    }
                    if (txtPrecio.getText().equals("") || txtPrecio.getText().equals("0")) {
                        Precio = new BigDecimal(0.0);
                    } else {
                        Precio = new BigDecimal(txtPrecio.getText().replace(",", "").replace("'", ""));
                    }
                    if (valido == true) {

                        Date fechaActual = new Date();
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(fechaActual);
                        cal.add(Calendar.MONTH, equipoSeleccionado.getFrecMantenimiento().intValue());
                        Date fechaProxima = cal.getTime();

                        DtoMantenimiento mantenimiento = new DtoMantenimiento();
                        mantenimiento.setEquipo(equipoSeleccionado);
                        mantenimiento.setAnomalias(Anomalias);
                        mantenimiento.setFechaProximo(fechaProxima);
                        mantenimiento.setFechaRegistro(fechaActual);
                        mantenimiento.setLabores(Labores);
                        mantenimiento.setObservaciones(Observaciones);
                        mantenimiento.setPrecio(Precio);
                        mantenimiento.setProovedores(Proovedores);
                        mantenimiento.setRecibido(Recivido);
                        mantenimiento.setRegistrado(GlobalUtils.getContext().getLoginUser());
                        mantenimiento.setResponsable(Responsable);
                        mantenimiento.setTipoMant(TipoMantenimiento.getByNombre(cboTipoMant.getSelectedItem().toString()).getCode());

                        boolean mantenimientoRegister = cmdMaintenance.createMaintenanceGeneral(mantenimiento);


                        if (mantenimientoRegister) {
                            JOptionPane.showMessageDialog(null, " Mantenimiento general ingresado", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                            limpiarCampos();
                            panelPaginador.enablePageButtons();
                            pageData.setNumRows(0);
                            vaciar();
                            llenarDatos(pageData);
                            updateNotifications();
                        } else {
                            JOptionPane.showMessageDialog(null, " No fue posible ingresar el nuevo mantenimiento general.", "METROFARM", JOptionPane.ERROR_MESSAGE);
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
                    String NumFom, TipoMant, Responsable, Recivido, Anomalias, Labores, Observaciones, Proovedores;
                    BigDecimal Precio;

                    NumFom = txtNumForm.getText();
                    TipoMant = cboTipoMant.getSelectedItem().toString();
                    Responsable = txtResponsable.getText();
                    Recivido = txtRecivido.getText();
                    Anomalias = txaAnomalia.getText();
                    Labores = txaLabores.getText();
                    Observaciones = txaObservaciones.getText();
                    Proovedores = txaProovedores.getText();

                    if (TablaDatos.getSelectedRow() > -1) {
                        boolean valido = true;
                        if (TipoMant.equals("")) {
                            valido = false;
                        }
                        if (Responsable.equals("")) {
                            valido = false;
                        }
                        if (Recivido.equals("")) {
                            valido = false;
                        }
                        if (txtPrecio.getText().equals("") || txtPrecio.getText().equals("0")) {
                            Precio = new BigDecimal(0.0);
                        } else {
                            Precio = new BigDecimal(txtPrecio.getText().replace(",", "").replace("'", ""));
                        }

                        if (valido == true) {

                            DtoMantenimiento mantenimiento = new DtoMantenimiento();
                            mantenimiento.setNumFom(NumFom);
                            mantenimiento.setEquipo(equipoSeleccionado);
                            mantenimiento.setAnomalias(Anomalias);
                            mantenimiento.setLabores(Labores);
                            mantenimiento.setObservaciones(Observaciones);
                            mantenimiento.setPrecio(Precio);
                            mantenimiento.setProovedores(Proovedores);
                            mantenimiento.setRecibido(Recivido);
                            mantenimiento.setRegistrado(GlobalUtils.getContext().getLoginUser());
                            mantenimiento.setResponsable(Responsable);
                            mantenimiento.setTipoMant(TipoMantenimiento.getByNombre(cboTipoMant.getSelectedItem().toString()).getCode());

                            boolean maintenanceUpdated = cmdMaintenance.updateMaintenanceGeneral(mantenimiento);

                            if (maintenanceUpdated) {
                                JOptionPane.showMessageDialog(null, " Mantenimiento general actualizado", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                                limpiarCampos();
                                panelPaginador.enablePageButtons();
                                vaciar();
                                llenarDatos(pageData);
                            } else {
                                JOptionPane.showMessageDialog(null, " No fue posible actualizar la información del mantenimiento general.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, " Los campos marcados con * son obligatorios.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, " Debes seleccionar un registro a actualizar.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
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
                    String NumFormulario;

                    NumFormulario = txtNumForm.getText();
                    if (NumFormulario.equals("")) {
                        JOptionPane.showMessageDialog(null, " Debes ingresar un número de mantenimiento general válido.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        DtoMantenimiento maintenance = new DtoMantenimiento();
                        maintenance.setNumFom(NumFormulario);
                        ArrayList<DtoMantenimiento> Buscar = cmdMaintenance.findMaintenanceForEquipments(maintenance);

                        if (Buscar.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No existe un mantenimiento general registrado con este número", "METROFARM", JOptionPane.ERROR_MESSAGE);
                        } else {
                            panelPaginador.enablePageButtons();
                            vaciar();
                            setDataModel(getMantenimientos(Buscar, getDataModel()));
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
                        ObjectResponse response = cmdMaintenance.findMaintenanceEquipment(equipo,null);
                        ArrayList<DtoMantenimiento> mantenimientos= (ArrayList<DtoMantenimiento>)response.getRecords();
                         if (mantenimientos==null || !mantenimientos.isEmpty()) {
                            panelPaginador.enablePageButtons(); 
                            vaciar();
                            setDataModel(getMantenimientos(mantenimientos, getDataModel()));
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
                    codigomet = txtidMetrologia.getText();
                    if (codigomet.trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, " Debe digitar el código de metrología del equipo que desea buscar.");
                    } else {
                        DtoEquipo equipo = new DtoEquipo();
                        equipo.setCodigoMet(codigomet);
                         ObjectResponse response= cmdMaintenance.findMaintenanceEquipment(equipo,null);
                         ArrayList<DtoMantenimiento> mantenimientos = (ArrayList<DtoMantenimiento>)response.getRecords();
                        if (mantenimientos==null || !mantenimientos.isEmpty()) {
                            panelPaginador.enablePageButtons();
                            vaciar();
                            setDataModel(getMantenimientos(mantenimientos, getDataModel()));
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
                    serie = txtNumSerie.getText();
                    if (serie.trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, " Debe digitar el número de serie del equipo que desea buscar.");
                    } else {
                        DtoEquipo equipo = new DtoEquipo();
                        equipo.setSerie(serie);
                        ObjectResponse response = cmdMaintenance.findMaintenanceEquipment(equipo,null);
                        ArrayList<DtoMantenimiento> mantenimientos=(ArrayList<DtoMantenimiento>)response.getRecords();
                         if (mantenimientos==null || !mantenimientos.isEmpty()) {
                             panelPaginador.enablePageButtons();
                            vaciar();
                            setDataModel(getMantenimientos(mantenimientos, getDataModel()));
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

        if (e.getSource() == btnsacartabla) {
            TablaEquipos tab = new TablaEquipos(this);
            tab.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

        if (e.getSource() == btnMantDelMes) {
            filtrarMantenimientosMes();
        }

        if (e.getSource() == btnMantVencidos) {
            filtrarMantenimientosVencidos();
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
        ObjectResponse response  = cmdMaintenance.findMaintenanceEquipment(new DtoEquipo(),pageData);
        ArrayList<DtoMantenimiento> mantenimientos=(ArrayList<DtoMantenimiento>)response.getRecords();
        this.pageData = response.getpageResponse();
        setDataModel(getMantenimientos(mantenimientos, getDataModel()));
        TablaDatos.setBackground(Color.white);
    }

    public void vaciar() {
        TablaDatos.setBackground(Color.white);
        int i = getDataModel().getRowCount();
        while (i > 0) {
            getDataModel().removeRow(0);
            i--;
        }
    }

    public void setDataModel(DefaultTableModel dataModel) {
        this.dataModel = dataModel;
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
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    int fila = 0;
                    try {
                        fila = TablaDatos.getSelectedRow();
                        TablaDatos.setSelectionBackground(new Color(Integer.parseInt("015289", 16)));
                        if (fila > -1) {
                            btnGuardar.setEnabled(false);
                            DtoMantenimiento mantenimientoSeleccionado = (DtoMantenimiento) TablaDatos.getValueAt(fila, 12);

                            if (mantenimientoSeleccionado.getLabores() != null && mantenimientoSeleccionado.getProovedores().isEmpty()
                                    && mantenimientoSeleccionado.getRecibido() != null && mantenimientoSeleccionado.getAnomalias().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Este equipo fue registrado desde primeros mantenimientos.\n Por favor actualice la información adicional del mantenimiento.", "MetroFarm", JOptionPane.WARNING_MESSAGE);
                            }

                            txtNumForm.setText(mantenimientoSeleccionado.getNumFom());
                            txtNumForm.setEditable(false);
                            txtcodInv.setText(mantenimientoSeleccionado.getEquipo().getCodigoInv());
                            txtcodInv.setEditable(false);
                            txtidMetrologia.setText(mantenimientoSeleccionado.getEquipo().getCodigoMet());
                            txtidMetrologia.setEditable(false);
                            txtNumSerie.setText(mantenimientoSeleccionado.getEquipo().getSerie());
                            txtNumSerie.setEditable(false);
                            txtNombre.setText(mantenimientoSeleccionado.getEquipo().getNombre());
                            txtMarca.setText(mantenimientoSeleccionado.getEquipo().getMarca());
                            txtModelo.setText(mantenimientoSeleccionado.getEquipo().getModelo());
                            txtUbicacion.setText(mantenimientoSeleccionado.getEquipo().getUbicacion());
                            cboTipoMant.setSelectedItem(TipoMantenimiento.getByCode(mantenimientoSeleccionado.getEquipo().getTipo()).getNombre());
                            txtResponsable.setText(mantenimientoSeleccionado.getResponsable());
                            txtRecivido.setText(mantenimientoSeleccionado.getRecibido());
                            txtRegistrado.setText(mantenimientoSeleccionado.getRegistrado().getNombres() + " " + mantenimientoSeleccionado.getRegistrado().getNombres());
                            txaAnomalia.setText(mantenimientoSeleccionado.getAnomalias());
                            txaLabores.setText(mantenimientoSeleccionado.getLabores());
                            txaObservaciones.setText(mantenimientoSeleccionado.getObservaciones());
                            txaProovedores.setText(mantenimientoSeleccionado.getProovedores());
                            txtPrecio.setText(util.makeGroupingNumber(String.valueOf(mantenimientoSeleccionado.getPrecio())));
                            txtcodInv.setEditable(false);
                            txtNombre.setEditable(false);
                            txtNumSerie.setEditable(false);
                            txtModelo.setEditable(false);

                            ImageIcon imagen = new ImageIcon(Base64.decodeBase64(mantenimientoSeleccionado.getEquipo().getFoto()));
                            imagen = AjustarUmagen(imagen);
                            lblFoto.setIcon(imagen);
                        }

                    } catch (NumberFormatException | HeadlessException ex) {
                        JOptionPane.showMessageDialog(null, "Hubo un error :" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();

        }

        if (e.getSource() == txtPrecio) {
            if (txtPrecio.getText().equals("0")) {
                txtPrecio.setText("");
            }
        }

    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == txtPrecio) {
            if (txtPrecio.getText().equals("")) {
                txtPrecio.setText("0");
            }
        }
    }

    public DtoEquipo getEquipment() {
        DtoEquipo equipoFind = new DtoEquipo();
        equipoFind.setCodigoInv(!txtcodInv.getText().trim().equals("") ? txtcodInv.getText().trim() : null);
        equipoFind.setCodigoMet(!txtidMetrologia.getText().trim().equals("") ? txtidMetrologia.getText().trim() : null);
        equipoFind.setSerie(!txtNumSerie.getText().trim().equals("") ? txtNumSerie.getText().trim() : null);

        ObjectResponse response = cmdEquipment.findEquipments(equipoFind, null);
        ArrayList<DtoEquipo> equipments = (ArrayList<DtoEquipo>) response.getRecords();
        if (equipments != null && !equipments.isEmpty()) {
            return equipments.get(0);
        } else {
            return null;
        }

    }

    public DefaultTableModel getMantenimientos(ArrayList<DtoMantenimiento> mantenimientos, DefaultTableModel model) {

        for (DtoMantenimiento c : mantenimientos) {

            Object nuevo[] = {c.getNumFom(), c.getEquipo().getCodigoInv(), c.getEquipo().getCodigoMet(), c.getEquipo().getModelo(), c.getEquipo().getSerie(), c.getEquipo().getNombre(), c.getEquipo().getUbicacion(), TipoMantenimiento.getByCode(c.getTipoMant()).getNombre(), c.getResponsable(), c.getRecibido(), GlobalUtils.getDateFormatted(c.getFechaRegistro()), GlobalUtils.getDateFormatted(c.getFechaProximo()), c};
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

    public void limpiarCampos() {
        txtNumForm.setText("");
        txtcodInv.setText("");
        txtcodInv.setBackground(Color.WHITE);
        txtcodInv.setEditable(true);
        txtidMetrologia.setText("");
        txtidMetrologia.setBackground(Color.WHITE);
        txtidMetrologia.setEditable(true);
        txtMarca.setText("");
        txtMarca.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
        txtNumSerie.setText("");
        txtNumSerie.setBackground(Color.WHITE);
        txtNumSerie.setEditable(true);
        txtNombre.setText("");
        txtNombre.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
        txtResponsable.setText("");
        txtRecivido.setText("");
        txtUbicacion.setText("");
        txtUbicacion.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
        txtModelo.setText("");
        txtModelo.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
        txaAnomalia.setText("");
        txaLabores.setText("");
        txaObservaciones.setText("");
        txaProovedores.setBackground(Color.white);
        txaProovedores.setText("");
        lblFoto.setIcon(null);
        txtPrecio.setText("");
        btnGuardar.setEnabled(true);
        equipoSeleccionado = null;
    }

    public void updateNotifications() {
        Thread updateNotification = new Thread(new ThreadNotification(menu));
        updateNotification.start();
    }

    public JTextArea getTxaProovedores() {
        return txaProovedores;
    }

    public void filtrarMantenimientosMes() {
        ventana.bloquear(frame);
        SwingWorker worker;
        worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                ArrayList<DtoMantenimiento> maintenanceMonth = cmdMaintenance.findMaintenancesMonthScheduledEquipment(new DtoEquipo());

                if (maintenanceMonth != null && !maintenanceMonth.isEmpty()) {
                    vaciar();
                    setDataModel(getMantenimientos(maintenanceMonth, getDataModel()));
                    TablaDatos.setBackground(Color.yellow);
                } else {
                    JOptionPane.showMessageDialog(null, " No se encontraron mantenimientos generales para este mes.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                }
                ventana.desbloquear();
                return null;
            }
        };
        worker.execute();
    }

    public void filtrarMantenimientosVencidos() {
        ventana.bloquear(frame);
        SwingWorker worker;
        worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                ArrayList<DtoMantenimiento> maintenanceMonth = cmdMaintenance.findMaintenancesExpiredEquipment(new DtoEquipo());

                if (maintenanceMonth != null && !maintenanceMonth.isEmpty()) {
                    vaciar();
                    setDataModel(getMantenimientos(maintenanceMonth, getDataModel()));
                    TablaDatos.setBackground(Color.red);
                } else {
                    JOptionPane.showMessageDialog(null, " No se encontraron mantenimientos generales vencidos.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                }
                ventana.desbloquear();
                return null;
            }
        };
        worker.execute();
    }
}
