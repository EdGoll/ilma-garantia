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
@Table(name = "MONED")
public class DinMoned  implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8726607785450630764L;

	@Column(name="CODMONED")
	@Id
	@Getter
	private Integer codModen;
	
	@Column(name="GLOMONED")
	@Getter
	private String gloMoned;

	@Column(name="FECFIN")
	@Temporal(TemporalType.DATE)
	@Getter 
	private Date fechaFin;
	
	@Column(name="FECINI")
	@Temporal(TemporalType.DATE)
	@Getter 
	private Date fechaInicio;
	
	@OneToMany(mappedBy="codmoned",fetch=FetchType.LAZY)
	@Getter @Setter
	private List<Garantia> listaGarantias = new ArrayList<Garantia>();	
	
	public DinMoned() {
		super();
	}


}
