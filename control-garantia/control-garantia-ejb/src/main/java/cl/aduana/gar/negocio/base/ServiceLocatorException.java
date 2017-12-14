package cl.aduana.gar.negocio.base;
/**
 * Clase que permite personalizar excepciones provenientes de la no 
 * invocación a un componente mediante jndi
 * 
 * @author Eduardo Godoy
 * @version 1.0, 03/02/2013
 * 
 */
public class ServiceLocatorException extends Exception {

    private static final long serialVersionUID = 1L;

    public ServiceLocatorException() {
        super();
    }

    public ServiceLocatorException(Throwable causa) {
        super(causa);
    }

    public ServiceLocatorException(String message) {
        super(message);
    }

    public ServiceLocatorException(String message, Throwable cause) {
        super(message, cause);
    }

}
