/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.utils.tablas;

import com.co.farmatech.metrofarm.dto.DtoCalibracion;
import com.co.farmatech.metrofarm.dto.DtoProgramCalificacion;
import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author bestrada
 */
public class AparienciaCronogramaCalificacion extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table,Object value, boolean selected,boolean focused,int row,int column) {

        // Only for specific cell

        //Se verifica fecha de programacion, la fecha de ejecucion y se evalua el cumplimiento
        if(value instanceof JLabel){
        DtoProgramCalificacion calificacion=(DtoProgramCalificacion)table.getValueAt(row, 23);
        if(calificacion.getCumplimiento()==1){          
            Calendar calCumplio=Calendar.getInstance();
            calCumplio.setTime(calificacion.getFechaEjecucion());
            
            if(column==calCumplio.get(Calendar.MONTH)+11){
                JLabel label=(JLabel)value;
                label.setBackground(Color.blue);
                label.setText("Cumplido");
                return label;
            }
        }else{
            //Programado
            Calendar calActual=Calendar.getInstance();
            calActual.setTime(new Date());
            
            Calendar cal=Calendar.getInstance();
            cal.setTime(calificacion.getFechaProxima());
            
            if(column==cal.get(Calendar.MONTH)+11){
                JLabel label=(JLabel)value;
                label.setText("Programado");
                if(cal.get(Calendar.MONTH)==calActual.get(Calendar.MONTH) && calificacion.getFechaEjecucion()==null){
                    label.setBackground(Color.pink);
                }else{
                    label.setBackground(Color.gray);
                }
                return label;
            }
            
            
            if(calificacion.getFechaEjecucion()!=null){
            Calendar calEjecutado=Calendar.getInstance();
            calEjecutado.setTime(calificacion.getFechaEjecucion());
            
            
            if(column==calEjecutado.get(Calendar.MONTH)+11){
                JLabel label=(JLabel)value;
                    label.setBackground(Color.gray);
                    label.setText("Ejecutado");
                    return label;
                }
            
            
            }
            
        }
        return (JLabel)value;
        }else{
            return super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        }
        
       
    }
}

