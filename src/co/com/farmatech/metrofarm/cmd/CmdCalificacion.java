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
import com.co.farmatech.metrofarm.dto.DtoCalificacion;
import com.co.farmatech.metrofarm.dto.DtoEquipo;
import com.co.farmatech.metrofarm.dto.DtoProgramCalificacion;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author bestrada
 */
public class CmdCalificacion {

    Properties properties = AccessProperties.getInstance().getProperties();
    ServiceLocator locator = ServiceLocator.getInstance();

    public boolean createFirstCalification(DtoCalificacion calificacion) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.QUALIFICATION_SERVICE, Operations.CREATE_FIRST_CALIFICATION, calificacion);
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

    public boolean createCalification(DtoCalificacion calificacion) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.QUALIFICATION_SERVICE, Operations.CREATE_CALIFICATION, calificacion);
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

    public ArrayList<DtoCalificacion> findCalifications(DtoEquipo equipo) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.QUALIFICATION_SERVICE, Operations.FIND_CALIFICATION, equipo);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                if (objResponse.getRecords() != null) {
                    return (ArrayList<DtoCalificacion>) objResponse.getRecords();
                }
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new ArrayList<DtoCalificacion>();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
        return new ArrayList<DtoCalificacion>();
    }
    
        public ArrayList<DtoProgramCalificacion> findCalificationsMonth(DtoEquipo equipo) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.QUALIFICATION_SERVICE, Operations.FIND_CALIFICATION_MONTH_SCHEDULED, equipo);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                if (objResponse.getRecords() != null) {
                    return (ArrayList<DtoProgramCalificacion>) objResponse.getRecords();
                }
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new ArrayList<DtoProgramCalificacion>();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
        return new ArrayList<DtoProgramCalificacion>();
    }
        
        public ArrayList<DtoProgramCalificacion> findCalificationsExpired(DtoEquipo equipo) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.QUALIFICATION_SERVICE, Operations.FIND_CALIFICATION_MONTH_EXPIRED, equipo);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                if (objResponse.getRecords() != null) {
                    return (ArrayList<DtoProgramCalificacion>) objResponse.getRecords();
                }
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new ArrayList<DtoProgramCalificacion>();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
        return new ArrayList<DtoProgramCalificacion>();
    }
        
            public ArrayList<DtoProgramCalificacion> findCalificationsProgramming(DtoEquipo equipo) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.QUALIFICATION_SERVICE, Operations.FIND_CALIFICATION_PROGRAMMING, equipo);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                if (objResponse.getRecords() != null) {
                    return (ArrayList<DtoProgramCalificacion>) objResponse.getRecords();
                }
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new ArrayList<DtoProgramCalificacion>();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
        return new ArrayList<DtoProgramCalificacion>();
    }
    
}
