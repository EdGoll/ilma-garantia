package cl.aduana.gar.negocio.servicios;

import javax.ejb.Local; 
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import cl.aduana.gar.negocio.integracion.dao.persistencia.PersonDao;
import cl.aduana.gar.negocio.modelo.entidades.Person;
import cl.aduana.gar.negocio.base.GenericFacadeImpl;
import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;

/**
 * Clase Servicio de ejemplo para operaciones sobre la entidad Person.
 */
@Local(PersonServ.class)
@Stateless
public class PersonServImpl 
        extends GenericFacadeImpl<Person, PersonDao> 
        implements PersonServ {
	
    @Inject
    @LoggerUtil
    private Logger logger;

    @Inject
    private PersonDao dao;

    @Override
    public PersonDao getDao() {
        return dao;
    }

    /**
     * Implementacion metodo no perteneciente al CRUD particular para el servicio PersonService.
     */
    @Override
    public void metodoParticularNegocio() {
        logger.debug("Metodo No-CRUD.");
    }

}
