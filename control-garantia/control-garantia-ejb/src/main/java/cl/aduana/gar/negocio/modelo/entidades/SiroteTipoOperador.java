package cl.aduana.gar.negocio.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import cl.aduana.gar.negocio.base.qualifiers.EntityAuditable;
import cl.aduana.gar.negocio.base.ExtraModel;

/**
 * Entidad de negocio que representa a la tabla PERSON
 * 
 */
@EntityAuditable
@Entity
@Table(name = "NWOP_TIPO_OPERADOR")
public class SiroteTipoOperador  implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -905990003573430744L;

	@Id
	@Getter
	private Integer id;
	
	@Getter
	private String codigo;
	
	@Getter
	private String descripcion;
	
	@Getter
	private String activo;
	
	@Column(name="FECHA_CREACION")
	@Temporal(TemporalType.DATE)
	@Getter 
	private Date fechaCreacion;
	
	@Column(name="USUARIO_CREACION")
	@Getter
	private String usuarioCreacion;
	
	@Column(name="FECHA_ULTIMAMOD")
	@Temporal(TemporalType.DATE)
	@Getter 
	private Date fechaUltimaModificacion;
	
	@Column(name="USUARIO_ULTIMAMOD")
	@Getter
	private String usuarioUltimaModificacion;
	
	public SiroteTipoOperador() {
		super();
	}

	

}
