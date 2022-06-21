package sample.DTO;


import java.sql.Blob;
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
public class ContractDTO {
    private int contractID;
    private Date dateSign;
    private Blob contractImage;
    private String userID;
    private String apartmentID;
    private float value;
    private Date expiryDate;
    private int monthsOfDebt;
    private float interestRate;
    private String contractType;
    private boolean status;
    
    public ContractDTO(){
        this.contractID = 0;
        this.dateSign = null;
        this.contractImage = null ;
        this.userID = "";
        this.apartmentID = "";
        this.value = 0;
        this.expiryDate = null;
        this.monthsOfDebt = 0;
        this.interestRate = 0;
        this.contractType = "";
        this.status = false;
    }   

    public ContractDTO(int contractID, Date dateSign, Blob contractImage, String userID, String apartmentID, float value, Date expiryDate, int monthsOfDebt, float interestRate, String contractType, boolean status) {
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
    }
    
    public int getContractID() {
        return contractID;
    }

    public void setContractID(int contractID) {
        this.contractID = contractID;
    }

    public Date getDateSign() {
        return dateSign;
    }

    public void setDateSign(Date dateSign) {
        this.dateSign = dateSign;
    }

    public Blob getContractImage() {
        return contractImage;
    }

    public void setContractImage(Blob contractImage) {
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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getMonthsOfDebt() {
        return monthsOfDebt;
    }

    public void setMonthsOfDebt(int monthsOfDebt) {
        this.monthsOfDebt = monthsOfDebt;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
