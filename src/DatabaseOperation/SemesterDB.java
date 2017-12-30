/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseOperation;

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
public class SemesterDB {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    public SemesterDB() {
        conn = DBUtility.openConnection();
    }
    
    public ResultSet getAllSemester(){
        String sql = "Select * from Semester";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();           
        } catch (SQLException ex) {
            Logger.getLogger(SemesterDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }    
}
