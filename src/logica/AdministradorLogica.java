/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import modelo.Administrador;
import modelo.Usuario;
import persistencia.AdministradorJpaController;

/**
 *
 * @author Usuario
 */
public class AdministradorLogica {

    AdministradorJpaController administradorDAO;

    public AdministradorLogica() {
        administradorDAO = new AdministradorJpaController();
    }
    
    public Administrador buscarUsuario(int id){
        return administradorDAO.findAdministrador(id);
    }
    
    public Administrador buscarAdmin(String nick, String pass) throws Exception{
        try{
            nick.charAt(0);
            
        }
        catch(StringIndexOutOfBoundsException e){
            throw new Exception("El campo usuario no debe estar vacío");
        }
        try{
            pass.charAt(0);
        }
        catch(StringIndexOutOfBoundsException e){
            throw new Exception("El campo contraseña no debe estar vacío");
        }
        
        List<Administrador> administrador = administradorDAO.findAdministradorEntities();
        for (int i = 0; i < administrador.size(); i++) {
            System.out.println(administrador.get(i).getAdminID()+" "+administrador.get(i).getContrasena());
            if(administrador.get(i).getAdminID() == Integer.parseInt(nick) &&
                    administrador.get(i).getContrasena().equals(pass)){
                return administrador.get(i);
            }
        }  
        return null;
    }    
    
}
