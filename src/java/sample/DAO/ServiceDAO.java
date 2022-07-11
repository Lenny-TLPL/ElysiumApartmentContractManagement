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
    private static final String GET_SERVICE_BY_NAME="SELECT serviceID, serviceName, description, price, status FROM tblService WHERE serviceName LIKE ?";
    private static final String GET_SERVICE_BY_ID="SELECT serviceID, serviceName, description, price, status FROM tblService WHERE serviceID = ?";
    private static final String ADD_NEW_SERVICE="INSERT INTO tblService (serviceName, status, description, price) VALUES (?, ?, ?, ?) ";
    private static final String UPDATE_SERVICE_STATUS="UPDATE tblService SET status = ? WHERE serviceID = ?";
    private static final String UDDATE_SERVICE="UPDATE tblService SET serviceName = ?, description = ?, price = ?, status = ? WHERE serviceID = ?";
    
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
                stm.setNString(1, "%"+search+"%");             
                rs = stm.executeQuery();
                while (rs.next()) { 
                    int serviceID = rs.getInt("serviceID");
                    String serviceName = rs.getNString("serviceName") ;
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
    
    public ServiceDTO getServiceByName(String search) throws SQLException {
        ServiceDTO service = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_SERVICE_BY_NAME);
                stm.setNString(1, "%"+search+"%");             
                rs = stm.executeQuery();
                if (rs.next()) { 
                    int serviceID = rs.getInt("serviceID");
                    String serviceName = rs.getNString("serviceName") ;
                    float price = rs.getFloat("price");
                    boolean status = rs.getBoolean("status");
                    String description = rs.getNString("description");
                    service = new ServiceDTO(serviceID, serviceName, description , price, status);
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
        return service;
    }
    
    public boolean addService(ServiceDTO service) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_NEW_SERVICE);
                ptm.setNString(1, service.getServiceName());
                ptm.setBoolean(2, service.isStatus());
                ptm.setNString(3, service.getDescription());
                ptm.setFloat(4, service.getPrice());
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
    
    public ServiceDTO getServiceByID(int serviceID) throws SQLException {
        ServiceDTO service = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_SERVICE_BY_ID);
                stm.setInt(1, serviceID);             
                rs = stm.executeQuery();
                if (rs.next()) { 
                    String serviceName = rs.getNString("serviceName") ;
                    float price = rs.getFloat("price");
                    boolean status = rs.getBoolean("status");
                    String description = rs.getNString("description");
                    service = new ServiceDTO(serviceID, serviceName, description , price, status);
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
        return service;
    }
    
    public boolean updateServiceStatus(int serviceID, boolean status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_SERVICE_STATUS);
                ptm.setBoolean(1, status);
                ptm.setInt(2, serviceID);

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
    
    public boolean updateService(ServiceDTO service) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UDDATE_SERVICE);
                ptm.setNString(1, service.getServiceName());
                ptm.setBoolean(4, service.isStatus());
                ptm.setNString(2, service.getDescription());
                ptm.setFloat(3, service.getPrice());
                ptm.setInt(5, service.getServiceID());
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
