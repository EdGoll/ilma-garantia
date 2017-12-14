package cl.aduana.gar.negocio.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

/**
 * Clase abstracta de la que pueden heredar Objetos Entities para agregar una
 * coleccion de key, Values.
 * 
 * @author Eduardo Godoy.
 *
 */
public abstract class ExtraModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String USER_PRINCIPAL = "USR_ACT";

	@Transient
	private Map<String, Object> modelProperties = new HashMap<String, Object>();

	public Map<String, Object> getModelProperties() {
		return modelProperties;
	}

	public void setModelProperties(Map<String, Object> modelProperties) {
		this.modelProperties = modelProperties;
	}

	public void setUserPrincipal(Object userName) {
		this.getModelProperties().put(USER_PRINCIPAL, userName);
	}

	public Object getUserPrincipal() {
		return this.getModelProperties().get(USER_PRINCIPAL);
	}

}
