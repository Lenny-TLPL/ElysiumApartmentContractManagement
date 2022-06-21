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
public class ApartmentTypeDTO {
    private int typeID;
    private String typeName;
    private float buyingPrice;
    private float leasingPrice;
    private String description;
    
    public ApartmentTypeDTO(){
        this.typeID = 0;
        this.typeName = "";
        this.buyingPrice = 0;
        this.leasingPrice = 0;
        this.description = "";
    }

    public ApartmentTypeDTO(int typeID, String typeName, float buyingPrice, float leasingPrice, String description) {
        this.typeID = typeID;
        this.typeName = typeName;
        this.buyingPrice = buyingPrice;
        this.leasingPrice = leasingPrice;
        this.description = description;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public float getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(float buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public float getLeasingPrice() {
        return leasingPrice;
    }

    public void setLeasingPrice(float leasingPrice) {
        this.leasingPrice = leasingPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    
}
