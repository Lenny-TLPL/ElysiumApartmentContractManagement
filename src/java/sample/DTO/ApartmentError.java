/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DTO;

/**
 *
 * @author Phi Long
 */
public class ApartmentError {

    private String apartmentID;
    private String area;
    private String apartmentStatus;
    private String typeName;
    private String floor;
    private String buildingName;
    private String userID;
    private String errorMessage;

    public ApartmentError() {
        this.apartmentID = "";
        this.area = "";
        this.apartmentStatus = "";
        this.typeName = "";
        this.floor = "";
        this.buildingName = "";
        this.userID = "";
        this.errorMessage = "";
    }

    public ApartmentError(String apartmentID, String area, String apartmentStatus, String typeName, String floor, String buildingName, String userID, String errorMessage) {
        this.apartmentID = apartmentID;
        this.area = area;
        this.apartmentStatus = apartmentStatus;
        this.typeName = typeName;
        this.floor = floor;
        this.buildingName = buildingName;
        this.userID = userID;
        this.errorMessage = errorMessage;
    }

    public String getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(String apartmentID) {
        this.apartmentID = apartmentID;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
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

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
