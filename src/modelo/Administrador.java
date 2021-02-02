/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jhon
 */
@Entity
@Table(name = "administrador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a")
    , @NamedQuery(name = "Administrador.findByAdminID", query = "SELECT a FROM Administrador a WHERE a.adminID = :adminID")
    , @NamedQuery(name = "Administrador.findByNombreAdmin", query = "SELECT a FROM Administrador a WHERE a.nombreAdmin = :nombreAdmin")
    , @NamedQuery(name = "Administrador.findByTipoAdmin", query = "SELECT a FROM Administrador a WHERE a.tipoAdmin = :tipoAdmin")
    , @NamedQuery(name = "Administrador.findByContrasena", query = "SELECT a FROM Administrador a WHERE a.contrasena = :contrasena")})
public class Administrador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "adminID")
    private Integer adminID;
    @Basic(optional = false)
    @Column(name = "nombreAdmin")
    private String nombreAdmin;
    @Basic(optional = false)
    @Column(name = "tipoAdmin")
    private String tipoAdmin;
    @Basic(optional = false)
    @Column(name = "contrasena")
    private String contrasena;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adminID")
    private List<Accesosistema> accesosistemaList;

    public Administrador() {
    }

    public Administrador(Integer adminID) {
        this.adminID = adminID;
    }

    public Administrador(Integer adminID, String nombreAdmin, String tipoAdmin, String contrasena) {
        this.adminID = adminID;
        this.nombreAdmin = nombreAdmin;
        this.tipoAdmin = tipoAdmin;
        this.contrasena = contrasena;
    }

    public Integer getAdminID() {
        return adminID;
    }

    public void setAdminID(Integer adminID) {
        this.adminID = adminID;
    }

    public String getNombreAdmin() {
        return nombreAdmin;
    }

    public void setNombreAdmin(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
    }

    public String getTipoAdmin() {
        return tipoAdmin;
    }

    public void setTipoAdmin(String tipoAdmin) {
        this.tipoAdmin = tipoAdmin;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @XmlTransient
    public List<Accesosistema> getAccesosistemaList() {
        return accesosistemaList;
    }

    public void setAccesosistemaList(List<Accesosistema> accesosistemaList) {
        this.accesosistemaList = accesosistemaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminID != null ? adminID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrador)) {
            return false;
        }
        Administrador other = (Administrador) object;
        if ((this.adminID == null && other.adminID != null) || (this.adminID != null && !this.adminID.equals(other.adminID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Administrador[ adminID=" + adminID + " ]";
    }
    
}
