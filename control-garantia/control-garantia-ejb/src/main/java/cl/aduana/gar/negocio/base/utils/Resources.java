package cl.aduana.gar.negocio.base.utils;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Clase Util para el manejo de <a href=
 * "https://docs.oracle.com/javase/7/docs/api/java/util/ResourceBundle.html"
 * >ResourceBundle</a>.
 * 
 * @author Eduardo Godoy
 */

public class Resources {

	private static final String PROPERTIES_PATH = "properties/propertiesPath";

	private Resources() {
		// constructor vacio.
	}

	/**
	 * Obtiene valor desde archivo de propiedades, indicando path (paquete) y key.
	 * @param path
	 * @param key
	 * @return Valor asociado a un key, ubicado en un path (paquete).
	 */
	public static String getValue(String path, String key) {
		ResourceBundle res = PropertyResourceBundle.getBundle(Resources
				.getPropertiesPath(path));
		return res.getString(key);
	}

	/**
	 * Obtiene objento de archivo de propiedades (ResourceBundle), indicando una ruta (path).
	 * @param path
	 * @return Objeto ResourceBundle con instancia del archivo de propiedades indicado en el path.
	 */
	public static ResourceBundle getPageList(String path) {
		return PropertyResourceBundle.getBundle(Resources
				.getPropertiesPath(path));
	}

	/**
	 * Obtiene valor desde archivo de propiedades, a traves de su key.
	 * @param key
	 * @return Valor asociado a un key.
	 */
	public static String getPropertiesPath(String key) {
		ResourceBundle res = PropertyResourceBundle.getBundle(PROPERTIES_PATH);
		return res.getString(key);
	}

}