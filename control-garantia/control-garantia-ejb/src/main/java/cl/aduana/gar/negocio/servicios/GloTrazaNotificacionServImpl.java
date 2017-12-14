package cl.aduana.gar.negocio.servicios;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import cl.aduana.gar.negocio.base.BusinessLiveCycleExcepcion;
import cl.aduana.gar.negocio.base.GenericFacadeImpl;
import cl.aduana.gar.negocio.integracion.dao.persistencia.GloTrazaNotificaDao;
import cl.aduana.gar.negocio.modelo.entidades.GloTrazaNotificacion;
import cl.aduana.gar.negocio.modelo.entidades.TrazabilidadServicios;

/**
 * Clase del tipo EJB 3 SessionBean utilizada como patron Facade encargada de
 * ser la unica puerta de entrada y salida entre la capa de negocio y la capa
 * cliente. Posee todos los metodos de negocio implementados desde su interfaz
 * remota y se encarga de generar los requerimientos a la capa de accesos a
 * datos la cual devolvera objetos de negocio, los cuales seran mapeados a un
 * objeto DTO para ser enviado a la capa cliente y esta a su vez a la capa que
 * lo requiera.
 * 
 * @author Eduardo Godoy
 * @version 1.0, 17/02/2014
 * @see cl.aduana.module.negocio.service.GloTrazaNotificacionService
 */
@Local(GloTrazaNotificacionServ.class)
@Stateless
public class GloTrazaNotificacionServImpl extends
		GenericFacadeImpl<GloTrazaNotificacion, GloTrazaNotificaDao>
		implements GloTrazaNotificacionServ {

	@Inject
	private GloTrazaNotificaDao dao;

	@Override
	public GloTrazaNotificaDao getDao() {
		return dao;
	}

	/**
	 * Obtiene todos los registros de trazabilidad.
	 * 
	 * @return Lista de objetos de TrazabilidadServicios.
	 */
	@Override
	public List<TrazabilidadServicios> findAllTrazavilidad()
			throws BusinessLiveCycleExcepcion {
		List<TrazabilidadServicios> list = null;
//		try {
//			list = jdbcDao.getAllTrazabilidadServicioBean();
//		} catch (DAOException e) {
//			throw new BusinessLiveCycleExcepcion("Error en la Fuente de Datos",
//					e.getCause());
//		}
		return list;
	}

}
