package cl.aduana.gar.negocio.base;

import java.util.List;

/**
 * Interfaz generica que define los m�todos basicos que debe tener un Facade
 * implementado en su capa de negocio. Esta interfaz debe rser extendida desde
 * la interfaz propia de cada Facade respecto a su negocio, y debe ser declarada
 * con el tipo de VO que deberar trabajar mediante su vcaracteristica Generic.
 * Ver ejemplo en @see TrazabilidadFacadeRemote
 * 
 * @author Eduardo Godoy
 * @version 1.0, 17/02/2014
 */

public interface GenericFacade<ENTITY> {

    public ENTITY find(Object id) throws BusinessLiveCycleExcepcion;

    /**
     * Metodo que utiliza la capa de acceso a datos JPA-Hibernate para recuperar
     * los documentos notificados por JBoss-ESB *
     * 
     * @return List<GloTrazaNotificacionDTO>
     * @throws DAOException
     */
    public List<ENTITY> findAll() throws BusinessLiveCycleExcepcion;

    public List<ENTITY> findAll(int first, int pageSize) throws BusinessLiveCycleExcepcion;
    
    /**
     * Metodo que utiliza la capa de acceso a datos JPA-Hibernate para insertar
     * un nuevo documento del tipo por JBoss-ESB.
     * @param entity
     * @return List<TrazabilidadVO>
     * @throws DAOException
     */
    public ENTITY insert(ENTITY entity) throws BusinessLiveCycleExcepcion;

    /**
     * Metodo que utiliza la capa de acceso a datos JPA-Hibernate para eliminar
     * un nuevo documento del tipo por JBoss-ESB.
     * 
     * @throws DAOException
     */
    public void delete(ENTITY entity) throws BusinessLiveCycleExcepcion;

    /**
     * Metodo que utiliza la capa de acceso a datos JPA-Hibernate para
     * actualizar un nuevo documento del tipo por JBoss-ESB.
     * 
     * @param entity
     * @return List<TrazabilidadVO>
     * @throws DAOException
     */
    public ENTITY update(ENTITY entity) throws BusinessLiveCycleExcepcion;

    public Integer countRowsOfEntity() throws BusinessLiveCycleExcepcion;
}
