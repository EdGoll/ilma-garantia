package cl.aduana.gar.negocio.base.producers;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

import cl.aduana.gar.negocio.base.qualifiers.LoggedMethod;
import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;

/**
 * Clase de tipo interceptor, implementada mediante CDI
 * la capacidad de logear lo que ha recivido un metodo 
 * invocado, a través de la anotación @LooggerMethod
 * @see cl.aduana.gar.negocio.base.qualifiers.LoggedMethod
 * @author Eduardo Godoy
 * @version 1.1, 23/09/2016
 */

@LoggedMethod
@Interceptor
public class LoggerInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final DateFormat DATE_FORMAT = SimpleDateFormat
            .getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
    
    @Inject
    @LoggerUtil
    private Logger logger;

    @AroundInvoke
    public Object logEntity(InvocationContext context) {        
        Object ret = null;
        try {
            ret = context.proceed();            
            for (Object parameter : context.getParameters()) {                              
                logger.info("\n\n **LOGGER METHOD**  "
                        + "\n Tier: " +"WEB"
                        + "\n Class: "+ context .getTarget()
                                .getClass()
                                .getSimpleName()
                                .subSequence(0,context.getTarget().getClass()
                                                .getSimpleName().indexOf('$'))
                        + "\n Method-Name: "+context.getMethod().getName()
                        + "\n Parameter-Type: "+ parameter.getClass().getSimpleName()
                        + "\n Parameter-Value: " + parameter 
                        + "\n Date: " + DATE_FORMAT.format(new Date()) 
                        + "\n");
            }
        } catch (Exception e) {
            logger.error(
                    "Error, No se pudo logear el metodo seleccionado en "
                            + context
                                    .getTarget()
                                    .getClass()
                                    .getSimpleName()
                                    .subSequence(
                                            0,
                                            context.getTarget().getClass()
                                                    .getSimpleName()
                                                    .indexOf('$')), e);
        }
        return ret;
    }
    

}