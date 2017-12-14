package cl.aduana.gar.negocio.integracion.dao.persistencia;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import cl.aduana.gar.negocio.modelo.entidades.GloTrazaNotificacion;
import cl.aduana.gar.negocio.base.GenericCrudDaoImpl;
import cl.aduana.gar.negocio.base.qualifiers.DataBase;
import cl.aduana.gar.negocio.base.qualifiers.DataBase.DataBaseType;

/**
 * Clase del tipo EJB SessionBean que implementa los metodos desde su interfazz
 * local necesarios para operar con la capa de datos y la Entidad
 * GloTrazaNotificacion.-
 * 
 * @author Eduardo Godoy
 * @version 1.0, 12/03/2014
 * @see cl.aduana.gar.negocio.integracion.dao.persistencia.GloTrazaNotificacionDao
 * @see cl.aduana.gar.negocio.integracion.model.entities.GloTrazaNotificacion
 * @see cl.aduana.gar.negocio.base.GenericCrudDaoImpl
 */

@Named
public class GloTrazaNotificaDaoImpl 
    extends GenericCrudDaoImpl<GloTrazaNotificacion> 
    implements GloTrazaNotificaDao {

    @Inject
    @DataBase(type = DataBaseType.LOCAL)
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return this.em;
    }

}
