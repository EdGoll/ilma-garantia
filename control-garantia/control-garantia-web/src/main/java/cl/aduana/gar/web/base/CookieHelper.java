package cl.aduana.gar.web.base;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Clase Helper que implementa manejo de cookies HTTP.
 * @author egodoy
 *
 */
public class CookieHelper {

	public CookieHelper() {
		
	}

	/**
	 * Asigna una cookie a la respuesta (response) HTTP.
	 * @param name
	 * @param value
	 * @param expiry
	 */
	public static void setCookie(String name, String value, int expiry) {
		Cookie cookie = getCookie(name);

		if (cookie != null) {
			cookie.setValue(value);
		} else {
			HttpServletRequest request = getHttpServletRequest();
			cookie = new Cookie(name, value);
			cookie.setPath(request.getContextPath());
		}

		cookie.setMaxAge(expiry);
		getHttpServletResponse().addCookie(cookie);

	}

	/**
	 * Obtiene el valor de una cookie identificada por un nombre desde el request HTTP.
	 * @param name
	 * @return Valor de la cookie consultada.
	 */
	public static Cookie getCookie(String name) {
		Cookie cookie = null;
		Cookie[] userCookies = getHttpServletRequest().getCookies();
		if (userCookies != null && userCookies.length > 0) {
			for (int i = 0; i < userCookies.length; i++) {
				if (userCookies[i].getName().equals(name)) {
					cookie = userCookies[i];
					return cookie;
				}
			}
		}
		return null;
	}

	/**
	 * Borra una cookie en la respuesta (response) HTTP.
	 * @param cookieName
	 */
	public static void removeCookie(String cookieName) {
		Cookie cookie = getCookie(cookieName);
		cookie.setMaxAge(0);
		getHttpServletResponse().addCookie(cookie);
	}

	/**
	 * Obtiene el contexto Request HTTP.
	 * @return Objeto HttpServletRequest 
	 */
	private static HttpServletRequest getHttpServletRequest() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (HttpServletRequest) facesContext.getExternalContext()
				.getRequest();
	}

	/**
	 * Obtiene el contexto Response HTTP.
	 * @return Objeto HttpServletResponse 
	 */
	private static HttpServletResponse getHttpServletResponse() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (HttpServletResponse) facesContext.getExternalContext()
				.getResponse();
	}

}
