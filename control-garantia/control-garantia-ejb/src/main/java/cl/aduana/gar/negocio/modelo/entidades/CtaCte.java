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
@Table(name = "GR_CTACTE")
public class CtaCte implements Serializable {

	private static final long serialVersionUID = 3486846344016911095L;
	
	@Column(name="id_ctacte")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="id_garantia")
	@Getter 
	private Integer idGarantia;
	
	@Column(name="monto_total")
	@Getter @Setter
	private Integer montoTotal;
	
	@Column(name="monto_operacion")
	@Getter @Setter
	private Integer montoOperacion;
	
	@Column(name="monto_disponible")
	@Getter @Setter
	private Integer montoDisponible;
	
	@Getter @Setter
	private String accion;
	
	@Column(name="fecha_registro")
	@Temporal(TemporalType.DATE)
	@Getter @Setter
	private Date fechaRegistro;

	//@Column(name="id_op")
	@ManyToOne
	@JoinColumn(name = "id_operacion", nullable = false)
	@Getter @Setter
	private Operacion operacion;
	
//	@Column(name="id_garantia")	
//	@Getter @Setter
//	private Garantia idGarantia;
	
	

	public CtaCte() {
		super();
	}


}
