/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.JPanel;
import modelo.Administrador;
import modelo.Usuario;

/**
 *
 * @author Usuario
 */
public class vistaLogin extends javax.swing.JFrame {

    Usuario usuario;
    Administrador administrador;
    JPanel panels[] = new JPanel[14];
    
    public vistaLogin() {
        LoginPanel loginPanel = new LoginPanel(this); 
        loginPanel.setBounds(0, 0, 1200, 600);
        agregarPanel(0,loginPanel); // Panel 0 - Panel logueo
        loginPanel.setVisible(true);
        
        SesionAdminAdministrativo sesionAdminPanel = new SesionAdminAdministrativo(this);  
        sesionAdminPanel.setBounds(0, 0, 1200, 600);
        agregarPanel(1,sesionAdminPanel); // Panel 1 - Panel control Administracion
        sesionAdminPanel.setVisible(false);
        
        IngresoPanel ingresoPanel = new IngresoPanel(this);
        ingresoPanel.setBounds(0, 0, 1200, 600);
        agregarPanel(2,ingresoPanel); // Panel 2 - Ingreso usuario general
        ingresoPanel.setVisible(false);
        
        IngresosUsuarioPanel ingresosUsuarioPanel = new IngresosUsuarioPanel(this); 
        ingresosUsuarioPanel.setBounds(0, 0, 1200, 600);
        agregarPanel(3,ingresosUsuarioPanel); // Panel 3 - Consulta Ingresos por usuario
        ingresosUsuarioPanel.setVisible(false);
        
        IngresosGeneralPanel ingresosGeneralPanel = new IngresosGeneralPanel(this);
        ingresosGeneralPanel.setBounds(0, 0, 1200, 600);
        agregarPanel(4,ingresosGeneralPanel); // Panel 4 - Consulta todos los ingresos
        ingresosGeneralPanel.setVisible(false);
        
        RegModUsurPanel regModUsurPanel = new RegModUsurPanel(this);
        regModUsurPanel.setBounds(0, 0, 1200, 600);
        agregarPanel(5,regModUsurPanel); // Panel 5 - Registrar y Modificar Usuarios
        regModUsurPanel.setVisible(false);
        
        ConsultaUsuariosPanel consultaUsuariosPanel = new ConsultaUsuariosPanel(this);
        consultaUsuariosPanel.setBounds(0, 0, 1200, 600);
        agregarPanel(6,consultaUsuariosPanel); // Panel 6 - Consultar todos los usuarios
        consultaUsuariosPanel.setVisible(false);
        
        RegModEquipoPanel regModEquipoPanel = new RegModEquipoPanel(this);
        regModEquipoPanel.setBounds(0, 0, 1200, 600);
        agregarPanel(7,regModEquipoPanel); // Panel 7 - Registro y modificacion de equipos
        regModEquipoPanel.setVisible(false);
        
        consultarEquiposPanel equiposPanel = new consultarEquiposPanel(this);
        equiposPanel.setBounds(0, 0, 1200, 600);
        agregarPanel(8,equiposPanel); // Panel 8 - Consulta de equipos
        equiposPanel.setVisible(false);      
        
        ConsultarPrestamosUsur prestamosUsuario = new ConsultarPrestamosUsur(this);
        prestamosUsuario.setBounds(0, 0, 1200, 600);
        agregarPanel(9,prestamosUsuario); // Panel 9 - Consulta de prestamos de usuario
        prestamosUsuario.setVisible(false);    
        
        RegistroPrestamoPanel prestamoPanel = new RegistroPrestamoPanel(this);
        prestamoPanel.setBounds(0, 0, 1200, 600);
        agregarPanel(10,prestamoPanel); // Panel 10 - Registro de prestamos de usuario
        prestamoPanel.setVisible(false);   
        
        RegistroDevolucionPanel devolucionPanel = new RegistroDevolucionPanel(this);
        devolucionPanel.setBounds(0, 0, 1200, 600);
        agregarPanel(11,devolucionPanel); // Panel 11 - Registro de Devolucion de usuario
        devolucionPanel.setVisible(false);  
        
        ConsultaPrestamos consultaPrestamos = new ConsultaPrestamos(this);
        consultaPrestamos.setBounds(0, 0, 1200, 600);
        agregarPanel(12,consultaPrestamos); // Panel 12 - Consulta de prestamos
        consultaPrestamos.setVisible(false);
        
        
        VerEquiposPanel verEquipos = new VerEquiposPanel(this);
        verEquipos.setBounds(0, 0, 1200, 600);
        agregarPanel(13,verEquipos); // Panel 12 - Consulta de prestamos
        verEquipos.setVisible(false);
        
        this.add(loginPanel);
        this.add(sesionAdminPanel);
        this.add(ingresoPanel);
        this.add(ingresosUsuarioPanel);
        this.add(ingresosGeneralPanel);
        this.add(regModUsurPanel);
        this.add(consultaUsuariosPanel);
        this.add(regModEquipoPanel);
        this.add(equiposPanel);
        this.add(prestamosUsuario);
        this.add(prestamoPanel);
        this.add(devolucionPanel);
        this.add(consultaPrestamos);
        this.add(verEquipos);
        initComponents();
      
    }
    
    //En este metodo realizo el intercambio de paneles dejanso visible el panel de interes
    // indicado por el entero n, y ocultando los demas paneles que no son de interes
    public void swap(int n){
        for(int i=0; i < panels.length; i++){
            panels[i].setVisible(false);
        }
        panels[n].setVisible(true);
    } 
    
    // Agrego los paneles que he creado a un arreglo de paneles, con esto es posible
    // hacer de manera facil el intercambio entre paneles, con el metodo swap 
    // se realiza el intercambio de paneles depues de haberlo agrado al arreglo
    public void agregarPanel(int n, JPanel panel){
        panels[n] = panel;
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    public Administrador getAdministrador(){
        return administrador;
    }
    
    public void setAdministrador(Administrador administrador){
        this.administrador = administrador;
        SesionAdminAdministrativo auxSesion = (SesionAdminAdministrativo) panels[1]; // recoge el panel 1 de sesion
        auxSesion.setAdministrador(administrador);
        auxSesion.setUltimoAccesoSistemaAdmin(administrador);
        auxSesion.setOcultarBotones(administrador);
    }
    
    public String getAsignaturaSesion(){
        String Asignatura = "";
        SesionAdminAdministrativo auxSesion = (SesionAdminAdministrativo) panels[1]; // recoge el panel 1 de sesion
        Asignatura = auxSesion.getAsignatura();
        return Asignatura;
    }
    
    public void cerrarSesion(){
        LoginPanel auxSesion = (LoginPanel) panels[0]; // recoge el panel 0 de sesion
        auxSesion.borrarTodo();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 602, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(vistaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vistaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
