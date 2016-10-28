/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.utils;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Fondo_Registro extends JPanel {

    //String ruta="/Imagenes/";
    ImageIcon fon;

    public Fondo_Registro(ImageIcon fondo) {
        fon = fondo;

    }

    // private Image IMG =new ImageIcon(fon).getImage();
    @Override
    public void paintChildren(Graphics g) {




        g.drawImage(fon.getImage(), 0, 0, this);
        super.paintChildren(g);
    }
}
