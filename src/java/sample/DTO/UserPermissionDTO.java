/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DTO;

/**
 *
 * @author Phi Long
 */
public class UserPermissionDTO {
    private String userID;
    private int permissionID;

    public UserPermissionDTO() {
        this.userID = "";
        this.permissionID = 0;
    }

    public UserPermissionDTO(String userID, int permissionID) {
        this.userID = userID;
        this.permissionID = permissionID;
    }
    
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(int permissionID) {
        this.permissionID = permissionID;
    }

    
    
}
