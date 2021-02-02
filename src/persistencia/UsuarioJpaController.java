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
import javax.persistence.Persistence;
import modelo.Ingreso;
import modelo.Usuario;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Jhon
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(){
        this.emf = Persistence.createEntityManagerFactory("GIPLE_UVTULUAPU");
    }
    
    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getPrestamoList() == null) {
            usuario.setPrestamoList(new ArrayList<Prestamo>());
        }
        if (usuario.getIngresoList() == null) {
            usuario.setIngresoList(new ArrayList<Ingreso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Prestamo> attachedPrestamoList = new ArrayList<Prestamo>();
            for (Prestamo prestamoListPrestamoToAttach : usuario.getPrestamoList()) {
                prestamoListPrestamoToAttach = em.getReference(prestamoListPrestamoToAttach.getClass(), prestamoListPrestamoToAttach.getPrestamoID());
                attachedPrestamoList.add(prestamoListPrestamoToAttach);
            }
            usuario.setPrestamoList(attachedPrestamoList);
            List<Ingreso> attachedIngresoList = new ArrayList<Ingreso>();
            for (Ingreso ingresoListIngresoToAttach : usuario.getIngresoList()) {
                ingresoListIngresoToAttach = em.getReference(ingresoListIngresoToAttach.getClass(), ingresoListIngresoToAttach.getIngresoID());
                attachedIngresoList.add(ingresoListIngresoToAttach);
            }
            usuario.setIngresoList(attachedIngresoList);
            em.persist(usuario);
            for (Prestamo prestamoListPrestamo : usuario.getPrestamoList()) {
                Usuario oldUsuarioIDOfPrestamoListPrestamo = prestamoListPrestamo.getUsuarioID();
                prestamoListPrestamo.setUsuarioID(usuario);
                prestamoListPrestamo = em.merge(prestamoListPrestamo);
                if (oldUsuarioIDOfPrestamoListPrestamo != null) {
                    oldUsuarioIDOfPrestamoListPrestamo.getPrestamoList().remove(prestamoListPrestamo);
                    oldUsuarioIDOfPrestamoListPrestamo = em.merge(oldUsuarioIDOfPrestamoListPrestamo);
                }
            }
            for (Ingreso ingresoListIngreso : usuario.getIngresoList()) {
                Usuario oldUsuarioIDOfIngresoListIngreso = ingresoListIngreso.getUsuarioID();
                ingresoListIngreso.setUsuarioID(usuario);
                ingresoListIngreso = em.merge(ingresoListIngreso);
                if (oldUsuarioIDOfIngresoListIngreso != null) {
                    oldUsuarioIDOfIngresoListIngreso.getIngresoList().remove(ingresoListIngreso);
                    oldUsuarioIDOfIngresoListIngreso = em.merge(oldUsuarioIDOfIngresoListIngreso);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getUsuarioID()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getUsuarioID());
            List<Prestamo> prestamoListOld = persistentUsuario.getPrestamoList();
            List<Prestamo> prestamoListNew = usuario.getPrestamoList();
            List<Ingreso> ingresoListOld = persistentUsuario.getIngresoList();
            List<Ingreso> ingresoListNew = usuario.getIngresoList();
            List<String> illegalOrphanMessages = null;
            for (Prestamo prestamoListOldPrestamo : prestamoListOld) {
                if (!prestamoListNew.contains(prestamoListOldPrestamo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Prestamo " + prestamoListOldPrestamo + " since its usuarioID field is not nullable.");
                }
            }
            for (Ingreso ingresoListOldIngreso : ingresoListOld) {
                if (!ingresoListNew.contains(ingresoListOldIngreso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ingreso " + ingresoListOldIngreso + " since its usuarioID field is not nullable.");
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
            usuario.setPrestamoList(prestamoListNew);
            List<Ingreso> attachedIngresoListNew = new ArrayList<Ingreso>();
            for (Ingreso ingresoListNewIngresoToAttach : ingresoListNew) {
                ingresoListNewIngresoToAttach = em.getReference(ingresoListNewIngresoToAttach.getClass(), ingresoListNewIngresoToAttach.getIngresoID());
                attachedIngresoListNew.add(ingresoListNewIngresoToAttach);
            }
            ingresoListNew = attachedIngresoListNew;
            usuario.setIngresoList(ingresoListNew);
            usuario = em.merge(usuario);
            for (Prestamo prestamoListNewPrestamo : prestamoListNew) {
                if (!prestamoListOld.contains(prestamoListNewPrestamo)) {
                    Usuario oldUsuarioIDOfPrestamoListNewPrestamo = prestamoListNewPrestamo.getUsuarioID();
                    prestamoListNewPrestamo.setUsuarioID(usuario);
                    prestamoListNewPrestamo = em.merge(prestamoListNewPrestamo);
                    if (oldUsuarioIDOfPrestamoListNewPrestamo != null && !oldUsuarioIDOfPrestamoListNewPrestamo.equals(usuario)) {
                        oldUsuarioIDOfPrestamoListNewPrestamo.getPrestamoList().remove(prestamoListNewPrestamo);
                        oldUsuarioIDOfPrestamoListNewPrestamo = em.merge(oldUsuarioIDOfPrestamoListNewPrestamo);
                    }
                }
            }
            for (Ingreso ingresoListNewIngreso : ingresoListNew) {
                if (!ingresoListOld.contains(ingresoListNewIngreso)) {
                    Usuario oldUsuarioIDOfIngresoListNewIngreso = ingresoListNewIngreso.getUsuarioID();
                    ingresoListNewIngreso.setUsuarioID(usuario);
                    ingresoListNewIngreso = em.merge(ingresoListNewIngreso);
                    if (oldUsuarioIDOfIngresoListNewIngreso != null && !oldUsuarioIDOfIngresoListNewIngreso.equals(usuario)) {
                        oldUsuarioIDOfIngresoListNewIngreso.getIngresoList().remove(ingresoListNewIngreso);
                        oldUsuarioIDOfIngresoListNewIngreso = em.merge(oldUsuarioIDOfIngresoListNewIngreso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getUsuarioID();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getUsuarioID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Prestamo> prestamoListOrphanCheck = usuario.getPrestamoList();
            for (Prestamo prestamoListOrphanCheckPrestamo : prestamoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Prestamo " + prestamoListOrphanCheckPrestamo + " in its prestamoList field has a non-nullable usuarioID field.");
            }
            List<Ingreso> ingresoListOrphanCheck = usuario.getIngresoList();
            for (Ingreso ingresoListOrphanCheckIngreso : ingresoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Ingreso " + ingresoListOrphanCheckIngreso + " in its ingresoList field has a non-nullable usuarioID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
