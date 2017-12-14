package cl.aduana.gar.web.base.exceptions;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import cl.aduana.gar.web.base.JsfUtils;
import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;

/**
 * Clase que implementa la gestión personalizada de excepciones.
 * @author egodoy
 *
 */
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

    @Inject
    @LoggerUtil
    private Logger logger; 
    
    private ExceptionHandler wrapped;
    private static String exceptionUrl = "/principal/exception";

    
    private JsfUtils jsfUtils = new JsfUtils();    
    
    CustomExceptionHandler(ExceptionHandler exception) {
        this.wrapped = exception;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }
  
    @Override
    public void handle() throws FacesException {
        StringBuilder builder = new StringBuilder(); 
        final Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
        
        while (i.hasNext()) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            Throwable t = context.getException();
             for (StackTraceElement st : context.getException().getStackTrace()) {
                builder.append(st.toString()+"\n");               
            }
            
            final Map<String, Object> sessionMap = FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap();
                jsfUtils.goPage(exceptionUrl);
                sessionMap.put("EXCEPTION_MESSAGE_CAUSE", t.getMessage());
                sessionMap.put("EXCEPTION_STACK_TRACE", builder);
                sessionMap.put("EXCEPTION_STACK_TRACE_", context.getException().getStackTrace());
                
        }
        
        getWrapped().handle();
    }
    

}
