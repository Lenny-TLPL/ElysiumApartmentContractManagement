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
import sample.DTO.MonthlyFeeDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Phi Long
 */
public class MonthlyFeeDAO {

    private static final String GET_MONTHLY_FEE_DETAIL_WITH_STATUS = "SELECT * FROM tblMonthlyFee WHERE userID LIKE ? AND apartmentID LIKE ? AND status = ? ";
    private static final String ADD_MONTHLY_FEE = "INSERT INTO tblMonthlyFee(userID, apartmentID, status) VALUES (?, ?, 1)";
    private static final String GET_MONTHLY_FEE_DETAIL_WITH_USERID_APARTMENTID = "SELECT * FROM tblMonthlyFee WHERE userID LIKE ?   OR apartmentID LIKE ?";
    private static final String MONTHLY_FEE_CALCULATE = "EXEC monthlyFeeCalculate ?";

    public boolean addMonthly(String userID, String apartmentID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_MONTHLY_FEE);
                ptm.setString(1, userID);
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

    public MonthlyFeeDTO getMonthlyFeeDetail(String userID, String apartmentID, Boolean status) throws SQLException {
        MonthlyFeeDTO monthlyFee = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_MONTHLY_FEE_DETAIL_WITH_STATUS);
                stm.setString(1, userID);
                stm.setString(2, apartmentID);
                stm.setBoolean(3, status);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int monthlyFeeID = rs.getInt("monthlyFeeID");
                    float waterFee = rs.getFloat("waterFee");
                    float electricityFee = rs.getFloat("electricityFee");
                    float contractFee = rs.getFloat("contractFee");
                    float serviceFee = rs.getFloat("serviceFee");
                    monthlyFee = new MonthlyFeeDTO(monthlyFeeID, userID, apartmentID, waterFee, electricityFee, contractFee, serviceFee, status);
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
        return monthlyFee;
    }

    public ArrayList<MonthlyFeeDTO> getMonthlyFeeList(String search) throws SQLException {
        ArrayList<MonthlyFeeDTO> list = new ArrayList<>();
        MonthlyFeeDTO monthlyFee = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_MONTHLY_FEE_DETAIL_WITH_USERID_APARTMENTID);
                stm.setString(1, "%" + search + "%");
                stm.setString(2, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String apartmentID = rs.getString("apartmentID");
                    int monthlyFeeID = rs.getInt("monthlyFeeID");
                    float waterFee = rs.getFloat("waterFee");
                    float electricityFee = rs.getFloat("electricityFee");
                    float contractFee = rs.getFloat("contractFee");
                    float serviceFee = rs.getFloat("serviceFee");
                    Boolean status = rs.getBoolean("status");
                    monthlyFee = new MonthlyFeeDTO(monthlyFeeID, userID, apartmentID, waterFee, electricityFee, contractFee, serviceFee, status);
                    list.add(monthlyFee);
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

    public boolean monthlyFeeCalculate(String apartmentID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(MONTHLY_FEE_CALCULATE);
                ptm.setString(1, apartmentID);
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
