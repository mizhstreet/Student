/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseOperation;

import Classes.Subject;
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
public class SubjectDB {
    Connection conn;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public SubjectDB() {
        conn = DBUtility.openConnection();
    }
    public int getNumberOfSubject(){
        int i = 0;
        String sql = "select * from Subjects where status = 1";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
    public int insertSubject(Subject dummySubject){
        int id = 0;
        String insertQuery = "insert into Subjects"
                    + " values('"
                    + dummySubject.getName()
                    + "'," + dummySubject.getSemId()+ ""
                    + ",'" + dummySubject.getDescription()+ "'"
                    + "," + (dummySubject.isStatus() ? 1 : 0)+ ""
                    + ")";
        try {
            pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            pstmt.execute();
            rs = pstmt.getGeneratedKeys();
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDB.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return id;
    }
    public ResultSet getAllSubject(){
        String sql = "Select subject_id, Subjects.name, description, status, Semester.name as semname from Subjects inner join Semester on Subjects.sem_id = Semester.sem_id";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public void updateSubject(Subject dummySubject){
        String query = "Update Subjects set name='"+dummySubject.getName()+"', "
                + "description='"+dummySubject.getDescription()+"', "
                + "status="+(dummySubject.isStatus() ? 1 : 0)+", "
                + "sem_id="+dummySubject.getSemId()+" "
                + "where subject_id="+dummySubject.getSubject_id();
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClassDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
