/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DAO;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sample.DTO.UserDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Phi Long
 */
public class UserDAO {
    private static final String LOGIN="SELECT fullName, email, phone, address, birthday, citizenID, gender, dateJoin, status, roleID FROM tblUser  WHERE (userID = ? or phone = ?) AND password = ? AND status = 1";
    private static final String CHECK_DUPLICATE_USER="SELECT citizenID FROM tblUser WHERE tblUser.citizenID COLLATE SQL_Latin1_General_CP1_CS_AS = ?  AND tblUser.roleID NOT IN(1,2,3)";
    private static final String ADD_USER="EXEC addUser ?, ?, ?, ?, ?, ?, ?, ?, ?";
    private static final String GET_LIST_USER="SELECT userID, password, fullName, email, phone, address, birthday, citizenID, gender, dateJoin, status, roleID FROM tblUser "
            + "WHERE tblUser.roleID = COALESCE((SELECT TOP 1 roleID FROM tblRole WHERE roleName COLLATE SQL_Latin1_General_CP1_CS_AS = ?),0)"
            + " AND (fullName LIKE ? OR userID LIKE ? OR citizenID LIKE ? OR phone LIKE ?)";
    private static final String GET_LIST_LOGIN_USER_PERMMISSION="SELECT p.permissionName FROM tblUserPermission u "
            + "INNER JOIN tblPermission p ON u.permissionID=p.permissionID  " +
              " WHERE userID COLLATE SQL_Latin1_General_CP1_CS_AS = ?";
    
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
                stm.setString(2, userID);
                stm.setString(3, password);
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
                    int roleID = rs.getInt("roleID");
                    user = new UserDTO(userID, fullName, email, phone, address, birthDay, citizenID, gender, password, dateJoin, status, roleID);
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
    
    public boolean checkDuplicateUser(String citizenID) throws SQLException{ 
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE_USER);
                ptm.setString(1,citizenID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    check=true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public boolean addUser(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_USER);
                ptm.setString(1, user.getFullName());
                ptm.setString(2, user.getEmail());
                ptm.setString(3, user.getPhone());
                ptm.setString(4, user.getAddress());
                ptm.setDate(5,new java.sql.Date(user.getBirthDay().getTime()));
                ptm.setString(6, user.getCitizenID());
                ptm.setString(7, user.getGender());
                ptm.setString(8, user.getPassword());
                ptm.setInt(9, user.getRoleID());
                check = ptm.executeUpdate() > 0 ? true : false; //execute update dung cho insert,delete
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public ArrayList<UserDTO> getListUser(String type, String search) throws SQLException {
        ArrayList<UserDTO> list = new ArrayList<>();
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_USER);
                stm.setString(1, type);
                stm.setString(2, "%"+search+"%");
                stm.setString(3, "%"+search+"%");
                stm.setString(4, "%"+search+"%");
                stm.setString(5, "%"+search+"%");               
                rs = stm.executeQuery();
                while (rs.next()) { 
                    String userID = rs.getString("userID");
                    String password = rs.getString("password");
                    String fullName = rs.getString("fullName") ;
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    Date birthDay = rs.getDate("birthday");
                    String citizenID = rs.getString("citizenID");
                    String gender = rs.getString("gender");
                    Date dateJoin = rs.getDate("dateJoin");
                    boolean status = rs.getBoolean("status");  
                    int roleID = rs.getInt("roleID");
                    user = new UserDTO(userID, fullName, email, phone, address, birthDay, citizenID, gender, password, dateJoin, status, roleID);
                    list.add(user);
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
        return list;
    }
    
    public ArrayList<String> getListLoginUserPermission(String userID) throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_LOGIN_USER_PERMMISSION);
                stm.setString(1, userID);              
                rs = stm.executeQuery();
                while (rs.next()) { 
                    String permission = rs.getString("permissionName");
                    list.add(permission);
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
        return list;
    }
}
