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
import com.co.farmatech.metrofarm.dto.DtoEquipo;
import com.co.farmatech.metrofarm.dto.PageData;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author bestrada
 */
public class CmdEquipment {

    Properties properties = AccessProperties.getInstance().getProperties();
    ServiceLocator locator = ServiceLocator.getInstance();

    public ObjectResponse findEquipments(DtoEquipo equipoFind, PageData pageData) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.EQUIPMENT_SERVICE, Operations.FIND_EQUIPMENT, equipoFind,pageData);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                return objResponse;

            } else {
                throw new JankinsMarshallException(objResponse.getDescription(), objResponse.getErrorCode(), objResponse.getDescription(), "ERROR", 1l);
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }

    }

    public boolean findEquipmentExist(DtoEquipo equipoFind) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.EQUIPMENT_SERVICE, Operations.FIND_EQUIPMENT_EXIST, equipoFind);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                if (objResponse.getObj() != null && objResponse.getObj() instanceof DtoEquipo) {
                    return true;
                } else {
                    return false;
                }
            } else {
                throw new JankinsMarshallException(objResponse.getDescription(), objResponse.getErrorCode(), objResponse.getDescription(), "ERROR", 1l);
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
    }
    
       public boolean createEquipment(DtoEquipo equipoFind) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.EQUIPMENT_SERVICE, Operations.CREATE_EQUIPMENT, equipoFind);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                return true;
            }else{
                JOptionPane.showMessageDialog(null, objResponse.getDescription(),"Código "+objResponse.getErrorCode(),JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
    }
       
     public boolean updateEquipment(DtoEquipo equipoFind) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.EQUIPMENT_SERVICE, Operations.UPDATE_EQUIPMENT, equipoFind);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                return true;
            }else{
                JOptionPane.showMessageDialog(null, objResponse.getDescription(),"Código "+objResponse.getErrorCode(),JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
    }
       
       
    
    
}
