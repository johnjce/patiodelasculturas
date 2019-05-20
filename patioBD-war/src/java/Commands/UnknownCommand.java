package Commands;

import ejb.PersistLog;
import ejb.StaticsBean;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

public class UnknownCommand extends FrontCommand{
    HttpSession session;
    @Override
    public void process(){
        session = request.getSession();
        try {
            StaticsBean sb = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/StaticsBean!ejb.StaticsBean");
            sb.addComponent("UnknownCommand");
            PersistLog persistLog = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/PersistLog!ejb.PersistLog");
            persistLog.setText("UnknownCommand", "404", (String) session.getAttribute("nickName"));
        } catch (NamingException | IOException ex) {
            Logger.getLogger(UnknownCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        forward("/unknown.jsp");
    }

}