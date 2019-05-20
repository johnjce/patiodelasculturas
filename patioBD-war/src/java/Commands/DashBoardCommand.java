package Commands;

import com.sun.xml.ws.util.StringUtils;
import ejb.PersistLog;
import ejb.StaticsBean;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.Date;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

public class DashBoardCommand extends FrontCommand {

    HttpSession ses;
    Session session;
    final String host = "smtp.elpatiodelasculturas.org.es";
    final String user = "contactoweb@elpatiodelasculturas.org.es";
    final String password = "95Wb9~rg";//Cambiar datos, Ramdom generada para hacer commit :)

    @Override
    public void process() {
        try {
            StaticsBean sb = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/StaticsBean!ejb.StaticsBean");
            PersistLog persistLog = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/PersistLog!ejb.PersistLog");
            ses = request.getSession();

            if (null != request.getParameter("ema")) {
                if (sendMail()) {
                    sb.addComponent("sendEmail");
                    persistLog.setText("EventsCommand", "sendEmail", this.getUser());
                    return;
                }
            }
            sb.addComponent("DashBoardCommand");

        } catch (NamingException | IOException ex) {
        }
        forward("/dashBoard.jsp");
    }

    private boolean sendMail() {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.auth", "true");
            session = getMailSession();
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.user));
            message.setSubject("Contacto desde El patio de las culturas");
            message.setText(
                    "Tipo de contacto: " + request.getParameter("type") + "\n"
                    + "Nombre del(@) interesa@: " + request.getParameter("fname") + "\n"
                    + "Email: " + request.getParameter("email") + "\n"
                    + "Mensaje: " + request.getParameter("message") + "\n"
                    + "Enviado: " + new Timestamp(new Date().getTime()) + "por:" + this.getUser() + "\n"
            );
            //send the message  
            Transport.send(message);
            System.out.println("El mensaje se envío correctamente");
            return true;
        } catch (MessagingException e) {
            System.out.println("El mensaje no se envío, por favor intentelo de nuevo.");
            return false;
        }
    }

    public String getUser() {
        if (this.ses.getAttribute("nickName") == null) {
            return "Invitado";
        }
        return this.ses.getAttribute("nickName").toString();
    }

    private Session getMailSession() {
        if (session == null) {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", host);
                properties.put("mail.smtp.auth", "true");
                session = Session.getInstance(properties,
                        new Authenticator() {
                    @Override
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });
        }
        return session;
    }
}
