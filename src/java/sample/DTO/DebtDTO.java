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
public class DebtDTO {
    private int debtID;
    private String userID;
    private float waterDebt;
    private float electricityDebt;
    private float contractDebt;
    private float serviceDebt;
    private boolean status;
    
    public DebtDTO(){
        this.debtID = 0;
        this.userID = "";
        this.waterDebt = 0;
        this.electricityDebt = 0;
        this.contractDebt = 0;
        this.serviceDebt = 0;
        this.status = false;
    }

    public DebtDTO(int debtID, String userID, float waterDebt, float electricityDebt, float contractDebt, float serviceDebt, boolean status) {
        this.debtID = debtID;
        this.userID = userID;
        this.waterDebt = waterDebt;
        this.electricityDebt = electricityDebt;
        this.contractDebt = contractDebt;
        this.serviceDebt = serviceDebt;
        this.status = status;
    }

    public int getDebtID() {
        return debtID;
    }

    public void setDebtID(int debtID) {
        this.debtID = debtID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public float getWaterDebt() {
        return waterDebt;
    }

    public void setWaterDebt(float waterDebt) {
        this.waterDebt = waterDebt;
    }

    public float getElectricityDebt() {
        return electricityDebt;
    }

    public void setElectricityDebt(float electricityDebt) {
        this.electricityDebt = electricityDebt;
    }

    public float getContractDebt() {
        return contractDebt;
    }

    public void setContractDebt(float contractDebt) {
        this.contractDebt = contractDebt;
    }

    public float getServiceDebt() {
        return serviceDebt;
    }

    public void setServiceDebt(float serviceDebt) {
        this.serviceDebt = serviceDebt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }   
}
