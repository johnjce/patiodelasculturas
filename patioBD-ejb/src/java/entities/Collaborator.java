/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jhontsPC
 */
@Entity
@Table(name = "collaborator")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Collaborator.findAll", query = "SELECT c FROM Collaborator c")
    , @NamedQuery(name = "Collaborator.findById", query = "SELECT c FROM Collaborator c WHERE c.id = :id")
    , @NamedQuery(name = "Collaborator.findByName", query = "SELECT c FROM Collaborator c WHERE c.name = :name")
    , @NamedQuery(name = "Collaborator.findByShortDescription", query = "SELECT c FROM Collaborator c WHERE c.shortDescription = :shortDescription")
    , @NamedQuery(name = "Collaborator.findByNetwoksId", query = "SELECT c FROM Collaborator c WHERE c.netwoksId = :netwoksId")})
public class Collaborator implements Serializable {

    @Basic(optional = false)
    @Lob
    @Column(name = "logo")
    private byte[] logo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCollaborator")
    private Collection<PersonasPatio> personasPatioCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "shortDescription")
    private String shortDescription;
    @Basic(optional = false)
    @Column(name = "netwoksId")
    private int netwoksId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Collection<Socialnetwoks> socialnetwoksCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "collaboratorId")
    private Collection<Eventxcollaborator> eventxcollaboratorCollection;

    public Collaborator() {
    }

    public Collaborator(Integer id) {
        this.id = id;
    }

    public Collaborator(Integer id, String name, byte[] logo, String description, String shortDescription, int netwoksId) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.description = description;
        this.shortDescription = shortDescription;
        this.netwoksId = netwoksId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getNetwoksId() {
        return netwoksId;
    }

    public void setNetwoksId(int netwoksId) {
        this.netwoksId = netwoksId;
    }

    @XmlTransient
    public Collection<Socialnetwoks> getSocialnetwoksCollection() {
        return socialnetwoksCollection;
    }

    public void setSocialnetwoksCollection(Collection<Socialnetwoks> socialnetwoksCollection) {
        this.socialnetwoksCollection = socialnetwoksCollection;
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
        if (!(object instanceof Collaborator)) {
            return false;
        }
        Collaborator other = (Collaborator) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Collaborator[ id=" + id + " ]";
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    @XmlTransient
    public Collection<PersonasPatio> getPersonasPatioCollection() {
        return personasPatioCollection;
    }

    public void setPersonasPatioCollection(Collection<PersonasPatio> personasPatioCollection) {
        this.personasPatioCollection = personasPatioCollection;
    }
    
}
