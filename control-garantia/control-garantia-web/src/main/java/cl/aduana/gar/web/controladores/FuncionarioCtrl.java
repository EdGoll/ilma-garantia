package cl.aduana.gar.web.controladores;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import cl.aduana.gar.negocio.modelo.entidades.Funcionario;
import cl.aduana.gar.negocio.servicios.FuncionarioService;
import cl.aduana.gar.web.base.GenericControllerCrud;

@Model
public class FuncionarioCtrl extends GenericControllerCrud<Funcionario, FuncionarioService>{
	
	@EJB
	private FuncionarioService service;

	@PostConstruct
	private void initFuncionarioCtrl() {
		super.init();
	}

	@Override
	public String getObjectSessionName() {
		return "FUNCIONARIO";
	}	
	
	@Override
	public FuncionarioService getService() {
		return service;
	}

	@Override
	public String getFormPage() {
		return "/principal/funcionarios/form";
	}

	@Override
	public String getPage() {
		return "/principal/funcionarios";
	}

}
