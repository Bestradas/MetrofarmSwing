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
import com.co.farmatech.metrofarm.dto.DtoRegistroCambios;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author bestrada
 */
public class CmdChangeRegister {

    Properties properties = AccessProperties.getInstance().getProperties();
    ServiceLocator locator = ServiceLocator.getInstance();

    public boolean createChangeRegister(DtoRegistroCambios registroCambios) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.CHANGE_REGISTER_SERVICE, Operations.CREATE_CHANGE_REGISTER, registroCambios);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
    }

    public ArrayList<DtoRegistroCambios> listChangeRegister(DtoRegistroCambios registroCambios) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.CHANGE_REGISTER_SERVICE, Operations.LIST_CHANGE_REGISTER, registroCambios);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                return objResponse.getRecords() == null ? new ArrayList<DtoRegistroCambios>() : (ArrayList<DtoRegistroCambios>) objResponse.getRecords();

            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                throw new JankinsMarshallException(objResponse.getDescription(), objResponse.getErrorCode(), objResponse.getDescription(), "ERROR", 1l);
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
    }
}
