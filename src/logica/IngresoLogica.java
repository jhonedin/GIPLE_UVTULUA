/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import modelo.Ingreso;
import modelo.Usuario;
import persistencia.IngresoJpaController;

/**
 *
 * @author Usuario
 */
public class IngresoLogica {

    IngresoJpaController ingresoDAO;

    
    
    public IngresoLogica() {
        ingresoDAO = new IngresoJpaController();
    }
    
    public List<Ingreso> listarIngresos(){
        return ingresoDAO.findIngresoEntities();
    }
    
    public void registrarIngreso(Ingreso ingreso){
        ingresoDAO.create(ingreso);
    }
    
    public void registro_ingreso_salida(Usuario usuario) throws Exception{   
        
        if(usuario == null) throw new Exception("Este usuario no esta registrado");
        List<Ingreso> listaIngresoPorUsuario = listarIngresosporUsuario(usuario);
        if(listaIngresoPorUsuario.size() > 0){
            Ingreso IngresoUsuarioReciente = listaIngresoPorUsuario.get(listaIngresoPorUsuario.size()-1);
            if(IngresoUsuarioReciente.getHoraSalida()==null){
                Calendar calendario = new GregorianCalendar();
                int hora = calendario.get(Calendar.HOUR_OF_DAY);
                int minutos = calendario.get(Calendar.MINUTE);
                IngresoUsuarioReciente.setHoraSalida(hora+":"+minutos);
                ingresoDAO.edit(IngresoUsuarioReciente); // registra la hora de salida de ultimo ingreso reciente
            }
            else{
                Ingreso ingreso = new Ingreso();
                int ultimoID = ultimoID();
                ingreso.setIngresoID((long)(ultimoID+1));
                ingreso.setUsuarioID(usuario);
                Calendar calendario = new GregorianCalendar();
                int hora = calendario.get(Calendar.HOUR_OF_DAY);
                int minutos = calendario.get(Calendar.MINUTE);
                ingreso.setHoraIngreso(hora+":"+minutos);
                ingreso.setFecha(calendario.getTime());
                registrarIngreso(ingreso);
            }   
        }
        else{
            Ingreso ingreso = new Ingreso();
            int ultimoID = ultimoID();
            ingreso.setIngresoID((long)(ultimoID+1));
            ingreso.setUsuarioID(usuario);
            Calendar calendario = new GregorianCalendar();
            int hora = calendario.get(Calendar.HOUR_OF_DAY);
            int minutos = calendario.get(Calendar.MINUTE);
            ingreso.setHoraIngreso(hora+":"+minutos);
            ingreso.setFecha(calendario.getTime());
            registrarIngreso(ingreso);        
        }
    } 
    
    public List<Ingreso> listarIngresosporUsuario(Usuario usuario){
        List<Ingreso> ingresosPorUsuario = new ArrayList<Ingreso>();
        List<Ingreso> ingresos = listarIngresos();
        for(int i=0;i < ingresos.size();i++)
        {
            if(ingresos.get(i).getUsuarioID().equals(usuario))
            {
                ingresosPorUsuario.add(ingresos.get(i));
            }
        }
        return ingresosPorUsuario;
    }    
    
    public int ultimoID()
    {
        List<Ingreso> IngresoUsuarioReciente = listarIngresos();   
        int n = IngresoUsuarioReciente.size()-1;
        if(n > 0){
            return IngresoUsuarioReciente.get(n).getIngresoID().intValue(); // de long se convierte a int
        }
        else return 0;
    }    
    
    ////////////////////////////////////////////////////////
    public List<Ingreso> listarIngresosPorFiltro(String programa,String semestre){  // String prog, String rol
        List<Ingreso> ingresosPorFiltro = new ArrayList<Ingreso>();
        List<Ingreso> ingresos = listarIngresos();
        if(programa.equals("TODOS") && semestre.equals("TODOS")){
             ingresosPorFiltro = ingresos;
             return ingresosPorFiltro;
        }
        else{
            if(semestre.equals("TODOS") && !programa.equals("TODOS")){
                for(int i=0;i < ingresos.size();i++){
                     if(Integer.valueOf(programa) == ingresos.get(i).getUsuarioID().getProgramaAcademico()){
                        ingresosPorFiltro.add(ingresos.get(i));
                     }          
                }
                return ingresosPorFiltro;
             }
            if(programa.equals("TODOS") && !semestre.equals("TODOS")){             
                for(int i=0;i < ingresos.size();i++){
                    if(Integer.valueOf(semestre) == ingresos.get(i).getUsuarioID().getSemestre()){
                        ingresosPorFiltro.add(ingresos.get(i));
                    }
                }
                return ingresosPorFiltro;
            }
            else{
                for(int i=0;i < ingresos.size();i++){
                    if((Integer.valueOf(programa) == ingresos.get(i).getUsuarioID().getProgramaAcademico()) && (Integer.valueOf(semestre) == ingresos.get(i).getUsuarioID().getSemestre())){
                        ingresosPorFiltro.add(ingresos.get(i));
                    }
                }
                return ingresosPorFiltro;
            }
        }
    }
    

    
    
}
