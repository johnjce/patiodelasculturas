/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jhontsPC
 */
@Entity
@Table(name = "event")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e")
    , @NamedQuery(name = "Event.findById", query = "SELECT e FROM Event e WHERE e.id = :id")
    , @NamedQuery(name = "Event.findByUserId", query = "SELECT e FROM Event e WHERE e.userId = :userId")
    , @NamedQuery(name = "Event.findByTitle", query = "SELECT e FROM Event e WHERE e.title = :title")
    , @NamedQuery(name = "Event.findByPlaceEvent", query = "SELECT e FROM Event e WHERE e.placeEvent = :placeEvent")
    , @NamedQuery(name = "Event.findByEventDate", query = "SELECT e FROM Event e WHERE e.eventDate = :eventDate")
    , @NamedQuery(name = "Event.findByCreationDate", query = "SELECT e FROM Event e WHERE e.creationDate = :creationDate")
    , @NamedQuery(name = "Event.findByType", query = "SELECT e FROM Event e WHERE e.type = :type")
    , @NamedQuery(name = "Event.findImageById", query = "SELECT e FROM Event e WHERE e.id = :id")})
public class Event implements Serializable {

    @Basic(optional = false)
    @Lob
    @Column(name = "cartel")
    private byte[] cartel;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "placeEvent")
    private String placeEvent;
    @Basic(optional = false)
    @Column(name = "eventDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;
    @Basic(optional = false)
    @Column(name = "creationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;

    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEvent")
    private Collection<Eventxcomment> eventxcommentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventId")
    private Collection<Eventxcollaborator> eventxcollaboratorCollection;

    public Event() {
    }

    public Event(Integer id) {
        this.id = id;
    }

    public Event(Integer id, User userId, String title, String description, String placeEvent, Date eventDate, Date creationDate, String type, byte[] cartel) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.placeEvent = placeEvent;
        this.eventDate = eventDate;
        this.creationDate = creationDate;
        this.type = type;
        this.cartel = cartel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlaceEvent() {
        return placeEvent;
    }

    public void setPlaceEvent(String placeEvent) {
        this.placeEvent = placeEvent;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<Eventxcomment> getEventxcommentCollection() {
        return eventxcommentCollection;
    }

    public void setEventxcommentCollection(Collection<Eventxcomment> eventxcommentCollection) {
        this.eventxcommentCollection = eventxcommentCollection;
    }

    @XmlTransient
    public Collection<Eventxcollaborator> getEventxcollaboratorCollection() {
        return eventxcollaboratorCollection;
    }

    public void setEventxcollaboratorCollection(Collection<Eventxcollaborator> eventxcollaboratorCollection) {
        this.eventxcollaboratorCollection = eventxcollaboratorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "entities.Event[ id=" + id + " ]";
    }

    public byte[] getCartel() {
        return cartel;
    }

    public void setCartel(byte[] cartel) {
        this.cartel = cartel;
    }

}
