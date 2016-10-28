/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Softcaribbean
 */
public class PrintJPanel extends JFrame implements Printable {
    JPanel componente;
     public PrintJPanel(JPanel componentes)
     {
         setIconImage(new ImageIcon(getClass().getClassLoader().getResource("Utilidades/Imagenes/FARMA.png")).getImage());

         
        componente=componentes;         
        try
        {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this);
            job.printDialog();
            job.print();
        } catch (PrinterException ex) { }   
     }
      
     public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException 
     {
        if (pageIndex > 0) 
            return NO_SUCH_PAGE;
         Graphics2D g2d = (Graphics2D)graphics;
        //Punto donde empezará a imprimir dentro la pagina (100, 50)
        g2d.translate(  pageFormat.getImageableX()+50, pageFormat.getImageableY()+50);
        g2d.scale(0.50,0.50); //Reducción de la impresión al 50%
        componente.printAll(graphics);
        return PAGE_EXISTS;                
    }
     

}
