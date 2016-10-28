/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.utils;

import co.com.farmatech.metrofarm.cmd.CmdCalificacion;
import co.com.farmatech.metrofarm.cmd.CmdEquipment;
import co.com.farmatech.metrofarm.utils.tablas.aparienciaTabla;
import co.com.farmatech.metrofarm.views.Calibracion;
import co.com.farmatech.metrofarm.views.Calificacion;
import co.com.farmatech.metrofarm.views.MantenimientoEquipo;
import co.com.farmatech.metrofarm.views.MantenimientoInstrumento;
import co.janker.dto.objresponse.ObjectResponse;
import co.jankins.psf.common.enums.Estados;
import co.jankins.psf.common.enums.Frecuencias;
import co.jankins.psf.common.enums.TipoEquipo;
import co.jankins.psf.common.global.GlobalUtils;
import com.co.farmatech.metrofarm.dto.DtoCalificacion;
import com.co.farmatech.metrofarm.dto.DtoEquipo;
import com.co.farmatech.metrofarm.dto.PageData;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.apache.commons.codec.binary.Base64;
import ventana.bloqueo.VentanaBloqueo;

/**
 *
 * @author bestrada
 */
public class TablaEquipos extends JFrame implements MouseListener, ActionListener {

    JTable TablaDatos;
    private Object[][] DetallesIdentificacion = new Object[0][0];
    private Object[] ColumnasIdentificacion = {"Código de Inventario", "Código de Metrologia", "Marca", "Modelo", "Número de Serie", "Nombre", "Tipo de Artefacto", "Clasificación del equipo", "Estado", "Cliente", "Observaciones", "Fecha de registro", "Frecuencia Mantenimiento", "Frecuencia Calibración", "Frecuencia Calif Opercacion", "Frecuencia Calif Desempeño", "Objeto"};
    private DefaultTableModel dataModel = new DefaultTableModel(DetallesIdentificacion, ColumnasIdentificacion);
    JScrollPane PanelDatos;
    ImageIcon Icofondo;
    Formu_Fondo PanelGeneral1;
    String ruta = "co/com/farmatech/metrofarm/resources/imagenes/";
    CmdEquipment cmd;
    CmdCalificacion cmdCalificacion;
    JFrame frameParent;
    private final VentanaBloqueo ventana = new VentanaBloqueo();
    JTextField txtFiltrar;
    Font TLetra = new Font("Calibri", Font.PLAIN, 12);
    private TableRowSorter filtro;
    PanelPaginador panelPaginador;
    PageData pageData;

    public TablaEquipos(JFrame frame) {

        super("Tabla equipos METROFARM -v1");

        ((JPanel) getContentPane()).setOpaque(false);  // para que muestre la imagen
        this.setSize(1250, 350);
        this.setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);
        setBackground(Color.white);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(0);

        frameParent = frame;
        cmd = new CmdEquipment();
        cmdCalificacion = new CmdCalificacion();

        panelPaginador = new PanelPaginador();
        panelPaginador.setBounds(920, 270, 300, 29);

        pageData = panelPaginador.getPageData();
        pageData.setRowsPerPage(10);

        setIconImage(new ImageIcon(getClass().getClassLoader().getResource(ruta + "FARMA.png")).getImage());

        Icofondo = new ImageIcon(getClass().getClassLoader().getResource(ruta + "fondomenu.png"));

        PanelGeneral1 = new Formu_Fondo(Icofondo);
        PanelGeneral1.setBorder(BorderFactory.createTitledBorder(""));
        //PanelGeneral1.add(fondo);
        PanelGeneral1.setBackground(Color.black);
        PanelGeneral1.setForeground(Color.red);
        PanelGeneral1.reshape(0, 0, 1250, 350);
        PanelGeneral1.setLayout(null);

        PanelGeneral1.setVisible(true);
        this.getContentPane().add(PanelGeneral1);

        TablaDatos = new JTable(dataModel);
        PanelDatos = new JScrollPane(TablaDatos);
        PanelDatos.setBounds(1, 1, 1250, 250);
        TablaDatos.addMouseListener(this);
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(dataModel);
        TablaDatos.setRowSorter(elQueOrdena);
        ocultarColumna("Objeto");

        JLabel lblFiltro = new JLabel("Filtrar por nombre de equipo/instrumento ");
        lblFiltro.setForeground(Color.black);
        lblFiltro.setFont(TLetra);
        lblFiltro.setBounds(110, 270, 300, 20);

        txtFiltrar = new JTextField("");
        txtFiltrar.setBounds(450, 270, 420, 26);
        txtFiltrar.setFont(TLetra);
        txtFiltrar.setForeground(Color.black);
        txtFiltrar.setVisible(true);
        txtFiltrar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                txtFiltrar.addKeyListener(new KeyAdapter() {
                    public void keyReleased(final KeyEvent e) {
                        filtroNombre();
                    }
                });

                filtro = new TableRowSorter(TablaDatos.getModel());
                TablaDatos.setRowSorter(filtro);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        panelPaginador.getBtnBack().addActionListener(this);
        panelPaginador.getBtnBackward().addActionListener(this);
        panelPaginador.getBtnNext().addActionListener(this);
        panelPaginador.getBtnForward().addActionListener(this);

        PanelGeneral1.add(PanelDatos);
        PanelGeneral1.add(lblFiltro);
        PanelGeneral1.add(txtFiltrar);
        PanelGeneral1.add(panelPaginador);

        pageData.setFirstQuery(true);
        ventana.bloquear(this);
        SwingWorker worker;
        worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                pageData.setCurrentPage(1);
                vaciar();
                llenarDatos(pageData);
                ventana.desbloquear();
                return null;
            }
        };
        worker.execute();
        this.repaint();


    }

    public void llenarDatos(PageData pageData) {
        try {
            ArrayList<DtoEquipo> equipos = new ArrayList<DtoEquipo>();
            ObjectResponse response = cmd.findEquipments(new DtoEquipo(), pageData);
            equipos = (ArrayList<DtoEquipo>) response.getRecords();
            this.pageData = response.getpageResponse();
            setDataModel(getEquipos(equipos, dataModel));
            TablaDatos.setDefaultRenderer(Object.class, new aparienciaTabla());
            repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "MetroFarm", JOptionPane.ERROR_MESSAGE);
        }
    }

    public DefaultTableModel getEquipos(ArrayList<DtoEquipo> result, DefaultTableModel model) {

        for (DtoEquipo c : result) {
            String act = "Si";
            if (c.getEstado() == Estados.DESACTIVADO.getCode()) {
                act = "No";
            }
            try {
                Object nuevo[] = {c.getCodigoInv(), c.getCodigoMet(), c.getMarca(), c.getModelo(), c.getSerie(), c.getNombre(), TipoEquipo.getByCode(c.getTipo()).getNombre(), c.getUbicacion(), Estados.getByCode(c.getEstado()).getDescription(), c.getCliente(), c.getObservaciones(), GlobalUtils.getDateFormatted(c.getFecha()), Frecuencias.getByCode(c.getFrecMantenimiento()).getDescription(), Frecuencias.getByCode(c.getFrecCalibracion()).getDescription(), Frecuencias.getByCode(c.getFrecCalifOPerativa()).getDescription(), Frecuencias.getByCode(c.getFrecCalifDesempenio()).getDescription(), c};
                if (frameParent instanceof MantenimientoInstrumento) {
                    if (TipoEquipo.INSTRUMENTO.getCode().equals(c.getTipo())) {
                        model.addRow(nuevo);
                    }
                } else if (frameParent instanceof MantenimientoEquipo) {
                    if (TipoEquipo.EQUIPO.getCode().equals(c.getTipo())) {
                        model.addRow(nuevo);
                    }
                } else if (frameParent instanceof Calificacion) {
                    if (!c.getFrecCalifDesempenio().equals(Frecuencias.NOAPLICA.getCode()) || !c.getFrecCalifOPerativa().equals(Frecuencias.NOAPLICA.getCode())) {
                        model.addRow(nuevo);
                    }
                } else {
                    model.addRow(nuevo);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        return model;

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
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == TablaDatos) {
            ventana.bloquear(this);
            SwingWorker worker;
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    int fila = 0;
                    try {
                        fila = TablaDatos.getSelectedRow();
                        TablaDatos.setSelectionBackground(new Color(Integer.parseInt("015289", 16)));
                        if (fila > -1) {
                            if (String.valueOf(TablaDatos.getValueAt(fila, 6)).equals("")) {
                                JOptionPane.showMessageDialog(null, "El equipo seleccionado se encuentra entre los equipos pendientes a registrar", "MetroFarm v1.0", JOptionPane.ERROR_MESSAGE);
                            }
                            DtoEquipo equipo = ((DtoEquipo) TablaDatos.getValueAt(fila, 16));
                            if (frameParent instanceof Calibracion) {
                                if (equipo.getFrecCalibracion().equals(Frecuencias.NOAPLICA.getCode())) {
                                    JOptionPane.showMessageDialog(null, "Este equipo no esta habilitado para Calibraciones", "MetroFarm v1.0", JOptionPane.ERROR_MESSAGE);
                                    ventana.desbloquear();
                                    return null;
                                } else {
                                    ((Calibracion) frameParent).txtcodInv.setText(equipo.getCodigoInv());
                                    ((Calibracion) frameParent).txtCodmet.setText(equipo.getCodigoMet());
                                    ((Calibracion) frameParent).txtMarca.setText(equipo.getMarca());
                                    ((Calibracion) frameParent).txtModelo.setText(equipo.getModelo());
                                    ((Calibracion) frameParent).txtnumse.setText(equipo.getSerie());
                                    ((Calibracion) frameParent).txtnom.setText(equipo.getNombre());
                                    ((Calibracion) frameParent).txtUbicac.setText(equipo.getUbicacion());
                                    ((Calibracion) frameParent).txtEstado.setText(Estados.getByCode(equipo.getEstado()).getDescription());
                                    ((Calibracion) frameParent).txtCliente.setText(equipo.getCliente());

                                    ((Calibracion) frameParent).txtcodInv.setBackground(new java.awt.Color(204, 255, 204));
                                    ((Calibracion) frameParent).txtcodInv.setEditable(false);

                                    ((Calibracion) frameParent).txtCodmet.setBackground(new java.awt.Color(204, 255, 204));
                                    ((Calibracion) frameParent).txtCodmet.setEditable(false);
                                    ((Calibracion) frameParent).txtnumse.setBackground(new java.awt.Color(204, 255, 204));
                                    ((Calibracion) frameParent).txtnumse.setEditable(false);
                                    ((Calibracion) frameParent).txtnom.setBackground(new java.awt.Color(204, 255, 204));
                                    ((Calibracion) frameParent).txtUbicac.setBackground(new java.awt.Color(204, 255, 204));
                                    ((Calibracion) frameParent).txtMarca.setBackground(new java.awt.Color(204, 255, 204));
                                    ((Calibracion) frameParent).txtModelo.setBackground(new java.awt.Color(204, 255, 204));
                                    ((Calibracion) frameParent).txtEstado.setBackground(new java.awt.Color(204, 255, 204));
                                    ((Calibracion) frameParent).txtCliente.setBackground(new java.awt.Color(204, 255, 204));
                                    ((Calibracion) frameParent).equipoSeleccionado = equipo;
                                    ImageIcon imagen = new ImageIcon(Base64.decodeBase64(equipo.getFoto()));
                                    imagen = AjustarUmagen(imagen);

                                    ((Calibracion) frameParent).lblfoto.setIcon(imagen);

                                }
                            } else if (frameParent instanceof MantenimientoInstrumento) {
                                if (equipo.getFrecMantenimiento().equals(Frecuencias.NOAPLICA.getCode())) {
                                    JOptionPane.showMessageDialog(null, "Este instrumento no esta habilitado para Mantenimientos", "MetroFarm v1.0", JOptionPane.ERROR_MESSAGE);
                                    ventana.desbloquear();
                                    return null;
                                } else {
                                    ((MantenimientoInstrumento) frameParent).txtcodInv.setText(equipo.getCodigoInv());
                                    ((MantenimientoInstrumento) frameParent).txtidMetrologia.setText(equipo.getCodigoMet());
                                    ((MantenimientoInstrumento) frameParent).txtMarca.setText(equipo.getMarca());
                                    ((MantenimientoInstrumento) frameParent).txtModelo.setText(equipo.getModelo());
                                    ((MantenimientoInstrumento) frameParent).txtNumSerie.setText(equipo.getSerie());
                                    ((MantenimientoInstrumento) frameParent).txtNombre.setText(equipo.getNombre());
                                    ((MantenimientoInstrumento) frameParent).txtUbicacion.setText(equipo.getUbicacion());

                                    ((MantenimientoInstrumento) frameParent).txtcodInv.setBackground(new java.awt.Color(204, 255, 204));
                                    ((MantenimientoInstrumento) frameParent).txtcodInv.setEditable(false);

                                    ((MantenimientoInstrumento) frameParent).txtidMetrologia.setBackground(new java.awt.Color(204, 255, 204));
                                    ((MantenimientoInstrumento) frameParent).txtidMetrologia.setEditable(false);
                                    ((MantenimientoInstrumento) frameParent).txtNumSerie.setBackground(new java.awt.Color(204, 255, 204));
                                    ((MantenimientoInstrumento) frameParent).txtNumSerie.setEditable(false);
                                    ((MantenimientoInstrumento) frameParent).txtNombre.setBackground(new java.awt.Color(204, 255, 204));
                                    ((MantenimientoInstrumento) frameParent).txtUbicacion.setBackground(new java.awt.Color(204, 255, 204));
                                    ((MantenimientoInstrumento) frameParent).txtMarca.setBackground(new java.awt.Color(204, 255, 204));
                                    ((MantenimientoInstrumento) frameParent).txtModelo.setBackground(new java.awt.Color(204, 255, 204));
                                    ((MantenimientoInstrumento) frameParent).equipoSeleccionado = equipo;
                                    ImageIcon imagen = new ImageIcon(Base64.decodeBase64(equipo.getFoto()));
                                    imagen = AjustarUmagen(imagen);

                                    ((MantenimientoInstrumento) frameParent).lblFoto.setIcon(imagen);

                                }
                            } else if (frameParent instanceof MantenimientoEquipo) {
                                if (equipo.getFrecMantenimiento().equals(Frecuencias.NOAPLICA.getCode())) {
                                    JOptionPane.showMessageDialog(null, "Este instrumento no esta habilitado para Mantenimientos", "MetroFarm v1.0", JOptionPane.ERROR_MESSAGE);
                                    ventana.desbloquear();
                                    return null;
                                } else {
                                    ((MantenimientoEquipo) frameParent).txtcodInv.setText(equipo.getCodigoInv());
                                    ((MantenimientoEquipo) frameParent).txtidMetrologia.setText(equipo.getCodigoMet());
                                    ((MantenimientoEquipo) frameParent).txtMarca.setText(equipo.getMarca());
                                    ((MantenimientoEquipo) frameParent).txtModelo.setText(equipo.getModelo());
                                    ((MantenimientoEquipo) frameParent).txtNumSerie.setText(equipo.getSerie());
                                    ((MantenimientoEquipo) frameParent).txtNombre.setText(equipo.getNombre());
                                    ((MantenimientoEquipo) frameParent).txtUbicacion.setText(equipo.getUbicacion());

                                    ((MantenimientoEquipo) frameParent).txtcodInv.setBackground(new java.awt.Color(204, 255, 204));
                                    ((MantenimientoEquipo) frameParent).txtcodInv.setEditable(false);

                                    ((MantenimientoEquipo) frameParent).txtidMetrologia.setBackground(new java.awt.Color(204, 255, 204));
                                    ((MantenimientoEquipo) frameParent).txtidMetrologia.setEditable(false);
                                    ((MantenimientoEquipo) frameParent).txtNumSerie.setBackground(new java.awt.Color(204, 255, 204));
                                    ((MantenimientoEquipo) frameParent).txtNumSerie.setEditable(false);
                                    ((MantenimientoEquipo) frameParent).txtNombre.setBackground(new java.awt.Color(204, 255, 204));
                                    ((MantenimientoEquipo) frameParent).txtUbicacion.setBackground(new java.awt.Color(204, 255, 204));
                                    ((MantenimientoEquipo) frameParent).txtMarca.setBackground(new java.awt.Color(204, 255, 204));
                                    ((MantenimientoEquipo) frameParent).txtModelo.setBackground(new java.awt.Color(204, 255, 204));
                                    ((MantenimientoEquipo) frameParent).equipoSeleccionado = equipo;
                                    ImageIcon imagen = new ImageIcon(Base64.decodeBase64(equipo.getFoto()));
                                    imagen = AjustarUmagen(imagen);

                                    ((MantenimientoEquipo) frameParent).lblFoto.setIcon(imagen);

                                }
                            } else if (frameParent instanceof Calificacion) {
                                ((Calificacion) frameParent).txtcodInv.setText(equipo.getCodigoInv());
                                ((Calificacion) frameParent).txtcodInv.setBackground(new java.awt.Color(204, 255, 204));
                                ((Calificacion) frameParent).txtcodInv.setEditable(false);
                                ((Calificacion) frameParent).txtCodmet.setText(equipo.getCodigoMet());
                                ((Calificacion) frameParent).txtCodmet.setBackground(new java.awt.Color(204, 255, 204));
                                ((Calificacion) frameParent).txtCodmet.setEditable(false);
                                ((Calificacion) frameParent).txtnumse.setText(equipo.getSerie());
                                ((Calificacion) frameParent).txtnumse.setEditable(false);
                                ((Calificacion) frameParent).txtnumse.setBackground(new java.awt.Color(204, 255, 204));
                                ((Calificacion) frameParent).txtnom.setText(equipo.getNombre());
                                ((Calificacion) frameParent).txtnom.setBackground(new java.awt.Color(204, 255, 204));
                                ((Calificacion) frameParent).txtUbicac.setText(equipo.getUbicacion());
                                ((Calificacion) frameParent).txtUbicac.setBackground(new java.awt.Color(204, 255, 204));
                                ((Calificacion) frameParent).equipoSeleccionado = equipo;

                                ArrayList<DtoCalificacion> calificaciones = cmdCalificacion.findCalifications(equipo);
                                if (calificaciones != null && calificaciones.size() > 0) {
                                    ((Calificacion) frameParent).calificacionSeleccionada = calificaciones.get(0);

                                }
                            }
                        }

                    } catch (NumberFormatException | HeadlessException ex) {
                        JOptionPane.showMessageDialog(null, "Hubo un error :" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        ventana.desbloquear();
                        return null;
                    }

                    ventana.desbloquear();

                    return null;
                }
            };
            worker.execute();
        }
    }

    public ImageIcon AjustarUmagen(ImageIcon icon) {
        ImageIcon adaptada;

        if (icon.getIconWidth() > 90) {
            adaptada = new ImageIcon(icon.getImage().
                    getScaledInstance(250, -1, Image.SCALE_DEFAULT));

        } else {
            adaptada = icon;
        }
        return adaptada;
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

    public void ocultarColumna(String column) {
        TableColumn tc = TablaDatos.getColumn(column); //Obtienes la columna
        tc.setPreferredWidth(0); //Y le das un tamaño
        tc.setMinWidth(0);
        tc.setMaxWidth(0);
    }

    public void filtroNombre() {

        String cadena = txtFiltrar.getText().toString();
        try {
            filtro.setRowFilter(RowFilter.regexFilter("(?i)" + cadena, 5));
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == panelPaginador.getBtnNext()) {
            ventana.bloquear(this);
            SwingWorker worker;
            pageData.setFirstQuery(true);
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    pageData.nextPage();
                    if (pageData.getMaxPages() == pageData.getCurrentPage()) {
                        panelPaginador.getBtnForward().setEnabled(false);
                        panelPaginador.getBtnNext().setEnabled(false);
                    }
                    vaciar();
                    llenarDatos(pageData);
                    panelPaginador.setPagText(pageData.getCurrentPage() + " de " + pageData.getMaxPages());
                    if (pageData.getMaxPages() > 1) {
                        panelPaginador.getBtnBackward().setEnabled(true);
                        panelPaginador.getBtnBack().setEnabled(true);
                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();
        }

        if (e.getSource() == panelPaginador.getBtnBack()) {
            ventana.bloquear(this);
            SwingWorker worker;
            pageData.setFirstQuery(true);
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    pageData.previousPage();
                    if (pageData.getCurrentPage() == 1) {
                        panelPaginador.getBtnBackward().setEnabled(false);
                        panelPaginador.getBtnBack().setEnabled(false);
                    }
                    vaciar();
                    llenarDatos(pageData);
                    panelPaginador.setPagText(pageData.getCurrentPage() + " de " + pageData.getMaxPages());
                    if (pageData.getMaxPages() > 1) {
                        panelPaginador.getBtnForward().setEnabled(true);
                        panelPaginador.getBtnNext().setEnabled(true);
                    }

                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();

        }

        if (e.getSource() == panelPaginador.getBtnBackward()) {
            ventana.bloquear(this);
            SwingWorker worker;
            pageData.setFirstQuery(true);
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    pageData.setCurrentPage(1);
                    panelPaginador.getBtnBackward().setEnabled(false);
                    panelPaginador.getBtnBack().setEnabled(false);
                    vaciar();
                    llenarDatos(pageData);
                    panelPaginador.setPagText(pageData.getCurrentPage() + " de " + pageData.getMaxPages());
                    if (pageData.getMaxPages() > 1) {
                        panelPaginador.getBtnForward().setEnabled(true);
                        panelPaginador.getBtnNext().setEnabled(true);
                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();
        }

        if (e.getSource() == panelPaginador.getBtnForward()) {
            ventana.bloquear(this);
            SwingWorker worker;
            pageData.setFirstQuery(true);
            worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    pageData.setCurrentPage(pageData.getMaxPages());
                    panelPaginador.getBtnForward().setEnabled(false);
                    panelPaginador.getBtnNext().setEnabled(false);
                    vaciar();
                    llenarDatos(pageData);
                    panelPaginador.setPagText(pageData.getCurrentPage() + " de " + pageData.getMaxPages());
                    if (pageData.getMaxPages() > 1) {
                        panelPaginador.getBtnBackward().setEnabled(true);
                        panelPaginador.getBtnBack().setEnabled(true);
                    }
                    ventana.desbloquear();
                    return null;
                }
            };
            worker.execute();
        }

    }
}
