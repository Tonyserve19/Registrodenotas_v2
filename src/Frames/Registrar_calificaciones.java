/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Clases.Conectar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author USUARIO
 */
public class Registrar_calificaciones extends javax.swing.JFrame {
    
    DefaultTableModel modelo= new DefaultTableModel();
    int idalumno=0;
    
    String nombre_alumno="";
    String nombre_curso="";
    int cantcalif=0;
    
    /**
     * Creates new form Registrar_calificaciones
     */
    public Registrar_calificaciones() {
        initComponents();
        //cerrar();
        
        //TextPrompt tipo=new TextPrompt("Ingresa Tipo",txttipo);
        TextPrompt calificacion=new TextPrompt("Ingresa Calificacion",txtcalificacion);
        this.setLocationRelativeTo(null);
        
        txtalumno.setEditable(false);
        cmbcurso.setEnabled(false);
        cargarcombocurso(cmbcurso);
        idalumno=Gestionar_alumnos.idalumno;
        //cantcalif=Informacion_alumnos.cantcalif;
        
        //txtpt.setText(String.valueOf(cantcalif));
        
        
        try {
            
            PreparedStatement ps=cn.prepareStatement("SELECT nombre FROM alumnos where id_alumno='"+idalumno+"'");
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                
                nombre_alumno=rs.getString("nombre");
                
                
            }
            
            
        } catch (SQLException e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null,"Error al consultar nombre del alumno");
        
        }
        
        txtalumno.setText(nombre_alumno);
        ////////////////////////////////////////////////////////////////
        try {
            
            PreparedStatement ps=cn.prepareStatement("SELECT id_curso_asignado FROM alumnos where id_alumno='"+idalumno+"'");
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                
                nombre_curso=rs.getString("id_curso_asignado");
                
                
            }
            
            
        } catch (SQLException e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null,"Error al consultar el curso del alumno");
        
        }
        
        cmbcurso.setSelectedItem(nombre_curso);
        
        try {
            
            PreparedStatement pst=cn.prepareStatement(
            "SELECT id_nota,tipo,calificacion FROM notas WHERE id_alumno_nota='"+idalumno+"'");
            ResultSet rs=pst.executeQuery();
            
            tabla_calificaciones=new JTable(modelo);
            jScrollPane2.setViewportView(tabla_calificaciones);
            
            modelo.addColumn("ID nota");
            modelo.addColumn("Tipo");
            modelo.addColumn("Calificacion");
            
            while(rs.next()){
                
                Object[] fila=new Object[3];
                for (int i = 0; i < 3; i++) {
                    fila[i]=rs.getObject(i+1);
                    
                }
                modelo.addRow(fila);
                ////////////////////////////////////////////////////
      
            }
            
        } catch (SQLException e) {
            System.err.println("Error en el llenado de la calificacion");
        }
        cantcalif=modelo.getRowCount();
        //txtp.setText(String.valueOf(cantcalif));
    }
    /////////////////////////////////////////////////////////
    
    public void cargarcombocurso(JComboBox combodelcurso){
        try {
            String sql="SELECT nombre_curso FROM curso";
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            
            while(rs.next()){
                
                combodelcurso.addItem(rs.getString("nombre_curso"));
            }
        } catch (SQLException e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null,"Error al cargar cursos");
        
        }
    }
    
    
    ///////////////////////////////////////////////////////////
public void cerrar(){
        
        try {
                this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                addWindowListener(new WindowAdapter(){
                    
                    public void windowClosing(WindowEvent e){
                        
                        confirmarsalida();
                        
                    }
                    
                });
            
            
        } catch (Exception e) {
        }
        
    }
    public void confirmarsalida(){
        int valor=JOptionPane.showConfirmDialog
        (this,"¿Desea cerrar la aplicacion?","Advertencia",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(valor==JOptionPane.YES_OPTION){
            
            JOptionPane.showMessageDialog(null,"Hasta Pronto","",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        
    }
    
    public boolean TieneTipoValido(String tipo){
        return tipo != "Seleccione Tipo";
    }
    public boolean TieneCalificacion(){
        return txtcalificacion.getText().isEmpty();
    }
    public boolean TieneCalificacionValida(){
        int calif = Integer.parseInt(txtcalificacion.getText());
        return calif>=0 && calif <=20;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtalumno = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbcurso = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtcalificacion = new javax.swing.JTextField();
        btnregistrar = new javax.swing.JButton();
        btnvolver = new javax.swing.JButton();
        cbxtipo = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_calificaciones = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Registrar Calificaciones");

        jLabel2.setText("Alumno:");

        jLabel3.setText("Tipo:");

        jLabel4.setText("Curso:");

        cmbcurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione curso" }));

        jLabel5.setText("Calificacion:");

        btnregistrar.setText("Registrar");
        btnregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrarActionPerformed(evt);
            }
        });

        btnvolver.setText("Volver");
        btnvolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvolverActionPerformed(evt);
            }
        });

        cbxtipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Tipo", "Parcial 1", "Parcial 2", "Parcial Final" }));
        cbxtipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxtipoActionPerformed(evt);
            }
        });

        tabla_calificaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabla_calificaciones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnregistrar)
                                .addGap(18, 18, 18)
                                .addComponent(btnvolver))
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(cmbcurso, 0, 198, Short.MAX_VALUE)
                            .addComponent(txtcalificacion)
                            .addComponent(txtalumno)
                            .addComponent(cbxtipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(jLabel1)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtalumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3)
                        .addGap(21, 21, 21)
                        .addComponent(cbxtipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbcurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtcalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnregistrar)
                    .addComponent(btnvolver))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrarActionPerformed
        
        if(cantcalif<3){
            String curso=cmbcurso.getSelectedItem().toString();
        
        try {
                if((TieneTipoValido(cbxtipo.getSelectedItem().toString()) == false)){
                    
                    JOptionPane.showMessageDialog(null,"Escoja un tipo de nota");
                }
                
                else if(TieneCalificacion() == true){
                    
                    JOptionPane.showMessageDialog(null,"Ingrese la calificacion");
                }
                ////////////////////////////////////////////////////////////
                
                else {
                    if(TieneCalificacionValida() == true){
                    PreparedStatement ps=cn.prepareStatement("INSERT INTO notas(id_alumno_nota,id_curso_nota,tipo,calificacion) VALUES(?,?,?,?)");
                     
                     ps.setInt(1, idalumno);
                     ps.setString(2, curso);
                     ps.setString(3, cbxtipo.getSelectedItem().toString());                    
                     ps.setString(4, txtcalificacion.getText());   
                     
                     ps.executeUpdate();
                     
                     JOptionPane.showMessageDialog(null,"Datos Guardados");
                    }else{
                         
                     JOptionPane.showMessageDialog(null,"Ingrese una nota correcta");
                     }
                }
                        
        } catch (SQLException e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null,"Error al Guardar Datos");
        
        }
        }else{
            JOptionPane.showMessageDialog(null,"Ya se agregaron todas las notas, actualice o elimine una.");
        }
            
        
    }//GEN-LAST:event_btnregistrarActionPerformed

    private void btnvolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvolverActionPerformed
        Informacion_alumnos info_alumnos=new Informacion_alumnos();
        info_alumnos.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnvolverActionPerformed

    private void cbxtipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxtipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxtipoActionPerformed

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
            java.util.logging.Logger.getLogger(Registrar_calificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrar_calificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrar_calificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrar_calificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registrar_calificaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnregistrar;
    private javax.swing.JButton btnvolver;
    private javax.swing.JComboBox<String> cbxtipo;
    private javax.swing.JComboBox<String> cmbcurso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla_calificaciones;
    private javax.swing.JTextField txtalumno;
    private javax.swing.JTextField txtcalificacion;
    // End of variables declaration//GEN-END:variables
Conectar con=new Conectar();
Connection cn=con.conexion();

}
