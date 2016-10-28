/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.views;

import co.com.farmatech.metrofarm.cmd.CmdCalificacion;
import co.com.farmatech.metrofarm.cmd.CmdEquipment;
import co.com.farmatech.metrofarm.resources.Propiedades;
import co.com.farmatech.metrofarm.utils.Formu_Fondo;
import co.com.farmatech.metrofarm.utils.TablaEquipos;
import co.com.farmatech.metrofarm.utils.UtilFarmatech;
import co.com.farmatech.metrofarm.utils.buttons.BotonesMenu;
import co.janker.dto.objresponse.ObjectResponse;
import co.jankins.psf.common.enums.Frecuencias;
import co.jankins.psf.common.enums.TipoCalificacion;
import com.co.farmatech.metrofarm.dto.DtoCalificacion;
import com.co.farmatech.metrofarm.dto.DtoEquipo;
import com.co.farmatech.metrofarm.dto.DtoProgramCalificacion;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
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
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FileUtils;
import ventana.bloqueo.VentanaBloqueo;

/**
 *
 * @author bestrada
 */
public class Calificacion extends JFrame implements ActionListener {

    Formu_Fondo PanelGeneral1, PanelPDF;
    ImageIcon Icofondo, IcoTextura;
    JPanel Panelinfo, panelver;
    JLabel lblCodinv, lblCodmet, lblNumserie, lblnom, lblubica;
    JLabel lblcaldiseño, lblcalins, lblcaloper, lblcaldesem;
    public JTextField txtcodInv, txtCodmet, txtnumse, txtnom, txtUbicac;
    JFileChooser elegir = new JFileChooser();
    public JButton cargar1 = new JButton("Cargar PDF");
    public JButton cargar2 = new JButton("Cargar PDF");
    public JButton cargar3 = new JButton("Cargar PDF");
    public JButton cargar4 = new JButton("Cargar PDF");
    public PagePanel campoPDF = new PagePanel();
    public JButton btnSiguiente = new JButton("Siguiente");
    public JButton btnAtras = new JButton("Atras");
    public JButton btnBuscar = new JButton("Buscar Pàgina");
    String ruta = "co/com/farmatech/metrofarm/resources/imagenes/";
    public ImageIcon ver1 = new ImageIcon(getClass().getClassLoader().getResource(ruta + "ver_pdf.gif"));
    public JButton btnver1 = new JButton("ver", ver1);
    public JButton btnver2 = new JButton("ver", new ImageIcon(getClass().getClassLoader().getResource(ruta + "ver_pdf.gif")));
    public JButton btnver3 = new JButton("ver", new ImageIcon(getClass().getClassLoader().getResource(ruta + "ver_pdf.gif")));
    public JButton btnver4 = new JButton("ver", new ImageIcon(getClass().getClassLoader().getResource(ruta + "ver_pdf.gif")));
    public JButton btnNuevo = new JButton("Nuevo", new ImageIcon(getClass().getClassLoader().getResource(ruta + "nuevo.gif")));
    public JButton btnSalir = new JButton("Salir", new ImageIcon(getClass().getClassLoader().getResource(ruta + "volver.gif")));
    public JButton btnEliminar = new JButton("Eliminar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "eliminar.gif")));
    public JButton btnActualizar = new JButton("Actualizar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "actualizar.gif")));
    public JButton btnBuscarInv = new JButton("Buscar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "buscar.gif")));
    public JButton btnBuscaridMetr = new JButton("Buscar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "buscar.gif")));
    public JButton btnBuscarSerie = new JButton("Buscar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "buscar.gif")));
    public BotonesMenu btnDescargar = new BotonesMenu(new ImageIcon(getClass().getClassLoader().getResource(ruta + "Descargar.png")), new ImageIcon(getClass().getClassLoader().getResource(ruta + "Descargar.png")));
    public BotonesMenu btnsacartabla = new BotonesMenu(new ImageIcon(getClass().getClassLoader().getResource(ruta + "table.png")), new ImageIcon(getClass().getClassLoader().getResource(ruta + "table2.png")));
    String ruta1 = "";
    String ruta2 = "";
    String ruta3 = "";
    String ruta4 = "";
    String extencion;
    static String nomb;
    public static File ArchivoDiseño = null;
    public static File ArchivoInstalacion = null;
    public static File ArchivoOperacion = null;
    public static File ArchivoDesempeño = null;
    public static File ArchivoSeleccionado = null;
    int pagina = 1;
    int paginas = 13;
    File NOAPLICA;
    UtilFarmatech util = new UtilFarmatech();
    private final VentanaBloqueo ventana = new VentanaBloqueo();
    JFrame frame;
    CmdEquipment cmdEquipment = new CmdEquipment();
    public DtoEquipo equipoSeleccionado;
    public DtoCalificacion calificacionSeleccionada;
    String Faltan = "Las siguientes calificaciones no han sido adjuntadas, por lo tanto solo se actualizaran las que ha seleccionado \n";
    CmdCalificacion cmd;

    public Calificacion(MenuPrincipal menu) {

        super("Calificación METROFARM -v1");

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
        cmd = new CmdCalificacion();

        setIconImage(new ImageIcon(getClass().getClassLoader().getResource(ruta + "FARMA.png")).getImage());
        //setLocationRelativeTo(null);
        Font TLetra = new Font("Arial", Font.PLAIN, 12);
        Font Advertencia = new Font("Arial", Font.PLAIN, 15);

        Icofondo = new ImageIcon(getClass().getClassLoader().getResource(ruta + "fondomenu.png"));
        IcoTextura = new ImageIcon(getClass().getClassLoader().getResource(ruta + "textura.png"));

        PanelGeneral1 = new Formu_Fondo(Icofondo);
        PanelGeneral1.setBorder(BorderFactory.createTitledBorder(""));
        //PanelGeneral1.add(fondo);
        PanelGeneral1.setBackground(Color.black);
        PanelGeneral1.setForeground(Color.red);
        PanelGeneral1.reshape(0, 0, 1366, 768);
        PanelGeneral1.setLayout(null);

        FileNameExtensionFilter filtrer = new FileNameExtensionFilter("Archivos pdf", "pdf");
        elegir.setFileFilter(filtrer);
        elegir.setMultiSelectionEnabled(false);
        elegir.setAcceptAllFileFilterUsed(false);

        Panelinfo = new JPanel();
        Panelinfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(Integer.parseInt("015289", 16))), "Datos del equipo"));
        //Panelinfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(Integer.parseInt("015289", 16))), "Datos del equipo", WIDTH, PROPERTIES, TLetra, new Color(Integer.parseInt("EF2F24", 16))));
        Panelinfo.setBackground(getBackground());
        Panelinfo.setForeground(new Color(Integer.parseInt("EF2F24", 16)));
        Panelinfo.reshape(10, 10, 500, 550);
        Panelinfo.setLayout(null);
        Panelinfo.setVisible(true);

        PanelPDF = new Formu_Fondo(IcoTextura);
        PanelPDF.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(Integer.parseInt("015289", 16))), "Lector PDF"));
        //Panelinfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(Integer.parseInt("015289", 16))), "Datos del equipo", WIDTH, PROPERTIES, TLetra, new Color(Integer.parseInt("EF2F24", 16))));
        PanelPDF.setBackground(Color.GRAY);
        PanelPDF.setForeground(new Color(Integer.parseInt("EF2F24", 16)));
        PanelPDF.reshape(515, 10, 740, 715);
        PanelPDF.setLayout(null);
        PanelPDF.setVisible(true);

        panelver = new JPanel();
        panelver.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(Integer.parseInt("015289", 16))), "Calificaciones"));
        //Panelinfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(Integer.parseInt("015289", 16))), "Datos del equipo", WIDTH, PROPERTIES, TLetra, new Color(Integer.parseInt("EF2F24", 16))));
        panelver.setBackground(getBackground());
        panelver.setForeground(new Color(Integer.parseInt("EF2F24", 16)));
        panelver.reshape(20, 210, 300, 320);
        panelver.setLayout(null);
        panelver.setVisible(true);

        NOAPLICA = util.crearFile(Propiedades.class.getResourceAsStream("NO.pdf"), "NO.pdf");

        //panel pdf
        //Dimesion del frame y panel
        Dimension pantalla, cuadro;
        pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        cuadro = this.getSize();
        this.setLocation(((pantalla.width - cuadro.width) / 2),
                (pantalla.height - cuadro.height) / 2);
        campoPDF.setBounds(20, 20, 700, 675);
        campoPDF.setBackground(Color.white);

        btnSiguiente.setBounds(380, 695, 100, 20);
        btnSiguiente.setVisible(true);
        btnSiguiente.addActionListener(this);
        btnAtras.setBounds(255, 695, 100, 20);
        btnAtras.addActionListener(this);
        btnBuscar.setBounds(300, 500, 100, 20);
        btnBuscar.addActionListener(this);

        /////////////////////////////////botones crud
        btnNuevo.setBounds(10, 680, 100, 25);
        btnNuevo.addActionListener(this);
        btnNuevo.setVisible(true);

        btnActualizar.setBounds(110, 680, 100, 25);
        btnActualizar.addActionListener(this);
        btnActualizar.setVisible(true);
        btnActualizar.setEnabled(false);

        btnEliminar.setBounds(210, 680, 100, 25);
        btnEliminar.addActionListener(this);
        btnEliminar.setVisible(true);

        btnSalir.setBounds(310, 680, 100, 25);
        btnSalir.addActionListener(this);
        btnSalir.setVisible(true);

        btnBuscarInv.setBounds(320, 14, 100, 25);
        btnBuscarInv.addActionListener(this);
        btnBuscarInv.setVisible(true);

        btnBuscaridMetr.setBounds(320, 54, 100, 25);
        btnBuscaridMetr.addActionListener(this);
        btnBuscaridMetr.setVisible(true);

        btnBuscarSerie.setBounds(320, 94, 100, 25);
        btnBuscarSerie.addActionListener(this);
        btnBuscarSerie.setVisible(true);

        JLabel mostraequipos = new JLabel("Mostrar equipos");
        mostraequipos.setBounds(205, 580, 200, 20);
        mostraequipos.setFont(TLetra);
        mostraequipos.setForeground(Color.black);
        mostraequipos.setVisible(true);

        btnsacartabla.setBounds(220, 600, 50, 50);
        btnsacartabla.addActionListener(this);
        btnsacartabla.setVisible(true);

        cargar1.addActionListener(this);
        cargar2.addActionListener(this);
        cargar3.addActionListener(this);
        cargar4.addActionListener(this);

        lblCodinv = new JLabel("* Còdigo inventario");
        lblCodinv.setBounds(30, 15, 200, 20);
        lblCodinv.setFont(TLetra);
        lblCodinv.setForeground(Color.black);
        lblCodinv.setVisible(true);

        txtcodInv = new JTextField("");
        txtcodInv.setBounds(200, 15, 100, 20);
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
                                    if (equipo.getFrecCalifDesempenio().equals(Frecuencias.NOAPLICA.getCode()) && equipo.getFrecCalifOPerativa().equals(Frecuencias.NOAPLICA.getCode())) {
                                        JOptionPane.showMessageDialog(null, "Este equipo no esta habilitado para calificaciones", "METROFARM", JOptionPane.ERROR_MESSAGE);
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
        lblCodmet.setBounds(30, 55, 200, 20);
        lblCodmet.setFont(TLetra);
        lblCodmet.setForeground(Color.black);
        lblCodmet.setVisible(true);

        txtCodmet = new JTextField("");
        txtCodmet.setBounds(200, 55, 100, 20);
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
                                    if (equipo.getFrecCalifDesempenio().equals(Frecuencias.NOAPLICA.getCode()) && equipo.getFrecCalifOPerativa().equals(Frecuencias.NOAPLICA.getCode())) {
                                        JOptionPane.showMessageDialog(null, "Este equipo no esta habilitado para calificaciones", "METROFARM", JOptionPane.ERROR_MESSAGE);
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
                                    if (equipo.getFrecCalifDesempenio().equals(Frecuencias.NOAPLICA.getCode()) && equipo.getFrecCalifOPerativa().equals(Frecuencias.NOAPLICA.getCode())) {
                                        JOptionPane.showMessageDialog(null, "Este equipo no esta habilitado para calificaciones", "METROFARM", JOptionPane.ERROR_MESSAGE);
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

                                } else {
                                    JOptionPane.showMessageDialog(null, "No existe un equipo registrado con este número de serie", "METROFARM", JOptionPane.ERROR_MESSAGE);
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
        lblnom.setBounds(30, 135, 200, 20);
        lblnom.setFont(TLetra);
        lblnom.setForeground(Color.black);
        lblnom.setVisible(true);

        txtnom = new JTextField("");
        txtnom.setBounds(200, 135, 200, 20);
        txtnom.setFont(TLetra);
        txtnom.setForeground(Color.black);
        txtnom.setVisible(true);
        txtnom.setEditable(false);

        lblubica = new JLabel("* Ubicaciòn");
        lblubica.setBounds(30, 175, 200, 20);
        lblubica.setFont(TLetra);
        lblubica.setForeground(Color.black);
        lblubica.setVisible(true);

        txtUbicac = new JTextField("");
        txtUbicac.setBounds(200, 175, 200, 20);
        txtUbicac.setFont(TLetra);
        txtUbicac.setForeground(Color.black);
        txtUbicac.setVisible(true);
        txtUbicac.setEditable(false);

        lblcaldiseño = new JLabel("* Calificaciòn de diseño");
        lblcaldiseño.setBounds(30, 15, 200, 20);
        lblcaldiseño.setFont(TLetra);
        lblcaldiseño.setForeground(Color.black);
        lblcaldiseño.setVisible(true);

        cargar1.setBounds(30, 35, 100, 25);
        cargar1.setVisible(true);

        lblcalins = new JLabel("* Calificaciòn de instalaciòn");
        lblcalins.setBounds(30, 80, 200, 20);
        lblcalins.setFont(TLetra);
        lblcalins.setForeground(Color.black);
        lblcalins.setVisible(true);

        cargar2.setBounds(30, 100, 100, 25);
        cargar2.setVisible(true);

        lblcaloper = new JLabel("* Calificaciòn de operaciòn");
        lblcaloper.setBounds(30, 145, 200, 20);
        lblcaloper.setFont(TLetra);
        lblcaloper.setForeground(Color.black);
        lblcaloper.setVisible(true);

        cargar3.setBounds(30, 165, 100, 25);
        cargar3.setVisible(true);

        lblcaldesem = new JLabel("* Calificaciòn de desempeño");
        lblcaldesem.setBounds(30, 210, 200, 20);
        lblcaldesem.setFont(TLetra);
        lblcaldesem.setForeground(Color.black);
        lblcaldesem.setVisible(true);

        cargar4.setBounds(30, 230, 100, 25);
        cargar4.setVisible(true);

        /////////////////////////////////////////////////////////////////////btnver_pdf
        btnver1.setBounds(140, 35, 70, 25);
        btnver1.setVisible(false);
        btnver1.addActionListener(this);

        btnver2.setBounds(140, 100, 70, 25);
        btnver2.setVisible(false);
        btnver2.addActionListener(this);

        btnver3.setBounds(140, 165, 70, 25);
        btnver3.setVisible(false);
        btnver3.addActionListener(this);

        btnver4.setBounds(140, 230, 70, 25);
        btnver4.setVisible(false);
        btnver4.addActionListener(this);

        btnDescargar.setBounds(318, 693, 100, 25);
        btnDescargar.setVisible(false);
        btnDescargar.setToolTipText("Descargar documento");
        btnDescargar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDescargar.addActionListener(this);

        Panelinfo.add(lblCodinv);
        Panelinfo.add(lblCodmet);
        Panelinfo.add(lblNumserie);
        Panelinfo.add(lblnom);
        Panelinfo.add(lblubica);
        Panelinfo.add(txtcodInv);
        Panelinfo.add(txtCodmet);
        Panelinfo.add(txtnumse);
        Panelinfo.add(txtnom);
        Panelinfo.add(txtUbicac);
        Panelinfo.add(panelver);
        Panelinfo.add(btnBuscarInv);
        Panelinfo.add(btnBuscaridMetr);
        Panelinfo.add(btnBuscarSerie);

        panelver.add(lblcaldiseño);
        panelver.add(lblcalins);
        panelver.add(lblcaloper);
        panelver.add(lblcaldesem);

        panelver.add(cargar1);
        panelver.add(cargar2);
        panelver.add(cargar3);
        panelver.add(cargar4);

        panelver.add(btnver1);
        panelver.add(btnver2);
        panelver.add(btnver3);
        panelver.add(btnver4);

        PanelGeneral1.add(Panelinfo);
        PanelGeneral1.add(PanelPDF);
        PanelGeneral1.add(btnNuevo);
        PanelGeneral1.add(btnSalir);
        PanelGeneral1.add(btnEliminar);
        PanelGeneral1.add(btnActualizar);
        PanelGeneral1.add(mostraequipos);
        PanelGeneral1.add(btnsacartabla);

        PanelGeneral1.setVisible(true);
        this.getContentPane().add(PanelGeneral1);

        PanelPDF.add(campoPDF);
        PanelPDF.add(btnAtras);
        PanelPDF.add(btnBuscar);
        PanelPDF.add(btnSiguiente);
        PanelPDF.add(btnDescargar);

        campoPDF.setAutoscrolls(true);

        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cargar1) {
            this.ruta1 = "";

            FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("Documento Pdf", "pdf");
            elegir.setFileFilter(filtroImagen);
            int returnVal = elegir.showDialog(Calificacion.this, "Cargar");

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = elegir.getSelectedFile();
                String direccion = file.getPath();

                this.ruta1 = direccion;
                cargar1.setBackground(Color.blue);

                System.out.println("la ruta 1 es: " + ruta1);

            } else {
                System.out.println("Carga cancelada por el usuario");
            }

        }
        if (e.getSource() == cargar2) {

            this.ruta2 = "";

            FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("Documento Pdf", "pdf");
            elegir.setFileFilter(filtroImagen);
            int returnVal = elegir.showDialog(Calificacion.this, "Cargar");

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = elegir.getSelectedFile();
                String direccion = file.getPath();

                this.ruta2 = direccion;
                cargar2.setBackground(Color.blue);

                System.out.println("la ruta 2 es: " + ruta2);

            } else {
                System.out.println("Carga cancelada por el usuario");
            }
        }

        if (e.getSource() == cargar3) {

            this.ruta3 = "";

            FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("Documento Pdf", "pdf");
            elegir.setFileFilter(filtroImagen);
            int returnVal = elegir.showDialog(Calificacion.this, "Cargar");

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = elegir.getSelectedFile();
                String direccion = file.getPath();

                this.ruta3 = direccion;
                cargar3.setBackground(Color.blue);

                System.out.println("la ruta 3 es: " + ruta3);

            } else {
                System.out.println("Carga cancelada por el usuario");
            }
        }

        if (e.getSource() == cargar4) {

            this.ruta4 = "";

            FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("Documento Pdf", "pdf");
            elegir.setFileFilter(filtroImagen);
            int returnVal = elegir.showDialog(Calificacion.this, "Cargar");

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = elegir.getSelectedFile();
                String direccion = file.getPath();

                this.ruta4 = direccion;
                cargar4.setBackground(Color.blue);

                System.out.println("la ruta 4 es: " + ruta4);

            } else {
                System.out.println("Carga cancelada por el usuario");
            }
        }

        if (e.getSource() == btnNuevo) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    pagina = 0;
                    campoPDF.showPage(null);
                    ArchivoDesempeño = null;
                    ArchivoDiseño = null;
                    ArchivoInstalacion = null;
                    ArchivoOperacion = null;
                    ArchivoSeleccionado = null;
                    FileOutputStream ArchivoDiseño = null;
                    FileOutputStream ArchivoInstalacion = null;
                    FileOutputStream ArchivoOperacion = null;
                    FileOutputStream ArchivoDesempeño = null;
                    FileOutputStream ArchivoSeleccionado = null;
                    btnver1.setVisible(false);
                    btnver2.setVisible(false);
                    btnver3.setVisible(false);
                    btnver4.setVisible(false);
                    txtcodInv.setText("");
                    txtcodInv.setBackground(Color.WHITE);
                    txtcodInv.setEditable(true);
                    txtCodmet.setText("");
                    txtCodmet.setBackground(Color.WHITE);
                    txtCodmet.setEditable(true);
                    txtnumse.setText("");
                    txtnumse.setBackground(Color.WHITE);
                    txtnumse.setEditable(true);
                    txtnom.setText("");
                    txtnom.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                    txtUbicac.setText("");
                    txtUbicac.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                    btnDescargar.setVisible(false);
                    btnActualizar.setEnabled(false);
                    ruta1 = "";
                    ruta2 = "";
                    ruta3 = "";
                    ruta4 = "";

                    equipoSeleccionado = null;
                    calificacionSeleccionada = null;
                    cargar1.setBackground(null);
                    cargar2.setBackground(null);
                    cargar3.setBackground(null);
                    cargar4.setBackground(null);
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();

        }

        if (e.getSource() == btnSalir) {
            int respuesta = JOptionPane.showConfirmDialog(null, "Esta seguro que desea salir", "METROFARM", JOptionPane.YES_NO_OPTION);
            switch (respuesta) {
                case JOptionPane.YES_OPTION:
                    this.dispose();
                    System.gc();
                    break;
                case JOptionPane.NO_OPTION:
                    break;
            }
        }

        if (e.getSource() == btnsacartabla) {
            TablaEquipos tab = new TablaEquipos(this);
            tab.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

        /*
         if (e.getSource() == btnEliminar) {
         ventana.bloquear(frame);
         SwingWorker worker;
         worker = new SwingWorker() {
         @Override
         protected Object doInBackground() throws Exception {
         String CodInv;
         CodInv = txtcodInv.getText();

         boolean valido = true;
         if (CodInv.equals("")) {
         valido = false;
         }

         if (valido == true) {

         Conexion con = new Conexion();

         bussinessManager manager = new bussinessManager();

         boolean result = manager.eliminarCalificacion(con.getCon(), CodInv);
         boolean eliminarprogramaciones = manager.eliminarprogramaciones(con.getCon(), CodInv);
         System.out.print(">" + result);

         if (result == false) {
         JOptionPane.showMessageDialog(null, " No existe una calificaciòn asociada a este equipo.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
         } else {

         JOptionPane.showMessageDialog(null, " Calificaciones eliminadas", "METROFARM", JOptionPane.INFORMATION_MESSAGE);

         pagina = 0;
         campoPDF.showPage(null);

         ArchivoSeleccionado = null;
         FileOutputStream ArchivoDiseño = null;
         FileOutputStream ArchivoInstalacion = null;
         FileOutputStream ArchivoOperacion = null;
         FileOutputStream ArchivoDesempeño = null;
         FileOutputStream ArchivoSeleccionado = null;
         btnver1.setVisible(false);
         btnver2.setVisible(false);
         btnver3.setVisible(false);
         btnver4.setVisible(false);
         txtcodInv.setText("");
         txtcodInv.setBackground(Color.WHITE);
         txtcodInv.setEditable(true);
         txtCodmet.setText("");
         txtCodmet.setBackground(Color.WHITE);
         txtCodmet.setEditable(true);

         txtnumse.setText("");
         txtnumse.setBackground(Color.WHITE);
         txtnumse.setEditable(true);
         txtnom.setText("");
         txtnom.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
         txtUbicac.setText("");
         txtUbicac.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));

         btnGuardar.setEnabled(true);
         btnActualizar.setEnabled(false);

         ruta1 = "";
         ruta2 = "";
         ruta3 = "";
         ruta4 = "";

         }

         } else {
         JOptionPane.showMessageDialog(null, "Ingrese un codigo de inventario valido para poder eliminar la calificaciòn.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
         }
         ventana.desbloquear();
         return null;
         }
         };
         worker.execute();

         }
         * */
        if (e.getSource() == btnActualizar) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    ArrayList<DtoProgramCalificacion> programaciones = new ArrayList<DtoProgramCalificacion>();
                    File diseño = null;
                    File inst = null;
                    File opera = null;
                    File desem = null;
                    Calendar calActual = Calendar.getInstance();
                    if (calificacionSeleccionada != null) {
                        boolean falta = false;
                        boolean valido = true;

                        if (valido == true) {
                            if (!"".equals(ruta1)) {
                                diseño = new File(ruta1);
                                calificacionSeleccionada.setFileDiseno(FileUtils.readFileToByteArray(diseño));
                            } else {
                                falta = true;
                                Faltan += "*Diseño \n";
                            }

                            if (!"".equals(ruta2)) {
                                inst = new File(ruta2);
                                calificacionSeleccionada.setFileInstalacion(FileUtils.readFileToByteArray(inst));
                            } else {
                                falta = true;
                                Faltan += "*Instalación \n";
                            }

                            if (!"".equals(ruta3)) {
                                opera = new File(ruta3);
                                calificacionSeleccionada.setFileOperacion(FileUtils.readFileToByteArray(opera));
                                calActual.setTime(new Date());
                                calActual.add(Calendar.MONTH, calificacionSeleccionada.getEquipo().getFrecCalifOPerativa().intValue());//sumamos la frecuencia para que calcule el proximo

                                DtoProgramCalificacion programacionOperacion = new DtoProgramCalificacion();
                                programacionOperacion.setTipoCalificacion(TipoCalificacion.CALIFICACION_OPERACION.getCode());
                                programacionOperacion.setEquipo(calificacionSeleccionada.getEquipo());
                                programacionOperacion.setFechaRegistro(new Date());
                                programacionOperacion.setFechaProxima(calActual.getTime());

                                programaciones.add(programacionOperacion);

                            } else {
                                falta = true;
                                Faltan += "*Operacion \n";
                            }

                            if (!"".equals(ruta4)) {
                                desem = new File(ruta4);
                                calificacionSeleccionada.setFiledesempeno(FileUtils.readFileToByteArray(desem));
                                calActual.setTime(new Date());
                                calActual.add(Calendar.MONTH, calificacionSeleccionada.getEquipo().getFrecCalifDesempenio().intValue());//sumamos la frecuencia para que calcule el proximo

                                DtoProgramCalificacion programacionDesempenio = new DtoProgramCalificacion();
                                programacionDesempenio.setTipoCalificacion(TipoCalificacion.CALIFICACION_DESEMPENIO.getCode());
                                programacionDesempenio.setEquipo(calificacionSeleccionada.getEquipo());
                                programacionDesempenio.setFechaRegistro(new Date());
                                programacionDesempenio.setFechaProxima(calActual.getTime());

                                programaciones.add(programacionDesempenio);
                            } else {
                                falta = true;
                                Faltan += "*Desempeño \n";
                            }

                            //Verificamos que el equipo si exista
                            int respuesta = JOptionPane.YES_OPTION;
                            if (falta == true) {
                                respuesta = JOptionPane.showConfirmDialog(null, Faltan, "METROFARM", JOptionPane.YES_NO_OPTION);
                                switch (respuesta) {
                                    case JOptionPane.NO_OPTION:
                                        break;
                                }
                            }

                            if (respuesta == JOptionPane.YES_OPTION) {
                                calificacionSeleccionada.setProgramCalif(programaciones);
                                boolean updated = cmd.createCalification(calificacionSeleccionada);

                                if (updated) {
                                    JOptionPane.showMessageDialog(null, "Calificaciones actualizadas con éxito");
                                    pagina = 0;
                                    campoPDF.showPage(null);
                                    ArchivoDesempeño = null;
                                    ArchivoDiseño = null;
                                    ArchivoInstalacion = null;
                                    ArchivoOperacion = null;
                                    ArchivoSeleccionado = null;
                                    FileOutputStream ArchivoDiseño = null;
                                    FileOutputStream ArchivoInstalacion = null;
                                    FileOutputStream ArchivoOperacion = null;
                                    FileOutputStream ArchivoDesempeño = null;
                                    FileOutputStream ArchivoSeleccionado = null;
                                    btnver1.setVisible(false);
                                    btnver2.setVisible(false);
                                    btnver3.setVisible(false);
                                    btnver4.setVisible(false);
                                    txtcodInv.setText("");
                                    txtcodInv.setBackground(Color.WHITE);
                                    txtcodInv.setEditable(true);
                                    txtCodmet.setText("");
                                    txtCodmet.setBackground(Color.WHITE);
                                    txtCodmet.setEditable(true);
                                    txtnumse.setText("");
                                    txtnumse.setBackground(Color.WHITE);
                                    txtnumse.setEditable(true);
                                    txtnom.setText("");
                                    txtnom.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                    txtUbicac.setText("");
                                    txtUbicac.setBackground(new Color(Integer.parseInt("F0F0F0", 16)));
                                    btnDescargar.setVisible(false);
                                    ruta1 = "";
                                    ruta2 = "";
                                    ruta3 = "";
                                    ruta4 = "";
                                    btnActualizar.setEnabled(false);
                                    cargar1.setBackground(null);
                                    cargar2.setBackground(null);
                                    cargar3.setBackground(null);
                                    cargar4.setBackground(null);
                                    calificacionSeleccionada=null;
                                } else {
                                    JOptionPane.showMessageDialog(null, "No se han podido actualizar las calificaciones");
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Debes seleccionar un equipo", "MetroFarm", JOptionPane.WARNING_MESSAGE);

                    }

                    Faltan = "Las siguientes calificaciones no han sido adjuntadas, por lo tanto solo se actualizaran las que ha seleccionado \n";
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
                        buscarCalificacion(equipo);
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
                        JOptionPane.showMessageDialog(null, " Debe digitar el código de metología del equipo que desea buscar.");
                    } else {
                        DtoEquipo equipo = new DtoEquipo();
                        equipo.setCodigoMet(codigomet);
                        buscarCalificacion(equipo);
                    }
                    ventana.desbloquear();
                    return null;
                }
            };

            worker.execute();

        }

        if (e.getSource()
                == btnBuscarSerie) {
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
                        equipo.setCodigoMet(serie);
                        buscarCalificacion(equipo);
                    }
                    ventana.desbloquear();
                    return null;
                }
            };

            worker.execute();

        }

        if (e.getSource()
                == btnver1) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    ArchivoSeleccionado = ArchivoDiseño;
                    btnDescargar.setVisible(true);
                    campoPDF.showPage(null);
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();
        }

        if (e.getSource()
                == btnver2) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    ArchivoSeleccionado = ArchivoInstalacion;
                    btnDescargar.setVisible(true);
                    campoPDF.showPage(null);
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();

        }

        if (e.getSource()
                == btnver3) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    ArchivoSeleccionado = ArchivoOperacion;
                    btnDescargar.setVisible(true);
                    campoPDF.showPage(null);
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();

        }

        if (e.getSource()
                == btnver4) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    ArchivoSeleccionado = ArchivoDesempeño;
                    btnDescargar.setVisible(true);
                    campoPDF.showPage(null);
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();

        }

        if (e.getSource() == btnDescargar) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    int returnVal = elegir.showDialog(Calificacion.this, "Guardar");

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        InputStream in = null;
                        try {
                            File Archivo = new File(String.valueOf(elegir.getSelectedFile().toString()));
                            String Ruta = String.valueOf(elegir.getSelectedFile().getPath() + ".pdf");
                            File destino = new File(Ruta);
                            in = new FileInputStream(ArchivoSeleccionado);
                            OutputStream out = new FileOutputStream(destino);

                            byte[] buf = new byte[1024];
                            int len;

                            while ((len = in.read(buf)) > 0) {
                                out.write(buf, 0, len);
                            }
                            in.close();
                            out.close();

                        } catch (FileNotFoundException ex) {
                            JOptionPane.showMessageDialog(null, "Hubo un error :" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Hubo un error :" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        } finally {
                            try {
                                in.close();
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, "Hubo un error :" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }

                    } else {
                        System.out.println("Carga cancelada por el usuario");
                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();

        }

        if (e.getSource()
                == btnSiguiente) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    if (ArchivoSeleccionado == null) {
                        JOptionPane.showMessageDialog(null, "Para visualizar una calificación debe cargarla y luego presionar el boton ver", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                    } else {

                        pagina += 1;
                        if (pagina > paginas || pagina < 1) {
                            pagina = 1;
                        }
                        try {

                            //Ubicación del archivo pdf
                            RandomAccessFile raf = new RandomAccessFile(ArchivoSeleccionado, "r");
                            FileChannel channel = raf.getChannel();
                            MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                            PDFFile pdffile = new PDFFile(buf);

                            // muestra la pagina en la posición de la variable pagina page channel.size()
                            PDFPage page = pdffile.getPage(pagina);
                            paginas = pdffile.getNumPages(); //numero de paginas del documento
                            campoPDF.useZoomTool(true);
                            campoPDF.showPage(page);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Hubo un error :" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        repaint();
                        campoPDF.repaint();

                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();

        }

        if (e.getSource()
                == btnAtras) {
            ventana.bloquear(frame);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    pagina -= 1;
                    if (pagina > paginas || pagina < 1) {
                        pagina = 1;
                    }
                    try {

                        //ubicación del archivo pdf.
                        RandomAccessFile raf = new RandomAccessFile(ArchivoSeleccionado, "r");
                        FileChannel channel = raf.getChannel();
                        MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                        PDFFile pdffile = new PDFFile(buf);

                        // muestra la pagina en la posición de la variable pagina page channel.size()
                        PDFPage page = pdffile.getPage(pagina);
                        paginas = pdffile.getNumPages(); ////numero de paginas del documento
                        campoPDF.setClip(null);
                        campoPDF.useZoomTool(false);
                        campoPDF.showPage(page);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Hubo un error :" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    repaint();
                    campoPDF.repaint();
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();

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

    public File createTemporalFile(byte[] array, String name) {
        File Archivo = null;
        try {
            Archivo = File.createTempFile("Pdf" + name, ".pdf");
            try (FileOutputStream fileOuputStream = new FileOutputStream(Archivo)) {
                fileOuputStream.write(array);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Archivo;
    }

    public void buscarCalificacion(DtoEquipo equipo) {
        ArrayList<DtoCalificacion> calibracion = cmd.findCalifications(equipo);
        if (!calibracion.isEmpty()) {
            txtcodInv.setText(calibracion.get(0).getEquipo().getCodigoInv());
            txtcodInv.setEditable(false);
            txtCodmet.setText(calibracion.get(0).getEquipo().getCodigoMet());
            txtCodmet.setEditable(false);
            txtnumse.setText(calibracion.get(0).getEquipo().getSerie());
            txtnumse.setEditable(false);
            txtnom.setText(calibracion.get(0).getEquipo().getNombre());
            txtUbicac.setText(calibracion.get(0).getEquipo().getUbicacion());
            File diseño = createTemporalFile(calibracion.get(0).getFileDiseno(), "Diseno");
            File Inst = createTemporalFile(calibracion.get(0).getFileInstalacion(), "Instalacion");
            File Ope = createTemporalFile(calibracion.get(0).getFileOperacion(), "Operacion");
            File Desemp = createTemporalFile(calibracion.get(0).getFiledesempeno(), "Desempenio");
            ArchivoDiseño = diseño;
            ArchivoInstalacion = Inst;
            ArchivoOperacion = Ope;
            ArchivoDesempeño = Desemp;
            btnver1.setVisible(true);
            btnver2.setVisible(true);
            btnver3.setVisible(true);
            btnver4.setVisible(true);
            btnActualizar.setEnabled(true);
            calificacionSeleccionada=calibracion.get(0);
        } else {
            JOptionPane.showMessageDialog(null, " No se encontraron datos con la informacion ingresada.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
