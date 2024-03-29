/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import logica.IngresoLogica;
import modelo.Ingreso;
import modelo.Usuario;

/**
 *
 * @author Usuario
 */
public class IngresosGeneralPanel extends javax.swing.JPanel {

    /**
     * Creates new form IngresosGeneralPanel
     */
    vistaLogin frame;
    DefaultTableModel modelo;
    public IngresosGeneralPanel(vistaLogin frame) {
        this.frame = frame;
        initComponents();
        setBackground(Color.white);
        programaComboBox.removeAllItems();
        programaComboBox.addItem("TODOS");
        programaComboBox.addItem("2710");
        programaComboBox.addItem("2711");
        programaComboBox.addItem("2712");
        programaComboBox.addItem("2724");
        programaComboBox.addItem("3743");
        programaComboBox.addItem("3753");
        semestreComboBox.removeAllItems();
        semestreComboBox.addItem("TODOS");
        semestreComboBox.addItem("1");
        semestreComboBox.addItem("2");
        semestreComboBox.addItem("3");
        semestreComboBox.addItem("4");
        semestreComboBox.addItem("5");
        semestreComboBox.addItem("6");
        semestreComboBox.addItem("7");
        semestreComboBox.addItem("8");
        semestreComboBox.addItem("9");
        semestreComboBox.addItem("10");
    }
    
    public void llenarTablaIngresos(){
        IngresoLogica ingresoLogica = new IngresoLogica();
        List<Ingreso> listaIngresos = ingresoLogica.listarIngresosPorFiltro(programaComboBox.getSelectedItem().toString(), 
                                                                            semestreComboBox.getSelectedItem().toString());
        campoCantidadRegistros.setText(String.valueOf(listaIngresos.size()));
        campoCantidadRegistros.setEditable(false);
        setTabla(listaIngresos);
    }
    
    public void setTabla(List<Ingreso> ingresos){
        modelo =  (DefaultTableModel) consultaIngresoUsurTabla.getModel();
        int size = modelo.getRowCount();
        for(int i=0; i < size; i++){
            modelo.removeRow(0);
        }
        String aux[];
        for(int j=0;j< ingresos.size();j++){
            aux = new String[7];
            aux[0] = ingresos.get(j).getIngresoID().toString();//ID ingreso
            aux[1] = ingresos.get(j).getFecha().toString();// Fecha
            aux[2] = ingresos.get(j).getHoraIngreso();// Hora Ingreso
            aux[3] = ingresos.get(j).getHoraSalida();// Hora Salida
            aux[4] = ingresos.get(j).getUsuarioID().getNombreUsuario();
            aux[5] = ingresos.get(j).getUsuarioID().getUsuarioID().toString();
            aux[6] = String.valueOf(ingresos.get(j).getUsuarioID().getProgramaAcademico());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        consultaIngresoUsurTabla = new javax.swing.JTable();
        botonConsultarTodos = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        botonVolver = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        programaComboBox = new javax.swing.JComboBox<String>();
        jLabel5 = new javax.swing.JLabel();
        campoCantidadRegistros = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        semestreComboBox = new javax.swing.JComboBox();

        botonLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo-universidad-del-valle-2.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 0));
        jLabel1.setText("GIPLE V2.0 - LABORATORIO DE ELECTRÓNICA UNIVERSIDAD DEL VALLE SEDE TULUA");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 0));
        jLabel2.setText("CONSULTA INGRESOS GENERAL");

        consultaIngresoUsurTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Ingreso", "Fecha ", "Hora Ingreso", "Hora Salida", "Nombre", "ID usuario", "Programa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(consultaIngresoUsurTabla);

        botonConsultarTodos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonConsultarTodos.setText("Consultar");
        botonConsultarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConsultarTodosActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 0));
        jLabel3.setText("Consulta todos los ingresos del semestre");

        botonVolver.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonVolver.setForeground(new java.awt.Color(255, 0, 0));
        botonVolver.setText("Volver");
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Filtrar por programa");

        programaComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("Cantidad de Registros Encontrados");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("Filtrar por semestre");

        semestreComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addContainerGap(114, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(semestreComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(botonVolver, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                    .addComponent(botonConsultarTodos, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(programaComboBox, 0, 157, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(campoCantidadRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addComponent(botonLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonConsultarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(programaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCantidadRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(semestreComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        frame.swap(1); // hace llamado al panel de administracion
    }//GEN-LAST:event_botonVolverActionPerformed

    private void botonConsultarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConsultarTodosActionPerformed
        llenarTablaIngresos();
    }//GEN-LAST:event_botonConsultarTodosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonConsultarTodos;
    private javax.swing.JButton botonLogo;
    private javax.swing.JButton botonVolver;
    private javax.swing.JTextField campoCantidadRegistros;
    private javax.swing.JTable consultaIngresoUsurTabla;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> programaComboBox;
    private javax.swing.JComboBox semestreComboBox;
    // End of variables declaration//GEN-END:variables
}
