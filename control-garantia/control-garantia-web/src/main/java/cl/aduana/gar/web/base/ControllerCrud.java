package cl.aduana.gar.web.base;

import java.util.List;

import javax.faces.event.ActionEvent;

import cl.aduana.gar.negocio.base.BusinessLiveCycleExcepcion;

/**
 * Interfaz Controlador Crud Generico.
 * @author egodoy
 *
 * @param <ENTITY>
 */
public interface ControllerCrud<ENTITY> {
	
	public List<ENTITY> getRows() throws BusinessLiveCycleExcepcion;
	public void actionNewEntity(ActionEvent actionEvent);
	public void actionNewEntityInFormPage(ActionEvent actionEvent);
	public void actionAddEntity(ENTITY nueEntity) throws BusinessLiveCycleExcepcion;
	public void actionActualizarShowPanel(ENTITY actEntity);
	public void actionActualizarShowPanelInformPage(ENTITY actEntity);
	public void actualizarEntity(ENTITY actEntity) throws BusinessLiveCycleExcepcion;
	public void eliminarEntity(ENTITY eliEntity) throws BusinessLiveCycleExcepcion;
	public void cancelarOperacionCrud();
	public void cancelarOperacionCrudInForm();
	public void actionNewEntityInFormPage() ;
	public void actionActualizarFormPage(ENTITY actEntity);
	
	
		
}
