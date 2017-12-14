package cl.aduana.gar.web.controladores.paginadores;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import cl.aduana.gar.web.controladores.GloTrazanotificacionApp;
import cl.aduana.gar.negocio.modelo.entidades.GloTrazaNotificacion;
import cl.aduana.gar.negocio.base.qualifiers.LoggerUtil;

/**
 * 
 * @author egodoy
 *
 */
@Named
@ViewScoped
public class GlotrazaNotifiDataPagBean 
    extends LazyDataModel<GloTrazaNotificacion> {

	private static final long serialVersionUID = 1L;

    @Inject
    @LoggerUtil
    private Logger logger;

    @Inject
    private GloTrazanotificacionApp app;

    @PostConstruct
    public void init() {
        try {
            super.setRowCount(app.countRowsOfEntity().intValue());
        } catch (Exception e) {
            logger.error("Error en la fuente de datos DAO", e);
        }
    }

    @Override
    public List<GloTrazaNotificacion> load(int first, int pageSize,
            String sortField, SortOrder sortOrder, @SuppressWarnings("rawtypes") Map filters) {
        List<GloTrazaNotificacion> lista = null;
        try {
            lista = app.findAll(first, pageSize);
        } catch (Exception e) {
            logger.error("Error en la fuente de datos DAO", e);
        }
        return lista;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }

    /**
     * 
     * @return long id
     */
    @Override
    public Object getRowKey(GloTrazaNotificacion entity) {
        return entity.getId();
    }

    /**
     * Obtiene un registro de la entidad &lt;GloTrazaNotificacion&gt;
     * @return Instancia de &lt;GloTrazaNotificacion&gt;
     */
    @Override
    public GloTrazaNotificacion getRowData(String rowKey) {      
        GloTrazaNotificacion entity = null;
        try {
            entity = app.find(Long.parseLong(rowKey));
        } catch (Exception e) {
            logger.error("Error en la fuente de datos del paginador", e);
        }
        return entity;
    }

}
