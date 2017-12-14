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
@Table(name = "GR_GARANTIA")
public class Garantia extends ExtraModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4565884812754697277L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_garantia")
	@Getter 
	private Long id;
	
	@Column(name="rut_operador")
	@Getter @Setter
	private Integer rutOperador;
	
	@Column(name="nombre_operador")
	@Getter @Setter
	private String nombreOperador;
		
	@Column(name="num_garantia_operador")
	@Getter @Setter
	private Integer numGarantiaOperador;
	
//	@Column(name="entidad_aseguradora")
//	@Getter @Setter
//	private Integer entidadAseguradora;
	
	@Column(name="monto_total")
	@Getter @Setter
	private Integer montoTotal;
	
	@Column(name="fecha_emision")
	@Temporal(TemporalType.DATE)
	@Getter @Setter
	private Date fechaEmision;
	
	@Column(name="fecha_inicio")
	@Temporal(TemporalType.DATE)
	@Getter @Setter
	private Date fechaInicio;
	
	@Column(name="fecha_expiracion")
	@Temporal(TemporalType.DATE)
	@Getter @Setter
	private Date fechaExpiracion;
	
	@Column(name="fecha_inicio_contrato")
	@Temporal(TemporalType.DATE)
	@Getter @Setter
	private Date fechaInicioContrato;
	
	@Column(name="fecha_termino_contrato")
	@Temporal(TemporalType.DATE)
	@Getter @Setter
	private Date fechaTerminoContrato;		
	
	@Column(name="monto_operaciones_anuales")
	@Getter @Setter
	private Integer montoOperacionesAnuales;
	
	@Column(name="fecha_habilitacion_recinto")
	@Temporal(TemporalType.DATE)
	@Getter @Setter
	private Date fechaHabilitacionRecinto;		
	
	@Column(name="fecha_registro")
	@Temporal(TemporalType.DATE)
	@Getter 
	private Date fechaRegistro;		
	
	@Getter @Setter
	private String activo;	
	
	//@Column(name="estado_garantia")
	@ManyToOne
	@JoinColumn(name = "id_estado_gar", nullable = false)
	@Getter @Setter
	private EstadoGarantia estadogarantia;	
	
	//@Column(name="cod_tipo_documento")
	@ManyToOne
	@JoinColumn(name = "id_tipo_doc_gar", nullable = false)
	@Getter @Setter
	private TipoDocGarantia tipoDocumento;
	
	//@Column(name="cod_moneda")
	@ManyToOne
	@JoinColumn(name = "CODMONED", nullable = false)
	@Getter @Setter
	private DinMoned codmoned;		
	
	//@Column(name="entidad_aseguradora")
	@ManyToOne
	@JoinColumn(name = "id_aseguradora", nullable = false)
	@Getter @Setter
	private Aseguradora aseguradora;		
	
	//@Column(name="cod_tipo_operador")
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id")
	private SiroteTipoOperador tipooperador;	
	
	@OneToMany(mappedBy="garantia",fetch=FetchType.LAZY)
	@Getter @Setter
	private List<Operacion> listaGarantias = new ArrayList<Operacion>();		
	
	@OneToMany(mappedBy="garantia",fetch=FetchType.LAZY)
	@Getter @Setter
	private List<Custodia> listaCustodias = new ArrayList<Custodia>();	
	
	
	public Garantia() {
		super();
	} 	
	
	@PrePersist
	protected void onCreate() {
		this.fechaRegistro = new Date();
	}

}
