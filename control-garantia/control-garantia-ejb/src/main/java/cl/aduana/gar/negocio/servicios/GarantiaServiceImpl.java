package cl.aduana.gar.negocio.servicios;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import cl.aduana.gar.negocio.base.GenericFacadeImpl;
import cl.aduana.gar.negocio.integracion.dao.persistencia.GarantiaDao;
import cl.aduana.gar.negocio.modelo.entidades.Garantia;

@Local(GarantiaService.class)
@Stateless
public class GarantiaServiceImpl 
	extends GenericFacadeImpl<Garantia, GarantiaDao> 
	implements GarantiaService{

	@Inject
	private GarantiaDao dao;
	
	@Override
	public GarantiaDao getDao() {
		return dao;
	}

}
