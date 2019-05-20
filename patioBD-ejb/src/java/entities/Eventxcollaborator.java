/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhontsPC
 */
@Entity
@Table(name = "eventxcollaborator")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eventxcollaborator.findAll", query = "SELECT e FROM Eventxcollaborator e")
    , @NamedQuery(name = "Eventxcollaborator.findById", query = "SELECT e FROM Eventxcollaborator e WHERE e.id = :id")})
public class Eventxcollaborator implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "collaboratorId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Collaborator collaboratorId;
    @JoinColumn(name = "eventId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Event eventId;

    public Eventxcollaborator() {
    }

    public Eventxcollaborator(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collaborator getCollaboratorId() {
        return collaboratorId;
    }

    public void setCollaboratorId(Collaborator collaboratorId) {
        this.collaboratorId = collaboratorId;
    }

    public Event getEventId() {
        return eventId;
    }

    public void setEventId(Event eventId) {
        this.eventId = eventId;
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
        if (!(object instanceof Eventxcollaborator)) {
            return false;
        }
        Eventxcollaborator other = (Eventxcollaborator) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Eventxcollaborator[ id=" + id + " ]";
    }
    
}
