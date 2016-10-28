/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.views;

import co.com.farmatech.metrofarm.cmd.CmdCalificacion;
import co.com.farmatech.metrofarm.utils.Formu_Fondo;
import co.jankins.psf.common.enums.TipoCalificacion;
import com.co.farmatech.metrofarm.dto.DtoEquipo;
import com.co.farmatech.metrofarm.dto.DtoProgramCalificacion;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import ventana.bloqueo.VentanaBloqueo;

/**
 *
 * @author bestrada
 */
public class TablaCalificaciones extends JFrame implements MouseListener {

    JTable TablaDatos;
    private Object[][] DetallesIdentificacion = new Object[0][0];
    private Object[] ColumnasIdentificacion = {"Cód Inventario", "Cód Metrologia", "Número de Serie", "Nombre", "Clasificación", "Calificación pendiente", "Objeto"};
    private DefaultTableModel dataModel = new DefaultTableModel(DetallesIdentificacion, ColumnasIdentificacion);
    JScrollPane PanelDatos;
    ImageIcon Icofondo;
    Formu_Fondo PanelGeneral1;
    CmdCalificacion cmd;
    Calificacion frameParent;
    String ruta = "co/com/farmatech/metrofarm/resources/imagenes/";
    private final VentanaBloqueo ventana = new VentanaBloqueo();

    public TablaCalificaciones(Calificacion frame, String tittle) {

        super(tittle);

        ((JPanel) getContentPane()).setOpaque(false);  // para que muestre la imagen
        this.setSize(700, 250);
        this.setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);
        setBackground(Color.white);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.frameParent = frame;

        setIconImage(new ImageIcon(getClass().getClassLoader().getResource(ruta + "FARMA.png")).getImage());
        //setLocationRelativeTo(null);
        Font TLetra = new Font("Arial", Font.PLAIN, 12);
        Font Advertencia = new Font("Arial", Font.PLAIN, 15);

        cmd = new CmdCalificacion();

        Icofondo = new ImageIcon(getClass().getClassLoader().getResource(ruta + "fondomenu.png"));

        PanelGeneral1 = new Formu_Fondo(Icofondo);
        PanelGeneral1.setBorder(BorderFactory.createTitledBorder(""));
        //PanelGeneral1.add(fondo);
        PanelGeneral1.setBackground(Color.black);
        PanelGeneral1.setForeground(Color.red);
        PanelGeneral1.reshape(0, 0, 700, 250);
        PanelGeneral1.setLayout(null);

        PanelGeneral1.setVisible(true);
        this.getContentPane().add(PanelGeneral1);

        TablaDatos = new JTable(dataModel);
        PanelDatos = new JScrollPane(TablaDatos);
        PanelDatos.setBounds(1, 1, 700, 250);
        TablaDatos.addMouseListener(this);
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(dataModel);
        TablaDatos.setRowSorter(elQueOrdena);
        TablaDatos.setToolTipText("Elija el equipo,presione buscar, cargue las calificaciones pertinentes y presione actualizar");
        TablaDatos.setBackground(Color.yellow);


        ocultarColumna("Objeto");

        PanelGeneral1.add(PanelDatos);
    }

    public void llenarDatosMes() {
        ArrayList<DtoProgramCalificacion> calificationMonth = cmd.findCalificationsMonth(new DtoEquipo());

        setDataModel(getCalificacion(calificationMonth, getDataModel()));

    }

    public void llenarDatosVencidos() {
        ArrayList<DtoProgramCalificacion> calificationMonth = cmd.findCalificationsExpired(new DtoEquipo());
        setDataModel(getCalificacion(calificationMonth, getDataModel()));
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
                        DtoProgramCalificacion programacion = ((DtoProgramCalificacion) TablaDatos.getValueAt(fila, 6));
                        DtoEquipo equipo = programacion.getEquipo();
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
                        ((Calificacion) frameParent).buscarCalificacion(equipo);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    ventana.desbloquear();

                    return null;
                }
            };
            worker.execute();

        }

    }

    public DefaultTableModel getCalificacion(ArrayList<DtoProgramCalificacion> programming, DefaultTableModel model) {

        for (DtoProgramCalificacion c : programming) {

            Object nuevo[] = {c.getEquipo().getCodigoInv(), c.getEquipo().getCodigoMet(), c.getEquipo().getSerie(), c.getEquipo().getNombre(), c.getEquipo().getUbicacion(), TipoCalificacion.getByCode(c.getTipoCalificacion()).getNombre(), c};
            model.addRow(nuevo);

        }
        return model;

    }

    public void ocultarColumna(String column) {
        TableColumn tc = TablaDatos.getColumn(column); //Obtienes la columna
        tc.setPreferredWidth(0); //Y le das un tamaño
        tc.setMinWidth(0);
        tc.setMaxWidth(0);
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
}
