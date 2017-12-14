package cl.aduana.gar.web.controladores;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;

import cl.aduana.gar.web.controladores.paginadores.GlotrazaNotifiDataPagBean;
import cl.aduana.gar.web.base.JsfUtils;
import cl.aduana.gar.negocio.modelo.entidades.GloTrazaNotificacion;
import cl.aduana.gar.negocio.modelo.entidades.Person;
import cl.aduana.gar.negocio.modelo.entidades.TrazabilidadServicios;
import cl.aduana.gar.negocio.base.BusinessLiveCycleExcepcion;
import cl.aduana.gar.negocio.base.qualifiers.Audited;
import cl.aduana.gar.negocio.base.qualifiers.LoggedMethod;
import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;
import cl.aduana.gar.negocio.servicios.GloTrazaNotificacionServ;

/**
 * Clase de ejemplo que representa un Controlador ManageBean de JSF, el cual se
 * ubica detras de la vista y gestiona su comportamiento para lel DTO
 * GloTrazaNotificaciónDTO.
 * 
 * @author Nombre Apellido
 * @version 1.0, 31/05/2013
 */

@Named
@ViewScoped
public class GloTrazaNotificacionCtrl {

	@Inject
	@LoggerUtil
	private Logger logger;

	@EJB
	private GloTrazaNotificacionServ service;

	private String categoria;

	private List<GloTrazaNotificacion> listaGloTrazaNotificacion;

	private GloTrazaNotificacion gloTrazaNotificacionDTOSelected;
	private Person personSelected = new Person();
	private GloTrazaNotificacion selectedRow;
	private Person selectedRowPerson;

	private String msjPanel = "****hola***";

	@Inject
	private JsfUtils jsfUtils;

	@Inject
	private GlotrazaNotifiDataPagBean paginatorLazyBean;

	public List<GloTrazaNotificacion> getListaGloTrazaNotificacionDTO() {
		return listaGloTrazaNotificacion;
	}

	public void setSelectedRow(GloTrazaNotificacion selectedRow) {
		this.selectedRow = selectedRow;
	}

	public GloTrazaNotificacion getSelectedRow() {
		return this.selectedRow;
	}

	public void goConsultar() throws IOException {
		this.setSessionObject("gloTrazaNotificacionDTOSelected",
				gloTrazaNotificacionDTOSelected);
		jsfUtils.goPage("/principal/consultar/"
				+ gloTrazaNotificacionDTOSelected.getId());
	}

	public GloTrazaNotificacion getGloTrazaNotificacionDTOSelected() {
		return gloTrazaNotificacionDTOSelected;
	}

	public String setGloTrazaNotificacionDTOConsultada() {
		return gloTrazaNotificacionDTOSelected.getCategoria();
	}

	public String getGloTrazaNotificacionDTOConsultada() {
		return this.categoria;
	}

	private Object setSessionObject(String objName, GloTrazaNotificacion obj) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext extCtx = ctx.getExternalContext();
		Map<String, Object> sessionMap = extCtx.getSessionMap();
		return sessionMap.put(objName, obj);
	}

	public GlotrazaNotifiDataPagBean getPaginatorLazyBean() {
		return paginatorLazyBean;
	}

	public void setPaginatorLazyBean(GlotrazaNotifiDataPagBean paginatorLazyBean) {
		this.paginatorLazyBean = paginatorLazyBean;
	}

	public void onRowSelect(SelectEvent event) {
		gloTrazaNotificacionDTOSelected = (GloTrazaNotificacion) event
				.getObject();
		try {
			goConsultar();
		} catch (IOException e) {
			logger.error("Destino no encontrado", e);
		}
	}

	public void onRowSelectPerson(SelectEvent event) {
		personSelected = (Person) event.getObject();

	}

	public List<TrazabilidadServicios> getListaTrazabilidad()
			throws BusinessLiveCycleExcepcion {
		return service.findAllTrazavilidad();
	}

	public List<GloTrazaNotificacion> findAll(Integer inicio, Integer cantRegs) {
		List<GloTrazaNotificacion> trazaNotificacionList = null;
		try {
			trazaNotificacionList = service.findAll(inicio, cantRegs);
		} catch (Exception e) {
			logger.error("Error al acceder a la capa de Negocio", e);
		}
		return trazaNotificacionList;
	}

	@LoggedMethod
	@Audited
	public GloTrazaNotificacion find(Long id) {
		GloTrazaNotificacion trazaNotificacion = null;
		try {
			trazaNotificacion = service.find(id);
		} catch (Exception e) {
			logger.error("Error obtener objeto desde capa de negocio.", e);
		}
		return trazaNotificacion;
	}

	public List<TrazabilidadServicios> findAllTravilidadTO()
			throws BusinessLiveCycleExcepcion {
		return service.findAllTrazavilidad();
	}

	public Integer countRowsOfEntity() throws BusinessLiveCycleExcepcion {
		return service.countRowsOfEntity();
	}

	public String getMsjPanel() {
		return msjPanel;
	}

	public void setMsjPanel(String msjPanel) {
		this.msjPanel = msjPanel;
	}

	public Person getSelectedRowPerson() {
		return selectedRowPerson;
	}

	public void setSelectedRowPerson(Person selectedRowPerson) {
		this.selectedRowPerson = selectedRowPerson;
	}

	public Person getPersonSelected() {
		return personSelected;
	}

	public void setPersonSelected(Person personSelected) {
		this.personSelected = personSelected;
	}

}
