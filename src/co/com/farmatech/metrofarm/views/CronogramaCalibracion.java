/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.views;

import co.com.farmatech.metrofarm.cmd.CmdCalibracion;
import co.com.farmatech.metrofarm.resources.Propiedades;
import co.com.farmatech.metrofarm.utils.ExcelExportCalibration;
import co.com.farmatech.metrofarm.utils.Formu_Panel;
import co.com.farmatech.metrofarm.utils.UtilFarmatech;
import co.com.farmatech.metrofarm.utils.buttons.BotonesMenu;
import co.com.farmatech.metrofarm.utils.tablas.AparienciaCronogramaCalibracion;
import co.janker.dto.objresponse.ObjectResponse;
import co.jankins.psf.common.global.GlobalUtils;
import com.co.farmatech.metrofarm.dto.DtoCalibracion;
import com.co.farmatech.metrofarm.dto.DtoEquipo;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultTreeModel;
import ventana.bloqueo.VentanaBloqueo;

/**
 *
 * @author bestrada
 */
public class CronogramaCalibracion extends JFrame implements ActionListener {

    private Object[][] DetallesIdentificacion = new Object[0][0];
    private Object[] ColumnasIdentificacion = {"EQUIPO/INSTRUMENTO", "MARCA", "MODELO", "SERIE", "CÓDIGO INTERNO", "UBICACIÓN", "FRECUENCIA", "FECHA REGISTRO", "PROGRAMADA", "EJECUTADO", "ENÉRO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE", "Objeto"};
    private DefaultTableModel dataModel = new DefaultTableModel(DetallesIdentificacion, ColumnasIdentificacion);
    Formu_Panel PanelGeneral1;
    JLabel lblAno;
    JTextField txtAno;
    DefaultTreeModel modelo = null;
    JScrollPane scrollPane = new JScrollPane();
    public static JTable TablaCrono;
    JScrollPane PanelDatos;
    ImageIcon Icofondo;
    Font TLetra = new Font("Arial", Font.ITALIC, 15);
    String ruta = "co/com/farmatech/metrofarm/resources/imagenes/";
    JButton btnFiltar = new JButton("Filtrar Cronograma", new ImageIcon(getClass().getClassLoader().getResource(ruta + "buscar.gif")));
    JButton btnTodos = new JButton("Mostrar Todos", new ImageIcon(getClass().getClassLoader().getResource(ruta + "buscar.gif")));
    private JMenuBar Menu;
    private JMenu menu1, menu2;
    private JMenuItem m11, m21;
    JLabel Codigo;
    Font TCodigo = new Font("Arial", Font.ITALIC, 35);
    public BotonesMenu btnDescargar = new BotonesMenu(new ImageIcon(getClass().getClassLoader().getResource(ruta + "excel.png")), new ImageIcon(getClass().getClassLoader().getResource(ruta + "excel.png")));
    JFileChooser elegir;
    JScrollPane scroll;
    UtilFarmatech util = new UtilFarmatech();
    CmdCalibracion cmd;
    VentanaBloqueo ventana = new VentanaBloqueo();

    public CronogramaCalibracion() {

        super("Cronograma de calibración METROFARM -v1");

        ((JPanel) getContentPane()).setOpaque(false);  // para que muestre la imagen
        this.setSize(1366, 768);

        setResizable(false);
        getContentPane().setLayout(null);
        setBackground(Color.white);
        setVisible(true);
        setLayout(null);
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource(ruta + "FARMA.png")).getImage());
        setDefaultCloseOperation(0);
        setLocationRelativeTo(null);


        Icofondo = new ImageIcon(getClass().getClassLoader().getResource(ruta + "fondotablas.png"));
        cmd = new CmdCalibracion();
        elegir = new JFileChooser();

        Menu = new JMenuBar();
        menu1 = new JMenu("Archivo");
        menu2 = new JMenu("Cronograma 2012");
        Menu.add(menu1);
        Menu.add(menu2);

        m11 = new JMenuItem("Regresar");
        m11.addActionListener(this);
        m11.setIcon(new ImageIcon(getClass().getClassLoader().getResource(ruta + "atras.png")));
        menu1.add(m11);

        m21 = new JMenuItem("Visualizar");
        m21.addActionListener(this);
        m21.setIcon(new ImageIcon(getClass().getClassLoader().getResource(ruta + "excel2012.png")));
        menu2.add(m21);

        Codigo = new JLabel("F-228-1");
        Codigo.setBounds(1150, 620, 150, 100);
        Codigo.setFont(TCodigo);
        Codigo.setForeground(Color.red);
        Codigo.setBackground(Color.white);
        Codigo.setVisible(true);

        lblAno = new JLabel("Cronograma del año");
        lblAno.setBounds(450, 630, 150, 20);
        lblAno.setFont(TLetra);
        lblAno.setForeground(Color.black);
        lblAno.setVisible(true);

        txtAno = new JTextField("");
        txtAno.setBounds(600, 630, 100, 20);
        txtAno.setFont(TLetra);
        txtAno.setForeground(Color.black);
        txtAno.setVisible(true);
        txtAno.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int limite = 4;
                char caracter = e.getKeyChar();
                if (((caracter < '0')
                        || (caracter > '9'))
                        && (caracter != KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    e.consume();
                    JOptionPane.showMessageDialog(null, "No se permiten letras en este campo", "METROFARM", JOptionPane.ERROR_MESSAGE);
                }


                if (txtAno.getText().length() == limite) {
                    e.consume();
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Este campo solo puede tener como maximo 4 caracteres", "METROFARM", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnFiltar.setBounds(720, 627, 170, 25);
        btnFiltar.setVisible(true);

        btnDescargar.setBounds(900, 627, 30, 30);
        btnDescargar.setVisible(true);
        btnDescargar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDescargar.setToolTipText("Descargar en formato Excel");

        btnTodos.setBounds(100, 627, 170, 25);
        btnTodos.setVisible(true);

        PanelGeneral1 = new Formu_Panel(Icofondo);
        scroll = new JScrollPane(PanelGeneral1);
        scroll.setBounds(0, 0, 1360, 720);/// tamño escroll del panel dentro del jframe medida hancha 1360
        scroll.setViewportView(PanelGeneral1);
        scroll.getViewport().setView(PanelGeneral1);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.repaint();
        //scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        PanelGeneral1.setBorder(BorderFactory.createTitledBorder(""));
        PanelGeneral1.setBackground(Color.black);
        PanelGeneral1.setForeground(Color.red);
        //PanelGeneral1.setBounds(0, 0,100,100);
        PanelGeneral1.setPreferredSize(new Dimension(2000, 768));// dimencion real del panel general tamaño 2050
        PanelGeneral1.setLayout(null);



        //scroll.add(PanelGeneral1);
        //PanelGeneral1.setLayout(null);


        TablaCrono = new JTable(dataModel) {
            @Override
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        TablaCrono.getTableHeader().setReorderingAllowed(false);
        TablaCrono.setBounds(0, 0, 100, 720);// tamaño de la tabla que es contenida por el panel gerenarl1
        PanelDatos = new JScrollPane(TablaCrono);
        PanelDatos.setBounds(0, 0, 2000, 600);// tamaño del scroll que contiene la tabla 
        PanelDatos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        PanelDatos.repaint();
        //PanelDatos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(dataModel);
        TablaCrono.setRowSorter(elQueOrdena);
        TablaCrono.repaint();

        PanelGeneral1.add(PanelDatos);
        PanelGeneral1.add(lblAno);
        PanelGeneral1.add(txtAno);
        PanelGeneral1.add(btnFiltar);
        PanelGeneral1.add(btnTodos);
        PanelGeneral1.add(Codigo);
        PanelGeneral1.add(btnDescargar);

        btnFiltar.addActionListener(this);
        btnTodos.addActionListener(this);
        btnDescargar.addActionListener(this);

        setJMenuBar(Menu);

        PanelGeneral1.setVisible(true);
        PanelGeneral1.repaint();
        this.getContentPane().add(scroll);
        ocultarColumna("Objeto");
        TablaCrono.getColumnModel().getColumn(10).setCellRenderer(new AparienciaCronogramaCalibracion());
        TablaCrono.getColumnModel().getColumn(11).setCellRenderer(new AparienciaCronogramaCalibracion());
        TablaCrono.getColumnModel().getColumn(12).setCellRenderer(new AparienciaCronogramaCalibracion());
        TablaCrono.getColumnModel().getColumn(13).setCellRenderer(new AparienciaCronogramaCalibracion());
        TablaCrono.getColumnModel().getColumn(14).setCellRenderer(new AparienciaCronogramaCalibracion());
        TablaCrono.getColumnModel().getColumn(15).setCellRenderer(new AparienciaCronogramaCalibracion());
        TablaCrono.getColumnModel().getColumn(16).setCellRenderer(new AparienciaCronogramaCalibracion());
        TablaCrono.getColumnModel().getColumn(17).setCellRenderer(new AparienciaCronogramaCalibracion());
        TablaCrono.getColumnModel().getColumn(18).setCellRenderer(new AparienciaCronogramaCalibracion());
        TablaCrono.getColumnModel().getColumn(19).setCellRenderer(new AparienciaCronogramaCalibracion());
        TablaCrono.getColumnModel().getColumn(20).setCellRenderer(new AparienciaCronogramaCalibracion());
        TablaCrono.getColumnModel().getColumn(21).setCellRenderer(new AparienciaCronogramaCalibracion());

        ventana.bloquear(this);
        SwingWorker worker;
        worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                vaciar();
                llenarDatos();
                ventana.desbloquear();

                return null;
            }
        };
        worker.execute();

        this.repaint();


    }

    public void llenarDatos() {

        ObjectResponse response = cmd.findCalibrations(new DtoEquipo(),null);
        ArrayList<DtoCalibracion> calibraciones=(ArrayList<DtoCalibracion>)response.getRecords();
        setDataModel(getCronCalibraciones(calibraciones, getDataModel()));

    }

    private void vaciar() {
        int i = getDataModel().getRowCount();
        while (i > 0) {
            getDataModel().removeRow(0);
            i--;
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
        if (e.getSource() == btnFiltar) {
            if (txtAno.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un año a filtrar", "METROFARM", JOptionPane.ERROR_MESSAGE);
            }
            if (txtAno.getText().length() == 4) {
                vaciar();
                String Year;
                Year = txtAno.getText();

                ObjectResponse response = cmd.findCalibrations(new DtoEquipo(),null);
                 ArrayList<DtoCalibracion> calibraciones=(ArrayList<DtoCalibracion>)response.getRecords();
                setDataModel(getCronCalibraciones(calibraciones, getDataModel(), Year));

            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar un tamaño minimo de 4 caracteres", "METROFARM", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == btnTodos) {
            vaciar();
            llenarDatos();

        }

        if (e.getSource() == btnDescargar) {

            if (TablaCrono.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "No hay dato en la tabla para exportar", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
            } else {

                FileNameExtensionFilter filtrer = new FileNameExtensionFilter("Archivos de excel", "xls");
                elegir.setFileFilter(filtrer);
                elegir.setDialogTitle("Guardar archivo");
                elegir.setMultiSelectionEnabled(false);
                elegir.setAcceptAllFileFilterUsed(false);


                if (elegir.showSaveDialog(null) == elegir.APPROVE_OPTION) {
                    List<JTable> tb = new ArrayList<>();
                    List<String> nom = new ArrayList<>();
                    tb.add(TablaCrono);
                    nom.add("Cronograma");

                    String file = elegir.getSelectedFile().toString().concat(".xls");
                    try {
                        ExcelExportCalibration excel = new ExcelExportCalibration(tb, new File(file), nom);
                        if (excel.export()) {
                            JOptionPane.showMessageDialog(null, "Los datos fueron exportados a excel", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Hubo un error :" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    System.out.println("Carga cancelada por el usuario");
                }
            }
        }

        if (e.getSource() == m11) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea regresar?", "METROFARM", JOptionPane.YES_NO_OPTION);
            switch (respuesta) {
                case JOptionPane.YES_OPTION:
                    this.dispose();
                    Cronogramas men = new Cronogramas();
                    break;
                case JOptionPane.NO_OPTION:
                    break;

            }
        }

        if (e.getSource() == m21) {
            InputStream in = Propiedades.class.getResourceAsStream("METROLOGIA2012.xlsx");
            util.abrirFile(in, "METROLOGIA2012.xlsx");

        }
    }

    public DefaultTableModel getCronCalibraciones(List<DtoCalibracion> calibraciones, DefaultTableModel model) {
        for (DtoCalibracion c : calibraciones) {
            Object nuevo[] = {c.getEquipo().getNombre(), c.getEquipo().getMarca(), c.getEquipo().getModelo(), c.getEquipo().getSerie(), c.getEquipo().getCodigoInv(), c.getEquipo().getUbicacion(), c.getEquipo().getFrecCalibracion() == 1 ? c.getEquipo().getFrecCalibracion() + " Mes" : c.getEquipo().getFrecCalibracion() + " Meses", GlobalUtils.getDateFormatted(c.getFechaRegistro()), GlobalUtils.getDateFormatted(c.getProximaCalibracion()), c.getFechaEjecutada() != null ? GlobalUtils.getDateFormatted(c.getFechaEjecutada()) : "Sin Registro", new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), c};
            model.addRow(nuevo);

        }
        return model;

    }

    public DefaultTableModel getCronCalibraciones(List<DtoCalibracion> calibraciones, DefaultTableModel model, String Year) {
        for (DtoCalibracion c : calibraciones) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(c.getProximaCalibracion());
            if ((cal.get(Calendar.YEAR) + "").equals(Year)) {
                Object nuevo[] = {c.getEquipo().getNombre(), c.getEquipo().getMarca(), c.getEquipo().getModelo(), c.getEquipo().getSerie(), c.getEquipo().getCodigoInv(), c.getEquipo().getUbicacion(), c.getEquipo().getFrecCalibracion() == 1 ? c.getEquipo().getFrecCalibracion() + " Mes" : c.getEquipo().getFrecCalibracion() + " Meses", GlobalUtils.getDateFormatted(c.getFechaRegistro()), GlobalUtils.getDateFormatted(c.getProximaCalibracion()), c.getFechaEjecutada() != null ? GlobalUtils.getDateFormatted(c.getFechaEjecutada()) : "Sin Registro", new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), c};
                model.addRow(nuevo);
            }


        }
        return model;

    }

    public final void ocultarColumna(String column) {
        TableColumn tc = TablaCrono.getColumn(column); //Obtienes la columna
        tc.setPreferredWidth(0); //Y le das un tamaño
        tc.setMinWidth(0);
        tc.setMaxWidth(0);
    }
}
