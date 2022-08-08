/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DAO;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import javax.servlet.http.Part;
import sample.DTO.ContractDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Phi Long
 */
public class ContractDAO {

    private static final String GET_LIST_CONTRACT = "SELECT contractID, dateSign, userID, apartmentID, contractType, status FROM tblContract WHERE apartmentID LIKE ? OR userID LIKE ?";
    private static final String GET_LIST_CONTRACT_USER = "SELECT contractID, dateSign, userID, apartmentID, contractType, status FROM tblContract WHERE apartmentID LIKE ? AND userID LIKE ?";
    private static final String ADD_CONTRACT = "EXEC addContract ?, ?, ?, ?, ?, ?, ?, ?";
    private static final String GET_CONTRACT_DETAIL_WITHOUT_IMAGE = "SELECT contractID, dateSign, userID, apartmentID, value, expiryDate, monthsOfDebt, interestRate, contractType, status FROM tblContract WHERE contractID = ?";
    private static final String GET_CONTRACT_IMAGE = "SELECT contractImage FROM tblContract WHERE contractID = ?";
    private static final String GET_LIST_DUE_CONTRACT = "SELECT userID,contractID, apartmentID FROM tblContract WHERE DAY(expiryDate) =  DAY(GETDATE()) AND MONTH(expiryDate) =  MONTH(GETDATE()) AND YEAR(expiryDate) =  YEAR(GETDATE()) ";
    private static final String MANAGE_DUE_CONTRACT = "EXEC checkValidContract ?, ?, ?";
    private static final String GET_LIST_USER_MAIL_ALMOST_DUE_CONTRACT = "SELECT  DISTINCT u.email FROM tblContract c INNER JOIN tblUser u ON c.userID = u.userID\n" +
"WHERE YEAR(expiryDate) = YEAR(GETDATE()) AND MONTH(expiryDate)=MONTH(GETDATE()) AND DAY(expiryDate)-DAY(GETDATE()) = ?";
    private static final String GET_LIST_USER_ID_ALMOST_DUE_CONTRACT = "SELECT  DISTINCT u.userID FROM tblContract c INNER JOIN tblUser u ON c.userID = u.userID\n" +
"WHERE YEAR(expiryDate) = YEAR(GETDATE()) AND MONTH(expiryDate)=MONTH(GETDATE()) AND DAY(expiryDate)-DAY(GETDATE()) = ?";
    private static final String GET_CONTRACT_DETAIL_WITH_USERID_APARTMENTID = "SELECT contractID, dateSign, userID, apartmentID, value, expiryDate, monthsOfDebt, interestRate, contractType, status FROM tblContract WHERE userID = ? AND apartmentID = ? AND status = 1";
    private static final String UPDATE_CONTRACT_STATUS="UPDATE tblContract SET status = ? WHERE contractID = ?";
    private static final String UPDATE_CONTRACT = "UPDATE tblContract SET dateSign = ?, contractImage = ?, expiryDate = ?, monthsOfDebt = ?, interestRate = ?, value = ? WHERE contractID = ?";
    private static final String UPDATE_CONTRACT_WITHOUT_IMAGE="UPDATE tblContract SET dateSign = ?,  expiryDate = ?, monthsOfDebt = ?, interestRate = ?, value = ? WHERE contractID = ?";
    
    public ArrayList<ContractDTO> getListContract(String search) throws SQLException {
        ArrayList<ContractDTO> list = new ArrayList<>();
        ContractDTO contract = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_CONTRACT);
                stm.setString(1, "%" + search + "%");
                stm.setString(2, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int contractID = rs.getInt("contractID");
                    Date dateSign = rs.getDate("dateSign");
//                    Blob contractImage=rs.getBlob("contractImage");
//                    if(rs.getBytes("contracImage")!=null){
//                        byte[] img = rs.getBytes("contractImage");
//                    ImageIcon image = new ImageIcon(img);
//                    contractImage = image.getImage();    
//                    }else{
//                        contractImage=null;
//                    }
                    String userID = rs.getString("userID");
                    String apartmentID = rs.getString("apartmentID");
//                    float value = rs.getFloat("value");
//                    Date expiryDate = rs.getDate("expiryDate");
//                    int monthsOfDebt = rs.getInt("monthsOfDebt");
//                    float interestRate= rs.getFloat("interestRate");
                    String contractType = rs.getString("contractType");
                    boolean status = rs.getBoolean("status");
                    contract = new ContractDTO(contractID, dateSign, null, userID, apartmentID, 0, null, 0, 0, contractType, status);
                    list.add(contract);
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
    
    public ArrayList<ContractDTO> getListContractUser(String search, String userID) throws SQLException {
        ArrayList<ContractDTO> list = new ArrayList<>();
        ContractDTO contract = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_CONTRACT_USER);
                stm.setString(1, "%" + search + "%");
                stm.setString(2, userID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int contractID = rs.getInt("contractID");
                    Date dateSign = rs.getDate("dateSign");
//                    Blob contractImage=rs.getBlob("contractImage");
//                    if(rs.getBytes("contracImage")!=null){
//                        byte[] img = rs.getBytes("contractImage");
//                    ImageIcon image = new ImageIcon(img);
//                    contractImage = image.getImage();    
//                    }else{
//                        contractImage=null;
//                    }
                    String apartmentID = rs.getString("apartmentID");
//                    float value = rs.getFloat("value");
//                    Date expiryDate = rs.getDate("expiryDate");
//                    int monthsOfDebt = rs.getInt("monthsOfDebt");
//                    float interestRate= rs.getFloat("interestRate");
                    String contractType = rs.getString("contractType");
                    boolean status = rs.getBoolean("status");
                    contract = new ContractDTO(contractID, dateSign, null, userID, apartmentID, 0, null, 0, 0, contractType, status);
                    list.add(contract);
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

    public boolean addContract(ContractDTO contract, Part imageFilePart) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_CONTRACT);
                ptm.setDate(1, new java.sql.Date(contract.getDateSign().getTime()));
                ptm.setBlob(2, imageFilePart.getInputStream());
                ptm.setString(3, contract.getApartmentID());
                ptm.setString(4, contract.getContractType());
                if (contract.getExpiryDate() != null) {
                    ptm.setDate(5, new java.sql.Date(contract.getExpiryDate().getTime()));
                } else {
                    ptm.setDate(5, null);
                }
                ptm.setFloat(6, contract.getMonthsOfDebt());
                ptm.setString(7, contract.getUserID());
                ptm.setFloat(8, contract.getInterestRate());
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

    public ContractDTO getContractDetail(int contractID) throws SQLException {
        ContractDTO contract = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_CONTRACT_DETAIL_WITHOUT_IMAGE);
                stm.setInt(1, contractID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    Date dateSign = rs.getDate("dateSign");
//                    Blob contractImage=rs.getBlob("contractImage");
//                    if(rs.getBytes("contracImage")!=null){
//                        byte[] img = rs.getBytes("contractImage");
//                    ImageIcon image = new ImageIcon(img);
//                    contractImage = image.getImage();    
//                    }else{
//                        contractImage=null;
//                    }
                    String userID = rs.getString("userID");
                    String apartmentID = rs.getString("apartmentID");
                    boolean status = rs.getBoolean("status");
                    String contractType = rs.getString("contractType");
                    float value = rs.getFloat("value");
                    if (contractType.equals("buying")) {
                        contract = new ContractDTO(contractID, dateSign, null, userID, apartmentID, value, null, 0, 0, contractType, status);
                    } else if (contractType.equals("amortization")) {
                        Date expiryDate = rs.getDate("expiryDate");
                        int monthsOfDebt = rs.getInt("monthsOfDebt");
                        float interestRate = rs.getFloat("interestRate");
                        contract = new ContractDTO(contractID, dateSign, null, userID, apartmentID, value, expiryDate, monthsOfDebt, interestRate, contractType, status);
                    } else if (contractType.equals("leasing")) {
                        Date expiryDate = rs.getDate("expiryDate");
                        contract = new ContractDTO(contractID, dateSign, null, userID, apartmentID, value, expiryDate, 0, 0, contractType, status);
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
        return contract;
    }

    public String getContractImage(int contractID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String base64Image = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_CONTRACT_IMAGE);
                stm.setInt(1, contractID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    Blob blob = rs.getBlob("contractImage");

                    InputStream inputStream = blob.getBinaryStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    byte[] imageBytes = outputStream.toByteArray();

                    base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
//                    Blob contractImage=rs.getBlob("contractImage");
//                    if(rs.getBytes("contracImage")!=null){
//                        byte[] img = rs.getBytes("contractImage");
//                    ImageIcon image = new ImageIcon(img);
//                    contractImage = image.getImage();    
//                    }else{
//                        contractImage=null;
//                    }

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
        return base64Image;
    }

    public ArrayList<ContractDTO> getListDueContract() throws SQLException {
        ArrayList<ContractDTO> list = new ArrayList<>();
        ContractDTO contract = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_DUE_CONTRACT);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int contractID = rs.getInt("contractID");
                    String userID = rs.getString("userID");
                    String apartmentID = rs.getString("apartmentID");
                    contract = new ContractDTO(contractID, null, null, userID, apartmentID, 0, null, 0, 0, null, false);
                    list.add(contract);
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

    public boolean manageDueContract(String userID, int contractID, String apartmentID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(MANAGE_DUE_CONTRACT);
                ptm.setString(1, userID);
                ptm.setInt(2, contractID);
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
    
    public ArrayList<String> getListUserEMailAlmostDueContract(int diffDate) throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_USER_MAIL_ALMOST_DUE_CONTRACT);
                stm.setInt(1, diffDate );
                rs = stm.executeQuery();
                while (rs.next()) {
                    String email = rs.getNString("email");
                    if(email != null){
                        list.add(email);
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
    
    public ArrayList<String> getListUserIDAlmostDueContract(int diffDate) throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_USER_ID_ALMOST_DUE_CONTRACT);
                stm.setInt(1, diffDate );
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    if(userID != null){
                        list.add(userID);
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
    
    public ContractDTO getContractDetail(String userID, String apartmentID) throws SQLException {
        ContractDTO contract = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_CONTRACT_DETAIL_WITH_USERID_APARTMENTID);
                stm.setString(1, userID);
                stm.setString(2, apartmentID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    Date dateSign = rs.getDate("dateSign");
//                    Blob contractImage=rs.getBlob("contractImage");
//                    if(rs.getBytes("contracImage")!=null){
//                        byte[] img = rs.getBytes("contractImage");
//                    ImageIcon image = new ImageIcon(img);
//                    contractImage = image.getImage();    
//                    }else{
//                        contractImage=null;
//                    }
                    int contractID = rs.getInt("contractID");
                    boolean status = rs.getBoolean("status");
                    String contractType = rs.getString("contractType");
                    float value = rs.getFloat("value");
                    if (contractType.equals("buying")) {
                        contract = new ContractDTO(contractID, dateSign, null, userID, apartmentID, value, null, 0, 0, contractType, status);
                    } else if (contractType.equals("amortization")) {
                        Date expiryDate = rs.getDate("expiryDate");
                        int monthsOfDebt = rs.getInt("monthsOfDebt");
                        float interestRate = rs.getFloat("interestRate");
                        contract = new ContractDTO(contractID, dateSign, null, userID, apartmentID, value, expiryDate, monthsOfDebt, interestRate, contractType, status);
                    } else if (contractType.equals("leasing")) {
                        Date expiryDate = rs.getDate("expiryDate");
                        contract = new ContractDTO(contractID, dateSign, null, userID, apartmentID, value, expiryDate, 0, 0, contractType, status);
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
        return contract;
    }
    
    public boolean updateContractStatus(int contractID, boolean status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_CONTRACT_STATUS);
                ptm.setBoolean(1, status);
                ptm.setInt(2, contractID);
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
    
    public boolean updateContract(ContractDTO contract, Part imageFilePart) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_CONTRACT);
                ptm.setDate(1, new java.sql.Date(contract.getDateSign().getTime()));
                ptm.setBlob(2, imageFilePart.getInputStream());
                if (contract.getExpiryDate() != null) {
                    ptm.setDate(3, new java.sql.Date(contract.getExpiryDate().getTime()));
                } else {
                    ptm.setDate(3, null);
                }
                ptm.setFloat(4, contract.getMonthsOfDebt());
                ptm.setFloat(5, contract.getInterestRate());
                ptm.setFloat(6, contract.getValue());
                ptm.setInt(7, contract.getContractID());
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
    
    public boolean updateContract(ContractDTO contract) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_CONTRACT_WITHOUT_IMAGE);
                ptm.setDate(1, new java.sql.Date(contract.getDateSign().getTime()));
                if (contract.getExpiryDate() != null) {
                    ptm.setDate(2, new java.sql.Date(contract.getExpiryDate().getTime()));
                } else {
                    ptm.setDate(2, null);
                }
                ptm.setFloat(3, contract.getMonthsOfDebt());
                ptm.setFloat(4, contract.getInterestRate());
                ptm.setFloat(5, contract.getValue());
                ptm.setInt(6, contract.getContractID());
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
