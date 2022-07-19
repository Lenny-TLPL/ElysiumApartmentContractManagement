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
import sample.DTO.ApartmentTypeDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Phi Long
 */
public class ApartmentTypeDAO {

    private static final String GET_TYPE_LIST = "SELECT typeID, typeName, buyingPrice, leasingPrice, description FROM tblApartmentType\n"
            + "	WHERE typeName LIKE ? OR description LIKE ?";
    private static final String CHECK_DUPLICATE = "SELECT typeID, typeName, buyingPrice, leasingPrice, description FROM tblApartmentType\n"
            + "	WHERE typeName LIKE ? ";
    private static final String ADD_NEW_APARTMENT_TYPE="INSERT INTO tblApartmentType (typeName, buyingPrice, leasingPrice, description) VALUES (?, ?, ?, ?)";
    
    public ArrayList<ApartmentTypeDTO> getApartmentTypeList(String search) throws SQLException {
        ArrayList<ApartmentTypeDTO> list = new ArrayList<>();
        ApartmentTypeDTO apartmentType = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_TYPE_LIST);
                stm.setNString(1, "%" + search + "%");
                stm.setNString(2, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int typeID = rs.getInt("typeID");
                    String typeName = rs.getNString("typeName");
                    String description = rs.getNString("description");
                    float buyingPrice = rs.getFloat("buyingPrice");
                    float leasingPrice = rs.getFloat("leasingPrice");
                    apartmentType = new ApartmentTypeDTO(typeID, typeName, buyingPrice, leasingPrice, description);
                    list.add(apartmentType);
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
    
    public boolean checkDuplicate(String typeName) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CHECK_DUPLICATE);
                stm.setNString(1, typeName);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
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
    
    public boolean addApartmentType(String typeName, float buyingPrice, float leasingPrice, String description ) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_NEW_APARTMENT_TYPE);
                ptm.setNString(1, typeName);
                ptm.setFloat(2, buyingPrice);
                ptm.setFloat(3, leasingPrice);
                ptm.setNString(4, description);
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
