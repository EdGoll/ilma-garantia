package cl.aduana.gar.web.base.exceptions;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import cl.aduana.gar.web.base.CookieHelper;
import cl.aduana.gar.web.base.JsfUtils;
import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;

/**
 * Clase Backing Bean que implementa el manejo de navegacion "hacia atras" (back) 
 * @author egodoy
 *
 */
@Model
public class ExceptionBean {
	
    @Inject
    @LoggerUtil
    private Logger logger;

	@Inject
	private JsfUtils jsfUtils;
	
	/**
	 * Ejecuta redireccion navegacion "hacia atras" (back).
	 * @return String vacio.
	 */
    public String back() {
   	 	jsfUtils.goPage(CookieHelper.getCookie("urlUserType").getValue());
   	 	logger.info(CookieHelper.getCookie("urlUserType").getValue());
   	 	return "";
    }
	
}
