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
import sample.DTO.ApartmentBuildingDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Phi Long
 */
public class ApartmentBuildingDAO {

    private static final String GET_LIST_APARTMENT_BUILDING = "	SELECT buildingID, buildingName, districtName, status, maxFloor, maxApartment FROM tblApartmentBuilding b \n"
            + "	INNER JOIN tblDistrict d ON d.districtID = b.districtID\n"
            + "	WHERE districtName LIKE ? OR buildingName LIKE ?";
    private static final String UPDATE_BUILDING_STATUS="UPDATE tblApartmentBuilding SET status = ? WHERE buildingID = ? ";
    private static final String CHECK_DUPLICATE="SELECT buildingID FROM tblApartmentBuilding WHERE buildingName like ? AND districtID = ?";
    private static final String ADD_NEW_BUILDING="INSERT INTO tblApartmentBuilding(buildingName, districtID, status, maxFloor, maxApartment)"
            + " VALUES(?, ?, ?, ?, ?)";
    private static final String GET_BUILDING="SELECT buildingID, buildingName, districtName, status, maxFloor, maxApartment FROM tblApartmentBuilding b INNER JOIN tblDistrict d ON d.districtID = b.districtID WHERE buildingName LIKE ? ";
    
    public ArrayList<ApartmentBuildingDTO> getApartmentBuildingList(String search) throws SQLException {
        ArrayList<ApartmentBuildingDTO> list = new ArrayList<>();
        ApartmentBuildingDTO apartmentBuilding = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_APARTMENT_BUILDING);
                stm.setNString(1, "%" + search + "%");
                stm.setNString(2, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int buildingID = rs.getInt("buildingID");
                    Boolean status = rs.getBoolean("status");
                    String districtName = rs.getNString("districtName");
                    String buildingName = rs.getNString("buildingName");
                    int maxFloor = rs.getInt("maxFloor");
                    int maxApartment = rs.getInt("maxApartment");
                    apartmentBuilding = new ApartmentBuildingDTO(buildingID, buildingName, districtName, status, maxFloor, maxApartment);
                    list.add(apartmentBuilding);
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
    
    public boolean updateApartmentBuildingStatus(int buildingID, boolean status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_BUILDING_STATUS);
                ptm.setBoolean(1, status);
                ptm.setInt(2, buildingID);

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
    
    public boolean checkDuplicate(String buildingName, int districtID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setNString(1, buildingName);
                ptm.setInt(2, districtID);
                rs=ptm.executeQuery();
                if(rs.next()){
                    return true;
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
        return false;
    }
    
    public boolean addApartmentBuilding(String buildingName, int districtID, int maxFloor, int maxApartment) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_NEW_BUILDING);
                ptm.setNString(1, buildingName);
                ptm.setInt(2, districtID);
                ptm.setBoolean(3, true);
                ptm.setInt(4, maxFloor);
                ptm.setInt(5, maxApartment);
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
    
    public ApartmentBuildingDTO getApartmentBuilding(String buildingName) throws SQLException {
        ApartmentBuildingDTO apartmentBuilding = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_BUILDING);
                stm.setNString(1, buildingName);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int buildingID = rs.getInt("buildingID");
                    Boolean status = rs.getBoolean("status");
                    String districtName = rs.getNString("districtName");
                    int maxFloor = rs.getInt("maxFloor");
                    int maxApartment = rs.getInt("maxApartment");
                    apartmentBuilding = new ApartmentBuildingDTO(buildingID, buildingName, districtName, status, maxFloor, maxApartment);
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
        return apartmentBuilding;
    }
}
