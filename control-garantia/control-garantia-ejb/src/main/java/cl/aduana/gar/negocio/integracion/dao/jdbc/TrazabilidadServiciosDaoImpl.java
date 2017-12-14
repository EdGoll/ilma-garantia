package cl.aduana.gar.negocio.integracion.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import cl.aduana.gar.negocio.modelo.entidades.TrazabilidadServicios;
import cl.aduana.gar.negocio.base.DAOException;
import cl.aduana.gar.negocio.base.OracleDAO;
import cl.aduana.gar.negocio.base.qualifiers.DataBase;
import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;
import cl.aduana.gar.negocio.base.qualifiers.DataBase.DataBaseAccessBy;
import cl.aduana.gar.negocio.base.utils.Resources;

/**
 * Clase de acceso a datos mediante jdbc
 * @author Eduardo Godoy
 * @LastUpdate Eduardo Godoy
 * @version 1.0, 17/02/2014
 * 
 */

@Named
public class TrazabilidadServiciosDaoImpl 
	extends OracleDAO 
	implements TrazabilidadServicioDao {
  
    @Inject
    @LoggerUtil
    private Logger logger;
	

    private Connection conn;

    /**
     * cmetodo que retorna una lista de objetos java Bean desde una funte de
     * datos atraves de un sql. Realiza el mapeo desde un ResultSet a un
     * JavaBean del tipo TrazabilidadServicioBean
     * 
     * @return {@link List<TrazabilidadServicioBean>}.
     * 
     **/
    public List<TrazabilidadServicios> getAllTrazabilidadServicioBean()
            throws DAOException {
        TrazabilidadServicios trazabilidadServicioBean = null;
        List<TrazabilidadServicios> listTrazabilidadVOs = null;
//        try {            
//            stmt = conn.prepareStatement(Resources.getValue(ORACLE_QUERYS,
//                    "queryGetAllTrazabildadServicios"));
//            rs = stmt.executeQuery();
//            listTrazabilidadVOs = new ArrayList<TrazabilidadServicios>();
//            while (rs.next()) {
//                trazabilidadServicioBean = new TrazabilidadServicios();
//                trazabilidadServicioBean
//                        .setDocumento(rs.getString("documento"));
//                trazabilidadServicioBean.setEstado(rs.getString("estado"));
//                trazabilidadServicioBean.setGlosa(rs.getString("glosa"));
//                trazabilidadServicioBean
//                        .setCategoria(rs.getString("categoria"));
//                trazabilidadServicioBean.setServicio(rs.getString("servicio"));
//                trazabilidadServicioBean.setTipo(rs.getString("tipo"));
//                trazabilidadServicioBean
//                        .setProtocolo(rs.getString("protocolo"));
//                listTrazabilidadVOs.add(trazabilidadServicioBean);
//            }
//        } catch (SQLException ex) {
//        	logger.error("getAllTrazabilidadVO() -> Error en la Fuente de Datos");
//            throw new DAOException("Error en la Fuente de Datos" + " ["
//                    + TrazabilidadServiciosDaoImpl.class.getSimpleName()
//                    + " getAllTrazabilidadVO()" + "]", ex);
//        } finally {
//            this.connectionObjectsToNull();
//        }
        return listTrazabilidadVOs;
    }
    
}
