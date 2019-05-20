package ejb;

import beans.UserFacade;
import entities.User;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
@LocalBean
public class UserSessionBean {

    @PersistenceContext(unitName = "patioBD-ejbPU")
    private EntityManager em;
    private String username = "";
    private int type;
    private String name;

    public boolean validateUser(String nickname, String pass) {
        try {
            UserFacade userFacade = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/UserFacade!beans.UserFacade");

            Query uq = em.createNamedQuery("User.findByNickname", User.class);
            uq.setParameter("nickname", nickname);
            User logingUser = (User) uq.getSingleResult();
            if (logingUser.getPassword().equals(pass)) {
                this.type = logingUser.getRol().getPrivilegeLevel();
                this.username = nickname;
                return true;
            }
        } catch (NamingException | NoResultException ex) {
        }
        return false;
    }

    public String getUsername() {
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/PersistLog!ejbs.PersistLog");
            persistLog.setText("SessionBean", "getUserName", username);
        } catch (NamingException | IOException ex) {
        }
        return username;
    }

    public int getType() {
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/PersistLog!ejbs.PersistLog");
            persistLog.setText("SessionBean", "getType", username);
        } catch (NamingException | IOException ex) {
        }
        return type;
    }

    public String getName() {
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/PersistLog!ejbs.PersistLog");
            persistLog.setText("SessionBean", "getName", username);
        } catch (NamingException | IOException ex) {
        }
        return name;
    }

    public void init(String username, int type, String name) {
        this.username = username;
        this.type = type;
        this.name = name;
        addUserToSingleton();
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/PersistLog!ejbs.PersistLog");
            persistLog.setText("SessionBean", "init", username);
        } catch (NamingException | IOException ex) {
        }
    }

    public void addUserToSingleton() {
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/PersistLog!ejbs.PersistLog");
            persistLog.setText("SessionBean", "addUserToSingleton", username);
        } catch (NamingException | IOException ex) {
        }
        StaticsBean sb;
        try {
            sb = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/StaticsBean!ejbs.StaticsBean");
            sb.addUser(username);
        } catch (NamingException ex) {
        }
    }

    @PreDestroy
    public void remove() {

        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/PersistLog!ejbs.PersistLog");
            persistLog.setText("SessionBean", "PreDestroy", username);
        } catch (NamingException | IOException ex) {
        }
        try {
            StaticsBean sb = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/StaticsBean!ejbs.StaticsBean");
            sb.removeUser(username);
        } catch (NamingException ex) {
        }
    }

    @PostConstruct
    public void postconstruct() {
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/PersistLog!ejbs.PersistLog");
            persistLog.setText("SessionBean", "postConstruct", username);
        } catch (NamingException | IOException ex) {
        }

    }

    @PostActivate
    public void postactivate() {
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/PersistLog!ejbs.PersistLog");
            persistLog.setText("SessionBean", "postActivate", username);
        } catch (NamingException | IOException ex) {
        }

    }

    //@PreActivate
    public void preactivate() {
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/PersistLog!ejbs.PersistLog");
            persistLog.setText("SessionBean", "preActivate", username);
        } catch (NamingException | IOException ex) {
        }

    }

    @Override
    public String toString() {
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/PersistLog!ejbs.PersistLog");
            persistLog.setText("SessionBean", "toString", username);
        } catch (NamingException | IOException ex) {
        }
        return "--->" + username + " --- " + type;
    }

    public boolean isAdmin() {
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/PersistLog!ejbs.PersistLog");
            persistLog.setText("SessionBean", "isAdmin", username);
        } catch (NamingException | IOException ex) {
        }
        return type == 0;
    }

}
