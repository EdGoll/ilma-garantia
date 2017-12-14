package cl.aduana.gar.negocio.base;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;
/**
 * Clase que se encarga de invocar un datasource para el caso
 * que se requiera implementar el patron Dao producto del 
 * llamado a packages pl/sql
 * 
 * @author Eduardo Godoy
 * @version 1.0, 17/02/2014
 * @version 1.1, 23/09/2016
 */
public class ServiceLocator {

    @Inject
    @LoggerUtil
    private Logger logger;

    private Map<String, Object> cache;
    private InitialContext ic;
    private static ServiceLocator me;

    static {
    
            me = new ServiceLocator();

    }

    @PostConstruct
    private void init() {
        try {
            ic = new InitialContext();
            cache = Collections.synchronizedMap(new HashMap<String, Object>());
        } catch (NamingException ex) {
            logger.error("Error tratar de recuper el contexto de Negocio", ex);
        }

    }

    public static final ServiceLocator getInstance() {
        return me;
    }

    /**
     * Retorna el data source configurado a nivel de servidor
     * seg�n el nombre jndi entregado por parametro.
     * @param jndiHomeName String
     * @return Object ds 
     */ 
    public final Object getDataSource(String jndiHomeName)
            throws ServiceLocatorException {
        Object dataSource = null;
        try {
            if (cache.containsKey(jndiHomeName)) {
                dataSource = (Object) cache.get(jndiHomeName);
            } else {
                dataSource = (Object) ic.lookup(jndiHomeName);
                cache.put(jndiHomeName, dataSource);
            }
        } catch (NamingException ex) {
            throw new ServiceLocatorException("JNDI DataSource incorrecto", ex);
        } catch (Exception e) {
            throw new ServiceLocatorException(
                    "Error al obtener el DataSource.", e);
        }
        return dataSource;
    }

}