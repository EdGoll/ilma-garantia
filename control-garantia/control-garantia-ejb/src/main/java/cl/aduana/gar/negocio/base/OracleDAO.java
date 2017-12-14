package cl.aduana.gar.negocio.base;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Clase que define los parametros reutilizables para acceder a una fuente de
 * datos especifica
 * 
 * @author Eduardo Godoy
 * @version 1.0, 17/02/2014
 * 
 */

public class OracleDAO {

	public static final String ORACLE_QUERYS = "oracleQuerys";	
    protected PreparedStatement stmt = null;
    protected CallableStatement call = null;
    protected ResultSet rs = null;    
    
    public void connectionObjectsToNull(){
    	if(rs != null){
    		rs = null;
    	}    	
    	if(stmt != null){
    		stmt = null;
    	}
    	if(call != null){
    		call = null;
    	}
    }

}
