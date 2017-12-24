/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseOperation;

import Classes.Attendance;
import Classes.Student;
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
    public void rollUp(Attendance dummyAttendance){
        String sql = "insert into Attendance"
                    + " values("
                    + (dummyAttendance.isMarked() ? 1 : 0)
                    + "," + dummyAttendance.getSessionId()+ ""
                    + "," + dummyAttendance.getClassID()+ ""
                    + ")";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void editStudentAttendance(Student dummyStudent){
        String sql = "Update Attendance set attendant=";
    }
    public void rollUpStudent(Attendance dummyAttendance, Student dummyStudent, byte stt){
//        String sql = "Insert into Attendance_student set attendstatus="+stt+" where"
//                + " attend_id ="+dummyAttendance.getAttendID()+" AND student_id="+dummyStudent.getStudentId();
        String sql = "Insert into Attendance_Student values("+dummyAttendance.getAttendID()+","
                +dummyStudent.getStudentId()+","+stt+")";
        try {
            pstmt = conn.prepareStatement(sql);
             pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDB.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
}
