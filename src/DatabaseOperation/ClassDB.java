/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseOperation;

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
public class ClassDB {
    Connection conn;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public ClassDB() {
        conn = DBUtility.openConnection();
    }
//    Test only
//    public static void main(String[] args) {
//        Classes.Class c = new Classes.Class();
//        c.setDescription("Lop nay hoc Java");
//        c.setName("C1605M");
//        c.setStatus(true);
//        ClassDB cdb = new ClassDB();
//        cdb.activateClass(c);
//    }
    public void insertClass(Classes.Class dummyClass){
        String insertQuery = "insert into Class"
                    + " values('"
                    + dummyClass.getName()
                    + "','" + dummyClass.getDescription()+ "'"
                    + "," + (dummyClass.isStatus() ? 1 : 0)+ ""
                    + ")";
        try {
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClassDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void deactivateClass(Classes.Class dummyClass){
        String query = "Update Class set status = 0 where class_id="+"'"+dummyClass.getClassID()+"'";
        try {           
            pstmt = conn.prepareStatement(query);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void activateClass(Classes.Class dummyClass){
        String query = "Update Class set status = 1 where class_id="+"'"+dummyClass.getClassID()+"'";
        try {           
            pstmt = conn.prepareStatement(query);
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ResultSet getAllClass(){
        String query = "Select * from Class";
        try {
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ClassDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
