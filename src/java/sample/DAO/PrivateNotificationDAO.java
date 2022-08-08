/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import sample.DTO.PrivateNotificationDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Phi Long
 */
public class PrivateNotificationDAO {
    private static final String GET_LIST_PRIVATE_NOTIFICATION="	SELECT * FROM tblPrivateNotification WHERE notiHeader LIKE ? OR notiContent LIKE ? OR userID LIKE ?";
    private static final String ADD_NEW_PRIVATE_NOTIFICATION ="	INSERT INTO tblPrivateNotification (notiHeader, notiContent, notiDate,userID, status) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_PRIVATE_NOTIFICATION_BY_ID = "SELECT notiID, notiHeader, notiContent, notiDate, status, userID FROM tblPrivateNotification WHERE notiID = ?";
    private static final String UPDATE_PRIVATE_NOTIFICATION="UPDATE tblPrivateNotification SET notiHeader = ?, notiContent = ?, notiDate = ?, userID = ? WHERE notiID = ?";
    private static final String UPDATE_PRIVATE_NOTIFICATION_USER="UPDATE tblPrivateNotification SET notiHeader = ?, notiContent = ?, notiDate = ?, userID = ?, status = ? WHERE notiID = ?";
    private static final String GET_LIST_USER_PRIVATE_NOTIFICATION="SELECT * FROM tblPrivateNotification WHERE (notiHeader LIKE ? OR notiContent LIKE ?) AND userID LIKE ?";
    
    public ArrayList<PrivateNotificationDTO> getListPrivateNotification( String search) throws SQLException {
        ArrayList<PrivateNotificationDTO> list = new ArrayList<>();
        PrivateNotificationDTO noti = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_PRIVATE_NOTIFICATION);
                stm.setString(1, "%"+search+"%");
                stm.setString(2, "%"+search+"%");  
                stm.setString(3, "%"+search+"%");  
                rs = stm.executeQuery();
                while (rs.next()) { 
                    int notiID = rs.getInt("notiID");
                    String notiHeader = rs.getNString("notiHeader");
                    String notiContent = rs.getNString("notiContent");
                    Date notiDate = rs.getDate("notiDate");
                    boolean status = rs.getBoolean("status");
                    String userID = rs.getString("userID");
                    noti = new PrivateNotificationDTO(notiID, notiHeader, notiContent, notiDate, userID, status);
                    list.add(noti);
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
        Collections.sort(list);
        return list;
    }
    
    public boolean addPrivateNotification(String notiHeader, String notiContent, String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_NEW_PRIVATE_NOTIFICATION);
                ptm.setNString(1, notiHeader);
                ptm.setNString(2, notiContent);
                ptm.setDate(3, new java.sql.Date(java.sql.Date.valueOf(LocalDate.now()).getTime()));
                ptm.setString(4, userID);
                ptm.setBoolean(5, false);
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
    
    public PrivateNotificationDTO getPrivateNotificationByID(int notificationID) throws SQLException {
        PrivateNotificationDTO noti = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_PRIVATE_NOTIFICATION_BY_ID);
                stm.setInt(1, notificationID);          
                rs = stm.executeQuery();
                while (rs.next()) { 
                    int notiID = rs.getInt("notiID");
                    String notiHeader = rs.getNString("notiHeader");
                    String notiContent = rs.getNString("notiContent");
                    Date notiDate = rs.getDate("notiDate");
                    boolean status = rs.getBoolean("status");
                    String userID = rs.getString("userID");
                    noti = new PrivateNotificationDTO(notiID, notiHeader, notiContent, notiDate, userID, status);
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
        return noti;
    }
    
    public boolean updatePrivateNotification(PrivateNotificationDTO privateNotification) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PRIVATE_NOTIFICATION);
                ptm.setNString(1, privateNotification.getNotiHeader());
                ptm.setNString(2, privateNotification.getNotiContent());
                ptm.setDate(3, new java.sql.Date(java.sql.Date.valueOf(LocalDate.now()).getTime()));
                ptm.setString(4, privateNotification.getUserID());
                ptm.setInt(5, privateNotification.getNotiID());
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
    
    public boolean updatePrivateNotificationUser(PrivateNotificationDTO privateNotification) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PRIVATE_NOTIFICATION_USER);
                ptm.setNString(1, privateNotification.getNotiHeader());
                ptm.setNString(2, privateNotification.getNotiContent());
                ptm.setDate(3, new java.sql.Date(java.sql.Date.valueOf(LocalDate.now()).getTime()));
                ptm.setString(4, privateNotification.getUserID());
                ptm.setInt(6, privateNotification.getNotiID());
                ptm.setBoolean(5, privateNotification.isStatus());
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
    public ArrayList<PrivateNotificationDTO> getListPrivateNotification(String userID, String search) throws SQLException {
        ArrayList<PrivateNotificationDTO> list = new ArrayList<>();
        PrivateNotificationDTO noti = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_USER_PRIVATE_NOTIFICATION);
                stm.setString(1, "%"+search+"%");
                stm.setString(2, "%"+search+"%");  
                stm.setString(3, userID);  
                rs = stm.executeQuery();
                while (rs.next()) { 
                    int notiID = rs.getInt("notiID");
                    String notiHeader = rs.getNString("notiHeader");
                    String notiContent = rs.getNString("notiContent");
                    Date notiDate = rs.getDate("notiDate");
                    boolean status = rs.getBoolean("status");
                    noti = new PrivateNotificationDTO(notiID, notiHeader, notiContent, notiDate, userID, status);
                    list.add(noti);
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
        Collections.sort(list);
        return list;
    }
}
