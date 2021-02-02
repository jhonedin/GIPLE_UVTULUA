/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Usuario;
import modelo.Equipo;
import modelo.Prestamo;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Jhon
 */
public class PrestamoJpaController implements Serializable {

    public PrestamoJpaController(){
        this.emf = Persistence.createEntityManagerFactory("GIPLE_UVTULUAPU");
    }
    
    public PrestamoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prestamo prestamo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioID = prestamo.getUsuarioID();
            if (usuarioID != null) {
                usuarioID = em.getReference(usuarioID.getClass(), usuarioID.getUsuarioID());
                prestamo.setUsuarioID(usuarioID);
            }
            Equipo equipoID = prestamo.getEquipoID();
            if (equipoID != null) {
                equipoID = em.getReference(equipoID.getClass(), equipoID.getEquipoID());
                prestamo.setEquipoID(equipoID);
            }
            em.persist(prestamo);
            if (usuarioID != null) {
                usuarioID.getPrestamoList().add(prestamo);
                usuarioID = em.merge(usuarioID);
            }
            if (equipoID != null) {
                equipoID.getPrestamoList().add(prestamo);
                equipoID = em.merge(equipoID);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Prestamo prestamo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prestamo persistentPrestamo = em.find(Prestamo.class, prestamo.getPrestamoID());
            Usuario usuarioIDOld = persistentPrestamo.getUsuarioID();
            Usuario usuarioIDNew = prestamo.getUsuarioID();
            Equipo equipoIDOld = persistentPrestamo.getEquipoID();
            Equipo equipoIDNew = prestamo.getEquipoID();
            if (usuarioIDNew != null) {
                usuarioIDNew = em.getReference(usuarioIDNew.getClass(), usuarioIDNew.getUsuarioID());
                prestamo.setUsuarioID(usuarioIDNew);
            }
            if (equipoIDNew != null) {
                equipoIDNew = em.getReference(equipoIDNew.getClass(), equipoIDNew.getEquipoID());
                prestamo.setEquipoID(equipoIDNew);
            }
            prestamo = em.merge(prestamo);
            if (usuarioIDOld != null && !usuarioIDOld.equals(usuarioIDNew)) {
                usuarioIDOld.getPrestamoList().remove(prestamo);
                usuarioIDOld = em.merge(usuarioIDOld);
            }
            if (usuarioIDNew != null && !usuarioIDNew.equals(usuarioIDOld)) {
                usuarioIDNew.getPrestamoList().add(prestamo);
                usuarioIDNew = em.merge(usuarioIDNew);
            }
            if (equipoIDOld != null && !equipoIDOld.equals(equipoIDNew)) {
                equipoIDOld.getPrestamoList().remove(prestamo);
                equipoIDOld = em.merge(equipoIDOld);
            }
            if (equipoIDNew != null && !equipoIDNew.equals(equipoIDOld)) {
                equipoIDNew.getPrestamoList().add(prestamo);
                equipoIDNew = em.merge(equipoIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = prestamo.getPrestamoID();
                if (findPrestamo(id) == null) {
                    throw new NonexistentEntityException("The prestamo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prestamo prestamo;
            try {
                prestamo = em.getReference(Prestamo.class, id);
                prestamo.getPrestamoID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prestamo with id " + id + " no longer exists.", enfe);
            }
            Usuario usuarioID = prestamo.getUsuarioID();
            if (usuarioID != null) {
                usuarioID.getPrestamoList().remove(prestamo);
                usuarioID = em.merge(usuarioID);
            }
            Equipo equipoID = prestamo.getEquipoID();
            if (equipoID != null) {
                equipoID.getPrestamoList().remove(prestamo);
                equipoID = em.merge(equipoID);
            }
            em.remove(prestamo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Prestamo> findPrestamoEntities() {
        return findPrestamoEntities(true, -1, -1);
    }

    public List<Prestamo> findPrestamoEntities(int maxResults, int firstResult) {
        return findPrestamoEntities(false, maxResults, firstResult);
    }

    private List<Prestamo> findPrestamoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Prestamo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Prestamo findPrestamo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Prestamo.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrestamoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Prestamo> rt = cq.from(Prestamo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
