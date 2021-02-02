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
import modelo.Prestamo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Equipo;
import modelo.Mantenimiento;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Jhon
 */
public class EquipoJpaController implements Serializable {

    public EquipoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Equipo equipo) throws PreexistingEntityException, Exception {
        if (equipo.getPrestamoList() == null) {
            equipo.setPrestamoList(new ArrayList<Prestamo>());
        }
        if (equipo.getMantenimientoList() == null) {
            equipo.setMantenimientoList(new ArrayList<Mantenimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Prestamo> attachedPrestamoList = new ArrayList<Prestamo>();
            for (Prestamo prestamoListPrestamoToAttach : equipo.getPrestamoList()) {
                prestamoListPrestamoToAttach = em.getReference(prestamoListPrestamoToAttach.getClass(), prestamoListPrestamoToAttach.getPrestamoID());
                attachedPrestamoList.add(prestamoListPrestamoToAttach);
            }
            equipo.setPrestamoList(attachedPrestamoList);
            List<Mantenimiento> attachedMantenimientoList = new ArrayList<Mantenimiento>();
            for (Mantenimiento mantenimientoListMantenimientoToAttach : equipo.getMantenimientoList()) {
                mantenimientoListMantenimientoToAttach = em.getReference(mantenimientoListMantenimientoToAttach.getClass(), mantenimientoListMantenimientoToAttach.getMantenimientoID());
                attachedMantenimientoList.add(mantenimientoListMantenimientoToAttach);
            }
            equipo.setMantenimientoList(attachedMantenimientoList);
            em.persist(equipo);
            for (Prestamo prestamoListPrestamo : equipo.getPrestamoList()) {
                Equipo oldEquipoIDOfPrestamoListPrestamo = prestamoListPrestamo.getEquipoID();
                prestamoListPrestamo.setEquipoID(equipo);
                prestamoListPrestamo = em.merge(prestamoListPrestamo);
                if (oldEquipoIDOfPrestamoListPrestamo != null) {
                    oldEquipoIDOfPrestamoListPrestamo.getPrestamoList().remove(prestamoListPrestamo);
                    oldEquipoIDOfPrestamoListPrestamo = em.merge(oldEquipoIDOfPrestamoListPrestamo);
                }
            }
            for (Mantenimiento mantenimientoListMantenimiento : equipo.getMantenimientoList()) {
                Equipo oldEquipoIDOfMantenimientoListMantenimiento = mantenimientoListMantenimiento.getEquipoID();
                mantenimientoListMantenimiento.setEquipoID(equipo);
                mantenimientoListMantenimiento = em.merge(mantenimientoListMantenimiento);
                if (oldEquipoIDOfMantenimientoListMantenimiento != null) {
                    oldEquipoIDOfMantenimientoListMantenimiento.getMantenimientoList().remove(mantenimientoListMantenimiento);
                    oldEquipoIDOfMantenimientoListMantenimiento = em.merge(oldEquipoIDOfMantenimientoListMantenimiento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEquipo(equipo.getEquipoID()) != null) {
                throw new PreexistingEntityException("Equipo " + equipo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Equipo equipo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipo persistentEquipo = em.find(Equipo.class, equipo.getEquipoID());
            List<Prestamo> prestamoListOld = persistentEquipo.getPrestamoList();
            List<Prestamo> prestamoListNew = equipo.getPrestamoList();
            List<Mantenimiento> mantenimientoListOld = persistentEquipo.getMantenimientoList();
            List<Mantenimiento> mantenimientoListNew = equipo.getMantenimientoList();
            List<String> illegalOrphanMessages = null;
            for (Prestamo prestamoListOldPrestamo : prestamoListOld) {
                if (!prestamoListNew.contains(prestamoListOldPrestamo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Prestamo " + prestamoListOldPrestamo + " since its equipoID field is not nullable.");
                }
            }
            for (Mantenimiento mantenimientoListOldMantenimiento : mantenimientoListOld) {
                if (!mantenimientoListNew.contains(mantenimientoListOldMantenimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mantenimiento " + mantenimientoListOldMantenimiento + " since its equipoID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Prestamo> attachedPrestamoListNew = new ArrayList<Prestamo>();
            for (Prestamo prestamoListNewPrestamoToAttach : prestamoListNew) {
                prestamoListNewPrestamoToAttach = em.getReference(prestamoListNewPrestamoToAttach.getClass(), prestamoListNewPrestamoToAttach.getPrestamoID());
                attachedPrestamoListNew.add(prestamoListNewPrestamoToAttach);
            }
            prestamoListNew = attachedPrestamoListNew;
            equipo.setPrestamoList(prestamoListNew);
            List<Mantenimiento> attachedMantenimientoListNew = new ArrayList<Mantenimiento>();
            for (Mantenimiento mantenimientoListNewMantenimientoToAttach : mantenimientoListNew) {
                mantenimientoListNewMantenimientoToAttach = em.getReference(mantenimientoListNewMantenimientoToAttach.getClass(), mantenimientoListNewMantenimientoToAttach.getMantenimientoID());
                attachedMantenimientoListNew.add(mantenimientoListNewMantenimientoToAttach);
            }
            mantenimientoListNew = attachedMantenimientoListNew;
            equipo.setMantenimientoList(mantenimientoListNew);
            equipo = em.merge(equipo);
            for (Prestamo prestamoListNewPrestamo : prestamoListNew) {
                if (!prestamoListOld.contains(prestamoListNewPrestamo)) {
                    Equipo oldEquipoIDOfPrestamoListNewPrestamo = prestamoListNewPrestamo.getEquipoID();
                    prestamoListNewPrestamo.setEquipoID(equipo);
                    prestamoListNewPrestamo = em.merge(prestamoListNewPrestamo);
                    if (oldEquipoIDOfPrestamoListNewPrestamo != null && !oldEquipoIDOfPrestamoListNewPrestamo.equals(equipo)) {
                        oldEquipoIDOfPrestamoListNewPrestamo.getPrestamoList().remove(prestamoListNewPrestamo);
                        oldEquipoIDOfPrestamoListNewPrestamo = em.merge(oldEquipoIDOfPrestamoListNewPrestamo);
                    }
                }
            }
            for (Mantenimiento mantenimientoListNewMantenimiento : mantenimientoListNew) {
                if (!mantenimientoListOld.contains(mantenimientoListNewMantenimiento)) {
                    Equipo oldEquipoIDOfMantenimientoListNewMantenimiento = mantenimientoListNewMantenimiento.getEquipoID();
                    mantenimientoListNewMantenimiento.setEquipoID(equipo);
                    mantenimientoListNewMantenimiento = em.merge(mantenimientoListNewMantenimiento);
                    if (oldEquipoIDOfMantenimientoListNewMantenimiento != null && !oldEquipoIDOfMantenimientoListNewMantenimiento.equals(equipo)) {
                        oldEquipoIDOfMantenimientoListNewMantenimiento.getMantenimientoList().remove(mantenimientoListNewMantenimiento);
                        oldEquipoIDOfMantenimientoListNewMantenimiento = em.merge(oldEquipoIDOfMantenimientoListNewMantenimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = equipo.getEquipoID();
                if (findEquipo(id) == null) {
                    throw new NonexistentEntityException("The equipo with id " + id + " no longer exists.");
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
            Equipo equipo;
            try {
                equipo = em.getReference(Equipo.class, id);
                equipo.getEquipoID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The equipo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Prestamo> prestamoListOrphanCheck = equipo.getPrestamoList();
            for (Prestamo prestamoListOrphanCheckPrestamo : prestamoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Equipo (" + equipo + ") cannot be destroyed since the Prestamo " + prestamoListOrphanCheckPrestamo + " in its prestamoList field has a non-nullable equipoID field.");
            }
            List<Mantenimiento> mantenimientoListOrphanCheck = equipo.getMantenimientoList();
            for (Mantenimiento mantenimientoListOrphanCheckMantenimiento : mantenimientoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Equipo (" + equipo + ") cannot be destroyed since the Mantenimiento " + mantenimientoListOrphanCheckMantenimiento + " in its mantenimientoList field has a non-nullable equipoID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(equipo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Equipo> findEquipoEntities() {
        return findEquipoEntities(true, -1, -1);
    }

    public List<Equipo> findEquipoEntities(int maxResults, int firstResult) {
        return findEquipoEntities(false, maxResults, firstResult);
    }

    private List<Equipo> findEquipoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Equipo.class));
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

    public Equipo findEquipo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Equipo.class, id);
        } finally {
            em.close();
        }
    }

    public int getEquipoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Equipo> rt = cq.from(Equipo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
