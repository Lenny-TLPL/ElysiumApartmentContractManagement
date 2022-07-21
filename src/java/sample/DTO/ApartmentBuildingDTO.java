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
public class ApartmentBuildingDTO {
    private int buildingID;
    private String buildingName;
    private String districtName;
    private boolean status;
    private int maxFloor;
    private int maxApartment;
    
    public ApartmentBuildingDTO(){
        this.buildingID = 0;
        this.buildingName = "";
        this.districtName = "";
        this.status = false;
        this.maxApartment = 0;
        this.maxFloor = 0;
    }

    public ApartmentBuildingDTO(int buildingID, String buildingName, String districtName, boolean status, int maxFloor, int maxApartment) {
        this.buildingID = buildingID;
        this.buildingName = buildingName;
        this.districtName = districtName;
        this.status = status;
        this.maxFloor = maxFloor;
        this.maxApartment = maxApartment;
    }

    public int getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(int buildingID) {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(int maxFloor) {
        this.maxFloor = maxFloor;
    }

    public int getMaxApartment() {
        return maxApartment;
    }

    public void setMaxApartment(int maxApartment) {
        this.maxApartment = maxApartment;
    }
    
}
