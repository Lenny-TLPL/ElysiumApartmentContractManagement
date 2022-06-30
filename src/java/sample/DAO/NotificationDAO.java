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
import sample.DTO.NotificationDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Phi Long
 */
public class NotificationDAO {
    private static final String GET_LIST_NOTIFICATION="	SELECT * FROM tblNotification WHERE notiHeader LIKE ? OR notiContent LIKE ? ";
    
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
}
