package cl.aduana.gar.negocio.base;
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
 * @LastUpdate Eduardo Godoy
 * @version 1.0, 17/02/2014
 * @version 2.0, 23/09/2016
 */
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.apache.log4j.Logger;
 
import cl.aduana.gar.negocio.base.auditoria.AuditoriaService;
import cl.aduana.gar.negocio.base.auditoria.Auditoria.TipoOperacion;
import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;
import cl.aduana.gar.negocio.base.utils.GenericsUtils;
/**
 * Clase Abstracta de tipo  generic que implementa los  metodos basicos que debe 
 * tener un Facade que de acceso a operaciones CRUD
 * Esta Clase debe rser extendida desde
 * un EJB concreto y al cual se le inyecta el DAO crud.
 *  
 * @author Eduardo Godoy
 * @version 1.0, 17/02/2014
 * 
 */
public abstract class GenericFacadeImpl<                    
                    ENTITY, 
                    DAO_INTERFACE extends GenericCrudDao<ENTITY>                    
                    >
        implements GenericFacade<ENTITY> {


    public abstract DAO_INTERFACE getDao();

    @Inject
    @LoggerUtil
    private Logger logger;
    
    @EJB
    private AuditoriaService auditService;

   
    /**
     * Recupera desde el registro seg�n el id entregado 
     * por parametro a traves del tipo de Clase definido.
     * @param id Object
     * @return ENTITY DTO 
     */ 
    @Override
    public ENTITY find(Object id) throws BusinessLiveCycleExcepcion {
        try {            
            return this.getDao().find(id);
        } catch (Exception e) {
            throw new BusinessLiveCycleExcepcion(
                    BusinessLiveCycleExcepcion.class.getSimpleName(), e);
        }
    }
    
    
    /**
     * Recupera desde el repositorio todos los registros del tipo instanciado a
     * traves del tipo de Clase definido.
     *  
     * @return List<DTO>
     * 
     */
    @Override
    public List<ENTITY> findAll() throws BusinessLiveCycleExcepcion {
        try {            
            return  this.getDao().findAll();
        } catch (Exception e) {
            throw new BusinessLiveCycleExcepcion(
                    BusinessLiveCycleExcepcion.class.getSimpleName(), e);
        }
    }

    /**
     * Recupera desde el repositorio todos los registros del tipo instanciado a
     * traves del tipo de Clase definido, partiendo desde el registro numero
     * begin y desde ahi extrae la cantidad maxNumReg.
     * 
     * @param first Integer
     * @param pageSize Integer
     * @return List<T>
     * 
     */
    @Override
    public List<ENTITY> findAll(int first, int pageSize)
            throws BusinessLiveCycleExcepcion {
        try {
        	return this.getDao().findAll(first, pageSize);         
        } catch (Exception e) {
            throw new BusinessLiveCycleExcepcion(
                    BusinessLiveCycleExcepcion.class.getSimpleName(), e);
        }
    }

    /**
     * Metodo generico que guarda en el repositorio una nueva instancia del
     * entity bean definido, luego retorna el mismo entity con sus valores
     * actualizados.
     * 
     * @param entity DTO
     * @return ENTITY DTO
     * 
     */
	@Override	    
    public ENTITY insert(ENTITY entity) throws BusinessLiveCycleExcepcion {
        try {      
        	ENTITY entityNew = this.getDao().insert(entity);
            auditService.traceObject(entity, entityNew, TipoOperacion.INSERT);
        	return entity;
        } catch (Exception e) {
        	logger.error(e);
            throw new BusinessLiveCycleExcepcion(
                    BusinessLiveCycleExcepcion.class.getSimpleName(), e);
        }
    }

    /**
     * Metodo generico que elimina del repositorio una instancia de un
     * EntityBean del tipo generico definido una ves implementada la interfaz.
     * @param entity DTO bean
     */
	@Override
    public void delete(ENTITY entity) throws BusinessLiveCycleExcepcion {
        try {            
        	auditService.traceObject(entity, entity, TipoOperacion.DELETE);        	
            this.getDao().delete(entity);            
        } catch (Exception e) {
            throw new BusinessLiveCycleExcepcion(
                    BusinessLiveCycleExcepcion.class.getSimpleName(), e);
        }
    }

    /**
     * Metodo generico que actualiza en repositorio una instancia de un
     * EntityBean del tipo generico definido una ves implementada la interfaz.
     * @param entity DTO bean
     * @return ENTITY DTO
     */
	@Override    
    public ENTITY update(ENTITY entity) throws BusinessLiveCycleExcepcion {
        try {
        	ENTITY entityUpdate = this.getDao().update(entity);
        	auditService.traceObject(entity, entityUpdate,TipoOperacion.UPDATE);
        	return  entity;        
        } catch (Exception e) {
            throw new BusinessLiveCycleExcepcion(
                    BusinessLiveCycleExcepcion.class.getSimpleName(), e);
        }
    }
    

    /**
     * Metodo generico que retorna la cantidad de registros 
     * que contiene una entidad
     * @return Integer
     */
    @Override
    public Integer countRowsOfEntity() throws BusinessLiveCycleExcepcion {
        try {
            @SuppressWarnings("unchecked")
            Class<ENTITY> entity = (Class<ENTITY>) GenericsUtils
                    .getTypeParameterClass(this.getClass(), 0);
            return this.getDao().countRowsOfEntity(entity);
        } catch (Exception e) {
            throw new BusinessLiveCycleExcepcion(
                    BusinessLiveCycleExcepcion.class.getSimpleName(), e);
        }
    }


}

