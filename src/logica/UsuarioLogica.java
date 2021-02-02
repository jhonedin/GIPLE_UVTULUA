/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
import persistencia.UsuarioJpaController;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class UsuarioLogica {

    UsuarioJpaController usuarioDAO;

    public UsuarioLogica() {
        usuarioDAO = new UsuarioJpaController();
    }
    
    public List<Usuario> listarUsuarios(){
        return usuarioDAO.findUsuarioEntities();
    }
    
    public List<Usuario> listarUsuariosPorFiltro(String prog, String rol, String semestre){
         List<Usuario> usuariosPorFiltro = new ArrayList<Usuario>();
         List<Usuario> usuarios = listarUsuarios();
         if(prog.equals("TODOS") && rol.equals("TODOS") && semestre.equals("TODOS")){
             usuariosPorFiltro = usuarios;
             return usuariosPorFiltro;
         }
         else{
            if(rol.equals("TODOS") && semestre.equals("TODOS") && !prog.equals("TODOS")){
                for(int i=0;i < usuarios.size();i++){
                     if(Integer.valueOf(prog) == usuarios.get(i).getProgramaAcademico()){
                         usuariosPorFiltro.add(usuarios.get(i));
                     }          
                }
                return usuariosPorFiltro;
             }
            if(prog.equals("TODOS") && semestre.equals("TODOS") && !rol.equals("TODOS")){             
                for(int i=0;i < usuarios.size();i++){
                    if(rol.equals(usuarios.get(i).getRol())){
                        usuariosPorFiltro.add(usuarios.get(i));
                    }
                }
                return usuariosPorFiltro;
            }
            if(prog.equals("TODOS") && rol.equals("TODOS") && !semestre.equals("TODOS")){
                for(int i=0;i < usuarios.size();i++){
                    if(Integer.valueOf(semestre) == usuarios.get(i).getSemestre()){
                        usuariosPorFiltro.add(usuarios.get(i));
                    }
                }
                return usuariosPorFiltro;
            }
            if(!prog.equals("TODOS") && !semestre.equals("TODOS") && rol.equals("TODOS")){
                for(int i=0;i < usuarios.size();i++){
                    if( (Integer.valueOf(prog) == usuarios.get(i).getProgramaAcademico()) && 
                        (Integer.valueOf(semestre) == usuarios.get(i).getSemestre())){
                         usuariosPorFiltro.add(usuarios.get(i));
                    }
                }
                return usuariosPorFiltro;
            }
            else{
                for(int i=0;i < usuarios.size();i++){
                    if( (Integer.valueOf(prog) == usuarios.get(i).getProgramaAcademico()) && 
                        (rol.equals(usuarios.get(i).getRol())) && 
                        (Integer.valueOf(semestre) == usuarios.get(i).getSemestre())){
                        usuariosPorFiltro.add(usuarios.get(i));
                    }
                }
                return usuariosPorFiltro;
            }
        }
    }
    
    public Usuario buscarUsuario(int id){
        return usuarioDAO.findUsuario(id);
    }
    
    public void RegistrarUsuario(Usuario usuario) throws Exception{
        if(usuario.getUsuarioID()== null) throw new Exception("No se ha ingresado identificación de usuario");
        if(usuario.getUsuarioID()< 1000000) throw new Exception("Identificación de usuario invalida");
        if(usuario.getUsuarioID()< 0) throw new Exception("Identificación de usuario invalida");
        try{
            usuario.getNombreUsuario().charAt(0);
        }
        catch(Exception e){
            throw new Exception("No se ha ingresado nombre de usuario");
        }
        if(usuario.getProgramaAcademico()< 1000 || usuario.getProgramaAcademico()> 9999) throw new Exception("Programa academico invalido");
        if(usuario.getSemestre() < 0) throw new Exception("Semestre invalido");
        try{
            usuario.getRol().charAt(0);
        }
        catch(Exception e){
            throw new Exception("No se ha ingresado rol de usuario");
        }
        String rol = usuario.getRol();
        if(!(rol.equals("MATRICULADO") || rol.equals("PENDIENTE MATRICULA") || rol.equals("DOCENTE")
                || rol.equals("ADMINISTRATIVO"))) throw new Exception("Rol invalido");
        
        usuarioDAO.create(usuario);
    }
    
     public void modificar(Usuario usuario) throws NonexistentEntityException, Exception{
        if(usuario.getUsuarioID()== null) throw new Exception("No se ha ingresado identificación de usuario");
        if(usuario.getUsuarioID()< 1000000) throw new Exception("Identificación de usuario invalida");
        if(usuario.getUsuarioID()< 0) throw new Exception("Identificación de usuario invalida");
        try{
            usuario.getNombreUsuario().charAt(0);
        }
        catch(Exception e){
            throw new Exception("No se ha ingresado nombre de usuario");
        }
        if(usuario.getProgramaAcademico()< 1000 || usuario.getProgramaAcademico()> 9999) throw new Exception("Programa academico invalido");
        if(usuario.getSemestre() < 0) throw new Exception("Semestre invalido");
        try{
            usuario.getRol().charAt(0);
        }
        catch(Exception e){
            throw new Exception("No se ha ingresado rol de usuario");
        }
        String rol = usuario.getRol();
       if(!(rol.equals("MATRICULADO") || rol.equals("PENDIENTE MATRICULA") || rol.equals("DOCENTE")
                || rol.equals("ADMINISTRATIVO"))) throw new Exception("Rol invalido");
        usuarioDAO.edit(usuario);
    }
     
     
    
}
