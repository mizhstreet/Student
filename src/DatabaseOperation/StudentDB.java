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
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mbiuu
 */

public class StudentDB {
    Connection conn;
    PreparedStatement pstmt;
    CallableStatement cstmt;
    ResultSet rs;
//    Test
//    public static void main(String[] args) {
//        Student minh = new Student();
//        minh.setAddress("tan dan");
//        minh.setDob(new Date(2007,12,3));
//        minh.setFname("MInh");
//        minh.setLname("biu");
//        minh.setStatus(true);
//        minh.setRollNo("B7855");
//        minh.setJoinedDate(new Date(2007,12,3));
//        minh.setPhone("09342432332");
//        minh.setImage("3.jpg");
//        StudentDB sdb = new StudentDB();
//        Classes.Class c = new Classes.Class();
//        c.setClassID(1);
//        c.setStatus(true);
//        sdb.insertStudent(minh,c);
////        sdb.getAllStudent();
////        sdb.activateStudent(minh);
//
//    }
    public StudentDB() {
        conn = DBUtility.openConnection();
    }
    public boolean insertStudent(Student dummyStudent, Classes.Class dummyClass){
        boolean check = false;
        String insertQuery = "insert into Students"
                    + " values('"
                    + dummyStudent.getFname()
                    + "','" + dummyStudent.getLname() + "'"
                    + ",'" + dummyStudent.getDob()+ "'"
                    + ",'" + dummyStudent.getPhone() + "'"
                    + ",'" + dummyStudent.getJoinedDate()+ "'"
                    + ",'" + dummyStudent.getAddress() + "'"
                    + "," + (dummyStudent.isStatus()  ?  1 : 0)+ ""
                    + ",'" + dummyStudent.getImage()+ "'"
                    + ",'" + dummyStudent.getRollNo()+ "'"
                    + ")";
        
        String getStudentId = "select student_id from Students where rollno="+"'"+dummyStudent.getRollNo()+"'";
        try {
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.execute();
//            System.out.println(getStudentId);
            pstmt= conn.prepareStatement(getStudentId);
            ResultSet rss =pstmt.executeQuery();
            while(rss.next()){
                dummyStudent.setStudentId(rss.getInt("student_id"));
            }
            if(dummyClass != null)
            check=insertStudentToClass(dummyStudent, dummyClass);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
    public boolean insertStudentToClass(Student dummyStudent, Classes.Class dummyClass){
        String insertClass = "insert into class_student"
                    + " values('"
                    + dummyStudent.getStudentId()
                    + "'," + dummyClass.getClassID()+ ""
                    + ")";
        try {
            pstmt= conn.prepareStatement(insertClass);
            int i = pstmt.executeUpdate();
            if(i>0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public void editStudent(Student dummyStudent,String roll){
        String updateQuery ="update Students set rollno='"
                    +dummyStudent.getRollNo()+"', fname = '"
                    +dummyStudent.getFname()+"', lname='"
                    +dummyStudent.getLname()+"', dob = '"
                    +dummyStudent.getDob()+"', phone = '"
                    +dummyStudent.getPhone()+"',joined_date = '"
                    +dummyStudent.getJoinedDate()+"', address = '"
                    +dummyStudent.getAddress()+"'"
                    +" where rollno ="+"'"+roll+"'"
                ;
        try {
            pstmt = conn.prepareStatement(updateQuery);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    public void deactivateStudent(Student dummyStudent){
        String query = "Update Students set status = 0 where rollno="+"'"+dummyStudent.getRollNo()+"'";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void activateStudent(Student dummyStudent){
        String query = "Update Students set status = 1 where rollno="+"'"+dummyStudent.getRollNo()+"'";
        try {           
            pstmt = conn.prepareStatement(query);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ResultSet getAllStudentInClass(Classes.Class dummyClass){
        String query = "select s.student_id as sid, fname, rollno, "
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
    public ResultSet getAllStudent(){
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
        public ResultSet getAllOnlyStudent(String query){
            if(query == null)
            query = "select * from students where status = 1";
        try {
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public ResultSet SearchStudent(String rollNo){
        if(rollNo == null)
            JOptionPane.showMessageDialog(null,"Input your id student you want to search");
            String query = "select * from students where rollNo = '"+ rollNo +"' and status = 1";
        try {
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
