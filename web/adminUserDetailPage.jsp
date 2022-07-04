<%-- 
    Document   : newjsp
    Created on : Jun 18, 2022, 1:59:07 AM
    Author     : Phi Long
--%>

<%@page import="sample.DTO.UserDTO"%>
<%@page import="sample.DTO.PermissionDTO"%>
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

        <!--<title>Responsive Regisration Form </title>--> 
    </head>
    <body>
        <!--        else if (("Customer Resident").contains(request.getParameter("type"))&&request.getAttribute("APARTMENT_LIST") == null) {
                        request.getRequestDispatcher("MainController?action=GetMaterial&require=Apartment&type="+request.getParameter("type")+"&redirect=adminAddUserPage.jsp").forward(request, response);
                    }-->
        <% if (request.getParameter("type") == null) {
                response.sendRedirect("adminMainPage.jsp");
            } else if (("HR Manager Board Manager Employee").contains(request.getParameter("type")) && request.getAttribute("PERMISSION_LIST") == null) {
                response.sendRedirect("MainController?action=Search&type=Board Manager&search=");
            } else {%>
        <div class="container">
            <header>${requestScope.USER_DETAIL.fullName}</header>

            <form action="MainController" method="POST" enctype="multipart/form-data">
                <div class="form first">
                    <div class="details personal">
                        <span class="title">Personal Details</span>

                        <div class="fields">
                            <div class="input-field">
                                <label>UserID </label>
                                <input name="userID" "type="text" class="form-control" required="" value="${requestScope.USER_DETAIL.userID}" minlength="4" maxlength="60">     
                            </div>

                            <div class="input-field">
                                <label>RoleName </label>    
                                <input name="roleName" "type="text" class="form-control" required="" value="${requestScope.ROLE_NAME}" minlength="4" maxlength="60">     
                            </div>

                            <div class="input-field">
                                <label>Full Name </label>
                                <input name="fullName" "type="text" class="form-control" required="" value="${requestScope.USER_DETAIL.fullName}" minlength="4" maxlength="60">     
                            </div>

                            <div class="input-field">
                                <label>Date of Birth  </label>
                                <input type="text" name ="birthday"class="form-control" required pattern="\d{4}-\d{2}-\d{2}"value="${requestScope.USER_DETAIL.birthDay}">
                            </div>

                            <div class="input-field">
                                <label>Email  </label>
                                <input type="email" id="example-email" name="email" class="form-control" value="${requestScope.USER_DETAIL.email}">
                            </div>

                            <div class="input-field">
                                <label>Phone Number </label>
                                <input name="phone"type="text" class="form-control" minlength="10" maxlength="11" required="" value="${requestScope.USER_DETAIL.phone}">
                            </div>

                            <div class="input-field">
                                <label>Gender  </label>
                                <select required="" name="gender">
                                    <option value="${requestScope.USER_DETAIL.gender}">${requestScope.USER_DETAIL.gender}</option>
                                    <option value="male">Male</option>
                                    <option value="female">Female</option>
                                    <option value="other">Other</option>
                                </select>
                            </div>

                            <div class="input-field">
                                <label>Address  </label>
                                <input type="text" id="example-email" name="address" class="form-control" value="${requestScope.USER_DETAIL.address}">
                            </div>

                            <div class="input-field">
                                <label>CitizenID </label>
                                <input type="text" name ="citizenID" class="form-control" placeholder="Enter your citizen ID" maxlength="12" required=""value="${requestScope.USER_DETAIL.citizenID}">
                            </div>

                            <div class="input-field">
                                <label>Password </label>
                                <input type="password" name="password" class="form-control" placeholder="Enter your password" minlength="6" maxlength="21" required=""value="${requestScope.USER_DETAIL.password}">
                            </div>

                            <div class="input-field">
                                <label>Date Join  </label>
                                <input type="text" name ="dateJoin"class="form-control" required pattern="\d{4}-\d{2}-\d{2}"value="${requestScope.USER_DETAIL.dateJoin}" readonly="">
                            </div>

                            <%if (((UserDTO) request.getAttribute("USER_DETAIL")).isStatus()) {%>
                            <div class="input-field">
                                <label>Status  </label>
                                <select id="status" required="" name="status" onchange="showColor(this)" style="color:#000; background-color: #669c19 " >
                                    <option value="true" style="color:#000; background-color: #669c19 ">Active</option>
                                    <option value="false" style="color:#000; background-color: #d3190d ">Inactive</option>
                                </select>
                            </div>
                            <%} else {%>
                            <div class="input-field">
                                <label>Status  </label>
                                <select id="status" required="" name="status" onchange="showColor(this)" style="color:#000; background-color: #d3190d " >
                                    <option value="false" style="color:#000; background-color: #d3190d ">Inactive</option>
                                    <option value="true" style="color:#000; background-color: #669c19 ">Active</option>
                                </select>
                            </div>
                            <%}%>
                        </div>
                        <input type="hidden" name="type" value="${param.type}"/>
                        <%if (request.getParameter("type").equals("Customer") || request.getParameter("type").equals("Resident")) {%>
                        <div class="buttons">
                            <button class="sumbit" type="reset">
                                <span class="btnText">Reset</span>
                            </button>     
                            <button class="submit" type="submit">
                                <span class="btnText">Update</span>
                                <i class="uil uil-navigator"></i>
                            </button>
                        </div>
                        <%} else {%>
                        <div class="buttons">
                            <button class="sumbit" type="reset">
                                <span class="btnText">Reset</span>
                            </button>     
                            <button class="nextBtn" type="button">
                                <span class="btnText">Next</span>
                                <i class="uil uil-navigator"></i>
                            </button>
                        </div>
                        <%}%>
                    </div>
                </div>
                <%if (request.getParameter("type").equals("Customer") || request.getParameter("type").equals("Resident")) {%>
                <%} else {%>
                <div class="form second">
                    <div class="details address">
                        <span class="title">Permission</span>

                        <div  >
                            <%  ArrayList<PermissionDTO> permissionList = (ArrayList<PermissionDTO>) request.getAttribute("PERMISSION_LIST");
                                ArrayList<PermissionDTO> userPermissionList = (ArrayList<PermissionDTO>) request.getAttribute("USER_PERMISSION_LIST");
                                if (permissionList != null) {
                                    if (permissionList.size() > 0) {
                                        for (int i = 0; i < permissionList.size(); i++) {
                                            if (userPermissionList.contains(permissionList.get(i).getPermissionName())) {%>
                            <div class="fields">
                                <label ><input type="checkbox" checked="" name="permissions" value="<%=permissionList.get(i).getPermissionID()%>">  <%=permissionList.get(i).getPermissionName()%></label>
                            </div>
                            <%} else {%>
                            <div class="fields">
                                <label ><input type="checkbox"  name="permissions" value="<%=permissionList.get(i).getPermissionID()%>">  <%=permissionList.get(i).getPermissionName()%></label>
                            </div>
                            <%}
                                        }
                                    }
                                }%>
                        </div>

                        <div class="buttons" style="width: 200%">
                            <div class="backBtn">
                                <i class="uil uil-navigator"></i>
                                <span class="btnText">Back</span>
                            </div>      
                            <button class="sumbit" type="reset">
                                <span class="btnText">Reset</span>
                            </button>       
                            <button class="submit" type="submit" name="action" value="Update">
                                <span class="btnText">Update</span>
                                <i class="uil uil-navigator"></i>
                            </button> 
                        </div>                      
                    </div> 
                </div>
                <%}%>
            </form>
            <button class="backButton">
                <i class="uil uil-arrow-left"></i>
                <span class="btnText"><a href="adminDashBoardPage.jsp">Back to admin page</a></span>               
            </button> 
        </div>
        <%}%>
        <script src="js/addjavascript.js"></script>
        <script src="js/addUserJS.js"></script>
        <script>function showColor(element)
                                    {
                                        if (element.value == "true") {
                                            document.getElementById('status').style.backgroundColor = "#669c19";
                                        } else if (element.value == "false") {
                                            document.getElementById('status').style.backgroundColor = "#d3190d";
                                        }

                                    }</script>
    </body>
</html>

