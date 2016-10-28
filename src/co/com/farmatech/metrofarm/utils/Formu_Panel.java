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
public class Formu_Panel extends JPanel {

    
    ImageIcon fon;

    public Formu_Panel(ImageIcon fondo) {
        fon = fondo;
    }

   
    @Override
    public void paintChildren(Graphics g) {

        g.drawImage(fon.getImage(), 0, 0, getWidth(), getHeight(), this);
        super.paintChildren(g);
    }
}