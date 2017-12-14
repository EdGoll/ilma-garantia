package cl.aduana.gar.negocio.modelo.ot;

import java.io.Serializable;

/**
 * Clase TransferObject (TO) para implementacion de trazabilidad.
 * @author Eduardo Godoy
 *
 */
public class TrazabilidadServicioOT implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String documento;
	private String estado;
	private String glosa;
	private String categoria;
	private String servicio;
	private String tipo;
	private String protocolo;

	public TrazabilidadServicioOT() {
		//Constructor vacio.
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getGlosa() {
		return glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

}
