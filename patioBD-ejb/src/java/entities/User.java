package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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

@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id")
    , @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name")
    , @NamedQuery(name = "User.findBySurname", query = "SELECT u FROM User u WHERE u.surname = :surname")
    , @NamedQuery(name = "User.findByNickname", query = "SELECT u FROM User u WHERE u.credentials.nickname = :nickname")
    , @NamedQuery(name = "User.findByBirthDate", query = "SELECT u FROM User u WHERE u.birthDate = :birthDate")
    , @NamedQuery(name = "User.findByProfilePic", query = "SELECT u FROM User u WHERE u.profilePic = :profilePic")
    , @NamedQuery(name = "User.findByRegisterDate", query = "SELECT u FROM User u WHERE u.registerDate = :registerDate")})
public class User implements Serializable {

    @Basic(optional = false)
    @Column(name = "nickname")
    private String nickname;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Lob
    @Column(name = "profilePic")
    private byte[] profilePic;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Collection<Eventxcomment> eventxcommentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Event> eventCollection;

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
    @Column(name = "surname")
    private String surname;
    

    @Embedded
    private Credentiales credentials;
    
    @Basic(optional = false)
    @Column(name = "birthDate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Basic(optional = false)
    @Column(name = "registerDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;
    @JoinColumn(name = "idRol", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Role idRol;
    

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name, String surname, String nickname, String password, Date birthDate, byte[] profilePic, Date registerDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.credentials = new Credentiales(nickname, password);
        this.birthDate = birthDate;
        this.profilePic = profilePic;
        this.registerDate = registerDate;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Role getRol() {
        return idRol;
    }

    public void setIdRol(Role idRol) {
        this.idRol = idRol;
    }

    public String getNickname() {
        return this.credentials.getNickname();
    }

    public void setNickname(String nickname) {
        this.credentials.setNickname(nickname);
    }

    public String getPassword() {
        return this.credentials.getPassword();
    }

    public void setPassword(String password) {
        this.credentials.setPassword(password);
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "entities.User[ id=" + id + " ]";
    }
    
    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }

    @XmlTransient
    public Collection<Eventxcomment> getEventxcommentCollection() {
        return eventxcommentCollection;
    }

    public void setEventxcommentCollection(Collection<Eventxcomment> eventxcommentCollection) {
        this.eventxcommentCollection = eventxcommentCollection;
    }

    @XmlTransient
    public Collection<Event> getEventCollection() {
        return eventCollection;
    }

    public void setEventCollection(Collection<Event> eventCollection) {
        this.eventCollection = eventCollection;
    }

}
