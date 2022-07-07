package sample.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sample.utils.DBUtils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Phi Long
 */
public class UserPermissionDAO {

    private static final String ADD_USER_PERMISSION = "INSERT INTO tblUserPermission (userID, permissionID) VALUES(?,?)";
    private static final String GET_USERS_HAVE_GIVEN_PERMISSION="SELECT p.userID FROM tblUserPermission p INNER JOIN tblUser u ON p.userID = u.userID WHERE permissionID = ?";
    private static final String DELETE_USER_PERMISSION="DELETE FROM tblUserPermission WHERE userID COLLATE SQL_Latin1_General_CP1_CS_AS = ? ";
               
    public boolean addUser(String userID, int permissionID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_USER_PERMISSION);
                ptm.setString(1, userID);
                ptm.setInt(2, permissionID);
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
    public ArrayList<String> getListUsersHaveGivenPermission(int permissionID) throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_USERS_HAVE_GIVEN_PERMISSION);
                stm.setInt(1, permissionID); 
                rs = stm.executeQuery();
                while (rs.next()) { 
                    String userID = rs.getString("userID");                   
                    list.add(userID);
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
    public boolean deleteUserPermission(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_USER_PERMISSION);
                ptm.setString(1, userID);
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
}
