/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Clases.Conectar;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.DocumentException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author USUARIO
 */
public class Informacion_alumnos extends javax.swing.JFrame {
    
    DefaultTableModel modelo= new DefaultTableModel();
    int idalumno=0;
    public static int cantcalif=0;
    public static int idcalificacion=0;
    
    /**
     * Creates new form Informacion_alumnos
     */
    public Informacion_alumnos() {
        initComponents();
        this.setLocationRelativeTo(null);
        //cerrar();
        txtnombre.setEditable(false);
        txtapellido.setEditable(false);
        txttelefono.setEditable(false);
        txtcalificacion.setEditable(false);
        txtestatus.setEditable(false);
        cmbgrado.setEnabled(false);
        
        idalumno=Gestionar_alumnos.idalumno;
        
        try {
                Connection cn=con.conexion();
                PreparedStatement ps=cn.prepareStatement("SELECT * FROM alumnos WHERE id_alumno='"+idalumno+"'");
                
                ResultSet rs=ps.executeQuery();
                
                if(rs.next()){
                    
                    setTitle("Informacion del alumno"+rs.getString("nombre"));
                    lblinfo_alumno.setText("Informacion del alumno: "+rs.getString("nombre"));
                    
                    txtnombre.setText(rs.getString("nombre"));
                    txtapellido.setText(rs.getString("apellido"));
                    cmbgrado.setSelectedItem(rs.getString("grado"));
                    txttelefono.setText(rs.getString("telefono"));
                }
            
            
        } catch (SQLException e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null,"Error al cargar alumno");
        }
        /////////////////////////////////////////////////////////////////
        
        try {
            
            PreparedStatement pst=cn.prepareStatement(
            "SELECT id_nota,tipo,calificacion FROM notas WHERE id_alumno_nota='"+idalumno+"'");
            ResultSet rs=pst.executeQuery();
            
            tabla_calificaciones=new JTable(modelo);
            jScrollPane1.setViewportView(tabla_calificaciones);
            
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
                
                int filax=0;
                int total=0;
                for (int i = 0; i < tabla_calificaciones.getRowCount(); i++) {
                    filax=Integer.parseInt(tabla_calificaciones.getValueAt(i,2).toString());
                    total+=filax;
                    
                    txtcalificacion.setText(""+(total/tabla_calificaciones.getRowCount()));
                    
                }
                int califa=Integer.parseInt(txtcalificacion.getText());
                
                if(califa >= 11){
                    txtestatus.setText(String.valueOf("Aprobado"));
                    txtcalificacion.setBackground(Color.green);
                }
                else
                {
                    txtestatus.setText(String.valueOf("Desaprobado"));
                    txtcalificacion.setBackground(Color.red);
                }
                
                
            }
            
        } catch (SQLException e) {
            System.err.println("Error en el llenado de la calificacion");
        }
        
        
        
        tabla_calificaciones.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                
                int fila_point=tabla_calificaciones.rowAtPoint(e.getPoint());
                int columna_point=0;
                
                if(fila_point>-1){
                    
                    idcalificacion=(int)modelo.getValueAt(fila_point,columna_point);
                    cantcalif=modelo.getColumnCount();
                    Informacion_calificaciones informacion_calificaciones=new Informacion_calificaciones();
                    informacion_calificaciones.setVisible(true);
                    dispose();
                    
                }
                
            }
        });
    
        
    }
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblinfo_alumno = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtapellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbgrado = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_calificaciones = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtestatus = new javax.swing.JTextField();
        btnimprimir = new javax.swing.JButton();
        btnvolver = new javax.swing.JButton();
        btnregistrar_alumnos = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtcalificacion = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lblinfo_alumno.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        lblinfo_alumno.setText("Informacion Alumno");

        jLabel2.setText("Nombre:");

        txtnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreActionPerformed(evt);
            }
        });

        jLabel3.setText("Apellidos:");

        jLabel4.setText("Ciclo:");

        cmbgrado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        jLabel5.setText("Telefono:");

        tabla_calificaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla_calificaciones);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Estatus:");

        txtestatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtestatusActionPerformed(evt);
            }
        });

        btnimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/2620507_employee_job_print_seeker_unemployee_icon.png"))); // NOI18N
        btnimprimir.setText("Imprimir");
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        btnvolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/8200192_previous_back_arrow_left_arrows_icon.png"))); // NOI18N
        btnvolver.setText("Volver");
        btnvolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvolverActionPerformed(evt);
            }
        });

        btnregistrar_alumnos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/6646630_game_power_powerup_star_up_icon.png"))); // NOI18N
        btnregistrar_alumnos.setText("Registrar Calificacion");
        btnregistrar_alumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrar_alumnosActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Promedio:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/home_circle_outline_icon_139029.png"))); // NOI18N
        jButton1.setText("INICIO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblinfo_alumno)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtestatus, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(233, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(txtnombre)
                        .addComponent(txtapellido)
                        .addComponent(cmbgrado, 0, 126, Short.MAX_VALUE)
                        .addComponent(txttelefono))
                    .addComponent(btnvolver))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnregistrar_alumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtcalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblinfo_alumno))
                    .addComponent(jButton1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtestatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnregistrar_alumnos)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(txtcalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbgrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnvolver)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreActionPerformed

    private void btnvolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvolverActionPerformed
        
        Gestionar_alumnos gestionar_alumnos=new Gestionar_alumnos();
        gestionar_alumnos.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_btnvolverActionPerformed

    private void btnregistrar_alumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrar_alumnosActionPerformed
        
        Registrar_calificaciones registrar_calificaciones=new Registrar_calificaciones();
        registrar_calificaciones.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_btnregistrar_alumnosActionPerformed

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
        
        Document documento=new Document();
        
        Calendar cal=Calendar.getInstance();
        Date fecha=new Date(cal.getTimeInMillis());
        SimpleDateFormat formato=new SimpleDateFormat("dd/m/yyyy");
        String verfecha=formato.format(fecha);
        
        try {
            
            String ruta=System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta+"/desktop/"+txtnombre.getText()+".pdf"));
            
            Paragraph parrafo=new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont("Arial",20,BaseColor.BLACK));
            parrafo.add("Informacion del alumno \n \n");
            
            Paragraph poner_fecha=new Paragraph();
            poner_fecha.setAlignment(Paragraph.ALIGN_CENTER);
            poner_fecha.add("Fecha: "+verfecha+" \n \n");
            
            documento.open();
            documento.add(parrafo);
            documento.add(poner_fecha);
            
            PdfPTable tablaalumno=new PdfPTable(4);
            
            tablaalumno.addCell("Nombre");
            tablaalumno.addCell("Apellido");
            tablaalumno.addCell("Grado");
            tablaalumno.addCell("Materia");
            
            try {
                PreparedStatement ps=cn.prepareStatement("SELECT alumnos.nombre, alumnos.apellido, alumnos.grado, alumnos.id_curso_asignado "
                                                         + "FROM alumnos WHERE id_alumno='"+idalumno+"'");
                ResultSet rs=ps.executeQuery();
                
                if(rs.next()){
                    do {
                        tablaalumno.addCell(rs.getString("alumnos.nombre"));
                        tablaalumno.addCell(rs.getString("alumnos.apellido"));
                        tablaalumno.addCell(rs.getString("alumnos.grado"));
                        tablaalumno.addCell(rs.getString("alumnos.id_curso_asignado"));
                    } while (rs.next());
                            {
                    documento.add(tablaalumno);
                }
                    
                }
                
                Paragraph parrafo2=new Paragraph();
                parrafo2.setAlignment(Paragraph.ALIGN_CENTER);
                parrafo2.setFont(FontFactory.getFont("Arial",20,Font.BOLD, BaseColor.BLACK));
                parrafo2.add("\n \n Notas Registradas \n \n");
                
                documento.add(parrafo2);
                
                PdfPTable tablatareas=new PdfPTable(2);
            
                tablatareas.addCell("Tipo");
                tablatareas.addCell("Calificacion");
               
                try {
                    
                    Connection cn2=con.conexion();
                    PreparedStatement ps2=cn2.prepareStatement("SELECT notas.tipo, notas.calificacion FROM notas WHERE id_alumno_nota='"+idalumno+"'");
                    ResultSet rs2=ps2.executeQuery();
                    
                    if(rs2.next()){
                        do {
                            
                            tablatareas.addCell(rs2.getString("notas.tipo"));
                            tablatareas.addCell(rs2.getString("notas.calificacion"));
                            
                        } while (rs2.next());{
                        
                                documento.add(tablatareas);
                    }
                
                    }
                
                } catch (SQLException e) {
                    System.err.println("Error al carga de notas"+e);
                }
                ///////////////////////////////////////////////////////////
                
                Paragraph parrafo3=new Paragraph();
                parrafo3.setAlignment(Paragraph.ALIGN_CENTER);
                parrafo3.setFont(FontFactory.getFont("Arial",17,Font.BOLD, BaseColor.BLACK));
                parrafo3.add("\n \n Promedio: \n \n");
                
                documento.add(parrafo3);
                
                PdfPTable tablapromedio=new PdfPTable(2);
            
                tablapromedio.addCell("(P1 + P2 + PF)/3");
               
                try {
                    
                    Connection cn3=con.conexion();
                    PreparedStatement ps3=cn3.prepareStatement("SELECT AVG(notas.calificacion) as proms FROM notas WHERE id_alumno_nota='"+idalumno+"'");
                    ResultSet rs3=ps3.executeQuery();
                    
                    if(rs3.next()){
                        do {
                            
                            tablapromedio.addCell(rs3.getString("proms"));
                            
                        } while (rs3.next());{
                        
                                documento.add(tablapromedio);
                    }
                
                    }
                
                } catch (SQLException e) {
                    System.err.println("Error al carga de notas"+e);
                }
                
            } catch (Exception e) {
                System.err.println("Error al obtener datos del alumno"+e);
            }
            
            
            ////////////////////////////////////////////////////////////////
            
            documento.close();
            JOptionPane.showMessageDialog(null,"Documento Creado con exito");
            
        } catch (DocumentException | IOException e) {
            System.err.println("Error en el PDF o en la ruta"+e);
            JOptionPane.showMessageDialog(null,"Error al generar PDF");
        }
        
    }//GEN-LAST:event_btnimprimirActionPerformed

    private void txtestatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtestatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtestatusActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Principal principal = new Principal();
        principal.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Informacion_alumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Informacion_alumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Informacion_alumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Informacion_alumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Informacion_alumnos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnimprimir;
    private javax.swing.JButton btnregistrar_alumnos;
    private javax.swing.JButton btnvolver;
    private javax.swing.JComboBox<String> cmbgrado;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblinfo_alumno;
    private javax.swing.JTable tabla_calificaciones;
    private javax.swing.JTextField txtapellido;
    private javax.swing.JTextField txtcalificacion;
    private javax.swing.JTextField txtestatus;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
Conectar con=new Conectar();
    Connection cn=con.conexion();

}
