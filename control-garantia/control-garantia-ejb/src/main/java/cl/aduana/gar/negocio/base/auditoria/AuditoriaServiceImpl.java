package cl.aduana.gar.negocio.base.auditoria;


import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import cl.aduana.gar.negocio.base.ExtraModel;
import cl.aduana.gar.negocio.base.qualifiers.EntityAuditable;
import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;
import cl.aduana.gar.negocio.base.qualifiers.MethodAudit;


/**
 * Clase que implementa los métodos necesarios para
 * la utilizacion de la capa de Auditoria
 * mediante un servicio EJB 
 * 
 * @author Eduardo Godoy. 
 * @version 1.0, 23/12/2016
 */

@Local(AuditoriaService.class)
@Stateless
public class AuditoriaServiceImpl
    implements AuditoriaService{
	
	
    @Inject
    @LoggerUtil
    private Logger logger;

    @Inject
    private AuditoriaDaoImpl dao;

	
	private void trace(Auditoria auditoria) {
		dao.insert(auditoria);
	}

    public Auditoria findByClass(Class<?> clazz, Object id){
    	return dao.findByClass(clazz, id);
    }
    
	@Override
	public void traceObject(Object entity, Object entityNew,Auditoria.TipoOperacion methodAudit)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, IOException {
		logger.info("fail");
		
		if (this.isEntityAudit(entity)) {
			((ExtraModel)entityNew).setUserPrincipal(((ExtraModel)entity).getUserPrincipal());
			Auditoria aud = this.getNewAuditoria(entityNew, methodAudit);
			this.trace(aud);
		}
	}

	private Auditoria getNewAuditoria(Object entity, Auditoria.TipoOperacion methodAudit)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		
		Auditoria aud = new Auditoria();
		Object obj = this.getEntityId(entity);
		String jsonInStringIn = this.serialiceObject(entity);
		if(obj instanceof Long){		
			aud.setClassId( ((Long)obj).intValue());
		}else{
			aud.setClassId( ((Integer)obj));
		}
		
		aud.setClassName(entity.getClass().getSimpleName());
		aud.setMethodName(methodAudit.name());
		aud.setNewEntity(jsonInStringIn);
		if(entity instanceof ExtraModel){
			aud.setUsuario(((ExtraModel) entity).getUserPrincipal().toString());
		}else{
			logger.error("Entidad es auditable pero no registra usuario.");
		}
		if (methodAudit == Auditoria.TipoOperacion.UPDATE) {
			if(obj instanceof Long){		
				obj = ((Long)obj).intValue();
			}			
			Auditoria oldAud = this.findByClass(entity.getClass(), obj);
			if (oldAud != null) {
				aud.setOldEntity(oldAud.getNewEntity());
			}
		} else {
			aud.setOldEntity(jsonInStringIn);
		}
		return aud;
	}	
	
	@Override
	public void findNewObject(Object methodParameter, MethodAudit methodAudit)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, IOException {
		if (this.isEntityAudit(methodParameter)) {
			Auditoria aud = this.getNewAuditoria(methodParameter, methodAudit);
			this.trace(aud);
		}
	}

	private boolean isEntityAudit(Object entity) {
		boolean resp = false;
		Annotation[] anotations = entity.getClass().getAnnotations();
		for (Annotation annotation : anotations) {
			logger.info("anotacion -> " + annotation.annotationType());
			if (annotation instanceof EntityAuditable) {
				resp = true;
				break;
			}
		}
		return resp;
	}

	private Auditoria getNewAuditoria(Object entity, MethodAudit methodAudit)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		
		Auditoria aud = new Auditoria();
		Object obj = this.getEntityId(entity);
		String jsonInStringIn = this.serialiceObject(entity);
		aud.setClassId((Integer) obj);
		aud.setClassName(entity.getClass().getSimpleName());
		aud.setMethodName(methodAudit.type().name());
		aud.setNewEntity(jsonInStringIn);
		if(entity instanceof ExtraModel){
			aud.setUsuario(((ExtraModel) entity).getUserPrincipal().toString());
		}else{
			logger.error("Entidad es auditable pero no registra usuario.");
		}
		if (methodAudit.type() == MethodAudit.MethodType.UPDATE) {
			Auditoria oldAud = this.findByClass(entity.getClass(), obj);
			if (oldAud != null) {
				aud.setOldEntity(oldAud.getNewEntity());
			}
		} else {
			aud.setOldEntity(jsonInStringIn);
		}
		return aud;
	}

	private Object getEntityId(Object entity) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		Class<?>[] emptyArray = null;
		Method method = entity.getClass()
				.getDeclaredMethod("getId", emptyArray);
		Object value = method.invoke(entity);
		return value;
	}

	private String serialiceObject(Object entity) {
		
		String jsonInStringIn = null;
		try {

			jsonInStringIn = entity.toString();

		} catch (Exception e) {
			logger.error(e);
		}
		return jsonInStringIn;
	}
       
	@Override
	public List<Auditoria> findAll(){
		return dao.findAll();
	}
}
