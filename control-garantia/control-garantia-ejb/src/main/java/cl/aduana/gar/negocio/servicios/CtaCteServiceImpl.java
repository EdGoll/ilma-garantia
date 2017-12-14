package cl.aduana.gar.negocio.servicios;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import cl.aduana.gar.negocio.base.GenericFacadeImpl;
import cl.aduana.gar.negocio.integracion.dao.persistencia.CtaCteDao;
import cl.aduana.gar.negocio.modelo.entidades.CtaCte;

@Local(CtaCteService.class)
@Stateless
public class CtaCteServiceImpl 
	extends GenericFacadeImpl<CtaCte, CtaCteDao> 
	implements CtaCteService{

	@Inject
	private CtaCteDao dao;
	
	@Override
	public CtaCteDao getDao() {
		return dao;
	}

}
