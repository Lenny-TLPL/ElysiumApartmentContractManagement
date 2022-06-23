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
    private static final String GET_LIST_BILLING_HISTORY="SELECT * FROM tblBillingHistory WHERE billName LIKE ? OR userID LIKE ?";
    
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
                rs = stm.executeQuery();
                while (rs.next()) { 
                    int billID = rs.getInt("serviceID");
                    String billName = rs.getString("serviceName") ;
                    Date payDate = rs.getDate("payDate");
                    float value = rs.getFloat("value");
                    String userID = rs.getString("userID");
                    billingHistory = new BillingHistoryDTO(billID, billName, payDate, userID, value);
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
