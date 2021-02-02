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
@Table(name = "mantenimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mantenimiento.findAll", query = "SELECT m FROM Mantenimiento m")
    , @NamedQuery(name = "Mantenimiento.findByMantenimientoID", query = "SELECT m FROM Mantenimiento m WHERE m.mantenimientoID = :mantenimientoID")
    , @NamedQuery(name = "Mantenimiento.findByObservaciones", query = "SELECT m FROM Mantenimiento m WHERE m.observaciones = :observaciones")
    , @NamedQuery(name = "Mantenimiento.findByEstado", query = "SELECT m FROM Mantenimiento m WHERE m.estado = :estado")
    , @NamedQuery(name = "Mantenimiento.findByMateriales", query = "SELECT m FROM Mantenimiento m WHERE m.materiales = :materiales")
    , @NamedQuery(name = "Mantenimiento.findByPersona", query = "SELECT m FROM Mantenimiento m WHERE m.persona = :persona")
    , @NamedQuery(name = "Mantenimiento.findByLugar", query = "SELECT m FROM Mantenimiento m WHERE m.lugar = :lugar")
    , @NamedQuery(name = "Mantenimiento.findByFechaPInicio", query = "SELECT m FROM Mantenimiento m WHERE m.fechaPInicio = :fechaPInicio")
    , @NamedQuery(name = "Mantenimiento.findByFechaPFin", query = "SELECT m FROM Mantenimiento m WHERE m.fechaPFin = :fechaPFin")
    , @NamedQuery(name = "Mantenimiento.findByFechaRInicio", query = "SELECT m FROM Mantenimiento m WHERE m.fechaRInicio = :fechaRInicio")
    , @NamedQuery(name = "Mantenimiento.findByFechaRFin", query = "SELECT m FROM Mantenimiento m WHERE m.fechaRFin = :fechaRFin")
    , @NamedQuery(name = "Mantenimiento.findByTipo", query = "SELECT m FROM Mantenimiento m WHERE m.tipo = :tipo")})
public class Mantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "mantenimientoID")
    private Integer mantenimientoID;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "estado")
    private String estado;
    @Column(name = "materiales")
    private String materiales;
    @Column(name = "persona")
    private String persona;
    @Column(name = "lugar")
    private String lugar;
    @Basic(optional = false)
    @Column(name = "fechaPInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaPInicio;
    @Column(name = "fechaPFin")
    @Temporal(TemporalType.DATE)
    private Date fechaPFin;
    @Column(name = "fechaRInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaRInicio;
    @Column(name = "fechaRFin")
    @Temporal(TemporalType.DATE)
    private Date fechaRFin;
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "equipoID", referencedColumnName = "equipoID")
    @ManyToOne(optional = false)
    private Equipo equipoID;

    public Mantenimiento() {
    }

    public Mantenimiento(Integer mantenimientoID) {
        this.mantenimientoID = mantenimientoID;
    }

    public Mantenimiento(Integer mantenimientoID, Date fechaPInicio) {
        this.mantenimientoID = mantenimientoID;
        this.fechaPInicio = fechaPInicio;
    }

    public Integer getMantenimientoID() {
        return mantenimientoID;
    }

    public void setMantenimientoID(Integer mantenimientoID) {
        this.mantenimientoID = mantenimientoID;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMateriales() {
        return materiales;
    }

    public void setMateriales(String materiales) {
        this.materiales = materiales;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFechaPInicio() {
        return fechaPInicio;
    }

    public void setFechaPInicio(Date fechaPInicio) {
        this.fechaPInicio = fechaPInicio;
    }

    public Date getFechaPFin() {
        return fechaPFin;
    }

    public void setFechaPFin(Date fechaPFin) {
        this.fechaPFin = fechaPFin;
    }

    public Date getFechaRInicio() {
        return fechaRInicio;
    }

    public void setFechaRInicio(Date fechaRInicio) {
        this.fechaRInicio = fechaRInicio;
    }

    public Date getFechaRFin() {
        return fechaRFin;
    }

    public void setFechaRFin(Date fechaRFin) {
        this.fechaRFin = fechaRFin;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Equipo getEquipoID() {
        return equipoID;
    }

    public void setEquipoID(Equipo equipoID) {
        this.equipoID = equipoID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mantenimientoID != null ? mantenimientoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mantenimiento)) {
            return false;
        }
        Mantenimiento other = (Mantenimiento) object;
        if ((this.mantenimientoID == null && other.mantenimientoID != null) || (this.mantenimientoID != null && !this.mantenimientoID.equals(other.mantenimientoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Mantenimiento[ mantenimientoID=" + mantenimientoID + " ]";
    }
    
}
