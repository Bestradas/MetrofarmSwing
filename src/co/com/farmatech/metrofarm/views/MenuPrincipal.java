/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.views;

import co.com.farmatech.metrofarm.utils.Formu_Fondo;
import co.com.farmatech.metrofarm.utils.buttons.BotonesMenu;
import co.com.farmatech.metrofarm.views.notification.ThreadNotification;
import co.jankins.psf.common.global.GlobalUtils;
import com.co.farmatech.metrofarm.dto.DtoUsuario;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

/**
 *
 * @author bestrada
 */
public class MenuPrincipal extends JFrame implements ActionListener, MouseListener {

    public JFrame Ventana;
    Formu_Fondo PanelGeneral;
    JLabel LblUsuario, LblContraseña, LblImagen, LblTitulo, Lbltexturas, Lblimag, Lblpuntos, lblPerfil, lblNotificacion, lblActualizar, lblManmes, lblMantVencido, lblCalibMes, lblCalibVencidos, lblCalifMes, lblCalifVencidas, lblMantGenMes, lblMantGenVenc;
    public JLabel lblActulizando;
    JTextField TxtUsuario, TxtContraseña;
    JButton BtnIngresar, BtnRecordar;
    ImageIcon IcoUsuarios, IcoUsuariosAnim, IcoEquipos, IcoEquiposAnim, IcoValidacion, IcoValidacionAnim, IcoCalibracion, IcoCalibracionAnim, IcoInformes, IcoInformesAnim, IcoAcercade, IcoAcercadeAnim, Icomenu, IcoMantenimiento, IcoMantenimientoAnim, Icomantgen, IcoMangenanim;
    Image IcoCabecera;
    Font TLetra = new Font("Arial", Font.PLAIN, 12);
    Font Letra = new Font("Arial", Font.PLAIN, 20);
    Font NomLetra = new Font("Arial", Font.PLAIN, 15);
    String usuario, Contraseña;
    private Container c;//Contenedor
    //////////////////Salir//////////////////////////////////////
    JLabel LblApagando, fondo, Lblcargando;
    Timer contador;
    String ruta = "co/com/farmatech/metrofarm/resources/imagenes/";
    String rutacancion = "/Cancion/";
    String Perfil;
    BotonesMenu BotonUsuarios, BotonEquipos, BotonMantenimiento, BotonValidacion, BotonCalibracion, BotonInformes, BotonAyuda, BotonMantgen;
    private JPanel panelPerfil;
    private JMenuBar Menu;
    private JMenu menu1, menu2;
    private JMenuItem m11, m21, m22;
    boolean sw1, sw2, sw3, sw4, sw5, sw6, sw7, sw8, sw9;
    DtoUsuario usuarioLogeado;
    ImageIcon imag2 = new ImageIcon(getClass().getClassLoader().getResource(ruta + "actualizando.GIF"));

    public MenuPrincipal() {

        this.usuarioLogeado = GlobalUtils.getContext().getLoginUser();
        //Se define la barra de menu

        lblActulizando = new JLabel(imag2);
        lblActulizando.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblActulizando.setBounds(0, 25, 16, 16);
        lblActulizando.setIcon(imag2);
        imag2.setImageObserver(lblActulizando);
        lblActulizando.setVisible(false);

        lblActualizar = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(ruta + "Refresh.png")));
        lblActualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblActualizar.setToolTipText("Refrescar Notificaciónes");
        lblActualizar.setFont(Letra);
        lblActualizar.setForeground(new Color(Integer.parseInt("EF2F24", 16)));
        lblActualizar.setBounds(0, 25, 16, 40);

        lblPerfil = new JLabel("Usuario");
        lblPerfil.setText(usuarioLogeado.getNombres() + " " + usuarioLogeado.getPerfil().getPerfil());
        lblPerfil.setForeground(Color.white);
        lblPerfil.setFont(NomLetra);
        lblPerfil.setBounds(0, 25, 100, 20);

        lblNotificacion = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(ruta + "nuevoequipo.png")));
        lblNotificacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblNotificacion.setFont(Letra);
        lblNotificacion.setForeground(new Color(Integer.parseInt("EF2F24", 16)));
        lblNotificacion.setBounds(0, 25, 16, 16);

        //Permiso notificacion equipo
        lblNotificacion.setVisible(usuarioLogeado.getPerfil().isNotificaEquipos());


        lblManmes = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(ruta + "manmes.png")));
        lblManmes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblManmes.setFont(Letra);

        lblManmes.setForeground(new Color(Integer.parseInt("EF2F24", 16)));
        lblManmes.setBounds(0, 25, 16, 16);

        //Permiso mantenimientos del mes
        lblManmes.setVisible(usuarioLogeado.getPerfil().isNotificaciones_mant_mes());

        lblMantVencido = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(ruta + "alerta.png")));
        lblMantVencido.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblMantVencido.setFont(Letra);
        lblMantVencido.setForeground(new Color(Integer.parseInt("EF2F24", 16)));
        lblMantVencido.setBounds(0, 25, 16, 16);

        //Permiso mantenimientos vencidos del mes
        lblMantVencido.setVisible(usuarioLogeado.getPerfil().isNotificaciones_man_ven_mes());

        lblMantGenMes = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(ruta + "mantgen.png")));
        lblMantGenMes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblMantGenMes.setFont(Letra);
        lblMantGenMes.setForeground(new Color(Integer.parseInt("EF2F24", 16)));
        lblMantGenMes.setBounds(0, 25, 16, 16);

        //Permiso mantenimientos generales del mes
        lblMantGenMes.setVisible(usuarioLogeado.getPerfil().isNotificacionesMantgenmes());


        lblMantGenVenc = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(ruta + "mantgenvenc.png")));
        lblMantGenVenc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblMantGenVenc.setFont(Letra);
        lblMantGenVenc.setForeground(new Color(Integer.parseInt("EF2F24", 16)));
        lblMantGenVenc.setBounds(0, 25, 16, 16);

        //Permiso mantenimientos generales vencidos    
        lblMantGenVenc.setVisible(usuarioLogeado.getPerfil().isNotificacionesMantgenvenc());

        lblCalibMes = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(ruta + "noticalib.png")));
        lblCalibMes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblCalibMes.setFont(Letra);

        lblCalibMes.setForeground(new Color(Integer.parseInt("EF2F24", 16)));
        lblCalibMes.setBounds(0, 25, 16, 16);

        //Permiso calibraciones del mes
        lblCalibMes.setVisible(usuarioLogeado.getPerfil().isNotificaciones_calib_mes());


        lblCalibVencidos = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(ruta + "alertacal.png")));

        lblCalibVencidos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblCalibVencidos.setFont(Letra);

        lblCalibVencidos.setForeground(new Color(Integer.parseInt("EF2F24", 16)));
        lblCalibVencidos.setBounds(0, 25, 16, 16);

        //Permiso calibraciones vencidas
        lblCalibVencidos.setVisible(usuarioLogeado.getPerfil().isNotificaciones_calib_ven());


        lblCalifMes = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(ruta + "calif.png")));

        lblCalifMes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblCalifMes.setFont(Letra);

        lblCalifMes.setForeground(new Color(Integer.parseInt("EF2F24", 16)));
        lblCalifMes.setBounds(0, 25, 16, 16);

        //Permiso calificaciones del mes
        lblCalifMes.setVisible(usuarioLogeado.getPerfil().isNotificaciones_calif_mes());


        lblCalifVencidas = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(ruta + "calvenc.png")));

        lblCalifVencidas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblCalifVencidas.setFont(Letra);

        lblCalifVencidas.setForeground(new Color(Integer.parseInt("EF2F24", 16)));
        lblCalifVencidas.setBounds(0, 25, 16, 16);

        lblCalifVencidas.setVisible(usuarioLogeado.getPerfil().isNotificaciones_calif_ven_mes());


        Menu = new JMenuBar();
        menu1 = new JMenu("Archivo");
        Menu.add(menu1);

        m11 = new JMenuItem("Cerrar sesión");
        m11.addActionListener(this);
        m11.setIcon(new ImageIcon(getClass().getClassLoader().getResource(ruta + "cerrar.png")));
        menu1.add(m11);

        menu2 = new JMenu("Backup");
        Menu.add(menu2);

        m21 = new JMenuItem("Generar backup");
        m21.addActionListener(this);
        m21.setIcon(new ImageIcon(getClass().getClassLoader().getResource(ruta + "generar.gif")));
        menu2.add(m21);

        m22 = new JMenuItem("Restaurar backup");
        m22.addActionListener(this);
        m22.setIcon(new ImageIcon(getClass().getClassLoader().getResource(ruta + "importar.png")));
        menu2.add(m22);

        //Imagenes de los botones	
        Icomenu = new ImageIcon(getClass().getClassLoader().getResource(ruta + "fondomenufarma.png"));

        IcoUsuarios = new ImageIcon(getClass().getClassLoader().getResource(ruta + "usuarioU.png"));
        IcoUsuariosAnim = new ImageIcon(getClass().getClassLoader().getResource(ruta + "usuario.png"));

        IcoEquipos = new ImageIcon(getClass().getClassLoader().getResource(ruta + "equiposU.png"));
        IcoEquiposAnim = new ImageIcon(getClass().getClassLoader().getResource(ruta + "Equipos.png"));

        IcoMantenimiento = new ImageIcon(getClass().getClassLoader().getResource(ruta + "mantenimientoU.png"));
        IcoMantenimientoAnim = new ImageIcon(getClass().getClassLoader().getResource(ruta + "calibracion.png"));

        IcoValidacion = new ImageIcon(getClass().getClassLoader().getResource(ruta + "validacionU.png"));
        IcoValidacionAnim = new ImageIcon(getClass().getClassLoader().getResource(ruta + "validacion.png"));

        IcoCalibracion = new ImageIcon(getClass().getClassLoader().getResource(ruta + "calibracionU.png"));
        IcoCalibracionAnim = new ImageIcon(getClass().getClassLoader().getResource(ruta + "calibration.png"));

        IcoInformes = new ImageIcon(getClass().getClassLoader().getResource(ruta + "InformeU.png"));
        IcoInformesAnim = new ImageIcon(getClass().getClassLoader().getResource(ruta + "pdf.png"));

        IcoAcercade = new ImageIcon(getClass().getClassLoader().getResource(ruta + "AyudaU.png"));
        IcoAcercadeAnim = new ImageIcon(getClass().getClassLoader().getResource(ruta + "ayuda.png"));

        Icomantgen = new ImageIcon(getClass().getClassLoader().getResource(ruta + "mantenimientogU.png"));
        IcoMangenanim = new ImageIcon(getClass().getClassLoader().getResource(ruta + "mant_general.png"));

        //////////////////////////Boton Usuarios///////////////////////////////////////////////
        BotonUsuarios = new BotonesMenu(IcoUsuarios, IcoUsuariosAnim);
        BotonUsuarios.setBounds(100, 60, 200, 200);
        BotonUsuarios.setVisible(true);
        BotonUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //////////////////////////Boton Equipos///////////////////////////////////////////////
        BotonEquipos = new BotonesMenu(IcoEquipos, IcoEquiposAnim);
        BotonEquipos.setBounds(400, 60, 200, 200);
        BotonEquipos.setVisible(true);
        BotonEquipos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //////////////////////////Boton Mantenimiento General///////////////////////////////////////////////
        BotonMantgen = new BotonesMenu(Icomantgen, IcoMangenanim);
        BotonMantgen.setBounds(1000, 60, 200, 200);
        BotonMantgen.setVisible(true);
        BotonMantgen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //////////////////////////Boton Mantenimiento///////////////////////////////////////////////
        BotonMantenimiento = new BotonesMenu(IcoMantenimiento, IcoMantenimientoAnim);
        BotonMantenimiento.setBounds(700, 60, 200, 200);
        BotonMantenimiento.setVisible(true);
        BotonMantenimiento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //////////////////////////Boton Validacion///////////////////////////////////////////////
        BotonValidacion = new BotonesMenu(IcoValidacion, IcoValidacionAnim);
        BotonValidacion.setBounds(100, 360, 200, 200);
        BotonValidacion.setVisible(true);
        BotonValidacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //////////////////////////Boton Calibracion///////////////////////////////////////////////
        BotonCalibracion = new BotonesMenu(IcoCalibracion, IcoCalibracionAnim);
        BotonCalibracion.setBounds(400, 360, 200, 200);
        BotonCalibracion.setVisible(true);
        BotonCalibracion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //////////////////////////Boton Informes///////////////////////////////////////////////
        BotonInformes = new BotonesMenu(IcoInformes, IcoInformesAnim);
        BotonInformes.setBounds(700, 360, 200, 200);
        BotonInformes.setVisible(true);
        BotonInformes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //////////////////////////Boton Ayuda///////////////////////////////////////////////
        BotonAyuda = new BotonesMenu(IcoAcercade, IcoAcercadeAnim);
        BotonAyuda.setBounds(1000, 360, 200, 200);
        BotonAyuda.setVisible(true);
        BotonAyuda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        panelPerfil = new JPanel();
        panelPerfil.setBounds(700, 0, 666, 30);
        panelPerfil.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panelPerfil.setBackground(new Color(Integer.parseInt("3979A4", 16)));
        panelPerfil.add(lblActulizando);
        panelPerfil.add(lblActualizar);
        panelPerfil.add(lblNotificacion);
        panelPerfil.add(lblManmes);
        panelPerfil.add(lblMantVencido);
        panelPerfil.add(lblMantGenMes);
        panelPerfil.add(lblMantGenVenc);
        panelPerfil.add(lblCalibMes);
        panelPerfil.add(lblCalibVencidos);
        panelPerfil.add(lblCalifMes);
        panelPerfil.add(lblCalifVencidas);

        panelPerfil.add(lblPerfil);

        PanelGeneral = new Formu_Fondo(Icomenu);
        PanelGeneral.setLayout(null);
        PanelGeneral.reshape(0, 0, 1366, 768);
        PanelGeneral.setBorder(BorderFactory.createTitledBorder(""));
        PanelGeneral.add(BotonUsuarios);
        PanelGeneral.add(BotonEquipos);
        PanelGeneral.add(BotonMantenimiento);
        PanelGeneral.add(BotonValidacion);
        PanelGeneral.add(BotonCalibracion);
        PanelGeneral.add(BotonInformes);
        PanelGeneral.add(BotonAyuda);
        PanelGeneral.add(panelPerfil);
        PanelGeneral.add(BotonMantgen);

        Ventana = new JFrame("METROFARM -V1  Farmatech S.A");
        Ventana.setLayout(null);
        Ventana.add(PanelGeneral);
        Ventana.setSize(1366, 768);
        Ventana.setLocationRelativeTo(null);
        Ventana.setIconImage(IcoCabecera);
        Ventana.setBackground(Color.pink);
        Ventana.setResizable(false);
        Ventana.show();
        Ventana.setVisible(true);
        Ventana.setJMenuBar(Menu);
        Ventana.setIconImage(new ImageIcon(getClass().getClassLoader().getResource(ruta + "FARMA.png")).getImage());
        Ventana.setDefaultCloseOperation(0);

        BotonUsuarios.addActionListener(this);
        BotonEquipos.addActionListener(this);
        BotonMantenimiento.addActionListener(this);
        BotonValidacion.addActionListener(this);
        BotonCalibracion.addActionListener(this);
        BotonInformes.addActionListener(this);
        BotonMantgen.addActionListener(this);
        BotonAyuda.addActionListener(this);
        lblActualizar.addMouseListener(this);
        lblNotificacion.addMouseListener(this);
        lblManmes.addMouseListener(this);
        lblMantVencido.addMouseListener(this);
        lblCalibMes.addMouseListener(this);
        lblCalibVencidos.addMouseListener(this);
        lblCalifMes.addMouseListener(this);
        lblCalifVencidas.addMouseListener(this);
        lblMantGenMes.addMouseListener(this);
        lblPerfil.addMouseListener(this);
        lblMantGenVenc.addMouseListener(this);
        actualizarNotificaciones();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == BotonUsuarios) {
            MenuUsuarios menudeUsuarios = new MenuUsuarios();
        }

        if (e.getSource() == BotonEquipos) {
            Equipos Equipo = new Equipos(this);

            Equipo.btnGuardar.setEnabled(GlobalUtils.getContext().getLoginUser().getPerfil().isGuardarEquipo());
            Equipo.btnNuevo.setEnabled(GlobalUtils.getContext().getLoginUser().getPerfil().isGuardarEquipo());

            Equipo.btnBuscarInv.setEnabled(GlobalUtils.getContext().getLoginUser().getPerfil().isBuscarEquipo());
            Equipo.btnBuscaridMetr.setEnabled(GlobalUtils.getContext().getLoginUser().getPerfil().isBuscarEquipo());
            Equipo.btnBuscarSerie.setEnabled(GlobalUtils.getContext().getLoginUser().getPerfil().isBuscarEquipo());
            Equipo.TablaDatos.setEnabled(GlobalUtils.getContext().getLoginUser().getPerfil().isBuscarEquipo());

            Equipo.btnActualizar.setEnabled(GlobalUtils.getContext().getLoginUser().getPerfil().isModificarEquipo());
            Equipo.TablaDatos.setVisible(GlobalUtils.getContext().getLoginUser().getPerfil().isModificarEquipo());

        }

        if (e.getSource() == BotonMantenimiento) {
            MantenimientoInstrumento mantenimiento = new MantenimientoInstrumento(this);


            mantenimiento.btnGuardar.setVisible(usuarioLogeado.getPerfil().isGuardarMantenimiento());



            mantenimiento.btnBuscar.setVisible(usuarioLogeado.getPerfil().isBuscarMantenimiento());
            mantenimiento.btnBuscarInv.setVisible(usuarioLogeado.getPerfil().isBuscarMantenimiento());
            mantenimiento.btnBuscaridMetr.setVisible(usuarioLogeado.getPerfil().isBuscarMantenimiento());
            mantenimiento.btnBuscarSerie.setVisible(usuarioLogeado.getPerfil().isBuscarMantenimiento());




            mantenimiento.btnActualizar.setVisible(usuarioLogeado.getPerfil().isModificarMantenimiento());
            mantenimiento.TablaDatos.setVisible(usuarioLogeado.getPerfil().isModificarMantenimiento());


        }

        if (e.getSource() == BotonMantgen) {

            MantenimientoEquipo mant = new MantenimientoEquipo(this);

            mant.btnGuardar.setVisible(usuarioLogeado.getPerfil().isGuardarMantGen());

            mant.btnBuscar.setVisible(usuarioLogeado.getPerfil().isBuscarMantGen());
            mant.btnBuscarInv.setVisible(usuarioLogeado.getPerfil().isBuscarMantGen());
            mant.btnBuscaridMetr.setVisible(usuarioLogeado.getPerfil().isBuscarMantGen());
            mant.btnBuscarSerie.setVisible(usuarioLogeado.getPerfil().isBuscarMantGen());


            mant.btnActualizar.setVisible(usuarioLogeado.getPerfil().isModificarMantGen());

        }

        if (e.getSource() == BotonValidacion) {
            Calificacion calificacion = new Calificacion(this);
            calificacion.btnBuscar.setEnabled(GlobalUtils.getContext().getLoginUser().getPerfil().isBuscarCalificacion());
            calificacion.btnBuscarInv.setVisible(GlobalUtils.getContext().getLoginUser().getPerfil().isBuscarCalificacion());
            calificacion.btnBuscaridMetr.setVisible(GlobalUtils.getContext().getLoginUser().getPerfil().isBuscarCalificacion());
            calificacion.btnBuscarSerie.setVisible(GlobalUtils.getContext().getLoginUser().getPerfil().isBuscarCalificacion());
            calificacion.btnActualizar.setEnabled(GlobalUtils.getContext().getLoginUser().getPerfil().isModificarCalificacion());
            calificacion.btnEliminar.setEnabled(GlobalUtils.getContext().getLoginUser().getPerfil().isEliminarCalificacion());


        }

        if (e.getSource() == BotonCalibracion) {
            Calibracion calibracion = new Calibracion(this);

            calibracion.btnGuardar.setEnabled(GlobalUtils.getContext().getLoginUser().getPerfil().isGuardarCalibracion());
            calibracion.btnBuscar.setEnabled(GlobalUtils.getContext().getLoginUser().getPerfil().isBuscarCalibracion());
            calibracion.btnBuscarInv.setVisible(GlobalUtils.getContext().getLoginUser().getPerfil().isBuscarEquipo());
            calibracion.btnBuscaridMetr.setVisible(GlobalUtils.getContext().getLoginUser().getPerfil().isBuscarEquipo());
            calibracion.btnBuscarSerie.setVisible(GlobalUtils.getContext().getLoginUser().getPerfil().isBuscarEquipo());
            calibracion.btnActualizar.setEnabled(GlobalUtils.getContext().getLoginUser().getPerfil().isModificarCalibracion());
            calibracion.btnEliminar.setEnabled(GlobalUtils.getContext().getLoginUser().getPerfil().isEliminarCalibracion());

        }

        if (e.getSource() == BotonInformes) {

            MenuCronogramaIndicador menu = new MenuCronogramaIndicador();

        }

        if (e.getSource() == BotonAyuda) {
//            PruebaAnimacion ayuda = new PruebaAnimacion(Nodo, Permiso);
        }

        if (e.getSource() == m21) {
//            new Exportar().setVisible(true);
        }

        if (e.getSource() == m22) {
//            new Restaurar().setVisible(true);
        }

        if (e.getSource() == m11) {
            int respuesta = JOptionPane.showConfirmDialog(null, "Esta seguro que desea cerrar sesión", "METROFARM", JOptionPane.YES_NO_OPTION);
            switch (respuesta) {
                case JOptionPane.YES_OPTION:
                    Ventana.dispose();
                    GlobalUtils.cleanContext();
                    Ingreso ingreso = new Ingreso();
                    break;
                case JOptionPane.NO_OPTION:
                    break;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == lblNotificacion) {
            Equipos equipos = new Equipos(this);
        }

        if (e.getSource() == lblActualizar) {
            actualizarNotificaciones();
        }


        if (e.getSource() == lblManmes) { // Ventana.dispose(); Mantenimiento
            MantenimientoInstrumento mantenimiento = new MantenimientoInstrumento(this);
            mantenimiento.filtrarMantenimientosMes();
        }

        if (e.getSource() == lblMantVencido) { // Ventana.dispose();
            MantenimientoInstrumento mantenimiento = new MantenimientoInstrumento(this);
            mantenimiento.filtrarMantenimientosVencidos();
        }

        if (e.getSource() == lblMantGenMes) { // Ventana.dispose();
            MantenimientoEquipo mantenimientogenvenc = new MantenimientoEquipo(this);
            mantenimientogenvenc.filtrarMantenimientosMes();

        }

        if (e.getSource() == lblMantGenVenc) { // Ventana.dispose();
            MantenimientoEquipo mantenimientogenvenc = new MantenimientoEquipo(this);
            mantenimientogenvenc.filtrarMantenimientosVencidos();
        }

        if (e.getSource() == lblCalibMes) { // Ventana.dispose(); Calibracion
            Calibracion calibracion = new Calibracion(this);
        }

        if (e.getSource() == lblCalibVencidos) {
            Calibracion calibracion = new Calibracion(this);
        }

        if (e.getSource() == lblCalifMes) { // Ventana.dispose();
            Calificacion cal = new Calificacion(this);
            TablaCalificaciones tablaCalificaciones = new TablaCalificaciones(cal, "Tabla Calificaciones del mes METROFARM -v1");
            tablaCalificaciones.llenarDatosMes();
        }

        if (e.getSource() == lblCalifVencidas) { // Ventana.dispose();
            Calificacion cal = new Calificacion(this);
            TablaCalificaciones tablaCalificaciones = new TablaCalificaciones(cal, "Tabla Calificaciones vencidas METROFARM -v1");
            tablaCalificaciones.llenarDatosVencidos();

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
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public boolean verificarnumero(int cont) {
        boolean sw;
        if (cont != 0) {
            sw = true;
        } else {
            sw = false;
        }
        return sw;
    }

    public JLabel getLblNotificacion() {
        return lblNotificacion;
    }

    public JLabel getLblManmes() {
        return lblManmes;
    }

    public JLabel getLblMantVencido() {
        return lblMantVencido;
    }

    public JLabel getLblCalibMes() {
        return lblCalibMes;
    }

    public JLabel getLblCalibVencidos() {
        return lblCalibVencidos;
    }

    public JLabel getLblCalifMes() {
        return lblCalifMes;
    }

    public JLabel getLblCalifVencidas() {
        return lblCalifVencidas;
    }

    public JLabel getLblMantGenMes() {
        return lblMantGenMes;
    }

    public JLabel getLblMantGenVenc() {
        return lblMantGenVenc;
    }

    public JLabel getLblActulizando() {
        return lblActulizando;
    }

    public JPanel getPanelPerfil() {
        return panelPerfil;
    }

    public JLabel getLblActualizar() {
        return lblActualizar;
    }

    public void actualizarNotificaciones() {
        Thread updateNotification = new Thread(new ThreadNotification(this));
        updateNotification.start();
    }
}
