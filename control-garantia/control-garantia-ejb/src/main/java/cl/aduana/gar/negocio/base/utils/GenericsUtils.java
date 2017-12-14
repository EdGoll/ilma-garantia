package cl.aduana.gar.negocio.base.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Clase Util para manejo de <a
 * href="https://docs.oracle.com/javase/tutorial/java/generics/why.html"
 * >Generics</a>.
 * 
 * @author Eduardo Godoy.
 *
 */
public class GenericsUtils {

	private GenericsUtils() {
		// constructor vacio.
	}

	/**
	 * Obtiene los tipos de datos de los parametros.
	 * 
	 * @param clazz
	 * @param index
	 * @return Tipo de parametro del objeto
	 */
	public static Object getTypeParameterClass(Class<?> clazz, int index) {
		Type type = clazz.getGenericSuperclass();
		ParameterizedType paramType = (ParameterizedType) type;
		return (Object) paramType.getActualTypeArguments()[index];
	}

}
