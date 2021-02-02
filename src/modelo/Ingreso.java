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
@Table(name = "ingreso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingreso.findAll", query = "SELECT i FROM Ingreso i")
    , @NamedQuery(name = "Ingreso.findByIngresoID", query = "SELECT i FROM Ingreso i WHERE i.ingresoID = :ingresoID")
    , @NamedQuery(name = "Ingreso.findByFecha", query = "SELECT i FROM Ingreso i WHERE i.fecha = :fecha")
    , @NamedQuery(name = "Ingreso.findByHoraIngreso", query = "SELECT i FROM Ingreso i WHERE i.horaIngreso = :horaIngreso")
    , @NamedQuery(name = "Ingreso.findByHoraSalida", query = "SELECT i FROM Ingreso i WHERE i.horaSalida = :horaSalida")})
public class Ingreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ingresoID")
    private Long ingresoID;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "horaIngreso")
    private String horaIngreso;
    @Column(name = "horaSalida")
    private String horaSalida;
    @JoinColumn(name = "usuarioID", referencedColumnName = "usuarioID")
    @ManyToOne(optional = false)
    private Usuario usuarioID;

    public Ingreso() {
    }

    public Ingreso(Long ingresoID) {
        this.ingresoID = ingresoID;
    }

    public Ingreso(Long ingresoID, Date fecha, String horaIngreso) {
        this.ingresoID = ingresoID;
        this.fecha = fecha;
        this.horaIngreso = horaIngreso;
    }

    public Long getIngresoID() {
        return ingresoID;
    }

    public void setIngresoID(Long ingresoID) {
        this.ingresoID = ingresoID;
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

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Usuario getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Usuario usuarioID) {
        this.usuarioID = usuarioID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ingresoID != null ? ingresoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingreso)) {
            return false;
        }
        Ingreso other = (Ingreso) object;
        if ((this.ingresoID == null && other.ingresoID != null) || (this.ingresoID != null && !this.ingresoID.equals(other.ingresoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Ingreso[ ingresoID=" + ingresoID + " ]";
    }
    
}
