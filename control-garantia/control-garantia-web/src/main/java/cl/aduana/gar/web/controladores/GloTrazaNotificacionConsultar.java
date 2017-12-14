package cl.aduana.gar.web.controladores;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import cl.aduana.gar.web.base.JsfUtils;
import cl.aduana.gar.negocio.modelo.entidades.GloTrazaNotificacion;
import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;

/**
 * Clase de ejemplo que representa un Controlador ManageBean de JSF, el cual se
 * ubica detras de la vista y gestiona su comportamiento para lel DTO
 * GloTrazaNotificacionDTO.
 * 
 * @author Nombre Apellido
 * @version 1.0, 31/05/2013
 */
@Model
public class GloTrazaNotificacionConsultar {

    @Inject
    @LoggerUtil
    private Logger logger;

    private GloTrazaNotificacion gloTrazaNotificacionSelectedDb;
    private GloTrazaNotificacion gloTrazaNotificacionFromSession;
    private String pid;
    private Long id;

    @Inject
    private JsfUtils jsfUtil;

    @Inject
    private GloTrazanotificacionApp gloTrazanotificacionApp;

    public void onload() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            this.id = Long.parseLong(pid);
            gloTrazaNotificacionSelectedDb = gloTrazanotificacionApp
                    .find(this.id);
        }
        gloTrazaNotificacionFromSession = this.getCategoriaFromSession();
    }

    public GloTrazaNotificacion getCategoriaFromSession() {
        GloTrazaNotificacion gloTrazaNotificacionDTO = (GloTrazaNotificacion) this
                .getSessionObject("gloTrazaNotificacionDTOSelected");
        return gloTrazaNotificacionDTO;
    }

    private Object getSessionObject(String objName) {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get(objName);
    }

    public void volver() {
        jsfUtil.goPage("/principal");
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public GloTrazaNotificacion getGloTrazaNotificacionSelectedDb() {
        return gloTrazaNotificacionSelectedDb;
    }

    public void setGloTrazaNotificacionDTOSelectedDb(
            GloTrazaNotificacion gloTrazaNotificacionSelectedDb) {
        this.gloTrazaNotificacionSelectedDb = gloTrazaNotificacionSelectedDb;
    }

    public GloTrazaNotificacion getGloTrazaNotificacionFromSession() {
        return gloTrazaNotificacionFromSession;
    }

    public void setGloTrazaNotificacionDTOFromSession(
            GloTrazaNotificacion gloTrazaNotificacionFromSession) {
        this.gloTrazaNotificacionFromSession = gloTrazaNotificacionFromSession;
    }

}
