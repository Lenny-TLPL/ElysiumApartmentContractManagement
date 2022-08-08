package sample.DTO;

import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Quang
 */
public class BillingHistoryDTO {

    private int billID;
    private Date payDate;
    private String userID;
    private String apartmentID;
    private float contractFee;
    private float serviceFee;
    private float waterFee;
    private float electricityFee;
    private float otherFee;
    private float receivedValue;
    private float change;

    public BillingHistoryDTO() {
        this.billID = 0;
        this.payDate = null;
        this.userID = "";
        this.apartmentID = "";
        this.contractFee = 0;
        this.serviceFee = 0;
        this.waterFee = 0;
        this.electricityFee = 0;
        this.receivedValue = 0;
        this.change = 0;
    }

    public BillingHistoryDTO(int billID, Date payDate, String userID, String apartmentID, float contractFee, float serviceFee, float waterFee, float electrictyFee, float otherFee, float receivedValue, float change) {
        this.billID = billID;
        this.payDate = payDate;
        this.userID = userID;
        this.apartmentID = apartmentID;
        this.contractFee = contractFee;
        this.serviceFee = serviceFee;
        this.waterFee = waterFee;
        this.electricityFee = electrictyFee;
        this.otherFee = otherFee;
        this.receivedValue = receivedValue;
        this.change = change;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
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

    public float getContractFee() {
        return contractFee;
    }

    public void setContractFee(float contractFee) {
        this.contractFee = contractFee;
    }

    public float getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(float serviceFee) {
        this.serviceFee = serviceFee;
    }

    public float getWaterFee() {
        return waterFee;
    }

    public void setWaterFee(float waterFee) {
        this.waterFee = waterFee;
    }

    public float getElectricityFee() {
        return electricityFee;
    }

    public void setElectricityFee(float electricityFee) {
        this.electricityFee = electricityFee;
    }

    public float getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(float otherFee) {
        this.otherFee = otherFee;
    }

    public float getReceivedValue() {
        return receivedValue;
    }

    public void setReceivedValue(float receivedValue) {
        this.receivedValue = receivedValue;
    }

    public float getChange() {
        return change;
    }

    public void setChange(float change) {
        this.change = change;
    }

}
