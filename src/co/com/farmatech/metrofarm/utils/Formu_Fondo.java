/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.utils;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Softcaribbean
 */
public class Formu_Fondo extends JPanel {

    //String ruta="/Imagenes/";
    ImageIcon fon;

    public Formu_Fondo() {

    }

    public Formu_Fondo(ImageIcon fondo) {
        fon = fondo;

    }

     // private Image IMG =new ImageIcon(fon).getImage();
    public void paintChildren(Graphics g) {

        g.drawImage(fon.getImage(), 0, 0, getWidth(), getHeight(), this);
        super.paintChildren(g);
    }

}
