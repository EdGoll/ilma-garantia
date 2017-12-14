package cl.aduana.gar.negocio.base.producers;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

import cl.aduana.gar.negocio.base.DAOException;
import cl.aduana.gar.negocio.base.auditoria.Auditoria;
import cl.aduana.gar.negocio.base.auditoria.AuditoriaService;
import cl.aduana.gar.negocio.base.qualifiers.Audited;
import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;

/**
 * Clase de tipo interceptor, implementada mediantes CDI
 * Su responsabilidad es implementar la funcionlida de 
 * capa de auditoria mediante el qualifier @Audited.
 * @see Audited 
 * @author Eduardo Godoy
 * @version 1.1, 23/09/2016
 */

@Audited
@Interceptor
public class AuditInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    @LoggerUtil
    private Logger logger;

    @EJB
    private AuditoriaService audit;
    
    /**
     * Metodo que utiliza la capa de acceso a datos JPA-Hibernate para insertar
     * un nuevo objeto  del tipo Auditoria. 
     * El metodo es invocado mediante el framework CDI cada vez que otro metodo 
     * que implemente una funcionalidad de negocio se le agrega 
     * la anotacion @see @Audited
     * @return List<TrazabilidadVO>
     * @throws DAOException
     */
    @AroundInvoke
    public Object audit(InvocationContext context) {
        Auditoria auditoriaDto = new Auditoria();
        Object ret = null;
        String paramType="";
        String paramValue="";
        auditoriaDto.setClassName(context.getTarget()
                .getClass()
                .getSimpleName()
                .subSequence(0,context.getTarget().getClass()
                                .getSimpleName().indexOf('$')).toString());
        auditoriaDto.setMethodName(context.getMethod().getName());
        try {
            ret = context.proceed();            
            for (Object parameter : context.getParameters()) {                                              
                paramType +=parameter.getClass().getSimpleName() +":"+parameter+";";
                paramValue += parameter+";";
            }
            auditoriaDto.setParamType(paramType);   
            auditoriaDto.setParamName(paramValue);
            auditoriaDto.setDate(new Date());
            logger.info(auditoriaDto);
         //   audit.trace(auditoriaDto);
        } catch (Exception e) {
            logger.error(
                    "Error, No se pudo guardar la traza seleccionada seleccionada: "
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