package cl.aduana.gar.web.base;



import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.omnifaces.util.Faces;

import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;


/**
 * Clase Abstracta que modela un CRUD generico.
 * @author golmedo
 *
 * @param <ENTITY>
 * @param <SERVICE>
 */
public abstract class Controller implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2282818490947820539L;

	@Inject
	@LoggerUtil
	private Logger logger;

	@Inject
	JsfUtils jsfUtils;

	public static final String AGREGAR_OBJ = "NUEVO_OBJ";
	public static final String ACTUALIZAR_OBJ = "ACTUALIZAR_OBJ";
	public static final String ROOT_PATH_URL = "/principal";


	public abstract String getFormPage();
	public abstract String getPage();
	public abstract void actionNewEntityInFormPage(String urlForm);

	public void showInfoMessage(String msj){
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						msj));		
	}
	
	public void showErrorMessage(String msj){
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
						msj));		
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
	
	public void cleanSession(String... keys){
		for (String key : keys) {			
			if(Faces.getSessionMap().containsKey(key)){
				Faces.getSessionMap().remove(key);
			}
		}		
	}	
	
	public void actionNewEntityInFormPage()  {		
		this.redirectTo(this.getFormPage());
	}
		
	
	public void cancelarOperacionCrudInForm(String... obj) {
		this.redirectTo(getPage());
		this.cleanSession(obj);
	}
				
}
