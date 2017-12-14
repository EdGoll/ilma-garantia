package cl.aduana.gar.negocio.base;

/**
 * Clase que permite personalizar excepciones provenientes de la capa de negocio
 * tales como sql exception, etc.
 * 
 * @author Eduardo Godoy
 * @version 1.0, 05/09/2016
 * 
 */

public class BusinessLiveCycleExcepcion extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -1757577541215311529L;

    /**
     * Contructor que recibe una cadena de string que representa un mensaje de
     * error personalizado por el desarrollador.
     * 
     * @param message Nombre de proyecto EAR
     * @param cause Throwble
     */
    public BusinessLiveCycleExcepcion(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Metodo encargado de retornar el valor del EJB desde su interfaz remota.
     * 
     * @param message String Nombre de proyecto EAR
     * @param cause Nombre de Session bean que implementa la interfaz Remota
     */
    public BusinessLiveCycleExcepcion(String message, Exception cause) {
        super(message, cause);

    }
}
