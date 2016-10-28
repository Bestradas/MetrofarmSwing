/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.views;

import co.com.farmatech.metrofarm.cmd.CmdCalificacion;
import co.com.farmatech.metrofarm.utils.ExcelExportCalification;
import co.com.farmatech.metrofarm.utils.Formu_Panel;
import co.com.farmatech.metrofarm.utils.buttons.BotonesMenu;
import co.com.farmatech.metrofarm.utils.tablas.AparienciaCronogramaCalibracion;
import co.com.farmatech.metrofarm.utils.tablas.AparienciaCronogramaCalificacion;
import static co.com.farmatech.metrofarm.views.CronogramaCalibracion.TablaCrono;
import co.jankins.psf.common.enums.TipoCalificacion;
import co.jankins.psf.common.global.GlobalUtils;
import com.co.farmatech.metrofarm.dto.DtoEquipo;
import com.co.farmatech.metrofarm.dto.DtoProgramCalificacion;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
public class CronogramaCalificacion extends JFrame implements ActionListener, MouseListener {

    private Object[][] DetallesIdentificacion = new Object[0][0];
    private Object[] ColumnasIdentificacion = {"EQUIPO/INSTRUMENTO", "MARCA", "MODELO", "SERIE", "CÓDIGO INTERNO", "UBICACIÓN", "FRECUENCIA CALIFICACIÓN", "FECHA DE REGISTRO", "PROGRAMADA", "FECHA DE EJECUCIÓN", "TIPO DE CALIFICACION", "ENÉRO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE", "Objeto"};
    private DefaultTableModel dataModel = new DefaultTableModel(DetallesIdentificacion, ColumnasIdentificacion);
    private Object[][] DetallesDesempeño = new Object[0][0];
    private Object[] ColumnasDesempeño = {"EQUIPO/INSTRUMENTO", "MARCA", "MODELO", "SERIE", "CÓDIGO INTERNO", "UBICACIÓN", "FRECUENCIA CALIFICACIÓN", "FECHA DE REGISTRO", "PROGRAMADA", "FECHA DE EJECUCIÓN", "TIPO DE CALIFICACION", "ENÉRO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE", "Objeto"};
    private DefaultTableModel dataModel2 = new DefaultTableModel(DetallesDesempeño, ColumnasDesempeño);
    Formu_Panel PanelGeneral1;
    JPanel PanelTablaOpe, PanelTablaDes;
    JLabel lblAno;
    JTextField txtAno;
    DefaultTreeModel modelo = null;
    JScrollPane scrollPane = new JScrollPane();
    static JTable TablaCronoCalifOPe, TablaCronoCalifDes;
    JScrollPane PanelDatos, PanelDatos2;
    ImageIcon Icofondo;
    Font TLetra = new Font("Arial", Font.ITALIC, 15);
    String ruta = "co/com/farmatech/metrofarm/resources/imagenes/";
    JButton btnFiltar = new JButton("Filtrar cronograma", new ImageIcon(getClass().getClassLoader().getResource(ruta + "buscar.gif")));
    private JMenuBar Menu;
    private JMenu menu1;
    private JMenuItem m11;
    JLabel Codigo;
    Font TCodigo = new Font("Arial", Font.ITALIC, 35);
    public BotonesMenu btnDescargar = new BotonesMenu(new ImageIcon(getClass().getClassLoader().getResource(ruta + "excel.png")), new ImageIcon(getClass().getClassLoader().getResource(ruta + "excel.png")));
    JFileChooser elegir;
//    ExcelTableExporter Exporterexcel;
    JCheckBox Operacion, Desempeño;
    JScrollPane scroll;
    CmdCalificacion cmd;
    VentanaBloqueo ventana = new VentanaBloqueo();

    public CronogramaCalificacion() throws ParseException {
        super("Cronograma de calificación METROFARM -v1");

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
        elegir = new JFileChooser();
        cmd = new CmdCalificacion();

        Menu = new JMenuBar();
        menu1 = new JMenu("Archivo");
        Menu.add(menu1);

        m11 = new JMenuItem("Regresar");
        m11.addActionListener(this);
        m11.setIcon(new ImageIcon(getClass().getClassLoader().getResource(ruta + "atras.png")));
        menu1.add(m11);

        Codigo = new JLabel("F-171-2");
        Codigo.setBounds(1050, 620, 150, 100);
        Codigo.setFont(TCodigo);
        Codigo.setForeground(Color.red);
        Codigo.setBackground(Color.white);
        Codigo.setVisible(true);

        lblAno = new JLabel("Cronograma del año");
        lblAno.setBounds(500, 630, 150, 20);
        lblAno.setFont(TLetra);
        lblAno.setForeground(Color.black);
        lblAno.setVisible(true);

        txtAno = new JTextField("");
        txtAno.setBounds(650, 630, 100, 20);
        txtAno.setFont(TLetra);
        txtAno.setForeground(Color.black);
        txtAno.setVisible(true);
        txtAno.addMouseListener(this);
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

        btnFiltar.setBounds(770, 627, 170, 25);
        btnFiltar.setVisible(true);

        btnDescargar.setBounds(950, 627, 30, 30);
        btnDescargar.setVisible(true);
        btnDescargar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDescargar.setToolTipText("Descargar en formato Excel");


        Operacion = new JCheckBox("Mostrar cronograma de operación");
        Operacion.setBounds(200, 615, 230, 25);
        Operacion.setVisible(true);
        Operacion.setSelected(true);
        Operacion.addMouseListener(this);
        Operacion.setOpaque(true);

        Desempeño = new JCheckBox("Mostrar cronograma de desempeño");
        Desempeño.setBounds(200, 645, 230, 25);
        Desempeño.setVisible(true);
        Desempeño.setSelected(true);
        Desempeño.addMouseListener(this);



        PanelGeneral1 = new Formu_Panel(Icofondo);
        scroll = new JScrollPane(PanelGeneral1);
        scroll.setBounds(0, 0, 1360, 721);/// tamño escroll del panel dentro del jframe medi hancha 1295
        scroll.setViewportView(PanelGeneral1);
        scroll.getViewport().setView(PanelGeneral1);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.repaint();
        PanelGeneral1.setBorder(BorderFactory.createTitledBorder(""));
        PanelGeneral1.setBackground(Color.black);
        PanelGeneral1.setForeground(Color.red);
        PanelGeneral1.setPreferredSize(new Dimension(2700, 768));// dimencion real del panel general
        PanelGeneral1.setLayout(null);

        PanelTablaOpe = new JPanel();
        PanelTablaOpe.setBorder(BorderFactory.createTitledBorder("CRONOGRAMA CALIFICACIONES OPERACIÓN"));
        PanelTablaOpe.setBackground(getBackground());
        PanelTablaOpe.setForeground(Color.red);
        PanelTablaOpe.reshape(0, 10, 2695, 300);
        PanelTablaOpe.setLayout(null);

        PanelTablaDes = new JPanel();
        PanelTablaDes.setBorder(BorderFactory.createTitledBorder("CRONOGRAMA CALIFICACIONES DESEMPEÑO"));
        PanelTablaDes.setForeground(Color.red);
        PanelTablaDes.setBackground(getBackground());
        PanelTablaDes.setForeground(Color.red);
        PanelTablaDes.reshape(0, 310, 2695, 300);
        PanelTablaDes.setLayout(null);

        TablaCronoCalifOPe = new JTable(dataModel) {
            @Override
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        TablaCronoCalifOPe.getTableHeader().setReorderingAllowed(false);
        TablaCronoCalifOPe.setBounds(0, 10, 100, 720);
        PanelDatos = new JScrollPane(TablaCronoCalifOPe);
        PanelDatos.setBounds(5, 30, 2665, 250);
        PanelDatos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(dataModel);
        TablaCronoCalifOPe.setRowSorter(elQueOrdena);

        TablaCronoCalifDes = new JTable(dataModel2) {
            @Override
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        TablaCronoCalifDes.getTableHeader().setReorderingAllowed(false);
        PanelDatos2 = new JScrollPane(TablaCronoCalifDes);
        PanelDatos2.setBounds(5, 30, 2665, 250);
        TableRowSorter<TableModel> elQueOrdena2 = new TableRowSorter<TableModel>(dataModel2);
        PanelDatos2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        PanelTablaOpe.add(PanelDatos);
        PanelTablaDes.add(PanelDatos2);

        PanelGeneral1.add(PanelTablaOpe);
        PanelGeneral1.add(PanelTablaDes);
        PanelGeneral1.add(Operacion);
        PanelGeneral1.add(Desempeño);
        PanelGeneral1.add(lblAno);
        PanelGeneral1.add(txtAno);
        PanelGeneral1.add(btnFiltar);
        PanelGeneral1.add(Codigo);
        PanelGeneral1.add(btnDescargar);



        btnFiltar.addActionListener(this);
        btnDescargar.addActionListener(this);

        setJMenuBar(Menu);

        PanelGeneral1.setVisible(true);
        this.getContentPane().add(scroll);

        ocultarColumna("Objeto",TablaCronoCalifOPe);
        ocultarColumna("Objeto",TablaCronoCalifDes);
        
        TablaCronoCalifOPe.getColumnModel().getColumn(11).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(12).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(13).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(14).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(15).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(16).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(17).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(18).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(19).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(20).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(21).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(22).setCellRenderer(new AparienciaCronogramaCalificacion());

        TablaCronoCalifDes.getColumnModel().getColumn(11).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(12).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(13).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(14).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(15).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(16).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(17).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(18).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(19).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(20).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(21).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(22).setCellRenderer(new AparienciaCronogramaCalificacion());

        ventana.bloquear(this);
        SwingWorker worker;
        worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                vaciarOpe();
                llenarDatosOpe();

                vaciarDes();
                llenarDatosDes();

                ventana.desbloquear();

                return null;
            }
        };
        worker.execute();


        repaint();


    }

//Metodos del datamodel Operacion------------------------------------------------------------------------------------------
    public void llenarDatosOpe() {
        ArrayList<DtoProgramCalificacion> calificaciones = cmd.findCalificationsProgramming(new DtoEquipo());
        setDataModelOpe(getCronogramaCalif(calificaciones, TipoCalificacion.CALIFICACION_OPERACION, getDataModelOpe()));
        TablaCronoCalifOPe.getColumnModel().getColumn(11).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(12).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(13).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(14).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(15).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(16).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(17).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(18).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(19).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(20).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(21).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifOPe.getColumnModel().getColumn(22).setCellRenderer(new AparienciaCronogramaCalificacion());

           }

    private void vaciarOpe() {
        int i = getDataModelOpe().getRowCount();
        while (i > 0) {
            getDataModelOpe().removeRow(0);
            i--;
        }
    }

    public void setDataModelOpe(DefaultTableModel dataModel) {
        this.dataModel = dataModel;
    }

    public DefaultTableModel getDataModelOpe() {
        return dataModel;
    }

//-------------------------------------------------------------------------------------------------------------------------    
//Metodos del datamodel Desempeño------------------------------------------------------------------------------------------
    public void llenarDatosDes() {
        ArrayList<DtoProgramCalificacion> calificaciones = cmd.findCalificationsProgramming(new DtoEquipo());
        setDataModelDes(getCronogramaCalif(calificaciones, TipoCalificacion.CALIFICACION_DESEMPENIO, getDataModelDes()));
        TablaCronoCalifDes.getColumnModel().getColumn(11).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(12).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(13).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(14).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(15).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(16).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(17).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(18).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(19).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(20).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(21).setCellRenderer(new AparienciaCronogramaCalificacion());
        TablaCronoCalifDes.getColumnModel().getColumn(22).setCellRenderer(new AparienciaCronogramaCalificacion());

        
    }

    private void vaciarDes() {
        int i = getDataModelDes().getRowCount();
        while (i > 0) {
            getDataModelDes().removeRow(0);
            i--;
        }
    }

    public void setDataModelDes(DefaultTableModel dataModel) {
        this.dataModel2 = dataModel;
    }

    public DefaultTableModel getDataModelDes() {
        return dataModel2;
    }

    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == btnFiltar) {
            if (txtAno.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un año a filtrar", "METROFARM", JOptionPane.ERROR_MESSAGE);
            }
            if (txtAno.getText().length() == 4) {

                DefaultTableModel DataOpe = null;
                DefaultTableModel DataDes = null;

                vaciarOpe();
                vaciarDes();
                String Year;
                Year = txtAno.getText();


                if (Operacion.isSelected() == true || Desempeño.isSelected() == true) {
                    if (Operacion.isSelected() == true) {
                        ArrayList<DtoProgramCalificacion> calificaciones = cmd.findCalificationsProgramming(new DtoEquipo());
                        setDataModelOpe(getCronogramaCalif(calificaciones, TipoCalificacion.CALIFICACION_OPERACION, getDataModelOpe(), Year));
                    }
                    if (Desempeño.isSelected() == true) {
                        ArrayList<DtoProgramCalificacion> calificaciones = cmd.findCalificationsProgramming(new DtoEquipo());
                        setDataModelDes(getCronogramaCalif(calificaciones, TipoCalificacion.CALIFICACION_DESEMPENIO, getDataModelDes(), Year));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe habilitar por lo menos un cronograma para poder filtrar una fecha", "METROFARM", JOptionPane.ERROR_MESSAGE);

                }

            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar un tamaño minimo de 4 caracteres", "METROFARM", JOptionPane.ERROR_MESSAGE);
            }
        }



        if (e.getSource() == btnDescargar) {

            if (TablaCronoCalifOPe.getRowCount() == 0 && TablaCronoCalifDes.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "No hay dato en las tablas para exportar", "METROFARM", JOptionPane.INFORMATION_MESSAGE);
            } else {
                FileNameExtensionFilter filtrer = new FileNameExtensionFilter("Archivos de excel", "xls");
                elegir.setFileFilter(filtrer);
                elegir.setDialogTitle("Guardar archivo");
                elegir.setMultiSelectionEnabled(false);
                elegir.setAcceptAllFileFilterUsed(false);


                if (elegir.showSaveDialog(null) == elegir.APPROVE_OPTION) {
                    List<JTable> tbOpe = new ArrayList<>();
                    List<JTable> tbDes = new ArrayList<>();
                    List<String> Ope = new ArrayList<>();
                    List<String> Des = new ArrayList<>();
                    tbOpe.add(TablaCronoCalifOPe);
                    tbDes.add(TablaCronoCalifDes);
                    Ope.add("Cronograma Calif Operación");
                    Des.add("Cronograma Calif Desempeño");

                    String file = elegir.getSelectedFile().toString().concat(".xls");
                    try {
                        ExcelExportCalification excel = new ExcelExportCalification(tbOpe, tbDes, new File(file), Ope, Des);
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
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea regresar?", "", JOptionPane.YES_NO_OPTION);
            switch (respuesta) {
                case JOptionPane.YES_OPTION:
                    this.dispose();
                    Cronogramas men = new Cronogramas();
                    break;
                case JOptionPane.NO_OPTION:
                    break;

            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == Operacion) {
            if (Operacion.isSelected() == false) {
                vaciarOpe();
            }
            if (Operacion.isSelected() == true) {

                llenarDatosOpe();

            }


        }

        if (e.getSource() == Desempeño) {
            if (Desempeño.isSelected() == false) {
                vaciarDes();
            }
            if (Desempeño.isSelected() == true) {

                llenarDatosDes();

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
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public DefaultTableModel getCronogramaCalif(ArrayList<DtoProgramCalificacion> array, TipoCalificacion tipoCalificacion, DefaultTableModel model) {

        for (DtoProgramCalificacion c : array) {
            if (c.getTipoCalificacion().equals(tipoCalificacion.getCode())) {
                Object nuevo[] = {c.getEquipo().getNombre(), c.getEquipo().getMarca(), c.getEquipo().getModelo(), c.getEquipo().getSerie(), c.getEquipo().getCodigoInv(), c.getEquipo().getUbicacion(), tipoCalificacion.equals(TipoCalificacion.CALIFICACION_OPERACION) ? c.getEquipo().getFrecCalifOPerativa() + " Meses" : c.getEquipo().getFrecCalifDesempenio() + " Meses", GlobalUtils.getDateFormatted(c.getFechaRegistro()), GlobalUtils.getDateFormatted(c.getFechaProxima()), c.getFechaEjecucion() != null ? GlobalUtils.getDateFormatted(c.getFechaEjecucion()) : "Sin Registro", TipoCalificacion.getByCode(c.getTipoCalificacion()).getNombre(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), c};
                model.addRow(nuevo);
            }
        }
        return model;
    }

    public DefaultTableModel getCronogramaCalif(ArrayList<DtoProgramCalificacion> array, TipoCalificacion tipoCalificacion, DefaultTableModel model, String Year) {

        for (DtoProgramCalificacion c : array) {
            if (c.getTipoCalificacion().equals(tipoCalificacion.getCode())) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(c.getFechaProxima());
                if ((cal.get(Calendar.YEAR) + "").equals(Year)) {
                    Object nuevo[] = {c.getEquipo().getNombre(), c.getEquipo().getMarca(), c.getEquipo().getModelo(), c.getEquipo().getSerie(), c.getEquipo().getCodigoInv(), c.getEquipo().getUbicacion(), tipoCalificacion.equals(TipoCalificacion.CALIFICACION_OPERACION) ? c.getEquipo().getFrecCalifOPerativa() + " Meses" : c.getEquipo().getFrecCalifDesempenio() + " Meses", GlobalUtils.getDateFormatted(c.getFechaRegistro()), GlobalUtils.getDateFormatted(c.getFechaProxima()), c.getFechaEjecucion() != null ? GlobalUtils.getDateFormatted(c.getFechaEjecucion()) : "Sin Registro", TipoCalificacion.getByCode(c.getTipoCalificacion()).getNombre(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), c};
                    model.addRow(nuevo);
                }
            }
        }
        return model;
    }

    public final void ocultarColumna(String column,JTable tabla) {
        TableColumn tc = tabla.getColumn(column); //Obtienes la columna
        tc.setPreferredWidth(0); //Y le das un tamaño
        tc.setMinWidth(0);
        tc.setMaxWidth(0);
    }
}
