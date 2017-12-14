package cl.aduana.gar.web.base;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;
import cl.aduana.seguridad.springsecurity.ConfigCustomLogin;
import cl.aduana.seguridad.springsecurity.CustomInitSecutityConfig;

/**
 * Clase que implementa Controlador JSF para acciones de Acceso a la aplicación
 * (Login)
 * 
 * @author egodoy
 *
 */
@Named
@SessionScoped
public class LoginBean {

	@Inject
	@LoggerUtil
	private Logger logger;

	@Inject
	private JsfUtils jsfUtils;

	@PostConstruct
	public void init() {
		CookieHelper.setCookie("urlUserType", jsfUtils.getUrlAlias(),
				3600 * 1000);
	}

	private String action;

	public String getExtAuthUserType() {
		return ConfigCustomLogin.USUARIO_EXTERNO;
	}

	public String getIntAuthUserType() {
		return ConfigCustomLogin.USUARIO_INTERNO;
	}

	public String getDefaultRequestParam() {
		return ConfigCustomLogin.USUARIO_PARAM_ID;
	}

	public String getAuthPage() {
		return ConfigCustomLogin.getLoginUrl();
	}

	public void actionExit() {
		try {
			String url = CustomInitSecutityConfig.getAccessUserType();
			CookieHelper.removeCookie("urlUserType");
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(url + "&ampfaces-redirect=true");
		} catch (Exception e) {
			logger.error("Error al salir del sistema.", e);
		}
	}

	public void actionSalir() {
		System.out.println("ir a logout");
		jsfUtils.goPage("/logout");
	}

	public boolean getLoginMessage() {
		if (this.getSessionObject("SPRING_SECURITY_LAST_EXCEPTION") != null) {
			this.errorLogin(this.getSessionObject(
					"SPRING_SECURITY_LAST_EXCEPTION").toString());
			return true;
		}
		return false;
	}

	private Object getSessionObject(String objName) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext extCtx = ctx.getExternalContext();
		Map<String, Object> sessionMap = extCtx.getSessionMap();
		return sessionMap.get(objName);
	}

	public void errorLogin(String message) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", message
						.split(":")[1]));
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
