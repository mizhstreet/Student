/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseOperation;
import Classes.Exam;
import Classes.ExamResult;
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
public class ExamDB {
    Connection conn;
    PreparedStatement pstmt;
    CallableStatement cstmt;
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
            JOptionPane.showMessageDialog(null, "Exam inserted successfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Exam inserted failed!");
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    public void editExam(Exam dummyExam){
        try {
            cstmt = conn.prepareCall("{call editExam(?,?,?,?,?,?,?)}");
            cstmt.setInt(1, dummyExam.getExam_id());
            cstmt.setDate(2, dummyExam.getExam_date());
            cstmt.setString(3, dummyExam.getName());
            cstmt.setInt(4, dummyExam.getSubject_id());
            cstmt.setInt(5, dummyExam.getCondition());
            cstmt.setInt(6, dummyExam.getTotalmark());
            cstmt.setInt(7, dummyExam.getClass_id());
            int i = cstmt.executeUpdate();
            if(i>0){
                JOptionPane.showMessageDialog(null, "Edited Successfully!");
            }
            else JOptionPane.showMessageDialog(null, "Edited Failed!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Edit Failed!");
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ResultSet getAllExamByClassID(int classID){
        try {
            cstmt = conn.prepareCall("{call getAllExamByClassID(?)}");
            cstmt.setInt(1, classID);
            rs = cstmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Cannot get all exams of this class!");
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public void deleteMark(Exam e){
        try {
            cstmt = conn.prepareCall("{call deleteExam(?)}");
            cstmt.setInt(1, e.getExam_id());
            cstmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Delete Failed!");
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
            JOptionPane.showMessageDialog(null, "Done!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed!");
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
