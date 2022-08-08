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
import sample.DTO.BillingHistoryDTO;
import sample.DTO.MonthlyFeeDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Phi Long
 */
public class MonthlyFeeDAO {
    private static final String GET_MONTHLY_FEE_DETAIL_WITH_STATUS = "SELECT * FROM tblMonthlyFee WHERE userID LIKE ? AND apartmentID LIKE ? AND status = ? ";
    private static final String ADD_MONTHLY_FEE = "INSERT INTO tblMonthlyFee(userID, apartmentID, status) VALUES (?, ?, 1)";
    private static final String GET_MONTHLY_FEE_DETAIL_WITH_USERID_APARTMENTID = "SELECT * FROM tblMonthlyFee WHERE userID LIKE ? OR apartmentID LIKE ?";
    private static final String MONTHLY_FEE_CALCULATE = "EXEC monthlyFeeCalculate ?";
    private static final String GET_LIST_USER_MAIL_FOR_MONTHLY_MAILING = "SELECT DISTINCT u.email FROM tblUser u INNER JOIN tblMonthlyFee m ON u.userID = m.userID";
    private static final String GET_LIST_USER_ID_FOR_MONTHLY_MAILING = "SELECT DISTINCT u.userID FROM tblUser u INNER JOIN tblMonthlyFee m ON u.userID = m.userID";
    private static final String GET_USER_MONTHLY_FEE_DETAIL = "SELECT * FROM tblMonthlyFee WHERE userID LIKE ? AND apartmentID LIKE ? ";
    private static final String UPDATE_MONTHLY_FEE_CONTRACT = "UPDATE tblMonthlyFee SET contractFee = ? WHERE userID = ? AND apartmentID = ?";
    private static final String UPDATE_MONTHLY_FEE_SERVICE = "UPDATE tblMonthlyFee SET serviceFee = ? WHERE userID = ? AND apartmentID = ?";
    private static final String UPDATE_MONTHLY_FEE_WATER = "UPDATE tblMonthlyFee SET waterFee = ? WHERE userID = ? AND apartmentID = ?";
    private static final String UPDATE_MONTHLY_FEE_ELECTRICITY = "UPDATE tblMonthlyFee SET electricityFee = ? WHERE userID = ? AND apartmentID = ?";
    private static final String UPDATE_MONTHLY_FEE_STATUS = "UPDATE tblMonthlyFee SET status = ? WHERE userID = ? AND apartmentID = ?";
    private static final String GET_ACTIVE_MONTHLY_FEE_APARTMENTID_WITH_USERID_APARTMENTID = "SELECT apartmentID FROM tblMonthlyFee WHERE status = 1";
    private static final String UPDATE_MONTHLY_FEE = "UPDATE tblMonthlyFee SET contractFee = ?, serviceFee = ?, waterFee = ?, electricityFee = ? WHERE monthlyFeeID = ?";
    
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

    public ArrayList<String> getUserMailForMonthlyMailing() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_USER_MAIL_FOR_MONTHLY_MAILING);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String email = rs.getNString("email");
                    list.add(email);
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

    public ArrayList<String> getUserIDForMonthlyMailing() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_USER_ID_FOR_MONTHLY_MAILING);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    list.add(userID);
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

    public ArrayList<MonthlyFeeDTO> getUserMonthlyFeeList(String userID, String search) throws SQLException {
        ArrayList<MonthlyFeeDTO> list = new ArrayList<>();
        MonthlyFeeDTO monthlyFee = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_USER_MONTHLY_FEE_DETAIL);
                stm.setString(1, userID);
                stm.setString(2, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String apartmentID = rs.getString("apartmentID");
                    int monthlyFeeID = rs.getInt("monthlyFeeID");
                    float waterFee = rs.getFloat("waterFee");
                    float electricityFee = rs.getFloat("electricityFee");
                    float contractFee = rs.getFloat("contractFee");
                    float serviceFee = rs.getFloat("serviceFee");
                    Boolean status = rs.getBoolean("status");
                    if (waterFee != 0 || contractFee != 0 || serviceFee != 0 || electricityFee != 0) {
                        monthlyFee = new MonthlyFeeDTO(monthlyFeeID, userID, apartmentID, waterFee, electricityFee, contractFee, serviceFee, status);
                        list.add(monthlyFee);
                    }

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

    public MonthlyFeeDTO getUserMonthlyFeeDetail(String userID, String apartmentID) throws SQLException {
        MonthlyFeeDTO monthlyFee = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_USER_MONTHLY_FEE_DETAIL);
                stm.setString(1, userID);
                stm.setString(2, "%" + apartmentID + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int monthlyFeeID = rs.getInt("monthlyFeeID");
                    float waterFee = rs.getFloat("waterFee");
                    float electricityFee = rs.getFloat("electricityFee");
                    float contractFee = rs.getFloat("contractFee");
                    float serviceFee = rs.getFloat("serviceFee");
                    Boolean status = rs.getBoolean("status");
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
    
    public boolean updateMonthlyFee(String userID, String apartmentID, BillingHistoryDTO bill) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        MonthlyFeeDTO monthlyFee = getUserMonthlyFeeDetail(userID, apartmentID);
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if(bill.getContractFee()>-1){
                    ptm = conn.prepareStatement(UPDATE_MONTHLY_FEE_CONTRACT);

                    monthlyFee.setContractFee(monthlyFee.getContractFee()-(bill.getReceivedValue()-bill.getChange()));
                    float a = monthlyFee.getContractFee();
                    ptm.setFloat(1, a);
                }else if(bill.getServiceFee()>-1) {
                    ptm = conn.prepareStatement(UPDATE_MONTHLY_FEE_SERVICE);
                    monthlyFee.setServiceFee(monthlyFee.getServiceFee()-(bill.getReceivedValue()-bill.getChange()));
                    ptm.setFloat(1, monthlyFee.getServiceFee());
                }else if(bill.getWaterFee()>-1){
                    ptm = conn.prepareStatement(UPDATE_MONTHLY_FEE_WATER);
                    monthlyFee.setWaterFee(monthlyFee.getWaterFee()-(bill.getReceivedValue()-bill.getChange()));
                    ptm.setFloat(1, monthlyFee.getWaterFee());
                }else if (bill.getElectricityFee()>-1){
                    ptm = conn.prepareStatement(UPDATE_MONTHLY_FEE_ELECTRICITY);
                    monthlyFee.setElectricityFee(monthlyFee.getElectricityFee()-(bill.getReceivedValue()-bill.getChange()));
                    ptm.setFloat(1, monthlyFee.getElectricityFee());
                }
                ptm.setString(2, userID);
                ptm.setString(3, apartmentID);
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
    
    public boolean updateMonthlyFee(String userID, String apartmentID, boolean status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_MONTHLY_FEE_STATUS);
                ptm.setBoolean(1, status);
                ptm.setString(2, userID);
                ptm.setString(3, apartmentID);
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
    
    public ArrayList<String> getActiveMonthlyFeeApartmentIDList() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ACTIVE_MONTHLY_FEE_APARTMENTID_WITH_USERID_APARTMENTID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String apartmentID = rs.getString("apartmentID");
                    list.add(apartmentID);
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
    
    public boolean updateMonthlyFee(float contractFee, float serviceFee, float waterFee, float electricityFee, int monthlyFeeID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_MONTHLY_FEE);
                ptm.setFloat(1, contractFee);
                ptm.setFloat(2, serviceFee);
                ptm.setFloat(3, waterFee);
                ptm.setFloat(4, electricityFee);
                ptm.setInt(5, monthlyFeeID);
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
