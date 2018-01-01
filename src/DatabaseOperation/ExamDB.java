/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseOperation;
import Classes.Exam;
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
public class ExamDB {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    public ExamDB() {
        conn = DBUtility.openConnection();
    }
    
    public void insertExam(Exam dummyExam){
        String insertQuery = "insert into Student"
                    + " values('"
                    + dummyExam.getExam_date()
                    + "'," + dummyExam.getName()+ "'"
                    + "," + dummyExam.getSubject_id()+ ""
                    + "," + dummyExam.getCondition()+ ""
                    + "," + dummyExam.getClass_id()+ ""
                    + "," + dummyExam.getTotalmark()+ ""
                    + ")";
        try {
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void editExam(Exam dummyExam){
        String updateQuery ="update Exam set exam_date = '"
                    +dummyExam.getExam_date()+"', name="
                    +dummyExam.getName()+", subject_id = '"
                    +dummyExam.getSubject_id()+"', condition = '"
                    +dummyExam.getCondition()+"',class_id = '"
                    +dummyExam.getClass_id();
        try {
            pstmt = conn.prepareStatement(updateQuery);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ResultSet getAllExamByClassID(int classID){
        String sql = "Select exam_id, s.name as subject_name, e.name, condition,totalmark, exam_date "
                + "from exam e inner join "
                + "Subjects s on e.subject_id = s.subject_id where class_id="+classID;
        try {
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
