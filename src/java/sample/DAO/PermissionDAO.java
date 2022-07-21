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
import sample.DTO.PermissionDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Phi Long
 */
public class PermissionDAO {
    private static final String GET_LIST_PERMISSION = "SELECT * FROM tblPermission p INNER JOIN tblRole r ON r.roleID = p.rolePriority WHERE permissionName LIKE ? OR r.roleName LIKE ?";
    private static final String GET_LIST_PERMISSION_WITH_PRIORITY = "SELECT * FROM tblPermission p INNER JOIN tblRole r ON r.roleID = p.rolePriority WHERE r.roleName LIKE ?";
    private static final String GET_LIST_PERMISSIONID_WITH_PRIORITY = "SELECT permissionID FROM tblPermission p INNER JOIN tblRole r ON r.roleID = p.rolePriority WHERE r.roleName LIKE ?";
    private static final String GET_PERMISSION_BY_NAME="SELECT permissionID, permissionName, status, rolePriority, roleName FROM tblPermission p INNER JOIN tblRole r ON r.roleID = p.rolePriority WHERE permissionName LIKE ?";
    private static final String ADD_NEW_PERMISSION="INSERT INTO tblPermission (permissionName, status, rolePriority) VALUES (?, ?, ?) ";
    private static final String UPDATE_PERMISSION_STATUS="UPDATE tblPermission SET status = ? WHERE permissionID = ?";
    private static final String UPDATE_PERMISSION="UPDATE tblPermission SET permissionName = ?, rolePriority = ? WHERE permissionID = ?";
    private static final String GET_LIST_AVAILABLE_PERMISSION = "SELECT permissionID FROM tblPermission p WHERE status = 1";
    
    public ArrayList<PermissionDTO> getListPermission(String search) throws SQLException {
        ArrayList<PermissionDTO> list = new ArrayList<>();
        PermissionDTO permission = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_PERMISSION);
                stm.setString(1, "%"+search+"%"); 
                stm.setString(2, "%"+search+"%");
                rs = stm.executeQuery();
                while (rs.next()) { 
                    int permissionID = rs.getInt("permissionID");
                    String permissionName = rs.getNString("permissionName") ;
                    String roleName = rs.getString("roleName");  
                    boolean status = rs.getBoolean("status");
                    permission = new PermissionDTO(permissionID, permissionName, roleName, status);
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
    
    public ArrayList<PermissionDTO> getListPermissionWithPriority(String roleNamePriority) throws SQLException {
        ArrayList<PermissionDTO> list = new ArrayList<>();
        PermissionDTO permission = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_PERMISSION_WITH_PRIORITY);
                stm.setString(1, "%"+roleNamePriority+"%");             
                rs = stm.executeQuery();
                while (rs.next()) { 
                    int permissionID = rs.getInt("permissionID");
                    String permissionName = rs.getNString("permissionName") ;
                    String roleName = rs.getString("roleName");  
                    boolean status = rs.getBoolean("status");
                    permission = new PermissionDTO(permissionID, permissionName, roleName, status);
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

    public PermissionDTO getPermissionByName(String search) throws SQLException {
        PermissionDTO permission = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_PERMISSION_BY_NAME);
                stm.setNString(1, "%"+search+"%");             
                rs = stm.executeQuery();
                if (rs.next()) { 
                    int permissionID = rs.getInt("permissionID");
                    String permissionName = rs.getNString("permissionName") ;                    
                    boolean status = rs.getBoolean("status");
                    String roleNamePriority = rs.getNString("roleName");
                    permission = new PermissionDTO(permissionID, permissionName, roleNamePriority, status);
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
        return permission;
    }
    
    public boolean addPermission(PermissionDTO permission, int roleID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_NEW_PERMISSION);
                ptm.setNString(1, permission.getPermissionName());
                ptm.setBoolean(2, permission.isStatus());
                ptm.setInt(3, roleID);
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
    
    public boolean updatePermissionStatus(int permissionID, boolean status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PERMISSION_STATUS);
                ptm.setBoolean(1, status);
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
    
    public ArrayList<Integer> getListPermissionIDWithPriority(String roleNamePriority) throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_PERMISSIONID_WITH_PRIORITY);
                stm.setString(1, "%"+roleNamePriority+"%");             
                rs = stm.executeQuery();
                while (rs.next()) { 
                    int permissionID = rs.getInt("permissionID");
                    list.add(permissionID);
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
    
    public boolean updatePermission(PermissionDTO permission, int roleID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PERMISSION);
                ptm.setNString(1, permission.getPermissionName());
                ptm.setInt(2, roleID);
                ptm.setInt(3, permission.getPermissionID());
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
    
    public ArrayList<Integer> getListAvailablePermission() throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_AVAILABLE_PERMISSION);
                rs = stm.executeQuery();
                while (rs.next()) { 
                    int permissionID = rs.getInt("permissionID") ;
                    list.add(permissionID);
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
