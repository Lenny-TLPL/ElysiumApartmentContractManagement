/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DTO;

/**
 *
 * @author Phi Long
 */
public class PermissionDTO {
    private int permissionID;
    private String permissionName;
    private boolean status;
    
    public void PermissionDTO(){
        this.permissionID=0;
        this.permissionName=null;
        this.status=false;
    }

    public PermissionDTO(int permissionID, String permissionName, boolean status) {
        this.permissionID = permissionID;
        this.permissionName = permissionName;
        this.status = status;
    }
    
    public int getPermissionID() {
        return permissionID;
    }

    public String getPermissionName() {
        return permissionName;
    }
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
