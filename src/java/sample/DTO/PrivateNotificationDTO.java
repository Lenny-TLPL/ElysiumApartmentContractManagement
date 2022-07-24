package sample.DTO;


import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Quang
 */
public class PrivateNotificationDTO extends NotificationDTO  {

    private String userID;
    
    public PrivateNotificationDTO(){
        super();
        this.userID = "";
    }

    public PrivateNotificationDTO(int notiID, String notiHeader, String notiContent, Date notiDate, String userID, boolean status) {
        super(notiID,notiHeader,notiContent,notiDate,status);
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
}