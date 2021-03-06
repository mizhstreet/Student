/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Attendance;

import Classes.Attendance;
import Classes.Subject;
import Classes.SubjectSession;
import DatabaseOperation.AttendanceDB;
import DatabaseOperation.ClassDB;
import DatabaseOperation.SubjectDB;
import DatabaseOperation.SubjectSessionDB;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author mbiuu
 */
public class AttendanceOptions extends javax.swing.JFrame {
    DefaultComboBoxModel<Classes.Class> cboClassModel;
    DefaultComboBoxModel<Subject> cboSubjectModel;
    DefaultComboBoxModel<SubjectSession> cboSubjectSessionModel;
    ArrayList<Subject> listSubject;
    ArrayList<SubjectSession> listSessions;
    ArrayList<Classes.Class> listClass;
    /**
     * Creates new form AttendanceOptions
     */
    public AttendanceOptions() {
        initComponents();
        listSubject = new ArrayList<>();
        listClass = new ArrayList<>();
        listSessions = new ArrayList<>();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        cboClassModel = new DefaultComboBoxModel<>();
        cboClass.setModel(cboClassModel);
        cboSubjectModel = new DefaultComboBoxModel<>();
        cboSubject.setModel(cboSubjectModel);
        cboSubjectSessionModel = new DefaultComboBoxModel<>();
        cboSession.setModel(cboSubjectSessionModel);
        loadClassForCombo();
        loadSubjectForCombo();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboClass = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cboSubject = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cboSession = new javax.swing.JComboBox();
        btnOk = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(110, 89, 222));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Attendance Options");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(130, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setForeground(new java.awt.Color(50, 33, 89));
        jLabel2.setText("Classes");

        cboClass.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setForeground(new java.awt.Color(50, 33, 89));
        jLabel3.setText("Subjects");

        cboSubject.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboSubject.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSubjectItemStateChanged(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(50, 33, 89));
        jLabel4.setText("Sessions");

        cboSession.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/button_ok.png"))); // NOI18N
        btnOk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOkMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboClass, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboSubject, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboSession, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnOk)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOk)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboSubjectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSubjectItemStateChanged
        // TODO add your handling code here:
        JComboBox<Subject> cb =  (JComboBox) evt.getSource();
        Object item = evt.getItem();

        if (evt.getStateChange() == ItemEvent.SELECTED) {
          // Item was just selected
            loadSessionForCombo(listSubject.get(cb.getSelectedIndex()).getSubject_id());
//            System.out.println(item.toString());
//            System.out.println(cb.getSelectedIndex());
        } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
          // Item is no longer selected
        }
    }//GEN-LAST:event_cboSubjectItemStateChanged

    private void btnOkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOkMouseClicked
        // TODO add your handling code here:
        AttendanceDB adb = new AttendanceDB();
        Attendance atd = new Attendance();
        String className = cboClass.getSelectedItem().toString();
        String sessionName = cboSession.getSelectedItem().toString();
        atd.setMarked(false);
        atd.setClassID(listClass.get(cboClass.getSelectedIndex()).getClassID());
        atd.setSessionId(listSessions.get(cboSession.getSelectedIndex()).getSessionId());
        ResultSet rs = adb.checkIfExisted(className, sessionName);
        try {
            while(rs.next()){
               atd.setAttendID(rs.getInt("attend_id"));
               atd.setMarked(true);              
            }
            this.setVisible(false);
            new AttendanceFrame(atd).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOkMouseClicked

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
            java.util.logging.Logger.getLogger(AttendanceOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AttendanceOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AttendanceOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AttendanceOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new AttendanceOptions().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnOk;
    private javax.swing.JComboBox cboClass;
    private javax.swing.JComboBox cboSession;
    private javax.swing.JComboBox cboSubject;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
    private void loadClassForCombo(){
        ResultSet rs;
        ClassDB classDB = new ClassDB();
        cboClassModel.removeAllElements();
        rs = classDB.getAllClass();
        try {
            while(rs.next()){
                Classes.Class c = new Classes.Class();
                c.setClassID(rs.getInt("class_id"));
                c.setName(rs.getString("name"));
                cboClassModel.addElement(c);
                listClass.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void loadSubjectForCombo(){
        ResultSet rs;
        SubjectDB subjectDB = new SubjectDB();
        cboSubjectModel.removeAllElements();
        rs = subjectDB.getAllSubject();
        try {
            while(rs.next()){
                Subject s = new Subject();
                s.setSubject_id(rs.getInt("subject_id"));
                s.setName(rs.getString("name"));
                listSubject.add(s);
                cboSubjectModel.addElement(s);              
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void loadSessionForCombo(int subjectID){
        ResultSet rs;
        SubjectSessionDB ssdb = new SubjectSessionDB();
        cboSubjectSessionModel.removeAllElements();      
        System.out.println(subjectID);
        rs = ssdb.getAllSubjectSession(subjectID);
        listSessions.clear();
        try {
            while(rs.next()){
                SubjectSession ss = new SubjectSession();
                ss.setName(rs.getString("name"));
                ss.setSessionId(rs.getInt("session_id"));
                cboSubjectSessionModel.addElement(ss);
                listSessions.add(ss);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
