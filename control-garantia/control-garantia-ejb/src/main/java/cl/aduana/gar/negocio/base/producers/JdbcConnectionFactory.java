package cl.aduana.gar.negocio.base.producers;

import java.sql.Connection;
import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import cl.aduana.gar.negocio.base.qualifiers.DataBase;
import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;
import cl.aduana.gar.negocio.base.qualifiers.DataBase.DataBaseAccessBy;
/**
 * Clase que gestiona las conecciones a base de datos. 
 * mediante jdbc en conjunto a la implementación del 
 * patron DAO.
 * Esta clase contiene el metodo que permite inyectar
 * un objeto de conexion cada ves que se inyecte  en una
 * clase DAO.
 * @see TrazabilidadServiciosDaoImpl
 * @author Eduardo Godoy. 
 * @version 1.0, 20/09/2016
 */ 

public class JdbcConnectionFactory {
    
    @Inject
    @LoggerUtil
    private Logger logger;
    
//    @Inject
//    @DataBase(accessBy = DataBaseAccessBy.DAO_JDBC) 
//    private  DataSource defaultDataSource;
       
    /*Metodo que provee la conexion cuando la instancia de clase Connection
     * es inyectada en el DAO utilizando @Inyect y @DataBase*/
//    @Produces
//    @RequestScoped
//    @DataBase(accessBy = DataBaseAccessBy.DAO_JDBC)
//    public  Connection createDefaultConnection() throws SQLException {
//    	logger.info("Opening conection...");
//        Connection connection =defaultDataSource.getConnection();
//        return connection;
//    }
//          
//    /*Metodo que genera el cierre de la conexion cuando el 
//     * metodo que consume la conexion termina */
//    public  void closeDefaultConnection(
//                @Disposes  
//                @DataBase(accessBy=DataBaseAccessBy.DAO_JDBC)  
//                Connection conn
//            ) throws SQLException {
//    	logger.info("Closing conection...");
//        conn.close();
//    }
//            
}
