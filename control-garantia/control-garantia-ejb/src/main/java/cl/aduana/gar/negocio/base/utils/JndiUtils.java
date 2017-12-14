package cl.aduana.gar.negocio.base.utils;

/**
 * Clase Util para el manejo de <a
 * href="http://docs.oracle.com/javase/tutorial/jndi/">JNDI (Java Naming and
 * Directory Interface)</a>
 * 
 * @author Eduardo Godoy
 *
 */
public class JndiUtils {

	private static final String SUFFIX_LOCAL = "Local";
	private static final String SUFFIX_REMOTE = "Remote";
	private static final String SUFFIX_JNDI_LOCAL = "/local";
	private static final String SUFFIX_JNDI_REMOTE = "/remote";

	private static final String PARENT_PROYECT = Resources.getValue(
			"negocioProject", "earProjectParentName").trim();

	private JndiUtils() {
	}

	public static String buildJndiName(Class<?> interfaceEjb) {
		if (interfaceEjb == null) {
			return null;
		} else if (interfaceEjb.getSimpleName().endsWith(SUFFIX_LOCAL)) {
			return buildJndiLocal(interfaceEjb);
		} else if (interfaceEjb.getSimpleName().endsWith(SUFFIX_REMOTE)) {
			return buildJndiRemote(interfaceEjb);
		}
		return null;
	}

	public static String buildJndiLocal(Class<?> interfaceLocal) {
		if (interfaceLocal == null
				|| !interfaceLocal.getSimpleName().endsWith(SUFFIX_LOCAL)) {
			return null;
		}
		return PARENT_PROYECT
				+ "/"
				+ interfaceLocal.getSimpleName().replace(SUFFIX_LOCAL,
						SUFFIX_JNDI_LOCAL);
	}

	public static String buildJndiRemote(Class<?> interfaceRemote) {
		if (interfaceRemote == null
				|| !interfaceRemote.getSimpleName().endsWith(SUFFIX_LOCAL)) {
			return null;
		}
		return PARENT_PROYECT
				+ "/"
				+ interfaceRemote.getSimpleName().replace(SUFFIX_REMOTE,
						SUFFIX_JNDI_REMOTE);
	}

	public static String getNameEJB(Class<?> interfaceEjb) {
		if (interfaceEjb == null) {
			return null;
		} else if (interfaceEjb.getSimpleName().endsWith(SUFFIX_LOCAL)) {
			return interfaceEjb.getSimpleName().replace(SUFFIX_LOCAL, "");
		} else if (interfaceEjb.getSimpleName().endsWith(SUFFIX_REMOTE)) {
			return interfaceEjb.getSimpleName().replace(SUFFIX_REMOTE, "");
		}

		return null;

	}
}
