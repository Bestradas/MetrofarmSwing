package co.com.farmatech.metrofarm.init;

import co.com.farmatech.metrofarm.utils.MP3;
import co.com.farmatech.metrofarm.views.Ingreso;
import java.awt.Cursor;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;

/**
 * @author Bryan
 *
 */
public class Farmatech extends JFrame
{
    JLabel lblCarga, fondo, lblCarga2;
    Timer contador;
    ImageIcon image1, image2;
    Shape forma;

    public Farmatech() {

        setSize(633, 384);
        getContentPane().setLayout(null);
        setUndecorated(true);
        setBackground(null);
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("co/com/farmatech/metrofarm/resources/imagenes/FARMA.png")).getImage());

        forma = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 70, 70);
        setShape(forma);
        setLocationRelativeTo(getRootPane());
        ((JPanel) getContentPane()).setOpaque(false);

        ImageIcon nu = new ImageIcon(getClass().getClassLoader().getResource("co/com/farmatech/metrofarm/resources/imagenes/iniciofarmatech.png"));
        ImageIcon imag = new ImageIcon(getClass().getClassLoader().getResource("co/com/farmatech/metrofarm/resources/imagenes/carga.gif"));
        ImageIcon imag2 = new ImageIcon(getClass().getClassLoader().getResource("co/com/farmatech/metrofarm/resources/imagenes/cargando.gif"));

        lblCarga = new JLabel(imag);
        lblCarga.setBounds(420, -20, 200, 100);
        lblCarga.setIcon(imag);
        lblCarga2 = new JLabel(imag2);
        lblCarga2.setBounds(190, 180, 200, 100);
        lblCarga2.setIcon(imag2);

        imag.setImageObserver(lblCarga);
        imag2.setImageObserver(lblCarga2);

        fondo = new JLabel(nu);
        fondo.setBounds(2, 0, 629, 384);
        fondo.add(lblCarga);
        fondo.add(lblCarga2);
        getLayeredPane().add(fondo, JLayeredPane.FRAME_CONTENT_LAYER);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        contador = new Timer(10000, new ActionListener() { //el 4000 es el tiempo en milisegundos(4 segundos)
            @Override
            public void actionPerformed(ActionEvent e) {
                show(false);
                contador.stop();//parar el contador para que no realice la accion cada 4 segundos               
                Ingreso ingreso = new Ingreso();//accion que va a realizar
                
            }
        });
        contador.start();//Iniciar el temporizador		
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            System.setProperty("file.encoding", "UTF-8");
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
            String filename = ("Kalimba.mp3");
            // nueva instancia de la clase MP3
            MP3 mp3 = new MP3(filename);
            mp3.play();


            // cuando el tiempo de proceso termina se detiene la reproduccion
            mp3.close();

            // reproducir el mp3 desde el principio
            mp3 = new MP3(filename);
            mp3.play();        			// prueba reproducir musica

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error :" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


          Farmatech farmatech = new Farmatech();
//        CmdUtils cmd=new CmdUtils();
//        GlobalUtils.registerContextInfo(new DtoUsuario(), new DtoPerfil());
//        cmd.synchronization(new DtoEquipo());

            

    }
        
}
