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
import com.co.farmatech.metrofarm.dto.DtoReport;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author bestrada
 */
public class CmdReports {

    Properties properties = AccessProperties.getInstance().getProperties();
    ServiceLocator locator = ServiceLocator.getInstance();

    public DtoReport reportCalibrationEquipment(DtoReport report) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.REPORT_SERVICE, Operations.REPORT_CALIBRATION_FOR_EQUIPMENT, report);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                return (DtoReport) objResponse.getObj();
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new DtoReport();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
    }

    public DtoReport reportCalibrationForMonth(DtoReport report) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.REPORT_SERVICE, Operations.REPORT_CALIBRATION_FOR_MONTH, report);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                return (DtoReport) objResponse.getObj();
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new DtoReport();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
    }

    public DtoReport reportIndicatorCalibration(DtoReport report) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.REPORT_SERVICE, Operations.INDICATOR_CALIBRATION, report);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                return (DtoReport) objResponse.getObj();
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new DtoReport();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
    }
    
    public DtoReport reportIndicatorCalification(DtoReport report) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.REPORT_SERVICE, Operations.INDICATOR_CALIFICATION, report);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                return (DtoReport) objResponse.getObj();
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new DtoReport();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
    }
    
    public DtoReport reportIndicatorMaintenanceGeneral(DtoReport report) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.REPORT_SERVICE, Operations.INDICATOR_MAINTENANCE_GENERAL, report);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                return (DtoReport) objResponse.getObj();
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new DtoReport();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
    }    
    
    public DtoReport reportMaintenanceEquipment(DtoReport report) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.REPORT_SERVICE, Operations.MAINTENANCE_FOR_EQUIPMENT, report);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                return (DtoReport) objResponse.getObj();
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new DtoReport();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
    }  
    
    public DtoReport reportMaintenanceGeneralMonth(DtoReport report) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.REPORT_SERVICE, Operations.MAINTENANCE_FOR_MONTH, report);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                return (DtoReport) objResponse.getObj();
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new DtoReport();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
    }  
    
    public DtoReport reportMaintenanceMonth(DtoReport report) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.REPORT_SERVICE, Operations.MAINTENANCE_FOR_EQUIPMENT_GENERAL, report);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                return (DtoReport) objResponse.getObj();
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new DtoReport();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
    } 
    
    public DtoReport reportMaintenanceMonthGeneral(DtoReport report) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.REPORT_SERVICE, Operations.MAINTENANCE_FOR_MONTH_GENERAL, report);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                return (DtoReport) objResponse.getObj();
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new DtoReport();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
    }     
    
}
