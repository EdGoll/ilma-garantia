package cl.aduana.gar.negocio.base.utils;

import java.text.SimpleDateFormat;

/**
 * Clase Util para manejo de tipo de datos java.util.Date
 * @author Eduardo Godoy.
 *
 */
public class DateUtils {
	
    private DateUtils() {
    	//constructor vacio.
    }

    /**
     * Gives date according to a fromat.
     * 
     * @param format
     *            string according to java.text.SimpleDateFormat
     * @return the date string according to format
     */
    public static String dateString(String format, java.util.Date theDate) {
        SimpleDateFormat theFormat = new SimpleDateFormat(format);
        return theFormat.format(theDate);
    }
}
