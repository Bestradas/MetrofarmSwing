/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.utils;

import co.com.farmatech.metrofarm.utils.buttons.BotonesMenu;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author Bryan
 */
public class PanelPDf extends JFrame implements ActionListener {

    Formu_Fondo PanelGeneral1, PanelPDF;
    JFileChooser elegir = new JFileChooser();
    public PagePanel campoPDF = new PagePanel();
    public JButton btnSiguiente = new JButton("Siguiente");
    public JButton btnAtras = new JButton("Atras");
    public JButton btnBuscar = new JButton("Buscar Pàgina");
    Scrollbar zoom;
    String ruta1 = "";
    String ruta2 = "";
    String ruta3 = "";
    String ruta4 = "";
    String extencion;
    int pagina = 1;
    int paginas = 13;
    ImageIcon IcoTextura, Icofondo;
    File ArchivoSeleccionado;
    String ruta = "co/com/farmatech/metrofarm/resources/imagenes/";
    public BotonesMenu btnDescargar = new BotonesMenu(new ImageIcon(getClass().getClassLoader().getResource(ruta+"Descargar.png")), new ImageIcon(getClass().getClassLoader().getResource(ruta+"Descargar.png")));
    public JButton btnSalir = new JButton("Salir", new ImageIcon(getClass().getClassLoader().getResource(ruta+"volver.gif")));


    public PanelPDf(File Archivo) {

        super("Documento de calibración METROFARM -v1");

        ((JPanel) getContentPane()).setOpaque(false);  // para que muestre la imagen
        this.setSize(800, 768);
        this.setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);
        setBackground(Color.white);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(0);

        ArchivoSeleccionado = Archivo;

        setIconImage(new ImageIcon(getClass().getClassLoader().getResource(ruta+"FARMA.png")).getImage());
        //setLocationRelativeTo(null);
        Font TLetra = new Font("Arial", Font.PLAIN, 12);
        Font Advertencia = new Font("Arial", Font.PLAIN, 15);

        Icofondo = new ImageIcon(getClass().getClassLoader().getResource(ruta+"fondomenu.png"));
        IcoTextura = new ImageIcon(getClass().getClassLoader().getResource(ruta+"textura.png"));


        PanelGeneral1 = new Formu_Fondo(Icofondo);
        PanelGeneral1.setBorder(BorderFactory.createTitledBorder(""));
        //PanelGeneral1.add(fondo);
        PanelGeneral1.setBackground(Color.black);
        PanelGeneral1.setForeground(Color.red);
        PanelGeneral1.reshape(0, 0, 800, 768);
        PanelGeneral1.setLayout(null);

        elegir.setCurrentDirectory(new File(System.getProperty("user.home")));
        elegir.setFileSelectionMode(JFileChooser.FILES_ONLY);
        elegir.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
        elegir.setAcceptAllFileFilterUsed(true);

        zoom = new Scrollbar(Scrollbar.HORIZONTAL, 20, 0, 1, 50);
        zoom.reshape(100, 705, 100, 15);

        PanelPDF = new Formu_Fondo(IcoTextura);
        PanelPDF.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(Integer.parseInt("015289", 16))), "Lector PDF"));
        //Panelinfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(Integer.parseInt("015289", 16))), "Datos del equipo", WIDTH, PROPERTIES, TLetra, new Color(Integer.parseInt("EF2F24", 16))));
        PanelPDF.setBackground(Color.GRAY);
        PanelPDF.setForeground(new Color(Integer.parseInt("EF2F24", 16)));
        PanelPDF.reshape(0, 0, 800, 768);
        PanelPDF.setLayout(null);
        PanelPDF.setVisible(true);

        //panel pdf
        //Dimesion del frame y panel
        Dimension pantalla, cuadro;
        pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        cuadro = this.getSize();
        this.setLocation(((pantalla.width - cuadro.width) / 2),
                (pantalla.height - cuadro.height) / 2);
        campoPDF.setBounds(10, 10, 780, 685);
        campoPDF.setBackground(Color.white);

        btnSiguiente.setBounds(380, 705, 100, 20);
        btnSiguiente.setVisible(true);
        btnSiguiente.addActionListener(this);

        btnAtras.setBounds(255, 705, 100, 20);
        btnAtras.addActionListener(this);

        btnBuscar.setBounds(300, 500, 100, 20);
        btnBuscar.addActionListener(this);

        btnSalir.setBounds(500, 705, 100, 20);
        btnSalir.setVisible(true);
        btnSalir.addActionListener(this);

        btnDescargar.setBounds(318, 703, 100, 25);
        btnDescargar.setVisible(true);
        btnDescargar.setToolTipText("Descargar Documento");
        btnDescargar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDescargar.addActionListener(this);


        PanelGeneral1.add(PanelPDF);

        campoPDF.setAutoscrolls(true);
        
        PanelGeneral1.setVisible(true);
        this.getContentPane().add(PanelGeneral1);

        PanelPDF.add(campoPDF);
        PanelPDF.add(btnAtras);
        PanelPDF.add(btnBuscar);
        PanelPDF.add(btnSiguiente);
        PanelPDF.add(btnDescargar);
        PanelPDF.add(btnSalir);
        PanelPDF.add(zoom);
        



        this.repaint();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSalir) {
            int respuesta = JOptionPane.showConfirmDialog(null, "Esta seguro que desea Salir", "Farmatech", JOptionPane.YES_NO_OPTION);
            switch (respuesta) {
                case JOptionPane.YES_OPTION:
                    this.dispose();
                    ArchivoSeleccionado.delete();
                    break;
                case JOptionPane.NO_OPTION:
                    break;

            }
        }

        if (e.getSource() == btnDescargar) {

            int returnVal = elegir.showDialog(PanelPDf.this, "Guardar");


            if (returnVal == JFileChooser.APPROVE_OPTION) {
                InputStream in = null;
                try {
                    File Archivo = new File(String.valueOf(elegir.getSelectedFile().toString()));
                    String Ruta = String.valueOf(elegir.getSelectedFile().getPath() + ".pdf");
                    File destino = new File(Ruta);
                    in = new FileInputStream(ArchivoSeleccionado);
                    OutputStream out = new FileOutputStream(destino);

                    byte[] buf = new byte[1024];
                    int len;

                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    in.close();
                    out.close();

                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();                    }
                }

            } else {
                System.out.println("Carga cancelada por el usuario");
            }
        }



        if (e.getSource() == btnSiguiente) {

            if (ArchivoSeleccionado == null) {
                JOptionPane.showMessageDialog(null, "Para visualizar una calificación debe cargarla y luego presionar el boton ver");
            } else {

                pagina += 1;
                if (pagina > paginas || pagina < 1) {
                    pagina = 1;
                }
                try {

                    //Ubicación del archivo pdf
                    RandomAccessFile raf = new RandomAccessFile(ArchivoSeleccionado, "r");
                    FileChannel channel = raf.getChannel();
                    MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                    PDFFile pdffile = new PDFFile(buf);

                    // muestra la pagina en la posición de la variable pagina page channel.size()
                    PDFPage page = pdffile.getPage(pagina);
                    paginas = pdffile.getNumPages(); //numero de paginas del documento
                    campoPDF.useZoomTool(true);
                    campoPDF.showPage(page);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                repaint();
                campoPDF.repaint();

            }

        }


        if (e.getSource() == btnAtras) {
            pagina -= 1;
            if (pagina > paginas || pagina < 1) {
                pagina = 1;
            }
            try {

                //ubicación del archivo pdf.
                RandomAccessFile raf = new RandomAccessFile(ArchivoSeleccionado, "r");
                FileChannel channel = raf.getChannel();
                MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                PDFFile pdffile = new PDFFile(buf);

                // muestra la pagina en la posición de la variable pagina page channel.size()
                PDFPage page = pdffile.getPage(pagina);
                paginas = pdffile.getNumPages(); ////numero de paginas del documento
                campoPDF.setClip(null);
                campoPDF.useZoomTool(false);
                campoPDF.showPage(page);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            repaint();
            campoPDF.repaint();
        }
    }


}
