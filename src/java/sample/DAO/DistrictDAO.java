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
    private static final String GET_DISTRICT_LIST ="SELECT districtID, districtName FROM tblDistrict WHERE districtName LIKE ?";
    
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
}
