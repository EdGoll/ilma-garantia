package cl.aduana.gar.negocio.base.producers;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;
/**
 * Clase que implementar el metodo productor que
 * permite obtener la instancia de Logger
 * mediante CDI. Esta capacidad se obtiene al utilizar
 * de forma declarativa la anotacion @LoggerUtil cuando 
 * se define el logger como atributo de la clase.
 *   
 * @author Eduardo Godoy. 
 * @version 1.0, 20/09/2016
 */
@Dependent
public class LoggerProducer {

	@Produces
	@LoggerUtil
	public Logger produceLog(InjectionPoint injectionPoint){		
		return  LogManager.getLogger(injectionPoint.getMember().getDeclaringClass());
	}
}
