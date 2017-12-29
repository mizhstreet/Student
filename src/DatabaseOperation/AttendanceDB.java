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
import java.sql.Statement;
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
    public int rollUp(Attendance dummyAttendance){
        int id = 0;
        String sql = "insert into Attendance"
                    + " values("
                    + (dummyAttendance.isMarked() ? 1 : 0)
                    + "," + dummyAttendance.getSessionId()+ ""
                    + "," + dummyAttendance.getClassID()+ ""
                    + ")";
        try {
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.execute();
            rs = pstmt.getGeneratedKeys();
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    public void editStudentAttendance(Student dummyStudent){
        String sql = "Update Attendance set attendant=";
    }
    public ResultSet checkIfExisted(String className, String sessionName){
        String sql = "Select attend_id from attendance a inner join Class c on a.class_id = c.class_id inner join "
                + "subject_sessions s on a.session_id = s.session_id "
                + "where c.name ='"+className+"' and s.name ='"+sessionName+"'";
        try {
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public void rollUpStudent(String sql){
//        String sql = "Insert into Attendance_student set attendstatus="+stt+" where"
//                + " attend_id ="+dummyAttendance.getAttendID()+" AND student_id="+dummyStudent.getStudentId();      
        try {
            pstmt = conn.prepareStatement(sql);
             pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDB.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    public void deleteRolledStudent(Attendance dummyAttendance){
        String sql = "Delete from Attendance_Student where attend_id="+dummyAttendance.getAttendID();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.execute();
                    } catch (SQLException ex) {
            Logger.getLogger(AttendanceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
