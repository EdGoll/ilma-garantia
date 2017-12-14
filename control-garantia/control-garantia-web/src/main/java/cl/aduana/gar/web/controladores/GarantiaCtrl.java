package cl.aduana.gar.web.controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;


import cl.aduana.gar.negocio.modelo.entidades.Garantia;
import cl.aduana.gar.negocio.servicios.GarantiaService;
import cl.aduana.gar.web.base.GenericControllerCrud;

@Model
public class GarantiaCtrl extends GenericControllerCrud<Garantia, GarantiaService>{
		
	@EJB
	private GarantiaService service;

	@PostConstruct
	private void initGarantiaCtrl() {
		super.init();
	}

	@Override
	public String getObjectSessionName() {
		return "GARANTIA";
	}	
	
	@Override
	public GarantiaService getService() {
		return service;
	}

	@Override
	public String getFormPage() {
		return "/principal/garantias/form";
	}

	@Override
	public String getPage() {
		return "/principal/garantias";
	}



}
