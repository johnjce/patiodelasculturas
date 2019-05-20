package beans;

import entities.Event;
import entities.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class EventFacade extends AbstractFacade<Event> {

    @PersistenceContext(unitName = "patioBD-ejbPU")
    private EntityManager eventManger;

    @Override
    protected EntityManager getEntityManager() {
        return eventManger;
    }

    public EventFacade() {
        super(Event.class);
    }

    public int getTotalByType(String type) {
        Query eq = eventManger.createNamedQuery("Event.findByType", Event.class);
        eq.setParameter("type", type);
        return eq.getResultList().size();
    }

    public List getAllByType(String type) {
        String qs = "SELECT e FROM Event e WHERE e.type LIKE :type ORDER BY e.eventDate ASC";
        Query eq = eventManger.createQuery(qs);
        eq.setParameter("type", type);
        return eq.getResultList();
    }

    public List getAllByTypeForCalendar(String type) {
        String qs = "SELECT e.eventDate FROM Event e WHERE e.type LIKE :type ORDER BY e.eventDate ASC";
        Query eq = eventManger.createQuery(qs);
        eq.setParameter("type", type);
        return eq.getResultList();
    }

    public List getByRangeAndType(Integer start, String type) {
        CriteriaBuilder cb = eventManger.getCriteriaBuilder();
        CriteriaQuery< Event> q = cb.createQuery(Event.class);
        Root<Event> c = q.from(Event.class);

        q.select(c).where(cb.like(c.get("type"), type)).orderBy(cb.asc(c.get("eventDate")));
        return eventManger.createQuery(q)
                .setFirstResult(start)
                .setMaxResults(10)
                .getResultList();
    }

    public void createEvent(String title, String description, String placeEvent, String eventDate, String type, InputStream cartel) {
        try {
            Event evento = new Event();
            User user = new User(2);
            evento.setUserId(user);
            evento.setTitle(title);
            evento.setDescription(description);
            evento.setPlaceEvent(placeEvent);
            evento.setEventDate(new SimpleDateFormat("yyyy-MM-dd").parse(eventDate));
            evento.setType(type);
            if(cartel!=null)
                evento = agregarImagen(title, cartel, evento);
            else
                evento.setCartel(new byte[0]);
            this.eventManger.persist(evento);
        } catch (ParseException ex) {
            Logger.getLogger(EventFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Event agregarImagen(String title, InputStream cartel, Event evento) {
        try {
            File f = new File(title + ".jpg");
            f.createNewFile();
            OutputStream os = new FileOutputStream(f);
            byte[] buf = new byte[1024];
            int len;
            while ((len = cartel.read(buf)) > 0) {
                os.write(buf, 0, len);
            }
            os.close();
            cartel.close();
            User user = new User(2);
            evento.setUserId(user);
            InputStream is = new FileInputStream(f);
            byte[] buffer = new byte[(int) f.length()];
            int readers = is.read(buffer);
            evento.setCartel(buffer);
            f.delete();
        } catch (IOException ex) {
            Logger.getLogger(EventFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return evento;
    }

    public boolean editEvent(int id, String tipo, String titulo, String descripcion, String fecha, String lugar, InputStream cartel) {
        Query eq = eventManger.createQuery("UPDATE  Event e "
                + "SET e.title = :title, "
                + "e.description = :description, "
                + "e.eventDate = :eventDate, "
                + "e.placeEvent = :placeEvent, "
                + "e.type = :type "
                + "WHERE e.id = :id");
        eq.setParameter("title", titulo);
        eq.setParameter("description", descripcion);
        try {
            eq.setParameter("eventDate", new SimpleDateFormat("yyyy-MM-dd").parse(fecha));
        } catch (ParseException ex) {
            eq.setParameter("eventDate", "2000-01-01");
        }
        eq.setParameter("placeEvent", lugar);
        eq.setParameter("type", tipo);
        eq.setParameter("id", id);
        Event evento = eventManger.find(Event.class, id);
        if (cartel != null) {
            evento = agregarImagen(titulo, cartel, evento);
        }
        this.eventManger.persist(evento);

        return eq.executeUpdate() == 1;
    }

    public boolean deleteEvent(int id) {
        Query eq = eventManger.createQuery("DELETE FROM Event e WHERE e.id = :id");
        eq.setParameter("id", id);
        return eq.executeUpdate() == 1;
    }

    public Event getById(int id) {
        Query eq = eventManger.createNamedQuery("Event.findById", Event.class);
        eq.setParameter("id", id);
        return (Event) eq.getSingleResult();
    }

}
