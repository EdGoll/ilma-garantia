package cl.aduana.gar.negocio.servicios;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import cl.aduana.gar.negocio.integracion.dao.persistencia.CustodiaDao;
import cl.aduana.gar.negocio.integracion.dao.persistencia.FuncionarioDao;
import cl.aduana.gar.negocio.modelo.entidades.Custodia;
import cl.aduana.gar.negocio.modelo.entidades.Funcionario;
import cl.aduana.gar.negocio.base.GenericFacadeImpl;

@Local(CustodiaService.class)
@Stateless
public class CustodiaServiceImpl 
	extends GenericFacadeImpl<Custodia, CustodiaDao> 
	implements CustodiaService{

	@Inject
	private CustodiaDao dao;
	
	@Override
	public CustodiaDao getDao() {
		return dao;
	}

}
