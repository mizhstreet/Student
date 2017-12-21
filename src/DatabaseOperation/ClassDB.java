/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Classes.Class;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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

    public void updateClass(Class dummyClass){
        String command = "update Class set name ="+ dummyClass.getName() +", description = ";
    }
    
}
