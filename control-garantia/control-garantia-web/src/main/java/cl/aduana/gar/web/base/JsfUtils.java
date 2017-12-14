package cl.aduana.gar.web.base;
/**
 * Clase de asistencia para utilizar funciones proporcionadas
 * por JSF se forma directa, encapsulando particularidades.
 * 
 * @author Eduardo Godoy
 * @LastUpdate Eduardo Godoy
 * @version 1.0, 04/01/2016
 */

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;

import com.ocpsoft.pretty.PrettyContext;

/**
 * Clase que implementa metodos de apoyo para manejo de JSF y PrettyFaces.
 * @author egodoy
 *
 */
@Named
public class JsfUtils {

	@Inject
	@LoggerUtil
	private Logger logger;

	/**
	 * Redirecciona navegacion hacia ruta URL PrettyFaces.
	 * @param prettyFacesUrl
	 */
	public void goPage(String prettyFacesUrl) {
		String urlRootPath = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestContextPath();
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(urlRootPath + prettyFacesUrl);

		} catch (Exception e) {
			logger.error(
					"Error al cargar la pagina seleccionada. Destino no encontrado ",
					e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:",
							"Destino no encontrado."));
		}
	}

	/**
	 * Obtiene URL Alias (PrettyFaces)
	 * @return URL &lt;PrettyContext&gt;
	 */
	public String getUrlAlias() {
		return PrettyContext.getCurrentInstance().getRequestURL().toURL();
	}

	/**
	 * Obtiene contexto JSF
	 * @param objName
	 * @return Contexto JSF Object&ltFacesContext;&gt;
	 */
	public static Object getObjectFromSession(String objName) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get(objName);
	}

	/**
	 * Actualiza (Update) objeto en sesion JSF.
	 */
	public static void putObjectInSession(String key, Object object) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(key, object);
	}

	/**
	 * Elimina (Delete) objeto en sesion JSF.
	 */
	public static Object removeObjectFromSession(String objName) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().remove(objName);
	}

}
