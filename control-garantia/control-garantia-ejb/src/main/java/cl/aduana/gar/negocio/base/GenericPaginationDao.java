package cl.aduana.gar.negocio.base;

import java.util.List;
/**
 * Interface  que define los métodos básicos para paginación, los cuales se exponene
 * mediante el patrón facade hacia las capas superiores.
 * Si se desea crear un modulo de tipo paginación se debe implementar esta interface.
 * de lo contrario definir interfaces concretas que permitan exponer solo funcionalidades
 * específicas y necesarias hacia la capa web.
 * Esta interface se debe implementar en una Clase DAO donde su responsabildiad
 * sea codificar los métodos para implementar una paginación eficiente.
 * 
 * @author Eduardo Godoy
 * @version 1.0, 03/02/2013
 * 
 */
public interface GenericPaginationDao<ENTITY,ID_TYPE> {
    

    /**
     * Recupera desde el registro segun el id entregado 
     * por parametro
     * traves del tipo de Clase definido.
     * 
     * @param id Object Id
     * @return List<T>
     * 
     */    
    public ENTITY find(ID_TYPE id);

    /**
     * Recupera desde el repositorio todos los registros del tipo instanciado a
     * traves del tipo de Clase definido.
     * 
     * @return List<T>
     * 
     */
    List<ENTITY> findAll();

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
    List<ENTITY> findAll(Integer begin, Integer maxNumReg);


    /**
     * Metodo generico que retorna la cantidad de registros 
     * que contiene una entidad
     * @param clazz
     * @return Integer
     */
    Integer countRowsOfEntity(Class<ENTITY> clazz);

}
