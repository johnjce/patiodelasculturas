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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhontsPC
 */
@Entity
@Table(name = "homePage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HomePage.findAll", query = "SELECT h FROM HomePage h")
    , @NamedQuery(name = "HomePage.findByHomeLeyenda", query = "SELECT h FROM HomePage h WHERE h.homeLeyenda = :homeLeyenda")
    , @NamedQuery(name = "HomePage.findByProyectoTitulo", query = "SELECT h FROM HomePage h WHERE h.proyectoTitulo = :proyectoTitulo")
    , @NamedQuery(name = "HomePage.findByPropuestaTitulo", query = "SELECT h FROM HomePage h WHERE h.propuestaTitulo = :propuestaTitulo")
    , @NamedQuery(name = "HomePage.findByDetrasTitulo", query = "SELECT h FROM HomePage h WHERE h.detrasTitulo = :detrasTitulo")
    , @NamedQuery(name = "HomePage.findById", query = "SELECT h FROM HomePage h WHERE h.id = :id")})
public class HomePage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "homeLeyenda")
    private String homeLeyenda;
    @Basic(optional = false)
    @Lob
    @Column(name = "homeImg")
    private byte[] homeImg;
    @Basic(optional = false)
    @Column(name = "proyectoTitulo")
    private String proyectoTitulo;
    @Basic(optional = false)
    @Lob
    @Column(name = "proyectoDescripcion")
    private String proyectoDescripcion;
    @Basic(optional = false)
    @Lob
    @Column(name = "proyectoImgBaile")
    private byte[] proyectoImgBaile;
    @Basic(optional = false)
    @Lob
    @Column(name = "proyectoImgTaller")
    private byte[] proyectoImgTaller;
    @Basic(optional = false)
    @Lob
    @Column(name = "proyectoImgEvento")
    private byte[] proyectoImgEvento;
    @Basic(optional = false)
    @Column(name = "propuestaTitulo")
    private String propuestaTitulo;
    @Basic(optional = false)
    @Lob
    @Column(name = "propuestaDescripcion")
    private String propuestaDescripcion;
    @Basic(optional = false)
    @Column(name = "detrasTitulo")
    private String detrasTitulo;
    @Basic(optional = false)
    @Lob
    @Column(name = "detrasDescripcion")
    private String detrasDescripcion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    public HomePage() {
    }

    public HomePage(Integer id) {
        this.id = id;
    }

    public HomePage(Integer id, String homeLeyenda, byte[] homeImg, String proyectoTitulo, String proyectoDescripcion, byte[] proyectoImgBaile, byte[] proyectoImgTaller, byte[] proyectoImgEvento, String propuestaTitulo, String propuestaDescripcion, String detrasTitulo, String detrasDescripcion) {
        this.id = id;
        this.homeLeyenda = homeLeyenda;
        this.homeImg = homeImg;
        this.proyectoTitulo = proyectoTitulo;
        this.proyectoDescripcion = proyectoDescripcion;
        this.proyectoImgBaile = proyectoImgBaile;
        this.proyectoImgTaller = proyectoImgTaller;
        this.proyectoImgEvento = proyectoImgEvento;
        this.propuestaTitulo = propuestaTitulo;
        this.propuestaDescripcion = propuestaDescripcion;
        this.detrasTitulo = detrasTitulo;
        this.detrasDescripcion = detrasDescripcion;
    }

    public String getHomeLeyenda() {
        return homeLeyenda;
    }

    public void setHomeLeyenda(String homeLeyenda) {
        this.homeLeyenda = homeLeyenda;
    }

    public byte[] getHomeImg() {
        return homeImg;
    }

    public void setHomeImg(byte[] homeImg) {
        this.homeImg = homeImg;
    }

    public String getProyectoTitulo() {
        return proyectoTitulo;
    }

    public void setProyectoTitulo(String proyectoTitulo) {
        this.proyectoTitulo = proyectoTitulo;
    }

    public String getProyectoDescripcion() {
        return proyectoDescripcion;
    }

    public void setProyectoDescripcion(String proyectoDescripcion) {
        this.proyectoDescripcion = proyectoDescripcion;
    }

    public byte[] getProyectoImgBaile() {
        return proyectoImgBaile;
    }

    public void setProyectoImgBaile(byte[] proyectoImgBaile) {
        this.proyectoImgBaile = proyectoImgBaile;
    }

    public byte[] getProyectoImgTaller() {
        return proyectoImgTaller;
    }

    public void setProyectoImgTaller(byte[] proyectoImgTaller) {
        this.proyectoImgTaller = proyectoImgTaller;
    }

    public byte[] getProyectoImgEvento() {
        return proyectoImgEvento;
    }

    public void setProyectoImgEvento(byte[] proyectoImgEvento) {
        this.proyectoImgEvento = proyectoImgEvento;
    }

    public String getPropuestaTitulo() {
        return propuestaTitulo;
    }

    public void setPropuestaTitulo(String propuestaTitulo) {
        this.propuestaTitulo = propuestaTitulo;
    }

    public String getPropuestaDescripcion() {
        return propuestaDescripcion;
    }

    public void setPropuestaDescripcion(String propuestaDescripcion) {
        this.propuestaDescripcion = propuestaDescripcion;
    }

    public String getDetrasTitulo() {
        return detrasTitulo;
    }

    public void setDetrasTitulo(String detrasTitulo) {
        this.detrasTitulo = detrasTitulo;
    }

    public String getDetrasDescripcion() {
        return detrasDescripcion;
    }

    public void setDetrasDescripcion(String detrasDescripcion) {
        this.detrasDescripcion = detrasDescripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof HomePage)) {
            return false;
        }
        HomePage other = (HomePage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.HomePage[ id=" + id + " ]";
    }
    
}
