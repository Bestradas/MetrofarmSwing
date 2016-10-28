/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.views;

import co.com.farmatech.metrofarm.cmd.CmdUsuarios;
import co.com.farmatech.metrofarm.cmd.CmdUtils;
import com.co.farmatech.metrofarm.dto.DtoUsuario;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import ventana.bloqueo.VentanaBloqueo;

/**
 *
 * @author Bryan
 */
public class PreguntaSeguridad implements WindowListener, ActionListener {

    JFrame Ventana;
    JLabel LblImagen, LblPregunta, LblEnunciado;
    JTextField TxtRespuesta;
    JButton BtnConsultar, BtnSalir;
    ImageIcon IcoRecordar, IcoSalir;
    Image IcoCabecera;
    JPanel PanDatos, PanBotones, PanGeneral;
    String password;  //nombre de la base de datos Acces con extension *.mdb o *.accdb
    String dbName;    //direccion de la base de datos
    String bd;        //driver para base de datos Access 2000, 2003, 2007, 2010
    String url;       //Variable de conexion
    Connection conn;   //Variable para generar consultas a la BD
    Statement st;
    String Pregunta;
    String Respuesta;
    DtoUsuario usuario;
    String ruta = "co/com/farmatech/metrofarm/resources/imagenes/";
    CmdUtils cmd;
    private final VentanaBloqueo ventana = new VentanaBloqueo();

    public PreguntaSeguridad(DtoUsuario usuario) {
        cmd = new CmdUtils();
        this.usuario = usuario;
        this.Pregunta = usuario.getPregunta();

        IcoRecordar = new ImageIcon(getClass().getClassLoader().getResource(ruta + "olvido.png"));
        IcoSalir = new ImageIcon(ruta + "salir.Gif");
        IcoCabecera = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(ruta + "FARMA.png"));

        LblPregunta = new JLabel("Pregunta de Seguridad:");
        LblPregunta.reshape(5, 5, 300, 20);
        LblPregunta.setForeground(Color.BLUE);

        LblEnunciado = new JLabel(Pregunta + " :");
        LblEnunciado.reshape(35, 30, 300, 20);

        LblImagen = new JLabel(IcoRecordar);
        LblImagen.reshape(20, 20, 100, 150);

        TxtRespuesta = new JTextField();
        TxtRespuesta.reshape(50, 55, 160, 20);

        BtnConsultar = new JButton("Enviar contraseña");
        BtnConsultar.setLayout(null);
        BtnConsultar.reshape(5, 100, 110, 15);
        BtnConsultar.setMnemonic('E');
        BtnConsultar.setToolTipText("Enviar datos al correo");
        BtnConsultar.addActionListener(this);
        BtnSalir = new JButton("Salir");
        BtnSalir.setLayout(null);
        BtnSalir.reshape(30, 125, 110, 15);
        BtnSalir.addActionListener(this);
        BtnSalir.setMnemonic('S');

        PanDatos = new JPanel();
        PanDatos.reshape(100, 5, 270, 90);
        PanDatos.setBorder(BorderFactory.createTitledBorder(""));
        PanDatos.setLayout(null);
        PanDatos.add(TxtRespuesta);
        PanDatos.add(LblPregunta);
        PanDatos.add(LblEnunciado);

        PanBotones = new JPanel();
        PanBotones.reshape(110, 95, 250, 65);
        PanBotones.setBorder(BorderFactory.createTitledBorder("Operaciones"));
        PanBotones.add(BtnConsultar);
        PanBotones.add(BtnSalir);

        PanGeneral = new JPanel();
        PanGeneral.setLayout(null);
        PanGeneral.reshape(10, 10, 380, 170);
        PanGeneral.setBorder(BorderFactory.createTitledBorder(""));
        PanGeneral.add(PanDatos);
        PanGeneral.add(PanBotones);

        Ventana = new JFrame("Recordar contraseña");
        Ventana.setLayout(null);
        Ventana.add(LblImagen);
        Ventana.add(PanGeneral);
        Ventana.resize(400, 215);
        Ventana.addWindowListener(this);
        Ventana.setLocationRelativeTo(null);
        Ventana.setIconImage(IcoCabecera);
        Ventana.setBackground(Color.pink);
        Ventana.setResizable(false);
        Ventana.show();
        Ventana.toFront();
    }

    public void Verificar_Respuesta() {

        try {
            if (TxtRespuesta.getText().equalsIgnoreCase(usuario.getRespuesta())) {
                cmd.enviarEmail(usuario);
                Ventana.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Respuesta incorrecta por favor intente de nuevo o contacte al administrador del sistema", "METROFARM", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            System.out.println("la excepcion fue " + ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == BtnSalir) {
            Ventana.dispose();
        }
        if (e.getSource() == BtnConsultar) {
            ventana.bloquear(Ventana);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    Verificar_Respuesta();
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();

        }
    }

    public void LimpiarCampos() {
        TxtRespuesta.setText("");
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

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
}
