package cl.aduana.gar.negocio.servicios;

import java.util.List;

import cl.aduana.gar.negocio.modelo.entidades.GloTrazaNotificacion;
import cl.aduana.gar.negocio.modelo.entidades.TrazabilidadServicios;
import cl.aduana.gar.negocio.base.BusinessLiveCycleExcepcion;
import cl.aduana.gar.negocio.base.GenericFacade;

/**
 * Interface para el manejo de acciones sobre la entidad GloTrazaNotificacion.
 * @author Eduardo Godoy.
 * 
 */
public interface GloTrazaNotificacionServ extends
        GenericFacade<GloTrazaNotificacion> {
    
    List<TrazabilidadServicios> findAllTrazavilidad() throws BusinessLiveCycleExcepcion;
    
}
