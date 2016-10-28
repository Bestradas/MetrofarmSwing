/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.utils;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author bestrada
 */
 public class ItemRenderer extends BasicComboBoxRenderer
    {
        @Override
        public Component getListCellRendererComponent(
             JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

            if (value != null)
            {
                ComboItem item = (ComboItem)value;
                setText( item.getDescription() );
            }

            if (index == -1)
            {
                ComboItem item = (ComboItem)value;
                setText( (item!=null?item.getDescription()+"":"") );
            }


            return this;
        }
        
        
    }