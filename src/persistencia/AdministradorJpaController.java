/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Accesosistema;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Administrador;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Jhon
 */
public class AdministradorJpaController implements Serializable {

    public AdministradorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Administrador administrador) throws PreexistingEntityException, Exception {
        if (administrador.getAccesosistemaList() == null) {
            administrador.setAccesosistemaList(new ArrayList<Accesosistema>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Accesosistema> attachedAccesosistemaList = new ArrayList<Accesosistema>();
            for (Accesosistema accesosistemaListAccesosistemaToAttach : administrador.getAccesosistemaList()) {
                accesosistemaListAccesosistemaToAttach = em.getReference(accesosistemaListAccesosistemaToAttach.getClass(), accesosistemaListAccesosistemaToAttach.getAccesoID());
                attachedAccesosistemaList.add(accesosistemaListAccesosistemaToAttach);
            }
            administrador.setAccesosistemaList(attachedAccesosistemaList);
            em.persist(administrador);
            for (Accesosistema accesosistemaListAccesosistema : administrador.getAccesosistemaList()) {
                Administrador oldAdminIDOfAccesosistemaListAccesosistema = accesosistemaListAccesosistema.getAdminID();
                accesosistemaListAccesosistema.setAdminID(administrador);
                accesosistemaListAccesosistema = em.merge(accesosistemaListAccesosistema);
                if (oldAdminIDOfAccesosistemaListAccesosistema != null) {
                    oldAdminIDOfAccesosistemaListAccesosistema.getAccesosistemaList().remove(accesosistemaListAccesosistema);
                    oldAdminIDOfAccesosistemaListAccesosistema = em.merge(oldAdminIDOfAccesosistemaListAccesosistema);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAdministrador(administrador.getAdminID()) != null) {
                throw new PreexistingEntityException("Administrador " + administrador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Administrador administrador) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrador persistentAdministrador = em.find(Administrador.class, administrador.getAdminID());
            List<Accesosistema> accesosistemaListOld = persistentAdministrador.getAccesosistemaList();
            List<Accesosistema> accesosistemaListNew = administrador.getAccesosistemaList();
            List<String> illegalOrphanMessages = null;
            for (Accesosistema accesosistemaListOldAccesosistema : accesosistemaListOld) {
                if (!accesosistemaListNew.contains(accesosistemaListOldAccesosistema)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Accesosistema " + accesosistemaListOldAccesosistema + " since its adminID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Accesosistema> attachedAccesosistemaListNew = new ArrayList<Accesosistema>();
            for (Accesosistema accesosistemaListNewAccesosistemaToAttach : accesosistemaListNew) {
                accesosistemaListNewAccesosistemaToAttach = em.getReference(accesosistemaListNewAccesosistemaToAttach.getClass(), accesosistemaListNewAccesosistemaToAttach.getAccesoID());
                attachedAccesosistemaListNew.add(accesosistemaListNewAccesosistemaToAttach);
            }
            accesosistemaListNew = attachedAccesosistemaListNew;
            administrador.setAccesosistemaList(accesosistemaListNew);
            administrador = em.merge(administrador);
            for (Accesosistema accesosistemaListNewAccesosistema : accesosistemaListNew) {
                if (!accesosistemaListOld.contains(accesosistemaListNewAccesosistema)) {
                    Administrador oldAdminIDOfAccesosistemaListNewAccesosistema = accesosistemaListNewAccesosistema.getAdminID();
                    accesosistemaListNewAccesosistema.setAdminID(administrador);
                    accesosistemaListNewAccesosistema = em.merge(accesosistemaListNewAccesosistema);
                    if (oldAdminIDOfAccesosistemaListNewAccesosistema != null && !oldAdminIDOfAccesosistemaListNewAccesosistema.equals(administrador)) {
                        oldAdminIDOfAccesosistemaListNewAccesosistema.getAccesosistemaList().remove(accesosistemaListNewAccesosistema);
                        oldAdminIDOfAccesosistemaListNewAccesosistema = em.merge(oldAdminIDOfAccesosistemaListNewAccesosistema);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = administrador.getAdminID();
                if (findAdministrador(id) == null) {
                    throw new NonexistentEntityException("The administrador with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrador administrador;
            try {
                administrador = em.getReference(Administrador.class, id);
                administrador.getAdminID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The administrador with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Accesosistema> accesosistemaListOrphanCheck = administrador.getAccesosistemaList();
            for (Accesosistema accesosistemaListOrphanCheckAccesosistema : accesosistemaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Administrador (" + administrador + ") cannot be destroyed since the Accesosistema " + accesosistemaListOrphanCheckAccesosistema + " in its accesosistemaList field has a non-nullable adminID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(administrador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Administrador> findAdministradorEntities() {
        return findAdministradorEntities(true, -1, -1);
    }

    public List<Administrador> findAdministradorEntities(int maxResults, int firstResult) {
        return findAdministradorEntities(false, maxResults, firstResult);
    }

    private List<Administrador> findAdministradorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Administrador.class));
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

    public Administrador findAdministrador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Administrador.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdministradorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Administrador> rt = cq.from(Administrador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
