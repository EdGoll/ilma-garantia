package cl.aduana.gar.negocio.base;

/**
 * Clase   que contiene  metodos genericos de acceso a datos los cuales que
 * pueden ser utilizados para paginación
 * Esta clase debe conocer previamente el entitymanager, el cual es inyectado mediante CDI.
 * @author Eduardo Godoy
 * @LastUpdate Eduardo Godoy
 * @version 1.0, 17/02/2014 
 * 
 */

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;
import cl.aduana.gar.negocio.base.utils.GenericsUtils;

public abstract class GenericPaginationDaoImpl<ENTITY,ID_TYPE> implements GenericPaginationDao<ENTITY,ID_TYPE> {

    private static final int POSITION_ENTITY_CLASS_TYPE = 0;
    private Class<ENTITY> entityClass = null;
    
    @Inject
    @LoggerUtil
    private Logger logger;    

    public abstract EntityManager getEntityManager();

    @SuppressWarnings("unchecked")
    @PostConstruct
    private void init() {
        this.entityClass = (Class<ENTITY>) GenericsUtils.getTypeParameterClass(
                this.getClass(), POSITION_ENTITY_CLASS_TYPE);
    }

    /**
     * Recupera desde el registro seg�n el id entregado 
     * por parametro
     * traves del tipo de Clase definido.
     * 
     * @param id Object Id
     * @return ENTITY T
     * 
     */ 
    @Override
    public ENTITY find(Object id) {
        return this.getEntityManager().find(entityClass, id);
    }

    /**
     * Recupera desde el repositorio todos los registros del tipo instanciado a
     * traves del tipo de Clase definido.
     *  
     * @return List<T>
     * 
     */
    @Override
    public List<ENTITY> findAll() {
        return findAll(entityClass);
    }

    /**
     * Recupera desde el repositorio todos los registros del tipo instanciado a
     * traves del tipo de Clase definido.
     * 
     * @param Class<T> 
     * @return List<T>
     * 
     */    
    @SuppressWarnings("unchecked")
    private List<ENTITY> findAll(Class<ENTITY> clazz) {
        String query = "FROM " + clazz.getSimpleName();
        Query queryH = this.getEntityManager().createQuery(query);
        return queryH.getResultList();

    }

    /**
     * Recupera desde el repositorio todos los registros del tipo instanciado a
     * traves del tipo de Clase definido, partiendo desde el registro numero
     * begin y desde ahi extrae la cantidad maxNumReg.
     * 
     * @param begin Integer 
     * @param maxNumReg Integer 
     * @return List<T>
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ENTITY> findAll(Integer begin, Integer maxNumReg) {
        String query = "FROM " + entityClass.getSimpleName();
        Query queryH = this.getEntityManager().createQuery(query)
                .setFirstResult(begin).setMaxResults(maxNumReg);
        return queryH.getResultList();

    }

    /**
     * Metodo generico que retorna la cantidad de registros 
     * que contiene una entidad
     * @param clazz
     * @return Integer
     */
    @Override
    public Integer countRowsOfEntity(Class<ENTITY> clazz) {
        Integer numRows = 0;
        try {
            Query query = this.getEntityManager().createQuery(
                    "select count(o) from " + clazz.getSimpleName() + " o");
            numRows = Integer.valueOf(String.valueOf(query.getSingleResult()));
            return numRows;
        } catch (Exception e) {
            logger.error("Error en la fuente de acceso a datos.", e);
        }
        return numRows;
    }

}
