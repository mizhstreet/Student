/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseOperation;

import Classes.Subject;
import Classes.SubjectSession;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mbiuu
 */
public class SubjectSessionDB {
    Connection conn;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    CallableStatement cstmt;
    public SubjectSessionDB() {
        conn = DBUtility.openConnection();
    }
    public ResultSet getAllSubjectSession(int subjectID){       
        try {
            cstmt = conn.prepareCall("{call getAllSubjectSession(?)}");
            cstmt.setInt(1, subjectID);
            rs = cstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectSessionDB.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return rs;
    }
    public void insertSubjectSession(SubjectSession ss){
        try {
            cstmt = conn.prepareCall("{call insertSubjectSession(?,?)}");
            cstmt.setInt(1, ss.getSubjectId());
            cstmt.setString(2, ss.getName());
            int i = cstmt.executeUpdate();
            if(i>0){
                JOptionPane.showMessageDialog(null, "Inserted Successfully!");
            }else{
                JOptionPane.showMessageDialog(null, "Inserted Failed!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectSessionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void editSubjectSession(SubjectSession ss){
        try {
            cstmt = conn.prepareCall("{call editSubjectSession(?,?,?)}");
            cstmt.setInt(1, ss.getSessionId());
            cstmt.setInt(2, ss.getSubjectId());
            cstmt.setString(3, ss.getName());
            int i = cstmt.executeUpdate();
            if(i>0){
                JOptionPane.showMessageDialog(null, "Edited Successfully!");
            }else{
                JOptionPane.showMessageDialog(null, "Edited Failed!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectSessionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
