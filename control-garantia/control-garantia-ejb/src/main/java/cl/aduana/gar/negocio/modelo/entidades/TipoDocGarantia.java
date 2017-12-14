package cl.aduana.gar.negocio.modelo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "GR_TIPO_DOC_GARANTIA")
public class TipoDocGarantia  implements Serializable {

	private static final long serialVersionUID = -7763121002447596017L;

	@Column(name="id_tipo_doc_gar")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private Long id;
	
	@Getter @Setter	
	private String nombre;
	
	@Getter @Setter	
	@Column(name="tipo_documento")
	private String tipoDocumento;
	
	@Getter @Setter	
	private String habilitante;
		
	@OneToMany(mappedBy="tipoDocumento",fetch=FetchType.LAZY)
	@Getter @Setter
	private List<Garantia> listaGarantias = new ArrayList<Garantia>();	
	
	public TipoDocGarantia() {
		super();
	}

}
