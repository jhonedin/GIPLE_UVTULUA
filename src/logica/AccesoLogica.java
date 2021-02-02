/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.List;
import modelo.Accesosistema;
import modelo.Administrador;
import persistencia.AccesosistemaJpaController;

/**
 *
 * @author Usuario
 */
public class AccesoLogica {

    AccesosistemaJpaController accesoDAO;
    
    public AccesoLogica() {
        accesoDAO = new AccesosistemaJpaController();
    }
    
    public void registrarAcceso(Accesosistema acceso){
        accesoDAO.create(acceso);
    }
    
    public List<Accesosistema> listarAccesos(){
        return accesoDAO.findAccesosistemaEntities();
    }
    
    
    public List<Accesosistema> listarAccesoporAdmin(Administrador Admin){
        List<Accesosistema> accesosPorAdmin = new ArrayList<Accesosistema>();
        List<Accesosistema> accesos = listarAccesos();
        for(int i=0;i < accesos.size();i++)
        {
            if(accesos.get(i).getAdminID().getAdminID() == Admin.getAdminID())
            {
                accesosPorAdmin.add(accesos.get(i));
            }
        }
        return accesosPorAdmin;
    }    
    
    public Accesosistema ultimoAccesoSistema(Administrador Admin){
        Accesosistema ultimoAcceso = new Accesosistema();
        List<Accesosistema> listaAccesos = new ArrayList<Accesosistema>();
        listaAccesos = listarAccesoporAdmin(Admin);
        int posicion = listaAccesos.size()-1;
        if(posicion>=0) ultimoAcceso = listaAccesos.get(posicion);
        return ultimoAcceso;
    }
    
}
