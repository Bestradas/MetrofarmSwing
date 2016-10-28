/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.views;

import co.com.farmatech.metrofarm.utils.Formu_Fondo;
import co.com.farmatech.metrofarm.utils.buttons.BotonesMenu;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import ventana.bloqueo.VentanaBloqueo;

/**
 *
 * @author bestrada
 */
public class Cronogramas extends JFrame implements ActionListener {

    public JFrame Ventana;
    Formu_Fondo PanelGeneral1;
    ImageIcon Icofondo;
    ImageIcon IcoCronog_calif, IcoCronog_calif_Anim, IcoCronog_calib, IcoCronog_calib_Anim, IcoCronog_mant, IcoCronog_mant_Anim;
    BotonesMenu btnCronogramas_calib, btnCronogramas_calif, btnCronogramas_mant;
    private JMenuBar Menu;
    private JMenu menu1;
    private JMenuItem m11;
    Image IcoCabecera;
    Font TLetra = new Font("Arial", Font.PLAIN, 12);
    Font Advertencia = new Font("Arial", Font.PLAIN, 15);
    String ruta = "co/com/farmatech/metrofarm/resources/imagenes/";
    private final VentanaBloqueo ventana = new VentanaBloqueo();

    public Cronogramas() {
        Menu = new JMenuBar();
        menu1 = new JMenu("Archivo");
        Menu.add(menu1);

        m11 = new JMenuItem("Regresar");
        m11.addActionListener(this);
        m11.setIcon(new ImageIcon(getClass().getClassLoader().getResource(ruta + "atras.png")));
        menu1.add(m11);

        Icofondo = new ImageIcon(getClass().getClassLoader().getResource(ruta + "fondomenufarma.png"));

        IcoCronog_calib = new ImageIcon(getClass().getClassLoader().getResource(ruta + "cronogramaU2.png"));
        IcoCronog_calib_Anim = new ImageIcon(getClass().getClassLoader().getResource(ruta + "cronograma2.png"));

        IcoCronog_calif = new ImageIcon(getClass().getClassLoader().getResource(ruta + "cronocalifU.png"));
        IcoCronog_calif_Anim = new ImageIcon(getClass().getClassLoader().getResource(ruta + "cronocalif.png"));

        IcoCronog_mant = new ImageIcon(getClass().getClassLoader().getResource(ruta + "cronogmantU.png"));
        IcoCronog_mant_Anim = new ImageIcon(getClass().getClassLoader().getResource(ruta + "cronogmant.png"));


        //////////////////////////Boton cronograma calibracion///////////////////////////////////////////////
        btnCronogramas_calib = new BotonesMenu(IcoCronog_calib, IcoCronog_calib_Anim);
        btnCronogramas_calib.setBounds(50, 100, 400, 400);
        btnCronogramas_calib.setVisible(true);
        btnCronogramas_calib.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //////////////////////////Boton cronograma mantenimiento///////////////////////////////////////////////
        btnCronogramas_mant = new BotonesMenu(IcoCronog_mant, IcoCronog_mant_Anim);
        btnCronogramas_mant.setBounds(500, 100, 400, 400);
        btnCronogramas_mant.setVisible(true);
        btnCronogramas_mant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //////////////////////////Boton cronograma calificacion///////////////////////////////////////////////
        btnCronogramas_calif = new BotonesMenu(IcoCronog_calif, IcoCronog_calif_Anim);
        btnCronogramas_calif.setBounds(950, 100, 400, 400);
        btnCronogramas_calif.setVisible(true);
        btnCronogramas_calif.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        PanelGeneral1 = new Formu_Fondo(Icofondo);
        PanelGeneral1.setBorder(BorderFactory.createTitledBorder(""));
        //PanelGeneral1.add(fondo);
        PanelGeneral1.setBackground(Color.black);
        PanelGeneral1.setForeground(Color.red);
        PanelGeneral1.reshape(0, 0, 1366, 768);
        PanelGeneral1.setLayout(null);

        PanelGeneral1.setVisible(true);
        this.getContentPane().add(PanelGeneral1);

        PanelGeneral1.add(btnCronogramas_calib);
        PanelGeneral1.add(btnCronogramas_calif);
        PanelGeneral1.add(btnCronogramas_mant);

        Ventana = new JFrame("Cronograma de calibración y calificación METROFARM -v1");
        Ventana.setLayout(null);
        Ventana.add(PanelGeneral1);
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


        btnCronogramas_calib.addActionListener(this);
        btnCronogramas_mant.addActionListener(this);
        btnCronogramas_calif.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {




        if (e.getSource() == btnCronogramas_calib) {
            Ventana.dispose();
            CronogramaCalibracion cronograma = new CronogramaCalibracion();




        }

        if (e.getSource()
                == btnCronogramas_mant) {
            Ventana.dispose();
            try {
                CronogramaMant cronomat = new CronogramaMant();
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Hubo un error :" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource()
                == btnCronogramas_calif) {
            try {
                Ventana.dispose();
                CronogramaCalificacion cronogramacal = new CronogramaCalificacion();
            } catch (ParseException ex) {
                Logger.getLogger(Cronogramas.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (e.getSource()
                == m11) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea regresar?", "", JOptionPane.YES_NO_OPTION);
            switch (respuesta) {
                case JOptionPane.YES_OPTION:
                    Ventana.dispose();
                    MenuCronogramaIndicador men = new MenuCronogramaIndicador();
                    break;
                case JOptionPane.NO_OPTION:
                    break;

            }

        }
    }
}
