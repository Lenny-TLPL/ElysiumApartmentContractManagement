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
import sample.DTO.ApartmentDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Phi Long
 */
public class ApartmentDAO {

//    private static final String GET_APARTMENT_WITH_GIVEN_STATUS = "SELECT * FROM tblApartment WHERE apartmentID LIKE ? AND apartmentStatus LIKE ?";
    private static final String GET_APARTMENT_DETAIL = "SELECT apartmentID, area, apartmentStatus, typeName, floor, buildingName, userID FROM tblApartment a \n"
            + "	INNER JOIN tblApartmentBuilding b ON a.buildingID=b.buildingID \n"
            + "	INNER JOIN tblDistrict d ON d.districtID = b.districtID\n"
            + "	INNER JOIN tblApartmentType t ON t.typeID = a.typeID\n"
            + "	WHERE apartmentID LIKE ? ";
    private static final String GET_LIST_APARTMENT = "SELECT apartmentID, area, apartmentStatus, typeName, floor, buildingName, userID FROM tblApartment a \n"
            + "	INNER JOIN tblApartmentBuilding b ON a.buildingID=b.buildingID \n"
            + "	INNER JOIN tblApartmentType t ON t.typeID = a.typeID\n"
            + "	WHERE apartmentID LIKE ? OR userID LIKE ? OR apartmentStatus LIKE ? OR typeName LIKE ? OR buildingName LIKE ? \n";
//    private static final String GET_APARTMENT_STATUS="SELECT apartmentStatus FROM tblApartment WHERE apartmentID LIKE ? ";
    private static final String UPDATE_APARTMENT_STATUS="UPDATE tblApartment SET apartmentStatus = ? WHERE apartmentID COLLATE SQL_Latin1_General_CP1_CS_AS = ? "; 
    private static final String ADD_APARTMENT="EXEC addApartment ?, ?, ?, ?, ?";
    private static final String GET_LIST_APARTMENT_IN_A_BUILDING = "SELECT apartmentID, area, apartmentStatus, typeName, floor, buildingName, userID FROM tblApartment a \n"
            + "	INNER JOIN tblApartmentBuilding b ON a.buildingID=b.buildingID \n"
            + "	INNER JOIN tblApartmentType t ON t.typeID = a.typeID\n"
            + "	WHERE a.buildingID = ? \n";
    private static final String GET_LIST_USER_APARTMENT = "SELECT apartmentID, area, apartmentStatus, typeName, floor, buildingName, userID FROM tblApartment a \n"
            + "	INNER JOIN tblApartmentBuilding b ON a.buildingID=b.buildingID \n"
            + "	INNER JOIN tblApartmentType t ON t.typeID = a.typeID\n"
            + "	WHERE (apartmentID LIKE ? OR typeName LIKE ? OR buildingName LIKE ?) AND userID LIKE ?  AND apartmentStatus LIKE 'rented'\n";
    
    public ApartmentDTO getApartment(String apartmentID) throws SQLException {
        ApartmentDTO apartment = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_APARTMENT_DETAIL);
                stm.setString(1, "%" + apartmentID + "%");
                rs = stm.executeQuery();
                if (rs.next()) {
                    String apartmentStatus = rs.getString("apartmentStatus");
                    String typeName = rs.getNString("typeName");
                    int floor = rs.getInt("floor");
                    String buildingName = rs.getNString("buildingName");
                    String userID = rs.getString("userID");
                    float area = rs.getFloat("area");
                    apartment = new ApartmentDTO(apartmentID, area, apartmentStatus, typeName, floor, buildingName, userID);
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
        return apartment;
    }

    public ArrayList<ApartmentDTO> getApartmentList(String search) throws SQLException {
        ArrayList<ApartmentDTO> list = new ArrayList<>();
        ApartmentDTO apartment = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_APARTMENT);
                stm.setString(1, "%" + search + "%");
                stm.setString(2, "%" + search + "%");
                stm.setString(3, "%" + search + "%");
                stm.setNString(4, "%" + search + "%");
                stm.setNString(5, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String apartmentID = rs.getString("apartmentID");
                    String apartmentStatus = rs.getString("apartmentStatus");
                    String typeName = rs.getNString("typeName");
                    int floor = rs.getInt("floor");
                    String buildingName = rs.getNString("buildingName");
                    String userID = rs.getString("userID");
                    float area = rs.getFloat("area");
                    apartment = new ApartmentDTO(apartmentID, area, apartmentStatus, typeName, floor, buildingName, userID);
                    list.add(apartment);
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
//    public String getApartmentStatus(String apartmentID) throws SQLException {
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        String status = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                stm = conn.prepareStatement(GET_APARTMENT_STATUS);
//                stm.setString(1, "%"+apartmentID+"%");
//                rs = stm.executeQuery();
//                if (rs.next()) { 
//                    status = rs.getString("apartmentStatus");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return status;
//    }
    public boolean updateApartmentStatus(String apartmentID, String apartmentStatus) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_APARTMENT_STATUS);
                ptm.setString(1, apartmentStatus);
                ptm.setString(2, apartmentID);

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
    
    public boolean addApartment(String apartmentStatus , String typeName, int floor, String buildingName, float area) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_APARTMENT);
                ptm.setString(1, apartmentStatus);
                ptm.setNString(2, typeName);
                ptm.setInt(3, floor);
                ptm.setNString(4, buildingName);
                ptm.setFloat(5, area);
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

    public ArrayList<ApartmentDTO> getApartmentListInABuilding(int buildingID) throws SQLException {
        ArrayList<ApartmentDTO> list = new ArrayList<>();
        ApartmentDTO apartment = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_APARTMENT_IN_A_BUILDING);
                stm.setInt(1, buildingID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String apartmentID = rs.getString("apartmentID");
                    String apartmentStatus = rs.getString("apartmentStatus");
                    String typeName = rs.getNString("typeName");
                    int floor = rs.getInt("floor");
                    String userID = rs.getString("userID");
                    String buildingName = rs.getNString("buildingName");
                    float area = rs.getFloat("area");
                    apartment = new ApartmentDTO(apartmentID, area, apartmentStatus, typeName, floor, buildingName, userID);
                    list.add(apartment);
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
    
    public ArrayList<ApartmentDTO> getUserApartmentList(String search, String userID) throws SQLException {
        ArrayList<ApartmentDTO> list = new ArrayList<>();
        ApartmentDTO apartment = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_USER_APARTMENT);
                stm.setString(1, "%" + search + "%");
                stm.setString(4, userID);
                stm.setNString(2, "%" + search + "%");
                stm.setNString(3, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String apartmentID = rs.getString("apartmentID");
                    String apartmentStatus = rs.getString("apartmentStatus");
                    String typeName = rs.getNString("typeName");
                    int floor = rs.getInt("floor");
                    String buildingName = rs.getNString("buildingName");
                    float area = rs.getFloat("area");
                    apartment = new ApartmentDTO(apartmentID, area, apartmentStatus, typeName, floor, buildingName, userID);
                    list.add(apartment);
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
