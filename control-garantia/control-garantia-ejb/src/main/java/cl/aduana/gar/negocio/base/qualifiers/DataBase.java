package cl.aduana.gar.negocio.base.qualifiers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.PARAMETER;
/**
 * Interface de tipo CDI  qualifier
 * que permite definir los tipos de bases de datos
 * que consumira el proyecto desarrollado 
 *  
 * @author Eduardo Godoy. 
 * @version 1.0, 20/09/2016
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ TYPE, METHOD, FIELD, PARAMETER })
public @interface DataBase {
    /*
     * Tambien se pueden agregar otros tipos de esquemas de negocio a los
     * que se requiera conectar. Luego se debe agregar como Produces Field a
     * DataBaseProducer.java
     */
    public enum DataBaseType {
        AUDITORIA, DEFAULT, DEVELOPMENT, TESTING, LOCAL,GARANTIA;
    }
    DataBaseType type() default DataBaseType.DEFAULT;    
    
    /*
     * Permite identificar el tipo de acceso a datos
     * que se utilizará, ya sea JPA o DAO mediante jdbc
     */
    public enum DataBaseAccessBy {
        JPA, DAO_JDBC;
    }
    DataBaseAccessBy accessBy() default DataBaseAccessBy.JPA;    
}
