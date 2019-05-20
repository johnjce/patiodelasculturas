/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.PersonasPatio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jhontsPC
 */
@Stateless
public class PersonasPatioFacade extends AbstractFacade<PersonasPatio> {

    @PersistenceContext(unitName = "patioBD-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonasPatioFacade() {
        super(PersonasPatio.class);
    }
    
}
