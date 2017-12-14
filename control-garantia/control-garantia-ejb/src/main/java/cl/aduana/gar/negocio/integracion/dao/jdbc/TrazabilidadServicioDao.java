package cl.aduana.gar.negocio.integracion.dao.jdbc;

import java.util.List;

import cl.aduana.gar.negocio.modelo.entidades.TrazabilidadServicios;
import cl.aduana.gar.negocio.base.DAOException;

/**
 * Interfaz que define los metodos especificos de acceso a datos mediante JDBC.
 * 
 * @author Eduardo Godoy
 * @version 1.0, 17/02/2014
 * 
 */
public interface TrazabilidadServicioDao {
    /**
     * Metodo que retorna una lista de objetos java Bean desde una funte de
     * datos atraves de un SQL. Realiza el mapeo desde un ResultSet a un
     * JavaBean del tipo TrazabilidadServicioBean
     * 
     * @return List<TrazabilidadServicioBean>
     * 
     */
    public List<TrazabilidadServicios> getAllTrazabilidadServicioBean()
            throws DAOException;
}
