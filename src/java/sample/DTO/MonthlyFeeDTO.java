package sample.DTO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Quang
 */
public class MonthlyFeeDTO {

    private int monthlyFeeID;
    private String userID;
    private String apartmentID;
    private float waterFee;
    private float electricityFee;
    private float contractFee;
    private float serviceFee;
    private boolean status;

    public MonthlyFeeDTO() {
        this.monthlyFeeID = 0;
        this.userID = "";
        this.apartmentID = "";
        this.waterFee = 0;
        this.electricityFee = 0;
        this.contractFee = 0;
        this.serviceFee = 0;
        this.status = false;
    }

    public MonthlyFeeDTO(int monthlyFeeID, String userID, String apartmentID, float waterFee, float electricityFee, float contractFee, float serviceFee, boolean status) {
        this.monthlyFeeID = monthlyFeeID;
        this.userID = userID;
        this.apartmentID = apartmentID;
        this.waterFee = waterFee;
        this.electricityFee = electricityFee;
        this.contractFee = contractFee;
        this.serviceFee = serviceFee;
        this.status = status;
    }

    public int getMonthlyFeeID() {
        return monthlyFeeID;
    }

    public void setMonthlyFeeID(int monthlyFeeID) {
        this.monthlyFeeID = monthlyFeeID;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
}
