/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sample.DTO.RoleDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Phi Long
 */
public class RoleDAO {
    private static final String GET_USER_ROLE="SELECT roleName FROM tblRole WHERE roleID = ?";
    private static final String GET_USER_ROLEID="SELECT roleID FROM tblROle WHERE roleName COLLATE SQL_Latin1_General_CP1_CS_AS = ?";
    private static final String GET_LIST_ROLE="	SELECT roleID, roleName FROM tblRole WHERE roleName LIKE ?";
    
    public String getUserRole(int roleID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String roleName="";
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_USER_ROLE);
                stm.setInt(1, roleID);
                rs = stm.executeQuery();
                if (rs.next()) { 
                    roleName=rs.getNString("roleName");                   
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
        return roleName;
    }
    public int getUserRoleID(String roleName) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int roleID=0;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_USER_ROLEID);
                stm.setNString(1,roleName);
                rs = stm.executeQuery();
                if (rs.next()) { 
                    roleID=rs.getInt("roleID");                   
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
        return roleID;
    }
    public ArrayList<RoleDTO> getListRole(String search) throws SQLException {
        ArrayList<RoleDTO> list = new ArrayList<>();
        RoleDTO role = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_ROLE);
                stm.setNString(1, "%"+search+"%");
             
                rs = stm.executeQuery();
                while (rs.next()) { 
                    String roleName= rs.getNString("roleName");
                    int roleID = rs.getInt("roleID");
                    role= new RoleDTO(roleID, roleName);
                    list.add(role);
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
