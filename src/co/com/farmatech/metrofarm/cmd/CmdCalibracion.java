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
import com.co.farmatech.metrofarm.dto.DtoCalibracion;
import com.co.farmatech.metrofarm.dto.DtoEquipo;
import com.co.farmatech.metrofarm.dto.DtoProveedor;
import com.co.farmatech.metrofarm.dto.PageData;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author bestrada
 */
public class CmdCalibracion {

    Properties properties = AccessProperties.getInstance().getProperties();
    ServiceLocator locator = ServiceLocator.getInstance();

    public boolean createCalibration(DtoCalibracion calibration) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.CALIBRATION_SERVICE, Operations.CREATE_FIRST_CALIBRATION, calibration);
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
    
        public boolean createCalibrationAndSchedule(DtoCalibracion calibration) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.CALIBRATION_SERVICE, Operations.CREATE_CALIBRATION, calibration);
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
    
        public boolean updateCalibration(DtoCalibracion calibration) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.CALIBRATION_SERVICE, Operations.UPDATE_CALIBRATION, calibration);
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
    
        public boolean deleteCalibration(DtoCalibracion calibration) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.CALIBRATION_SERVICE, Operations.DELETE_CALIBRATION, calibration);
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
    
    
        public ObjectResponse findCalibrations(DtoEquipo equipo, PageData pageData) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.CALIBRATION_SERVICE, Operations.FIND_CALIBRATION, equipo,pageData);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                    return objResponse;

            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new ObjectResponse();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
    }
        
                public ArrayList<DtoCalibracion> findCalibrations(DtoCalibracion calibracion) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.CALIBRATION_SERVICE, Operations.FIND_CALIBRATION, calibracion);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                if(objResponse.getRecords()!=null){
                    return (ArrayList<DtoCalibracion>)objResponse.getRecords();
                }
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new ArrayList<DtoCalibracion>();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
        return new ArrayList<DtoCalibracion>();
    }
        
      public ArrayList<DtoCalibracion> findCalibrationsMonthScheduled(DtoEquipo equipo) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.CALIBRATION_SERVICE, Operations.FIND_CALIBRATION_MONTH_SCHEDULED, equipo);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                if(objResponse.getRecords()!=null){
                    return (ArrayList<DtoCalibracion>)objResponse.getRecords();
                }
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new ArrayList<DtoCalibracion>();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
        return new ArrayList<DtoCalibracion>();
    }
      
     public ArrayList<DtoCalibracion> findCalibrationsMonthExpired(DtoEquipo equipo) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.CALIBRATION_SERVICE, Operations.FIND_CALIBRATION_MONTH_EXPIRED, equipo);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                if(objResponse.getRecords()!=null){
                    return (ArrayList<DtoCalibracion>)objResponse.getRecords();
                }
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new ArrayList<DtoCalibracion>();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
        return new ArrayList<DtoCalibracion>();
    }
     
    public ArrayList<DtoProveedor> findProviders(DtoProveedor provider) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.CALIBRATION_SERVICE, Operations.GET_PROVIDERS, provider);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                if(objResponse.getRecords()!=null){
                    return (ArrayList<DtoProveedor>)objResponse.getRecords();
                }
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new ArrayList<DtoProveedor>();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
        return new ArrayList<DtoProveedor>();
    }

}
