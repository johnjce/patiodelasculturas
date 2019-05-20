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
@Table(name = "socialnetwoks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Socialnetwoks.findAll", query = "SELECT s FROM Socialnetwoks s")
    , @NamedQuery(name = "Socialnetwoks.findById", query = "SELECT s FROM Socialnetwoks s WHERE s.id = :id")
    , @NamedQuery(name = "Socialnetwoks.findBySocialNetworkUrl", query = "SELECT s FROM Socialnetwoks s WHERE s.socialNetworkUrl = :socialNetworkUrl")
    , @NamedQuery(name = "Socialnetwoks.findBySocialNetworkName", query = "SELECT s FROM Socialnetwoks s WHERE s.socialNetworkName = :socialNetworkName")})
public class Socialnetwoks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "socialNetworkUrl")
    private String socialNetworkUrl;
    @Basic(optional = false)
    @Column(name = "socialNetworkName")
    private String socialNetworkName;
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Collaborator idUser;

    public Socialnetwoks() {
    }

    public Socialnetwoks(Integer id) {
        this.id = id;
    }

    public Socialnetwoks(Integer id, String socialNetworkUrl, String socialNetworkName) {
        this.id = id;
        this.socialNetworkUrl = socialNetworkUrl;
        this.socialNetworkName = socialNetworkName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSocialNetworkUrl() {
        return socialNetworkUrl;
    }

    public void setSocialNetworkUrl(String socialNetworkUrl) {
        this.socialNetworkUrl = socialNetworkUrl;
    }

    public String getSocialNetworkName() {
        return socialNetworkName;
    }

    public void setSocialNetworkName(String socialNetworkName) {
        this.socialNetworkName = socialNetworkName;
    }

    public Collaborator getIdUser() {
        return idUser;
    }

    public void setIdUser(Collaborator idUser) {
        this.idUser = idUser;
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
        if (!(object instanceof Socialnetwoks)) {
            return false;
        }
        Socialnetwoks other = (Socialnetwoks) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Socialnetwoks[ id=" + id + " ]";
    }
    
}
