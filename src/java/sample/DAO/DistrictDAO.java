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
import sample.DTO.DistrictDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Phi Long
 */
public class DistrictDAO {

    private static final String GET_DISTRICT_LIST = "SELECT districtID, districtName FROM tblDistrict WHERE districtName LIKE ?";
    private static final String ADD_NEW_DISTRICT = "INSERT INTO tblDistrict(districtName) VALUES(?)";
    private static final String CHECK_DUPLICATE = "SELECT districtID FROM tblDistrict WHERE districtName LIKE ?";
    private static final String UPDATE_DISTRICT = "UPDATE tblDistrict SET districtName = ? WHERE districtID = ?";
    
    public ArrayList<DistrictDTO> getDistrictList(String search) throws SQLException {
        ArrayList<DistrictDTO> list = new ArrayList<>();
        DistrictDTO district = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_DISTRICT_LIST);
                stm.setNString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int districtID = rs.getInt("districtID");
                    String districtName = rs.getNString("districtName");
                    district = new DistrictDTO(districtID, districtName);
                    list.add(district);
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

    public boolean addDistrict(String districtName) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_NEW_DISTRICT);
                ptm.setNString(1, districtName);
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

    public boolean checkDuplicate(String districtName) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CHECK_DUPLICATE);
                stm.setNString(1, districtName);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int districtID = rs.getInt("districtID");
                    if (districtID >0) {
                        return true;
                    }
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
        return false;
    }
    
    public boolean checkDuplicate(String districtName, int districtID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CHECK_DUPLICATE);
                stm.setNString(1, districtName);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("districtID");
                    if (districtID != id) {
                        return true;
                    }
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
        return false;
    }
    
    public boolean updateDistrict(String districtName, int districtID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_DISTRICT);
                ptm.setNString(1, districtName);
                ptm.setInt(2,districtID);
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
