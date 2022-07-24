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
    private static final String GET_LIST_BILLING_HISTORY="SELECT * FROM tblBillingHistory WHERE billName LIKE ? OR userID LIKE ? OR apartmentID LIKE ?";
    
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
                stm.setInt(1, Integer.parseInt(search));   
                stm.setString(2, "%"+search+"%"); 
                stm.setString(3, "%"+search+"%"); 
                rs = stm.executeQuery();
                while (rs.next()) { 
                    int billID = rs.getInt("serviceID");
                    String billName = rs.getNString("serviceName") ;
                    Date payDate = rs.getDate("payDate");
                    float value = rs.getFloat("value");
                    String userID = rs.getString("userID");
                    String apartmentID = rs.getString("apartmentID");
                    float receivedValue = rs.getFloat("receivedValue");
                    float change = rs.getFloat("change");
                    billingHistory = new BillingHistoryDTO(billID, billName, payDate, userID, apartmentID, value, receivedValue, change);
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
