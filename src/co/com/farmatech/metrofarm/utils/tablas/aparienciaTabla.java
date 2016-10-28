/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.utils.tablas;

/**
 *
 * @author Bryan
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class aparienciaTabla extends DefaultTableCellRenderer {



    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
    
        if (table.getValueAt(row, 1)==null) {
            setBackground(new Color(Integer.parseInt("EF2F24", 16)));

        } // SI NO ES ACTIVO ENTONCES COLOR ROJO
        else {
            setBackground(Color.white);


        }
        


  



        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;

    }
    
    
    
}
