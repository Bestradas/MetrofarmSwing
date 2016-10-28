/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.cmd;

import co.com.farmatech.metrofarm.locator.ServiceLocator;
import co.com.farmatech.metrofarm.properties.AccessProperties;
import co.janker.dto.objresponse.ObjectResponse;
import co.jankins.psf.common.exception.JankinsMarshallException;
import co.jankins.psf.common.operations.Operations;
import co.jankins.psf.common.services.Services;
import com.co.farmatech.metrofarm.dto.DtoNotificacion;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author bestrada
 */
public class CmdNotification {

    Properties properties = AccessProperties.getInstance().getProperties();
    ServiceLocator locator = ServiceLocator.getInstance();

    public DtoNotificacion getNotification(DtoNotificacion notification) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.NOTIFICATION_SERVICE, Operations.GET_NOTIFICATIONS, notification);
            if (objResponse.getObj() != null && objResponse.getObj() instanceof DtoNotificacion) {
                return ((DtoNotificacion) objResponse.getObj());
            } else {
                throw new JankinsMarshallException(objResponse.getDescription(), objResponse.getErrorCode(), objResponse.getDescription(), "ERROR", 1l);
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
    }
}
