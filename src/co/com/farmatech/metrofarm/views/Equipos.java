/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.views;

import co.com.farmatech.metrofarm.cmd.CmdEquipment;
import co.com.farmatech.metrofarm.cmd.CmdUtils;
import co.com.farmatech.metrofarm.utils.Formu_Fondo;
import co.com.farmatech.metrofarm.utils.PanelPaginador;
import co.com.farmatech.metrofarm.utils.tablas.aparienciaTabla;
import co.com.farmatech.metrofarm.views.notification.ThreadNotification;
import co.janker.dto.objresponse.ObjectResponse;
import co.jankins.psf.common.enums.Estados;
import co.jankins.psf.common.enums.Frecuencias;
import co.jankins.psf.common.enums.TipoEquipo;
import co.jankins.psf.common.global.GlobalUtils;
import com.co.farmatech.metrofarm.dto.DtoEquipo;
import com.co.farmatech.metrofarm.dto.PageData;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
import org.apache.commons.io.FileUtils;
import ventana.bloqueo.VentanaBloqueo;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Bryan
 */
public class Equipos extends JFrame implements ActionListener, MouseListener {
    //Tabla

    public JTable TablaDatos;
    JScrollPane PanelDatos;
    private Object[][] DetallesIdentificacion = new Object[0][0];
    private Object[] ColumnasIdentificacion = {"Código de Inventario", "Código de Metrologia", "Marca", "Modelo", "Número de Serie", "Nombre", "Tipo de Artefacto", "Clasificación del equipo", "Estado", "Cliente", "Observaciones", "Fecha de registro", "Frecuencia Mantenimiento", "Frecuencia Calibración", "Frecuencia Calif Opercacion", "Frecuencia Calif Desempeño", "Objeto"};
    private DefaultTableModel dataModel = new DefaultTableModel(DetallesIdentificacion, ColumnasIdentificacion);
    JLabel lblcodInv, lblidMetrologia, lblNumSerie, lblNombre, lblMarca, lblClasificacion, lblEstado, lblDescripcion, lblFrecuenciaMant, lblFrecuenciaCal, lblFrecuenciaCalifO, lblFrecuenciaCalifD, lblFoto, lblModelo, lblCliente, lblTipo, lblAdvertencia;
    JTextField txtcodInv, txtidMetrologia, txtNumSerie, txtNombre, txtMarca, txtModelo, txtCliente;
    JTextArea txaDescripcion;
    JComboBox cboClasificacion, cboEstado, cboFrecuenciaMant, cboFrecuenciaCal, cboFrecuenciaCalifO, cboFrecuenciaCalifD, cboTipo;
    Font TLetra = new Font("Arial", Font.PLAIN, 12);
    Font Advertencia = new Font("Arial", Font.PLAIN, 15);
    String ruta = "co/com/farmatech/metrofarm/resources/imagenes/";
    JButton btnNuevo = new JButton("Nuevo", new ImageIcon(getClass().getClassLoader().getResource(ruta + "nuevo.gif")));
    JButton btnActualizar = new JButton("Actualizar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "actualizar.gif")));
    JButton btnGuardar = new JButton("Insertar Equipo de terceros", new ImageIcon(getClass().getClassLoader().getResource(ruta + "guardar.gif")));
    JButton btnBuscarInv = new JButton("", new ImageIcon(getClass().getClassLoader().getResource(ruta + "buscar.gif")));
    JButton btnBuscaridMetr = new JButton("", new ImageIcon(getClass().getClassLoader().getResource(ruta + "buscar.gif")));
    JButton btnBuscarSerie = new JButton("", new ImageIcon(getClass().getClassLoader().getResource(ruta + "buscar.gif")));
    JButton btnSalir = new JButton("Salir", new ImageIcon(getClass().getClassLoader().getResource(ruta + "volver.gif")));
    JButton btnCambio = new JButton("Nuevo registro de cambios", new ImageIcon(getClass().getClassLoader().getResource(ruta + "cambios.png")));
    JButton btnCargar = new JButton("Cargar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "cargar.png")));
    JButton btnregistroPasado = new JButton("Registrar ultimos Mantenimientos,calificaciones,calibraciones", new ImageIcon(getClass().getClassLoader().getResource(ruta + "cargar.png")));
    JPanel Panelinfo, Panelpermiso, Panelopcion, PanelFoto;
    ImageIcon Icofondo;
    Formu_Fondo PanelGeneral1;
    String Ruta, extencion, nombre;
    private JMenuBar Menu;
    private JMenu menu1, menu2;
    private JMenuItem m11, m21;
    private final VentanaBloqueo ventana = new VentanaBloqueo();
    CmdEquipment cmd;
    MenuPrincipal menu;
    PanelPaginador panelPaginador;
    PageData pageData;

    public Equipos(MenuPrincipal menu) {



        super("Equipos e instrumentos METROFARM -v1");

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

        this.menu = menu;

        cmd = new CmdEquipment();
        panelPaginador = new PanelPaginador();
        panelPaginador.setBounds(60, 650, 300, 29);

        pageData = panelPaginador.getPageData();
        pageData.setRowsPerPage(5);

        Menu = new JMenuBar();
        menu1 = new JMenu("Registro de cambios");
        menu2 = new JMenu("Sincronización Equipos");
        Menu.add(menu1);
        Menu.add(menu2);

        m11 = new JMenuItem("Ver historial");
        m11.setIcon(new ImageIcon(getClass().getClassLoader().getResource(ruta + "historial.png")));
        menu1.add(m11);

        m21 = new JMenuItem("Sincronizar con Ofimática");
        m21.setIcon(new ImageIcon(getClass().getClassLoader().getResource(ruta + "historial.png")));
        menu2.add(m21);

        lblAdvertencia = new JLabel("ATENCIÓN: Los equipos que se encuentran en color rojo deben ser actualizados en el sistema puesto que acaban de ser enviados desde ofimática.");
        lblAdvertencia.setForeground(Color.red);
        lblAdvertencia.setBounds(150, 607, 1000, 50);
        lblAdvertencia.setFont(Advertencia);
        lblAdvertencia.setVisible(true);

        lblFoto = new JLabel();
        lblFoto.setBackground(Color.black);
        lblFoto.setBorder(BorderFactory.createEmptyBorder());
        lblFoto.setOpaque(true);
        lblFoto.setBackground(Color.white);
        lblFoto.reshape(25, 25, 250, 250);
        lblFoto.setLayout(null);

        Ruta = "";

        Icofondo = new ImageIcon(getClass().getClassLoader().getResource(ruta + "fondomenu.png"));
        PanelGeneral1 = new Formu_Fondo(Icofondo);
        PanelGeneral1.setBorder(BorderFactory.createTitledBorder(""));
        //PanelGeneral1.add(fondo);
        PanelGeneral1.setBackground(Color.black);
        PanelGeneral1.setForeground(Color.red);
        PanelGeneral1.reshape(0, 0, 1366, 768);
        PanelGeneral1.setLayout(null);

        PanelFoto = new JPanel();
        PanelFoto.setBorder(BorderFactory.createTitledBorder("Cargue foto de él equipo/instrumento"));
        //PanelGeneral1.add(fondo);
        PanelFoto.setForeground(Color.red);
        PanelFoto.reshape(50, 20, 300, 300);
        PanelFoto.setLayout(null);
        PanelFoto.add(lblFoto);



        lblcodInv = new JLabel("* Código de inventario");
        lblcodInv.setBounds(430, 40, 150, 20);
        lblcodInv.setFont(TLetra);
        lblcodInv.setForeground(Color.black);
        lblcodInv.setVisible(true);

        txtcodInv = new JTextField("");
        txtcodInv.setBounds(600, 40, 100, 20);
        txtcodInv.setFont(TLetra);
        txtcodInv.setForeground(Color.black);
        txtcodInv.setVisible(true);

        btnBuscarInv.setBounds(710, 40, 50, 25);
        btnBuscarInv.setVisible(true);

        lblidMetrologia = new JLabel("* Identificación de metrología");
        lblidMetrologia.setBounds(430, 70, 200, 20);
        lblidMetrologia.setFont(TLetra);
        lblidMetrologia.setForeground(Color.black);
        lblidMetrologia.setVisible(true);

        txtidMetrologia = new JTextField("");
        txtidMetrologia.setBounds(600, 70, 100, 20);
        txtidMetrologia.setFont(TLetra);
        txtidMetrologia.setForeground(Color.black);
        txtidMetrologia.setVisible(true);

        btnBuscaridMetr.setBounds(710, 70, 50, 25);
        btnBuscaridMetr.setVisible(true);

        lblNumSerie = new JLabel("* Número de serie");
        lblNumSerie.setBounds(430, 100, 200, 20);
        lblNumSerie.setFont(TLetra);
        lblNumSerie.setForeground(Color.black);
        lblNumSerie.setVisible(true);

        txtNumSerie = new JTextField("");
        txtNumSerie.setBounds(600, 100, 100, 20);
        txtNumSerie.setFont(TLetra);
        txtNumSerie.setForeground(Color.black);
        txtNumSerie.setVisible(true);

        btnBuscarSerie.setBounds(710, 100, 50, 25);
        btnBuscarSerie.setVisible(true);

        lblNombre = new JLabel("* Nombre del equipo");
        lblNombre.setBounds(430, 130, 200, 20);
        lblNombre.setFont(TLetra);
        lblNombre.setForeground(Color.black);
        lblNombre.setVisible(true);

        txtNombre = new JTextField("");
        txtNombre.setBounds(600, 130, 150, 20);
        txtNombre.setFont(TLetra);
        txtNombre.setForeground(Color.black);
        txtNombre.setVisible(true);

        lblMarca = new JLabel("* Marca");
        lblMarca.setBounds(430, 160, 200, 20);
        lblMarca.setFont(TLetra);
        lblMarca.setForeground(Color.black);
        lblMarca.setVisible(true);

        txtMarca = new JTextField("");
        txtMarca.setBounds(600, 160, 150, 20);
        txtMarca.setFont(TLetra);
        txtMarca.setForeground(Color.black);
        txtMarca.setVisible(true);

        lblClasificacion = new JLabel("* Clasificación");
        lblClasificacion.setBounds(430, 190, 200, 20);
        lblClasificacion.setFont(TLetra);
        lblClasificacion.setForeground(Color.black);
        lblClasificacion.setVisible(true);

        cboClasificacion = new JComboBox();
        cboClasificacion.addItem("");

        cboClasificacion.addItem("Laboratorio");
        cboClasificacion.addItem("Oficina");
        cboClasificacion.addItem("Producción líquidos");
        cboClasificacion.addItem("Producción sólidos");
        cboClasificacion.addItem("Producción cosméticos");
        cboClasificacion.addItem("Linea de empaque líquidos");
        cboClasificacion.addItem("Linea de empaque sólidos");
        cboClasificacion.addItem("Linea de empaque cosméticos");
        cboClasificacion.addItem("Tecnica");
        cboClasificacion.addItem("Magistrales");
        cboClasificacion.reshape(600, 190, 200, 20);
        cboClasificacion.setFont(TLetra);
        cboClasificacion.setForeground(Color.black);
        cboClasificacion.setVisible(true);

        lblModelo = new JLabel("* Modelo");
        lblModelo.setBounds(900, 130, 200, 20);
        lblModelo.setFont(TLetra);
        lblModelo.setForeground(Color.black);
        lblModelo.setVisible(true);

        txtModelo = new JTextField("");
        txtModelo.setBounds(1070, 130, 150, 20);
        txtModelo.setFont(TLetra);
        txtModelo.setForeground(Color.black);
        txtModelo.setVisible(true);

        lblEstado = new JLabel("* Estado");
        lblEstado.setBounds(900, 160, 200, 20);
        lblEstado.setFont(TLetra);
        lblEstado.setForeground(Color.black);
        lblEstado.setVisible(true);

        cboEstado = new JComboBox();
        cboEstado.addItem("");
        cboEstado.addItem("ACTIVADO");
        cboEstado.addItem("DESACTIVADO");
        cboEstado.reshape(1070, 160, 100, 20);
        cboEstado.setFont(TLetra);
        cboEstado.setForeground(Color.black);
        cboEstado.setVisible(true);

        lblDescripcion = new JLabel(" Descripción");
        lblDescripcion.setBounds(900, 190, 200, 20);
        lblDescripcion.setFont(TLetra);
        lblDescripcion.setForeground(Color.black);
        lblDescripcion.setVisible(true);

        // Se inicializa la variable
        // Por ende cambiamos el color de letra
        txaDescripcion = new JTextArea("Introduzca texto");
        txaDescripcion.setBounds(910, 220, 250, 100);
        txaDescripcion.setEditable(true);
        txaDescripcion.setBackground(Color.GRAY);
        txaDescripcion.setFont(TLetra);
        txaDescripcion.setForeground(new Color(255, 255, 255));
        txaDescripcion.setVisible(true);
        txaDescripcion.setLineWrap(true);//Provocar salto de linea cortando palabra
        txaDescripcion.setWrapStyleWord(true);//Provocar salto de linea despues del espacio

        lblFrecuenciaMant = new JLabel("* Frecuencia mantenimiento");
        lblFrecuenciaMant.setBounds(430, 220, 200, 20);
        lblFrecuenciaMant.setFont(TLetra);
        lblFrecuenciaMant.setForeground(Color.black);
        lblFrecuenciaMant.setVisible(true);

        cboFrecuenciaMant = new JComboBox();
        cboFrecuenciaMant.addItem("No Aplica");
        cboFrecuenciaMant.addItem("1 Mes");
        cboFrecuenciaMant.addItem("2 Meses");
        cboFrecuenciaMant.addItem("3 Meses");
        cboFrecuenciaMant.addItem("4 Meses");
        cboFrecuenciaMant.addItem("5 Meses");
        cboFrecuenciaMant.addItem("6 Meses");
        cboFrecuenciaMant.addItem("7 Meses");
        cboFrecuenciaMant.addItem("8 Meses");
        cboFrecuenciaMant.addItem("9 Meses");
        cboFrecuenciaMant.addItem("10 Meses");
        cboFrecuenciaMant.addItem("11 Meses");
        cboFrecuenciaMant.addItem("12 Meses");
        cboFrecuenciaMant.addItem("13 Meses");
        cboFrecuenciaMant.addItem("14 Meses");
        cboFrecuenciaMant.addItem("15 Meses");
        cboFrecuenciaMant.addItem("16 Meses");
        cboFrecuenciaMant.addItem("17 Meses");
        cboFrecuenciaMant.addItem("18 Meses");
        cboFrecuenciaMant.addItem("19 Meses");
        cboFrecuenciaMant.addItem("20 Meses");
        cboFrecuenciaMant.addItem("21 Meses");
        cboFrecuenciaMant.addItem("22 Meses");
        cboFrecuenciaMant.addItem("23 Meses");
        cboFrecuenciaMant.addItem("24 Meses");
        cboFrecuenciaMant.setBounds(600, 220, 90, 20);
        cboFrecuenciaMant.setFont(TLetra);
        cboFrecuenciaMant.setForeground(Color.black);
        cboFrecuenciaMant.setVisible(true);

        lblFrecuenciaCal = new JLabel("* Frecuencia de calibración");
        lblFrecuenciaCal.setBounds(430, 250, 200, 20);
        lblFrecuenciaCal.setFont(TLetra);
        lblFrecuenciaCal.setForeground(Color.black);
        lblFrecuenciaCal.setVisible(true);

        cboFrecuenciaCal = new JComboBox();
        cboFrecuenciaCal.addItem("No Aplica");
        cboFrecuenciaCal.addItem("1 Mes");
        cboFrecuenciaCal.addItem("2 Meses");
        cboFrecuenciaCal.addItem("3 Meses");
        cboFrecuenciaCal.addItem("4 Meses");
        cboFrecuenciaCal.addItem("5 Meses");
        cboFrecuenciaCal.addItem("6 Meses");
        cboFrecuenciaCal.addItem("7 Meses");
        cboFrecuenciaCal.addItem("8 Meses");
        cboFrecuenciaCal.addItem("9 Meses");
        cboFrecuenciaCal.addItem("10 Meses");
        cboFrecuenciaCal.addItem("11 Meses");
        cboFrecuenciaCal.addItem("12 Meses");
        cboFrecuenciaCal.addItem("13 Meses");
        cboFrecuenciaCal.addItem("14 Meses");
        cboFrecuenciaCal.addItem("15 Meses");
        cboFrecuenciaCal.addItem("16 Meses");
        cboFrecuenciaCal.addItem("17 Meses");
        cboFrecuenciaCal.addItem("18 Meses");
        cboFrecuenciaCal.addItem("19 Meses");
        cboFrecuenciaCal.addItem("20 Meses");
        cboFrecuenciaCal.addItem("21 Meses");
        cboFrecuenciaCal.addItem("22 Meses");
        cboFrecuenciaCal.addItem("23 Meses");
        cboFrecuenciaCal.addItem("24 Meses");

        cboFrecuenciaCal.setBounds(600, 250, 90, 20);
        cboFrecuenciaCal.setFont(TLetra);
        cboFrecuenciaCal.setForeground(Color.black);
        cboFrecuenciaCal.setVisible(true);

        lblFrecuenciaCalifO = new JLabel("* Frecuencia calificación operacion");
        lblFrecuenciaCalifO.setBounds(430, 280, 200, 20);
        lblFrecuenciaCalifO.setFont(TLetra);
        lblFrecuenciaCalifO.setForeground(Color.black);
        lblFrecuenciaCalifO.setVisible(true);

        cboFrecuenciaCalifO = new JComboBox();
        cboFrecuenciaCalifO.addItem("No Aplica");
        cboFrecuenciaCalifO.addItem("1 Mes");
        cboFrecuenciaCalifO.addItem("2 Meses");
        cboFrecuenciaCalifO.addItem("3 Meses");
        cboFrecuenciaCalifO.addItem("4 Meses");
        cboFrecuenciaCalifO.addItem("5 Meses");
        cboFrecuenciaCalifO.addItem("6 Meses");
        cboFrecuenciaCalifO.addItem("7 Meses");
        cboFrecuenciaCalifO.addItem("8 Meses");
        cboFrecuenciaCalifO.addItem("9 Meses");
        cboFrecuenciaCalifO.addItem("10 Meses");
        cboFrecuenciaCalifO.addItem("11 Meses");
        cboFrecuenciaCalifO.addItem("12 Meses");
        cboFrecuenciaCalifO.addItem("13 Meses");
        cboFrecuenciaCalifO.addItem("14 Meses");
        cboFrecuenciaCalifO.addItem("15 Meses");
        cboFrecuenciaCalifO.addItem("16 Meses");
        cboFrecuenciaCalifO.addItem("17 Meses");
        cboFrecuenciaCalifO.addItem("18 Meses");
        cboFrecuenciaCalifO.addItem("19 Meses");
        cboFrecuenciaCalifO.addItem("20 Meses");
        cboFrecuenciaCalifO.addItem("21 Meses");
        cboFrecuenciaCalifO.addItem("22 Meses");
        cboFrecuenciaCalifO.addItem("23 Meses");
        cboFrecuenciaCalifO.addItem("24 Meses");
        cboFrecuenciaCalifO.setBounds(700, 280, 90, 20);
        cboFrecuenciaCalifO.setFont(TLetra);
        cboFrecuenciaCalifO.setForeground(Color.black);
        cboFrecuenciaCalifO.setVisible(true);

        lblFrecuenciaCalifD = new JLabel("* Frecuencia calificación desempeño");
        lblFrecuenciaCalifD.setBounds(430, 310, 210, 20);
        lblFrecuenciaCalifD.setFont(TLetra);
        lblFrecuenciaCalifD.setForeground(Color.black);
        lblFrecuenciaCalifD.setVisible(true);

        cboFrecuenciaCalifD = new JComboBox();
        cboFrecuenciaCalifD.addItem("No Aplica");
        cboFrecuenciaCalifD.addItem("1 Mes");
        cboFrecuenciaCalifD.addItem("2 Meses");
        cboFrecuenciaCalifD.addItem("3 Meses");
        cboFrecuenciaCalifD.addItem("4 Meses");
        cboFrecuenciaCalifD.addItem("5 Meses");
        cboFrecuenciaCalifD.addItem("6 Meses");
        cboFrecuenciaCalifD.addItem("7 Meses");
        cboFrecuenciaCalifD.addItem("8 Meses");
        cboFrecuenciaCalifD.addItem("9 Meses");
        cboFrecuenciaCalifD.addItem("10 Meses");
        cboFrecuenciaCalifD.addItem("11 Meses");
        cboFrecuenciaCalifD.addItem("12 Meses");
        cboFrecuenciaCalifD.addItem("13 Meses");
        cboFrecuenciaCalifD.addItem("14 Meses");
        cboFrecuenciaCalifD.addItem("15 Meses");
        cboFrecuenciaCalifD.addItem("16 Meses");
        cboFrecuenciaCalifD.addItem("17 Meses");
        cboFrecuenciaCalifD.addItem("18 Meses");
        cboFrecuenciaCalifD.addItem("19 Meses");
        cboFrecuenciaCalifD.addItem("20 Meses");
        cboFrecuenciaCalifD.addItem("21 Meses");
        cboFrecuenciaCalifD.addItem("22 Meses");
        cboFrecuenciaCalifD.addItem("23 Meses");
        cboFrecuenciaCalifD.addItem("24 Meses");

        cboFrecuenciaCalifD.setBounds(700, 310, 90, 20);
        cboFrecuenciaCalifD.setFont(TLetra);
        cboFrecuenciaCalifD.setForeground(Color.black);
        cboFrecuenciaCalifD.setVisible(true);

        lblTipo = new JLabel("* Tipo");
        lblTipo.setBounds(900, 70, 200, 20);
        lblTipo.setFont(TLetra);
        lblTipo.setForeground(Color.black);
        lblTipo.setVisible(true);

        cboTipo = new JComboBox();
        cboTipo.addItem("Equipo");
        cboTipo.addItem("Instrumento");
        cboTipo.setBounds(1070, 70, 90, 20);
        cboTipo.setFont(TLetra);
        cboTipo.setForeground(Color.black);
        cboTipo.setVisible(true);

        lblCliente = new JLabel("* Cliente");
        lblCliente.setBounds(900, 100, 200, 20);
        lblCliente.setFont(TLetra);
        lblCliente.setForeground(Color.black);
        lblCliente.setVisible(true);

        txtCliente = new JTextField("");
        txtCliente.setBounds(1070, 100, 150, 20);
        txtCliente.setFont(TLetra);
        txtCliente.setForeground(Color.black);
        txtCliente.setVisible(true);

        TablaDatos = new JTable(dataModel) {
            @Override
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        TablaDatos.setSize(3000, 200);
        TablaDatos.getTableHeader().setReorderingAllowed(false);
        JTextField jtf = new JTextField();
        jtf.setEditable(false);
        TablaDatos.getColumnModel().getColumn(0).setMinWidth(130);
        TablaDatos.getColumnModel().getColumn(1).setMinWidth(140);
        TablaDatos.getColumnModel().getColumn(2).setMinWidth(130);
        TablaDatos.getColumnModel().getColumn(3).setMinWidth(130);
        TablaDatos.getColumnModel().getColumn(4).setMinWidth(130);
        TablaDatos.getColumnModel().getColumn(5).setMinWidth(160);
        TablaDatos.getColumnModel().getColumn(6).setMinWidth(180);
        TablaDatos.getColumnModel().getColumn(7).setMinWidth(150);
        TablaDatos.getColumnModel().getColumn(8).setMinWidth(100);
        TablaDatos.getColumnModel().getColumn(9).setMinWidth(150);
        TablaDatos.getColumnModel().getColumn(10).setMinWidth(150);
        TablaDatos.getColumnModel().getColumn(11).setMinWidth(150);
        TablaDatos.getColumnModel().getColumn(12).setMinWidth(100);
        TablaDatos.getColumnModel().getColumn(13).setMinWidth(100);
        TablaDatos.getColumnModel().getColumn(14).setMinWidth(180);
        TablaDatos.getColumnModel().getColumn(15).setMinWidth(180);
        TablaDatos.getColumnModel().getColumn(16).setMinWidth(180);
        ocultarColumna("Objeto");

        PanelDatos = new JScrollPane(TablaDatos);
        PanelDatos.setBounds(60, 420, 1200, 200);
        TablaDatos.addMouseListener(this);
        TablaDatos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        PanelDatos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(dataModel);
        TablaDatos.setRowSorter(elQueOrdena);

        //Botones
        btnregistroPasado.setBounds(15, 370, 400, 35);
        btnNuevo.setBounds(500, 640, 100, 25);
        btnGuardar.setBounds(550, 690, 200, 25);
        btnActualizar.setBounds(600, 640, 100, 25);
        btnSalir.setBounds(700, 640, 100, 25);
        btnCambio.setBounds(890, 640, 230, 55);
        btnCargar.setBounds(150, 300, 100, 25);

        btnregistroPasado.setVisible(false);
        btnNuevo.setVisible(true);
        btnActualizar.setVisible(true);
        btnGuardar.setVisible(true);
        btnSalir.setVisible(true);
        btnCambio.setVisible(false);
        btnCargar.setVisible(true);

        PanelGeneral1.setVisible(true);
        this.getContentPane().add(PanelGeneral1);

        PanelGeneral1.add(lblcodInv);
        PanelGeneral1.add(txtcodInv);
        PanelGeneral1.add(lblidMetrologia);
        PanelGeneral1.add(txtidMetrologia);
        PanelGeneral1.add(lblNumSerie);
        PanelGeneral1.add(txtNumSerie);
        PanelGeneral1.add(lblNombre);
        PanelGeneral1.add(txtNombre);
        PanelGeneral1.add(lblMarca);
        PanelGeneral1.add(txtMarca);
        PanelGeneral1.add(lblClasificacion);
        PanelGeneral1.add(cboClasificacion);
        PanelGeneral1.add(lblModelo);
        PanelGeneral1.add(txtModelo);
        PanelGeneral1.add(lblEstado);
        PanelGeneral1.add(cboEstado);
        PanelGeneral1.add(lblDescripcion);
        PanelGeneral1.add(txaDescripcion);
        PanelGeneral1.add(lblFrecuenciaMant);
        PanelGeneral1.add(cboFrecuenciaMant);
        PanelGeneral1.add(lblFrecuenciaCal);
        PanelGeneral1.add(cboFrecuenciaCal);
        PanelGeneral1.add(lblFrecuenciaCalifO);
        PanelGeneral1.add(cboFrecuenciaCalifO);
        PanelGeneral1.add(lblFrecuenciaCalifD);
        PanelGeneral1.add(cboFrecuenciaCalifD);
        PanelGeneral1.add(lblTipo);
        PanelGeneral1.add(cboTipo);
        PanelGeneral1.add(lblCliente);
        PanelGeneral1.add(txtCliente);
        PanelGeneral1.add(btnNuevo);
        PanelGeneral1.add(lblAdvertencia);

        PanelGeneral1.add(btnregistroPasado);
        PanelGeneral1.add(btnGuardar);
        PanelGeneral1.add(btnActualizar);
        PanelGeneral1.add(btnNuevo);
        PanelGeneral1.add(btnSalir);
        PanelGeneral1.add(btnCambio);
        PanelGeneral1.add(btnCargar);
        PanelGeneral1.add(btnBuscarInv);
        PanelGeneral1.add(btnBuscaridMetr);
        PanelGeneral1.add(btnBuscarSerie);
        PanelGeneral1.add(panelPaginador);
        PanelGeneral1.add(PanelDatos);
        PanelGeneral1.add(PanelFoto);


        setJMenuBar(Menu);

        btnSalir.addActionListener(this);
        btnNuevo.addActionListener(this);
        btnCargar.addActionListener(this);
        btnActualizar.addActionListener(this);
        btnGuardar.addActionListener(this);
        btnBuscarInv.addActionListener(this);
        btnBuscaridMetr.addActionListener(this);
        btnBuscarSerie.addActionListener(this);
        btnregistroPasado.addActionListener(this);
        btnCambio.addActionListener(this);
        panelPaginador.getBtnBack().addActionListener(this);
        panelPaginador.getBtnBackward().addActionListener(this);
        panelPaginador.getBtnNext().addActionListener(this);
        panelPaginador.getBtnForward().addActionListener(this);
        m11.addActionListener(this);
        m21.addActionListener(this);

        ventana.bloquear(this);
        SwingWorker worker;
        pageData.setFirstQuery(true);
        worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                vaciar();
                pageData.setCurrentPage(1);
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
                    System.gc();
                    break;
                case JOptionPane.NO_OPTION:
                    break;

            }
        }

        if (e.getSource() == btnNuevo) {
            txtcodInv.setText("");
            txtidMetrologia.setText("");
            txtMarca.setText("");
            txtNumSerie.setText("");
            txtNombre.setText("");
            cboClasificacion.setSelectedIndex(0);
            txtModelo.setText("");
            cboEstado.setSelectedIndex(0);
            cboFrecuenciaMant.setSelectedIndex(0);
            cboFrecuenciaCal.setSelectedIndex(0);
            cboFrecuenciaCalifO.setSelectedIndex(0);
            cboFrecuenciaCalifD.setSelectedIndex(0);
            txtCliente.setText("");
            txaDescripcion.setText("");
            lblFoto.setIcon(null);
            this.Ruta = "";
            this.extencion = "";
            txtcodInv.setEditable(true);
            txtNumSerie.setEditable(true);
            txtNombre.setEditable(true);
            txtModelo.setEditable(true);
            txtCliente.setEditable(true);
            txtMarca.setEditable(true);
            btnCambio.setVisible(false);
            repaint();
            updateNotifications();
            ventana.bloquear(this);
            panelPaginador.enablePageButtons();
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    vaciar();
                    llenarDatos(pageData);
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();

        }

        if (e.getSource() == btnGuardar) {
            ventana.bloquear(this);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    String Codinv, CodMetr, Marca, Modelo, Serie, Nombre, Ubicacion, Frecuencia, FrecuenciaCal, FrecuenciaCalifO, FrecuenciaCalifD, Cliente, Estado, Observaciones, Fecha, Hora, Tipo;
                    Calendar c = Calendar.getInstance();
                    File Foto = null;

                    Codinv = txtcodInv.getText();
                    CodMetr = txtidMetrologia.getText();
                    Marca = txtMarca.getText();
                    Modelo = txtModelo.getText();
                    Serie = txtNumSerie.getText();
                    Nombre = txtNombre.getText();
                    Ubicacion = cboClasificacion.getSelectedItem().toString();
                    Frecuencia = cboFrecuenciaMant.getSelectedItem().toString();
                    FrecuenciaCal = cboFrecuenciaCal.getSelectedItem().toString();
                    FrecuenciaCalifO = cboFrecuenciaCalifO.getSelectedItem().toString();
                    FrecuenciaCalifD = cboFrecuenciaCalifD.getSelectedItem().toString();
                    Estado = cboEstado.getSelectedItem().toString();
                    Observaciones = txaDescripcion.getText();
                    Cliente = txtCliente.getText();
                    Tipo = cboTipo.getSelectedItem().toString();
                    Date now = new Date(System.currentTimeMillis());
                    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
                    Hora = hour.format(now);
                    Fecha = date.format(now);

                    if (!"".equals(Ruta)) {
                        Foto = new File(Ruta);
                    }

                    boolean valido = true;
                    if (Codinv.equals("")) {
                        valido = false;
                    }
                    if (CodMetr.equals("")) {
                        valido = false;
                    }
                    if (CodMetr.equals("")) {
                        valido = false;
                    }

                    if (Marca.equals("")) {
                        valido = false;
                    }

                    if (Marca.equals("")) {
                        valido = false;
                    }

                    if (Modelo.equals("")) {
                        valido = false;
                    }
                    if (Serie.equals("")) {
                        valido = false;
                    }
                    if (Ubicacion.equals("")) {
                        valido = false;
                    }
                    if (Nombre.equals("")) {
                        valido = false;
                    }
                    if (Ubicacion.equals("")) {
                        valido = false;
                    }
                    if (Frecuencia.equals("")) {
                        valido = false;
                    }
                    if (FrecuenciaCal.equals("")) {
                        valido = false;
                    }
                    if (FrecuenciaCalifO.equals("")) {
                        valido = false;
                    }
                    if (FrecuenciaCalifD.equals("")) {
                        valido = false;
                    }
                    if (Cliente.equals("")) {
                        valido = false;
                    }
                    if (Estado.equals("")) {
                        valido = false;
                    }
                    if (Observaciones.equals("")) {
                        valido = false;
                    }

                    if (Ruta.equals("")) {
                        valido = false;
                        JOptionPane.showMessageDialog(null, "Es obligatorio cargar una foto para el equipo/instrumento", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                    }

                    if (valido == true) {
                        DtoEquipo equipoFind = new DtoEquipo();
                        equipoFind.setCodigoInv(Codinv);
                        equipoFind.setCodigoMet(CodMetr);
                        equipoFind.setSerie(Serie);
                        boolean exist = cmd.findEquipmentExist(equipoFind);
                        equipoFind.setCliente(Cliente);
                        equipoFind.setEstado(Estados.getByDescription(Estado).getCode());
                        equipoFind.setExtencion(extencion);
                        equipoFind.setFoto(Base64.encodeBase64URLSafe(FileUtils.readFileToByteArray(Foto)));
                        equipoFind.setFrecCalibracion(Frecuencias.getByDescription(cboFrecuenciaCal.getSelectedItem().toString()).getCode());
                        equipoFind.setFrecMantenimiento(Frecuencias.getByDescription(cboFrecuenciaMant.getSelectedItem().toString()).getCode());
                        equipoFind.setFrecCalifOPerativa(Frecuencias.getByDescription(cboFrecuenciaCalifO.getSelectedItem().toString()).getCode());
                        equipoFind.setFrecCalifDesempenio(Frecuencias.getByDescription(cboFrecuenciaCalifD.getSelectedItem().toString()).getCode());
                        equipoFind.setMarca(Marca);
                        equipoFind.setModelo(Modelo);
                        equipoFind.setNombre(Nombre);
                        equipoFind.setObservaciones(Observaciones);
                        equipoFind.setTipo(TipoEquipo.getByNombre(cboTipo.getSelectedItem().toString()).getCode());
                        equipoFind.setUbicacion(Ubicacion);
                        if (!exist) {

                            int respuesta = JOptionPane.showConfirmDialog(null, "Esta seguro que desea registrar este equipo como tercero", "METROFARM", JOptionPane.YES_NO_OPTION);
                            switch (respuesta) {
                                case JOptionPane.YES_OPTION:
                                    boolean result = cmd.createEquipment(equipoFind);

                                    if (result == true) {
                                        panelPaginador.enablePageButtons();
                                        pageData.setNumRows(0);
                                        vaciar();
                                        llenarDatos(pageData);
                                        JOptionPane.showMessageDialog(null, " Nuevo equipo de terceros ingresado", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                    break;
                                case JOptionPane.NO_OPTION:
                                    break;

                            }
                        } else {
                            JOptionPane.showMessageDialog(null, " El código de inventario,código de metrologia o número de serie ya existe.", "METROFARM", JOptionPane.WARNING_MESSAGE);
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
            ventana.bloquear(this);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {

                    String Codinv, CodMetr, Marca, Modelo, Serie, Nombre, Ubicacion, Cliente, Estado, Observaciones;
                    Calendar c = Calendar.getInstance();
                    File Foto = null;

                    Codinv = txtcodInv.getText();
                    CodMetr = txtidMetrologia.getText();
                    Marca = txtMarca.getText();
                    Modelo = txtModelo.getText();
                    Serie = txtNumSerie.getText();
                    Nombre = txtNombre.getText();
                    Ubicacion = cboClasificacion.getSelectedItem().toString();
                    Estado = cboEstado.getSelectedItem().toString();
                    Observaciones = txaDescripcion.getText();
                    Cliente = txtCliente.getText();


                    if (!"".equals(Ruta)) {
                        Foto = new File(Ruta);
                    }

                    boolean valido = true;
                    if (CodMetr.equals("")) {
                        valido = false;
                    }
                    if (Codinv.equals("")) {
                        valido = false;
                    }
                    if (Serie.equals("")) {
                        valido = false;
                    }

                    if (Nombre.equals("")) {
                        valido = false;
                    }

                    if (Marca.equals("")) {
                        valido = false;
                    }
                    if (Modelo.equals("")) {
                        valido = false;
                    }
                    if (Ubicacion.equals("")) {
                        valido = false;
                    }
                    if (Estado.equals("")) {
                        valido = false;
                    }
                    if (Cliente.equals("")) {
                        valido = false;
                    }
                    if (Ruta.equals("") && ((DtoEquipo) TablaDatos.getValueAt(TablaDatos.getSelectedRow(), 16)).getFoto() == null) {
                        JOptionPane.showMessageDialog(null, "Debes adjuntar una foto al equipo/instrumento");
                        ventana.desbloquear();
                        return null;
                    }

                    if (valido == true) {
                        DtoEquipo equipoUpdate = new DtoEquipo();
                        equipoUpdate.setCodigoInv(Codinv);
                        equipoUpdate.setCodigoMet(CodMetr);
                        equipoUpdate.setSerie(Serie);
                        equipoUpdate.setCliente(Cliente);
                        equipoUpdate.setEstado(Estados.getByDescription(Estado).getCode());
                        equipoUpdate.setExtencion(extencion);
                        equipoUpdate.setFoto(Foto != null ? Base64.encodeBase64URLSafe(FileUtils.readFileToByteArray(Foto)) : ((DtoEquipo) TablaDatos.getValueAt(TablaDatos.getSelectedRow(), 16)).getFoto());
                        equipoUpdate.setFrecCalibracion(Frecuencias.getByDescription(cboFrecuenciaCal.getSelectedItem().toString()).getCode());
                        equipoUpdate.setFrecMantenimiento(Frecuencias.getByDescription(cboFrecuenciaMant.getSelectedItem().toString()).getCode());
                        equipoUpdate.setFrecCalifOPerativa(Frecuencias.getByDescription(cboFrecuenciaCalifO.getSelectedItem().toString()).getCode());
                        equipoUpdate.setFrecCalifDesempenio(Frecuencias.getByDescription(cboFrecuenciaCalifD.getSelectedItem().toString()).getCode());
                        equipoUpdate.setMarca(Marca);
                        equipoUpdate.setModelo(Modelo);
                        equipoUpdate.setNombre(Nombre);
                        equipoUpdate.setObservaciones(Observaciones);
                        equipoUpdate.setTipo(TipoEquipo.getByNombre(cboTipo.getSelectedItem().toString()).getCode());
                        equipoUpdate.setUbicacion(Ubicacion);

                        boolean result = cmd.updateEquipment(equipoUpdate);

                        if (result == true) {
                            panelPaginador.enablePageButtons();
                            vaciar();
                            llenarDatos(pageData);
                            updateNotifications();
                            JOptionPane.showMessageDialog(null, " Se ha actualizado la información del equipo exitosamente", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
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

        if (e.getSource() == btnCargar) {
            ventana.bloquear(this);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    cargarFoto();
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();

        }

        if (e.getSource() == btnBuscarInv) {
            ventana.bloquear(this);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    DtoEquipo equipo = new DtoEquipo();
                    vaciar();
                    equipo.setCodigoInv(txtcodInv.getText());

                    if (equipo.getCodigoInv().trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, " Debe digitar el código de inventario que desea buscar.", "METROFARM", JOptionPane.WARNING_MESSAGE);
                        vaciar();
                        llenarDatos(pageData);
                    } else {
                        ObjectResponse response = cmd.findEquipments(equipo, pageData);
                        ArrayList<DtoEquipo> equipoFind = (ArrayList<DtoEquipo>) response.getRecords();
                        pageData = response.getpageResponse();
                        if (equipoFind != null && !equipoFind.isEmpty()) {
                            setDataModel(getEquipos(equipoFind, getDataModel()));
                        } else {
                            JOptionPane.showMessageDialog(null, " No se encontraron datos con la informacion ingresada.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                            vaciar();
                            llenarDatos(pageData);
                        }

                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();

        }

        if (e.getSource() == btnBuscaridMetr) {
            ventana.bloquear(this);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    DtoEquipo equipo = new DtoEquipo();
                    vaciar();
                    equipo.setCodigoMet(txtidMetrologia.getText());

                    if (equipo.getCodigoMet().trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, " Debe digitar el código de metrología del equipo que desea buscar.", "METROFARM", JOptionPane.WARNING_MESSAGE);
                        vaciar();
                        llenarDatos(pageData);
                    } else {
                        ObjectResponse response = cmd.findEquipments(equipo, pageData);
                        ArrayList<DtoEquipo> equipoFind = (ArrayList<DtoEquipo>) response.getRecords();
                        pageData = response.getpageResponse();
                        if (equipoFind != null && !equipoFind.isEmpty()) {
                            setDataModel(getEquipos(equipoFind, getDataModel()));
                        } else {
                            JOptionPane.showMessageDialog(null, " No se encontraron datos con la informacion ingresada.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                            vaciar();
                            llenarDatos(pageData);
                        }

                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();

        }

        if (e.getSource() == btnBuscarSerie) {
            ventana.bloquear(this);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    DtoEquipo equipo = new DtoEquipo();
                    vaciar();
                    equipo.setSerie(txtNumSerie.getText());

                    if (equipo.getSerie().trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, " Debe digitar el número de serie del equipo que desea buscar.", "METROFARM", JOptionPane.WARNING_MESSAGE);
                        vaciar();
                        llenarDatos(pageData);
                    } else {

                        ObjectResponse response = cmd.findEquipments(equipo, pageData);
                        ArrayList<DtoEquipo> equipoFind = (ArrayList<DtoEquipo>) response.getRecords();
                        pageData = response.getpageResponse();
                        if (equipoFind != null && !equipoFind.isEmpty()) {
                            setDataModel(getEquipos(equipoFind, getDataModel()));
                        } else {
                            JOptionPane.showMessageDialog(null, " No se encontraron datos con la informacion ingresada.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                            vaciar();
                            llenarDatos(pageData);
                        }

                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();

        }

        if (e.getSource() == btnCambio) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea ingresar un nuevo registro de cambios asociado a este equipo?", "METROFARM", JOptionPane.YES_NO_OPTION);
            switch (respuesta) {
                case JOptionPane.YES_OPTION:

                    RegistroDeCambio reg = new RegistroDeCambio((DtoEquipo) TablaDatos.getValueAt(TablaDatos.getSelectedRow(), 16));

                    break;
                case JOptionPane.NO_OPTION:
                    break;

            }
        }

        if (e.getSource() == btnregistroPasado) {
            if (TablaDatos.getSelectedRow() > -1) {
                ventana.desbloquear();
                IngresoAnteriores ingreso = new IngresoAnteriores((DtoEquipo) TablaDatos.getValueAt(TablaDatos.getSelectedRow(), 16));
                ingreso.setVisible(true);
                ingreso.toFront();
            } else {
                JOptionPane.showMessageDialog(null, "Por favor seleccione un registro de la tabla");
            }

        }

        if (e.getSource() == m11) {
            ventana.bloquear(this);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    ventana.desbloquear();
                    HistorialRegistroCambio histrorial_reg_cambios = new HistorialRegistroCambio();
                    histrorial_reg_cambios.toFront();
                    return null;
                }
            };
            worker.execute();

        }

        if (e.getSource() == m21) {
            ventana.bloquear(this);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    try {
                        CmdUtils cmdSynchronice = new CmdUtils();
                        cmdSynchronice.synchronization(new DtoEquipo());
                        JOptionPane.showMessageDialog(null, "Se sincronizaron los equipos exitosamente");
                        vaciar();
                        llenarDatos(pageData);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
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
                    panelPaginador.setPagText(pageData.getCurrentPage() + " de "+pageData.getMaxPages());
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
                    panelPaginador.setPagText(pageData.getCurrentPage() + " de "+pageData.getMaxPages());
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
                    panelPaginador.setPagText(pageData.getCurrentPage() + " de "+pageData.getMaxPages());
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
                    panelPaginador.setPagText(pageData.getCurrentPage() + " de "+pageData.getMaxPages());
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

    public void llenarDatos(PageData page) {
        try {
            ArrayList<DtoEquipo> equipos = new ArrayList<DtoEquipo>();
            DtoEquipo equipoRequest = new DtoEquipo();
            ObjectResponse response = cmd.findEquipments(equipoRequest, page);
            equipos = (ArrayList<DtoEquipo>) response.getRecords();
            pageData = response.getpageResponse();
            setDataModel(getEquipos(equipos, dataModel));
            TablaDatos.setDefaultRenderer(Object.class, new aparienciaTabla());
            repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "MetroFarm", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void vaciar() {
        int i = getDataModel().getRowCount();
        while (i > 0) {
            getDataModel().removeRow(0);
            i--;
        }
    }

    public DefaultTableModel getEquipos(ArrayList<DtoEquipo> result, DefaultTableModel model) {

        for (DtoEquipo c : result) {
            String act = "Si";
            if (c.getEstado() == Estados.DESACTIVADO.getCode()) {
                act = "No";
            }
            try {
                Object nuevo[] = {c.getCodigoInv(), c.getCodigoMet(), c.getMarca(), c.getModelo(), c.getSerie(), c.getNombre(), TipoEquipo.getByCode(c.getTipo()).getNombre(), c.getUbicacion(), Estados.getByCode(c.getEstado()).getDescription(), c.getCliente(), c.getObservaciones(), GlobalUtils.getDateFormatted(c.getFecha()), Frecuencias.getByCode(c.getFrecMantenimiento()).getDescription(), Frecuencias.getByCode(c.getFrecCalibracion()).getDescription(), Frecuencias.getByCode(c.getFrecCalifOPerativa()).getDescription(), Frecuencias.getByCode(c.getFrecCalifDesempenio()).getDescription(), c};
                model.addRow(nuevo);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        return model;

    }

    public void setDataModel(DefaultTableModel dataModel) {
        this.dataModel = dataModel;
    }

    public DefaultTableModel getDataModel() {
        return dataModel;
    }

    //Subprogramas
    public void cargarFoto() {
        ImageIcon adaptada;

        this.Ruta = "";
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        fc.setFileFilter(filtroImagen);
        fc.setMultiSelectionEnabled(false);
        fc.setAcceptAllFileFilterUsed(false);
        int returnVal = fc.showDialog(Equipos.this,
                "Cargar");

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String direccion = file.getPath();

            int i = file.getName().lastIndexOf('.');
            if (i > 0) {
                extencion = file.getName().substring(i + 1);
            }
            System.out.print("la extencion es" + extencion);
            this.Ruta = direccion;

            System.out.println("Usted Cargo: " + file.getName() + "con una ruta de " + file.getPath());
            ImageIcon icon = new ImageIcon(direccion);
            icon = AjustarUmagen(icon);

            lblFoto.setIcon(icon);

        } else {
            System.out.println("Carga cancelada por el usuario");
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == TablaDatos) {

            if (TablaDatos.getRowCount() > 0) {
                cargarDatosTablaClicked();
            }

        }
    }

    /**
     * Método que escucha accion del click para cargar los datos a la tabla con
     * su imagen
     */
    public void cargarDatosTablaClicked() {
        ventana.bloquear(this);
        SwingWorker worker;
        worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                int fila = 0;
                try {
                    fila = TablaDatos.getSelectedRow();
                    TablaDatos.setSelectionBackground(new Color(Integer.parseInt("015289", 16)));
                    if (fila > -1) {
                        DtoEquipo equipoSeleccionado = ((DtoEquipo) TablaDatos.getValueAt(fila, 16));
                        try {

                            if (equipoSeleccionado.getCodigoMet() != null && !equipoSeleccionado.getCodigoMet().equals("") && equipoSeleccionado.getUbicacion() != null && !equipoSeleccionado.getUbicacion().equals("")) {
                                ImageIcon imagen = new ImageIcon(Base64.decodeBase64(equipoSeleccionado.getFoto()));
                                imagen = AjustarUmagen(imagen);
                                lblFoto.setIcon(imagen);
                            } else {
                                lblFoto.setIcon(null);
                                JOptionPane.showMessageDialog(null, "Este equipo fue ingresado desde ofimática y debe ser ingresado al sistema metrofarm. \n Por favor registre los datos adicionales requeridos");
                            }
                        } catch (Exception exe) {
                            lblFoto.setIcon(null);
                            exe.printStackTrace();
                        }

                        txtcodInv.setText(equipoSeleccionado.getCodigoInv());
                        txtidMetrologia.setText(equipoSeleccionado.getCodigoMet() != null ? equipoSeleccionado.getCodigoMet() : "");
                        txtMarca.setText(equipoSeleccionado.getMarca());
                        txtModelo.setText(equipoSeleccionado.getModelo());
                        txtNombre.setText(equipoSeleccionado.getNombre());
                        txtNumSerie.setText(equipoSeleccionado.getSerie());
                        cboEstado.setSelectedItem(Estados.getByCode(equipoSeleccionado.getEstado()).getDescription());
                        cboClasificacion.setSelectedItem(equipoSeleccionado.getUbicacion() != null ? equipoSeleccionado.getUbicacion() : "");
                        cboFrecuenciaMant.setSelectedItem(Frecuencias.getByCode(equipoSeleccionado.getFrecMantenimiento()).getDescription());
                        cboTipo.setSelectedItem(TipoEquipo.getByCode(equipoSeleccionado.getTipo()).getNombre());

                        txtCliente.setText(equipoSeleccionado.getCliente());
                        txaDescripcion.setText(equipoSeleccionado.getObservaciones());

                        cboFrecuenciaCalifO.setSelectedItem(Frecuencias.getByCode(equipoSeleccionado.getFrecCalifOPerativa()).getDescription());
                        cboFrecuenciaCal.setSelectedItem(Frecuencias.getByCode(equipoSeleccionado.getFrecCalibracion()).getDescription());
                        cboFrecuenciaCalifD.setSelectedItem(Frecuencias.getByCode(equipoSeleccionado.getFrecCalifDesempenio()).getDescription());
                        txtcodInv.setEditable(false);
                        txtNombre.setEditable(false);
                        txtNumSerie.setEditable(false);
                        txtModelo.setEditable(false);
                        txtCliente.setEditable(false);
                        txtMarca.setEditable(false);

                        if (equipoSeleccionado.getCodigoMet() != null) {
                            btnCambio.setVisible(true);
                            btnregistroPasado.setVisible(true);
                        } else {
                            btnCambio.setVisible(false);
                            btnregistroPasado.setVisible(false);
                        }
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

    public int contarEquiposnuevos() {
        int cont = 0;

        for (int i = 0; i <= TablaDatos.getRowCount(); i++) {
            if (TablaDatos.getValueAt(i, 7).equals("")) {
                cont = cont + 1;
            }
        }
        return cont;
    }

    public void ocultarColumna(String column) {
        TableColumn tc = TablaDatos.getColumn(column); //Obtienes la columna
        tc.setPreferredWidth(0); //Y le das un tamaño
        tc.setMinWidth(0);
        tc.setMaxWidth(0);
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
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void updateNotifications() {
        Thread updateNotification = new Thread(new ThreadNotification(menu));
        updateNotification.start();
    }
}
