package cl.aduana.gar.web.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import cl.aduana.gar.negocio.modelo.entidades.GloTrazaNotificacion;
import cl.aduana.gar.negocio.modelo.entidades.TrazabilidadServicios;
import cl.aduana.gar.negocio.base.BusinessLiveCycleExcepcion;
import cl.aduana.gar.negocio.base.qualifiers.Audited;
import cl.aduana.gar.negocio.base.qualifiers.LoggedMethod;
import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;
import cl.aduana.gar.negocio.servicios.GloTrazaNotificacionServ;

/**
 * Implementacion de Backing Bean para llamada a servicios de negocio para la entidad &lt;GloTrazaNotificacion&gt;
 * @author egodoy
 *
 */
@ApplicationScoped
@Named
public class GloTrazanotificacionApp {

    @EJB
    private GloTrazaNotificacionServ service;

    @Inject
    @LoggerUtil
    private Logger logger;

    /**
     * Metodo para paginacion de registros de una entidad &lt;GloTrazaNotificacion&gt;
     * @param inicio
     * @param cantRegs
     * @return Lista de objetos GloTrazaNotificacion
     */
    public List<GloTrazaNotificacion> findAll(Integer inicio,
            Integer cantRegs) {
        List<GloTrazaNotificacion> trazaNotificacionList = null;
        try {
        	trazaNotificacionList = service.findAll(inicio, cantRegs);
        } catch (Exception e) {
            logger.error("Error al acceder a la capa de Negocio", e);
        }
        return trazaNotificacionList;
    }

    /**
     * Busca un registro por Id
     * @param id
     * @return Un registro de Entidad &lt;GloTrazaNotificacion&gt;
     */
    @LoggedMethod
    @Audited
    public GloTrazaNotificacion find(Long id) {
        GloTrazaNotificacion trazaNotificacion = null;
        try {
            trazaNotificacion = service.find(id);
            logger.info("todo ok");
        } catch (Exception e) {
            logger.error("Error obtener objeto desde capa de negocio.", e);
        }
        return trazaNotificacion;
    }

    /**
     * Obtiene todos los registros de trazabilidad
     * @return Lista de registros: List&lt;TrazabilidadServicios&gt;
     * @throws BusinessLiveCycleExcepcion
     */
    public List<TrazabilidadServicios> findAllTravilidadTO() throws BusinessLiveCycleExcepcion{
        return service.findAllTrazavilidad();
    } 

    /**
     * Cuenta las filas de resultado
     * @return Numero de filas de la entidad.
     * @throws BusinessLiveCycleExcepcion
     */
    public Integer countRowsOfEntity() throws BusinessLiveCycleExcepcion{
    	return service.countRowsOfEntity();
    }

}
