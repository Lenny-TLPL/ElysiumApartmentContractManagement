/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sample.DTO.ApartmentDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Phi Long
 */
public class ApartmentDAO {
    private static final String GET_APARTMENT_WITH_GIVEN_STATUS="SELECT * FROM tblApartment WHERE apartmentID LIKE ? AND apartmentStatus LIKE ?";
    
    public ApartmentDTO getApartment(String apartmentID, String apartmentStatus) throws SQLException {
        ApartmentDTO apartment= null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_APARTMENT_WITH_GIVEN_STATUS);
                stm.setString(1, "%"+apartmentID+"%");
                stm.setString(2, "%"+apartmentStatus+"%");
                rs = stm.executeQuery();
                while (rs.next()) { 
                    int typeID = rs.getInt("typeID");   
                    int floor = rs.getInt("floor");   
                    int buildingID = rs.getInt("buildingID");   
                    String userID = rs.getString("userID");
                    
                    apartment=new ApartmentDTO(apartmentID, apartmentStatus, typeID, floor, buildingID, userID);
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
}

