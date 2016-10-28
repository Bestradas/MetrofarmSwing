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
import com.co.farmatech.metrofarm.dto.DtoUsuario;
import com.co.farmatech.metrofarm.dto.PageData;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author bestrada
 */
public class CmdUsuarios {

    Properties properties = AccessProperties.getInstance().getProperties();
    ServiceLocator locator = ServiceLocator.getInstance();

    public DtoUsuario authenticate(DtoUsuario usuario) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.USER_SERVICE, Operations.AUTHENTICATE_USER, usuario);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                if (objResponse.getObj() != null && objResponse.getObj() instanceof DtoUsuario) {
                    return ((DtoUsuario) objResponse.getObj());
                } else {
                    throw new JankinsMarshallException(objResponse.getDescription(), objResponse.getErrorCode(), objResponse.getDescription(), "ERROR", 1l);
                }
            } else {
                throw new JankinsMarshallException(objResponse.getDescription(), objResponse.getErrorCode(), objResponse.getDescription(), "ERROR", 1l);
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }

    }

    public DtoUsuario getUserByDoc(DtoUsuario usuario) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.USER_SERVICE, Operations.USER_BY_DOCUMENT, usuario);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                if (objResponse.getObj() != null && objResponse.getObj() instanceof DtoUsuario) {
                    return ((DtoUsuario) objResponse.getObj());
                }
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new JankinsMarshallException(e);
        }

        return null;
    }

    public boolean createUsers(DtoUsuario usuario) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.USER_SERVICE, Operations.CREATE_USER, usuario);
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
    
        public boolean updateUser(DtoUsuario usuario) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.USER_SERVICE, Operations.UPDATE_USER, usuario);
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

    public ObjectResponse findUsers(DtoUsuario usuario, PageData pageData) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.USER_SERVICE, Operations.FIND_USERS, usuario,pageData);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                    return  objResponse;
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new JankinsMarshallException(e);
        }

        return null;
    }
}
