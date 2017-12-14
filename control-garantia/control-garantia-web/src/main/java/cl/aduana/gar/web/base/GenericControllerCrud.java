package cl.aduana.gar.web.base;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.apache.log4j.Logger;




import org.omnifaces.util.Faces;
import org.springframework.security.core.context.SecurityContextHolder;

import cl.aduana.gar.negocio.base.BusinessLiveCycleExcepcion;
import cl.aduana.gar.negocio.base.GenericFacade;
import cl.aduana.gar.negocio.base.ExtraModel;
import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

/**
 * Clase Abstracta que modela un CRUD generico.
 * @author golmedo
 *
 * @param <ENTITY>
 * @param <SERVICE>
 */
public abstract class GenericControllerCrud<ENTITY, SERVICE extends GenericFacade<ENTITY>>
		implements ControllerCrud<ENTITY> {

	@Inject
	@LoggerUtil
	private Logger logger;

	@Inject
	JsfUtils jsfUtils;

	public static final String AGREGAR_OBJ = "NUEVO_OBJ";
	public static final String ACTUALIZAR_OBJ = "ACTUALIZAR_OBJ";

	public abstract String getObjectSessionName();
	
	public abstract SERVICE getService();

	public abstract String getFormPage();

	public abstract String getPage();

	public void init(){
		Faces.getSessionMap().put(getObjectSessionName(), null);
	}
	
	@Override
	public void actionNewEntityInFormPage()  {
		this.cleanSession(this.getObjectSessionName());
		System.out.println(this.getFormPage());
		this.redirectTo(this.getFormPage());
	}
	
	/**
	 * Obtiene registros de una consulta.
	 * @return Lista de objetos de tipo &lt;ENTITY&gt;
	 */
	@Override
	public List<ENTITY> getRows() throws BusinessLiveCycleExcepcion {
		return getService().findAll();
	}

	@Override
	public void actionActualizarFormPage(ENTITY actEntity){
		Faces.getSessionMap().put(actEntity.toString(),actEntity);
		jsfUtils.goPage(this.getFormPage());		
	}
		
	
	/**
	 * Llama a Agrega una nueva entidad a la sesion (JSF).
	 */
	@Override
	public void actionNewEntity(ActionEvent actionEvent) {
		this.putAnewEntityInSession();
	}

	/**
	 * Agrega una nueva entidad a la sesion (JSF) y navega a una pagina que contiene el formulario ad-hoc.
	 */
	@Override
	public void actionNewEntityInFormPage(ActionEvent actionEvent) {
		this.putAnewEntityInSession();
		jsfUtils.goPage(this.getPage());
	}

	/**
	 * Agrega una nueva entidad a la sesion (JSF).
	 */
	@SuppressWarnings("unchecked")
	private void putAnewEntityInSession() {

		try {

			JsfUtils.putObjectInSession(
					this.getObjectSessionName(),
					((Class<ENTITY>) ((ParameterizedType) this.getClass()
							.getGenericSuperclass()).getActualTypeArguments()[0])
							.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error(e);
		}
	}

	/**
	 * Agrega (insert) entidad.
	 */
	@Override
	public void actionAddEntity(ENTITY nueEntity)
			throws BusinessLiveCycleExcepcion {
		if (nueEntity instanceof ExtraModel) {
			((ExtraModel) nueEntity).setUserPrincipal(SecurityContextHolder
					.getContext().getAuthentication().getName());
		}

		getService().insert(nueEntity);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"Registro creado con exito."));
		JsfUtils.removeObjectFromSession(this.getObjectSessionName());
	}

	/**
	 * Llama a actualizar (Update) objeto en sesion JSF.
	 */
	@Override
	public void actionActualizarShowPanel(ENTITY actEntity) {
		logger.info("Put object in session");
		JsfUtils.putObjectInSession(this.getObjectSessionName(), actEntity);
	}

	@Override
	public void actionActualizarShowPanelInformPage(ENTITY actEntity) {
		logger.info("Put object in session");
		JsfUtils.putObjectInSession(this.getObjectSessionName(), actEntity);
		jsfUtils.goPage(getFormPage());
	}

	/**
	 * Actualiza (Update) entidad.
	 */
	@Override
	public void actualizarEntity(ENTITY actEntity)
			throws BusinessLiveCycleExcepcion {
		if (actEntity instanceof ExtraModel) {
			((ExtraModel) actEntity).setUserPrincipal(SecurityContextHolder
					.getContext().getAuthentication().getName());
		}
		getService().update(actEntity);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"Registro Actualizado con exito."));
		JsfUtils.removeObjectFromSession(this.getObjectSessionName());
	}

	/**
	 * Elimina (Delete) entidad.
	 */
	@Override
	public void eliminarEntity(ENTITY eliEntity)
			throws BusinessLiveCycleExcepcion {
		if (eliEntity instanceof ExtraModel) {
			((ExtraModel) eliEntity).setUserPrincipal(SecurityContextHolder
					.getContext().getAuthentication().getName());
		}
		getService().delete(eliEntity);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"Registro eliminado con exito."));
	}

	/**
	 * Llama a Cancelar operacion (Saca el objeto de sesion).
	 */
	@Override
	public void cancelarOperacionCrud() {
		this.cleanSession();
	}

	/**
	 * Cancela operacion (Saca el objeto de sesion) y navega a pagina de formulario ad-hoc.
	 */
	@Override
	public void cancelarOperacionCrudInForm() {
		this.cleanSession();
		jsfUtils.goPage(getPage());
	}
	 

	

	/**
	 * Cancelar operacion (Saca el objeto de sesion).
	 */
	public void cleanSession(String... keys){
		for (String key : keys) {			
			if(Faces.getSessionMap().containsKey(key)){
				Faces.getSessionMap().remove(key);
			}
		}		
	}
	
	public void redirectTo(String url){
		try {
			Faces.redirect(FacesContext.getCurrentInstance()
					.getExternalContext().getRequestContextPath()+url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public boolean isObjInSession(){
		return Faces.getSessionAttribute(getObjectSessionName()) != null ? true : false;
	}

}
