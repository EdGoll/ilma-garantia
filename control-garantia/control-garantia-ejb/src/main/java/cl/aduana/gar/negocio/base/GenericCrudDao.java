package cl.aduana.gar.negocio.base;

import java.util.List;
import java.util.Map;

/**
 * Interface que define los métodos básicos de tipo crud, los cuales se exponene
 * mediante el patrón facade hacia las capas superiores. Si se desea crear un
 * modulo de tipo crud se sugiere implementar esta interface. de lo contrario
 * definir interfaces concretas que permitan exponer solo funcionalidades
 * específicas y necesarias hacia la capa web. Esta interface se debe
 * implementar en una Clase DAO donde su responsabildiad sea codificar los
 * métodos de tipo CRUD
 * 
 * @author Eduardo Godoy
 * @version 1.0, 03/02/2013
 * 
 */

public interface GenericCrudDao<T> {

	/**
	 * Recupera desde repositorio registro segun el id entregado por parametro
	 * traves del tipo de Clase definido.
	 * 
	 * @param id Object
	 * 
	 */
	public T find(Object id);

	/**
	 * Recupera desde el repositorio todos los registros del tipo instanciado a
	 * traves del tipo de Clase definido.
	 * 
	 * @return List<T>
	 * 
	 */
	List<T> findAll();

	/**
	 * Recupera desde el repositorio todos los registros del tipo instanciado a
	 * traves del tipo de Clase definido, partiendo desde el registro numero
	 * begin y desde ahi extrae la cantidad maxNumReg.
	 * 
	 * @param begin Integer
	 *            
	 * @param maxNumReg Integer
	 * @return List<T>
	 * 
	 */
	List<T> findAll(Integer begin, Integer maxNumReg);

	/**
	 * Metodo generico que guarda en el repositorio una nueva instancia del
	 * entity bean definido, luego retorna el mismo entity con sus valores
	 * actualizados.
	 * 
	 * @param entity
	 * 
	 */
	T insert(T entity);

	/**
	 * Metodo generico que elimina del repositorio una instancia de un
	 * EntityBean del tipo generico definido una ves implementada la interfaz.
	 * 
	 * @param entity
	 * 
	 */
	void delete(T entity);

	/**
	 * Metodo generico que actualiza en repositorio una instancia de un
	 * EntityBean del tipo generico definido una ves implementada la interfaz.
	 * 
	 * @param entity
	 * @return T
	 */
	T update(T entity);

	/**
	 * Metodo generico que retorna una lista de EntityBean del tipo generico
	 * definido una ves implementada la interfaz. A partir de del Objeto de
	 * negocio o Entity definido y un map con sus par�metro condicionales.
	 * 
	 * @return List<T>
	 */
	List<T> findBy(Class<T> clazz, Map<String, Object> restricciones);

	/**
	 * Metodo generico que retorna una lista de EntityBean del tipo generico
	 * definido una ves implementada la interfaz. A partir de del Objeto de
	 * negocio o Entity definido y un map con sus par�metro condicionales.
	 * 
	 * @param clazz List<Class<?>>
	 * @param restricciones Map <String, Object> 
	 * @return List<T>
	 */
	List<T> findBy(Class<T> clazz, List<Class<?>> listClases,
			Map<String, Object> restricciones);

	/**
	 * Metodo retorna un mensaje de verificaci�n, mediante el cual se puede
	 * verificar o no la conectividad a la capa de negocio remota, luego retorna
	 * su valor actualizado.
	 * @param query
	 * @return String
	 */
	List<T> findEntitiesByString(String query);

	/**
	 * Metodo generico que retorna la cantidad de registros que contiene una
	 * entidad
	 * @param clazz
	 * @return Integer
	 */
	Integer countRowsOfEntity(Class<T> clazz);

}
