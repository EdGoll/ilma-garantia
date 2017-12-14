package cl.aduana.gar.negocio.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import cl.aduana.gar.negocio.base.qualifiers.EntityAuditable;
import cl.aduana.gar.negocio.base.ExtraModel;

/**
 * Entidad de negocio que representa a la tabla PERSON
 * 
 */
@EntityAuditable
@Entity
@Table(name = "GR_CUSTODIA")
public class Custodia  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter 
	private Long id;
	
	@Column(name="id_garantia")
	@Getter @Setter
	private Long idGarantia;

	@Column(name="ingreso_garantia")
	@Temporal(TemporalType.DATE)
	@Getter @Setter
	private Date fechaIngresoGarantia;

	@Column(name="retiro_garantia")
	@Temporal(TemporalType.DATE)
	@Getter @Setter
	private Date fechaRetiroGarantia;
	
	@Column(name="usuario_ingreso")
	@Getter @Setter
	private String usuario;
	
	@Column(name="cod_aduana")
	@Getter @Setter
	private String codigoAduana;
	
	@Column(name="fecha_registro")
	@Temporal(TemporalType.DATE)
	@Getter 
	private Date fechaRegistro;
	
	@Column(name="observacion")
	@Getter @Setter
	private String observacion;

//	@Column(name="id_garantia")	
//	@Getter @Setter
//	private Garantia idGarantia;	
	
	//@Column(name="cod_estado")
	@ManyToOne
	@JoinColumn(name = "id_estado", nullable = false)
	@Getter @Setter
	private EstadoCustodia estado;
	
	//@Column(name="id_garantia")
	@ManyToOne
	@JoinColumn(name = "id_garantia", nullable = false,insertable=false, updatable=false)
	@Getter @Setter
	private Garantia garantia;
	
	public Custodia() {
		super();
	}
	
	@PrePersist
	protected void onCreate() {
		this.fechaRegistro = new Date();
	}	

}
