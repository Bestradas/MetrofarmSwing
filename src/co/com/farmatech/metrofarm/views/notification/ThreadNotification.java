/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.views.notification;

import co.com.farmatech.metrofarm.cmd.CmdNotification;
import co.com.farmatech.metrofarm.views.MenuPrincipal;
import com.co.farmatech.metrofarm.dto.DtoNotificacion;

/**
 *
 * @author bestrada
 */
public class ThreadNotification implements Runnable {

    MenuPrincipal menu;
    CmdNotification cmdNotifications;

    public ThreadNotification(MenuPrincipal menu) {
        cmdNotifications=new CmdNotification();
        this.menu = menu;
    }

    @Override
    public void run() {
        menu.getLblActualizar().setVisible(false);
        menu.getLblActulizando().setVisible(true);
        DtoNotificacion notifications = cmdNotifications.getNotification(new DtoNotificacion());
        if (notifications != null) {
            //Se setean notificaciones
            menu.getLblNotificacion().setText(notifications.getNotiEquiposNuevos() + "");
            menu.getLblNotificacion().setToolTipText("Hay " + notifications.getNotiEquiposNuevos() + " equipos que acaban de ser ingresados desde ofimática");
            menu.getLblManmes().setText(notifications.getNotiMantenimientosInstrumentosMes()+"");
            menu.getLblManmes().setToolTipText("Hay " + notifications.getNotiMantenimientosInstrumentosMes() + " instrumentos que tienen mantenimiento este mes");
            menu.getLblMantVencido().setText(notifications.getNotiMantenimientosInstrumentosVencidos()+"");
            menu.getLblMantVencido().setToolTipText("Hay " + notifications.getNotiMantenimientosInstrumentosVencidos() + " instrumentos que tienen mantenimientos vencidos");
            menu.getLblMantGenMes().setText(notifications.getNotiMantenimientosEquiposMes()+"");
            menu.getLblMantGenMes().setToolTipText("Hay " + notifications.getNotiMantenimientosEquiposMes() + " equipos que tienen mantenimiento general este mes");
            menu.getLblMantGenVenc().setText(notifications.getNotiMantenimientosEquiposVencidos()+"");
            menu.getLblMantGenVenc().setToolTipText("Hay " + notifications.getNotiMantenimientosEquiposVencidos() + " equipos que tienen mantenimiento general vencidos");
            menu.getLblCalibMes().setText(notifications.getNotiCalibracionesMes() + "");
            menu.getLblCalibMes().setToolTipText("Hay " + notifications.getNotiCalibracionesMes() + " equipos que tienen calibraciones este mes");
            menu.getLblCalibVencidos().setText(notifications.getNotiCalibracionesVencidas() + "");
            menu.getLblCalibVencidos().setToolTipText("Hay " + notifications.getNotiCalibracionesVencidas() + " equipos que tienen calibraciones vencidas");
            menu.getLblCalifMes().setText(notifications.getNotiCalificacionesMes()+"");
            menu.getLblCalifMes().setToolTipText("Hay " + notifications.getNotiCalificacionesMes() + " Equipos que tienen calificación este mes");
            menu.getLblCalifVencidas().setText(notifications.getNotiCalificacionesVencidos()+"");
            menu.getLblCalifVencidas().setToolTipText("Hay " + notifications.getNotiCalificacionesVencidos() + " equipos que tienen calificación vencidas");
            menu.getLblActulizando().setVisible(false);
            menu.getLblActualizar().setVisible(true);
            
        }





    }
}
