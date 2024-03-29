/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.EquipoLogica;
import logica.PrestamoLogica;
import modelo.Equipo;
import modelo.Prestamo;

/**
 *
 * @author Usuario
 */
public class RegModEquipoPanel extends javax.swing.JPanel {

    vistaLogin frame;
    EquipoLogica equipoLogica;
    DefaultTableModel modelo;
    private Prestamo prestamo;
    private List<Prestamo> listaPrestamos;
    private PrestamoLogica prestamoLogica;   
   
    public RegModEquipoPanel(vistaLogin frame) {
        this.frame = frame;
        equipoLogica = new EquipoLogica();

        equipoLogica = new EquipoLogica();
        listaPrestamos = new ArrayList<Prestamo>();
        prestamoLogica = new PrestamoLogica(); 
        
        initComponents();
        setBackground(Color.white);
        estadoComboBox.removeAllItems();
        estadoComboBox.addItem("DISPONIBLE");
        estadoComboBox.addItem("OCUPADO");
        estadoComboBox.addItem("EN MANTENIMIENTO");
        estadoComboBox.addItem("DADO DE BAJA");
        
        tipoComboBox.removeAllItems();
        tipoComboBox.addItem("A");
        tipoComboBox.addItem("B");
        tipoComboBox.addItem("C");
    }
    
    public void llenarTablaPrestamos(List<Prestamo> listPrestamos){
        
        List<Prestamo> listaPres;
        listaPres = listPrestamos;
        setTabla(listaPres);
    }  
    
public void setTabla(List<Prestamo> prestamos){
        modelo =  (DefaultTableModel) PrestamosTabla.getModel();
        
        int size = modelo.getRowCount();
        for(int i=0; i < size; i++){
            modelo.removeRow(0);
        }
        String aux[];
        for(int j=0;j< prestamos.size();j++){
            aux = new String[12];
            aux[0] = String.valueOf(prestamos.get(j).getPrestamoID());
            aux[1] = String.valueOf(prestamos.get(j).getUsuarioID().getUsuarioID());
            aux[2] = prestamos.get(j).getUsuarioID().getNombreUsuario();
            aux[3] = prestamos.get(j).getFechaEntrega().toString();
            aux[4] = prestamos.get(j).getHoraEntrega();
             try{
                aux[5] = prestamos.get(j).getFechaDevolucion().toString();
            }catch(Exception e){
                aux[5] = "No fecha";
            }
            aux[6] = prestamos.get(j).getHoraDevolucion();
            aux[7] = prestamos.get(j).getNombreMonitorAtendio();
            aux[8] = prestamos.get(j).getNombreMonitorDevolvio();
            aux[9] = prestamos.get(j).getEstadoPrestamo();
            aux[10] = prestamos.get(j).getObservaciones();
            aux[11] = prestamos.get(j).getAsignatura();
            modelo.addRow(aux);
        }
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
        jLabel3 = new javax.swing.JLabel();
        campoEquipoID = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoDescripcion = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tipoComboBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        campoHorasUso = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        campoFechaIngreso = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        campoRevision = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        PrestamosTabla = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        estadoComboBox = new javax.swing.JComboBox();
        campoEstado = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        campoTipo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        botonConsultar = new javax.swing.JButton();
        botonRegistar = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        botonVolver = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        botonBorrarCampos = new javax.swing.JButton();

        botonLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo-universidad-del-valle-2.png"))); // NOI18N
        botonLogo.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 0));
        jLabel1.setText("GIPLE V2.0 - LABORATORIO DE ELECTRÓNICA UNIVERSIDAD DEL VALLE SEDE TULUA");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 0));
        jLabel2.setText("Registro y Modificación de Equipos");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("ID Equipo:");

        campoDescripcion.setColumns(20);
        campoDescripcion.setRows(5);
        jScrollPane1.setViewportView(campoDescripcion);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 0));
        jLabel4.setText("Descripción:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 0));
        jLabel5.setText("TIPO:");

        tipoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 0));
        jLabel6.setText("HORAS DE USO:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("FECHA INGRESO:");

        campoFechaIngreso.setText("dd-mm-yyyy");
        campoFechaIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoFechaIngresoActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 0));
        jLabel8.setText("FECHA REVISION:");

        campoRevision.setText("dd-mm-yyyy");

        PrestamosTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Prestamo", "ID Usuario", "Nombre", "Fecha Entrega", "Hora Entrega", "Fecha Devol", "Hora Devol", "Atendio ", "Devolvio a", "Estado", "Obervaciones", "Asignatura"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(PrestamosTabla);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("ESTADO:");

        estadoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 51, 0));
        jLabel10.setText("Cambiar Estado a:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("Cambiar Tipo a:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("Para registrar seleccione un tipo");

        botonConsultar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonConsultar.setText("Consultar");
        botonConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConsultarActionPerformed(evt);
            }
        });

        botonRegistar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonRegistar.setText("Registrar");
        botonRegistar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistarActionPerformed(evt);
            }
        });

        botonModificar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonModificar.setText("Modificar");
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        botonVolver.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonVolver.setForeground(new java.awt.Color(255, 0, 0));
        botonVolver.setText("Volver");
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("Para registrar seleccione un estado");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("HISTORIAL DE PRESTAMOS DE ESTE EQUIPO");

        botonBorrarCampos.setText("Borrar Campos");
        botonBorrarCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarCamposActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(botonLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                    .addComponent(campoEquipoID))
                                .addGap(74, 74, 74)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(campoRevision, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(18, 18, 18)
                                                .addComponent(campoFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(44, 44, 44)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(campoHorasUso, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(46, 46, 46)
                                                .addComponent(botonBorrarCampos))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(campoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tipoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel12))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(7, 7, 7)
                                        .addComponent(campoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(estadoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel13))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(botonConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(botonRegistar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(botonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(17, 17, 17)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addComponent(botonLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(campoEquipoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(tipoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(campoFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(campoHorasUso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(campoRevision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonBorrarCampos))))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(estadoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonRegistar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addComponent(botonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void campoFechaIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoFechaIngresoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoFechaIngresoActionPerformed

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        frame.swap(1); // hace el llamado al panel de administracion
    }//GEN-LAST:event_botonVolverActionPerformed

    private void botonRegistarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistarActionPerformed
        
        try{
             Equipo equipoAux = new Equipo();
             int idEquipo = Integer.parseInt(campoEquipoID.getText());
             equipoAux.setEquipoID(idEquipo);// hago set del idEquipo
              
            String descripcion = campoDescripcion.getText();
            equipoAux.setDescripcion(descripcion);  // hago set de la descripcion 
            
            try{
               equipoAux.setTipoEquipo(tipoComboBox.getSelectedItem().toString().charAt(0)); // hago set del tipo
            }catch(Exception e){
               equipoAux.setTipoEquipo(null);
            }
            
            try{
                String fecha = campoFechaIngreso.getText();
                if(!(fecha.equals("dd-mm-yyyy"))){
                Integer anio = Integer.parseInt(fecha.charAt(6)+ "" + fecha.charAt(7) + "" +fecha.charAt(8)+ "" + fecha.charAt(9));
                Integer mes = Integer.parseInt(fecha.charAt(3)+ "" + fecha.charAt(4))-1;
                Integer dia = Integer.parseInt(fecha.charAt(0)+ "" + fecha.charAt(1));
                Calendar fecha1 = Calendar.getInstance();
                fecha1.set(anio, mes, dia);
                equipoAux.setFechaIngreso(fecha1.getTime()); // hago set de la fecha de ingreso
                }else{
                    equipoAux.setFechaIngreso(null);
                }
            }catch(Exception e){
                equipoAux.setFechaIngreso(null);
            }
          
            try{
                String fecha = campoRevision.getText();
                if(!(fecha.equals("dd-MM-yyyy"))){
                Integer anio = Integer.parseInt(fecha.charAt(6)+ "" + fecha.charAt(7) + "" +fecha.charAt(8)+ "" + fecha.charAt(9));
                Integer mes = Integer.parseInt(fecha.charAt(3)+ "" + fecha.charAt(4))-1;
                Integer dia = Integer.parseInt(fecha.charAt(0)+ "" + fecha.charAt(1));
                Calendar fecha2 = Calendar.getInstance();
                fecha2.set(anio, mes, dia);
                equipoAux.setFechaUltimaRevision(fecha2.getTime());
                }else{
                    equipoAux.setFechaUltimaRevision(null);
                }
            }catch(Exception e){
                equipoAux.setFechaUltimaRevision(null);
            }            
            
            try{
                String horas = campoHorasUso.getText();
                horas.charAt(0);
                equipoAux.setHorasUso(Integer.parseInt(horas+"")); // hago set de las horas de uso
            }catch(Exception e){
                equipoAux.setHorasUso(0);
            }
            
            equipoAux.setEstado(estadoComboBox.getSelectedItem().toString());

            try{
            equipoLogica.registrarEquipo(equipoAux);
            JOptionPane.showMessageDialog(this, "El equipo ha sido registrado satisfactoriamente");
            campoEquipoID.setText("");
            campoDescripcion.setText("");
            tipoComboBox.setSelectedItem("A");
            campoHorasUso.setText("");
            campoFechaIngreso.setText("dd-MM-yyyy");
            campoRevision.setText("dd-MM-yyyy");
            estadoComboBox.setSelectedItem("DISPONIBLE");
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, "El equipo ya existe");
            }
            
        }catch(Exception e){
             JOptionPane.showMessageDialog(this, "Debe ingresar un codigo de equipo");
        }    
    }//GEN-LAST:event_botonRegistarActionPerformed

    private void botonBorrarCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarCamposActionPerformed
        campoDescripcion.setText("");
        campoEquipoID.setText("");
        campoEstado.setText("");
        campoFechaIngreso.setText("dd-MM-yyyy");
        campoHorasUso.setText("");
        campoRevision.setText("dd-MM-yyyy");
        campoTipo.setText("");
        listaPrestamos.removeAll(listaPrestamos);
        llenarTablaPrestamos(listaPrestamos);
    }//GEN-LAST:event_botonBorrarCamposActionPerformed

    private void botonConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConsultarActionPerformed
        try{
            int codigoEquipo = Integer.parseInt(campoEquipoID.getText());
            Equipo equipo = equipoLogica.buscarEquipo(codigoEquipo);
            
            listaPrestamos.removeAll(listaPrestamos);
            listaPrestamos = prestamoLogica.listarPrestamosporEquipo(equipo);
            llenarTablaPrestamos(listaPrestamos);
            
            try{
                //equipo.getTipoEquipo().toString().charAt(0);
                //tipoComboBox.setSelectedItem(equipo.getTipoEquipo().toString());
                campoTipo.setText(equipo.getTipoEquipo().toString());
            }catch(Exception e){
                campoTipo.setText("NO IDENTIFICADO");
                //tipoComboBox.setSelectedItem("A");
            }
            campoHorasUso.setText(equipo.getHorasUso()+"");
            campoDescripcion.setText(equipo.getDescripcion());
            campoEstado.setText(equipo.getEstado());
            
            try{
                //equipo.getFechaIngreso().toString().charAt(0);
                SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd-MM-yyyy");
                String fecha = sdf.format(equipo.getFechaIngreso());
                campoFechaIngreso.setText(fecha);
            }catch(Exception e){
                campoFechaIngreso.setText("dd-MM-yyyy");
            }
            
            try{
                //equipo.getFechaRevision().toString().charAt(0);
                SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd-MM-yyyy");
                String fecha = sdf.format(equipo.getFechaUltimaRevision());
                campoRevision.setText(fecha);
            }catch(Exception e){
                campoRevision.setText("dd-MM-yyyy");
            }            
            
        }catch(Exception e){
            campoEquipoID.setText("");
            campoDescripcion.setText("");
            tipoComboBox.setSelectedItem("A");
            campoHorasUso.setText("");
            campoFechaIngreso.setText("dd-MM-yyyy");
            campoRevision.setText("dd-MM-yyyy");
            estadoComboBox.setSelectedItem("DISPONIBLE");
            //int numero = modelo.getRowCount();
            //for (int i = 0; i < numero; i++) {
            //    modelo.removeRow(0);
            //}
        }
    }//GEN-LAST:event_botonConsultarActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        // TODO add your handling code here:
            String estado = estadoComboBox.getSelectedItem().toString();
            String descripcion = campoDescripcion.getText();
            String tipo = tipoComboBox.getSelectedItem().toString();  
            int horasUso = Integer.valueOf(campoHorasUso.getText()+"");
            Equipo equipo = equipoLogica.buscarEquipo(Integer.parseInt(campoEquipoID.getText()));
            equipo.setEstado(estado);
            equipo.setTipoEquipo(tipo.charAt(0));
            equipo.setDescripcion(descripcion);
            equipo.setHorasUso(horasUso); 
            
            try{
                String fecha = campoRevision.getText();
                if(!(fecha.equals("dd-MM-yyyy"))){
                Integer anio = Integer.parseInt(fecha.charAt(6)+ "" + fecha.charAt(7) + "" +fecha.charAt(8)+ "" + fecha.charAt(9));
                Integer mes = Integer.parseInt(fecha.charAt(3)+ "" + fecha.charAt(4))-1;
                Integer dia = Integer.parseInt(fecha.charAt(0)+ "" + fecha.charAt(1));
                Calendar fecha3 = Calendar.getInstance();
                fecha3.set(anio, mes, dia);
                equipo.setFechaUltimaRevision(fecha3.getTime());
                }else{
                    equipo.setFechaUltimaRevision(null);
                }
            }catch(Exception e){
                equipo.setFechaUltimaRevision(null);
            }  
            
            try{
                String fecha = campoFechaIngreso.getText();
                if(!(fecha.equals("dd-MM-yyyy"))){
                Integer anio = Integer.parseInt(fecha.charAt(6)+ "" + fecha.charAt(7) + "" +fecha.charAt(8)+ "" + fecha.charAt(9));
                Integer mes = Integer.parseInt(fecha.charAt(3)+ "" + fecha.charAt(4))-1;
                Integer dia = Integer.parseInt(fecha.charAt(0)+ "" + fecha.charAt(1));
                Calendar fecha4 = Calendar.getInstance();
                fecha4.set(anio, mes, dia);
                equipo.setFechaUltimaRevision(fecha4.getTime());
                }else{
                    equipo.setFechaUltimaRevision(null);
                }
            }catch(Exception e){
                equipo.setFechaUltimaRevision(null);
            }             
            
        try {
            equipoLogica.modificarEquipo(equipo);
             JOptionPane.showMessageDialog(this, "Equipo modificado con exito");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Equipo no modificado");
        }
    }//GEN-LAST:event_botonModificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable PrestamosTabla;
    private javax.swing.JButton botonBorrarCampos;
    private javax.swing.JButton botonConsultar;
    private javax.swing.JButton botonLogo;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton botonRegistar;
    private javax.swing.JButton botonVolver;
    private javax.swing.JTextArea campoDescripcion;
    private javax.swing.JTextField campoEquipoID;
    private javax.swing.JTextField campoEstado;
    private javax.swing.JTextField campoFechaIngreso;
    private javax.swing.JTextField campoHorasUso;
    private javax.swing.JTextField campoRevision;
    private javax.swing.JTextField campoTipo;
    private javax.swing.JComboBox estadoComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox tipoComboBox;
    // End of variables declaration//GEN-END:variables
}
