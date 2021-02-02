/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logica.UsuarioLogica;
import modelo.Usuario;

/**
 *
 * @author Usuario
 */
public class RegModUsurPanel extends javax.swing.JPanel {

    vistaLogin frame;
    Usuario usuario;
    Usuario usuarioCrear;
    UsuarioLogica usuarioLogica;
    public RegModUsurPanel(vistaLogin frame) {
        this.frame = frame;
        usuarioLogica = new UsuarioLogica();
        usuario = new Usuario();
        usuarioCrear = new Usuario();
        initComponents();
        setBackground(Color.white);
        RolComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MATRICULADO",    
                                                                                 "PENDIENTE MATRICULA", 
                                                                                 "DOCENTE", "ADMINISTRATIVO" }));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonLogo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        botonVolver = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        campoUsuarioID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        campoNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        campoRol = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        RolComboBox = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        campoSemestre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        campoProgramaAcad = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        campoEmail = new javax.swing.JTextField();
        botonConsultar = new javax.swing.JButton();
        botonRegistar = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        botonBorrarCampos = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        botonLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo-universidad-del-valle-2.png"))); // NOI18N
        botonLogo.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 0));
        jLabel1.setText("GIPLE V2.0 - LABORATORIO DE ELECTRÓNICA UNIVERSIDAD DEL VALLE SEDE TULUA");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 0));
        jLabel2.setText("REGISTRAR Y MODIFICAR USUARIO");

        botonVolver.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        botonVolver.setForeground(new java.awt.Color(255, 51, 0));
        botonVolver.setText("Volver");
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("ID USUARIO: ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("NOMBRE: ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("ROL: ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 0));
        jLabel6.setText("CAMBIAR ROL A:");

        RolComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("SEMESTRE: ");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 0));
        jLabel8.setText("PROGRAMA ACADEMICO:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 51, 0));
        jLabel9.setText("Para consultar un usuario ingrese el ID Usuario:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 51, 0));
        jLabel10.setText("E-MAIL:");

        botonConsultar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        botonConsultar.setText("Consultar");
        botonConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConsultarActionPerformed(evt);
            }
        });

        botonRegistar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        botonRegistar.setText("Registrar");
        botonRegistar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistarActionPerformed(evt);
            }
        });

        botonModificar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        botonModificar.setText("Modificar");
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        botonBorrarCampos.setText("Borrar Campos");
        botonBorrarCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarCamposActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("Seleccione un rol para registrar usuario");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 51, 0));
        jLabel12.setText("Ingrese numero del programa academico");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(23, 23, 23)
                                                .addComponent(RolComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(botonBorrarCampos)
                                                    .addComponent(campoProgramaAcad, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel11)))))
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoUsuarioID, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campoSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campoRol, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(botonConsultar)
                        .addGap(28, 28, 28)
                        .addComponent(botonRegistar)
                        .addGap(28, 28, 28)
                        .addComponent(botonModificar)
                        .addGap(71, 71, 71)
                        .addComponent(botonVolver)))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addComponent(botonLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoUsuarioID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RolComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(campoProgramaAcad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botonBorrarCampos)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonVolver)
                    .addComponent(jLabel9)
                    .addComponent(botonConsultar)
                    .addComponent(botonRegistar)
                    .addComponent(botonModificar))
                .addGap(122, 122, 122))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        frame.swap(1); // hace el llamado al panel de administracion
    }//GEN-LAST:event_botonVolverActionPerformed

    private void botonConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConsultarActionPerformed
        String codigoUsuario = campoUsuarioID.getText();
        Usuario usuario = new Usuario();
        UsuarioLogica usuarioLogica = new UsuarioLogica();
        
        if(campoUsuarioID.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "El campo ID Usuario no debe estar vacio");
        }
        else{
            try{
                usuario = usuarioLogica.buscarUsuario(Integer.valueOf(codigoUsuario));
                campoNombre.setText(usuario.getNombreUsuario());
                campoRol.setText(usuario.getRol());
                campoSemestre.setText(String.valueOf(usuario.getSemestre()));
                campoProgramaAcad.setText(String.valueOf(usuario.getProgramaAcademico()));
                campoEmail.setText(usuario.getEmail());
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, "No se encontro el usuario");
            }
        }
    }//GEN-LAST:event_botonConsultarActionPerformed

    private void botonBorrarCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarCamposActionPerformed
        // TODO add your handling code here:
        campoNombre.setText("");
        campoRol.setText("");
        campoSemestre.setText("");
        campoProgramaAcad.setText("");
        campoEmail.setText("");
        campoUsuarioID.setText("");
    }//GEN-LAST:event_botonBorrarCamposActionPerformed

    private void botonRegistarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistarActionPerformed
        usuario = new Usuario();
        try{
            usuario.setUsuarioID(Integer.parseInt(campoUsuarioID.getText()));
            try{
                usuario.setSemestre(Integer.parseInt(campoSemestre.getText()));
                try{
                    usuario.setProgramaAcademico(Integer.parseInt(campoProgramaAcad.getText()));
                    usuario.setNombreUsuario(campoNombre.getText());
                    usuario.setRol(RolComboBox.getSelectedItem()+"");
                    usuario.setEmail(campoEmail.getText());
                    usuarioLogica.RegistrarUsuario(usuario);
                    JOptionPane.showMessageDialog(this, "Usuario registrado");
                }
                catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(this, "Programa académico incorrecto");
                }
                catch(NullPointerException e){
                    JOptionPane.showMessageDialog(this, "No ha ingresado programa académico");
                }
                catch(java.lang.RuntimeException e){
                    JOptionPane.showMessageDialog(this, "Ya existe este usuario");
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this, "Semestre incorrecto");
            }
            catch(NullPointerException e){
                JOptionPane.showMessageDialog(this, "No ha ingresado el semestre");
            }

        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Id incorrecto");
        }
        catch(NullPointerException e){
            JOptionPane.showMessageDialog(this, "No ha ingresado un id");
        }
    }//GEN-LAST:event_botonRegistarActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        // TODO add your handling code here:
       try{
            usuario = usuarioLogica.buscarUsuario(Integer.parseInt(campoUsuarioID.getText()));
            try{
                usuario.setSemestre(Integer.parseInt(campoSemestre.getText()));
                try{
                    usuario.setProgramaAcademico(Integer.parseInt(campoProgramaAcad.getText()));
                    usuario.setNombreUsuario(campoNombre.getText());
                    usuario.setRol(RolComboBox.getSelectedItem()+"");
                    usuario.setEmail(campoEmail.getText());
                    usuarioLogica.modificar(usuario);
                    JOptionPane.showMessageDialog(this, "Usuario modificado");
                }
                catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(this, "Programa académico incorrecto");
                }
                catch(NullPointerException e){
                    JOptionPane.showMessageDialog(this, "No ha ingresado programa académico");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }

            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this, "Semestre incorrecto");
            }
            catch(NullPointerException e){
                JOptionPane.showMessageDialog(this, "No ha ingresado el semestre");
            }

        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Id incorrecto");
        }
        catch(NullPointerException e){
            JOptionPane.showMessageDialog(this, "No ha ingresado un id");
        }        
    }//GEN-LAST:event_botonModificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox RolComboBox;
    private javax.swing.JButton botonBorrarCampos;
    private javax.swing.JButton botonConsultar;
    private javax.swing.JButton botonLogo;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton botonRegistar;
    private javax.swing.JButton botonVolver;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JTextField campoProgramaAcad;
    private javax.swing.JTextField campoRol;
    private javax.swing.JTextField campoSemestre;
    private javax.swing.JTextField campoUsuarioID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
