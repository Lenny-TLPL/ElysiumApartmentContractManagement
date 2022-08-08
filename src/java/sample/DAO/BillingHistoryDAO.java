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
import java.util.Date;
import sample.DTO.BillingHistoryDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Phi Long
 */
public class BillingHistoryDAO {
    private static final String GET_LIST_BILLING_HISTORY="SELECT * FROM tblBillingHistory WHERE  userID LIKE ? OR apartmentID LIKE ?";
    private static final String ADD_BILLING_HISTORY="INSERT INTO tblBillingHistory (userID, apartmentID, payDate, contractFee, serviceFee, electricityFee, waterFee, otherFee, receivedValue, change) VALUES(?, ?, GETDATE(), ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_LIST_USER_BILLING_HISTORY="SELECT * FROM tblBillingHistory WHERE  userID LIKE ? AND apartmentID LIKE ?";
    
    public ArrayList<BillingHistoryDTO> getListBilling(String search) throws SQLException {
        ArrayList<BillingHistoryDTO> list = new ArrayList<>();
        BillingHistoryDTO billingHistory = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_BILLING_HISTORY);
                stm.setString(1, "%"+search+"%"); 
                stm.setString(2, "%"+search+"%"); 
                rs = stm.executeQuery();
                while (rs.next()) { 
                    int billID = rs.getInt("billID");
                    Date payDate = rs.getDate("payDate");
                    float contractFee = rs.getFloat("contractFee");
                    float serviceFee = rs.getFloat("serviceFee");
                    float electricityFee = rs.getFloat("electricityFee");
                    float waterFee = rs.getFloat("waterFee");
                    float otherFee = rs.getFloat("otherFee");                    
                    String userID = rs.getString("userID");
                    String apartmentID = rs.getString("apartmentID");
                    float receivedValue = rs.getFloat("receivedValue");
                    float change = rs.getFloat("change");
                    billingHistory = new BillingHistoryDTO(billID, payDate, userID, apartmentID, contractFee, serviceFee, waterFee, electricityFee, otherFee, receivedValue, change);
                    list.add(billingHistory);
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
    
    public boolean addBillingHistory(BillingHistoryDTO billing) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_BILLING_HISTORY);
                ptm.setString(1, billing.getUserID());
                ptm.setString(2, billing.getApartmentID());
                if(billing.getContractFee()==-1){
                    ptm.setFloat(3,0);
                }else{
                    ptm.setFloat(3, billing.getContractFee());
                }
                if( billing.getServiceFee()==-1){
                    ptm.setFloat(4,0);
                }else{
                    ptm.setFloat(4, billing.getServiceFee());
                }
                if(billing.getElectricityFee()==-1){
                    ptm.setFloat(5,0);
                }else{
                    ptm.setFloat(5, billing.getElectricityFee());
                }
                if(billing.getWaterFee()==-1){
                    ptm.setFloat(6,0);
                }else{
                    ptm.setFloat(6, billing.getWaterFee());
                }
                if( billing.getOtherFee()==-1){
                    ptm.setFloat(7,0);
                }else{
                    ptm.setFloat(7, billing.getOtherFee());
                }
                if( billing.getChange()<=-0){
                    ptm.setFloat(9,0);
                }else{
                    ptm.setFloat(9, billing.getChange());
                }
                ptm.setFloat(8, billing.getReceivedValue());
                
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
    
    public ArrayList<BillingHistoryDTO> getListBilling(String userID, String search) throws SQLException {
        ArrayList<BillingHistoryDTO> list = new ArrayList<>();
        BillingHistoryDTO billingHistory = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_USER_BILLING_HISTORY);
                stm.setString(1, userID); 
                stm.setString(2, "%"+search+"%"); 
                rs = stm.executeQuery();
                while (rs.next()) { 
                    int billID = rs.getInt("billID");
                    Date payDate = rs.getDate("payDate");
                    float contractFee = rs.getFloat("contractFee");
                    float serviceFee = rs.getFloat("serviceFee");
                    float electricityFee = rs.getFloat("electricityFee");
                    float waterFee = rs.getFloat("waterFee");
                    float otherFee = rs.getFloat("otherFee");                    
                    String apartmentID = rs.getString("apartmentID");
                    float receivedValue = rs.getFloat("receivedValue");
                    float change = rs.getFloat("change");
                    billingHistory = new BillingHistoryDTO(billID, payDate, userID, apartmentID, contractFee, serviceFee, waterFee, electricityFee, otherFee, receivedValue, change);
                    list.add(billingHistory);
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
