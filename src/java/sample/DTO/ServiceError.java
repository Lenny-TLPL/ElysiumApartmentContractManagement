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
public class ServiceError {

    private String serviceID;
    private String serviceName;
    private String description;
    private String price;
    private String status;
    private String errorMessage;

    public ServiceError() {
        this.serviceID = "";
        this.serviceName = "";
        this.description = "";
        this.price = "";
        this.status = "";
        this.errorMessage = "";
    }

    public ServiceError(String serviceID, String serviceName, String description, String price, String status, String errorMessage) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
