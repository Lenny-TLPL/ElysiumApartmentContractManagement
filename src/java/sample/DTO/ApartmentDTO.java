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
    private float area;
    private String apartmentStatus;
    private String typeName;
    private int floor;
    private String buildingName;
    private String userID;
    
    public ApartmentDTO(){
        this.apartmentID = "";
        this.area = 0;
        this.apartmentStatus = "";
        this.typeName = "";
        this.floor = 0;
        this.buildingName = "";
        this.userID = "";
    }

    public ApartmentDTO(String apartmentID, float area, String apartmentStatus, String typeName, int floor, String buildingName, String userID) {
        this.apartmentID = apartmentID;
        this.area = area;
        this.apartmentStatus = apartmentStatus;
        this.typeName = typeName;
        this.floor = floor;
        this.buildingName = buildingName;
        this.userID = userID;
    }

    public String getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(String apartmentID) {
        this.apartmentID = apartmentID;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public String getApartmentStatus() {
        return apartmentStatus;
    }

    public void setApartmentStatus(String apartmentStatus) {
        this.apartmentStatus = apartmentStatus;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }              
}
