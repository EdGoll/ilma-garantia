package cl.aduana.gar.negocio.base.producers;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import cl.aduana.gar.negocio.base.qualifiers.DataBase;
import cl.aduana.gar.negocio.base.qualifiers.DataBase.DataBaseAccessBy;
import cl.aduana.gar.negocio.base.qualifiers.DataBase.DataBaseType;
/**
 * Clase de tipo singleton que implementa las 
 * fuentes de acceso a dato mediante mediante 
 * el framework CDI para ser inyectados como
 * instancias ya definidad mediante @Produce.
 * 
 * En esta clase se debe definir todos las fuentes
 * de acceso a datos que debe consumir el proyecto
 *, ya sean mediante jdbc directo o a través de la
 * API de persistencia y su ORM (JPA e hibernate).
 * 
 * @author Eduardo Godoy. 
 * @version 1.0, 20/09/2016
 */

@Singleton
public class DataBaseProducer {

    /*Se implementa fuente de acceso a datos mediante API de
     * persistencia en un esquema local para ejemplo*/
    @Produces
    @DataBase(type = DataBaseType.LOCAL, accessBy = DataBaseAccessBy.JPA)
    @PersistenceContext(unitName = "sampleLocalDb")
    EntityManager localEm;

    /*Se implementa fuente de acceso a datos mediante API de
     * persistencia en un esquema local de Auditoria.
     * Se sugiere utilizar un medio persistente con otra fuente de datos.
     * @see persistence.xml*/
    @Produces
    @DataBase(type = DataBaseType.AUDITORIA, accessBy = DataBaseAccessBy.JPA)
    @PersistenceContext(unitName = "auditLocalDb")
    EntityManager audEm;    

    
    @Produces
    @DataBase(type = DataBaseType.GARANTIA, accessBy = DataBaseAccessBy.JPA)
    @PersistenceContext(unitName = "garantiaPU")
    EntityManager garantiaEm; 
        
}
