/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DTO;

/**
 *
 * @author Phi Long
 */
public class DistrictError {
    private String districtName;
    private String errorMessage;
    public DistrictError() {
        districtName = "";
        errorMessage = "";
    }

    public DistrictError(String districtName, String errorMessage) {
        this.districtName = districtName;
        this.errorMessage = errorMessage;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    
    
}
