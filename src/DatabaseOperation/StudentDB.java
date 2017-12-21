/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseOperation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Classes.Student;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mbiuu
 */
public class StudentDB {
    Connection conn;
    PreparedStatement pstmt;
    CallableStatement cstmt;
    ResultSet rs;
    public StudentDB() {
        conn = DBUtility.openConnection();
    }
    public void insertStudent(Student dummyStudent){
        String insertQuery = "insert into Student"
                    + " values('"
                    + dummyStudent.getFname()
                    + "'," + dummyStudent.getLname() + ""
                    + ",'" + dummyStudent.getDob()+ "'"
                    + ",'" + dummyStudent.getPhone() + "'"
                    + ",'" + dummyStudent.getJoinedDate()+ "'"
                    + ",'" + dummyStudent.getAddress() + "'"
                    + ")";
        try {
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void editStudent(Student dummyStudent){
        String updateQuery ="update Student set fname = '"
                    +dummyStudent.getFname()+"', lname="
                    +dummyStudent.getLname()+", dob = '"
                    +dummyStudent.getDob()+"', phone = '"
                    +dummyStudent.getPhone()+"',joined_date = '"
                    +dummyStudent.getJoinedDate()+"', address = '"
                    +dummyStudent.getAddress();
        try {
            pstmt = conn.prepareStatement(updateQuery);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    public void deactiveStudent(Student dummyStudent){
        String query = "Update Student set status = 0 where student_id="+dummyStudent.getStudentId();
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void activeStudent(Student dummyStudent){
        String query = "Update Student set status = 1 where student_id="+dummyStudent.getStudentId();
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ResultSet getAllStudentInClass(Classes.Class dummyClass){
        String query = "select s.student_id as sid, fname, "
                + "lname, dob, phone, joined_date, address "
                + "from Students s inner join Class_Student on s.student_id = Class_Student.student_id "
                + "inner join Class c on Class_Student.class_id = c.class_id where c.class_id =" + dummyClass.getClassID();
        try {
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public ResultSet getAllStudent(Classes.Class dummyClass){
        String query = "select s.student_id as sid, fname, c.name as className"
                + "lname, dob, phone, joined_date, address "
                + "from Students s inner join Class_Student on s.student_id = Class_Student.student_id "
                + "inner join Class c on Class_Student.class_id = c.class_id ";
        try {
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
