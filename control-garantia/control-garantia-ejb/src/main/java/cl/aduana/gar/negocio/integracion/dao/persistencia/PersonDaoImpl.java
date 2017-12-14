package cl.aduana.gar.negocio.integracion.dao.persistencia;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import cl.aduana.gar.negocio.modelo.entidades.Person;
import cl.aduana.gar.negocio.base.GenericCrudDaoImpl;
import cl.aduana.gar.negocio.base.qualifiers.DataBase;
import cl.aduana.gar.negocio.base.qualifiers.DataBase.DataBaseType;

/**
 * Clase de ejemplo que implementa un DAO para la entidad Person
 * @author Eduardo Godoy.
 * @see cl.aduana.gar.negocio.base.GenericCrudDaoImpl
 * @see cl.aduana.gar.negocio.modelo.entidades.Person
 */
@Named
public class PersonDaoImpl 
    extends GenericCrudDaoImpl<Person> 
    implements PersonDao {

    @Inject
    @DataBase(type = DataBaseType.LOCAL)
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return this.em;
    }

    /**
     * Metodo que genera datos de ejemplo.
     */
    @PostConstruct
    private void init() {
        this.insert(this.getNewPerson("A", "AA", 20));
        this.insert(this.getNewPerson("B", "BB", 21));
        this.insert(this.getNewPerson("C", "CC", 22));
        this.insert(this.getNewPerson("D", "DD", 23));
        this.insert(this.getNewPerson("E", "EE", 24));
    }

    /**
     * Constructor para la clase Person
     * @param nombre
     * @param apellido
     * @param edad
     * @return Objeto Person instanciado con los valores enviados por parametros.
     */
    private Person getNewPerson(String nombre, String apellido, Integer edad) {
        Person p = new Person();
        p.setFirstName(nombre);
        p.setLastName(apellido);
        p.setAge(edad);
        return p;
    }
}
