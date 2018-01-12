/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseOperation;

import Classes.Subject;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mbiuu
 */
public class SubjectDB {
    Connection conn;
    CallableStatement cstmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public SubjectDB() {
        conn = DBUtility.openConnection();
    }
    public int getNumberOfSubject(){
        int i = 0;
        try {
            cstmt = conn.prepareCall("{call getCountOfSubjects}");
            rs = cstmt.executeQuery();
            while(rs.next()){
                i = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
    public int insertSubject(Subject dummySubject){
        int id = 0;
        String insertQuery = "insert into Subjects"
                    + " values('"
                    + dummySubject.getName()
                    + "'," + dummySubject.getSemId()+ ""
                    + ",'" + dummySubject.getDescription()+ "'"
                    + "," + (dummySubject.isStatus() ? 1 : 0)+ ""
                    + ")";
        try {
            pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            pstmt.execute();
            rs = pstmt.getGeneratedKeys();
            while(rs.next()){
                id = rs.getInt(1);
            }
            JOptionPane.showMessageDialog(null, "Inserted Succesfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Inserted Failed!");
            Logger.getLogger(SubjectDB.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return id;
    }
    public ResultSet getAllSubject(){
        try {
            cstmt = conn.prepareCall("{call getAllSubject}");
            rs = cstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public ResultSet getAllActiveSubject(){
        try {
            cstmt = conn.prepareCall("{call getAllActiveSubject}");
            rs = cstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public void updateSubject(Subject dummySubject){
        try {
            cstmt = conn.prepareCall("{call updateSubject (?,?,?,?,?)}");
            cstmt.setInt(1, dummySubject.getSubject_id());
            cstmt.setString(2, dummySubject.getName());
            cstmt.setInt(3, dummySubject.getSemId()); 
            cstmt.setInt(5, (dummySubject.isStatus() ? 1 : 0));
            cstmt.setString(4, dummySubject.getDescription());                    
            int i = cstmt.executeUpdate();
            if(i>0){
                JOptionPane.showMessageDialog(null, "Edited Successfully!");
            }
            else JOptionPane.showMessageDialog(null, "Edited Failed!");
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
