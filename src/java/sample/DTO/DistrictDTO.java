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
public class DistrictDTO {
    private int districtID;
    private String districtName;
    
    public DistrictDTO(){
        this.districtID = 0;
        this.districtName = "";
    }

    public DistrictDTO(int districtID, String districtName) {
        this.districtID = districtID;
        this.districtName = districtName;
    }

    public int getDistrictID() {
        return districtID;
    }

    public void setDistrictID(int districtID) {
        this.districtID = districtID;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }    
}
