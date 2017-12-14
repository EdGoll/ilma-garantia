package cl.aduana.gar.negocio.modelo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import cl.aduana.gar.negocio.base.qualifiers.EntityAuditable;


/**
 * Entidad de negocio que representa a la tabla PERSON
 * 
 */
@EntityAuditable
@Entity
@Table(name = "GR_ASEGURADORA")
public class Aseguradora  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3768060712361019192L;

	@Column(name="id_aseguradora")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private Long id;
	
	@Getter @Setter
	private String nombre;
	
	@Getter @Setter
	private String descripcion;
	
	
	@OneToMany(mappedBy="aseguradora",fetch=FetchType.LAZY)
	@Getter @Setter
	private List<Garantia> listaGarantias = new ArrayList<Garantia>();
	
	
	public Aseguradora() {
		super();
	}

	
}
