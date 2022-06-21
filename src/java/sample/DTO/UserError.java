/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DTO;

/**
 *
 * @author Phi Long
 */
public class UserError {
    private String userID ;   
    private String fullName ;
    private String email ;
    private String phone ;
    private String address ;
    private String birthday;
    private String citizenID;
    private String gender;
    private String password;
    private String dateJoin;
    private String status;
    private String roleName;
    private String errorMessage;
    
    public UserError() {
        this.userID = "";
        this.fullName = "";
        this.email = "";
        this.phone = "";
        this.address = "";
        this.birthday = "";
        this.citizenID = "";
        this.gender = "";
        this.password = "";
        this.dateJoin = "";
        this.status = "";
        this.roleName = "";
        this.errorMessage = "";
    }

    public UserError(String userID, String fullName, String email, String phone, String address, String birthday, String citizenID, String gender, String password, String dateJoin, String status, String roleName, String errorMessage) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
        this.citizenID = citizenID;
        this.gender = gender;
        this.password = password;
        this.dateJoin = dateJoin;
        this.status = status;
        this.roleName = roleName;
        this.errorMessage = errorMessage;
    }
    
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCitizenID() {
        return citizenID;
    }

    public void setCitizenID(String citizenID) {
        this.citizenID = citizenID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateJoin() {
        return dateJoin;
    }

    public void setDateJoin(String dateJoin) {
        this.dateJoin = dateJoin;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
