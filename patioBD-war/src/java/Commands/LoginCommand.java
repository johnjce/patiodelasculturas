package Commands;

import ejb.PersistLog;
import ejb.StaticsBean;
import ejb.UserSessionBean;
import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

public class LoginCommand extends FrontCommand {

    HttpSession session;

    @Override
    public void process() {
        session = request.getSession();
        String param = request.getParameter("param");
        if ("login".equals(param)) {
            if (login(session)) {
                forward("/index.jsp");
                return;
            }
        }
        if (session.getAttribute("nickName") == null) {
            forward("/login.jsp");
            return;
        }
        if ("logout".equals(param)) {
            logout(session);
        }
    }

    private void logout(HttpSession session) {
        this.logger("logout");
        StaticsBean sb;
        try {
            sb = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/StaticsBean!ejb.StaticsBean");
            sb.addComponent("LogoutCommand");
                    System.out.println(session.getAttribute("nickName"));
            sb.removeUser((String) session.getAttribute("nickName"));
        } catch (NamingException ex) {
        }
        session.invalidate();
        forward("/index.jsp");
    }

    private boolean login(HttpSession session) {
        String nickname = request.getParameter("nickname");
        String pass = request.getParameter("password");
        UserSessionBean userSessionBean = (UserSessionBean) session.getAttribute("userSessionBean");
        if (userSessionBean == null) {
            try {
                StaticsBean sb = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/StaticsBean!ejb.StaticsBean");
                userSessionBean = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/UserSessionBean!ejb.UserSessionBean");

                if (userSessionBean.validateUser(nickname, pass)) {
                    sb.addUser(userSessionBean.getUsername());
                    this.session.setAttribute("nickName", userSessionBean.getUsername());
                    this.session.setAttribute("privilegeLevel", userSessionBean.getType());
                    this.session.setAttribute("userSessionBean", userSessionBean);
                }
                sb.addComponent("LoginCommand");
                this.logger("login");
                return true;
            } catch (NamingException | NullPointerException npe) {
                return false;
            }
        }
        return false;
    }

    public void logger(String method) {
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/PersistLog!ejb.PersistLog");
            persistLog.setText("LoginCommand", method,  (String)this.session.getAttribute("nickName"));
        } catch (NamingException | IOException ex) {
        }

    }
}
