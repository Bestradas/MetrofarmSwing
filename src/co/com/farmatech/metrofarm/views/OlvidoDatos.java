/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.views;

import co.com.farmatech.metrofarm.cmd.CmdUsuarios;
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
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class OlvidoDatos implements ActionListener {

    JFrame Ventana;
    JLabel LblDocumento, LblImagen, LblPregunta;
    JTextField TxtDocumento;
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
    String ruta;
    CmdUsuarios cmd;
    private final VentanaBloqueo ventana = new VentanaBloqueo();

    public OlvidoDatos() {

        cmd = new CmdUsuarios();
        this.ruta = "co/com/farmatech/metrofarm/resources/imagenes/";
        IcoRecordar = new ImageIcon(getClass().getClassLoader().getResource(ruta + "olvido.png"));
        IcoSalir = new ImageIcon(ruta + "salir.Gif");
        IcoCabecera = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(ruta + "FARMA.png"));

        LblDocumento = new JLabel("Número  de documento");
        LblDocumento.reshape(50, 10, 300, 20);
        LblImagen = new JLabel(IcoRecordar);
        LblImagen.reshape(20, 20, 100, 150);

        TxtDocumento = new JTextField();
        TxtDocumento.reshape(35, 35, 160, 20);

        BtnConsultar = new JButton("Enviar");
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
        PanDatos.reshape(125, 20, 250, 70);
        PanDatos.setBorder(BorderFactory.createTitledBorder(""));
        PanDatos.setLayout(null);
        PanDatos.add(TxtDocumento);
        PanDatos.add(LblDocumento);

        PanBotones = new JPanel();
        PanBotones.reshape(125, 95, 250, 65);
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

        Ventana.setLocationRelativeTo(null);
        Ventana.setIconImage(IcoCabecera);
        Ventana.setBackground(Color.pink);
        Ventana.setResizable(false);
        Ventana.show();




    }

    public void Buscar_ti() {

        String documento = TxtDocumento.getText();
        DtoUsuario usuarioSend = new DtoUsuario();
        usuarioSend.setCodigo(documento);

        DtoUsuario usuario = cmd.getUserByDoc(usuarioSend);

        if (usuario != null) {
            PreguntaSeguridad pregSeguridad = new PreguntaSeguridad(usuario);
            Ventana.dispose();
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
                    Buscar_ti();
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();
        }
    }

    public void LimpiarCampos() {
        TxtDocumento.setText("");
    }

}