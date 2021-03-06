/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Attendance;

import Classes.Attendance;
import Classes.Student;
import DatabaseOperation.AttendanceDB;
import DatabaseOperation.ClassDB;
import DatabaseOperation.StudentDB;
import OtherComponents.AttendanceTableModel;
import OtherComponents.RollUpStatus;
import OtherComponents.RollUpStatusCellEditor;
import OtherComponents.RollUpStatusCellRenderer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author mbiuu
 */
public class AttendanceFrame extends javax.swing.JFrame {
    /**
     * Creates new form AttendanceFrame
     */
    ArrayList<Student> listPerson = new ArrayList<>();
    private AttendanceTableModel tableModel;
    private Attendance atd;

    public AttendanceFrame(Attendance atd) {
        this.atd = atd;
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        if(atd.isMarked()){
            jLabel4.setText("Already Rolled Up");
        }
        StudentDB sdb = new StudentDB();       
        Classes.Class c = new Classes.Class();
        c.setClassID(atd.getClassID());
        jLabel3.setText(new ClassDB().getClassNameByID(atd.getClassID()));
        if(atd.isMarked() == true){
            ResultSet rs = sdb.getAllStudentInClassWithAttendance(c, atd.getAttendID());
            try {
                while(rs.next()){
                    Student minh = new Student();
                    String rollUpStatusText="";
                    minh.setFname(rs.getString("fname"));
                    minh.setLname(rs.getString("lname"));
                    minh.setRollNo(rs.getString("rollno"));            
                    switch(rs.getInt("attendstatus")){
                        case 1: 
                           rollUpStatusText = "P";
                           break;
                        case 2:
                           rollUpStatusText = "PA";
                           break;
                        case 0: 
                            rollUpStatusText = "A";
                            break;
                    }
                    minh.setRollUpStatus(new RollUpStatus(rollUpStatusText));
                    minh.setStudentId(rs.getInt("sid"));
                    listPerson.add(minh);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else {
            ResultSet rs = sdb.getAllStudentInClass(c);
            try {
                while(rs.next()){
                    Student minh = new Student();
                    minh.setFname(rs.getString("fname"));
                    minh.setLname(rs.getString("lname"));
                    minh.setRollNo(rs.getString("rollno"));                               
                    minh.setRollUpStatus(new RollUpStatus("P"));
                    minh.setStudentId(rs.getInt("sid"));
                    listPerson.add(minh);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
        tableModel = new AttendanceTableModel(listPerson);

        List<RollUpStatus> listRs = new ArrayList<>();
		listRs.add(new RollUpStatus("P"));
		listRs.add(new RollUpStatus("PA"));
		listRs.add(new RollUpStatus("A"));
        tblAttend.setModel(tableModel);
        tblAttend.setDefaultRenderer(RollUpStatus.class, new RollUpStatusCellRenderer());
		tblAttend.setDefaultEditor(RollUpStatus.class, new RollUpStatusCellEditor(listRs));
		tblAttend.setRowHeight(25);
       
    }
    
    public AttendanceFrame() {
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAttend = new javax.swing.JTable();
        txtFinish = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(110, 89, 222));

        jLabel1.setFont(new java.awt.Font("Miriam Mono CLM", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Attendance Roll Up");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-check-file-filled-50.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Miriam Mono CLM", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(254, 254, 254));

        jLabel4.setBackground(new java.awt.Color(233, 227, 39));
        jLabel4.setForeground(new java.awt.Color(233, 227, 39));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addComponent(jLabel4))
        );

        tblAttend.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblAttend);

        txtFinish.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/button_finish.png"))); // NOI18N
        txtFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFinishActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1056, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFinishActionPerformed
        // TODO add your handling code here:
        if(!atd.isMarked()){
            int id = new AttendanceDB().rollUp(atd);
            if(id > 0) atd.setMarked(true);
            atd.setAttendID(id);
            insertStudentToAttendance();
        }else {
//            new AttendanceDB().deleteRolledStudent(atd);
//            insertStudentToAttendance();
            editStudentAttendance();
        }

    }//GEN-LAST:event_txtFinishActionPerformed

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
            java.util.logging.Logger.getLogger(AttendanceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AttendanceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AttendanceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AttendanceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new AttendanceFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAttend;
    private javax.swing.JButton txtFinish;
    // End of variables declaration//GEN-END:variables
    public void insertStudentToAttendance(){
        int rowCount = tblAttend.getRowCount();   
        for (int i = 0; i < rowCount; i++) {
            String stt = tblAttend.getValueAt(i, 4)+"";           
            listPerson.get(i).setRollUpStatus(new RollUpStatus(stt));            
        }
        new AttendanceDB().rollUpStudent(listPerson, atd);
    }
    public void editStudentAttendance(){
        int rowCount = tblAttend.getRowCount();    
        for (int i = 0; i < rowCount; i++) {
            String stt = tblAttend.getValueAt(i, 4)+"";           
            listPerson.get(i).setRollUpStatus(new RollUpStatus(stt));               
        }
        new AttendanceDB().editRolledStudent(listPerson, atd);
    }

}
