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
import com.co.farmatech.metrofarm.dto.DtoPerfil;
import com.co.farmatech.metrofarm.dto.PageData;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author bestrada
 */
public class CmdProfile {

    Properties properties = AccessProperties.getInstance().getProperties();
    ServiceLocator locator = ServiceLocator.getInstance();

    public ObjectResponse findProfiles(DtoPerfil perfil, PageData pageData) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.PROFILE_SERVICE, Operations.FIND_PROFILE, perfil,pageData);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                    return objResponse;
                
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new JankinsMarshallException(e);
        }

        return null;
    }

    public boolean createProfile(DtoPerfil perfil) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.PROFILE_SERVICE, Operations.CREATE_PROFILE, perfil);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                if (objResponse.getObj()!= null) {
                    return true;
                }
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new JankinsMarshallException(e);
        }
        return false;
    }
    
        public boolean updateProfile(DtoPerfil perfil) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.PROFILE_SERVICE, Operations.UPDATE_PROFILE, perfil);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                if (objResponse.getObj()!= null) {
                    return true;
                }
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new JankinsMarshallException(e);
        }
        return false;
    }
}
