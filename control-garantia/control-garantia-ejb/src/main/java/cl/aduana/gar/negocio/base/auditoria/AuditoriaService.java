package cl.aduana.gar.negocio.base.auditoria;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import cl.aduana.gar.negocio.base.DAOException;
import cl.aduana.gar.negocio.base.qualifiers.MethodAudit;

/**
 * Interface que define  los métodos necesarios para
 * la utilizacion de la capa de Auditoria
 * mediante un servicio EJB 
 * 
 * @author Eduardo Godoy. 
 * @version 1.0, 20/09/2016
 */
public interface AuditoriaService {

	 public Auditoria findByClass(Class<?> clazz,Object id);
	 public void findNewObject(Object entity,MethodAudit methodAudit)  throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException;
	List<Auditoria> findAll();
	void traceObject(Object entity, Object entityNew,Auditoria.TipoOperacion methodAudit)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, 
			IOException;
}
