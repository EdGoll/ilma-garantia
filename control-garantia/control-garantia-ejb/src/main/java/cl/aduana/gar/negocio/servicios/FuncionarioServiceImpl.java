package cl.aduana.gar.negocio.servicios;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import cl.aduana.gar.negocio.integracion.dao.persistencia.FuncionarioDao;
import cl.aduana.gar.negocio.modelo.entidades.Funcionario;
import cl.aduana.gar.negocio.base.GenericFacadeImpl;

@Local(FuncionarioService.class)
@Stateless
public class FuncionarioServiceImpl 
	extends GenericFacadeImpl<Funcionario, FuncionarioDao> 
	implements FuncionarioService{

	@Inject
	private FuncionarioDao dao;
	
	@Override
	public FuncionarioDao getDao() {
		return dao;
	}

}
