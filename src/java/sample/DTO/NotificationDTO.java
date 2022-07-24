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
public class NotificationDTO implements Comparable<NotificationDTO> {
    private int notiID;
    private String notiHeader;
    private String notiContent;
    private Date notiDate;
    private boolean status;
            
    public NotificationDTO(){
        this.notiID = 0;
        this.notiHeader = "";
        this.notiContent = "";
        this.notiDate = null;
        this.status = false;
    }

    public NotificationDTO(int notiID, String notiHeader, String notiContent, Date notiDate, boolean status) {
        this.notiID = notiID;
        this.notiHeader = notiHeader;
        this.notiContent = notiContent;
        this.notiDate = notiDate;
        this.status = status;
    }

    public int getNotiID() {
        return notiID;
    }

    public void setNotiID(int notiID) {
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

    public Date getNotiDate() {
        return notiDate;
    }

    public void setNotiDate(Date notiDate) {
        this.notiDate = notiDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }  
    
    @Override
    public int compareTo(NotificationDTO o) {
        if (notiDate.after(o.notiDate) ) return -1;
        else if(notiDate.before(o.notiDate)) return 1;
        return 0;
    }
}
