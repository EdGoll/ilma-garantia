package cl.aduana.gar.negocio.base.auditoria;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * Clase que mapea entidad auditoria desde un esquema relacional.
 * Utilizada para persistir los eventos de tipo i/o que necesiten
 * ser trazables dentro del ciclo de vida del objeto.
 * 
 * @author Eduardo Godoy. 
 * @version 1.0, 20/09/2016
 */

@Entity
@Table(name = "auditoria")
@NamedQueries(
		{
			@NamedQuery(name="Auditoria.findEntityLastState",
						query="select o from Auditoria o where o.updateDate = "
							+ "(select max(o_.updateDate) from  Auditoria o_ where o_.className = :name and o_.classId = :id)"),
			@NamedQuery(name="Auditoria.findAll",
						query="select o from Auditoria o order by o.updateDate desc"),
		}

)
public class Auditoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aud", nullable = false, insertable = false, updatable = false)
    private Long id;
    
    private String tierName;
    private String className;
    private String methodName;
    private String paramType;
    private String paramName;
    private Date creationDate;
    private String newEntity;
    private String oldEntity;
    private TipoAuditoria tipoAuditoria;
    private String usuario;
    private Date updateDate;
    private Integer classId;
            
    public Auditoria(    		
    		String tierName, 
    		String className, 
    		String methodName, 
    		String paramType, 
    		String paramName, 
    		Date date, 
    		String newEntity, 
    		String oldEntity,
    		TipoAuditoria tipoAuditoria) {
    	
        super();
        this.tierName = tierName;
        this.className = className;
        this.methodName = methodName;
        this.paramType = paramType;
        this.paramName = paramName;
        this.creationDate = date;
        this.tipoAuditoria= tipoAuditoria;
    }

    public String getTierName() {
        return tierName;
    }

    public void setTierName(String tierName) {
        this.tierName = tierName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Date getDate() {
        return creationDate;
    }

    public void setDate(Date date) {
        this.creationDate = date;
    }

    private static final long serialVersionUID = 1L;

    public Auditoria() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getNewEntity() {
		return newEntity;
	}

	public void setNewEntity(String newEntity) {
		this.newEntity = newEntity;
	}

	public String getOldEntity() {
		return oldEntity;
	}

	public void setOldEntity(String oldEntity) {
		this.oldEntity = oldEntity;
	}

    public TipoAuditoria getTipoAuditoria() {
		return tipoAuditoria;
	}

	public void setTipoAuditoria(TipoAuditoria tipoAuditoria) {
		this.tipoAuditoria = tipoAuditoria;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public enum TipoAuditoria {
		CAMPO, ALERTA
	}

	@PrePersist
	void updatedAt() {
		this.updateDate = new Date();
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public enum TipoOperacion{
		INSERT, DELETE, UPDATE,DEFAULT
	}

	@Override
	public String toString() {
		return "Auditoria [id=" + id + ", tierName=" + tierName
				+ ", className=" + className + ", methodName=" + methodName
				+ ", paramType=" + paramType + ", paramName=" + paramName
				+ ", creationDate=" + creationDate + ", newEntity=" + newEntity
				+ ", oldEntity=" + oldEntity + ", tipoAuditoria="
				+ tipoAuditoria + ", usuario=" + usuario + ", updateDate="
				+ updateDate + ", classId=" + classId + "]";
	}
	
	
}
