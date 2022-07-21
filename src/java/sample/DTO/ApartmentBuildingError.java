/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DTO;

/**
 *
 * @author Phi Long
 */
public class ApartmentBuildingError {
    private String buildingID;
    private String buildingName;
    private String districtName;
    private String status;
    private String maxFloor;
    private String maxApartment;
    private String errorMessage;

    public ApartmentBuildingError() {
        this.buildingID="";
        this.buildingName="";
        this.districtName="";
        this.errorMessage="";
        this.maxApartment="";
        this.maxFloor="";
        this.status="";
    }

    public ApartmentBuildingError(String buildingID, String buildingName, String districtName, String status, String maxFloor, String maxApartment, String errorMessage) {
        this.buildingID = buildingID;
        this.buildingName = buildingName;
        this.districtName = districtName;
        this.status = status;
        this.maxFloor = maxFloor;
        this.maxApartment = maxApartment;
        this.errorMessage = errorMessage;
    }

    public String getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(String buildingID) {
        this.buildingID = buildingID;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(String maxFloor) {
        this.maxFloor = maxFloor;
    }

    public String getMaxApartment() {
        return maxApartment;
    }

    public void setMaxApartment(String maxApartment) {
        this.maxApartment = maxApartment;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    
}
