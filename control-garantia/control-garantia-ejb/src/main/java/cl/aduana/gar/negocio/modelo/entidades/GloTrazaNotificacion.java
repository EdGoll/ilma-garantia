package cl.aduana.gar.negocio.modelo.entidades;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Entidad de negocio que representa a la tabla GLO_TRAZA_NOTIFICACION
 * perteneciente al esquema GLOBAL
 * 
 * @author Nombre Apellido
 * @version 1.0, 12/03/2014
 */
@Entity
//@Table(schema = "GLOBAL", name = "GLO_TRAZA_NOTIFICACION")
@Table(name = "GLO_TRAZA_NOTIFICACION")
public class GloTrazaNotificacion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String categoria;

    @Lob()
    private String documento;

    private Timestamp fecha;

    private String servicio;

    private String tipo;

    public GloTrazaNotificacion() {
    	//constructor vacio
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Timestamp getFecha() {
        return this.fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getServicio() {
        return this.servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Metodo sobre-escrito que representa esta clase como String.
     */
    @Override
    public String toString() {
        return "GloTrazaNotificacion [id=" + id + ", categoria=" + categoria
                + ", documento=" + documento + ", fecha=" + fecha
                + ", servicio=" + servicio + ", tipo=" + tipo + "]";
    }

}