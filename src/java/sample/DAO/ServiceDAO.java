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
import sample.DTO.ServiceDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Phi Long
 */
public class ServiceDAO {
    private static final String GET_LIST_SERVICE="SELECT serviceID, serviceName, price, status FROM tblService WHERE serviceName LIKE ?";
    
    public ArrayList<ServiceDTO> getListService(String search) throws SQLException {
        ArrayList<ServiceDTO> list = new ArrayList<>();
        ServiceDTO service = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_SERVICE);
                stm.setString(1, "%"+search+"%");             
                rs = stm.executeQuery();
                while (rs.next()) { 
                    int serviceID = rs.getInt("serviceID");
                    String serviceName = rs.getString("serviceName") ;
                    float price = rs.getFloat("price");
                    boolean status = rs.getBoolean("status");  
                    service = new ServiceDTO(serviceID, serviceName,"" , price, status);
                    list.add(service);
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
