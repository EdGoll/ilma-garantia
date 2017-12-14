package cl.aduana.gar.negocio.base.auditoria;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import cl.aduana.gar.negocio.base.qualifiers.DataBase;
import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;
import cl.aduana.gar.negocio.base.qualifiers.DataBase.DataBaseAccessBy;
import cl.aduana.gar.negocio.base.qualifiers.DataBase.DataBaseType;

/**
 * Clase DAO concreta que tiene la responsabilidad de persistir
 * lo que se ha identificado como objeto o metodo auditable.
 * @author Eduardo Godoy. 
 * @version 1.0, 23/12/2016
 */

@Named
public class AuditoriaDaoImpl  implements AuditoriaDao{
	
    @Inject
    @LoggerUtil
    private Logger logger;
    
    @Inject
    @DataBase(type = DataBaseType.LOCAL)
    private EntityManager em;
    

    @Override
    public EntityManager getEntityManager() {
        return this.em;
    }

	@Override
	public void insert(Auditoria auditoria) {
		this.getEntityManager().persist(auditoria);		
	}

	@Override
	public Auditoria findByClass(Class<?> clazz, Object id) {
		Query query = this.em.createNamedQuery("Auditoria.findEntityLastState");
		query.setParameter("name",clazz.getSimpleName());
		query.setParameter("id",id);
		List<Auditoria> lista = query.getResultList();
		return lista.size()>0 ? (Auditoria) query.getSingleResult() : null;
	}
	
	@Override
	public List<Auditoria> findAll(){
		Query query = this.em.createNamedQuery("Auditoria.findAll");
		return query.getResultList();
	}
	
	
}
