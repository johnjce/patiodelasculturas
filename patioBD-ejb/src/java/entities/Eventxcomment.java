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
@Table(name = "eventxcomment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eventxcomment.findAll", query = "SELECT e FROM Eventxcomment e")
    , @NamedQuery(name = "Eventxcomment.findById", query = "SELECT e FROM Eventxcomment e WHERE e.id = :id")
    , @NamedQuery(name = "Eventxcomment.findByCreationDate", query = "SELECT e FROM Eventxcomment e WHERE e.creationDate = :creationDate")
    , @NamedQuery(name = "Eventxcomment.findByComment", query = "SELECT e FROM Eventxcomment e WHERE e.comment = :comment")})
public class Eventxcomment implements Serializable {

    @JoinColumn(name = "idUser", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User idUser;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "creationDate")
    private int creationDate;
    @Basic(optional = false)
    @Column(name = "comment")
    private String comment;
    @JoinColumn(name = "idEvent", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Event idEvent;

    public Eventxcomment() {
    }

    public Eventxcomment(Integer id) {
        this.id = id;
    }

    public Eventxcomment(Integer id, int creationDate, String comment) {
        this.id = id;
        this.creationDate = creationDate;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Event getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Event idEvent) {
        this.idEvent = idEvent;
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
        if (!(object instanceof Eventxcomment)) {
            return false;
        }
        Eventxcomment other = (Eventxcomment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Eventxcomment[ id=" + id + " ]";
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }
    
}
