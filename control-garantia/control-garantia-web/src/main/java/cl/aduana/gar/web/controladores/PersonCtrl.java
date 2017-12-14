package cl.aduana.gar.web.controladores;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import cl.aduana.gar.web.base.ControllerCrud;
import cl.aduana.gar.web.base.GenericControllerCrud;
import cl.aduana.gar.negocio.modelo.entidades.Person;
import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;
import cl.aduana.gar.negocio.servicios.PersonServ;

/**
 * Clase de ejemplo que representa un Controlador ManageBean de JSF
 * para un ejempo de mantenedor de personas.
 * @author egodoy
 *
 */
@Model
public class PersonCtrl 
	extends GenericControllerCrud<Person, PersonServ>
	implements ControllerCrud<Person>{

	@Inject
	@LoggerUtil
	private Logger logger;
    
    @EJB 
    private PersonServ service;
    
    public void consultarPersonAction(Person per){
    	logger.info(per.getFirstName());
    }

	@Override
	public PersonServ getService() {
		return service;
	}

	@Override
	public String getFormPage() {
		return "#";
	}

	@Override
	public String getPage() {
		return "#";
	}

	@Override
	public String getObjectSessionName() {
		// TODO Auto-generated method stub
		return "Object";
	} 
}
