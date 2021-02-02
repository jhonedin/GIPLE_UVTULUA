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
import modelo.Accesosistema;
import modelo.Administrador;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Jhon
 */
public class AccesosistemaJpaController implements Serializable {

    public AccesosistemaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Accesosistema accesosistema) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrador adminID = accesosistema.getAdminID();
            if (adminID != null) {
                adminID = em.getReference(adminID.getClass(), adminID.getAdminID());
                accesosistema.setAdminID(adminID);
            }
            em.persist(accesosistema);
            if (adminID != null) {
                adminID.getAccesosistemaList().add(accesosistema);
                adminID = em.merge(adminID);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Accesosistema accesosistema) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Accesosistema persistentAccesosistema = em.find(Accesosistema.class, accesosistema.getAccesoID());
            Administrador adminIDOld = persistentAccesosistema.getAdminID();
            Administrador adminIDNew = accesosistema.getAdminID();
            if (adminIDNew != null) {
                adminIDNew = em.getReference(adminIDNew.getClass(), adminIDNew.getAdminID());
                accesosistema.setAdminID(adminIDNew);
            }
            accesosistema = em.merge(accesosistema);
            if (adminIDOld != null && !adminIDOld.equals(adminIDNew)) {
                adminIDOld.getAccesosistemaList().remove(accesosistema);
                adminIDOld = em.merge(adminIDOld);
            }
            if (adminIDNew != null && !adminIDNew.equals(adminIDOld)) {
                adminIDNew.getAccesosistemaList().add(accesosistema);
                adminIDNew = em.merge(adminIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = accesosistema.getAccesoID();
                if (findAccesosistema(id) == null) {
                    throw new NonexistentEntityException("The accesosistema with id " + id + " no longer exists.");
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
            Accesosistema accesosistema;
            try {
                accesosistema = em.getReference(Accesosistema.class, id);
                accesosistema.getAccesoID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The accesosistema with id " + id + " no longer exists.", enfe);
            }
            Administrador adminID = accesosistema.getAdminID();
            if (adminID != null) {
                adminID.getAccesosistemaList().remove(accesosistema);
                adminID = em.merge(adminID);
            }
            em.remove(accesosistema);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Accesosistema> findAccesosistemaEntities() {
        return findAccesosistemaEntities(true, -1, -1);
    }

    public List<Accesosistema> findAccesosistemaEntities(int maxResults, int firstResult) {
        return findAccesosistemaEntities(false, maxResults, firstResult);
    }

    private List<Accesosistema> findAccesosistemaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Accesosistema.class));
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

    public Accesosistema findAccesosistema(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Accesosistema.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccesosistemaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Accesosistema> rt = cq.from(Accesosistema.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
