/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Equipo;
import persistencia.EquipoJpaController;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class EquipoLogica {
    
    EquipoJpaController equipoDAO;
    private Equipo equipo;
    
    public EquipoLogica() {
        equipoDAO = new EquipoJpaController();
    }
    
    public List<Equipo> listarEquipos(){
        return equipoDAO.findEquipoEntities();
    }

    
    public void registrarEquipo(Equipo eq) throws Exception{
        //Validar el id del equipo
        
        if(eq.getHorasUso() < 0){
            throw new Exception("Las horas de uso de un equipo debe ser un valor positivo");
        }
        try{
            eq.getEstado().charAt(0);
        }catch(Exception e){
            eq.setEstado("DISPONIBLE");
        }
        
        equipoDAO.create(eq);
    }
    
    public void modificarEquipo(Equipo eq) throws NonexistentEntityException, Exception{
        if(eq.getEquipoID() == null){
            throw new Exception("El equipo/herramienta no se encuentra registrada");
        }
        if(eq.getHorasUso() < 0){
            throw new Exception("Las horas de uso de un equipo debe ser un valor positivo");
        }
        equipoDAO.edit(eq);
    }
    
    public Equipo buscarEquipo(int id){
        //validaciones
        return equipoDAO.findEquipo(id);
    }  
    
    // Esta funcion verifica la disponibilidad de un equipo
    // retorna 0 si esta disponible
    // retorna 1 si no esta disponible
    public int verificaDisponibilidadEquipo(int codigo){
        equipo = new Equipo();
        int d = 0;
        equipo = buscarEquipo(codigo);
        if(equipo.getEstado().equals("OCUPADO")){
            d = 1; // el equipo esta ocupado
        }
        else{
            if(equipo.getEstado().equals("EN MANTENIMIENTO")) d = 2; // el equipo esta en mantenimiento
            else if (equipo.getEstado().equals("DADO DE BAJA")) d = 3; // el equipo fue dado de baja
        }
        return d;
        
    }
    
    public void cambiarEstadoEquipopPrestamo(Equipo eq) throws Exception{
        if(eq.getEstado().equals("OCUPADO")){
            eq.setEstado("DISPONIBLE");
            modificarEquipo(eq);
        }
        else{
            if(eq.getEstado().equals("DISPONIBLE")){
               eq.setEstado("OCUPADO");
               modificarEquipo(eq);
            }
            else{
                JOptionPane.showMessageDialog(null, "El equipo se encuentra "+eq.getEstado()+" No esta disponible para prestamo");
            }
        }
    }

    public List<Equipo> listarEquiposPorFiltro(String tipo, String estado){
         List<Equipo> equiposPorFiltro = new ArrayList<Equipo>();
         List<Equipo> equipos = listarEquipos();
         if(tipo.equals("TODOS") && estado.equals("TODOS")){
             equiposPorFiltro = equipos;
             return equiposPorFiltro;
         }
         else{
            if(tipo.equals("TODOS") && !estado.equals("TODOS")){
                for(int i=0;i < equipos.size();i++){
                     if(estado.equals(equipos.get(i).getEstado())){
                         equiposPorFiltro.add(equipos.get(i));
                     }          
                }
                return equiposPorFiltro;
             }
            if(estado.equals("TODOS") && !tipo.equals("TODOS")){             
                for(int i=0;i < equipos.size();i++){
                    if(tipo.equals(equipos.get(i).getTipoEquipo().toString())){
                        equiposPorFiltro.add(equipos.get(i));
                    }
                }
                return equiposPorFiltro;
            }
            else{
                for(int i=0;i < equipos.size();i++){
                    if((tipo.toString().equals(equipos.get(i).getTipoEquipo().toString())) && (estado.equals(equipos.get(i).getEstado()))){
                        equiposPorFiltro.add(equipos.get(i));
                    }
                }
                return equiposPorFiltro;
            }
        }
    }    
    
    
    
}
