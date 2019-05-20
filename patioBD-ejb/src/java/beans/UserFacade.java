package beans;

import entities.Credentiales;
import entities.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "patioBD-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public Credentiales getCredentials(int id) {
        try {
            Query cq = em.createNamedQuery("User.getCredentiaslById", User.class);
            cq.setParameter("id", id);
            Credentiales cr = (Credentiales) cq.getSingleResult();
            return cr;
        } catch (NoResultException nre) { return null; }
    }

}
