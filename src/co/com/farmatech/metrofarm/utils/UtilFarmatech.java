/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.utils;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class UtilFarmatech {

    public UtilFarmatech() {
    }
    /**
     * It capitalizes the first letter of a word
     *
     * @param x
     * @return x with the first letter capitilized
     */
    /*Variable global*/
    public static ArrayList<String> permisos;

    /**
     * Devuelve un string con el formato ###'###.###
     *
     * aunque se recibe un string debe ser un numero
     *
     * @param number
     * @return
     */
    public String makeGroupingNumber(String number) {

        try {
            DecimalFormat formato = new DecimalFormat("####");
            number = formato.format(Double.valueOf(number));
            int posform = number.length() - 3;
            int sw = 0;
            while (posform > 0) {
                String leffnum = number.substring(0, posform);
                String rightnum = number.substring(posform);

                if (sw == 1) {
                    number = leffnum + "'" + rightnum;
                    sw = 0;
                } else {
                    number = leffnum + "," + rightnum;
                    sw = 1;
                }

                posform -= 3;
            }
        } catch (Exception e) {
            System.out.println("No se pudo dar formato debido que " + number + " no es un dato númerico");
            
        }

        return number;
    }

    /**
     * Metodo que alinea el texto a la derecha
     *
     * @param s
     * @param n
     * @return
     */
    public static String padRight(String cadena, int size) {
        return String.format("%1$-" + size + "s", cadena);

    }

    /**
     * Metodo que alinea el texto a la izquierda
     *
     * @param s
     * @param n
     * @return
     */
    public static String padLeft(String cadena, int size) {
        return String.format("%1$" + size + "s", cadena);

    }

    /**
     * Metodo qu limpia la grid
     *
     * @autor Andres Felipe Escobar Lopez afescobar@softcaribbean.com
     * @param dtm
     */
    public void limpiarTabla(DefaultTableModel dtm) {
        int filas = dtm.getRowCount();
        for (int i = 0; filas > i; i++) {
            dtm.removeRow(0);
        }
    }

    public static boolean escribirArchivo(byte[] fileBytes, String archivoDestino) {
        boolean correcto = false;
        try {
            OutputStream out = new FileOutputStream(archivoDestino);
            out.write(fileBytes);
            out.close();
            correcto = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return correcto;

    }

    /**
     * Metodo que me devuelve el dia que se compara a la fecha actual del
     * sistema
     *
     * @param parmetro
     * @return      *
     * public int obtenerParametroSistema(ParametrosSistema parametro){
     *
     * CmdParametroSistema cmd= new CmdParametroSistema(); ParametrosSistema
     * objeto= new ParametrosSistema(); int dias=0; try{
     * objeto=cmd.obtenerParametro(parametro); if(objeto!=null){
     * System.out.println("PARAMETRO-SISTEMA: "+objeto.getDsvalor()); dias=
     * Integer.parseInt(objeto.getDsvalor()); } }catch(CmdException ex){
     * ex.getStackTrace(); }
     *
     * return dias;
     *
     * }
     */
    public String capitalizar(String x) {

        if (x.length() > 0) {
            x = String.valueOf(x.charAt(0)).toUpperCase() + x.substring(1, x.length());
        }

        return x;
    }

    /**
     *
     *
     *
     * @param comp
     * @return
     */
    public boolean compararCaracteres(String comp) {

        String ilegales = "!¡|-°'[]{}´!¡@#,:$%^*()+\\<>\";'~";

        for (int i = 0; i < comp.length(); i++) {
            for (int j = 0; j < ilegales.length(); j++) {
                if (comp.charAt(i) == ilegales.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void anularCaracteres(java.awt.event.KeyEvent evt) {

        String ilegales = "'¿!¡|-°'[]{}´!@¡#,:$%^*()+\\<>\";'~";

        for (int j = 0; j < ilegales.length(); j++) {
            if (evt.getKeyChar() == ilegales.charAt(j)) {
                evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            }
        }

    }

    public String sustituirEnies(String x) {

        if (x.contains("ñ")) {
            return x.replace("ñ", "&n");
        }
        if (x.contains("Ñ")) {
            return x.replace("Ñ", "&N");
        }

        return x;
    }

    public DefaultTableModel vaciar(JTable table) {

        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int i = dtm.getRowCount();
        while (i > 0) {
            dtm.removeRow(i);
            i--;
        }

        return dtm;
    }

    public DefaultTableModel llenarTabla(ArrayList list) {
        DefaultTableModel dtm = new DefaultTableModel();

        for (int idx = 0; idx < list.size(); idx++) {
            dtm.addRow((Object[]) list.get(idx));
        }

        return dtm;
    }

    /**
     *
     * limpia todos los elementos de una clase, los cuales son JTextField y
     * JcomboBox text quedan en blanco y los comboBox quedan en el primer item
     * <br></br> <br></br> it cleans all the elements of the form, those
     * elements are JTextFields and JComboBoxes, the first one will be blank,
     * and the second one will select its first element
     *
     *
     * @param clase - the class type to know which elements must be cleaned
     * @param obj - the object, that must be the same class where the elements
     * to be cleaned have to be
     *
     */
    public void limpiarCampos(Class<?> clase, Object obj) {

        if (obj.getClass().getSuperclass().equals(JFrame.class) || obj.getClass().getSuperclass().equals(JInternalFrame.class)) {

            Field[] field = clase.getDeclaredFields();

            for (int idx = 0; idx < field.length; idx++) {

                field[idx].setAccessible(true);

                if (field[idx].getType().equals(JTextField.class)) {

                    try {
                        field[idx].setAccessible(true);
                        JTextField txt = (JTextField) (Object) field[idx].get(obj);
                        txt.setText("");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else if (field[idx].getType().equals(JComboBox.class)) {

                    try {
                        JComboBox cmb = (JComboBox) (Object) field[idx].get(obj);
                        cmb.setSelectedIndex(0);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }
    }

    /**
     * recibe el JTextField del cual se quiere controlar la longitud y la
     * longitud<br></br> deseada, recibe el evento, debe usarse KeyTyped para
     * que funcione correctamente
     *
     * @param evt-java.awt.event.KeyEvent
     * @param word-JTextField
     * @param longitud -int
     */
    public static void controlarLongitudText(java.awt.event.KeyEvent evt, JTextField word, int longitud) {

        if (word.getText().toString().length() >= longitud) {
            evt.consume();
        }
    }

    /**
     * cambia el foco, recibe el evento,y el próximo campo que recibirá el foco
     * <br></br>se debe usar con KeyPressed para que funcione correctamente
     *
     * @param evt -java.awt.event.KeyEvent
     * @param nextField -Component
     */
    public void cambiarFocusEnter(java.awt.event.KeyEvent evt, Component nextField) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            nextField.requestFocus();
        }
    }

    /**
     * Controla que el campo no tenga espacios dobles, y siempre esté en
     * mayusculas
     *
     * @param evt -java.awt.event.KeyEvent
     * @param word -JTextField
     */
    public void controlarCamposString(java.awt.event.KeyEvent evt, JTextField word) {
        int pos = word.getCaretPosition();
        int idx = evt.getKeyCode();
        if (idx < 37 || idx > 40) {
            if (idx != 8 && idx != 127) {
                word.setText(word.getText().toUpperCase());
                if (evt.getKeyChar() == ' ') {
                    while (word.getText().contains("  ")) {
                        word.setText(word.getText().replace("  ", " "));
                        pos--;
                    }

                }
            }
            word.setCaretPosition(pos);
        }
    }

    /**
     * Controla que solo haya 1 espacio entre palabras
     *
     * @param evt -java.awt.event.KeyEvent
     * @param word -JTextField
     *
     */
    public void anularEspaciosBlancos(java.awt.event.KeyEvent evt, JTextField word) {
        int pos = word.getCaretPosition();

        if (word.getText().contains(" ")) {
            word.setText(word.getText().replace(" ", ""));
            pos--;
        }

        word.setCaretPosition(pos);
    }

    /**
     * Anula las tildes y las diéresis, la función solo hace efecto con el
     * keyPressed
     *
     * @param evt -java.awt.event.KeyEvent
     */
    public void anularTildes(java.awt.event.KeyEvent evt) {
        if (evt.getKeyChar() == '´' || evt.getKeyChar() == '¨') {
            evt.consume();
        }
    }

    /**
     * valida si un dato es numérico o no
     *
     * @param obj -Object
     * @return true or false
     */
    public boolean isNumeric(Object obj) {

        try {
            Float.parseFloat(String.valueOf(obj));
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * valida si un dato es un número entero o no
     *
     * @param obj
     * @return true or false
     */
    public boolean isAnIntegerNumber(Object obj) {

        String num = obj.toString();

        try {
            Long.parseLong(num);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     *
     *
     *
     * @param fecha - Date
     * @return the diference in days
     */
    public long calcularDiferenciaFechasDias(Date fecha) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Calendar fechaactual = Calendar.getInstance();
        Calendar fechaanterior = Calendar.getInstance();
        fechaanterior.set(fecha.getYear() + 1900, fecha.getMonth(), fecha.getDate());
        long miliatual = fechaactual.getTimeInMillis();
        long milianterior = fechaanterior.getTimeInMillis();
        long diferecia = miliatual - milianterior;
        return diferecia / (24 * 60 * 60 * 1000);
    }

    /**
     * mueve un scroll con referencia al otro <br></br> It moves a scroll
     * depending on other one
     *
     * @param scrollautonomo -JScrollPane which is moved
     * @param scrolldependiente -JScrollPane which must be moved at the same
     * time
     */
    public void moverScrollDependiente(JScrollPane scrollautonomo, JScrollPane scrolldependiente) {
        Point point = scrollautonomo.getViewport().getViewPosition();
        Dimension dim1 = scrollautonomo.getViewport().getViewSize();
        Dimension dim2 = scrolldependiente.getViewport().getViewSize();
        float escalaX = 1;
        float escalaY = 1;
        if (dim1.width > dim2.width) {
            escalaX = (float) dim1.width / (float) dim2.width;
            escalaY = (float) dim1.height / (float) dim2.height;
            point.x /= escalaX;
            point.y /= escalaY;
            point.x -= 20;
        } else {
            escalaX = (float) dim2.width / (float) dim1.width;
            escalaY = (float) dim2.height / (float) dim1.height;
            point.x *= escalaX;
            point.y *= escalaY;
            point.x += 20;
        }
        scrolldependiente.getViewport().setViewPosition(point);

    }

    /**
     * Anula letras en un campo, usar preferiblemente con KeyTyped
     *
     *
     * @param evt -java.awt.event.KeyEvent
     */
    public void anularLetras(java.awt.event.KeyEvent evt) {
        int code = (int) evt.getKeyChar();

        if ((code >= 97 && code <= 122 || code >= 65 && code <= 90) || (code == 241 || code == 209)) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
        } else if (code >= 42 && code <= 47 || code == 521) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
        }

    }

    public void controlarFechas(JTextField word) {
        int pos = word.getCaretPosition();
        word.setText(word.getText().replace("-", ""));
        word.setText(word.getText().replace(".", ""));
        word.setText(word.getText().replace("//", "/"));
        word.setCaretPosition(pos--);
    }

    /*
     * Método que debuelve un booleano indicando que una cadena es numero o no
     */
    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            System.out.println("true");
            return true;
        } catch (NumberFormatException nfe) {
            System.out.println("false");
            return false;
        }
    }

    public void abrirFile(InputStream file,String name){
    
    try{
            File directorio = new File("temp"); //Creas un nuevo directorio a nivel de tu jar.
            directorio.mkdirs();
            directorio.setWritable(true);
            //copias la direccion
            String archivo = directorio.getCanonicalPath() + File.pathSeparator +name;
            //nuevo archivo en esa direccion
            File temp = new File(archivo);
            InputStream is = file;//this.getClass().getResourceAsStream("/microdi/docs/erfc.pdf");
            FileOutputStream archivoDestino = new FileOutputStream(temp);
            FileWriter fw = new FileWriter(temp);
            byte[] buffer = new byte[512*1024];
            //lees el archivo hasta que se acabe...
            int nbLectura;
            while ((nbLectura = is.read(buffer)) != -1)
                archivoDestino.write(buffer, 0, nbLectura);
            //cierras el archivo,el inputS y el FileW
            fw.close();
            archivoDestino.close();
            is.close();
            //abres el archivo temporal
            Desktop.getDesktop().open(temp);
        } catch (IOException ex) {
            System.out.println("Problema abriendo el pdf de erfc");
        }
    }
    
    public static File crearFile(InputStream file,String name){
    File temp=null;
    try{
            File directorio = new File("temp"); //Creas un nuevo directorio a nivel de tu jar.
            directorio.mkdirs();
            directorio.setWritable(true);
            //copias la direccion
            String archivo = directorio.getCanonicalPath() + File.pathSeparator +name;
            //nuevo archivo en esa direccion
            temp = new File(archivo);
            InputStream is = file;//this.getClass().getResourceAsStream("/microdi/docs/erfc.pdf");
            FileOutputStream archivoDestino = new FileOutputStream(temp);
            FileWriter fw = new FileWriter(temp);
            byte[] buffer = new byte[512*1024];
            //lees el archivo hasta que se acabe...
            int nbLectura;
            while ((nbLectura = is.read(buffer)) != -1)
                archivoDestino.write(buffer, 0, nbLectura);
            //cierras el archivo,el inputS y el FileW
            fw.close();
            archivoDestino.close();
            is.close();
            return temp;
        } catch (IOException ex) {
            System.out.println("Problema abriendo el pdf de erfc");
        }
    return temp;
    }    
    
}
