/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseOperation;

import Classes.Exam;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Classes.Student;
import java.sql.SQLException;
import java.util.List;
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

    public int getNumberOfStudent() {
        int i = 0;
        String sql = "select * from Students where status =1";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    public boolean insertStudent(Student dummyStudent, Classes.Class dummyClass) {
        boolean check = false;
        String insertQuery = "insert into Students"
                + " values('"
                + dummyStudent.getFname()
                + "','" + dummyStudent.getLname() + "'"
                + ",'" + dummyStudent.getDob() + "'"
                + ",'" + dummyStudent.getPhone() + "'"
                + ",'" + dummyStudent.getJoinedDate() + "'"
                + ",'" + dummyStudent.getAddress() + "'"
                + "," + (dummyStudent.isStatus() ? 1 : 0) + ""
                + ",'" + dummyStudent.getImage() + "'"
                + ",'" + dummyStudent.getRollNo() + "'"
                + ")";

        try {
            pstmt = conn.prepareStatement(insertQuery);
            int i = pstmt.executeUpdate();
//           
            if (i > 0) {
                check = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    public boolean insertStudentToClass(Student dummyStudent, Classes.Class dummyClass) {
        String insertClass = "insert into class_student"
                + " values('"
                + dummyStudent.getStudentId()
                + "'," + dummyClass.getClassID() + ""
                + ")";
        try {
            pstmt = conn.prepareStatement(insertClass);
            int i = pstmt.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ResultSet getAllMarkOfStudentByRollNo(String rollNo) {
        String sql = "select e.name as examname, condition, er.mark, er.remark, e.totalmark from Students s inner join "
                + "Exam_Result er on s.student_id = er.student_id "
                + "inner join Exam e on er.exam_id = e.exam_id where s.rollno='" + rollNo + "'";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public boolean editStudent(Student dummyStudent, String roll,int st_id,List<Classes.Class> list) {
        String updateQuery = "update Students set rollno='"
                + dummyStudent.getRollNo() + "', fname = '"
                + dummyStudent.getFname() + "', lname='"
                + dummyStudent.getLname() + "', dob = '"
                + dummyStudent.getDob() + "', phone = '"
                + dummyStudent.getPhone() + "',joined_date = '"
                + dummyStudent.getJoinedDate() + "', address = '"
                + dummyStudent.getAddress() + "'"
                + " where rollno =" + "'" + roll + "'";
        String deleteClass = "delete from Class_Student where Student_ID= '"+ st_id+"'";
        try {
            pstmt = conn.prepareStatement(deleteClass);
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement(updateQuery);
            int i = pstmt.executeUpdate();
            this.addClass(st_id, list);
            if(i>0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public void editImageStudent(int st_id,String image){
        String query = "Update Students set Image = '"+ image +"' where student_id="+ st_id;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deactivateStudent(Student dummyStudent) {
        String query = "Update Students set status = 0 where rollno=" + "'" + dummyStudent.getRollNo() + "'";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void activateStudent(Student dummyStudent) {
        String query = "Update Students set status = 1 where rollno=" + "'" + dummyStudent.getRollNo() + "'";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getAllStudentInClass(Classes.Class dummyClass) {
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

    public ResultSet getStudentHasMark(Exam e) {
        String query = "Select * from Exam_result where exam_id=" + e.getExam_id();
        try {
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getAllStudentInClassWithAttendance(Classes.Class dummyClass, int attendID) {
        String query = "select s.student_id as sid, fname, rollno, "
                + "lname, attendstatus, dob, phone, joined_date, address "
                + "from Students s inner join Class_Student on s.student_id = Class_Student.student_id "
                + "inner join Class c on Class_Student.class_id = c.class_id inner join Attendance_student a on"
                + " s.student_id = a.student_id"
                + " where c.class_id =" + dummyClass.getClassID() + " and attend_id=" + attendID;
        try {
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getAllStudent() {
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

    public ResultSet getAllOnlyStudent(String query) {
        if (query == null) {
            query = "select * from students where status = 1";
        }
        try {
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet SearchStudent(String rollNo) {
        if (rollNo == null) {
            JOptionPane.showMessageDialog(null, "Input your id student you want to search");
        }
        String query = "select * from students where rollNo = '" + rollNo + "' and status = 1";
        try {
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getNameClass() {
        String query = "select name, class_id from class";
        try {
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public ResultSet getClassNameOfStudent(int st_id) {
        String query = "select name from class c inner join Class_Student"
                + " on Class_student.Class_id = c.Class_id where student_id="+ st_id;
        try {
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public Boolean addClass(int st_id, List<Classes.Class> list) {
        try {
            String query = "insert into Class_Student values(?,?)";

            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(query);
            for (Classes.Class c : list) {
                pstmt.setInt(1, st_id);
                pstmt.setInt(2, c.getClassID());
                pstmt.addBatch();
            }
            int[] n = pstmt.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
            if (n.length > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
