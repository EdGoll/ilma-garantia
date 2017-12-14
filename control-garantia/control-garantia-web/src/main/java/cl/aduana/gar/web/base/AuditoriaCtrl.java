package cl.aduana.gar.web.base;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import cl.aduana.gar.negocio.base.auditoria.Auditoria;
import cl.aduana.gar.negocio.base.auditoria.AuditoriaService;

/**
 * Clase de ejemplo que representa un Controlador ManageBean de JSF para
 * registro de auditoria.
 * 
 * @author egodoy
 *
 */
@Model
public class AuditoriaCtrl {

	private List<Auditoria> listaCambios;

	@EJB
	private AuditoriaService service;

	/**
	 * La anotación PostConstruct se utiliza en un metodo que debe ejecutarse
	 * despues de que se realiza la inyección de dependencia para realizar
	 * cualquier inicializacion.
	 * 
	 */
	@PostConstruct
	private void init() {
		listaCambios = new ArrayList<Auditoria>();
		// Se obtienen todos los registros de auditoria
		listaCambios = service.findAll();
	}

	/**
	 * Obtiene lista de registros de auditoria.
	 * 
	 * @return Lista de registros de auditoria
	 */
	public List<Auditoria> getListaCambios() {
		return listaCambios;
	}

	/**
	 * Asigna lista de registros de auditoria a la variable "listaCambios".
	 * @param listaCambios
	 */
	public void setListaCambios(List<Auditoria> listaCambios) {
		this.listaCambios = listaCambios;
	}

}
