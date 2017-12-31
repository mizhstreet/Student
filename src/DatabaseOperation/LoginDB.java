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
public class LoginDB {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    public LoginDB() {
        conn = DBUtility.openConnection();
    }
    public boolean isLoggedInSuccess(String username, String password){
        boolean isSuccessful = false;
        String sql = "Select * from admin where username like '"+username+"' and password like '"+password+"'";
        try {
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                isSuccessful = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccessful;
    }
}
