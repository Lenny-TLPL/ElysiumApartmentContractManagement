/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DAO;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import sample.DTO.NotificationDTO;
import sample.utils.DBUtils;
import sample.utils.ToStringUtils;

/**
 *
 * @author Phi Long
 */
public class NotificationDAO {
    private static final String GET_LIST_NOTIFICATION="	SELECT * FROM tblNotification WHERE notiHeader LIKE ? OR notiContent LIKE ? ";
    private static final String ADD_NEW_NOTIFICATION="INSERT INTO tblNotification (notiHeader, notiContent, notiDate, status) VALUES (?, ?, ?, ?)";
    private static final String GET_NOTIFICATION_BY_ID = "SELECT notiID, notiHeader, notiContent, notiDate, status FROM tblNotification WHERE notiID = ?";
    
    public ArrayList<NotificationDTO> getListNotification( String search) throws SQLException {
        ArrayList<NotificationDTO> list = new ArrayList<>();
        NotificationDTO noti = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_NOTIFICATION);
                stm.setString(1, "%"+search+"%");
                stm.setString(2, "%"+search+"%");            
                rs = stm.executeQuery();
                while (rs.next()) { 
                    int notiID = rs.getInt("notiID");
                    String notiHeader = rs.getNString("notiHeader");
                    String notiContent = rs.getNString("notiContent");
                    Date notiDate = rs.getDate("notiDate");
                    boolean status = rs.getBoolean("status");
                    noti = new NotificationDTO(notiID, notiHeader, notiContent, notiDate, status);
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
        return list;
    }
   
    public boolean addNotification(String notiHeader, String notiContent) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_NEW_NOTIFICATION);
                ptm.setNString(1, notiHeader);
                ptm.setNString(2, notiContent);
                ptm.setDate(3, new java.sql.Date(java.sql.Date.valueOf(LocalDate.now()).getTime()));
                ptm.setBoolean(4, true);
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
    
    public NotificationDTO getNotificationByID(int notificationID) throws SQLException {
        NotificationDTO noti = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_NOTIFICATION_BY_ID);
                stm.setInt(1, notificationID);          
                rs = stm.executeQuery();
                while (rs.next()) { 
                    int notiID = rs.getInt("notiID");
                    String notiHeader = rs.getNString("notiHeader");
                    String notiContent = rs.getNString("notiContent");
                    Date notiDate = rs.getDate("notiDate");
                    boolean status = rs.getBoolean("status");
                    noti = new NotificationDTO(notiID, notiHeader, notiContent, notiDate, status);
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
}
