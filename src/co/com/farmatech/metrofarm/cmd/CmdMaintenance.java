/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.cmd;

import co.com.farmatech.metrofarm.locator.ServiceLocator;
import co.com.farmatech.metrofarm.properties.AccessProperties;
import co.janker.dto.objresponse.ObjectResponse;
import co.jankins.psf.common.enums.TipoEquipo;
import co.jankins.psf.common.exception.JankinsMarshallException;
import co.jankins.psf.common.operations.Operations;
import co.jankins.psf.common.services.Services;
import com.co.farmatech.metrofarm.dto.DtoEquipo;
import com.co.farmatech.metrofarm.dto.DtoMantenimiento;
import com.co.farmatech.metrofarm.dto.PageData;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author bestrada
 */
public class CmdMaintenance {

    Properties properties = AccessProperties.getInstance().getProperties();
    ServiceLocator locator = ServiceLocator.getInstance();

    //MAINTENANCE GENERAL
    public boolean createFirstMaintenanceGeneral(DtoMantenimiento mantenimiento) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.MAINTENANCE_GENERAL_SERVICE, Operations.CREATE_FIRST_MAINTENANCE, mantenimiento);
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

    public boolean createMaintenanceGeneral(DtoMantenimiento mantenimiento) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.MAINTENANCE_GENERAL_SERVICE, Operations.CREATE_MAINTENANCE, mantenimiento);
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

    public boolean updateMaintenanceGeneral(DtoMantenimiento mantenimiento) {
        DtoEquipo equipo = new DtoEquipo();
        equipo.setTipo(TipoEquipo.EQUIPO.getCode());
        mantenimiento.setEquipo(equipo);
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.MAINTENANCE_GENERAL_SERVICE, Operations.UPDATE_MAINTENANCE, mantenimiento);
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

    public ObjectResponse findMaintenanceEquipment(DtoEquipo equipo, PageData pageData) {
        DtoMantenimiento maintenance = new DtoMantenimiento();
        equipo.setTipo(TipoEquipo.EQUIPO.getCode());
        maintenance.setEquipo(equipo);
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.MAINTENANCE_GENERAL_SERVICE, Operations.FIND_MAINTENANCE, maintenance,pageData);
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

    public ArrayList<DtoMantenimiento> findMaintenanceForEquipments(DtoMantenimiento mantenimiento) {
        DtoEquipo equipo = new DtoEquipo();
        equipo.setTipo(TipoEquipo.EQUIPO.getCode());
        mantenimiento.setEquipo(equipo);
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.MAINTENANCE_GENERAL_SERVICE, Operations.FIND_MAINTENANCE, mantenimiento);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                if (objResponse.getRecords() != null) {
                    return (ArrayList<DtoMantenimiento>) objResponse.getRecords();
                }
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new ArrayList<DtoMantenimiento>();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
        return new ArrayList<DtoMantenimiento>();
    }

    public ArrayList<DtoMantenimiento> findMaintenancesMonthScheduledEquipment(DtoEquipo equipo) {
        equipo.setTipo(TipoEquipo.EQUIPO.getCode());
        DtoMantenimiento mantenimiento = new DtoMantenimiento();
        mantenimiento.setEquipo(equipo);
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.MAINTENANCE_GENERAL_SERVICE, Operations.FIND_MAINTENANCE_MONTH_SCHEDULED, mantenimiento);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                if (objResponse.getRecords() != null) {
                    return (ArrayList<DtoMantenimiento>) objResponse.getRecords();
                }
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new ArrayList<DtoMantenimiento>();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
        return new ArrayList<DtoMantenimiento>();
    }

    public ArrayList<DtoMantenimiento> findMaintenancesExpiredEquipment(DtoEquipo equipo) {
        equipo.setTipo(TipoEquipo.EQUIPO.getCode());
        DtoMantenimiento mantenimiento = new DtoMantenimiento();
        mantenimiento.setEquipo(equipo);
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.MAINTENANCE_GENERAL_SERVICE, Operations.FIND_MAINTENANCE_MONTH_EXPIRED, mantenimiento);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                if (objResponse.getRecords() != null) {
                    return (ArrayList<DtoMantenimiento>) objResponse.getRecords();
                }
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new ArrayList<DtoMantenimiento>();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
        return new ArrayList<DtoMantenimiento>();
    }

    public boolean createFirstMaintenanceInstrument(DtoMantenimiento mantenimiento) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.MAINTENANCE_SERVICE, Operations.CREATE_FIRST_MAINTENANCE, mantenimiento);
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

    public ObjectResponse findMaintenanceInstrument(DtoEquipo equipo,PageData pageData) {
        equipo.setTipo(TipoEquipo.INSTRUMENTO.getCode());
        DtoMantenimiento mantenimiento = new DtoMantenimiento();
        mantenimiento.setEquipo(equipo);
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.MAINTENANCE_SERVICE, Operations.FIND_MAINTENANCE, mantenimiento,pageData);
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

    public ArrayList<DtoMantenimiento> findMaintenancesExpiredInstrumnet(DtoEquipo equipo) {
        equipo.setTipo(TipoEquipo.INSTRUMENTO.getCode());
        DtoMantenimiento mantenimiento = new DtoMantenimiento();
        mantenimiento.setEquipo(equipo);
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.MAINTENANCE_SERVICE, Operations.FIND_MAINTENANCE_MONTH_EXPIRED, mantenimiento);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                if (objResponse.getRecords() != null) {
                    return (ArrayList<DtoMantenimiento>) objResponse.getRecords();
                }
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new ArrayList<DtoMantenimiento>();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
        return new ArrayList<DtoMantenimiento>();
    }

    public ArrayList<DtoMantenimiento> findMaintenancesMonthScheduledInstrument(DtoEquipo equipo) {
        equipo.setTipo(TipoEquipo.INSTRUMENTO.getCode());
        DtoMantenimiento mantenimiento = new DtoMantenimiento();
        mantenimiento.setEquipo(equipo);
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.MAINTENANCE_SERVICE, Operations.FIND_MAINTENANCE_MONTH_SCHEDULED, mantenimiento);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                if (objResponse.getRecords() != null) {
                    return (ArrayList<DtoMantenimiento>) objResponse.getRecords();
                }
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new ArrayList<DtoMantenimiento>();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
        return new ArrayList<DtoMantenimiento>();
    }

    public ArrayList<DtoMantenimiento> findMaintenanceForInstruments(DtoMantenimiento mantenimiento) {
        DtoEquipo equipo = new DtoEquipo();
        equipo.setTipo(TipoEquipo.INSTRUMENTO.getCode());
        mantenimiento.setEquipo(equipo);
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.MAINTENANCE_GENERAL_SERVICE, Operations.FIND_MAINTENANCE, mantenimiento);
            if (objResponse.getErrorCode() != null && objResponse.getErrorCode().equals("000")) {
                if (objResponse.getRecords() != null) {
                    return (ArrayList<DtoMantenimiento>) objResponse.getRecords();
                }
            } else {
                JOptionPane.showMessageDialog(null, objResponse.getDescription(), "Código " + objResponse.getErrorCode(), JOptionPane.ERROR_MESSAGE);
                return new ArrayList<DtoMantenimiento>();
            }
        } catch (Exception e) {
            throw new JankinsMarshallException(e);
        }
        return new ArrayList<DtoMantenimiento>();
    }

    public boolean updateMaintenance(DtoMantenimiento mantenimiento) {
        DtoEquipo equipo = new DtoEquipo();
        equipo.setTipo(TipoEquipo.INSTRUMENTO.getCode());
        mantenimiento.setEquipo(equipo);
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.MAINTENANCE_SERVICE, Operations.UPDATE_MAINTENANCE, mantenimiento);
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

    public boolean createMaintenance(DtoMantenimiento mantenimiento) {
        try {
            ObjectResponse objResponse = locator.execute(properties.getProperty("locator.url"), Services.MAINTENANCE_SERVICE, Operations.CREATE_MAINTENANCE, mantenimiento);
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
}
