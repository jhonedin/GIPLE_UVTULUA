/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jhon
 */
@Entity
@Table(name = "equipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e")
    , @NamedQuery(name = "Equipo.findByEquipoID", query = "SELECT e FROM Equipo e WHERE e.equipoID = :equipoID")
    , @NamedQuery(name = "Equipo.findByDescripcion", query = "SELECT e FROM Equipo e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "Equipo.findByTipoEquipo", query = "SELECT e FROM Equipo e WHERE e.tipoEquipo = :tipoEquipo")
    , @NamedQuery(name = "Equipo.findByFechaIngreso", query = "SELECT e FROM Equipo e WHERE e.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "Equipo.findByFechaUltimaRevision", query = "SELECT e FROM Equipo e WHERE e.fechaUltimaRevision = :fechaUltimaRevision")
    , @NamedQuery(name = "Equipo.findByHorasUso", query = "SELECT e FROM Equipo e WHERE e.horasUso = :horasUso")
    , @NamedQuery(name = "Equipo.findByEstado", query = "SELECT e FROM Equipo e WHERE e.estado = :estado")})
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "equipoID")
    private Integer equipoID;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "tipoEquipo")
    private Character tipoEquipo;
    @Column(name = "fechaIngreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Column(name = "fechaUltimaRevision")
    @Temporal(TemporalType.DATE)
    private Date fechaUltimaRevision;
    @Column(name = "horasUso")
    private Integer horasUso;
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoID")
    private List<Prestamo> prestamoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoID")
    private List<Mantenimiento> mantenimientoList;

    public Equipo() {
    }

    public Equipo(Integer equipoID) {
        this.equipoID = equipoID;
    }

    public Integer getEquipoID() {
        return equipoID;
    }

    public void setEquipoID(Integer equipoID) {
        this.equipoID = equipoID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Character getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(Character tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaUltimaRevision() {
        return fechaUltimaRevision;
    }

    public void setFechaUltimaRevision(Date fechaUltimaRevision) {
        this.fechaUltimaRevision = fechaUltimaRevision;
    }

    public Integer getHorasUso() {
        return horasUso;
    }

    public void setHorasUso(Integer horasUso) {
        this.horasUso = horasUso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Prestamo> getPrestamoList() {
        return prestamoList;
    }

    public void setPrestamoList(List<Prestamo> prestamoList) {
        this.prestamoList = prestamoList;
    }

    @XmlTransient
    public List<Mantenimiento> getMantenimientoList() {
        return mantenimientoList;
    }

    public void setMantenimientoList(List<Mantenimiento> mantenimientoList) {
        this.mantenimientoList = mantenimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equipoID != null ? equipoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.equipoID == null && other.equipoID != null) || (this.equipoID != null && !this.equipoID.equals(other.equipoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Equipo[ equipoID=" + equipoID + " ]";
    }
    
}
