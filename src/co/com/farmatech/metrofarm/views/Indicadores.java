/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.views;

import co.com.farmatech.metrofarm.cmd.CmdReports;
import co.com.farmatech.metrofarm.utils.Formu_Fondo;
import co.com.farmatech.metrofarm.utils.UtilFarmatech;
import co.com.farmatech.metrofarm.utils.buttons.BotonesMenu;
import com.co.farmatech.metrofarm.dto.DtoReport;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author bestrada
 */
public class Indicadores extends JFrame implements ActionListener {
    
    public JFrame Ventana;
    private JMenuBar Menu;
    private JMenu menu1;
    private JMenuItem m11;
    Formu_Fondo PanelGeneral1;
    ImageIcon Icofondo;
    ImageIcon IcoIndica_calif, IcoIndica_calif_Anim, IcoGastoCalib, IcoGastoCalibA, IcoIndica_calib, IcoIndica_calib_Anim, IcoGastoMant, IcoGastoMantanim;
    BotonesMenu btnIndica_calib, btnGastosCalib, btnIndica_calif, btnGastosMant;
    Image IcoCabecera;
    JFileChooser fc;
    JPopupMenu Pmenu, Pmenu2;
    JMenuItem menuItem1, menuItem2, menuItem3, menuItem4;
    UtilFarmatech util=new UtilFarmatech();
    CmdReports cmd=new CmdReports();
    String ruta = "co/com/farmatech/metrofarm/resources/imagenes/";

    public Indicadores() {

        Pmenu = new JPopupMenu();
        Pmenu2 = new JPopupMenu();
        menuItem1 = new JMenuItem("Reporte de costos por equipo");
        menuItem1.addActionListener(this);
        Pmenu.add(menuItem1);

        menuItem2 = new JMenuItem("Reporte de costos por mes");
        menuItem2.addActionListener(this);
        Pmenu.add(menuItem2);

        Pmenu2 = new JPopupMenu();
        menuItem3 = new JMenuItem("Reporte de costos por equipo");
        menuItem3.addActionListener(this);
        Pmenu2.add(menuItem3);

        menuItem4 = new JMenuItem("Reporte de costos por mes");
        menuItem4.addActionListener(this);
        Pmenu2.add(menuItem4);

        Menu = new JMenuBar();
        menu1 = new JMenu("Archivo");
        Menu.add(menu1);

        m11 = new JMenuItem("Regresar");
        m11.addActionListener(this);
        m11.setIcon(new ImageIcon(getClass().getClassLoader().getResource(ruta+"atras.png")));
        menu1.add(m11);

        fc = new JFileChooser();




        //setLocationRelativeTo(null);
        Font TLetra = new Font("Arial", Font.PLAIN, 12);
        Font Advertencia = new Font("Arial", Font.PLAIN, 15);

        Icofondo = new ImageIcon(getClass().getClassLoader().getResource(ruta+"fondomenufarma.png"));

        IcoIndica_calib = new ImageIcon(getClass().getClassLoader().getResource(ruta+"indicadoresU2.png"));
        IcoIndica_calib_Anim = new ImageIcon(getClass().getClassLoader().getResource(ruta+"indicadores2.png"));

        IcoIndica_calif = new ImageIcon(getClass().getClassLoader().getResource(ruta+"indicadorescalifU.png"));
        IcoIndica_calif_Anim = new ImageIcon(getClass().getClassLoader().getResource(ruta+"indicadorescalif.png"));

        IcoGastoCalib = new ImageIcon(getClass().getClassLoader().getResource(ruta+"indica_costo_calibU.png"));
        IcoGastoCalibA = new ImageIcon(getClass().getClassLoader().getResource(ruta+"indica_costo_calib.png"));

        IcoGastoMant = new ImageIcon(getClass().getClassLoader().getResource(ruta+"indica_costo_mantenU.png"));
        IcoGastoMantanim = new ImageIcon(getClass().getClassLoader().getResource(ruta+"indica_costo_manten.png"));

        //////////////////////////Boton indicador cumplimiento calibracion///////////////////////////////////////////////
        btnIndica_calib = new BotonesMenu(IcoIndica_calib, IcoIndica_calib_Anim);
        btnIndica_calib.setBounds(-50, 100, 400, 400);
        btnIndica_calib.setVisible(true);
        btnIndica_calib.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnIndica_calib.addActionListener(this);

        //////////////////////////Boton indicadores///////////////////////////////////////////////
        btnIndica_calif = new BotonesMenu(IcoIndica_calif, IcoIndica_calif_Anim);
        btnIndica_calif.setBounds(280, 100, 400, 400);
        btnIndica_calif.setVisible(true);
        btnIndica_calif.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnIndica_calif.addActionListener(this);

        //////////////////////////Boton indicador gasto calibracion///////////////////////////////////////////////
        btnGastosCalib = new BotonesMenu(IcoGastoCalib, IcoGastoCalibA);
        btnGastosCalib.setBounds(610, 100, 400, 400);
        btnGastosCalib.setVisible(true);
        btnGastosCalib.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnGastosCalib.setComponentPopupMenu(Pmenu);
        btnGastosCalib.setToolTipText("Presione click derecho para escoger el tipo de reporte de gasto a generar");


        //////////////////////////Boton indicador gasto mantenimiento///////////////////////////////////////////////
        btnGastosMant = new BotonesMenu(IcoGastoMant, IcoGastoMantanim);
        btnGastosMant.setBounds(940, 100, 400, 400);
        btnGastosMant.setVisible(true);
        btnGastosMant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnGastosMant.addActionListener(this);
        btnGastosMant.setComponentPopupMenu(Pmenu2);
        btnGastosMant.setToolTipText("Presione click derecho para escoger el tipo de reporte de gasto a generar");






        PanelGeneral1 = new Formu_Fondo(Icofondo);
        PanelGeneral1.setBorder(BorderFactory.createTitledBorder(""));
        //PanelGeneral1.add(fondo);
        PanelGeneral1.setBackground(Color.black);
        PanelGeneral1.setForeground(Color.red);
        PanelGeneral1.reshape(0, 0, 1366, 768);
        PanelGeneral1.setLayout(null);

        PanelGeneral1.setVisible(true);
        this.getContentPane().add(PanelGeneral1);

        PanelGeneral1.add(btnIndica_calib);
        PanelGeneral1.add(btnGastosCalib);
        PanelGeneral1.add(btnIndica_calif);
        PanelGeneral1.add(btnGastosMant);

        Ventana = new JFrame("indicadores de metrología METROFARM -v1");
        Ventana.setLayout(null);
        Ventana.add(PanelGeneral1);
        Ventana.setSize(1366, 768);
        Ventana.setLocationRelativeTo(null);
        Ventana.setIconImage(IcoCabecera);
        Ventana.setBackground(Color.pink);
        Ventana.setResizable(false);
        Ventana.show();
        Ventana.setVisible(true);
        Ventana.setJMenuBar(Menu);
        Ventana.setIconImage(new ImageIcon(getClass().getClassLoader().getResource(ruta+"FARMA.png")).getImage());
        Ventana.setDefaultCloseOperation(0);

        this.repaint();
        
    }

   @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == m11) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea regresar?", "", JOptionPane.YES_NO_OPTION);
            switch (respuesta) {
                case JOptionPane.YES_OPTION:
                    Ventana.dispose();
                    MenuCronogramaIndicador menu = new MenuCronogramaIndicador();
                    break;
                case JOptionPane.NO_OPTION:
                    break;

            }

        }

        if (e.getSource() == btnIndica_calib) {
            String Year = "";
            Year = JOptionPane.showInputDialog(null, "Por favor ingrese el año para el cual desea generar el indicador", "METROFARM v1.0", JOptionPane.OK_CANCEL_OPTION);
            if (Year != null) {
                if (Year.length() < 4 || Year.length() > 4 || util.isNumeric(Year)==false) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un año válido de por lo menos 4 carácteres");
                } else {
                    Calendar calendar=Calendar.getInstance();
                    calendar.set(Calendar.YEAR,Integer.parseInt(Year));
                    try {

                        int resp = fc.showSaveDialog(null);//JFileChooser de nombre RealizarBackupMySQL
                        if (resp == JFileChooser.APPROVE_OPTION) {//Si el usuario presiona aceptar; se genera el Backup
                            try {
                                DtoReport report=new DtoReport();
                                report.setFechaReporte(calendar.getTime());
                                DtoReport response=cmd.reportIndicatorCalibration(report);
                                String path = String.valueOf(fc.getSelectedFile().toString() + "_" + calendar.getTime().getDay()+calendar.getTime().getMonth()+calendar.getTime().getYear() +calendar.getTime().getSeconds()+ ".pdf");
                                File file=new File(path);
                                FileUtils.writeByteArrayToFile(file, response.getFileReporte());

                            } catch (Exception exe) {
                                exe.getMessage();
                                JOptionPane.showMessageDialog(null, "error exportando el reporte: " + exe.getMessage());
                            }

                            JOptionPane.showMessageDialog(null, "reporte generado", "Reportes estadísticos", JOptionPane.INFORMATION_MESSAGE);
                        } else if (resp == JFileChooser.CANCEL_OPTION) {
                            JOptionPane.showMessageDialog(null, "El Reporte ha sido cancelado por el usuario");
                        }

                    } catch (Exception exe) {

                        JOptionPane.showMessageDialog(null, "el error 3 es: " + exe);
                    }                        
                }
            }
        }

        if (e.getSource() == btnIndica_calif) {
            String Year = "";
            Year = JOptionPane.showInputDialog(null, "Por favor ingrese el año para el cual desea generar el indicador", "METROFARM v1.0", JOptionPane.OK_CANCEL_OPTION);
            if (Year != null) {
                if (Year.length() < 4 || Year.length() > 4 || util.isNumeric(Year)==false) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un año válido de por lo menos 4 carácteres");
                } else {
                    Calendar calendar=Calendar.getInstance();
                    calendar.set(Calendar.YEAR,Integer.parseInt(Year));
                    try {

                        int resp = fc.showSaveDialog(null);//JFileChooser de nombre RealizarBackupMySQL
                        if (resp == JFileChooser.APPROVE_OPTION) {//Si el usuario presiona aceptar; se genera el Backup
                            try {
                                DtoReport report=new DtoReport();
                                report.setFechaReporte(calendar.getTime());
                                DtoReport response=cmd.reportIndicatorCalification(report);
                                String path = String.valueOf(fc.getSelectedFile().toString() + "_" + calendar.getTime() + ".pdf");
                                File file=new File(path);
                                FileUtils.writeByteArrayToFile(file, response.getFileReporte());

                            } catch (Exception exe) {
                                exe.getMessage();
                                JOptionPane.showMessageDialog(null, "error exportando el reporte: " + exe.getMessage());
                            }

                            JOptionPane.showMessageDialog(null, "reporte generado", "Reportes estadísticos", JOptionPane.INFORMATION_MESSAGE);
                        } else if (resp == JFileChooser.CANCEL_OPTION) {
                            JOptionPane.showMessageDialog(null, "El Reporte ha sido cancelado por el usuario");
                        }

                    } catch (Exception exe) {

                        JOptionPane.showMessageDialog(null, "el error 3 es: " + exe);
                    }
                }
            }
        }

        if (e.getSource() == menuItem1) {
            String Year = "";
            Year = JOptionPane.showInputDialog(null, "Por favor ingrese el año para el cual desea generar el indicador", "METROFARM v1.0", JOptionPane.OK_CANCEL_OPTION);
            if (Year != null) {
                if (Year.length() < 4 || Year.length() > 4 || util.isNumeric(Year)==false) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un año válido de por lo menos 4 carácteres");
                } else {
                    Calendar calendar=Calendar.getInstance();
                    calendar.set(Calendar.YEAR,Integer.parseInt(Year));
                    try {

                        int resp = fc.showSaveDialog(null);//JFileChooser de nombre RealizarBackupMySQL
                        if (resp == JFileChooser.APPROVE_OPTION) {//Si el usuario presiona aceptar; se genera el Backup
                            try {
                                DtoReport report=new DtoReport();
                                report.setFechaReporte(calendar.getTime());
                                DtoReport response=cmd.reportCalibrationEquipment(report);
                                String path = String.valueOf(fc.getSelectedFile().toString() + "_" + calendar.getTime() + ".pdf");
                                File file=new File(path);
                                FileUtils.writeByteArrayToFile(file, response.getFileReporte());

                            } catch (Exception exe) {
                                exe.getMessage();
                                JOptionPane.showMessageDialog(null, "error exportando el reporte: " + exe.getMessage());
                            }

                            JOptionPane.showMessageDialog(null, "reporte generado", "Reportes estadísticos", JOptionPane.INFORMATION_MESSAGE);
                        } else if (resp == JFileChooser.CANCEL_OPTION) {
                            JOptionPane.showMessageDialog(null, "El Reporte ha sido cancelado por el usuario");
                        }

                    } catch (Exception exe) {

                        JOptionPane.showMessageDialog(null, "el error 3 es: " + exe);
                    }
                }
            }

        }

        if (e.getSource() == menuItem2) {
             String Year = "";
            Year = JOptionPane.showInputDialog(null, "Por favor ingrese el año para el cual desea generar el indicador", "METROFARM v1.0", JOptionPane.OK_CANCEL_OPTION);
            if (Year != null) {
                if (Year.length() < 4 || Year.length() > 4 || util.isNumeric(Year)==false) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un año válido de por lo menos 4 carácteres");
                } else {
                    Calendar calendar=Calendar.getInstance();
                    calendar.set(Calendar.YEAR,Integer.parseInt(Year));
                    try {

                        int resp = fc.showSaveDialog(null);//JFileChooser de nombre RealizarBackupMySQL
                        if (resp == JFileChooser.APPROVE_OPTION) {//Si el usuario presiona aceptar; se genera el Backup
                            try {
                                DtoReport report=new DtoReport();
                                report.setFechaReporte(calendar.getTime());
                                DtoReport response=cmd.reportCalibrationForMonth(report);
                                String path = String.valueOf(fc.getSelectedFile().toString() + "_" + calendar.getTime() + ".pdf");
                                File file=new File(path);
                                FileUtils.writeByteArrayToFile(file, response.getFileReporte());

                            } catch (Exception exe) {
                                exe.getMessage();
                                JOptionPane.showMessageDialog(null, "error exportando el reporte: " + exe.getMessage());
                            }

                            JOptionPane.showMessageDialog(null, "reporte generado", "Reportes estadísticos", JOptionPane.INFORMATION_MESSAGE);
                        } else if (resp == JFileChooser.CANCEL_OPTION) {
                            JOptionPane.showMessageDialog(null, "El Reporte ha sido cancelado por el usuario");
                        }

                    } catch (Exception exe) {

                        JOptionPane.showMessageDialog(null, "el error 3 es: " + exe);
                    }
                }
            }

        }

        if (e.getSource() == menuItem3) {
            String Year = "";
            Year = JOptionPane.showInputDialog(null, "Por favor ingrese el año para el cual desea generar el indicador", "METROFARM v1.0", JOptionPane.OK_CANCEL_OPTION);
            if (Year != null) {
                if (Year.length() < 4 || Year.length() > 4 || util.isNumeric(Year)==false) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un año válido de por lo menos 4 carácteres");
                } else {
                    Calendar calendar=Calendar.getInstance();
                    calendar.set(Calendar.YEAR,Integer.parseInt(Year));
                    try {

                        int resp = fc.showSaveDialog(null);//JFileChooser de nombre RealizarBackupMySQL
                        if (resp == JFileChooser.APPROVE_OPTION) {//Si el usuario presiona aceptar; se genera el Backup
                            try {
                                DtoReport report=new DtoReport();
                                report.setFechaReporte(calendar.getTime());
                                DtoReport response=cmd.reportMaintenanceEquipment(report);
                                String path = String.valueOf(fc.getSelectedFile().toString() + "_" + calendar.getTime() + ".pdf");
                                File file=new File(path);
                                FileUtils.writeByteArrayToFile(file, response.getFileReporte());

                            } catch (Exception exe) {
                                exe.getMessage();
                                JOptionPane.showMessageDialog(null, "error exportando el reporte: " + exe.getMessage());
                            }

                            JOptionPane.showMessageDialog(null, "reporte generado", "Reportes estadísticos", JOptionPane.INFORMATION_MESSAGE);
                        } else if (resp == JFileChooser.CANCEL_OPTION) {
                            JOptionPane.showMessageDialog(null, "El Reporte ha sido cancelado por el usuario");
                        }

                    } catch (Exception exe) {

                        JOptionPane.showMessageDialog(null, "el error 3 es: " + exe);
                    }
                }
            }

        }

        if (e.getSource() == menuItem4) {
             String Year = "";
            Year = JOptionPane.showInputDialog(null, "Por favor ingrese el año para el cual desea generar el indicador", "METROFARM v1.0", JOptionPane.OK_CANCEL_OPTION);
            if (Year != null) {
                if (Year.length() < 4 || Year.length() > 4 || util.isNumeric(Year)==false) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un año válido de por lo menos 4 carácteres");
                } else {
                    Calendar calendar=Calendar.getInstance();
                    calendar.set(Calendar.YEAR,Integer.parseInt(Year));
                    try {

                        int resp = fc.showSaveDialog(null);//JFileChooser de nombre RealizarBackupMySQL
                        if (resp == JFileChooser.APPROVE_OPTION) {//Si el usuario presiona aceptar; se genera el Backup
                            try {
                                DtoReport report=new DtoReport();
                                report.setFechaReporte(calendar.getTime());
                                DtoReport response=cmd.reportMaintenanceMonth(report);
                                String path = String.valueOf(fc.getSelectedFile().toString() + "_" + calendar.getTime() + ".pdf");
                                File file=new File(path);
                                FileUtils.writeByteArrayToFile(file, response.getFileReporte());

                            } catch (Exception exe) {
                                exe.getMessage();
                                JOptionPane.showMessageDialog(null, "error exportando el reporte: " + exe.getMessage());
                            }

                            JOptionPane.showMessageDialog(null, "reporte generado", "Reportes estadísticos", JOptionPane.INFORMATION_MESSAGE);
                        } else if (resp == JFileChooser.CANCEL_OPTION) {
                            JOptionPane.showMessageDialog(null, "El Reporte ha sido cancelado por el usuario");
                        }

                    } catch (Exception exe) {

                        JOptionPane.showMessageDialog(null, "el error 3 es: " + exe);
                    }
                }
            }

        }


    }
}
