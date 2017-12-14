package cl.aduana.gar.web.base;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 * Clase que implementa Controlador de opciones de navegacion del menu de
 * opciones.
 * 
 * @author egodoy
 *
 */
@Model
public class MenuCtrl {

	@Inject
	private JsfUtils jsfUtils;

	public void goProveedores() {
		jsfUtils.goPage("/principal/proveedores");
	}

	public void goInicio() {
		jsfUtils.goPage("/principal");
	}

	public void goPersonas() {
		jsfUtils.goPage("/principal/personas");
	}
	
	public void goPage(String prettyUrl) {
		jsfUtils.goPage(prettyUrl);
	}

	public void goAuditoria() {
		jsfUtils.goPage("/principal/auditoria");
	}

}
