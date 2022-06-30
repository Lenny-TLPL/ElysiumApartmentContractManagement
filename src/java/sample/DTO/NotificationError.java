/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.DTO;

/**
 *
 * @author Quang
 */
public class NotificationError {
    private String notiID;
    private String notiHeader;
    private String notiContent;
    private String notiDate;
    private String status;
    private String errorMessage;
            
    public NotificationError(){
        this.notiID = "";
        this.notiHeader = "";
        this.notiContent = "";
        this.notiDate = "";
        this.status = "";
        this.errorMessage = "";
    }

    public NotificationError(String notiID, String notiHeader, String notiContent, String notiDate, String status, String errorMessage) {
        this.notiID = notiID;
        this.notiHeader = notiHeader;
        this.notiContent = notiContent;
        this.notiDate = notiDate;
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public String getNotiID() {
        return notiID;
    }

    public void setNotiID(String notiID) {
        this.notiID = notiID;
    }

    public String getNotiHeader() {
        return notiHeader;
    }

    public void setNotiHeader(String notiHeader) {
        this.notiHeader = notiHeader;
    }

    public String getNotiContent() {
        return notiContent;
    }

    public void setNotiContent(String notiContent) {
        this.notiContent = notiContent;
    }

    public String getNotiDate() {
        return notiDate;
    }

    public void setNotiDate(String notiDate) {
        this.notiDate = notiDate;
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
