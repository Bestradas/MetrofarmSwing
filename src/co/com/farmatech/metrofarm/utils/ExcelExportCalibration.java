/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.utils;

import co.com.farmatech.metrofarm.resources.Propiedades;
import jxl.write.Label;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author bestrada
 */
public class ExcelExportCalibration {
    
    WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD);
    WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
    WritableFont subtitulosFont = new WritableFont(WritableFont.ARIAL, 8, WritableFont.NO_BOLD);
    WritableCellFormat subtitulo = new WritableCellFormat(subtitulosFont);
    WritableFont EquiposFont = new WritableFont(WritableFont.ARIAL, 8, WritableFont.NO_BOLD);
    WritableCellFormat equipos = new WritableCellFormat(EquiposFont);
    WritableFont FormatoFont = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD);
    WritableCellFormat formato = new WritableCellFormat(FormatoFont);
    WritableFont AnotacionFont = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
    WritableCellFormat anotacion = new WritableCellFormat(AnotacionFont);    
    private File file;
    private List<JTable> table;
    private List<String> nombreTab;
    UtilFarmatech util=new UtilFarmatech();
    
     public ExcelExportCalibration(List<JTable> table, File file, List<String> nombreTab) throws Exception {
        this.file = file;
        this.table = table;
        this.nombreTab = nombreTab;
        subtitulo.setBackground(Colour.BLUE_GREY);
        subtitulo.setWrap(true);
        subtitulo.setVerticalAlignment(VerticalAlignment.CENTRE);
        subtitulo.setAlignment(Alignment.CENTRE);
        subtitulo.setBorder(Border.ALL, BorderLineStyle.DASHED);

        equipos.setBackground(Colour.WHITE);
        equipos.setWrap(true);
        equipos.setVerticalAlignment(VerticalAlignment.CENTRE);
        equipos.setAlignment(Alignment.CENTRE);
        equipos.setBorder(Border.ALL, BorderLineStyle.THIN);

        formato.setBackground(Colour.WHITE);
        formato.setWrap(true);
        formato.setVerticalAlignment(VerticalAlignment.CENTRE);
        formato.setAlignment(Alignment.CENTRE);
        formato.setBorder(Border.ALL, BorderLineStyle.THIN);

        anotacion.setBackground(Colour.WHITE);
        anotacion.setBorder(Border.ALL, BorderLineStyle.THIN);
        anotacion.setBackground(Colour.LIGHT_GREEN);
        
        



        if (nombreTab.size() != table.size()) {
            throw new Exception("Error");
        }


    }

    public boolean export() {
        try {
            try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file))) {
                WritableWorkbook w = Workbook.createWorkbook(out);




                for (int i = 0; i < table.size(); i++) {
                    JTable tabla = table.get(i);
    //Como excel tiene muchas hojas esta crea o toma la hoja
    //Coloca el nombre del "tab" y el indice del tab
                    WritableSheet s = w.createSheet(nombreTab.get(i), 0);

                    for (int j = 0; j < tabla.getColumnCount(); j++) {
                        for (int k = 0; k < tabla.getRowCount(); k++) {
                            Object object = tabla.getModel().getValueAt(k, j);
                            String value="";
                            if(object instanceof JLabel){
                                value= ((JLabel)object).getText();
                            }else if(object instanceof String){
                                value=(String)object;
                            }
                            s.addCell(new Label(6, 0, "PROGRAMA  DE CALIBRACIÓN DE EQUIPOS E INSTRUMENTOS", titleFormat));
                            WritableImage der = new WritableImage(11, 0, 4, 3, util.crearFile(Propiedades.class.getResourceAsStream("cron.png"), "cron.png"));
                            WritableImage izq = new WritableImage(1, 0, 4, 3, util.crearFile(Propiedades.class.getResourceAsStream("cron.png"), "cron.png"));
                            s.addImage(der);
                            s.addImage(izq);
                            s.addCell(new Label(0, 2, "F-228-1", formato));
                            s.addCell(new Label(20,0, "Programado: mes para el cual esta programada la calibración.", anotacion));
                            s.addCell(new Label(20,1,"Ejecutado: mes en que se realiza la calibración.",anotacion));
                            s.addCell(new Label(20,2,"Cumplido:aparece cuando la fecha de programación y la fecha de ejecución coinciden con respecto al mes ",anotacion));
                            s.addCell(new Label(0, 3, "EQUIPO INSTRUMENTO", subtitulo));
                            s.addCell(new Label(1, 3, "MARCA", subtitulo));
                            s.addCell(new Label(2, 3, "MODELO", subtitulo));
                            s.addCell(new Label(3, 3, "SERIE", subtitulo));
                            s.addCell(new Label(4, 3, "CÓDIGO", subtitulo));
                            s.addCell(new Label(5, 3, "UBICACIÓN", subtitulo));
                            s.addCell(new Label(6, 3, "FRECUENCIA CALIBRACIÓN", subtitulo));
                            s.addCell(new Label(7, 3, "ÚLTIMA CALIBRACIÓN", subtitulo));
                            s.addCell(new Label(8, 3, "PRÓXIMA CALIBRACIÓN", subtitulo));
                            s.addCell(new Label(9, 3, "FECHA DE EJECUCION", subtitulo));
                            s.addCell(new Label(10, 3, "ÉNERO", subtitulo));
                            s.addCell(new Label(11, 3, "FEBRERO", subtitulo));
                            s.addCell(new Label(12, 3, "MARZO", subtitulo));
                            s.addCell(new Label(13, 3, "ABRIL", subtitulo));
                            s.addCell(new Label(14, 3, "MAYO", subtitulo));
                            s.addCell(new Label(15, 3, "JUNIO", subtitulo));
                            s.addCell(new Label(16, 3, "JULIO", subtitulo));
                            s.addCell(new Label(17, 3, "AGOSTO", subtitulo));
                            s.addCell(new Label(18, 3, "SEPTIEMBRE", subtitulo));
                            s.addCell(new Label(19, 3, "OCTUBRE", subtitulo));
                            s.addCell(new Label(20, 3, "NOVIEMBRE", subtitulo));
                            s.addCell(new Label(21, 3, "DICIEMBRE", subtitulo));                           
                            s.addCell(new Label(j, k + 4, value, equipos));
                            s.setColumnView(0,40);
                            s.setColumnView(6,20);
                            s.setColumnView(7,20); 
                            s.setColumnView(8,20);
                            s.setColumnView(9,20); 
                            s.setColumnView(5,20); 
                            s.setColumnView(18,20); 
                        }                          
                    }

                }



                w.write();
    //Cerramos el WritableWorkbook y DataOutputStream
                w.close();
            }


//si todo sale bien salimos de aqui con un true :D
            return true;

        } catch (IOException | WriteException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error excel:" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

//Si llegamos hasta aqui algo salio mal
        return false;
    }
}

    

