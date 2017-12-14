package cl.aduana.gar.negocio.integracion.dao.persistencia;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import cl.aduana.gar.negocio.modelo.entidades.Funcionario;
import cl.aduana.gar.negocio.base.GenericCrudDaoImpl;
import cl.aduana.gar.negocio.base.qualifiers.DataBase;
import cl.aduana.gar.negocio.base.qualifiers.DataBase.DataBaseType;

@Named
public class FuncionarioDaoImpl 
	extends GenericCrudDaoImpl<Funcionario> 	
	implements FuncionarioDao{

	@Inject
	@DataBase(type=DataBaseType.LOCAL)
	private EntityManager em;
	
	@Override
	public EntityManager getEntityManager() {
		return em;
	}

}
