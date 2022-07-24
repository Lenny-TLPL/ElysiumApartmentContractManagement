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
    private String billName;
    private Date payDate;
    private String userID;
    private String apartmentID;
    private float value;
    private float receivedValue;
    private float change;

    public BillingHistoryDTO() {
        this.billID = 0;
        this.billName = "";
        this.payDate = null;
        this.userID = "";
        this.apartmentID = "";
        this.value = 0;
        this.receivedValue = 0;
        this.change=0;
    }

    public BillingHistoryDTO(int billID, String billName, Date payDate, String userID, String apartmentID, float value, float receivedValue, float change) {
        this.billID = billID;
        this.billName = billName;
        this.payDate = payDate;
        this.userID = userID;
        this.apartmentID = apartmentID;
        this.value = value;
        this.receivedValue = receivedValue;
        this.change = change;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
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
