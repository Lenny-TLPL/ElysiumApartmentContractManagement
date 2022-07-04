/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DAO;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.Part;
import sample.DTO.ContractDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Phi Long
 */
public class ContractDAO {
    private static final String GET_LIST_CONTRACT="SELECT * FROM tblContract WHERE apartmentID LIKE ? OR userID LIKE ?";
    private static final String ADD_CONTRACT="EXEC addContract ?, ?, ?, ?, ?, ?, ?";
    
    public ArrayList<ContractDTO> getListContract( String search) throws SQLException {
        ArrayList<ContractDTO> list = new ArrayList<>();
        ContractDTO contract = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_CONTRACT);
                stm.setString(1, "%"+search+"%");
                stm.setString(2, "%"+search+"%");            
                rs = stm.executeQuery();
                while (rs.next()) { 
                    int contractID = rs.getInt("contractID");
                    Date dateSign = rs.getDate("dateSign");
                    Blob contractImage=rs.getBlob("contractImage");
//                    if(rs.getBytes("contracImage")!=null){
//                        byte[] img = rs.getBytes("contractImage");
//                    ImageIcon image = new ImageIcon(img);
//                    contractImage = image.getImage();    
//                    }else{
//                        contractImage=null;
//                    }
                    String userID = rs.getString("userID");
                    String apartmentID = rs.getString("apartmentID");
                    float value = rs.getFloat("value");
                    Date expiryDate = rs.getDate("expiryDate");
                    int monthsOfDebt = rs.getInt("monthsOfDebt");
                    float interestRate= rs.getFloat("interestRate");
                    String contractType = rs.getString("contractType");
                    boolean status = rs.getBoolean("status");
                    contract = new ContractDTO(contractID, dateSign, contractImage, userID, apartmentID, value, expiryDate, monthsOfDebt, interestRate, contractType, status);
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
    
    public boolean addContract(ContractDTO contract,Part imageFilePart) throws SQLException {
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
                ptm.setDate(5,new java.sql.Date(contract.getExpiryDate().getTime()));
                ptm.setFloat(6, contract.getMonthsOfDebt());
                ptm.setString(7, contract.getUserID());
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
