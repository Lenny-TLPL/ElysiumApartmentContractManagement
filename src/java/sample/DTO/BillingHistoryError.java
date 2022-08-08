/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DTO;

/**
 *
 * @author Phi Long
 */
public class BillingHistoryError {
    private String billID;
    private String payDate;
    private String userID;
    private String apartmentID;
    private String contractFee;
    private String serviceFee;
    private String waterFee;
    private String electricityFee;
    private String otherFee;
    private String receivedValue;
    private String change;
    private String errorMessage;

    public BillingHistoryError(String billID, String payDate, String userID, String apartmentID, String contractFee, String serviceFee, String waterFee, String electricityFee, String otherFee, String receivedValue, String change, String errorMessage) {
        this.billID = billID;
        this.payDate = payDate;
        this.userID = userID;
        this.apartmentID = apartmentID;
        this.contractFee = contractFee;
        this.serviceFee = serviceFee;
        this.waterFee = waterFee;
        this.electricityFee = electricityFee;
        this.otherFee = otherFee;
        this.receivedValue = receivedValue;
        this.change = change;
        this.errorMessage = errorMessage;
    }

    public BillingHistoryError() {
        this.billID = "";
        this.payDate = "";
        this.userID = "";
        this.apartmentID = "";
        this.contractFee = "";
        this.serviceFee = "";
        this.waterFee = "";
        this.electricityFee = "";
        this.otherFee = "";
        this.receivedValue = "";
        this.change = "";
        this.errorMessage = "";
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
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

    public String getContractFee() {
        return contractFee;
    }

    public void setContractFee(String contractFee) {
        this.contractFee = contractFee;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getWaterFee() {
        return waterFee;
    }

    public void setWaterFee(String waterFee) {
        this.waterFee = waterFee;
    }

    public String getElectricityFee() {
        return electricityFee;
    }

    public void setElectricityFee(String electricityFee) {
        this.electricityFee = electricityFee;
    }

    public String getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(String otherFee) {
        this.otherFee = otherFee;
    }

    public String getReceivedValue() {
        return receivedValue;
    }

    public void setReceivedValue(String receivedValue) {
        this.receivedValue = receivedValue;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    

}
