/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.views;

import co.com.farmatech.metrofarm.cmd.CmdUsuarios;
import co.com.farmatech.metrofarm.utils.Formu_Fondo;
import co.com.farmatech.metrofarm.utils.MP3;
import co.com.farmatech.metrofarm.utils.buttons.BotonesMenu;
import co.jankins.psf.common.exception.JankinsMarshallException;
import co.jankins.psf.common.global.GlobalUtils;
import com.co.farmatech.metrofarm.dto.DtoUsuario;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.Timer;
import ventana.bloqueo.VentanaBloqueo;

public class Ingreso extends JFrame implements KeyListener, ActionListener, WindowListener {

    static JProgressBar progreso;
    static JFrame Ventana;
    JPanel PanelUsuario, PanelOperaciones;
    JLabel LblUsuario, LblContraseña;
    JTextField TxtUsuario, TxtContraseña;
    static JButton BtnIngresar, BtnRecordar;
    BotonesMenu BtnSalir;
    ImageIcon Icotexturas, Icosalir, Icosalir1;
    Image IcoCabecera;
    Font TLetra = new Font("Arial", Font.BOLD, 15);
    Font Letra = new Font("Arial", Font.PLAIN, 15);
    private Image Tingreso = Toolkit.getDefaultToolkit().getImage("texturas.jpg");
    private Container c;//Contenedor
    //////////////////Salir//////////////////////////////////////
    JLabel LblApagando, fondo, Lblcargando;
    Timer contador;
    Formu_Fondo PanelGeneral;
    CmdUsuarios cmd = new CmdUsuarios();
    private final VentanaBloqueo ventana = new VentanaBloqueo();

    public Ingreso() {

        super("Inicio de sesión");
        setLayout(null);
        setSize(1366, 768);
        addWindowListener(this);
        setLocationRelativeTo(null);
        setBackground(Color.pink);
        setResizable(false);
        setVisible(true);
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("co/com/farmatech/metrofarm/resources/imagenes/FARMA.png")).getImage());
        setDefaultCloseOperation(0);

        progreso = new JProgressBar(0, 100);
        progreso.setBounds(530, 420, 330, 30);
        progreso.setVisible(false);

        Icotexturas = new ImageIcon(getClass().getClassLoader().getResource("co/com/farmatech/metrofarm/resources/imagenes/Login.png"));
        Icosalir = new ImageIcon(getClass().getClassLoader().getResource("co/com/farmatech/metrofarm/resources/imagenes/Farmatech.png"));
        Icosalir1 = new ImageIcon(getClass().getClassLoader().getResource("co/com/farmatech/metrofarm/resources/imagenes/salir.png"));

        BtnSalir = new BotonesMenu(Icosalir, Icosalir1);
        BtnSalir.reshape(400, 470, 500, 266);
        BtnSalir.addActionListener(this);
        BtnSalir.setForeground(Color.black);
        BtnSalir.setToolTipText("Abandonar el sistema");
        BtnSalir.setMnemonic('I');
        BtnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        LblUsuario = new JLabel("Usuario");
        LblUsuario.reshape(30, 30, 150, 20);
        LblUsuario.setFont(TLetra);
        LblUsuario.setForeground(Color.white);

        LblContraseña = new JLabel("Contraseña");
        LblContraseña.reshape(30, 80, 150, 20);
        LblContraseña.setFont(TLetra);
        LblContraseña.setForeground(Color.white);

        TxtUsuario = new JTextField();
        TxtUsuario.reshape(150, 30, 150, 20);
        TxtUsuario.addKeyListener(this);
        TxtUsuario.setToolTipText("Ingresar el usuario");
        TxtUsuario.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        TxtContraseña = new JPasswordField();
        TxtContraseña.addKeyListener(this);
        TxtContraseña.reshape(150, 80, 150, 20);
        TxtContraseña.setToolTipText("Ingresar la contraseña");
        TxtContraseña.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        BtnIngresar = new JButton("Ingresar");
        BtnIngresar.reshape(100, 30, 120, 25);
        BtnIngresar.addActionListener(this);
        BtnIngresar.setForeground(Color.black);
        BtnIngresar.setToolTipText("Ingresar al sistema");
        BtnIngresar.setMnemonic('I');
        BtnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        BtnRecordar = new JButton("Recordar contraseña");
        BtnRecordar.reshape(610, 280, 160, 20);
        BtnRecordar.addActionListener(this);
        BtnRecordar.setForeground(Color.black);
        BtnRecordar.setMnemonic('R');
        BtnRecordar.setToolTipText("¿Olvido datos?");
        BtnRecordar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        PanelUsuario = new JPanel();
        PanelUsuario.reshape(530, 70, 330, 120);
        PanelUsuario.setBorder(BorderFactory.createTitledBorder("Inicio de sesión"));
        PanelUsuario.setBorder(BorderFactory.createLineBorder(Color.white));
        PanelUsuario.setOpaque(false);
        PanelUsuario.setLayout(null);
        PanelUsuario.add(LblUsuario);
        PanelUsuario.add(LblContraseña);
        PanelUsuario.add(TxtUsuario);
        PanelUsuario.add(TxtContraseña);
        PanelUsuario.setBackground(Color.white);

        PanelOperaciones = new JPanel();
        PanelOperaciones.reshape(530, 200, 330, 100);
        PanelOperaciones.setBorder(BorderFactory.createTitledBorder(""));
        PanelOperaciones.setBorder(BorderFactory.createLineBorder(Color.white));
        PanelOperaciones.setOpaque(false);
        PanelOperaciones.setLayout(null);
        PanelOperaciones.add(BtnIngresar);
        PanelOperaciones.setBackground(Color.white);

        PanelGeneral = new Formu_Fondo(Icotexturas);
        PanelGeneral.setLayout(null);
        PanelGeneral.reshape(0, 0, 1366, 768);
        PanelGeneral.setBorder(BorderFactory.createTitledBorder(""));
        PanelGeneral.add(PanelUsuario);
        PanelGeneral.add(PanelOperaciones);
        PanelGeneral.add(BtnSalir);
        PanelGeneral.add(progreso);

        add(BtnRecordar);
        add(PanelGeneral);
        setIconImage(IcoCabecera);

        this.repaint();

    }

    public void Leer_Usuarios() {
        String usuario = TxtUsuario.getText();
        String contrasena = TxtContraseña.getText();

        DtoUsuario user = new DtoUsuario();
        user.setUsuario(usuario);
        user.setContrasena(contrasena);
        try {
            DtoUsuario userResponse = cmd.authenticate(user);
            if (userResponse != null) {

                GlobalUtils.registerContextInfo(userResponse, userResponse.getPerfil());

                progreso.setVisible(true);
                new Thread(new Hilo()).start();

                progreso.setVisible(false);
                JOptionPane.showMessageDialog(null, "Bievenido(a) " + userResponse.getNombres() + " usted ha ingresado como " + userResponse.getPerfil().getPerfil(), "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                // prueba reproducir musica

                String filename = "bienvenido.mp3";
                // nueva instancia de la clase MP3
                MP3 mp3 = new MP3(filename);
                mp3.play();
                // cuando el tiempo de proceso termina se detiene la reproduccion
                mp3.close();
                // reproducir el mp3 desde el principio
                mp3 = new MP3(filename);
                mp3.play();
                MenuPrincipal menuPrincipal = new MenuPrincipal();
                this.dispose();

            } else {
                JOptionPane.showMessageDialog(null, "El perfil de usuario no existe ", "METROFARM", JOptionPane.ERROR_MESSAGE);
            }
        } catch (JankinsMarshallException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), e.getCode(), JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        if (evento.getSource() == BtnIngresar) {

            progreso.setVisible(true);
            new Thread(new Hilo()).start();

            ventana.bloquear(this);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    if (TxtUsuario.getText().trim().equals("") || TxtContraseña.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "Usuario y contraseña requeridos", "Acceso MetroFarm", JOptionPane.WARNING_MESSAGE);
                        return null;
                    }
                    Leer_Usuarios();

                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();

            progreso.setVisible(false);

        }

        if (evento.getSource() == BtnSalir) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea abandonar el sistema?", "METROFARM", JOptionPane.YES_NO_OPTION);
            switch (respuesta) {
                case JOptionPane.YES_OPTION:
                    System.exit(0);
                    break;
                case JOptionPane.NO_OPTION:
                    break;

            }
        }

        if (evento.getSource() == BtnRecordar) {
            OlvidoDatos olvidodatos = new OlvidoDatos();
        }
    }

    public void LimpiarCampos() {
        TxtUsuario.setText("");
        TxtContraseña.setText("");
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error :" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        Ingreso ingreso = new Ingreso();
        ingreso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static class Hilo implements Runnable {

        @Override
        public void run() {
            for (int x = 0; x < 100; x++) {
                progreso.setValue(x);
                progreso.repaint();

                try {
                    Thread.sleep(10);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Hubo un error :" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}