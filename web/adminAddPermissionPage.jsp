<%-- 
    Document   : newjsp
    Created on : Jun 18, 2022, 1:59:07 AM
    Author     : Phi Long
--%>


<%@page import="sample.DTO.RoleDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--=== Coding by CodingLab | www.codinglabweb.com === -->
<html lang="en">
    <head>
        <!--        <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">-->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!----======== CSS ======== -->
        <link rel="stylesheet" href="css/addcss.css">

        <!----===== Iconscout CSS ===== -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
        <link rel="icon" type="image/png" sizes="21x21" href="images/logo1.png">

        <!--<title>Responsive Regisration Form </title>--> 
    </head>
    <body>
        <!--        else if (("Customer Resident").contains(request.getParameter("type"))&&request.getAttribute("APARTMENT_LIST") == null) {
                        request.getRequestDispatcher("MainController?action=GetMaterial&require=Apartment&type="+request.getParameter("type")+"&redirect=adminAddUserPage.jsp").forward(request, response);
                    }-->
        <% if (request.getParameter("type") == null) {
                response.sendRedirect("adminMainPage.jsp");
            } else if (request.getAttribute("ROLE_LIST") == null) {
                request.getRequestDispatcher("MainController?action=GetMaterial&require=Role&type=" + request.getParameter("type") + "&redirect=adminAddPermissionPage.jsp&search=").forward(request, response);
            } else {%>
        <div class="container">
            <header>Add New ${param.type}</header>

            <form action="MainController" method="POST" enctype="multipart/form-data">
                <div class="form first"style="width:100%">
                    <div class="details personal" style="width:100%">
                        <span class="title">Permission Details</span>

                        <div class="fields">
                            <div class="input-field" style="width:44%" >
                                <label>Permission Name  ${requestScope.ADD_PERMISSION_ERROR.permissionName}</label>
                                <input name="permissionName" type="text" class="form-control" placeholder="Enter permission name" required="" value="${param.permissionName}" minlength="4" maxlength="60">     
                            </div>

                            <div class="input-field"style="width:21%">
                                <label>Status  ${requestScope.ADD_PERMISSION_ERROR.status}</label>
                                <select required="" name="status" value="${param.status}">
                                    <option value="true" >Enable</option>
                                    <option value="false" >Disable</option>
                                </select>
                            </div>

                            <div class="input-field"style="width:24%">
                                <label>Role Priority  ${requestScope.ADD_PERMISSION_ERROR.roleNamePriority}</label>
                                <select name="rolePriority">
                                    <%  ArrayList<RoleDTO> roleList = (ArrayList<RoleDTO>) request.getAttribute("ROLE_LIST");
                                        if (roleList != null) {
                                            if (roleList.size() > 0) {
                                                for (int i = 0; i < roleList.size(); i++) {
                                                    if (("Customer Resident").contains(roleList.get(i).getRoleName())) {
                                                    } else {%>
                                    <option value="<%=roleList.get(i).getRoleName()%>"><%=roleList.get(i).getRoleName()%></option>
                                    <%}
                                                }
                                            }

                                        }
                                    %>
                                </select>
                            </div>                                      
                        </div>

                    </div>

                    <div class="details ID">

                        <input type="hidden" name="type" value="${param.type}"/>
                        ${requestScope.ADD_PERMISSION_ERROR.errorMessage}
                        ${requestScope.ADD_PERMISSION_SUCCESS} 
                        <div class="buttons">
                            <button class="sumbit" type="reset">
                                <span class="btnText">Reset</span>
                            </button>     
                            <button class="submit" type="submit" name="action" value="Add">
                                <span class="btnText">Submit</span>
                                <i class="uil uil-navigator"></i>
                            </button> 
                        </div>
                    </div>
                </div>
            </form>
            <button class="backButton">
                <i class="uil uil-arrow-left"></i>
                <span class="btnText"><a href="adminPermissionPage.jsp">Back to ${param.type} page</a></span>               
            </button> 
        </div>
        <%}%>
        <script src="js/addjavascript.js"></script>
        <script src="js/addUserJS.js"></script>
    </body>
</html>

