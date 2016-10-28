/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.views;

import co.com.farmatech.metrofarm.cmd.CmdChangeRegister;
import co.com.farmatech.metrofarm.utils.Fondo_Registro;
import co.com.farmatech.metrofarm.utils.Formu_Fondo;
import co.com.farmatech.metrofarm.utils.textfield_icon;
import co.jankins.psf.common.global.GlobalUtils;
import com.co.farmatech.metrofarm.dto.DtoRegistroCambios;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author bestrada
 */
public class HistorialRegistroCambio extends JFrame implements ActionListener, MouseListener {

    public JFrame Ventana;
    JTable TablaHistorial;
    Formu_Fondo PanelGeneral1;
    ImageIcon Icofondo;
    ImageIcon Icofondo2;
    JPanel Panelbotones;
    JScrollPane PanelHistorial;
    private JMenuBar Menu;
    private JMenu menu1;
    private JMenuItem m11;
    Image IcoCabecera;
    textfield_icon txtBuscar;
    Font TLetra = new Font("Calibri", Font.PLAIN, 12);
    Font fecha = new Font("Arial", Font.PLAIN, 14);
    Font boton = new Font("Century Gothic", Font.PLAIN, 18);
    private JButton btnvisualizar;
    private Object[][] DetallesIdentificacion = new Object[0][0];
    private Object[] ColumnasIdentificacion = {"Solicitud No", "Codigo de Equipo/Instrumento", "Nombre de Equipo/Instrumento", "Fecha", "Solicitado", "Área", "Prioridad", "Tipo de documento", "Clasificación", "tipo de cambio", "Objeto"};
    private DefaultTableModel dataModel = new DefaultTableModel(DetallesIdentificacion, ColumnasIdentificacion);
    String Codigoseleccionado;
    private TableRowSorter filtro;
    String ruta = "co/com/farmatech/metrofarm/resources/imagenes/";
    CmdChangeRegister cmd;

    public HistorialRegistroCambio() {
        this.btnvisualizar = new JButton("visualizar documento", new ImageIcon(getClass().getClassLoader().getResource(ruta + "ver.png")));
        cmd = new CmdChangeRegister();

        Menu = new JMenuBar();
        menu1 = new JMenu("Archivo");
        Menu.add(menu1);

        m11 = new JMenuItem("Salir del historial");
        m11.setIcon(new ImageIcon(getClass().getClassLoader().getResource(ruta + "exit_on_close.png")));
        menu1.add(m11);
        m11.addActionListener(this);



        setIconImage(new ImageIcon(getClass().getClassLoader().getResource(ruta + "FARMA.png")).getImage());

        Icofondo = new ImageIcon(getClass().getClassLoader().getResource(ruta + "fondomenu3.png"));
        Icofondo2 = new ImageIcon(getClass().getClassLoader().getResource(ruta + "textura.png"));

        btnvisualizar.setBounds(15, 55, 200, 25);
        btnvisualizar.setBackground(getBackground());
        btnvisualizar.addActionListener(this);
        btnvisualizar.setVisible(false);

        txtBuscar = new textfield_icon();
        txtBuscar.setBounds(15, 15, 200, 26);
        txtBuscar.setFont(TLetra);
        txtBuscar.setForeground(Color.gray);
        txtBuscar.setVisible(true);
        txtBuscar.setText("Buscar por fecha");
        txtBuscar.addMouseListener(this);
        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                txtBuscar.addKeyListener(new KeyAdapter() {
                    public void keyReleased(final KeyEvent e) {
                        filtroFecha();
                    }
                });

                filtro = new TableRowSorter(TablaHistorial.getModel());
                TablaHistorial.setRowSorter(filtro);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });


        TablaHistorial = new JTable(dataModel) {
            @Override
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        PanelHistorial = new JScrollPane(TablaHistorial);

        PanelHistorial.setBounds(
                250, 3, 635, 540);
        PanelHistorial.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        PanelHistorial.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        PanelHistorial.setViewportView(TablaHistorial);

        TablaHistorial.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(dataModel);

        TablaHistorial.setRowSorter(elQueOrdena);
        //TablaDatos.setDefaultRenderer(Object.class, new aparienciaCalibrcion());

        TablaHistorial.addMouseListener(
                this);

        TablaHistorial.getTableHeader().setReorderingAllowed(false);

        Panelbotones = new Fondo_Registro(Icofondo2);

        Panelbotones.setBorder(BorderFactory.createTitledBorder(""));
        Panelbotones.setBackground(Color.black);

        Panelbotones.setForeground(Color.red);

        Panelbotones.setBounds(
                0, 3, 250, 763);
        Panelbotones.setLayout(
                null);

        Panelbotones.add(txtBuscar);

        Panelbotones.add(btnvisualizar);
        PanelGeneral1 = new Formu_Fondo(Icofondo);

        PanelGeneral1.setBorder(BorderFactory.createTitledBorder(""));
        //PanelGeneral1.add(fondo);
        PanelGeneral1.setBackground(Color.black);

        PanelGeneral1.setForeground(Color.red);

        PanelGeneral1.reshape(
                0, 0, 1366, 768);
        PanelGeneral1.setLayout(
                null);

        PanelGeneral1.setVisible(
                true);
        this.getContentPane()
                .add(PanelGeneral1);

        PanelGeneral1.add(Panelbotones);

        PanelGeneral1.add(PanelHistorial);
        Ventana = new JFrame("Historial de registro de cambios METROFARM -v1");

        Ventana.setLayout(
                null);
        Ventana.add(PanelGeneral1);

        Ventana.setSize(
                900, 600);
        //Ventana.setLocationRelativeTo(null);
        Ventana.setIconImage(IcoCabecera);

        Ventana.setBackground(Color.pink);

        Ventana.setResizable(
                false);

        Ventana.show();

        Ventana.setVisible(
                true);
        Ventana.setJMenuBar(Menu);

        Ventana.setIconImage(
                new ImageIcon(getClass().getClassLoader().getResource(ruta + "FARMA.png")).getImage());
        Ventana.setDefaultCloseOperation(
                0);
        Ventana.toFront();
        ocultarColumna("Objeto");


        vaciar();
        llenarDatos();

    }

    public void filtroFecha() {

        String cadena = txtBuscar.getText().toString();
        try {
            filtro.setRowFilter(RowFilter.regexFilter(cadena, 3));
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    public void llenarDatos() {

        try {
            ArrayList<DtoRegistroCambios> cambios = new ArrayList<DtoRegistroCambios>();
            cambios = cmd.listChangeRegister(new DtoRegistroCambios());
            setDataModel(getRegistrosCambio(cambios, dataModel));
            Ventana.toFront();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "MetroFarm", JOptionPane.ERROR_MESSAGE);
        }
    }

    public DefaultTableModel getRegistrosCambio(ArrayList<DtoRegistroCambios> result, DefaultTableModel model) {

        if (result.isEmpty()) {
            return null;
        }
        for (DtoRegistroCambios c : result) {
            Object nuevo[] = {c.getCodigo(), c.getEquipoRegistro().getCodigoInv(), c.getEquipoRegistro().getNombre(), GlobalUtils.getDateFormatted(c.getFecha()), c.getSolicitado(), c.getArea(), c.getPrioridad(), c.getTipodoc(), c.getClasifcambio(), c.getTipocambio(),c};
            model.addRow(nuevo);
        }
        return model;
    }

    public void ocultarColumna(String column) {
        TableColumn tc = TablaHistorial.getColumn(column); //Obtienes la columna
        tc.setPreferredWidth(0); //Y le das un tamaño
        tc.setMinWidth(0);
        tc.setMaxWidth(0);
    }

    private void vaciar() {
        if (TablaHistorial.getRowCount() != 0) {
            int i = getDataModel().getRowCount();
            while (i > 0) {
                getDataModel().removeRow(0);
                i--;
            }
        }

    }

    public void setDataModel(DefaultTableModel dataModel) {
        this.dataModel = dataModel;
    }

    public DefaultTableModel getDataModel() {
        return dataModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == m11) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea regresar?", "MetroFarm", JOptionPane.YES_NO_OPTION);
            switch (respuesta) {
                case JOptionPane.YES_OPTION:
                    Ventana.dispose();
                    break;
                case JOptionPane.NO_OPTION:
                    break;

            }

        }

        if (e.getSource() == btnvisualizar) {
            try {
                if (TablaHistorial.getSelectedRow() > -1) {
                    VisualizarCambio viCambio = new VisualizarCambio((DtoRegistroCambios) TablaHistorial.getValueAt(TablaHistorial.getSelectedRow(), 10));
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione un registro de la tabla", "MetroFarm v1.0", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception exe) {
                JOptionPane.showMessageDialog(null, "Hubo un error :" + exe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }


    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == txtBuscar) {
            if (txtBuscar.getText().equals("Buscar por fecha")) {
                txtBuscar.setText("");
                txtBuscar.setFont(fecha);
                txtBuscar.setForeground(Color.BLACK);

            }
        }

        if (e.getSource() == TablaHistorial) {

            int fila = 0;
            try {
                fila = TablaHistorial.getSelectedRow();
                TablaHistorial.setSelectionBackground(new Color(Integer.parseInt("015289", 16)));
                if (fila > -1) {

                    Codigoseleccionado = String.valueOf(TablaHistorial.getValueAt(fila, 0));
                    btnvisualizar.setVisible(true);

                }


            } catch (Exception exe) {
                JOptionPane.showMessageDialog(null, "Hubo un error :" + exe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == txtBuscar) {
            btnvisualizar.setVisible(false);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == txtBuscar) {
            if (txtBuscar.getText().equals("")) {
                txtBuscar.setText("Buscar por fecha");
                txtBuscar.setFont(TLetra);
                txtBuscar.setForeground(Color.gray);
                if (TablaHistorial.getRowCount() != 0) {
                    vaciar();
                }
                llenarDatos();
            }





        }


    }
}
