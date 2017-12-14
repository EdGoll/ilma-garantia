package cl.aduana.gar.negocio.modelo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "GR_ESTADO_GARANTIA")
public class EstadoGarantia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="id_estado_gar")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private Long id;
	
	@Getter @Setter
	private String nombre;
	
	@Getter @Setter
	private String descripcion;
	
	@OneToMany(mappedBy="estadogarantia",fetch=FetchType.LAZY)
	@Getter @Setter
	private List<Garantia> listaGarantias = new ArrayList<Garantia>();
	
	public EstadoGarantia() {
		super();
	}

	
}
