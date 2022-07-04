/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DTO;

/**
 *
 * @author Phi Long
 */
public class ContractError {

    private String contractID;
    private String dateSign;
    private String contractImage;
    private String userID;
    private String apartmentID;
    private String value;
    private String expiryDate;
    private String monthsOfDebt;
    private String interestRate;
    private String contractType;
    private String status;
    private String errorMessage;

    public ContractError() {
        this.contractID = "";
        this.dateSign = "";
        this.contractImage = "";
        this.userID = "";
        this.apartmentID = "";
        this.value = "";
        this.expiryDate = "";
        this.monthsOfDebt = "";
        this.interestRate = "";
        this.contractType = "";
        this.status = "";
        this.errorMessage = "";
    }

    public ContractError(String contractID, String dateSign, String contractImage, String userID, String apartmentID, String value, String expiryDate, String monthsOfDebt, String interestRate, String contractType, String status, String errorMessage) {
        this.contractID = contractID;
        this.dateSign = dateSign;
        this.contractImage = contractImage;
        this.userID = userID;
        this.apartmentID = apartmentID;
        this.value = value;
        this.expiryDate = expiryDate;
        this.monthsOfDebt = monthsOfDebt;
        this.interestRate = interestRate;
        this.contractType = contractType;
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public String getContractID() {
        return contractID;
    }

    public void setContractID(String contractID) {
        this.contractID = contractID;
    }

    public String getDateSign() {
        return dateSign;
    }

    public void setDateSign(String dateSign) {
        this.dateSign = dateSign;
    }

    public String getContractImage() {
        return contractImage;
    }

    public void setContractImage(String contractImage) {
        this.contractImage = contractImage;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(String apartmentID) {
        this.apartmentID = apartmentID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getMonthsOfDebt() {
        return monthsOfDebt;
    }

    public void setMonthsOfDebt(String monthsOfDebt) {
        this.monthsOfDebt = monthsOfDebt;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
