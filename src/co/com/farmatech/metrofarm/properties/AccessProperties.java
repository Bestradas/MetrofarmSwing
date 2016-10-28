/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.properties;

import co.jankins.psf.common.exception.JankinsMarshallException;
import java.util.Properties;

/**
 *
 * @author bestrada
 */
public class AccessProperties {

	private static AccessProperties instance;

	/**
	 * Metodo que permite instanciar una sola vez la clase de AccessProperties
	 * 
	 * @return
	 * @author Bryan
	 */
	public static synchronized AccessProperties getInstance(){
		if (instance == null) {
			instance = new AccessProperties();
			return instance;
		}
		return instance;
	}

	/**
	 * Metodo que permite obtener los datos del archivo de configuracion
	 * para la conexion.
	 *  
	 * @return Properties
	 * @author Bryan
	 */
	public Properties getProperties(){
		try {
			//se crea una instancia a la clase Properties
			Properties properties = new Properties();
			//se leen el archivo .properties
			properties.load( getClass().getResourceAsStream("locator.properties") );
			//si el archivo de propiedades NO esta vacio retornan las propiedes leidas
			if (!properties.isEmpty()){
				return properties;
			}else{
				throw new JankinsMarshallException("El archivo de propiedades esta vacio");
			}

		} catch (Exception e) {
			throw new JankinsMarshallException("No se pudieron obtener las propiedades del Service Locator", e);
		}
	}

}