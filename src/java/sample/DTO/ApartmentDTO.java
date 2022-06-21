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
public class ApartmentDTO {
    private String apartmentID;
    private String apartmentStatus;
    private int typeID;
    private int floor;
    private int buildingID;
    private String userID;
    
    public ApartmentDTO(){
        this.apartmentID = "";
        this.apartmentStatus = "";
        this.typeID = 0;
        this.floor = 0;
        this.buildingID = 0;
        this.userID = "";
    }

    public ApartmentDTO(String apartmentID, String apartmentStatus, int typeID, int floor, int buildingID, String userID) {
        this.apartmentID = apartmentID;
        this.apartmentStatus = apartmentStatus;
        this.typeID = typeID;
        this.floor = floor;
        this.buildingID = buildingID;
        this.userID = userID;
    }

    public String getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(String apartmentID) {
        this.apartmentID = apartmentID;
    }

    public String getApartmentStatus() {
        return apartmentStatus;
    }

    public void setApartmentStatus(String apartmentStatus) {
        this.apartmentStatus = apartmentStatus;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }                      
}
