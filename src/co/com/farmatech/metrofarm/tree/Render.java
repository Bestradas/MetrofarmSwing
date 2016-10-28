/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.tree;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

/**
 *
 * @author bestrada
 */
public class Render implements TreeCellRenderer {

    JCheckBox check;
    JLabel label;
    JPanel panel;
//    Font TArbol = new Font("Arial", Font.PLAIN, 5);
    
    //constructor por deecto
    public Render() {
        check = new JCheckBox();
        check.setBackground(UIManager.getColor("Tree.background"));
        check.setBorder(BorderFactory.createLineBorder(Color.yellow));
        check.setSize(200, 50);
        label = new JLabel();
        label.setFont(UIManager.getFont("Tree.font"));
        panel = new JPanel();
        panel.setOpaque(false);
        panel.add(check);
        panel.add(label);
    }
    

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        //el objecto se transforma DefaultMutableTreeNode
        DefaultMutableTreeNode nodo=(DefaultMutableTreeNode)value;
        
        //accedo a los atributos de la clase plantilla
        //Esta parte es chevre por que puedes meter una clase en cada nodo del árbol
        //y cuando se selecciona se puede obtener todo lo referente a este nodo.
       //Observar la clase Plantilla y mira lo que contiene
        Plantilla clsPlantilla=(Plantilla)nodo.getUserObject();
        check.setSelected(clsPlantilla.getValor());
        label.setText(clsPlantilla.getNombre());
        label.setIcon(UIManager.getIcon("Tree.closedIcon"));
        return panel;
    }
    
}
