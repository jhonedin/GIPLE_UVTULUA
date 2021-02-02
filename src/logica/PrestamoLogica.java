/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import modelo.Equipo;
import modelo.Prestamo;
import modelo.Usuario;
import persistencia.PrestamoJpaController;

/**
 *
 * @author Usuario
 */
public class PrestamoLogica {
    
    PrestamoJpaController prestamoDAO;
    
    public PrestamoLogica() {
        prestamoDAO = new PrestamoJpaController();
    }
    
    public Prestamo buscarPrestamo(long id){
        return prestamoDAO.findPrestamo(id);
    }
    
    public void registrarPrestamo(Prestamo prestamo){
        prestamoDAO.create(prestamo);
    }
    
    public void modificarPrestamo(Prestamo prestamo) throws Exception{
        prestamoDAO.edit(prestamo);
    }
    
    public List<Prestamo> listarPrestamos(){
        return prestamoDAO.findPrestamoEntities();
    }
    
    public void registrarListadoPrestamos(List<Prestamo> listaPrestamo){
        
        for (Prestamo listaPrestamo1 : listaPrestamo) {
            registrarPrestamo(listaPrestamo1);
        }
        
    }
    
    public List<Prestamo> listarPrestamosporUsuario(Usuario usuario){
        List<Prestamo> prestamosPorUsuario = new ArrayList<Prestamo>();
        List<Prestamo> prestamos = listarPrestamos();
        for(int i=0;i < prestamos.size();i++)
        {
            if(prestamos.get(i).getUsuarioID().equals(usuario))
            {
                prestamosPorUsuario.add(prestamos.get(i));
            }
        }
        return prestamosPorUsuario;
    }
    
    public List<Prestamo> listarPrestamosporUsuarioPendientes(Usuario usuario){
        List<Prestamo> prestamosPorUsuario = new ArrayList<Prestamo>();
        List<Prestamo> prestamos = listarPrestamos();
        for(int i=0;i < prestamos.size();i++)
        {
            // Busca solo los prestamos pendientes de un usuario
            if(prestamos.get(i).getUsuarioID().equals(usuario) && prestamos.get(i).getEstadoPrestamo().equals("PENDIENTE"))
            {
                prestamosPorUsuario.add(prestamos.get(i));
            }
        }
        return prestamosPorUsuario;
    }    
    
    public boolean verificaPrestamoEquipoToUsuario(Equipo eq, Usuario us){
        boolean verificacion = false;
        List<Prestamo> prestamos = new ArrayList<Prestamo>();
        prestamos = listarPrestamos();
        for(int i=0;i<prestamos.size();i++){
            //Aqui se verifica que el equipo esta prestado a un usuario y ademas que esta pendiente
            // por devolverlo
            if((prestamos.get(i).getEquipoID().equals(eq)) && (prestamos.get(i).getUsuarioID().equals(us)) && (prestamos.get(i).getEstadoPrestamo().equals("PENDIENTE"))){
                verificacion = true;
                return verificacion;
            }
        }
        return verificacion;
    }
    
    public long getIDPrestamo(Equipo eq, Usuario us){
        long id = -1; // si devuelve -1 no encontro un prestamo que corresponda al usuario al equipo y que este pendiente
        List<Prestamo> prestamos = new ArrayList<Prestamo>();
        prestamos = listarPrestamos();
        for(int i=0;i<prestamos.size();i++){
            //Aqui se verifica que el equipo esta prestado a un usuario y ademas que esta pendiente
            // por devolverlo
            if((prestamos.get(i).getEquipoID().equals(eq)) && (prestamos.get(i).getUsuarioID().equals(us)) && (prestamos.get(i).getEstadoPrestamo().equals("PENDIENTE"))){
                id = prestamos.get(i).getPrestamoID();
                return id;
            }
        }        
        return id;
    }
    
    public List<Prestamo> listarPrestamosporAsignatura(String asignatura){
        List<Prestamo> prestamosPorAsignatura = new ArrayList<Prestamo>();
        List<Prestamo> prestamos = listarPrestamos();
        if(asignatura.equals("NINGUNA")){
            prestamosPorAsignatura = prestamos;
        }
        else{
            for(int i=0;i<prestamos.size();i++){
                if(prestamos.get(i).getAsignatura().equals(asignatura)){
                    prestamosPorAsignatura.add(prestamos.get(i));
                }
            }
        }
        return prestamosPorAsignatura;
    }
    
    public List<Prestamo> listarPrestamosporAsignaturaPendientes(String asignatura){    
            List<Prestamo> prestamosPorAsignatura = new ArrayList<Prestamo>();
        List<Prestamo> prestamos = listarPrestamos();
        if(asignatura.equals("NINGUNA")){
            for(int i=0;i<prestamos.size();i++){
                if(prestamos.get(i).getEstadoPrestamo().equals("PENDIENTE")){
                    prestamosPorAsignatura.add(prestamos.get(i));
                }
            }
        }
        else{
            for(int i=0;i<prestamos.size();i++){
                if((prestamos.get(i).getAsignatura().equals(asignatura)) && (prestamos.get(i).getEstadoPrestamo().equals("PENDIENTE"))){
                    prestamosPorAsignatura.add(prestamos.get(i));
                }
            }
        }
        return prestamosPorAsignatura;
    
    }
    
    public List<Prestamo> listarPrestamosporEquipo(Equipo equipo){
        List<Prestamo> prestamosPorEquipos = new ArrayList<Prestamo>();
        List<Prestamo> prestamos = listarPrestamos();
        for(int i=0; i < prestamos.size();i++){
            if(Objects.equals(prestamos.get(i).getEquipoID().getEquipoID(), equipo.getEquipoID())){
                prestamosPorEquipos.add(prestamos.get(i));
            }
        }
        return prestamosPorEquipos;
    }
    
    
}
