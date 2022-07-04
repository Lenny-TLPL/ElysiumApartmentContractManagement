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
    private String roleNamePriority;
    private boolean status;
    
    public PermissionDTO(){
        this.permissionID=0;
        this.permissionName=null;
        this.roleNamePriority=null;
        this.status=false;
    }

    public PermissionDTO(int permissionID, String permissionName, String roleNamePriority, boolean status) {
        this.permissionID = permissionID;
        this.permissionName = permissionName;
        this.roleNamePriority = roleNamePriority;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public int getPermissionID() {
        return permissionID;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public String getRoleNamePriority() {
        return roleNamePriority;
    }

    public void setRoleNamePriority(String roleNamePriority) {
        this.roleNamePriority = roleNamePriority;
    }

    public void setPermissionID(int permissionID) {
        this.permissionID = permissionID;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

}
