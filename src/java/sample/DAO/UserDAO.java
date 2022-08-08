/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DAO;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sample.DTO.UserDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Phi Long
 */
public class UserDAO {

    private static final String LOGIN = "SELECT fullName, email, phone, address, birthday, citizenID, gender, dateJoin, status, roleID FROM tblUser  WHERE (userID COLLATE SQL_Latin1_General_CP1_CS_AS = ?) AND password COLLATE SQL_Latin1_General_CP1_CS_AS = ? AND status = 1";
    private static final String CHECK_DUPLICATE_USER = "SELECT userID, citizenID FROM tblUser WHERE tblUser.citizenID COLLATE SQL_Latin1_General_CP1_CS_AS = ?  AND tblUser.roleID NOT IN(1,2,3)";
    private static final String CHECK_DUPLICATE_ADMIN = "SELECT userID, citizenID FROM tblUser WHERE tblUser.citizenID COLLATE SQL_Latin1_General_CP1_CS_AS = ?  AND tblUser.roleID NOT IN(4,5)";
    private static final String ADD_USER = "EXEC addUser ?, ?, ?, ?, ?, ?, ?, ?, ?";
    private static final String GET_LIST_USER = "SELECT userID, password, fullName, email, phone, address, birthday, citizenID, gender, dateJoin, status, roleID FROM tblUser "
            + "WHERE tblUser.roleID = COALESCE((SELECT TOP 1 roleID FROM tblRole WHERE roleName COLLATE SQL_Latin1_General_CP1_CS_AS = ?),0)"
            + " AND (fullName LIKE ? OR userID LIKE ? OR citizenID LIKE ? OR phone LIKE ? )";
    private static final String GET_LIST_LOGIN_USER_PERMMISSION = "SELECT p.permissionName FROM tblUserPermission u "
            + "INNER JOIN tblPermission p ON u.permissionID=p.permissionID  "
            + " WHERE userID COLLATE SQL_Latin1_General_CP1_CS_AS = ?";
    private static final String GET_LIST_LOGIN_USER_PERMMISSION_ID = "SELECT u.permissionID FROM tblUserPermission u "
            + " WHERE userID COLLATE SQL_Latin1_General_CP1_CS_AS = ?";
    private static final String GET_USER_ID_BY_CITIZEN_ID = "SELECT userID FROM tblUser "
            + "WHERE tblUser.roleID = COALESCE((SELECT TOP 1 roleID FROM tblRole WHERE roleName COLLATE SQL_Latin1_General_CP1_CS_AS = ?),0)"
            + " AND citizenID COLLATE SQL_Latin1_General_CP1_CS_AS = ? ";
    private static final String DELETE_USER = "DELETE FROM tblUser WHERE userID LIKE ? AND roleID=?";
    private static final String GET_USER_BY_ID_AND_STATUS = "SELECT fullName, email, phone, address, birthday, citizenID, gender, dateJoin, status, roleID, password FROM tblUser  WHERE userID COLLATE SQL_Latin1_General_CP1_CS_AS = ?  AND status = ?";
    private static final String GET_USER_DETAIL_BY_USERID_AND_ROLE = "SELECT fullName, email, phone, address, birthday, citizenID, gender, dateJoin, status, password FROM tblUser \n"
            + "WHERE userID COLLATE SQL_Latin1_General_CP1_CS_AS = ? AND roleID = ?";
    private static final String UPDATE_USER_STATUS = "UPDATE tblUser SET status = ? WHERE userID COLLATE SQL_Latin1_General_CP1_CS_AS = ? ";
    private static final String UPDATE_USER = "UPDATE tblUser SET fullName=?, email=?, phone=?, address=?, birthday=?, citizenID=?, gender=?, status=?, roleID=?, password=? WHERE userID COLLATE SQL_Latin1_General_CP1_CS_AS = ? ";
    private static final String GET_USER_BY_ID = "SELECT fullName, email, phone, address, birthday, citizenID, gender, dateJoin, status, roleID, password FROM tblUser  WHERE userID COLLATE SQL_Latin1_General_CP1_CS_AS = ?";
    private static final String UPDATE_USER_ROLE = "UPDATE tblUser SET roleID=? WHERE userID COLLATE SQL_Latin1_General_CP1_CS_AS = ? ";
    private static final String CHECK_DUPLICATE_EMAIL_USER = "SELECT userID, citizenID FROM tblUser WHERE tblUser.email COLLATE SQL_Latin1_General_CP1_CS_AS = ?  AND tblUser.roleID NOT IN(1,2,3)";
    private static final String CHECK_DUPLICATE_EMAIL_ADMIN = "SELECT userID, citizenID FROM tblUser WHERE tblUser.email COLLATE SQL_Latin1_General_CP1_CS_AS = ?  AND tblUser.roleID NOT IN(4,5)";
    private static final String CHECK_DUPLICATE_PHONE_USER = "SELECT userID, citizenID FROM tblUser WHERE tblUser.phone COLLATE SQL_Latin1_General_CP1_CS_AS = ?  AND tblUser.roleID NOT IN(1,2,3)";
    private static final String CHECK_DUPLICATE_PHONE_ADMIN = "SELECT userID, citizenID FROM tblUser WHERE tblUser.phone COLLATE SQL_Latin1_General_CP1_CS_AS = ?  AND tblUser.roleID NOT IN(4,5)";
    
    
    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(LOGIN);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getNString("fullName");
                    String email = rs.getNString("email");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    Date birthDay = rs.getDate("birthday");
                    String citizenID = rs.getString("citizenID");
                    String gender = rs.getString("gender");
                    Date dateJoin = rs.getDate("dateJoin");
                    boolean status = rs.getBoolean("status");
                    int roleID = rs.getInt("roleID");
                    user = new UserDTO(userID, fullName, email, phone, address, birthDay, citizenID, gender, password, dateJoin, status, roleID);
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
        return user;
    }

    public boolean checkDuplicateUser(String citizenID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE_USER);
                ptm.setString(1, citizenID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkDuplicateAdmin(String citizenID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE_ADMIN);
                ptm.setString(1, citizenID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    check = true;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkDuplicateUserV2(String citizenID, String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE_USER);
                ptm.setString(1, citizenID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    if (!userID.equals(rs.getString("userID"))) {
                        check = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkDuplicateAdminV2(String citizenID, String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE_ADMIN);
                ptm.setString(1, citizenID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    if (!userID.equals(rs.getString("userID"))) {
                        check = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean addUser(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_USER);
                ptm.setNString(1, user.getFullName());
                ptm.setNString(2, user.getEmail());
                ptm.setString(3, user.getPhone());
                ptm.setString(4, user.getAddress());
                ptm.setDate(5, new java.sql.Date(user.getBirthDay().getTime()));
                ptm.setString(6, user.getCitizenID());
                ptm.setString(7, user.getGender());
                ptm.setString(8, user.getPassword());
                ptm.setInt(9, user.getRoleID());
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

    public ArrayList<UserDTO> getListUser(String type, String search) throws SQLException {
        ArrayList<UserDTO> list = new ArrayList<>();
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_USER);
                stm.setNString(1, type);
                stm.setNString(2, "%" + search + "%");
                stm.setString(3, "%" + search + "%");
                stm.setString(4, "%" + search + "%");
                stm.setString(5, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String password = rs.getString("password");
                    String fullName = rs.getNString("fullName");
                    String email = rs.getNString("email");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    Date birthDay = rs.getDate("birthday");
                    String citizenID = rs.getString("citizenID");
                    String gender = rs.getString("gender");
                    Date dateJoin = rs.getDate("dateJoin");
                    boolean status = rs.getBoolean("status");
                    int roleID = rs.getInt("roleID");
                    user = new UserDTO(userID, fullName, email, phone, address, birthDay, citizenID, gender, password, dateJoin, status, roleID);
                    list.add(user);
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

    public ArrayList<String> getListLoginUserPermission(String userID) throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_LOGIN_USER_PERMMISSION);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String permission = rs.getNString("permissionName");
                    list.add(permission);
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
    
    public ArrayList<Integer> getListLoginUserPermissionID(String userID) throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_LOGIN_USER_PERMMISSION_ID);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int permission = rs.getInt("permissionID");
                    list.add(permission);
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

    public String getUserIDByCitizenID(String type, String citizenID) throws SQLException {
        String userID = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_USER_ID_BY_CITIZEN_ID);
                stm.setNString(1, type);
                stm.setString(2, citizenID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    userID = rs.getString("userID");
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
        return userID;
    }

    public boolean deleteUser(String userID, int roleID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_USER);
                ptm.setString(1, userID);
                ptm.setInt(2, roleID);
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

    public UserDTO getUserByIDAndStatus(String userID, boolean status) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_USER_BY_ID_AND_STATUS);
                stm.setString(1, userID);
                stm.setBoolean(2, status);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getNString("fullName");
                    String email = rs.getNString("email");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    Date birthDay = rs.getDate("birthday");
                    String citizenID = rs.getString("citizenID");
                    String gender = rs.getString("gender");
                    Date dateJoin = rs.getDate("dateJoin");
                    String password = rs.getString("password");
                    int roleID = rs.getInt("roleID");
                    user = new UserDTO(userID, fullName, email, phone, address, birthDay, citizenID, gender, password, dateJoin, status, roleID);
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
        return user;
    }

    public UserDTO getUserDetailByUserIDAndRoleID(String userID, int roleID) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_USER_DETAIL_BY_USERID_AND_ROLE);
                stm.setString(1, userID);
                stm.setInt(2, roleID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getNString("fullName");
                    String email = rs.getNString("email");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    Date birthDay = rs.getDate("birthday");
                    String citizenID = rs.getString("citizenID");
                    String gender = rs.getString("gender");
                    Date dateJoin = rs.getDate("dateJoin");
                    String password = rs.getString("password");
                    Boolean status = rs.getBoolean("status");
                    user = new UserDTO(userID, fullName, email, phone, address, birthDay, citizenID, gender, password, dateJoin, status, roleID);
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
        return user;
    }

    public boolean updateUserStatus(String userID, boolean status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_USER_STATUS);
                ptm.setBoolean(1, status);
                ptm.setString(2, userID);

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

    public boolean updateUser(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_USER);
                ptm.setNString(1, user.getFullName());
                ptm.setNString(2, user.getEmail());
                ptm.setString(3, user.getPhone());
                ptm.setString(4, user.getAddress());
                ptm.setDate(5, new java.sql.Date(user.getBirthDay().getTime()));
                ptm.setString(6, user.getCitizenID());
                ptm.setString(7, user.getGender());
                ptm.setBoolean(8, user.isStatus());
                ptm.setInt(9, user.getRoleID());
                ptm.setString(11, user.getUserID());
                ptm.setString(10, user.getPassword());
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

    public UserDTO getUserByID(String userID) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_USER_BY_ID);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getNString("fullName");
                    String email = rs.getNString("email");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    Date birthDay = rs.getDate("birthday");
                    String citizenID = rs.getString("citizenID");
                    String gender = rs.getString("gender");
                    Date dateJoin = rs.getDate("dateJoin");
                    String password = rs.getString("password");
                    int roleID = rs.getInt("roleID");
                    Boolean status = rs.getBoolean("status");
                    user = new UserDTO(userID, fullName, email, phone, address, birthDay, citizenID, gender, password, dateJoin, status, roleID);
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
        return user;
    }
    
    public boolean updateUserRole(int roleID, String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_USER_ROLE);
                ptm.setInt(1, roleID);
                ptm.setString(2, userID);
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
    
    public boolean checkDuplicateEmailUser(String email) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE_EMAIL_USER);
                ptm.setString(1, email);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkDuplicateEmailAdmin(String email) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE_EMAIL_ADMIN);
                ptm.setString(1, email);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    check = true;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkDuplicateEmailUserV2(String email, String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE_EMAIL_USER);
                ptm.setString(1, email);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    if (!userID.equals(rs.getString("userID"))) {
                        check = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkDuplicateEmailAdminV2(String email, String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE_EMAIL_ADMIN);
                ptm.setString(1, email);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    if (!userID.equals(rs.getString("userID"))) {
                        check = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public boolean checkDuplicatePhoneUser(String phone) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE_PHONE_USER);
                ptm.setString(1, phone);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkDuplicatePhoneAdmin(String email) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE_PHONE_ADMIN);
                ptm.setString(1, email);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    check = true;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkDuplicatePhoneUserV2(String phone, String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE_PHONE_USER);
                ptm.setString(1, phone);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    if (!userID.equals(rs.getString("userID"))) {
                        check = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkDuplicatePhoneAdminV2(String phone, String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE_PHONE_ADMIN);
                ptm.setString(1, phone);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    if (!userID.equals(rs.getString("userID"))) {
                        check = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
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
