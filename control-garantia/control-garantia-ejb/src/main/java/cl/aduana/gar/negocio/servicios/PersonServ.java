package cl.aduana.gar.negocio.servicios;

import cl.aduana.gar.negocio.modelo.entidades.Person;
import cl.aduana.gar.negocio.base.GenericFacade;

/**
 * Interface Servicio de ejemplo para operaciones sobre la entidad Person.
 * @author golmedo
 *
 */
public interface PersonServ extends GenericFacade<Person> {

	/**
	 * Ejemplo de metodo no perteneciente al CRUD particular para el servicio PersonService. 
	 */
	void metodoParticularNegocio();

}
