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
public class SubjectDB {
    Connection conn;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public SubjectDB() {
        conn = DBUtility.openConnection();
    }
    public void insertSubject(Subject dummySubject){
        String insertQuery = "insert into Class"
                    + " values('"
                    + dummySubject.getName()
                    + "'," + dummySubject.getSemId()+ ""
                    + ",'" + dummySubject.getDescription()+ "'"
                    + "," + (dummySubject.isStatus() ? 1 : 0)+ ""
                    + ")";
        try {
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDB.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    public ResultSet getAllSubject(){
        String sql = "Select * from Subjects";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
