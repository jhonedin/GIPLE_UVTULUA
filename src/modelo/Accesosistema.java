/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jhon
 */
@Entity
@Table(name = "accesosistema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accesosistema.findAll", query = "SELECT a FROM Accesosistema a")
    , @NamedQuery(name = "Accesosistema.findByAccesoID", query = "SELECT a FROM Accesosistema a WHERE a.accesoID = :accesoID")
    , @NamedQuery(name = "Accesosistema.findByFecha", query = "SELECT a FROM Accesosistema a WHERE a.fecha = :fecha")
    , @NamedQuery(name = "Accesosistema.findByHoraIngreso", query = "SELECT a FROM Accesosistema a WHERE a.horaIngreso = :horaIngreso")})
public class Accesosistema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "accesoID")
    private Long accesoID;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "horaIngreso")
    private String horaIngreso;
    @JoinColumn(name = "adminID", referencedColumnName = "adminID")
    @ManyToOne(optional = false)
    private Administrador adminID;

    public Accesosistema() {
    }

    public Accesosistema(Long accesoID) {
        this.accesoID = accesoID;
    }

    public Accesosistema(Long accesoID, Date fecha, String horaIngreso) {
        this.accesoID = accesoID;
        this.fecha = fecha;
        this.horaIngreso = horaIngreso;
    }

    public Long getAccesoID() {
        return accesoID;
    }

    public void setAccesoID(Long accesoID) {
        this.accesoID = accesoID;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public Administrador getAdminID() {
        return adminID;
    }

    public void setAdminID(Administrador adminID) {
        this.adminID = adminID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accesoID != null ? accesoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accesosistema)) {
            return false;
        }
        Accesosistema other = (Accesosistema) object;
        if ((this.accesoID == null && other.accesoID != null) || (this.accesoID != null && !this.accesoID.equals(other.accesoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Accesosistema[ accesoID=" + accesoID + " ]";
    }
    
}
