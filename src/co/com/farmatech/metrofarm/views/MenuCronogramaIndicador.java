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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author bestrada
 */
public class MenuCronogramaIndicador extends JFrame implements ActionListener {

    Formu_Fondo PanelGeneral1;
    ImageIcon Icofondo;
    ImageIcon IcoCronog, IcoCronogAnim, IcoIndicadores, IcoIndicadoresAnim;
    BotonesMenu btnCronogramas, btnIndicadores;
    private JMenuBar Menu;
    private JMenu menu1;
    private JMenuItem m11;
    Image IcoCabecera;
    Font TLetra = new Font("Arial", Font.PLAIN, 12);
    Font Letra = new Font("Arial", Font.PLAIN, 20);
    Font NomLetra = new Font("Arial", Font.PLAIN, 15);
    String ruta = "co/com/farmatech/metrofarm/resources/imagenes/";

    public MenuCronogramaIndicador() {
        super("Cronograma e indicadores METROFARM -v1");

        ((JPanel) getContentPane()).setOpaque(false);  // para que muestre la imagen
        this.setSize(1366, 768);
        this.setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);
        setBackground(Color.white);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(0);

        Menu = new JMenuBar();
        menu1 = new JMenu("Archivo");
        Menu.add(menu1);

        m11 = new JMenuItem("Regresar");
        m11.addActionListener(this);
        m11.setIcon(new ImageIcon(getClass().getClassLoader().getResource(ruta+"atras.png")));
        menu1.add(m11);




        Icofondo = new ImageIcon(getClass().getClassLoader().getResource(ruta+"fondomenufarma.png"));

        IcoCronog = new ImageIcon(getClass().getClassLoader().getResource(ruta+"cronogramaU.png"));
        IcoCronogAnim = new ImageIcon(getClass().getClassLoader().getResource(ruta+"cronograma.png"));

        IcoIndicadores = new ImageIcon(getClass().getClassLoader().getResource(ruta+"indicadoresU.png"));
        IcoIndicadoresAnim = new ImageIcon(getClass().getClassLoader().getResource(ruta+"indicadores.png"));


        //////////////////////////Boton cronograma///////////////////////////////////////////////
        btnCronogramas = new BotonesMenu(IcoCronog, IcoCronogAnim);
        btnCronogramas.setBounds(200, 100, 400, 400);
        btnCronogramas.setVisible(true);
        btnCronogramas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //////////////////////////Boton indicadores///////////////////////////////////////////////
        btnIndicadores = new BotonesMenu(IcoIndicadores, IcoIndicadoresAnim);
        btnIndicadores.setBounds(700, 100, 400, 400);
        btnIndicadores.setVisible(true);
        btnIndicadores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        PanelGeneral1 = new Formu_Fondo(Icofondo);
        PanelGeneral1.setBorder(BorderFactory.createTitledBorder(""));
        //PanelGeneral1.add(fondo);
        PanelGeneral1.setBackground(Color.black);
        PanelGeneral1.setForeground(Color.red);
        PanelGeneral1.reshape(0, 0, 1366, 768);
        PanelGeneral1.setLayout(null);

        PanelGeneral1.setVisible(true);
        this.getContentPane().add(PanelGeneral1);

        PanelGeneral1.add(btnCronogramas);
        PanelGeneral1.add(btnIndicadores);

        add(PanelGeneral1);
        setJMenuBar(Menu);
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource(ruta+"FARMA.png")).getImage());

        btnCronogramas.addActionListener(this);
        btnIndicadores.addActionListener(this);

        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == m11) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea regresar?", "", JOptionPane.YES_NO_OPTION);
            switch (respuesta) {
                case JOptionPane.YES_OPTION:
                    this.dispose();
                   break;
                case JOptionPane.NO_OPTION:
                    break;

            }

        }

        if (e.getSource() == btnCronogramas) {
            this.dispose();
            Cronogramas cronograma = new Cronogramas();
        }

        if (e.getSource() == btnIndicadores) {
            this.dispose();
            Indicadores ind = new Indicadores();
        }
    }
}
