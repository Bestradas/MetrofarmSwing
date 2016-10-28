/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.cmd;

import co.com.farmatech.metrofarm.locator.ServiceLocator;
import co.janker.dto.objresponse.ObjectResponse;
import java.util.Date;

/**
 *
 * @author bestrada
 */
public class CmdCommons {
    
    ServiceLocator locator= ServiceLocator.getInstance();
    
    public Date fechaActual(){
        ObjectResponse obj=(ObjectResponse)locator.execute("http://localhost:8080/ProyectoBaseMaven", "COMMON_SERVICE", "FECHA_ACTUAL", null);
        if(obj.getErrorCode().equals("000")){
            return null;
        }
        return null;
    }
    
}
