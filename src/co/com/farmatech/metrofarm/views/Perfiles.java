/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.views;

import co.com.farmatech.metrofarm.cmd.CmdProfile;
import co.com.farmatech.metrofarm.tree.Editor;
import co.com.farmatech.metrofarm.tree.Plantilla;
import co.com.farmatech.metrofarm.tree.Render;
import co.com.farmatech.metrofarm.utils.Formu_Fondo;
import co.com.farmatech.metrofarm.utils.PanelPaginador;
import co.com.farmatech.metrofarm.utils.UtilFarmatech;
import co.janker.dto.objresponse.ObjectResponse;
import co.jankins.psf.common.enums.Estados;
import com.co.farmatech.metrofarm.dto.DtoPerfil;
import com.co.farmatech.metrofarm.dto.PageData;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import ventana.bloqueo.VentanaBloqueo;

/**
 *
 * @author bestrada
 */
public class Perfiles extends JInternalFrame implements ActionListener, MouseListener {

    private Object[][] DetallesIdentificacion = new Object[0][0];
    private Object[] ColumnasIdentificacion = {"Perfil", "Estado", "Objeto"};
    private DefaultTableModel dataModel = new DefaultTableModel(DetallesIdentificacion, ColumnasIdentificacion);
    Formu_Fondo PanelGeneral1;
    ImageIcon Icofondo;
    JPanel Panelinforma, Panelarbol;
    JLabel lblPerfil, lblEstado;
    JTextField txtPerf;
    JComboBox cboEstado;
    JTree JTree1;
    DefaultTreeModel modelo = null;
    Editor edit;
    JScrollPane scrollPane = new JScrollPane();
    public JTable Tablaperfiles;
    String ruta = "co/com/farmatech/metrofarm/resources/imagenes/";
    JScrollPane PanelDatos;
    public JButton btnGuardar = new JButton("Guardar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "guardar.gif")));
    public JButton btnNuevo = new JButton("Nuevo", new ImageIcon(getClass().getClassLoader().getResource(ruta + "nuevo.gif")));
    public JButton btnActualizar = new JButton("Actualizar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "actualizar.gif")));
    public JButton btnBuscar = new JButton("Buscar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "buscar.gif")));
    //nodo padre
    DefaultMutableTreeNode root = new DefaultMutableTreeNode(new Plantilla("1", "Permisos", true, ""));
    //nodos hijos, padres de los permisos
    DefaultMutableTreeNode hijo = new DefaultMutableTreeNode(new Plantilla("2", "Usuarios", true, ""));
    DefaultMutableTreeNode hijo2 = new DefaultMutableTreeNode(new Plantilla("3", "Perfiles", true, ""));
    DefaultMutableTreeNode hijo4 = new DefaultMutableTreeNode(new Plantilla("4", "Equipos", true, ""));
    DefaultMutableTreeNode hijo5 = new DefaultMutableTreeNode(new Plantilla("5", "Mantenimiento", true, ""));
    DefaultMutableTreeNode hijo6 = new DefaultMutableTreeNode(new Plantilla("6", "Calificaciòn", true, ""));
    DefaultMutableTreeNode hijo7 = new DefaultMutableTreeNode(new Plantilla("7", "Calibraciòn", true, ""));
    DefaultMutableTreeNode hijo8 = new DefaultMutableTreeNode(new Plantilla("8", "Mantenimiento General", true, ""));
    //Modelo del checkbox y Nodo Checkbox
    Plantilla PGuardarUsuario;
    DefaultMutableTreeNode GuardarUsuario;
    Plantilla PbuscarUsuario;
    DefaultMutableTreeNode BuscarUsu;
    Plantilla PModificarUsu;
    DefaultMutableTreeNode ModificarUsu;
    //////////////////////////////////////////////
    Plantilla PGuardarPerfil;
    DefaultMutableTreeNode GuardarPer;
    Plantilla PBuscarPerfil;
    DefaultMutableTreeNode BuscarPer;
    Plantilla PModficarPefil;
    DefaultMutableTreeNode ModificarPer;
    ///////////////////////////////////////////////
    Plantilla PGuardarEquipo;
    DefaultMutableTreeNode GuardarEquipo;
    Plantilla PBuscarEquipo;
    DefaultMutableTreeNode BuscarEquipo;
    Plantilla PModificarEquipo;
    DefaultMutableTreeNode ModificarEquipo;
    Plantilla PNotificacionesEqNuev;
    DefaultMutableTreeNode NotificacionesEqNuev;
    //////////////////////////////////////////////////////////////
    Plantilla PGuardarMantenimiento;
    DefaultMutableTreeNode GuardarManteninimiento;
    Plantilla PBuscarMantenimiento;
    DefaultMutableTreeNode BuscarMantenimiento;
    Plantilla PModificarMantenimiento;
    DefaultMutableTreeNode ModificarMantenimiento;
    Plantilla PEliminarMantenimiento;
    DefaultMutableTreeNode EliminarMantenimiento;
    Plantilla PNotificaciones_man_mes;
    DefaultMutableTreeNode Notificaciones_man_mes;
    Plantilla PNotificaciones_man_ven_mes;
    DefaultMutableTreeNode Notificaciones_man_ven_mes;
    ///////////////////////////////////////////////////////////////
    Plantilla PGuardarCalificacion;
    DefaultMutableTreeNode GuardarCali;
    Plantilla PBuscarCalificacion;
    DefaultMutableTreeNode BuscarCalificacion;
    Plantilla PModificarCalificacion;
    DefaultMutableTreeNode ModificarCalficacion;
    Plantilla PEliminarCalificacion;
    DefaultMutableTreeNode EliminarCalificacion;
    Plantilla PNotificaciones_calif_mes;
    DefaultMutableTreeNode Notificaciones_calif_mes;
    Plantilla PNotificaciones_calif_ven_mes;
    DefaultMutableTreeNode Notificaciones_calif_ven_mes;
    /////////////////////////////////////////////////////////////////
    Plantilla PGuardarCalib;
    DefaultMutableTreeNode GuardarCalib;
    Plantilla PBuscarCalib;
    DefaultMutableTreeNode BuscarCalib;
    Plantilla PModificarCalib;
    DefaultMutableTreeNode ModificarCalib;
    Plantilla PEliminarCalib;
    DefaultMutableTreeNode EliminarCalib;
    Plantilla PNotificaciones_calib_mes;
    DefaultMutableTreeNode Notificaciones_calib_mes;
    Plantilla PNotificaciones_calib_ven_mes;
    DefaultMutableTreeNode Notificaciones_calib_ven_mes;
    /////////////////////////////////////////////////////////////////
    Plantilla PGuardarMantGen;
    DefaultMutableTreeNode GuardarMantGen;
    Plantilla PBuscarMantGen;
    DefaultMutableTreeNode BuscarMantGen;
    Plantilla PModificarMantGen;
    DefaultMutableTreeNode ModificarMantGen;
    Plantilla PEliminarMantGen;
    DefaultMutableTreeNode EliminarMantGen;
    Plantilla PNotificaciones_mantgen_mes;
    DefaultMutableTreeNode Notificaciones_mantgen_mes;
    Plantilla PNotificaciones_mantgen_ven;
    DefaultMutableTreeNode Notificaciones_mantgen_ven;
    private final VentanaBloqueo ventana = new VentanaBloqueo();
    CmdProfile cmdProfile;
    PanelPaginador panelPaginador;
    PageData pageData;

    public Perfiles() {

        super("Perfiles METROFARM -v1");

        ((JPanel) getContentPane()).setOpaque(false);  // para que muestre la imagen
        this.setSize(1366, 768);

        setResizable(false);
        getContentPane().setLayout(null);
        setBackground(Color.white);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(0);

        setFrameIcon(new ImageIcon(getClass().getClassLoader().getResource(ruta + "FARMA.png")));

        cmdProfile = new CmdProfile();
        panelPaginador = new PanelPaginador();
        panelPaginador.setBounds(870, 660, 300, 29);
        
        pageData = panelPaginador.getPageData();
        pageData.setRowsPerPage(20);

        Font TLetra = new Font("Arial", Font.PLAIN, 12);
        Icofondo = new ImageIcon(getClass().getClassLoader().getResource(ruta + "fondomenu.png"));

        JTree1 = new JTree();
        scrollPane = new JScrollPane(JTree1);
        scrollPane = new JScrollPane(this.JTree1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(JTree1);
        scrollPane.setBounds(10, 15, 370, 675);

        //Inicializamos las Plantilla
        PGuardarUsuario = new Plantilla("3", "Guardar", false, "");
        PbuscarUsuario = new Plantilla("4", "Buscar", false, "");
        PModificarUsu = new Plantilla("5", "Modificar", false, "");

        //////////////////////////////////////////////////////////////
        PGuardarPerfil = new Plantilla("3", "Guardar", false, "");
        PBuscarPerfil = new Plantilla("4", "Buscar", false, "");
        PModficarPefil = new Plantilla("5", "Modificar", false, "");

        ///////////////////////////////////////////////////////////////
        PGuardarEquipo = new Plantilla("3", "Guardar", false, "");
        PBuscarEquipo = new Plantilla("4", "Buscar", false, "");
        PModificarEquipo = new Plantilla("5", "Modificar", false, "");
        PNotificacionesEqNuev = new Plantilla("7", "Notificar equipos nuevos", false, "");
        //////////////////////////////////////////////////////////////////////////////////////////
        PGuardarMantenimiento = new Plantilla("3", "Guardar", false, "");
        PBuscarMantenimiento = new Plantilla("4", "Buscar", false, "");
        PModificarMantenimiento = new Plantilla("5", "Modificar", false, "");
        PEliminarMantenimiento = new Plantilla("6", "Eliminar", false, "");
        PNotificaciones_man_mes = new Plantilla("7", "Notificar mantenimientos del mes", false, "");
        PNotificaciones_man_ven_mes = new Plantilla("8", "Notificar mantenimientos vencidos", false, "");
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        PGuardarCalificacion = new Plantilla("3", "Guardar", false, "");
        PBuscarCalificacion = new Plantilla("4", "Buscar", false, "");
        PModificarCalificacion = new Plantilla("5", "Modificar", false, "");
        PEliminarCalificacion = new Plantilla("6", "Eliminar", false, "");
        PNotificaciones_calif_mes = new Plantilla("7", "Notificar calificaciones del mes", false, "");
        PNotificaciones_calif_ven_mes = new Plantilla("8", "Notificar calificaciones vencidas", false, "");
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        PGuardarCalib = new Plantilla("3", "Guardar", false, "");
        PBuscarCalib = new Plantilla("4", "Buscar", false, "");
        PModificarCalib = new Plantilla("5", "Modificar", false, "");
        PEliminarCalib = new Plantilla("6", "Eliminar", false, "");
        PNotificaciones_calib_mes = new Plantilla("7", "Notificar calibraciones del mes", false, "");
        PNotificaciones_calib_ven_mes = new Plantilla("8", "Notificar calibraciones vencidas", false, "");
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        PGuardarMantGen = new Plantilla("3", "Guardar", false, "");
        PBuscarMantGen = new Plantilla("4", "Buscar", false, "");
        PModificarMantGen = new Plantilla("5", "Modificar", false, "");
        PEliminarMantGen = new Plantilla("6", "Eliminar", false, "");
        PNotificaciones_mantgen_mes = new Plantilla("7", "Notificar mantenimientos generales del mes", false, "");
        PNotificaciones_mantgen_ven = new Plantilla("8", "Notificar mantenimientos generales vencidas", false, "");

        //Inicializamos los nodos y asignamos la plantilla
        GuardarUsuario = new DefaultMutableTreeNode(PGuardarUsuario);
        BuscarUsu = new DefaultMutableTreeNode(PbuscarUsuario);
        ModificarUsu = new DefaultMutableTreeNode(PModificarUsu);

        //////////////////////////////////////////////////////////////
        GuardarPer = new DefaultMutableTreeNode(PGuardarPerfil);
        BuscarPer = new DefaultMutableTreeNode(PBuscarPerfil);
        ModificarPer = new DefaultMutableTreeNode(PModficarPefil);

        ///////////////////////////////////////////////////////////////
        GuardarEquipo = new DefaultMutableTreeNode(PGuardarEquipo);
        BuscarEquipo = new DefaultMutableTreeNode(PBuscarEquipo);
        ModificarEquipo = new DefaultMutableTreeNode(PModificarEquipo);
        NotificacionesEqNuev = new DefaultMutableTreeNode(PNotificacionesEqNuev);
        ////////////////////////////////////////////////////////////////
        GuardarManteninimiento = new DefaultMutableTreeNode(PGuardarMantenimiento);
        BuscarMantenimiento = new DefaultMutableTreeNode(PBuscarMantenimiento);
        ModificarMantenimiento = new DefaultMutableTreeNode(PModificarMantenimiento);
        EliminarMantenimiento = new DefaultMutableTreeNode(PEliminarMantenimiento);
        Notificaciones_man_mes = new DefaultMutableTreeNode(PNotificaciones_man_mes);
        Notificaciones_man_ven_mes = new DefaultMutableTreeNode(PNotificaciones_man_ven_mes);
        ////////////////////////////////////////////////////////////////////
        GuardarCali = new DefaultMutableTreeNode(PGuardarCalificacion);
        BuscarCalificacion = new DefaultMutableTreeNode(PBuscarCalificacion);
        ModificarCalficacion = new DefaultMutableTreeNode(PModificarCalificacion);
        EliminarCalificacion = new DefaultMutableTreeNode(PEliminarCalificacion);
        Notificaciones_calif_mes = new DefaultMutableTreeNode(PNotificaciones_calif_mes);
        Notificaciones_calif_ven_mes = new DefaultMutableTreeNode(PNotificaciones_calif_ven_mes);

        //////////////////////////////////////////////////////////////////////////
        GuardarCalib = new DefaultMutableTreeNode(PGuardarCalib);
        BuscarCalib = new DefaultMutableTreeNode(PBuscarCalib);
        ModificarCalib = new DefaultMutableTreeNode(PModificarCalib);
        EliminarCalib = new DefaultMutableTreeNode(PEliminarCalib);
        Notificaciones_calib_mes = new DefaultMutableTreeNode(PNotificaciones_calib_mes);
        Notificaciones_calib_ven_mes = new DefaultMutableTreeNode(PNotificaciones_calib_ven_mes);
        //////////////////////////////////////////////////////////////////////////
        GuardarMantGen = new DefaultMutableTreeNode(PGuardarMantGen);
        BuscarMantGen = new DefaultMutableTreeNode(PBuscarMantGen);
        ModificarMantGen = new DefaultMutableTreeNode(PModificarMantGen);
        EliminarMantGen = new DefaultMutableTreeNode(PEliminarMantGen);
        Notificaciones_mantgen_mes = new DefaultMutableTreeNode(PNotificaciones_mantgen_mes);
        Notificaciones_mantgen_ven = new DefaultMutableTreeNode(PNotificaciones_mantgen_ven);

        //Agregamos los nodos a su padre
        hijo.add(GuardarUsuario);
        hijo.add(BuscarUsu);
        hijo.add(ModificarUsu);

        hijo2.add(GuardarPer);
        hijo2.add(BuscarPer);
        hijo2.add(ModificarPer);

        hijo4.add(GuardarEquipo);
        hijo4.add(BuscarEquipo);
        hijo4.add(ModificarEquipo);
        hijo4.add(NotificacionesEqNuev);

        hijo5.add(GuardarManteninimiento);
        hijo5.add(BuscarMantenimiento);
        hijo5.add(ModificarMantenimiento);
        hijo5.add(EliminarMantenimiento);
        hijo5.add(Notificaciones_man_mes);
        hijo5.add(Notificaciones_man_ven_mes);

        hijo6.add(GuardarCali);
        hijo6.add(BuscarCalificacion);
        hijo6.add(ModificarCalficacion);
        hijo6.add(EliminarCalificacion);
        hijo6.add(Notificaciones_calif_mes);
        hijo6.add(Notificaciones_calif_ven_mes);

        hijo7.add(GuardarCalib);
        hijo7.add(BuscarCalib);
        hijo7.add(ModificarCalib);
        hijo7.add(EliminarCalib);
        hijo7.add(Notificaciones_calib_mes);
        hijo7.add(Notificaciones_calib_ven_mes);

        hijo8.add(GuardarMantGen);
        hijo8.add(BuscarMantGen);
        hijo8.add(ModificarMantGen);
        hijo8.add(EliminarMantGen);
        hijo8.add(Notificaciones_mantgen_mes);
        hijo8.add(Notificaciones_mantgen_ven);

        root.add(hijo);
        root.add(hijo2);
        root.add(hijo4);
        root.add(hijo5);
        root.add(hijo6);
        root.add(hijo7);
        root.add(hijo8);

        modelo = new DefaultTreeModel(root);
        JTree1.setModel(modelo);
        JTree1.setEditable(true);
        JTree1.setCellRenderer(new Render());
        JTree1.setCellEditor(new Editor());

        lblPerfil = new JLabel("* Perfil");
        lblPerfil.setBounds(30, 55, 200, 20);
        lblPerfil.setFont(TLetra);
        lblPerfil.setForeground(Color.black);
        lblPerfil.setVisible(true);

        txtPerf = new JTextField("");
        txtPerf.setBounds(200, 55, 200, 20);
        txtPerf.setFont(TLetra);
        txtPerf.setForeground(Color.black);
        txtPerf.setVisible(true);
        txtPerf.addKeyListener(new KeyAdapter() {
            //Validacion Telefono
            public void keyTyped(KeyEvent e) {
                UtilFarmatech.controlarLongitudText(e, txtPerf, 150);
            }
        });

        lblEstado = new JLabel("* Estado");
        lblEstado.setBounds(30, 95, 200, 20);
        lblEstado.setFont(TLetra);
        lblEstado.setForeground(Color.black);
        lblEstado.setVisible(true);

        cboEstado = new JComboBox();
        cboEstado.addItem("ACTIVADO");
        cboEstado.addItem("DESACTIVADO");
        cboEstado.reshape(200, 95, 200, 20);
        cboEstado.setFont(TLetra);
        cboEstado.setForeground(Color.black);
        cboEstado.setVisible(true);

        btnBuscar.setBounds(430, 650, 100, 25);
        btnBuscar.addActionListener(this);
        btnBuscar.setVisible(true);

        btnNuevo.setBounds(370, 650, 100, 25);
        btnNuevo.addActionListener(this);
        btnNuevo.setVisible(true);

        btnGuardar.setBounds(470, 650, 100, 25);
        btnGuardar.addActionListener(this);
        btnGuardar.setVisible(true);

        btnActualizar.setBounds(570, 650, 100, 25);
        btnActualizar.addActionListener(this);
        btnActualizar.setVisible(true);

        btnBuscar.setBounds(670, 650, 100, 25);
        btnBuscar.addActionListener(this);
        btnBuscar.setVisible(true);

        Tablaperfiles = new JTable(dataModel) {
            @Override
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        PanelDatos = new JScrollPane(Tablaperfiles);
        PanelDatos.setBounds(370, 250, 800, 400);
        Tablaperfiles.getTableHeader().setReorderingAllowed(false);

        Tablaperfiles.addMouseListener(this);

        panelPaginador.getBtnBack().addActionListener(this);
        panelPaginador.getBtnBackward().addActionListener(this);
        panelPaginador.getBtnNext().addActionListener(this);
        panelPaginador.getBtnForward().addActionListener(this);

        Panelinforma = new JPanel();
        Panelinforma.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(Integer.parseInt("015289", 16))), "Informaciòn del perfil"));
        //Panelinfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(Integer.parseInt("015289", 16))), "Datos del equipo", WIDTH, PROPERTIES, TLetra, new Color(Integer.parseInt("EF2F24", 16))));
        Panelinforma.setBackground(getBackground());
        Panelinforma.setForeground(new Color(Integer.parseInt("EF2F24", 16)));
        Panelinforma.reshape(370, 10, 500, 200);
        Panelinforma.setLayout(null);
        Panelinforma.setVisible(true);

        Panelarbol = new JPanel();
        Panelarbol.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(Integer.parseInt("015289", 16))), "Permisos"));
        Panelarbol.setBackground(getBackground());
        Panelarbol.setForeground(new Color(Integer.parseInt("EF2F24", 16)));
        Panelarbol.reshape(1, 1, 350, 770);
        Panelarbol.setLayout(null);
        Panelarbol.setVisible(true);

        PanelGeneral1 = new Formu_Fondo(Icofondo);
        PanelGeneral1.setBorder(BorderFactory.createTitledBorder(""));

        PanelGeneral1.setBackground(Color.black);
        PanelGeneral1.setForeground(Color.red);
        PanelGeneral1.reshape(0, 0, 1366, 768);
        PanelGeneral1.setLayout(null);

        Panelinforma.add(lblPerfil);
        Panelinforma.add(txtPerf);
        Panelinforma.add(lblEstado);
        Panelinforma.add(cboEstado);

        Panelarbol.add(scrollPane);

        PanelGeneral1.add(Panelinforma);

        PanelGeneral1.add(Panelarbol);

        PanelGeneral1.add(PanelDatos);
        PanelGeneral1.add(btnGuardar);
        PanelGeneral1.add(btnNuevo);
        PanelGeneral1.add(btnActualizar);
        PanelGeneral1.add(btnBuscar);
        PanelGeneral1.add(panelPaginador);

        PanelGeneral1.setVisible(true);
        this.getContentPane().add(PanelGeneral1);
        this.ocultarColumna("Objeto");

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
    }

    private void llenarDatos(PageData page) {

        ObjectResponse response = cmdProfile.findProfiles(new DtoPerfil(),page);
        ArrayList<DtoPerfil> profiles=(ArrayList<DtoPerfil>)response.getRecords();
         pageData = response.getpageResponse();
        setDataModel(getPerfiles(profiles, getDataModel()));

    }

    private void vaciar() {
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

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnNuevo) {
            txtPerf.setText("");
            cboEstado.setSelectedIndex(0);
            btnGuardar.setEnabled(true);

            PGuardarUsuario.setValor(false);
            PbuscarUsuario.setValor(false);
            PModificarUsu.setValor(false);

            PGuardarPerfil.setValor(false);
            PBuscarPerfil.setValor(false);
            PModficarPefil.setValor(false);

            PGuardarEquipo.setValor(false);
            PBuscarEquipo.setValor(false);
            PModificarEquipo.setValor(false);
            PNotificacionesEqNuev.setValor(false);

            PGuardarMantenimiento.setValor(false);
            PBuscarMantenimiento.setValor(false);
            PModificarMantenimiento.setValor(false);
            PEliminarMantenimiento.setValor(false);
            PNotificaciones_man_mes.setValor(false);
            PNotificaciones_man_ven_mes.setValor(false);

            PGuardarCalificacion.setValor(false);
            PBuscarCalificacion.setValor(false);
            PModificarCalificacion.setValor(false);
            PEliminarCalificacion.setValor(false);
            PNotificaciones_calif_mes.setValor(false);
            PNotificaciones_calif_ven_mes.setValor(false);

            PGuardarCalib.setValor(false);
            PBuscarCalib.setValor(false);
            PModificarCalib.setValor(false);
            PEliminarCalib.setValor(false);
            PNotificaciones_calib_mes.setValor(false);
            PNotificaciones_calib_ven_mes.setValor(false);

            PGuardarMantGen.setValor(false);
            PBuscarMantGen.setValor(false);
            PModificarMantGen.setValor(false);
            PEliminarMantGen.setValor(false);
            PNotificaciones_mantgen_mes.setValor(false);
            PNotificaciones_mantgen_ven.setValor(false);
            panelPaginador.enablePageButtons();
            vaciar();
            llenarDatos(pageData);

        }

        if (e.getSource() == btnGuardar) {
            ventana.bloquear(this);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    String Perfil;
                    Long Estado;
                    boolean GuardarUsuariao, BuscarUsuario, ModificarUsuario, EliminarUsuario, GuardarPerfil, BuscarPerfil, ModificarPerfil, EliminarPerfil, GuardarProvee, BuscarProvee, ModificarProvee, EliminarProvee,
                            GuardarEquipo, BuscarEquipo, ModificarEquipo, EliminarEquipo, NotificaEquipos,
                            GuardarMantenimiento, BuscarMantenimiento, ModificarMantenimiento, EliminarMantenimiento, Notificaciones_mant_mes, Notificaciones_man_ven_mes,
                            GuardarCalificacion, BuscarCalificacion, ModificarCalificacion, EliminarCalificacion, Notificaciones_calif_mes, Notificaciones_calif_ven_mes,
                            GuardarCalibracion, BuscarCalibracion, ModificarCalibracion, EliminarCalibracion, Notificaciones_calib_mes, Notificaciones_calib_ven,
                            GuardarMantGen, BuscarMantGen, ModificarMantGen, EliminarMantGen, Notificaciones_mantgen_mes, Notificaciones_mantgen_ven;

                    Perfil = txtPerf.getText();
                    Estado = Estados.getByDescription(cboEstado.getSelectedItem().toString()).getCode();
                    GuardarUsuariao = PGuardarUsuario.getValor();
                    BuscarUsuario = PbuscarUsuario.getValor();
                    ModificarUsuario = PModificarUsu.getValor();

                    GuardarPerfil = PGuardarPerfil.getValor();
                    BuscarPerfil = PBuscarPerfil.getValor();
                    ModificarPerfil = PModficarPefil.getValor();

                    GuardarEquipo = PGuardarEquipo.getValor();
                    BuscarEquipo = PBuscarEquipo.getValor();
                    ModificarEquipo = PModificarEquipo.getValor();
                    NotificaEquipos = PNotificacionesEqNuev.getValor();

                    GuardarMantenimiento = PGuardarMantenimiento.getValor();
                    BuscarMantenimiento = PBuscarEquipo.getValor();
                    ModificarMantenimiento = PModificarMantenimiento.getValor();
                    EliminarMantenimiento = PEliminarMantenimiento.getValor();
                    Notificaciones_mant_mes = PNotificaciones_man_mes.getValor();
                    Notificaciones_man_ven_mes = PNotificaciones_man_ven_mes.getValor();

                    GuardarCalificacion = PGuardarCalificacion.getValor();
                    BuscarCalificacion = PBuscarCalificacion.getValor();
                    ModificarCalificacion = PModificarCalificacion.getValor();
                    EliminarCalificacion = PModificarCalificacion.getValor();
                    Notificaciones_calif_mes = PNotificaciones_calif_mes.getValor();
                    Notificaciones_calif_ven_mes = PNotificaciones_calif_ven_mes.getValor();

                    GuardarCalibracion = PGuardarCalib.getValor();
                    BuscarCalibracion = PBuscarCalib.getValor();
                    ModificarCalibracion = PModificarCalib.getValor();
                    EliminarCalibracion = PEliminarCalib.getValor();
                    Notificaciones_calib_mes = PNotificaciones_calib_mes.getValor();
                    Notificaciones_calib_ven = PNotificaciones_calib_mes.getValor();

                    GuardarMantGen = PGuardarMantGen.getValor();
                    BuscarMantGen = PBuscarMantGen.getValor();
                    ModificarMantGen = PModificarMantGen.getValor();
                    EliminarMantGen = PEliminarMantGen.getValor();
                    Notificaciones_mantgen_mes = PNotificaciones_mantgen_mes.getValor();
                    Notificaciones_mantgen_ven = PNotificaciones_mantgen_ven.getValor();


                    boolean valido = true;
                    if (Perfil.equals("")) {
                        valido = false;
                    }

                    if (valido == true) {
                        DtoPerfil perfil = new DtoPerfil();
                        perfil.setPerfil(Perfil);
                        perfil.setEstado(Estado);
                        perfil.setBuscarCalibracion(BuscarCalibracion);
                        perfil.setBuscarCalificacion(BuscarCalificacion);
                        perfil.setBuscarEquipo(BuscarEquipo);
                        perfil.setBuscarMantGen(BuscarMantGen);
                        perfil.setBuscarMantenimiento(BuscarMantenimiento);
                        perfil.setBuscarPerfil(BuscarPerfil);
                        perfil.setBuscarUsuario(BuscarUsuario);
                        perfil.setEliminarCalibracion(EliminarCalibracion);
                        perfil.setEliminarCalificacion(EliminarCalificacion);
                        perfil.setEliminarMantGen(EliminarMantGen);
                        perfil.setEliminarMantenimiento(EliminarMantenimiento);
                        perfil.setGuardarCalibracion(GuardarCalibracion);
                        perfil.setGuardarCalificacion(GuardarCalificacion);
                        perfil.setGuardarEquipo(GuardarEquipo);
                        perfil.setGuardarMantGen(GuardarMantGen);
                        perfil.setGuardarMantenimiento(GuardarMantenimiento);
                        perfil.setGuardarPerfil(GuardarPerfil);
                        perfil.setGuardarUsuariao(GuardarUsuariao);
                        perfil.setModificarCalibracion(ModificarCalibracion);
                        perfil.setModificarCalificacion(ModificarCalificacion);
                        perfil.setModificarEquipo(ModificarEquipo);
                        perfil.setModificarMantGen(ModificarMantGen);
                        perfil.setModificarMantenimiento(ModificarMantenimiento);
                        perfil.setModificarPerfil(ModificarPerfil);
                        perfil.setModificarUsuario(ModificarUsuario);
                        perfil.setNotificaEquipos(NotificaEquipos);
                        perfil.setNotificacionesMantgenmes(Notificaciones_mantgen_mes);
                        perfil.setNotificacionesMantgenvenc(Notificaciones_mantgen_ven);
                        perfil.setNotificaciones_calib_mes(Notificaciones_calib_mes);
                        perfil.setNotificaciones_calib_ven(Notificaciones_calib_ven);
                        perfil.setNotificaciones_calif_mes(Notificaciones_calif_mes);
                        perfil.setNotificaciones_calif_ven_mes(Notificaciones_calif_ven_mes);
                        perfil.setNotificaciones_man_ven_mes(Notificaciones_man_ven_mes);
                        perfil.setNotificaciones_mant_mes(Notificaciones_mant_mes);
                        boolean result = cmdProfile.createProfile(perfil);

                        if (result == false) {
                            JOptionPane.showMessageDialog(null, " No fue posible ingresar el nuevo perfil.");
                        } else {
                            JOptionPane.showMessageDialog(null, " Perfil Ingresado");
                            panelPaginador.enablePageButtons();
                            pageData.setNumRows(0);
                            vaciar();
                            ventana.desbloquear();
                            llenarDatos(pageData);
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

        if (e.getSource() == btnBuscar) {
            ventana.bloquear(this);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    String Perfil;
                    Perfil = txtPerf.getText().trim();

                    if (Perfil.trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "Recuerda que debes ingresar el nombre del perfil a buscar", "Metro-Farm v1.0", JOptionPane.ERROR_MESSAGE);
                    } else {
                        DtoPerfil perfilFind = new DtoPerfil();
                        perfilFind.setPerfil(Perfil);
                        ObjectResponse response= cmdProfile.findProfiles(perfilFind,null);
                         ArrayList<DtoPerfil> arrayBuscar = (ArrayList<DtoPerfil>)response.getRecords();
                        if (arrayBuscar != null && arrayBuscar.size() > 0) {
                            panelPaginador.enablePageButtons();
                            vaciar();
                            setDataModel(getPerfiles(arrayBuscar, getDataModel()));
                        } else {
                            JOptionPane.showMessageDialog(null, "No existe un perfil registrado con este código", "MetroFarm v1.0", JOptionPane.ERROR_MESSAGE);
                        }
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
                    String Perfil;
                    long Estado, Codigo;
                    boolean GuardarUsuariao, BuscarUsuario, ModificarUsuario, EliminarUsuario, GuardarPerfil, BuscarPerfil, ModificarPerfil, EliminarPerfil, GuardarProvee, BuscarProvee, ModificarProvee, EliminarProvee,
                            GuardarEquipo, BuscarEquipo, ModificarEquipo, EliminarEquipo, NotificaEquipos,
                            GuardarMantenimiento, BuscarMantenimiento, ModificarMantenimiento, EliminarMantenimiento, Notificaciones_mant_mes, Notificaciones_man_ven_mes,
                            GuardarCalificacion, BuscarCalificacion, ModificarCalificacion, EliminarCalificacion, Notificaciones_calif_mes, Notificaciones_calif_ven_mes,
                            GuardarCalibracion, BuscarCalibracion, ModificarCalibracion, EliminarCalibracion, Notificaciones_calib_mes, Notificaciones_calib_ven,
                            GuardarMantGen, BuscarMantGen, ModificarMantGen, EliminarMantGen, Notificaciones_mantgen_mes, Notificaciones_mantgen_ven;
                    if (Tablaperfiles.getSelectedRow() > -1) {
                        Codigo = ((DtoPerfil) Tablaperfiles.getValueAt(Tablaperfiles.getSelectedRow(), 2)).getCodperfil();
                        Perfil = txtPerf.getText();
                        Estado = Estados.getByDescription(cboEstado.getSelectedItem() + "").getCode();
                        GuardarUsuariao = PGuardarUsuario.getValor();
                        BuscarUsuario = PbuscarUsuario.getValor();
                        ModificarUsuario = PModificarUsu.getValor();

                        GuardarPerfil = PGuardarPerfil.getValor();
                        BuscarPerfil = PBuscarPerfil.getValor();
                        ModificarPerfil = PModficarPefil.getValor();

                        GuardarEquipo = PGuardarEquipo.getValor();
                        BuscarEquipo = PBuscarEquipo.getValor();
                        ModificarEquipo = PModificarEquipo.getValor();
                        NotificaEquipos = PNotificacionesEqNuev.getValor();

                        GuardarMantenimiento = PGuardarMantenimiento.getValor();
                        BuscarMantenimiento = PBuscarMantenimiento.getValor();
                        ModificarMantenimiento = PModificarMantenimiento.getValor();
                        EliminarMantenimiento = PEliminarMantenimiento.getValor();
                        Notificaciones_mant_mes = PNotificaciones_man_mes.getValor();
                        Notificaciones_man_ven_mes = PNotificaciones_man_ven_mes.getValor();

                        GuardarCalificacion = PGuardarCalificacion.getValor();
                        BuscarCalificacion = PBuscarCalificacion.getValor();
                        ModificarCalificacion = PModificarCalificacion.getValor();
                        EliminarCalificacion = PModificarCalificacion.getValor();
                        Notificaciones_calif_mes = PNotificaciones_calif_mes.getValor();
                        Notificaciones_calif_ven_mes = PNotificaciones_calif_ven_mes.getValor();

                        GuardarCalibracion = PGuardarCalib.getValor();
                        BuscarCalibracion = PBuscarCalib.getValor();
                        ModificarCalibracion = PModificarCalib.getValor();
                        EliminarCalibracion = PEliminarCalib.getValor();
                        Notificaciones_calib_mes = PNotificaciones_calib_mes.getValor();
                        Notificaciones_calib_ven = PNotificaciones_calib_mes.getValor();

                        GuardarMantGen = PGuardarMantGen.getValor();
                        BuscarMantGen = PBuscarMantGen.getValor();
                        ModificarMantGen = PModificarMantGen.getValor();
                        EliminarMantGen = PEliminarMantGen.getValor();
                        Notificaciones_mantgen_mes = PNotificaciones_mantgen_mes.getValor();
                        Notificaciones_mantgen_ven = PNotificaciones_mantgen_ven.getValor();

                        boolean valido = true;
                        if (Perfil.equals("")) {
                            valido = false;
                        }
                        if (valido == true) {
                            DtoPerfil perfil = new DtoPerfil();
                            perfil.setCodperfil(Codigo);
                            perfil.setPerfil(Perfil);
                            perfil.setEstado(Estado);
                            perfil.setBuscarCalibracion(BuscarCalibracion);
                            perfil.setBuscarCalificacion(BuscarCalificacion);
                            perfil.setBuscarEquipo(BuscarEquipo);
                            perfil.setBuscarMantGen(BuscarMantGen);
                            perfil.setBuscarMantenimiento(BuscarMantenimiento);
                            perfil.setBuscarPerfil(BuscarPerfil);
                            perfil.setBuscarUsuario(BuscarUsuario);
                            perfil.setEliminarCalibracion(EliminarCalibracion);
                            perfil.setEliminarCalificacion(EliminarCalificacion);
                            perfil.setEliminarMantGen(EliminarMantGen);
                            perfil.setEliminarMantenimiento(EliminarMantenimiento);
                            perfil.setGuardarCalibracion(GuardarCalibracion);
                            perfil.setGuardarCalificacion(GuardarCalificacion);
                            perfil.setGuardarEquipo(GuardarEquipo);
                            perfil.setGuardarMantGen(GuardarMantGen);
                            perfil.setGuardarMantenimiento(GuardarMantenimiento);
                            perfil.setGuardarPerfil(GuardarPerfil);
                            perfil.setGuardarUsuariao(GuardarUsuariao);
                            perfil.setModificarCalibracion(ModificarCalibracion);
                            perfil.setModificarCalificacion(ModificarCalificacion);
                            perfil.setModificarEquipo(ModificarEquipo);
                            perfil.setModificarMantGen(ModificarMantGen);
                            perfil.setModificarMantenimiento(ModificarMantenimiento);
                            perfil.setModificarPerfil(ModificarPerfil);
                            perfil.setModificarUsuario(ModificarUsuario);
                            perfil.setNotificaEquipos(NotificaEquipos);
                            perfil.setNotificacionesMantgenmes(Notificaciones_mantgen_mes);
                            perfil.setNotificacionesMantgenvenc(Notificaciones_mantgen_ven);
                            perfil.setNotificaciones_calib_mes(Notificaciones_calib_mes);
                            perfil.setNotificaciones_calib_ven(Notificaciones_calib_ven);
                            perfil.setNotificaciones_calif_mes(Notificaciones_calif_mes);
                            perfil.setNotificaciones_calif_ven_mes(Notificaciones_calif_ven_mes);
                            perfil.setNotificaciones_man_ven_mes(Notificaciones_man_ven_mes);
                            perfil.setNotificaciones_mant_mes(Notificaciones_mant_mes);
                            boolean result = cmdProfile.updateProfile(perfil);
                            if (result == false) {
                                JOptionPane.showMessageDialog(null, " No fue posible actualizar la informacion del perfil.");
                            } else {
                                JOptionPane.showMessageDialog(null, " Perfil Actualizado");
                                panelPaginador.enablePageButtons();
                                vaciar();
                                ventana.desbloquear();
                                llenarDatos(pageData);
                            }

                        } else {

                            JOptionPane.showMessageDialog(null, " Los campos marcados con * son obligatorios.");
                        }
                        ventana.desbloquear();
                        return null;
                    } else {
                        JOptionPane.showMessageDialog(null, " Debes seleccionar un registro de la tabla a actualizar.");
                        return null;
                    }

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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == Tablaperfiles) {

            Tablaperfiles.setSelectionBackground(new Color(Integer.parseInt("015289", 16)));
            ventana.bloquear(this);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    int fila;
                    fila = Tablaperfiles.getSelectedRow();
                    if (fila > -1) {
                        String codigo;


                        try {
                            DtoPerfil Buscar = (DtoPerfil) Tablaperfiles.getValueAt(fila, 2);
                            btnGuardar.setEnabled(false);
                            btnActualizar.setEnabled(true);
                            txtPerf.setText(Buscar.getPerfil());
                            cboEstado.setSelectedItem(Estados.getByCode(Buscar.getEstado()).getDescription());

                            PGuardarUsuario.setValor(Buscar.isGuardarUsuariao());
                            PbuscarUsuario.setValor(Buscar.isBuscarUsuario());
                            PModificarUsu.setValor(Buscar.isModificarUsuario());

                            PGuardarPerfil.setValor(Buscar.isGuardarPerfil());
                            PBuscarPerfil.setValor(Buscar.isBuscarPerfil());
                            PModficarPefil.setValor(Buscar.isModificarPerfil());

                            PGuardarEquipo.setValor(Buscar.isGuardarEquipo());
                            PBuscarEquipo.setValor(Buscar.isBuscarEquipo());
                            PModificarEquipo.setValor(Buscar.isModificarEquipo());
                            PNotificacionesEqNuev.setValor(Buscar.isNotificaEquipos());

                            PGuardarMantenimiento.setValor(Buscar.isGuardarMantenimiento());
                            PBuscarMantenimiento.setValor(Buscar.isBuscarMantenimiento());
                            PModificarMantenimiento.setValor(Buscar.isModificarMantenimiento());
                            PEliminarMantenimiento.setValor(Buscar.isEliminarMantenimiento());
                            PNotificaciones_man_mes.setValor(Buscar.isNotificaciones_mant_mes());
                            PNotificaciones_man_ven_mes.setValor(Buscar.isNotificaciones_man_ven_mes());

                            PGuardarCalificacion.setValor(Buscar.isGuardarCalificacion());
                            PBuscarCalificacion.setValor(Buscar.isBuscarCalificacion());
                            PModificarCalificacion.setValor(Buscar.isModificarCalificacion());
                            PEliminarCalificacion.setValor(Buscar.isEliminarCalificacion());
                            PNotificaciones_calif_mes.setValor(Buscar.isNotificaciones_calif_mes());
                            PNotificaciones_calif_ven_mes.setValor(Buscar.isNotificaciones_calif_ven_mes());

                            PGuardarCalib.setValor(Buscar.isGuardarCalibracion());
                            PBuscarCalib.setValor(Buscar.isBuscarCalibracion());
                            PModificarCalib.setValor(Buscar.isModificarCalibracion());
                            PEliminarCalib.setValor(Buscar.isEliminarCalibracion());
                            PNotificaciones_calib_mes.setValor(Buscar.isNotificaciones_calib_mes());
                            PNotificaciones_calib_ven_mes.setValor(Buscar.isNotificaciones_calib_ven());

                            PGuardarMantGen.setValor(Buscar.isGuardarMantGen());
                            PBuscarMantGen.setValor(Buscar.isBuscarMantGen());
                            PModificarMantGen.setValor(Buscar.isModificarMantGen());
                            PEliminarMantGen.setValor(Buscar.isEliminarMantGen());
                            PNotificaciones_mantgen_mes.setValor(Buscar.isNotificacionesMantgenmes());
                            PNotificaciones_mantgen_ven.setValor(Buscar.isNotificacionesMantgenvenc());

                            JTree1.expandRow(1);
                            JTree1.expandRow(6);
                            JTree1.expandRow(11);
                            JTree1.expandRow(17);
                            JTree1.expandRow(24);
                            JTree1.expandRow(31);
                            JTree1.expandRow(38);



                        } catch (Exception exe) {
                            exe.getMessage();
                        }

                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();
        }
    }

    public DefaultTableModel getPerfiles(ArrayList<DtoPerfil> perfiles, DefaultTableModel model) {

        for (DtoPerfil c : perfiles) {
            //	nit	rut	nombre	representante	contacto	direccion	telefono	celular	fax	cuenta	banco	correo
            Object nuevo[] = {c.getPerfil(), Estados.getByCode(c.getEstado()).getDescription(), c};
            model.addRow(nuevo);

        }
        return model;

    }

    private void ocultarColumna(String column) {
        TableColumn tc = Tablaperfiles.getColumn(column); //Obtienes la columna
        tc.setPreferredWidth(0); //Y le das un tamaño
        tc.setMinWidth(0);
        tc.setMaxWidth(0);
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
}
