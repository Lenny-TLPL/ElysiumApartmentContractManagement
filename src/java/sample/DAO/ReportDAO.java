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
import sample.utils.DBUtils;

/**
 *
 * @author Phi Long
 */
public class ReportDAO {
    //human resource
    private static final String GET_USER_QUANTITY = "SELECT COALESCE(count(tblUser.userID),0) AS quantity FROM tblUser WHERE tblUser.status = 1";
    private static final String GET_BOARD_MANAGER_QUANTITY = "SELECT COALESCE(count(tblUser.userID),0) AS quantity FROM tblUser INNER JOIN tblRole ON tblUser.roleID = tblRole.roleID WHERE tblUser.roleID = 1 AND tblUser.status = 1";
    private static final String GET_HR_MANAGER_QUANTITY = "SELECT COALESCE(count(tblUser.userID),0) AS quantity FROM tblUser INNER JOIN tblRole ON tblUser.roleID = tblRole.roleID WHERE tblUser.roleID = 2 AND tblUser.status = 1";
    private static final String GET_EMPLOYEE_QUANTITY = "SELECT COALESCE(count(tblUser.userID),0) AS quantity FROM tblUser INNER JOIN tblRole ON tblUser.roleID = tblRole.roleID WHERE tblUser.roleID = 3 AND tblUser.status = 1";
    private static final String GET_RESIDENT_QUANTITY = "SELECT COALESCE(count(tblUser.userID),0) AS quantity FROM tblUser INNER JOIN tblRole ON tblUser.roleID = tblRole.roleID WHERE tblUser.roleID = 4 AND tblUser.status = 1";
    private static final String GET_CUSTOMER_QUANTITY = "SELECT COALESCE(count(tblUser.userID),0) AS quantity FROM tblUser INNER JOIN tblRole ON tblUser.roleID = tblRole.roleID WHERE tblUser.roleID = 5 AND tblUser.status = 1";
    //apartment
    private static final String GET_TOTAL_APARTMENT="SELECT COALESCE(count(apartmentID),0) AS quantity FROM tblApartment";
    private static final String GET_TOTAL_AVAILABLE_APARTMENT="SELECT COALESCE(count(apartmentID),0) AS quantity FROM tblApartment WHERE apartmentStatus LIKE 'available' ";
    private static final String GET_TOTAL_RENTED_APARTMENT="SELECT COALESCE(count(apartmentID),0) AS quantity FROM tblApartment WHERE apartmentStatus LIKE 'rented' ";
    private static final String GET_TOTAL_MAINTENACE_APARTMENT="SELECT COALESCE(count(apartmentID),0) AS quantity FROM tblApartment WHERE apartmentStatus LIKE 'maintenance' ";
    //contract
    private static final String GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR="SELECT COALESCE(count(contractID),0) AS quantity FROM tblContract WHERE YEAR(dateSign)=YEAR(GETDATE())";
    private static final String GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_1="SELECT COALESCE(count(contractID),0) AS quantity FROM tblContract WHERE YEAR(dateSign)=YEAR(GETDATE()) AND MONTH(dateSign) = 1";
    private static final String GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_2="SELECT COALESCE(count(contractID),0) AS quantity FROM tblContract WHERE YEAR(dateSign)=YEAR(GETDATE()) AND MONTH(dateSign) = 2";
    private static final String GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_3="SELECT COALESCE(count(contractID),0) AS quantity FROM tblContract WHERE YEAR(dateSign)=YEAR(GETDATE()) AND MONTH(dateSign) = 3";
    private static final String GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_4="SELECT COALESCE(count(contractID),0) AS quantity FROM tblContract WHERE YEAR(dateSign)=YEAR(GETDATE()) AND MONTH(dateSign) = 4";
    private static final String GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_5="SELECT COALESCE(count(contractID),0) AS quantity FROM tblContract WHERE YEAR(dateSign)=YEAR(GETDATE()) AND MONTH(dateSign) = 5";
    private static final String GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_6="SELECT COALESCE(count(contractID),0) AS quantity FROM tblContract WHERE YEAR(dateSign)=YEAR(GETDATE()) AND MONTH(dateSign) = 6";
    private static final String GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_7="SELECT COALESCE(count(contractID),0) AS quantity FROM tblContract WHERE YEAR(dateSign)=YEAR(GETDATE()) AND MONTH(dateSign) = 7";
    private static final String GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_8="SELECT COALESCE(count(contractID),0) AS quantity FROM tblContract WHERE YEAR(dateSign)=YEAR(GETDATE()) AND MONTH(dateSign) = 8";
    private static final String GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_9="SELECT COALESCE(count(contractID),0) AS quantity FROM tblContract WHERE YEAR(dateSign)=YEAR(GETDATE()) AND MONTH(dateSign) = 9";
    private static final String GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_10="SELECT COALESCE(count(contractID),0) AS quantity FROM tblContract WHERE YEAR(dateSign)=YEAR(GETDATE()) AND MONTH(dateSign) = 10";
    private static final String GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_11="SELECT COALESCE(count(contractID),0) AS quantity FROM tblContract WHERE YEAR(dateSign)=YEAR(GETDATE()) AND MONTH(dateSign) = 11";
    private static final String GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_12="SELECT COALESCE(count(contractID),0) AS quantity FROM tblContract WHERE YEAR(dateSign)=YEAR(GETDATE()) AND MONTH(dateSign) = 12";
   
    
    
    
    //HUMAN RESOURCE
    public int getUserQuantity() throws SQLException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_USER_QUANTITY);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
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
        return quantity;
    }

    public int getBoardManagerQuantity() throws SQLException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_BOARD_MANAGER_QUANTITY);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
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
        return quantity;
    }

    public int getHRManagerQuantity() throws SQLException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_HR_MANAGER_QUANTITY);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
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
        return quantity;
    }

    public int getEmployeeQuantity() throws SQLException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_EMPLOYEE_QUANTITY);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
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
        return quantity;
    }

    public int getResidentQuantity() throws SQLException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_RESIDENT_QUANTITY);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
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
        return quantity;
    }

    public int getCustomerQuantity() throws SQLException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_CUSTOMER_QUANTITY);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
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
        return quantity;
    }
    
    //APARMTENT
    public int getTotalApartment() throws SQLException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_TOTAL_APARTMENT);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
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
        return quantity;
    }   
    
    public int getTotalAvailableApartment() throws SQLException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_TOTAL_AVAILABLE_APARTMENT);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
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
        return quantity;
    }
    
    public int getTotalRentedApartment() throws SQLException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_TOTAL_RENTED_APARTMENT);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
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
        return quantity;
    }
    
    public int getTotalMaintenanceApartment() throws SQLException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_TOTAL_MAINTENACE_APARTMENT);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
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
        return quantity;
    }
    
    //CONTRACT
//    public int getTotalContractSignedThisYear() throws SQLException {
//        int quantity = 0;
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    quantity = rs.getInt("quantity");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return quantity;
//    }   
//    
//    public int getTotalContractSignedThisYear1() throws SQLException {
//        int quantity = 0;
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_1);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    quantity = rs.getInt("quantity");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return quantity;
//    }   
//    
//    public int getTotalContractSignedThisYear2() throws SQLException {
//        int quantity = 0;
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_2);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    quantity = rs.getInt("quantity");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return quantity;
//    }   
//    
//    public int getTotalContractSignedThisYear3() throws SQLException {
//        int quantity = 0;
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_3);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    quantity = rs.getInt("quantity");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return quantity;
//    }   
//    
//    public int getTotalContractSignedThisYear4() throws SQLException {
//        int quantity = 0;
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_4);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    quantity = rs.getInt("quantity");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return quantity;
//    }   
//    
//    public int getTotalContractSignedThisYear5() throws SQLException {
//        int quantity = 0;
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_5);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    quantity = rs.getInt("quantity");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return quantity;
//    }   
//    
//    public int getTotalContractSignedThisYear6() throws SQLException {
//        int quantity = 0;
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_6);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    quantity = rs.getInt("quantity");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return quantity;
//    }   
//    
//    public int getTotalContractSignedThisYear7() throws SQLException {
//        int quantity = 0;
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_7);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    quantity = rs.getInt("quantity");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return quantity;
//    }   
//    
//    public int getTotalContractSignedThisYear8() throws SQLException {
//        int quantity = 0;
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_8);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    quantity = rs.getInt("quantity");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return quantity;
//    }   
//    
//    public int getTotalContractSignedThisYear9() throws SQLException {
//        int quantity = 0;
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_9);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    quantity = rs.getInt("quantity");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return quantity;
//    }   
//    
//    public int getTotalContractSignedThisYear10() throws SQLException {
//        int quantity = 0;
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_10);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    quantity = rs.getInt("quantity");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return quantity;
//    }   
//    
//    public int getTotalContractSignedThisYear11() throws SQLException {
//        int quantity = 0;
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_11);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    quantity = rs.getInt("quantity");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return quantity;
//    }   
//    
//    public int getTotalContractSignedThisYear12() throws SQLException {
//        int quantity = 0;
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_12);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    quantity = rs.getInt("quantity");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return quantity;
//    }  
    
    public ArrayList<Integer> getTotalContractSignedThisYear() throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        int quantity = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
                list.add(quantity);
                
                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_1);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
                list.add(quantity);
                
                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_2);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
                list.add(quantity);
                
                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_3);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
                list.add(quantity);
                
                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_4);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
                list.add(quantity);
                
                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_5);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
                list.add(quantity);
                
                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_6);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
                list.add(quantity);
                
                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_7);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
                list.add(quantity);
                
                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_8);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
                list.add(quantity);
                
                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_9);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
                list.add(quantity);
                
                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_10);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
                list.add(quantity);
                
                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_11);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
                list.add(quantity);
                
                stm = conn.prepareStatement(GET_TOTAL_CONTRACT_SIGNED_THIS_YEAR_12);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
                list.add(quantity);
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
