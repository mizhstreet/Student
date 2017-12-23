/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseOperation;

import Classes.Attendance;
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
public class AttendanceDB {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    public AttendanceDB() {
        conn = DBUtility.openConnection();
    }
    public static void main(String[] args) {
        
    }
    public void rollUp(Attendance dummyAttendance){
        String sql = "insert into Attendance"
                    + " values('"
                    + dummyAttendance.getStudentId()
                    + "'," + dummyAttendance.isAttendant()+ ""
                    + ",'" + dummyAttendance.getSessionId()+ "'"
                    + ")";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void editStudentAttendance(Attendance dummyAttedance){
        String sql = "Update Attendance set attendant=";
    }
    
}
