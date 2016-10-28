/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.utils.buttons;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Bryan
 */
public class BotonesMenu extends JButton {
ImageIcon rep;	
ImageIcon ampli;
// Constructor del Botón Redondo



public BotonesMenu( String rotulo ) {
super( rotulo );
// Hacemos que el JButton no pinte su fondo, de este modo podremos
// nosotros hacer que el color de fondo que se salga de la figura sea
// del mismo color que el fondo de la ventana
setContentAreaFilled( false );

}

public BotonesMenu(ImageIcon Normal,ImageIcon Enfocada){
rep=new ImageIcon(Normal.getImage());
ampli=new ImageIcon(Enfocada.getImage());
setSize(88,104);
setIcon(rep);
setRolloverIcon(ampli);
setContentAreaFilled( false );
setFocusable(false);

}
// Este es el método que pinta el botón en el color correspondiente al estado
// en que se encuentre, y también coloca el rótulo que se haya indicado en el
// centro del botón
@Override
protected void paintComponent( Graphics g ) {
if( getModel().isArmed() ) {
// Se puede hacer que la característica de Pulsado sea una propiedad de
// esta clase
g.setColor( null );
}
else {
g.setColor( null );
}


// Llamando al método de la clase padre, haremos que aparezca el rótulo y
// hacemos que el restángulo correspondiente al botón sea el que controla
// el foco
super.paintComponent( g );
}

// Pintamos el borde del botón con una línea simple
@Override
protected void paintBorder( Graphics g ) {
g.setColor( getForeground() );


}

    





}

