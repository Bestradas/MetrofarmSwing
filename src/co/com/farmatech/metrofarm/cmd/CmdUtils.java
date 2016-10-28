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
import com.co.farmatech.metrofarm.dto.DtoUsuario;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author bestrada
 */
public class CmdUtils {
    
    Properties properties=AccessProperties.getInstance().getProperties();
    ServiceLocator locator=ServiceLocator.getInstance();
    
    public void enviarEmail (DtoUsuario usuario){
        try{
           ObjectResponse objResponse=locator.execute(properties.getProperty("locator.url"), Services.EMAIL_SERVICE, Operations.FORGGET_PASSWORD, usuario);
          if(objResponse.getErrorCode()!=null && objResponse.getErrorCode().equals("000")){
              
          }else{
              JOptionPane.showMessageDialog(null,objResponse.getDescription(),"Código "+objResponse.getErrorCode(),JOptionPane.ERROR_MESSAGE);
              throw new JankinsMarshallException(objResponse.getDescription());
          }
        }catch(Exception e){
            throw new JankinsMarshallException(e);
        }

    }
    
        public void synchronization (DtoEquipo equipo){
        try{
           ObjectResponse objResponse=locator.execute(properties.getProperty("locator.url"), Services.EQUIPMENT_SERVICE, Operations.SYNCHRONICE, equipo);
          if(objResponse.getErrorCode()!=null && objResponse.getErrorCode().equals("000")){
              
          }else{
              JOptionPane.showMessageDialog(null,objResponse.getDescription(),"Código "+objResponse.getErrorCode(),JOptionPane.ERROR_MESSAGE);
              throw new JankinsMarshallException(objResponse.getDescription());
          }
        }catch(Exception e){
            throw new JankinsMarshallException(e);
        }

    }
    
}
