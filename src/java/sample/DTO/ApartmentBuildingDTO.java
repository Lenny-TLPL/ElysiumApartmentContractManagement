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
    private int districtID;
    private boolean status;
    
    public ApartmentBuildingDTO(){
        this.buildingID = 0;
        this.buildingName = "";
        this.districtID = 0;
        this.status = false;
    }

    public ApartmentBuildingDTO(int buildingID, String buildingName, int districtID, boolean status) {
        this.buildingID = buildingID;
        this.buildingName = buildingName;
        this.districtID = districtID;
        this.status = status;
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

    public int getDistrictID() {
        return districtID;
    }

    public void setDistrictID(int districtID) {
        this.districtID = districtID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
