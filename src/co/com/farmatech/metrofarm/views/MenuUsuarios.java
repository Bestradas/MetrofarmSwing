/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.views;

import co.com.farmatech.metrofarm.utils.ComboItem;
import co.jankins.psf.common.global.GlobalUtils;
import com.co.farmatech.metrofarm.dto.DtoUsuario;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import org.matrix.BlackTabbedPane;

/**
 *
 * @author bestrada
 */
public class MenuUsuarios extends JFrame{
    
    static String[] Nodo;
    static boolean[] Permiso;
    private BlackTabbedPane blackTabbedPane2;
    DtoUsuario usuarioLogeado;
    String ruta = "co/com/farmatech/metrofarm/resources/imagenes/";

    public MenuUsuarios() {

        super("Administración del sistema");
        
        this.usuarioLogeado= GlobalUtils.getContext().getLoginUser();
        // crear objeto JTabbedPane 
        blackTabbedPane2 = new BlackTabbedPane();
        blackTabbedPane2.setTabPlacement(2);
        //blackTabbedPane2.setTabPlacement(JTabbedPane.LEFT);
        ImageIcon IcoUsuarios = new ImageIcon(getClass().getClassLoader().getResource(ruta+"p.png"));
        ImageIcon IcoPerfiles = new ImageIcon(getClass().getClassLoader().getResource(ruta+"d.png"));


        // establecer pane11 y agregarlo al objeto JTabbedPane 
        Usuarios us = new Usuarios();
        us.setSize(1024, 768);
        //codigo,tipodeid,nombres,telefono,celular,numeroid,apellidos,telefono2,email,usuario,contraseña,Asignar,pregunta,respuesta
        us.txtcodigo.setText(usuarioLogeado.getCodigo());
        us.txtcodigo.setEditable(false);
        us.cbotipoid.setSelectedItem(usuarioLogeado.getTipo());
        us.txtnombres.setText(usuarioLogeado.getNombres());
        us.txttelefono1.setText(usuarioLogeado.getTelefono1());
        us.txtcelular.setText(usuarioLogeado.getCelular());
        us.txtapellido.setText(usuarioLogeado.getApellidos());
        us.txttelefono2.setText(usuarioLogeado.getTelefono2());
        us.txtcorreo.setText(usuarioLogeado.getCorreo());
        us.txtusuario.setText(usuarioLogeado.getUsuario());
        us.txtcontraseña.setText(usuarioLogeado.getContrasena());
        us.cbopregunta.setSelectedItem(usuarioLogeado.getPregunta());
        us.txtrespuesta.setText(usuarioLogeado.getRespuesta());
        us.setBorder(null);

        //Permisos
        if (!usuarioLogeado.getPerfil().isGuardarUsuariao()) {
            us.btnGuardar.setEnabled(false);
            us.btnNuevo.setEnabled(false);
        }
        if (!usuarioLogeado.getPerfil().isBuscarUsuario()) {
            us.btnBuscar.setEnabled(false);
            us.TablaDatos.setEnabled(false);

        }
        if (!usuarioLogeado.getPerfil().isModificarUsuario()) {
            us.btnActualizar.setEnabled(false);

        }
        if (!usuarioLogeado.getPerfil().isEliminarUsuario()) {
            us.btnEliminar.setEnabled(false);
        }
        
        blackTabbedPane2.addTab("Usuarios", IcoUsuarios, us, "Primer panel");

        // establecer panel2 y agregarlo al objeto JTabbedPane

        Perfiles per = new Perfiles();
        per.setSize(1366, 768);

        if (!usuarioLogeado.getPerfil().isGuardarPerfil()) {
            per.btnGuardar.setEnabled(false);
            per.btnNuevo.setEnabled(false);
        }
        if (!usuarioLogeado.getPerfil().isBuscarPerfil()) {
            per.btnBuscar.setEnabled(false);
            per.Tablaperfiles.setEnabled(false);
        }
        if (!usuarioLogeado.getPerfil().isModificarPerfil()) {
            per.btnActualizar.setEnabled(false);
            per.Tablaperfiles.setEnabled(false);
        }


        blackTabbedPane2.addTab("Perfiles", IcoPerfiles, per, "Segundo panel");


        // establecer panel2 y agregarlo al objeto JTabbedPane
        // agregar objeto JTabbedPane al contenedor
        getContentPane().add(blackTabbedPane2);

        setSize(1366, 768);
        setVisible(true);
        setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource(ruta+"FARMA.png")).getImage());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }
}
    

