package cl.aduana.gar.negocio.modelo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Table(name = "GR_OPERACION")
public class Operacion  implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5985363371944902921L;

	@Column(name="id_operacion")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter 
	private Long id;
	
//	@Column(name="id_garantia")	
//	@Getter @Setter
//	private Garantia idGarantia;

	private Integer numIdent;
	
	@Column(name="cod_tipo_operacion")
	@Getter @Setter	
	private Integer tipoOperacion;
	
	@Column(name="monto_operacion")
	@Getter @Setter	
	private Integer montoOperacion;

	@Column(name="inicio_operacion")
	@Temporal(TemporalType.DATE)
	@Getter @Setter	
	private Date inicioOperacion;

	@Column(name="vence_operacion")
	@Temporal(TemporalType.DATE)
	@Getter @Setter	
	private Date venceOperacion;	
	
	private String accion;
	
	@Column(name="fecha_registro")
	@Temporal(TemporalType.DATE)
	@Getter
	private Date fechaRegistro;
	
	@Getter @Setter	
	private String activo;
	
	@OneToMany(mappedBy="operacion",fetch=FetchType.LAZY)
	@Getter @Setter
	private List<CtaCte> listaCtaCte = new ArrayList<CtaCte>();			
	
//	@Column(name="id_garantia")
	@ManyToOne
	@JoinColumn(name = "id_garantia", nullable = false)
	@Getter @Setter
	private Garantia garantia;
	
	public Operacion() {
		super();
	}
	
	@PrePersist
	protected void onCreate() {
		this.fechaRegistro = new Date();
	}

}
