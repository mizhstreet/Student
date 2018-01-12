/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseOperation;

import Classes.Attendance;
import Classes.Student;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mbiuu
 */
public class AttendanceDB {
    Connection conn;
    PreparedStatement pstmt;
    CallableStatement cstmt;
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
    public ResultSet checkIfExisted(String className, String sessionName){
        try {
            cstmt = conn.prepareCall("{call checkAttendanceSession (?,?)}");
            cstmt.setString(1, className);
            cstmt.setString(2, sessionName);
            rs = cstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public void rollUpStudent(ArrayList<Student> listStudent, Attendance atd){    
        try {
            Statement stmt = conn.createStatement();
            for (Student s : listStudent) {
                int type = 0;
                switch (s.getRollUpStatus().toString()) {
                case "P":
                    type = 1;
                    break;
                case "PA":
                    type =2;
                    break;
                case "A":
                    type =0;
                    break;
                default:
                    break;
                }
                String sql = "Insert into Attendance_Student values("+atd.getAttendID()+","
                        +s.getStudentId()+","+type+")";
                stmt.addBatch(sql);                
            }
            stmt.executeBatch();
            JOptionPane.showMessageDialog(null, "Rolled Up Succesfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Rolled Up Failed!");
            Logger.getLogger(AttendanceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void editRolledStudent(ArrayList<Student> listStudent, Attendance atd){
        try {
            Statement stmt = conn.createStatement();
            for (Student s : listStudent) {
                int type = 0;
                switch (s.getRollUpStatus().toString()) {
                case "P":
                    type = 1;
                    break;
                case "PA":
                    type =2;
                    break;
                case "A":
                    type =0;
                    break;
                default:
                    break;
                }
                String sql = "Update Attendance_Student set attendstatus ="+type+" where "
                        + "attend_id ="+atd.getAttendID()+" And student_id ="+s.getStudentId();
                stmt.addBatch(sql);                
            }
            stmt.executeBatch();
            JOptionPane.showMessageDialog(null, "Edited Succesfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Edited Failed!");
            Logger.getLogger(AttendanceDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
