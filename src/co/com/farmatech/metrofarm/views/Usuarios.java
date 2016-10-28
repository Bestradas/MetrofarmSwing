/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.views;

import co.com.farmatech.metrofarm.cmd.CmdProfile;
import co.com.farmatech.metrofarm.cmd.CmdUsuarios;
import co.com.farmatech.metrofarm.utils.ComboItem;
import co.com.farmatech.metrofarm.utils.Formu_Fondo;
import co.com.farmatech.metrofarm.utils.ItemRenderer;
import co.com.farmatech.metrofarm.utils.PanelPaginador;
import co.com.farmatech.metrofarm.utils.UtilFarmatech;
import co.janker.dto.objresponse.ObjectResponse;
import co.jankins.psf.common.enums.Estados;
import co.jankins.psf.common.enums.TipoDocumento;
import co.jankins.psf.common.global.GlobalUtils;
import com.co.farmatech.metrofarm.dto.DtoPerfil;
import com.co.farmatech.metrofarm.dto.DtoUsuario;
import com.co.farmatech.metrofarm.dto.PageData;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
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
import javax.swing.JToolBar;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import ventana.bloqueo.VentanaBloqueo;

/**
 *
 * @author bestrada
 */
public class Usuarios extends JInternalFrame implements ActionListener, MouseListener {

    JToolBar Barr = new JToolBar();
    private Object[][] DetallesIdentificacion = new Object[0][0];
    private Object[] ColumnasIdentificacion = {"Código", "Tipo de Identificación", "Nombres", "Apellidos", "Teléfono", "Celular", "Correo", "Perfil", "Objeto"};
    private DefaultTableModel dataModel = new DefaultTableModel(DetallesIdentificacion, ColumnasIdentificacion);
    JLabel lblcodigo, lbltipoid, lblnombres, lblapellidos, lbltelefono1, lbltelefono2, lblcelular, lblcorreo, lblusuario, lblcontraseña, lblconfirmar, lblasignar, fondo, lblpregunta, lblrespuesta, lblestado;
    JTextField txtcodigo, txtnombres, txtapellido, txttelefono1, txttelefono2, txtcelular, txtcorreo, txtusuario, txtcontraseña, txtconfirmar, txtrespuesta;
    JComboBox cbotipoid, cboasignar, cbopregunta, cboestado;
    Font TLetra = new Font("Arial", Font.PLAIN, 12);
    ImageIcon img, Icofondo;
    Formu_Fondo PanelGeneral1;
    public JTable TablaDatos;
    JScrollPane PanelDatos;
    JButton btnNuevo;
    JButton btnGuardar;
    JButton btnActualizar;
    JButton btnBuscar;
    JButton btnEliminar;
    JButton btnSalir;
    JPanel Panelpersonal, Panelperfil;
    Vector comboModel;
    CmdProfile cmdProfile;
    CmdUsuarios cmdUser;
    String ruta = "co/com/farmatech/metrofarm/resources/imagenes/";
    private final VentanaBloqueo ventana = new VentanaBloqueo();
    DtoUsuario usuarioLogeado;
    PanelPaginador panelPaginador;
    PageData pageData;

    public Usuarios() {
        super("Usuarios del Sistema");

        cmdProfile = new CmdProfile();
        cmdUser = new CmdUsuarios();

        panelPaginador = new PanelPaginador();
        panelPaginador.setBounds(760, 690, 300, 29);

        pageData = panelPaginador.getPageData();
        pageData.setRowsPerPage(10);

        ((JPanel) getContentPane()).setOpaque(false);  // para que muestre la imagen
        this.setSize(1366, 768);
        this.setLocation(100, 100);
        setResizable(false);
        getContentPane().setLayout(null);
        setBackground(Color.white);
        setFrameIcon(new ImageIcon(getClass().getClassLoader().getResource(ruta + "FARMA.png")));

        btnNuevo = new JButton("Nuevo", new ImageIcon(getClass().getClassLoader().getResource(ruta + "nuevo.gif")));
        btnGuardar = new JButton("Guardar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "guardar.gif")));
        btnActualizar = new JButton("Actualizar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "actualizar.gif")));
        btnBuscar = new JButton("Buscar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "buscar.gif")));
        btnEliminar = new JButton("Eliminar", new ImageIcon(getClass().getClassLoader().getResource(ruta + "eliminar.gif")));
        btnSalir = new JButton("Salir", new ImageIcon(getClass().getClassLoader().getResource(ruta + "volver.gif")));

        this.usuarioLogeado = GlobalUtils.getContext().getLoginUser();

        img = new ImageIcon("Imagenes\\.png");
        JLabel fondo = new JLabel(img);
        fondo.setBounds(0, 0, 1115, 875);

        setVisible(true);
        //setLocationRelativeTo(null);

        comboModel = new Vector();

        Icofondo = new ImageIcon(getClass().getClassLoader().getResource(ruta + "fondomenu.png"));

        PanelGeneral1 = new Formu_Fondo(Icofondo);
        PanelGeneral1.setBorder(BorderFactory.createTitledBorder(""));
        PanelGeneral1.setBackground(Color.white);
        PanelGeneral1.setForeground(Color.red);
        PanelGeneral1.reshape(0, 0, 1366, 768);
        PanelGeneral1.setLayout(null);

        Panelpersonal = new JPanel();
        Panelpersonal.setBorder(BorderFactory.createTitledBorder("Información personal"));
        Panelpersonal.setOpaque(false);
        Panelpersonal.setForeground(Color.red);
        Panelpersonal.reshape(110, 30, 780, 200);
        Panelpersonal.setLayout(null);

        Panelperfil = new JPanel();
        Panelperfil.setBorder(BorderFactory.createTitledBorder("Perfil de usuario"));
        Panelperfil.setOpaque(false);
        Panelperfil.setForeground(Color.red);
        Panelperfil.reshape(110, 250, 600, 200);
        Panelperfil.setLayout(null);

        lblcodigo = new JLabel("* Número de identificación");
        lblcodigo.setBounds(30, 70, 150, 20);
        lblcodigo.setFont(TLetra);
        lblcodigo.setForeground(Color.black);
        lblcodigo.setVisible(true);

        txtcodigo = new JTextField("");
        txtcodigo.setBounds(180, 70, 100, 20);
        txtcodigo.setFont(TLetra);
        txtcodigo.setForeground(Color.black);
        txtcodigo.setVisible(true);
        txtcodigo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                UtilFarmatech.controlarLongitudText(e, txtcodigo, 25);
                char caracter = e.getKeyChar();
                if (((caracter < '0')
                        || (caracter > '9'))
                        && (caracter != KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    e.consume();
                    JOptionPane.showMessageDialog(null, "No se permiten letras en este campo", "METROFARM", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        lbltipoid = new JLabel("* Tipo de identificación");
        lbltipoid.setBounds(410, 70, 150, 20);
        lbltipoid.setFont(TLetra);
        lbltipoid.setForeground(Color.black);
        lbltipoid.setVisible(true);

        cbotipoid = new JComboBox();
        cbotipoid.addItem(TipoDocumento.CC.getNombre());
        cbotipoid.addItem(TipoDocumento.TI.getNombre());
        cbotipoid.reshape(570, 70, 200, 20);
        cbotipoid.setFont(TLetra);
        cbotipoid.setForeground(Color.black);
        cbotipoid.setVisible(true);

        lblnombres = new JLabel("* Nombres");
        lblnombres.reshape(30, 100, 150, 20);
        lblnombres.setFont(TLetra);
        lblnombres.setForeground(Color.black);
        lblnombres.setVisible(true);

        txtnombres = new JTextField("");
        txtnombres.setBounds(180, 100, 200, 20);
        txtnombres.setFont(TLetra);
        txtnombres.setForeground(Color.black);
        txtnombres.setVisible(true);
        txtnombres.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                UtilFarmatech.controlarLongitudText(e, txtnombres, 100);
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

        lbltelefono1 = new JLabel("* Teléfono1");
        lbltelefono1.reshape(30, 130, 150, 20);
        lbltelefono1.setFont(TLetra);
        lbltelefono1.setForeground(Color.black);
        lbltelefono1.setVisible(true);

        txttelefono1 = new JTextField("");
        txttelefono1.setBounds(180, 130, 150, 20);
        txttelefono1.setFont(TLetra);
        txttelefono1.setForeground(Color.black);
        txttelefono1.setVisible(true);
        txttelefono1.addKeyListener(new KeyAdapter() {
            //Validacion Telefono
            public void keyTyped(KeyEvent e) {
                UtilFarmatech.controlarLongitudText(e, txttelefono1, 20);
                char caracter = e.getKeyChar();
                if (((caracter < '0')
                        || (caracter > '9'))
                        && (caracter != KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    e.consume();
                    JOptionPane.showMessageDialog(null, "No se permiten letras en este campo", "METROFARM", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        lblcelular = new JLabel("* Célular");
        lblcelular.setBounds(30, 160, 150, 20);
        lblcelular.setFont(TLetra);
        lblcelular.setForeground(Color.black);
        lblcelular.setVisible(true);

        txtcelular = new JTextField("");
        txtcelular.setBounds(180, 160, 150, 20);
        txtcelular.setFont(TLetra);
        txtcelular.setForeground(Color.black);
        txtcelular.setVisible(true);
        txtcelular.addKeyListener(new KeyAdapter() {
            //Validacion Telefono
            public void keyTyped(KeyEvent e) {
                UtilFarmatech.controlarLongitudText(e, txtcelular, 20);
                char caracter = e.getKeyChar();
                if (((caracter < '0')
                        || (caracter > '9'))
                        && (caracter != KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    e.consume();
                    JOptionPane.showMessageDialog(null, "No se permiten letras en este campo", "METROFARM", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        lblapellidos = new JLabel("* Apellidos");
        lblapellidos.reshape(410, 100, 150, 20);
        lblapellidos.setFont(TLetra);
        lblapellidos.setForeground(Color.black);
        lblapellidos.setVisible(true);

        txtapellido = new JTextField("");
        txtapellido.setBounds(570, 100, 200, 20);
        txtapellido.setFont(TLetra);
        txtapellido.setForeground(Color.black);
        txtapellido.setVisible(true);
        txtapellido.addKeyListener(new KeyAdapter() {
            //Validacion Apellido
            public void keyTyped(KeyEvent e) {
                UtilFarmatech.controlarLongitudText(e, txtapellido, 100);
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

        lbltelefono2 = new JLabel(" Teléfono 2");
        lbltelefono2.reshape(410, 130, 150, 20);
        lbltelefono2.setFont(TLetra);
        lbltelefono2.setForeground(Color.black);
        lbltelefono2.setVisible(true);

        txttelefono2 = new JTextField("");
        txttelefono2.setBounds(570, 130, 150, 20);
        txttelefono2.setFont(TLetra);
        txttelefono2.setForeground(Color.black);
        txttelefono2.setVisible(true);
        txttelefono2.addKeyListener(new KeyAdapter() {
            //Validacion Telefono
            public void keyTyped(KeyEvent e) {
                UtilFarmatech.controlarLongitudText(e, txttelefono2, 20);
                char caracter = e.getKeyChar();
                if (((caracter < '0')
                        || (caracter > '9'))
                        && (caracter != KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    e.consume();
                    JOptionPane.showMessageDialog(null, "No se permiten letras en este campo", "METROFARM", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        lblcorreo = new JLabel(" Correo electrónico*");
        lblcorreo.setBounds(410, 160, 150, 20);
        lblcorreo.setFont(TLetra);
        lblcorreo.setForeground(Color.black);
        lblcorreo.setVisible(true);

        txtcorreo = new JTextField("");
        txtcorreo.setBounds(570, 160, 200, 20);
        txtcorreo.setFont(TLetra);
        txtcorreo.setForeground(Color.black);
        txtcorreo.setVisible(true);
        txtcorreo.addKeyListener(new KeyAdapter() {
            //Validacion Telefono
            public void keyTyped(KeyEvent e) {
                UtilFarmatech.controlarLongitudText(e, txtcorreo, 300);
            }
        });

        lblusuario = new JLabel("* Usuario");
        lblusuario.setBounds(30, 40, 150, 20);
        lblusuario.setFont(TLetra);
        lblusuario.setForeground(Color.black);
        lblusuario.setVisible(true);

        txtusuario = new JTextField("");
        txtusuario.setBounds(180, 40, 150, 20);
        txtusuario.setFont(TLetra);
        txtusuario.setForeground(Color.black);
        txtusuario.setVisible(true);
        txtusuario.addKeyListener(new KeyAdapter() {
            //Validacion Telefono
            public void keyTyped(KeyEvent e) {
                UtilFarmatech.controlarLongitudText(e, txtusuario, 150);
            }
        });

        lblcontraseña = new JLabel("*Contraseña");
        lblcontraseña.setBounds(30, 70, 150, 20);
        lblcontraseña.setFont(TLetra);
        lblcontraseña.setForeground(Color.black);
        lblcontraseña.setVisible(true);

        txtcontraseña = new JTextField("");
        txtcontraseña.setBounds(180, 70, 150, 20);
        txtcontraseña.setFont(TLetra);
        txtcontraseña.setForeground(Color.black);
        txtcontraseña.setVisible(true);
        txtcontraseña.addKeyListener(new KeyAdapter() {
            //Validacion Telefono
            public void keyTyped(KeyEvent e) {
                UtilFarmatech.controlarLongitudText(e, txtcontraseña, 150);
            }
        });

        lblasignar = new JLabel(" Asignar perfil");
        lblasignar.setBounds(340, 40, 150, 20);
        lblasignar.setFont(TLetra);
        lblasignar.setForeground(Color.black);
        lblasignar.setVisible(true);

        cboasignar = new JComboBox(comboModel);
        comboModel.add(new ComboItem(-1L, "Seleccione uno"));
        traer_combo();
        cboasignar.setBounds(430, 40, 150, 20);
        cboasignar.setFont(TLetra);
        cboasignar.setForeground(Color.black);
        cboasignar.setVisible(true);

        lblestado = new JLabel(" Estado");
        lblestado.setBounds(340, 70, 150, 20);
        lblestado.setFont(TLetra);
        lblestado.setForeground(Color.black);
        lblestado.setVisible(true);

        cboestado = new JComboBox();
        cboestado.setBounds(430, 70, 150, 20);
        cboestado.addItem("ACTIVADO");
        cboestado.addItem("DESACTIVADO");
        cboestado.setFont(TLetra);
        cboestado.setForeground(Color.black);
        cboestado.setVisible(true);


        lblpregunta = new JLabel("*Pregunta de seguridad");
        lblpregunta.setBounds(30, 100, 150, 20);
        lblpregunta.setFont(TLetra);
        lblpregunta.setForeground(Color.black);
        lblpregunta.setVisible(true);

        cbopregunta = new JComboBox();
        cbopregunta.setBounds(180, 100, 240, 20);
        cbopregunta.addItem("Lugar de nacimiento de la madre");
        cbopregunta.addItem("Mejor amigo de la infancia");
        cbopregunta.addItem("Nombre de la primera mascota");
        cbopregunta.addItem("Profesor favorito");
        cbopregunta.addItem("Personaje historico favorito");
        cbopregunta.addItem("Ocupacion del abuelo");
        cbopregunta.setFont(TLetra);
        cbopregunta.setForeground(Color.black);
        cbopregunta.setVisible(true);

        lblrespuesta = new JLabel("*Respuesta");
        lblrespuesta.setBounds(30, 130, 150, 20);
        lblrespuesta.setFont(TLetra);
        lblrespuesta.setForeground(Color.black);
        lblrespuesta.setVisible(true);

        txtrespuesta = new JTextField("");
        txtrespuesta.setBounds(180, 130, 150, 20);
        txtrespuesta.setFont(TLetra);
        txtrespuesta.setForeground(Color.black);
        txtrespuesta.setVisible(true);
        txtrespuesta.addKeyListener(new KeyAdapter() {
            //Validacion Telefono
            public void keyTyped(KeyEvent e) {
                UtilFarmatech.controlarLongitudText(e, txtrespuesta, 200);
            }
        });


        TablaDatos = new JTable(dataModel) {
            @Override
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        PanelDatos = new JScrollPane(TablaDatos);
        TablaDatos.setSize(3000, 200);
        TablaDatos.getColumnModel().getColumn(0).setMinWidth(130);
        TablaDatos.getColumnModel().getColumn(0).setMinWidth(130);
        TablaDatos.getColumnModel().getColumn(1).setMinWidth(140);
        TablaDatos.getColumnModel().getColumn(2).setMinWidth(130);
        TablaDatos.getColumnModel().getColumn(3).setMinWidth(130);
        TablaDatos.getColumnModel().getColumn(4).setMinWidth(130);
        TablaDatos.getColumnModel().getColumn(5).setMinWidth(160);
        TablaDatos.getColumnModel().getColumn(6).setMinWidth(180);
        TablaDatos.getColumnModel().getColumn(7).setMinWidth(150);
        TablaDatos.getColumnModel().getColumn(8).setMinWidth(100);

        PanelDatos.setBounds(60, 480, 1000, 200);
        TablaDatos.addMouseListener(this);
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(dataModel);
        TablaDatos.setRowSorter(elQueOrdena);
        TablaDatos.getTableHeader().setReorderingAllowed(false);
        TablaDatos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        PanelDatos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        btnNuevo.setBounds(130, 440, 100, 25);
        btnGuardar.setBounds(230, 440, 100, 25);
        btnActualizar.setBounds(330, 440, 100, 25);
        btnBuscar.setBounds(430, 440, 100, 25);
        btnEliminar.setBounds(530, 440, 100, 25);
        btnSalir.setBounds(520, 440, 100, 25);

        btnNuevo.setVisible(true);
        btnEliminar.setVisible(false);
        btnGuardar.setVisible(true);
        btnActualizar.setVisible(true);
        btnBuscar.setVisible(true);
        btnSalir.setVisible(false);

        this.getContentPane().add(btnNuevo);
        this.getContentPane().add(btnEliminar);
        this.getContentPane().add(btnGuardar);
        this.getContentPane().add(btnActualizar);
        this.getContentPane().add(btnBuscar);
        this.getContentPane().add(btnSalir);

        //campos lbl panel informacion personal
        this.getContentPane().add(lblcodigo);
        this.getContentPane().add(lblnombres);
        this.getContentPane().add(lbltipoid);
        this.getContentPane().add(cbotipoid);
        this.getContentPane().add(lblapellidos);
        this.getContentPane().add(lbltelefono1);
        this.getContentPane().add(lbltelefono2);
        this.getContentPane().add(lblcelular);
        this.getContentPane().add(lblcorreo);

        //campos txt panel informacion personal
        this.getContentPane().add(txtcodigo);
        this.getContentPane().add(txtnombres);
        this.getContentPane().add(txtapellido);
        this.getContentPane().add(txttelefono1);
        this.getContentPane().add(txttelefono2);
        this.getContentPane().add(txtcelular);
        this.getContentPane().add(txtcorreo);

        //campos lbl panel Perfil
        this.getContentPane().add(lblusuario);
        this.getContentPane().add(lblcontraseña);

        this.getContentPane().add(lblasignar);
        this.getContentPane().add(lblestado);

        //campos txt panel Perfil
        this.getContentPane().add(txtusuario);
        this.getContentPane().add(txtcontraseña);

        this.getContentPane().add(cboasignar);
        this.getContentPane().add(cboestado);
        this.getContentPane().add(lblpregunta);
        this.getContentPane().add(cbopregunta);

        this.getContentPane().add(lblrespuesta);
        this.getContentPane().add(txtrespuesta);

        PanelGeneral1.setVisible(true);
        this.getContentPane().add(PanelGeneral1);

        Panelpersonal.setVisible(true);
        this.getContentPane().add(Panelpersonal);

        Panelperfil.setVisible(true);
        this.getContentPane().add(Panelperfil);

        PanelGeneral1.add(Panelpersonal);
        PanelGeneral1.add(Panelperfil);
        PanelGeneral1.add(panelPaginador);
        PanelGeneral1.add(PanelDatos);
        PanelGeneral1.add(fondo);

        Panelpersonal.add(lblcodigo);
        Panelpersonal.add(lblnombres);
        Panelpersonal.add(cbotipoid);
        Panelpersonal.add(lblapellidos);
        Panelpersonal.add(lbltelefono1);
        Panelpersonal.add(lbltelefono2);
        Panelpersonal.add(lblcelular);
        Panelpersonal.add(lblcorreo);
        Panelpersonal.add(txtcodigo);
        Panelpersonal.add(txtnombres);
        Panelpersonal.add(lbltipoid);
        Panelpersonal.add(txtapellido);
        Panelpersonal.add(txttelefono1);
        Panelpersonal.add(txttelefono2);
        Panelpersonal.add(txtcelular);
        Panelpersonal.add(txtcorreo);

        Panelperfil.add(lblusuario);
        Panelperfil.add(lblcontraseña);
        Panelperfil.add(cboasignar);
        Panelperfil.add(lblasignar);
        Panelperfil.add(lblestado);
        Panelperfil.add(cboestado);
        Panelperfil.add(txtusuario);
        Panelperfil.add(txtcontraseña);
        Panelperfil.add(cbopregunta);
        Panelperfil.add(lblpregunta);
        Panelperfil.add(lblrespuesta);
        Panelperfil.add(txtrespuesta);

        btnGuardar.addActionListener(this);
        btnNuevo.addActionListener(this);

        btnActualizar.addActionListener(this);
        btnBuscar.addActionListener(this);
        btnEliminar.addActionListener(this);

        btnSalir.addActionListener(this);

        panelPaginador.getBtnBack().addActionListener(this);
        panelPaginador.getBtnBackward().addActionListener(this);
        panelPaginador.getBtnNext().addActionListener(this);
        panelPaginador.getBtnForward().addActionListener(this);

        pageData.setFirstQuery(true);
        ocultarColumna("Objeto");
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

        if (e.getSource() == btnNuevo) {

            btnGuardar.setEnabled(true);
            txtcodigo.setEditable(true);
            txtcodigo.setText("");
            cbotipoid.setSelectedItem("");
            txtnombres.setText("");
            txttelefono1.setText("");
            txtcelular.setText("");
            txtapellido.setText("");
            txttelefono2.setText("");
            txtcorreo.setText("");
            txtusuario.setText("");
            txtcontraseña.setText("");
            cbopregunta.setSelectedItem("");
            txtrespuesta.setText("");
            cboasignar.setSelectedIndex(0);
            cboestado.setSelectedIndex(0);
            panelPaginador.enablePageButtons();
            vaciar();
            llenarDatos(pageData);
            repaint();


        }

        if (e.getSource() == btnGuardar) {
            ventana.bloquear(this);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    String Codigo, Tipo, Nombres, Telefono1, Celular, Apellidos, Telefono2, Correo, Usuario, Contrasena, Pregunta, Respuesta;
                    ComboItem perfil;
                    Long Estado;

                    Codigo = txtcodigo.getText().trim();
                    Tipo = String.valueOf(cbotipoid.getSelectedItem());
                    Nombres = txtnombres.getText().trim();
                    Telefono1 = txttelefono1.getText().trim();
                    Celular = txtcelular.getText().trim();
                    Apellidos = txtapellido.getText().trim();
                    Telefono2 = txttelefono2.getText().trim();
                    Correo = txtcorreo.getText().trim();
                    Usuario = txtusuario.getText().trim();
                    Contrasena = txtcontraseña.getText().trim();
                    perfil = ((ComboItem) cboasignar.getSelectedItem());
                    Pregunta = String.valueOf(cbopregunta.getSelectedItem());
                    Respuesta = txtrespuesta.getText().trim();
                    Estado = Estados.getByDescription(cboestado.getSelectedItem() + "").getCode();

                    boolean valido = true;
                    if (Codigo.equals("")) {
                        valido = false;
                    }
                    if (Tipo.equals("")) {
                        valido = false;
                    }
                    if (Nombres.equals("")) {
                        valido = false;
                    }

                    if (Telefono1.equals("")) {
                        valido = false;
                    }

                    if (Celular.equals("")) {
                        valido = false;
                    }
                    if (Apellidos.equals("")) {
                        valido = false;
                    }
                    if (Correo.equals("")) {
                        valido = false;
                    }
                    if (Usuario.equals("")) {
                        valido = false;
                    }
                    if (Contrasena.equals("")) {
                        valido = false;
                    }
                    if (Pregunta.equals("")) {
                        valido = false;
                    }
                    if (Respuesta.equals("")) {
                        valido = false;
                    }
                    if (((ComboItem) perfil).getId() == -1) {
                        valido = false;
                    }

                    if (valido == true) {
                        DtoUsuario usuario = new DtoUsuario();
                        usuario.setCodigo(Codigo);
                        usuario.setApellidos(Apellidos);
                        usuario.setCelular(Celular);
                        usuario.setContrasena(Contrasena);
                        usuario.setCorreo(Correo);
                        usuario.setIdPerfil(perfil.getId());
                        usuario.setNombres(Nombres);
                        usuario.setPregunta(Pregunta);
                        usuario.setRespuesta(Respuesta);
                        usuario.setTelefono1(Telefono1);
                        usuario.setTelefono2(Telefono2);
                        usuario.setTipo(TipoDocumento.getByNombre(Tipo).getCode());
                        usuario.setUsuario(Usuario);
                        usuario.setEstado(Estado);

                        DtoPerfil perfilUsuario = new DtoPerfil();
                        perfilUsuario.setCodperfil(perfil.getId());
                        usuario.setPerfil(perfilUsuario);

                        boolean result = cmdUser.createUsers(usuario);
                        if (result == false) {
                            JOptionPane.showMessageDialog(null, " No fue posible ingresar el nuevo usuario.", "METROFARM", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, " Usuario creado con éxito");
                            panelPaginador.enablePageButtons();
                            pageData.setNumRows(0);
                            vaciar();
                            ventana.desbloquear();
                            llenarDatos(pageData);
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

        if (e.getSource() == btnBuscar) {
            ventana.bloquear(this);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    String Codigo;
                    Codigo = txtcodigo.getText().trim();

                    if (Codigo.equals("")) {
                        JOptionPane.showMessageDialog(null, "Recuerda que debes ingresar el codigo del usuario a buscar", "METROFARM", JOptionPane.ERROR_MESSAGE);
                    } else {

                        DtoUsuario usuario = new DtoUsuario();
                        usuario.setCodigo(Codigo);

                        ObjectResponse response = cmdUser.findUsers(usuario, null);
                        ArrayList<DtoUsuario> userfind = (ArrayList<DtoUsuario>) response.getRecords();
                        if (userfind==null || userfind.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No existe un usuario registrado con este código", "METROFARM", JOptionPane.ERROR_MESSAGE);
                        } else {
                            btnGuardar.setEnabled(false);
                            btnActualizar.setEnabled(true);
                            txtcodigo.setText(userfind.get(0).getCodigo());
                            txtcodigo.setEditable(false);
                            cbotipoid.setSelectedItem(userfind.get(0).getTipo());
                            txtnombres.setText(userfind.get(0).getNombres());
                            txttelefono1.setText(userfind.get(0).getTelefono1());
                            txtcelular.setText(userfind.get(0).getCelular());
                            txtapellido.setText(userfind.get(0).getApellidos());
                            txttelefono2.setText(userfind.get(0).getTelefono2());
                            txtcorreo.setText(userfind.get(0).getCorreo());
                            txtusuario.setText(userfind.get(0).getUsuario());
                            txtcontraseña.setText(userfind.get(0).getContrasena());
                            cboasignar.setSelectedIndex(getSelectCombo(new ComboItem(userfind.get(0).getPerfil().getCodperfil(), userfind.get(0).getPerfil().getPerfil())));
                            cbopregunta.setSelectedItem(userfind.get(0).getPregunta());
                            txtrespuesta.setText(userfind.get(0).getRespuesta());
                            cboestado.setSelectedItem(Estados.getByCode(userfind.get(0).getEstado()).getDescription());
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
                    String Codigo, Tipo, Nombres, Telefono1, Celular, Apellidos, Telefono2, Correo, Usuario, Contrasena, Pregunta, Respuesta;
                    ComboItem perfil;
                    Long Estado;

                    Codigo = txtcodigo.getText().trim();
                    Tipo = String.valueOf(cbotipoid.getSelectedItem());
                    Nombres = txtnombres.getText().trim();
                    Telefono1 = txttelefono1.getText().trim();
                    Celular = txtcelular.getText().trim();
                    Apellidos = txtapellido.getText().trim();
                    Telefono2 = txttelefono2.getText().trim();
                    Correo = txtcorreo.getText().trim();
                    Usuario = txtusuario.getText().trim();
                    Contrasena = txtcontraseña.getText().trim();
                    perfil = ((ComboItem) cboasignar.getSelectedItem());
                    Pregunta = String.valueOf(cbopregunta.getSelectedItem());
                    Respuesta = txtrespuesta.getText().trim();
                    Estado = Estados.getByDescription(cboestado.getSelectedItem() + "").getCode();

                    boolean valido = true;
                    if (Codigo.equals("")) {
                        valido = false;
                    }
                    if (Tipo.equals("")) {
                        valido = false;
                    }
                    if (Nombres.equals("")) {
                        valido = false;
                    }

                    if (Telefono1.equals("")) {
                        valido = false;
                    }

                    if (Celular.equals("")) {
                        valido = false;
                    }
                    if (Apellidos.equals("")) {
                        valido = false;
                    }
                    if (Correo.equals("")) {
                        valido = false;
                    }
                    if (Usuario.equals("")) {
                        valido = false;
                    }
                    if (Contrasena.equals("")) {
                        valido = false;
                    }
                    if (Pregunta.equals("")) {
                        valido = false;
                    }
                    if (Respuesta.equals("")) {
                        valido = false;
                    }
                    if (perfil.getId() == -1) {
                        valido = false;
                    }

                    if (valido == true) {
                        DtoUsuario usuario = new DtoUsuario();
                        usuario.setCodigo(Codigo);
                        usuario.setApellidos(Apellidos);
                        usuario.setCelular(Celular);
                        usuario.setContrasena(Contrasena);
                        usuario.setCorreo(Correo);
                        usuario.setIdPerfil(perfil.getId());
                        usuario.setNombres(Nombres);
                        usuario.setPregunta(Pregunta);
                        usuario.setRespuesta(Respuesta);
                        usuario.setTelefono1(Telefono1);
                        usuario.setTelefono2(Telefono2);
                        usuario.setTipo(TipoDocumento.getByNombre(Tipo).getCode());
                        usuario.setUsuario(Usuario);
                        usuario.setEstado(Estado);

                        DtoPerfil perfilUsuario = new DtoPerfil();
                        perfilUsuario.setCodperfil(perfil.getId());
                        usuario.setPerfil(perfilUsuario);

                        boolean result = cmdUser.updateUser(usuario);
                        if (result == false) {
                            JOptionPane.showMessageDialog(null, " No fue posible actualizar la informacion del usuario.", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, " Usuario actualizado", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                            panelPaginador.enablePageButtons();
                            vaciar();
                            ventana.desbloquear();
                            llenarDatos(pageData);
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

    public void llenarDatos(PageData page) {

        ObjectResponse response = cmdUser.findUsers(new DtoUsuario(), pageData);
        ArrayList<DtoUsuario> users = (ArrayList<DtoUsuario>) response.getRecords();
        pageData = response.getpageResponse();
        setDataModel(getUsuarios(users, getDataModel()));

    }

    public DefaultTableModel getUsuarios(ArrayList<DtoUsuario> users, DefaultTableModel model) {

        for (DtoUsuario c : users) {
            Object nuevo[] = {c.getCodigo(), TipoDocumento.getByCode(c.getTipo()).getNombre(), c.getNombres(), c.getApellidos(), c.getTelefono1(), c.getCelular(), c.getCorreo(), c.getPerfil().getPerfil(), c};
            model.addRow(nuevo);

        }
        return model;

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
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == TablaDatos) {
            int fila = 0;
            try {
                fila = TablaDatos.getSelectedRow();
                TablaDatos.setSelectionBackground(new Color(Integer.parseInt("015289", 16)));
                if (fila > -1) {
                    try {
                        DtoUsuario usuario = (DtoUsuario) TablaDatos.getValueAt(fila, 8);
                        btnGuardar.setEnabled(false);
                        txtcodigo.setText(usuario.getCodigo());
                        txtcodigo.setEditable(false);
                        cbotipoid.setSelectedItem(TipoDocumento.getByCode(usuario.getTipo()).getNombre());
                        txtnombres.setText(usuario.getNombres());
                        txttelefono1.setText(usuario.getTelefono1());
                        txtcelular.setText(usuario.getCelular());
                        txtapellido.setText(usuario.getApellidos());
                        txttelefono2.setText(usuario.getTelefono2());
                        txtcorreo.setText(usuario.getCorreo());
                        txtusuario.setText(usuario.getUsuario());
                        txtcontraseña.setText(usuario.getContrasena());
                        cboasignar.setSelectedIndex(getSelectCombo(new ComboItem(usuario.getPerfil().getCodperfil(), usuario.getPerfil().getPerfil())));
                        cbopregunta.setSelectedItem(usuario.getPregunta());
                        txtrespuesta.setText(usuario.getRespuesta());
                        cboestado.setSelectedItem(Estados.getByCode(usuario.getEstado()).getDescription());
                        btnActualizar.setEnabled(true);
                    } catch (Exception exe) {
                        JOptionPane.showMessageDialog(null, "Hubo un error :" + exe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException | HeadlessException ex) {
                JOptionPane.showMessageDialog(null, "Hubo un error :" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void traer_combo() {
        try {
            ObjectResponse response= cmdProfile.findProfiles(new DtoPerfil(),null);
            ArrayList<DtoPerfil> arrayPerfil=(ArrayList<DtoPerfil>)response.getRecords();
            if(arrayPerfil!=null){
            for (int i = 0; i < arrayPerfil.size(); i++) {
                comboModel.add(new ComboItem(arrayPerfil.get(i).getCodperfil(), arrayPerfil.get(i).getPerfil()));
            }
            cboasignar.setRenderer(new ItemRenderer());
            cboasignar.setSelectedIndex(getSelectCombo(new ComboItem(usuarioLogeado.getPerfil().getCodperfil(), usuarioLogeado.getPerfil().getPerfil())));
            repaint();
            }else{
                JOptionPane.showMessageDialog(null, "No existen perfiles creados","Metrofarm",JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Código 999", JOptionPane.ERROR_MESSAGE);
        }

    }

    public int getSelectCombo(ComboItem combo) {
        int selected = 0;
        int size = cboasignar.getItemCount();
        for (int i = 0; i < size; i++) {
            if (((ComboItem) cboasignar.getItemAt(i)).getId() == combo.getId()) {
                return i;
            }
        }
        return selected;
    }

    public void ocultarColumna(String column) {
        TableColumn tc = TablaDatos.getColumn(column); //Obtienes la columna
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
