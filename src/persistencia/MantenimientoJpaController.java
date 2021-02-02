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
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Equipo;
import modelo.Mantenimiento;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Jhon
 */
public class MantenimientoJpaController implements Serializable {

    public MantenimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mantenimiento mantenimiento) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipo equipoID = mantenimiento.getEquipoID();
            if (equipoID != null) {
                equipoID = em.getReference(equipoID.getClass(), equipoID.getEquipoID());
                mantenimiento.setEquipoID(equipoID);
            }
            em.persist(mantenimiento);
            if (equipoID != null) {
                equipoID.getMantenimientoList().add(mantenimiento);
                equipoID = em.merge(equipoID);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMantenimiento(mantenimiento.getMantenimientoID()) != null) {
                throw new PreexistingEntityException("Mantenimiento " + mantenimiento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mantenimiento mantenimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mantenimiento persistentMantenimiento = em.find(Mantenimiento.class, mantenimiento.getMantenimientoID());
            Equipo equipoIDOld = persistentMantenimiento.getEquipoID();
            Equipo equipoIDNew = mantenimiento.getEquipoID();
            if (equipoIDNew != null) {
                equipoIDNew = em.getReference(equipoIDNew.getClass(), equipoIDNew.getEquipoID());
                mantenimiento.setEquipoID(equipoIDNew);
            }
            mantenimiento = em.merge(mantenimiento);
            if (equipoIDOld != null && !equipoIDOld.equals(equipoIDNew)) {
                equipoIDOld.getMantenimientoList().remove(mantenimiento);
                equipoIDOld = em.merge(equipoIDOld);
            }
            if (equipoIDNew != null && !equipoIDNew.equals(equipoIDOld)) {
                equipoIDNew.getMantenimientoList().add(mantenimiento);
                equipoIDNew = em.merge(equipoIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mantenimiento.getMantenimientoID();
                if (findMantenimiento(id) == null) {
                    throw new NonexistentEntityException("The mantenimiento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mantenimiento mantenimiento;
            try {
                mantenimiento = em.getReference(Mantenimiento.class, id);
                mantenimiento.getMantenimientoID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mantenimiento with id " + id + " no longer exists.", enfe);
            }
            Equipo equipoID = mantenimiento.getEquipoID();
            if (equipoID != null) {
                equipoID.getMantenimientoList().remove(mantenimiento);
                equipoID = em.merge(equipoID);
            }
            em.remove(mantenimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mantenimiento> findMantenimientoEntities() {
        return findMantenimientoEntities(true, -1, -1);
    }

    public List<Mantenimiento> findMantenimientoEntities(int maxResults, int firstResult) {
        return findMantenimientoEntities(false, maxResults, firstResult);
    }

    private List<Mantenimiento> findMantenimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mantenimiento.class));
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

    public Mantenimiento findMantenimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mantenimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getMantenimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mantenimiento> rt = cq.from(Mantenimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
