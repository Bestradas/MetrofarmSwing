/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.tree;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;

/**
 *
 * @author bestrada
 */
public class Editor extends AbstractCellEditor
        implements TreeCellEditor, ActionListener {

    JCheckBox check;
    JLabel label;
    JTextField textField;
    Plantilla clsPlantilla;
    JComponent editedComponent;
    JPanel panel;
    Font TArbol = new Font("Arial", Font.PLAIN, 5);

    public Editor() {
        check = new JCheckBox();
        check.addActionListener(this);
        check.setBackground(UIManager.getColor("Tree.background"));
        check.setBorder(null);
        check.setSize(200, 50);
        label = new JLabel();
        label.setFont(UIManager.getFont("Tree.font"));
        textField = new JTextField();
        textField.addActionListener(this);
        textField.setBackground(UIManager.getColor("Tree.background"));
        textField.setBorder(null);
        panel = new JPanel();


        panel.setOpaque(false);
        panel.add(check);
        panel.add(label);
        panel.add(textField);
    }

    @Override
    public Object getCellEditorValue() {
        if (editedComponent == textField) {
            clsPlantilla.setNombre(textField.getText());
        } else {
            clsPlantilla.setValor(check.isSelected());
        }
        return clsPlantilla;
    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        clsPlantilla = (Plantilla) node.getUserObject();
        check.setSelected(clsPlantilla.getValor());
        label.setIcon(UIManager.getIcon("Tree.openedIcon"));
        textField.setText(clsPlantilla.getNombre());
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        editedComponent = (JComponent) e.getSource();
        super.stopCellEditing();
    }
}