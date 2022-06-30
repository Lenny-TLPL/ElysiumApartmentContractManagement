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
public class PrivateNotificationError extends NotificationError{
    private String userID;
    
    public PrivateNotificationError(){
        super();
        this.userID = "";
    }

    public PrivateNotificationError(String userID, String errorMessage) {
        this.userID = userID;
    }

    public PrivateNotificationError(String userID) {
        this.userID = userID;
    }

    public PrivateNotificationError(String userID, String notiID, String notiHeader, String notiContent, String notiDate, String status, String errorMessage) {
        super(notiID, notiHeader, notiContent, notiDate, status, errorMessage);
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }   
}
