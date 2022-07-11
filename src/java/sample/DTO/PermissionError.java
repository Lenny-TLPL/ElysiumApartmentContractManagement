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
public class PermissionError {
    private String permissionID;
    private String permissionName;
    private String roleNamePriority;
    private String status;
    private String errorMessage;
    
    public PermissionError(){
        this.permissionID = "";
        this.permissionName = "";
        this.roleNamePriority = "";
        this.status = "";
        this.errorMessage = "";
    }

    public PermissionError(String permissionID, String permissionName, String roleNamePriority, String status, String errorMessage) {
        this.permissionID = permissionID;
        this.permissionName = permissionName;
        this.roleNamePriority = roleNamePriority;
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public String getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(String permissionID) {
        this.permissionID = permissionID;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getRoleNamePriority() {
        return roleNamePriority;
    }

    public void setRoleNamePriority(String roleNamePriority) {
        this.roleNamePriority = roleNamePriority;
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
