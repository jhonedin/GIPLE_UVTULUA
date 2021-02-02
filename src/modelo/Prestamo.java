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
@Table(name = "prestamo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p")
    , @NamedQuery(name = "Prestamo.findByPrestamoID", query = "SELECT p FROM Prestamo p WHERE p.prestamoID = :prestamoID")
    , @NamedQuery(name = "Prestamo.findByHoraEntrega", query = "SELECT p FROM Prestamo p WHERE p.horaEntrega = :horaEntrega")
    , @NamedQuery(name = "Prestamo.findByHoraDevolucion", query = "SELECT p FROM Prestamo p WHERE p.horaDevolucion = :horaDevolucion")
    , @NamedQuery(name = "Prestamo.findByFechaEntrega", query = "SELECT p FROM Prestamo p WHERE p.fechaEntrega = :fechaEntrega")
    , @NamedQuery(name = "Prestamo.findByFechaDevolucion", query = "SELECT p FROM Prestamo p WHERE p.fechaDevolucion = :fechaDevolucion")
    , @NamedQuery(name = "Prestamo.findByAsignatura", query = "SELECT p FROM Prestamo p WHERE p.asignatura = :asignatura")
    , @NamedQuery(name = "Prestamo.findByObservaciones", query = "SELECT p FROM Prestamo p WHERE p.observaciones = :observaciones")
    , @NamedQuery(name = "Prestamo.findByEstadoPrestamo", query = "SELECT p FROM Prestamo p WHERE p.estadoPrestamo = :estadoPrestamo")
    , @NamedQuery(name = "Prestamo.findByNombreMonitorAtendio", query = "SELECT p FROM Prestamo p WHERE p.nombreMonitorAtendio = :nombreMonitorAtendio")
    , @NamedQuery(name = "Prestamo.findByNombreMonitorDevolvio", query = "SELECT p FROM Prestamo p WHERE p.nombreMonitorDevolvio = :nombreMonitorDevolvio")})
public class Prestamo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prestamoID")
    private Long prestamoID;
    @Basic(optional = false)
    @Column(name = "horaEntrega")
    private String horaEntrega;
    @Column(name = "horaDevolucion")
    private String horaDevolucion;
    @Basic(optional = false)
    @Column(name = "fechaEntrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @Column(name = "fechaDevolucion")
    @Temporal(TemporalType.DATE)
    private Date fechaDevolucion;
    @Column(name = "asignatura")
    private String asignatura;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "estadoPrestamo")
    private String estadoPrestamo;
    @Column(name = "nombreMonitorAtendio")
    private String nombreMonitorAtendio;
    @Column(name = "nombreMonitorDevolvio")
    private String nombreMonitorDevolvio;
    @JoinColumn(name = "usuarioID", referencedColumnName = "usuarioID")
    @ManyToOne(optional = false)
    private Usuario usuarioID;
    @JoinColumn(name = "equipoID", referencedColumnName = "equipoID")
    @ManyToOne(optional = false)
    private Equipo equipoID;

    public Prestamo() {
    }

    public Prestamo(Long prestamoID) {
        this.prestamoID = prestamoID;
    }

    public Prestamo(Long prestamoID, String horaEntrega, Date fechaEntrega) {
        this.prestamoID = prestamoID;
        this.horaEntrega = horaEntrega;
        this.fechaEntrega = fechaEntrega;
    }

    public Long getPrestamoID() {
        return prestamoID;
    }

    public void setPrestamoID(Long prestamoID) {
        this.prestamoID = prestamoID;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public String getHoraDevolucion() {
        return horaDevolucion;
    }

    public void setHoraDevolucion(String horaDevolucion) {
        this.horaDevolucion = horaDevolucion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(String estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }

    public String getNombreMonitorAtendio() {
        return nombreMonitorAtendio;
    }

    public void setNombreMonitorAtendio(String nombreMonitorAtendio) {
        this.nombreMonitorAtendio = nombreMonitorAtendio;
    }

    public String getNombreMonitorDevolvio() {
        return nombreMonitorDevolvio;
    }

    public void setNombreMonitorDevolvio(String nombreMonitorDevolvio) {
        this.nombreMonitorDevolvio = nombreMonitorDevolvio;
    }

    public Usuario getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Usuario usuarioID) {
        this.usuarioID = usuarioID;
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
        hash += (prestamoID != null ? prestamoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.prestamoID == null && other.prestamoID != null) || (this.prestamoID != null && !this.prestamoID.equals(other.prestamoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Prestamo[ prestamoID=" + prestamoID + " ]";
    }
    
}
