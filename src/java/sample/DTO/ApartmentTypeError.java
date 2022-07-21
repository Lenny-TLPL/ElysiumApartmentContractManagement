/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DTO;

/**
 *
 * @author Phi Long
 */
public class ApartmentTypeError {
    private String typeID;
    private String typeName;
    private String buyingPrice;
    private String leasingPrice;
    private String description;
    private String errorMessage;

    public ApartmentTypeError() {
        this.buyingPrice="";
        this.description="";
        this.errorMessage="";
        this.leasingPrice="";
        this.typeID="";
        this.typeName="";
    }

    public ApartmentTypeError(String typeID, String typeName, String buyingPrice, String leasingPrice, String description, String errorMessage) {
        this.typeID = typeID;
        this.typeName = typeName;
        this.buyingPrice = buyingPrice;
        this.leasingPrice = leasingPrice;
        this.description = description;
        this.errorMessage = errorMessage;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(String buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public String getLeasingPrice() {
        return leasingPrice;
    }

    public void setLeasingPrice(String leasingPrice) {
        this.leasingPrice = leasingPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    
    
    
}
