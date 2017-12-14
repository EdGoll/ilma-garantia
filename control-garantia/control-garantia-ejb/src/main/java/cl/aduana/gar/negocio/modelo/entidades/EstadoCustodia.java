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
@Table(name = "GR_ESTADO_CUSTODIA")
public class EstadoCustodia  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="id_estado")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter 
	private Long id;
 
	@Getter @Setter
	private String descripcion;
	
	@Getter @Setter
	private String nombre;
	
	@Getter @Setter
	private String activo;
	
	@OneToMany(mappedBy="estado",fetch=FetchType.LAZY)
	@Getter @Setter
	private List<Custodia> listaCustodias = new ArrayList<Custodia>();			

	public EstadoCustodia() {
		super();
	}
	
}
