package cl.aduana.gar.negocio.base.utils;

import java.util.StringTokenizer;

/**
 * Clase Util que implementa metodos para manejo de java.lang.String.
 * 
 * @author Eduardo Godoy.
 *
 */
public class StringUtils {

	private StringUtils() {
		// constructor vacio.
	}

	/**
	 * Agrega caracteres a un String.
	 * 
	 * @param valorString
	 *            String a modificar
	 * @param largo
	 *            Cantidad de caracteres a agregar
	 * @param caracter
	 *            Caracter a agregar
	 * @param izquierda
	 *            true: Agrega a la Izquierda; false: Agrega a la derecha.
	 * @return String modificado de acuerdo a los parametros indicados.
	 */
	public static String paddingString(String valorString, int largo,
			char caracter, boolean izquierda) {
		StringBuilder str = new StringBuilder(valorString);
		int strLength = str.length();
		if (largo > 0 && largo > strLength) {
			for (int i = 0; i < largo; i++) {
				if (izquierda) {
					if (i < largo - strLength) {
						str.insert(0, caracter);
					}
				} else {
					if (i > strLength) {
						str.append(caracter);
					}
				}
			}
		}
		return str.toString();
	}

	/**
	 * Capitaliza una palabra. Solo la primera letra es Capitalizada (Convierte
	 * a mayusculas)
	 *
	 * @param word
	 *            Palabra a capitalizar.
	 * @return La palabra Capitalizada.
	 */
	public static String capitalize(String word) {
		return word.toUpperCase().substring(0, 1) + word.substring(1);
	}

	/**
	 * Des-Capitalizar una palabra. Solo la primera letra es Des-Capitalizada.
	 *
	 * @param word
	 *            Palabra a Des-Capitalizar
	 * @return La palabra Des-Capitalizada.
	 */
	public static String unCapitalize(String word) {
		return word.toLowerCase().substring(0, 1) + word.substring(1);
	}

	/**
	 * Camelizar una palabra separada por "guion abajo" (underscore) <br />
	 * Ejemplo: aaa_bbb => aaaBbb
	 *
	 * @param frase
	 *            - La palabra que contiene separador "guion abajo"
	 *            (underscore).
	 * @return La palabra Camelizada.
	 */
	public static String camelize(String frase) {
		String underscore = "_";
		String separator = " ";
		if (frase.indexOf(underscore) != -1) {
			separator = underscore;
		}
		StringTokenizer st = new StringTokenizer(frase, separator);
		String ret = st.nextToken();
		while (st.hasMoreTokens()) {
			ret += StringUtils.capitalize(st.nextToken());
		}
		return ret;
	}

	/**
	 * Evalua si un string es vacio. Un string es vacio si: es null, es un
	 * string vacio o si string.trim() es un string vacio.
	 *
	 * @param string
	 *            - El String a analizar.
	 * @return boolean <br/>
	 *         - <b>true:</b> El String es vacio <br/>
	 *         - <b>false:</b> en otro caso.
	 */
	public static boolean isEmpty(String string) {
		return string == null ? true : "".equals(string.trim());
	}

	/**
	 * Evalua si un objeto es un String vacio.
	 * 
	 * @param object
	 *            Objeto a analizar como String.
	 * @return boolean <br/>
	 *         - <b>true:</b> El String es vacio <br/>
	 *         - <b>false:</b> el String no es vacio.
	 */
	public static boolean isEmptyString(Object object) {
		return object == null ? true : "".equals(object.toString().trim());
	}

	/**
	 * Transforma un string, filtrando solo los caracteres alfabeticos [a-z,A-Z,
	 * ' ']
	 * 
	 * @param string
	 *            El string a analizar.
	 * @return String filtrado solo con los caracteres permitidos.
	 */
	public static String transformToAlphabetic(String string) {
		StringBuilder out = new StringBuilder();
		String sigma = "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < string.length(); i++) {
			out.append((sigma.indexOf(string.charAt(i)) != -1) ? string
					.charAt(i) : ' ');
		}
		return out.toString();
	}

}
