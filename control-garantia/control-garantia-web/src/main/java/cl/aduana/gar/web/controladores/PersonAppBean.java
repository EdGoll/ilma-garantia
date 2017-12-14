package cl.aduana.gar.web.controladores;

import javax.ejb.EJB;

import cl.aduana.gar.negocio.servicios.PersonServ;

public class PersonAppBean {

    @EJB    
    private PersonServ service;

    public PersonServ getPersonDelegateBean() {
        return service;
    }

    public void setPersonDelegateBean(PersonServ service) {
        this.service = service;
    }

}
