package cl.aduana.gar.web.base.utils;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Ejemplo de excepciones: NullPointerException, IllegalStateException y ViewExpiredException
 * Corresponde a una clase del <a href="http://www.primefaces.org/showcase/"> showcase de primefaces</a>
 * @author primefaces.org
 *
 */
@ManagedBean
@RequestScoped
public class ExceptionHandlerView {
	
	/**
	 * Lanza NullPointerException
	 */
	public void throwNullPointerException() {
        throw new NullPointerException("A NullPointerException!");
    }

	/**
	 * Lanza IllegalStateException
	 */
    public void throwWrappedIllegalStateException() {
        Throwable t = new IllegalStateException("A wrapped IllegalStateException!");
        throw new FacesException(t);
    }

    /**
     * Lanza ViewExpiredException
     */
    public void throwViewExpiredException() {
        throw new ViewExpiredException("A ViewExpiredException!",
                FacesContext.getCurrentInstance().getViewRoot().getViewId());
    }

}