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
    private static final String GET_LIST_PERMISSION = "	SELECT * FROM tblPermission WHERE permissionName LIKE ?";
    
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
                rs = stm.executeQuery();
                while (rs.next()) { 
                    int permissionID = rs.getInt("permissionID");
                    String permissionName = rs.getString("permissionName") ;
                    boolean status = rs.getBoolean("status");  
                    permission = new PermissionDTO(permissionID, permissionName, status);
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
