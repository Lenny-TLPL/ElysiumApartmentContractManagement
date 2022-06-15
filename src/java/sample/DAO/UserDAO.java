/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sample.DTO.UserDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Phi Long
 */
public class UserDAO {
    private static final String LOGIN="SELECT fullName, email, phone, address, birthday, citizenID, gender, dateJoin, status, roleName FROM tblUser u INNER JOIN tblRole r ON r.roleID = u.roleID WHERE userID = ? AND password = ? AND status = 1";
    
    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(LOGIN);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) { 
                    String fullName = rs.getString("fullName") ;
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    Date birthDay = rs.getDate("birthday");
                    String citizenID = rs.getString("citizenID");
                    String gender = rs.getString("gender");
                    Date dateJoin = rs.getDate("dateJoin");
                    boolean status = rs.getBoolean("status");
                    String roleName = rs.getString("roleName");
                    user = new UserDTO(userID, fullName, email, phone, address, birthDay, citizenID, gender, password, dateJoin, status, roleName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }
}
