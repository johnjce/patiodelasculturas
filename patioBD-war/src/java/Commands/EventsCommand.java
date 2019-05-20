package Commands;

import ejb.StaticsBean;
import beans.EventFacade;
import ejb.PersistLog;
import entities.Event;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

public class EventsCommand extends FrontCommand {

    HttpSession ses;
    EventFacade ef;
    int start = 0;
    int end = 0;
    List eventsList = null;

    @Override
    public void process() {
        this.startFacade();
        int id = 0;
        String act = "listar";
        if (request.getParameter("id") != null && request.getParameter("act") == null) {
            showImage(Integer.parseInt((String) request.getParameter("id")));
            return;
        }
        if (request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
        }
        if (request.getParameter("act") != null) {
            act = request.getParameter("act");
        }
        switch (act) {
            case "del":
                deleteEvent(id);
                break;
            case "edit":
                getFormEdit(id);
                break;
            case "updateEvent":
                updateEvent(id);
                break;
            case "createEvent":
                createEvent();
                break;
            case "getList":
                getList();
                return;
            default:
                end = ef.getTotalByType(request.getParameter("filter"));
                ses.setAttribute("total", end);
                listarEventos();
                forward("/eventsList.jsp");
                return;
        }
        forward("/dashBoard.jsp");
    }
    
    private void getList() {
        if (request.getParameter("listName") != null) {
            if (request.getParameter("dateList") == null) {
                ses.setAttribute("dateList", new ArrayList());
            }
            this.logger("getList::" + request.getParameter("listName"));
            List dateList = ef.getAllByTypeForCalendar(request.getParameter("listName"));
            ses.setAttribute("dateList", dateList);
        }
    }
    
    private void deleteEvent(int id) {
        this.logger("deleteEven");
        if (ef.deleteEvent(id)) {
            ses.setAttribute("mensaje", "Cambios realizados con éxito.");
        }
    }

    private void updateEvent(int id) {
        this.logger("updateEvent");
        try {
            String tipo = request.getParameter("tipo");
            String titulo = request.getParameter("titulo");
            String descripcion = request.getParameter("descripcion");
            String fecha = request.getParameter("fecha");
            String lugar = request.getParameter("lugar");
            if (ef.editEvent(id, tipo, titulo, descripcion, fecha, lugar, createImagen(request.getPart("cartel")))) {
                ses.setAttribute("mensaje", "Cambios realizados con éxito.");
                ses.setAttribute("evento", null);
            }
        } catch (IOException | ServletException ex) {
        }
    }

    private void createEvent() {
        this.logger("createEvent");
        try {
            String tipo = request.getParameter("tipo");
            String titulo = request.getParameter("titulo");
            String descripcion = request.getParameter("descripcion");
            String fecha = request.getParameter("fecha");
            String lugar = request.getParameter("lugar");
            InputStream imgCartel=null;
            if(request.getPart("cartel")!=null){
                imgCartel = createImagen(request.getPart("cartel"));
            }
            ef.createEvent(titulo, descripcion, lugar, fecha, tipo, imgCartel);
            ses.setAttribute("eventsList", eventsList);
            ses.setAttribute("mensaje", "Cambios realizados con éxito.");
        } catch (IOException | ServletException ex) {
        }
    }

    public void listarEventos() {
        if (request.getParameter("dateList") == null) {
            ses.setAttribute("dateList", new ArrayList());
        }
        if (request.getParameter("page") != null) {
            start = Integer.parseInt(request.getParameter("page")) - 1;
            if (start > 0) {
                start *= 10;
            }
            this.logger("listarEventos-Criteria");
            eventsList = ef.getByRangeAndType(start, request.getParameter("filter"));
        } else {
            this.logger("listarEventos-JPQL");
            eventsList = ef.getAllByType(request.getParameter("filter"));
        }
        ses.setAttribute("eventsList", eventsList);
    }

    private void showImage(int id) {
        this.logger("showImage");
        OutputStream o = null;
        byte[] imgData = ef.find(id).getCartel();
        if (imgData.length == 0) {
            imgData = ef.find(1).getCartel();
        }
        imgData = scale(imgData, 300, 150);
        try {
            response.setContentType("image/jpg");
            o = response.getOutputStream();
            o.write(imgData);
            o.flush();
            o.close();
        } catch (IOException ex) {
        } finally {
            try {
                o.close();
            } catch (IOException ex) {
            }
        }
    }

    public byte[] scale(byte[] fileData, int width, int height) {
        ByteArrayInputStream in = new ByteArrayInputStream(fileData);
        try {
            BufferedImage img = ImageIO.read(in);
            if (height == 0) {
                height = (width * img.getHeight()) / img.getWidth();
            }
            if (width == 0) {
                width = (height * img.getWidth()) / img.getHeight();
            }
            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            ImageIO.write(imageBuff, "jpg", buffer);

            return buffer.toByteArray();
        } catch (IOException e) {
        }
        return null;
    }

    private void getFormEdit(int id) {
        Event e = ef.getById(id);
        ses.setAttribute("evento", e);
    }

    private void startFacade() {
        ses = request.getSession();
        ef = (EventFacade) ses.getAttribute("eventFacade");
        try {
            StaticsBean sb = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/StaticsBean!ejb.StaticsBean");
            sb.addComponent("EventsCommand");
            ef = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/EventFacade");
        } catch (NamingException e) {
            eventsList = new ArrayList();
        }
    }

    private InputStream createImagen(Part media) {
        InputStream fileContent = null;
        try {
            Part filePart = media;
            if (filePart.getSize() > 0) {
                fileContent = filePart.getInputStream();
            }
        } catch (IOException ex) {
        }
        return fileContent;
    }

    public String getUser() {
        if (this.ses.getAttribute("nickName") == null) {
            return "";
        }
        return this.ses.getAttribute("nickName").toString();
    }

    public String getPrivilegeLevel() {
        if (this.ses.getAttribute("privilegeLevel") == null) {
            return "5";
        }
        return this.ses.getAttribute("privilegeLevel").toString();
    }

    public void logger(String method) {
        try {
            StaticsBean sb = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/StaticsBean!ejb.StaticsBean");
            sb.addComponent(method);
            PersistLog persistLog = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/PersistLog!ejb.PersistLog");
            persistLog.setText("EventsCommand", method, this.getUser());
        } catch (NamingException | IOException ex) {
        }
    }
}
