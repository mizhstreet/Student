/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseOperation;
import Classes.Exam;
import Classes.ExamResult;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    
    public int insertExam(Exam dummyExam){
        int id =0;
        String insertQuery = "insert into Exam"
                    + " values('"
                    + dummyExam.getExam_date()
                    + "','" + dummyExam.getName()+ "'"
                    + "," + dummyExam.getSubject_id()+ ""
                    + "," + dummyExam.getCondition()+ ""
                    + "," + dummyExam.getClass_id()+ ""
                    + "," + dummyExam.getTotalmark()+ ""
                    + ")";
        try {
            System.out.println(insertQuery);
            pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            pstmt.execute();
            rs = pstmt.getGeneratedKeys();
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    public void editExam(Exam dummyExam){
        String updateQuery ="update Exam set exam_date = '"
                    +dummyExam.getExam_date()+"', name='"
                    +dummyExam.getName()+"', subject_id = "
                    +dummyExam.getSubject_id()+", condition = "
                    +dummyExam.getCondition()+",class_id = "
                    +dummyExam.getClass_id()+", totalmark ="
                    +dummyExam.getTotalmark()+" where exam_id="+dummyExam.getExam_id();
        try {
            pstmt = conn.prepareStatement(updateQuery);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ResultSet getAllExamByClassID(int classID){
        String sql = "Select exam_id, s.name as subject_name, e.name, condition,totalmark, exam_date "
                + "from exam e inner join "
                + "Subjects s on e.subject_id = s.subject_id where class_id="+classID;
        try {
//            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public void deleteMark(Exam e){
        String sql = "delete from exam_result where exam_id="+e.getExam_id();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void insertMark(ArrayList<ExamResult> er){
        try {
            Statement stmt = conn.createStatement();
            for (ExamResult examResult : er) {
                String sql = "Insert into exam_result values("+examResult.getExamID()+
                        ","+examResult.getStudentID()+","+examResult.getMark()
                        +",'"+examResult.getRemark()+"')";
                stmt.addBatch(sql);
            }
            stmt.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
