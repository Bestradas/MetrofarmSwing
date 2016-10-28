/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.utils;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 *
 * @author bestrada
 */
public class textfield_icon extends JTextField {

    //la imagen que incorporare al componente
    private Image image = new ImageIcon(getClass().getClassLoader().getResource("co/com/farmatech/metrofarm/resources/imagenes/buscar_txt.png")).getImage();
    private Image image2 = new ImageIcon(getClass().getClassLoader().getResource("co/com/farmatech/metrofarm/resources/imagenes/line.png")).getImage();

    {//agrego un margen constructores
        //getMargin().right=30;
        //getMargin().right =image.getHeight(this);
        getMargin().right=25;
    }

    //agrego los constructores del jtextfield
    public textfield_icon() {
        super("");
    }

    public textfield_icon(Document doc, String text, int columns) {
        super(doc, text, columns);
    }

    public textfield_icon(int columns) {
        super(columns);
    }

    public textfield_icon(String text, int columns) {
        super(text, columns);
    }

    public textfield_icon(String text) {
        super(text);
    }

    @Override
    public void paint(Graphics g) {
        if (isVisible()) { //sobrescribiendo el componente
            super.paint(g);
            g.drawImage(image, 180, 5, null); // dibujo la imagen
            g.drawImage(image2, 174, 2, null); // dibujo la imagen
        }
    }
}
