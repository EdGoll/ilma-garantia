package cl.aduana.gar.negocio.base;

/**
 * Clase Padre generica que contiene m�todos genericos de acceso a datos los cuales que
 * pueden ser utilizados para cualquier accion que requiera de acceso a datos mediante.
 * Esta clase debe conocer prebiamente el entity manager, el cual es entregado por el EJB 
 * que la heredera dentro de la capa de negocio.
 *  

 * @author Eduardo Godoy
 * @LastUpdate Eduardo Godoy
 * @version 1.0, 17/02/2014 
 * 
 */

import java.util.List;
import java.util.Map;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;
import cl.aduana.gar.negocio.base.utils.GenericsUtils;
import cl.aduana.gar.negocio.base.utils.StringUtils;

public abstract class GenericCrudDaoImpl<T> implements GenericCrudDao<T> {
	
    @Inject
    @LoggerUtil
    private Logger logger;
    
    private static final int POSITION_ENTITY_CLASS_TYPE = 0;
    private Class<T> entityClass = null;

    public abstract EntityManager getEntityManager();

    @SuppressWarnings("unchecked")
    @PostConstruct
    private void init() {
        this.entityClass = (Class<T>) GenericsUtils.getTypeParameterClass(
                this.getClass(), POSITION_ENTITY_CLASS_TYPE);
    }

    /**
     * Recupera desde el registro seg�n el id entregado 
     * por parametro
     * traves del tipo de Clase definido.
     * 
     * @param id Object
     * @return T
     * 
     */ 
    @Override
    public T find(Object id) {
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
    public List<T> findAll() {
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
    private List<T> findAll(Class<T> clazz) {
        String query = "FROM " + clazz.getSimpleName();
        return this.getEntityManager().createQuery(query).getResultList();

    }

    /**
     * Recupera desde el repositorio todos los registros del tipo instanciado a
     * traves del tipo de Clase definido, partiendo desde el registro numero
     * begin y desde ahi extrae la cantidad maxNumReg.
     * 
     * @param begin Integer
     * @param maxNumReg Integer maxNumReg
     * @return List<T>
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll(Integer begin, Integer maxNumReg) {
        String query = "FROM " + entityClass.getSimpleName();
        Query queryH = this.getEntityManager().createQuery(query)
                .setFirstResult(begin).setMaxResults(maxNumReg);
        return queryH.getResultList();

    }

    /**
     * Metodo generico que guarda en el repositorio una nueva instancia del
     * entity bean definido, luego retorna el mismo entity con sus valores
     * actualizados.
     * 
     * @param entity T
     * @return T
     * 
     */
    @Override
    public T insert(T entity) {
        this.getEntityManager().persist(entity);
        this.getEntityManager().flush();
        this.getEntityManager().refresh(entity);
        return entity;
    }

    /**
     * Metodo generico que elimina del repositorio una instancia de un
     * EntityBean del tipo generico definido una ves implementada la interfaz.
     * 
     */
    @Override
    public void delete(T entity) {
        try {
            Object managed = this.getEntityManager().merge(entity);
            this.getEntityManager().remove(managed);
            this.getEntityManager().flush();
        } catch (Exception e) {
        	logger.error(
                    "Error en la fuente de acceso a datos al eliminar registro.",
                    e);
        }
    }

    /**
     * Metodo generico que actualiza en repositorio una instancia de un
     * EntityBean del tipo generico definido una ves implementada la interfaz.
     * @param entity T
     * @return T
     */
    @Override
    public T update(T entity) {
        try {
            this.getEntityManager().merge(entity);
            this.getEntityManager().flush();
        } catch (Exception e) {
        	logger.error(
                    "Error en la fuente de acceso a datos al realizar Actualizaci�n de registro.",
                    e);
        }
        return entity;
    }

    /**
     * Metodo retorna un mensaje de verificaci�n, mediante el cual se puede
     * verificar o no la conectividad a la capa de negocio remota, luego retorna
     * su valor actualizado.
     * 
     * @return String
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findEntitiesByString(String query) {
        return this.getEntityManager().createQuery(query).getResultList();
    }

    /**
     * Metodo generico que retorna una lista de EntityBean del tipo generico
     * definido una ves implementada la interfaz. A partir de del Objeto de
     * negocio o Entity definido y un map con sus par�metro condicionales.
     * 
     * @param clazz
     * @param restricciones
     * @return List<T>
     */
    public List<T> findBy(Class<T> clazz, Map<String, Object> restricciones) {

        String query = "select o from " + clazz.getSimpleName() + " o where ";
        StringBuilder builder = new StringBuilder();

        Iterator<Map.Entry<String, Object>> iterator = restricciones.entrySet()
                .iterator();
        Map.Entry<String, Object> entry;

        String comparador = "";
        String valorBusqueda = "";

        while (iterator.hasNext()) {
            comparador = " = ";
            entry = iterator.next();
            valorBusqueda = entry.getValue().toString();
            if (!StringUtils.isEmpty(valorBusqueda)) {
                if ((valorBusqueda.indexOf("*")) != -1) {
                    valorBusqueda = valorBusqueda.replace('*', '%');
                    comparador = " LIKE ";
                }
                builder.append("and o." + entry.getKey());
                builder.append(comparador);
                builder.append("'" + valorBusqueda + "' ");
            }
        }
        query += builder.substring(3);
        return findEntitiesByString(query);
    }

    /**
     * Metodo generico que retorna una lista de EntityBean del tipo generico
     * definido una ves implementada la interfaz. A partir de del Objeto de
     * negocio o Entity definido y un map con sus par�metro condicionales.
     * 
     * @param clazz
     * @param listClases
     * @param restricciones
     * @return List<T>
     */
    @Override
    public List<T> findBy(Class<T> clazz, List<Class<?>> listClases,
            Map<String, Object> restricciones) {
        String cadenaEntitys = "";
        for (int i = 1; i < listClases.size(); i++) {
            cadenaEntitys += listClases.get(i);
            if (i + 1 < listClases.size()) {
                cadenaEntitys += ",";
            }
        }

        String query = "select " + clazz.getSimpleName() + " from "
                + clazz.getSimpleName() + ", " + cadenaEntitys + " o where ";
        StringBuilder builder = new StringBuilder();

        Iterator<Map.Entry<String, Object>> iterator = restricciones.entrySet()
                .iterator();
        Map.Entry<String, Object> entry;

        String comparador = "";
        String valorBusqueda = "";

        while (iterator.hasNext()) {
            comparador = " = ";
            entry = iterator.next();
            valorBusqueda = entry.getValue().toString();
            if (!StringUtils.isEmpty(valorBusqueda)) {
                if ((valorBusqueda.indexOf(">=")) != -1) {
                    valorBusqueda = valorBusqueda.replaceAll(">=", " ");
                    comparador = " >= ";
                } else if ((valorBusqueda.indexOf("<=")) != -1) {
                    valorBusqueda = valorBusqueda.replaceAll("<=", " ");
                    comparador = " <= ";
                } else if ((valorBusqueda.indexOf(">")) != -1) {
                    valorBusqueda = valorBusqueda.replace('>', ' ');
                    comparador = " > ";
                } else if ((valorBusqueda.indexOf("<")) != -1) {
                    valorBusqueda = valorBusqueda.replace('<', ' ');
                    comparador = " < ";
                } else if ((valorBusqueda.indexOf("%")) != -1) {
                    comparador = " LIKE ";
                }
                builder.append("and o." + entry.getKey());
                builder.append(comparador);
                builder.append("'" + valorBusqueda + "' ");
            }
        }
        query += builder.substring(3);
        return findEntitiesByString(query);
    }

    /**
     * Metodo generico que retorna la cantidad de registros 
     * que contiene una entidad
     * @return Integer
     */
    @Override
    public Integer countRowsOfEntity(Class<T> clazz) {
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
