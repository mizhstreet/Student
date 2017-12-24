/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseOperation;

import Classes.Subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mbiuu
 */
public class SubjectSessionDB {
    Connection conn;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public SubjectSessionDB() {
        conn = DBUtility.openConnection();
    }
    public ResultSet getAllSubjectSession(Subject dummySubject){
        String sql = "select * from Subject_Sessions where subject_id="+dummySubject.getSubject_id();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectSessionDB.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return rs;
    }
    public ResultSet getAllSubjectSessionByName(String name){
        String sql = "Select ss.session_id, ss.name from Subject_Sessions ss inner join Subjects s on ss.subject_id = s.subject_id "
                + "where s.name ='"+name+"'";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectSessionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
}
