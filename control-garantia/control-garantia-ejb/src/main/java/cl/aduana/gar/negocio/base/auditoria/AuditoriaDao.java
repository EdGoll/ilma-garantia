package cl.aduana.gar.negocio.base.auditoria;
import java.util.List;

import javax.persistence.EntityManager;

/**
 *  interface  que define los metodos 
 *  de tipo CRUD para la gestion de capa de auditoria.
 * 
 * @author Eduardo Godoy. 
 * @version 1.0, 23/12/2016
 */


public interface AuditoriaDao {
	public EntityManager getEntityManager();
	void insert(Auditoria auditoria); 
	 public Auditoria findByClass(Class<?> clazz,Object id);
	List<Auditoria> findAll();
}

