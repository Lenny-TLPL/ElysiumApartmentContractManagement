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
        <link rel="icon" type="image/png" sizes="21x21" href="images/logo1.png">

        <!--<title>Responsive Regisration Form </title>--> 
    </head>
    <body>
        <!--        else if (("Customer Resident").contains(request.getParameter("type"))&&request.getAttribute("APARTMENT_LIST") == null) {
                        request.getRequestDispatcher("MainController?action=GetMaterial&require=Apartment&type="+request.getParameter("type")+"&redirect=adminAddUserPage.jsp").forward(request, response);
                    }-->
        <% if (request.getParameter("type") == null) {
                response.sendRedirect("adminMainPage.jsp");
            } else if (("HR Manager Board Manager Employee").contains(request.getParameter("type")) && request.getAttribute("PERMISSION_LIST") == null) {
                response.sendRedirect("MainController?action=Search&type=" + request.getParameter("type") + "&search=");
            } else if (request.getAttribute("USER_DETAIL") == null) {
                response.sendRedirect("MainController?action=Search&type=" + request.getParameter("type") + "&search=");
            } else {
                String backValue = (request.getParameter("type")).replaceAll(" ", "");%>
        <div class="container">
            <header>${requestScope.USER_DETAIL.fullName}</header>

            <form action="MainController" method="POST" enctype="multipart/form-data" onsubmit="submitPermission()">
                <div class="form first">
                    <div class="details personal">
                        <span class="title">Personal Details</span>

                        <div class="fields">
                            <div class="input-field">
                                <label>UserID ${requestScope.UPDATE_USER_ERROR.userID}</label>
                                <input name="userID" "type="text" class="form-control" required="" value="${requestScope.USER_DETAIL.userID}" minlength="4" maxlength="60" readonly="">     
                            </div>
                            <%if (("Board Manager HR Manager Employee").contains((String) (request.getAttribute("ROLE_NAME")))) {%>
                            <div class="input-field">
                                <label>Role Name ${requestScope.UPDATE_USER_ERROR.roleName}  </label>
                                <select id="roleName" required="" name="roleName"  onchange="showPermission(this)">
                                    <option value="${requestScope.ROLE_NAME}">${requestScope.ROLE_NAME}</option>
                                    <option value="Board Manager">Board Manager</option>
                                    <option value="HR Manager">HR Manager</option>
                                    <option value="Employee">Employee</option>
                                </select>
                            </div>
                            <%} else {%>
                            <div class="input-field">
                                <label>Role Name ${requestScope.UPDATE_USER_ERROR.roleName}  </label>
                                <select required="" name="roleName">
                                    <option value="${requestScope.ROLE_NAME}">${requestScope.ROLE_NAME}</option>
                                    <option value="Resident">Resident</option>
                                    <option value="Customer">Customer</option>
                                </select>
                            </div>

                            <!--                            <div class="input-field">
                                                            <label>RoleName </label>    
                                                            <input name="roleName" readonly="" type="text" class="form-control" required="" value="${requestScope.ROLE_NAME}" minlength="4" maxlength="60">     
                                                        </div>-->
                            <%}%>
                            <div class="input-field">
                                <label>Full Name ${requestScope.UPDATE_USER_ERROR.fullName} </label>
                                <input name="fullName" "type="text" class="form-control" required="" value="${requestScope.USER_DETAIL.fullName}" minlength="4" maxlength="60" placeholder="Enter full name.">     
                            </div>

                            <div class="input-field">
                                <label>Date of Birth ${requestScope.UPDATE_USER_ERROR.birthday}  </label>
                                <input type="date" name ="birthday"class="form-control" required pattern="\d{4}-\d{2}-\d{2}"value="${requestScope.USER_DETAIL.birthDay}" placeholder="Enter birthday">
                            </div>

                            <div class="input-field">
                                <label>Email ${requestScope.UPDATE_USER_ERROR.email} </label>
                                <input type="email" id="example-email" name="email" class="form-control" value="${requestScope.USER_DETAIL.email}" placeholder="Enter email">
                            </div>

                            <div class="input-field">
                                <label>Phone Number ${requestScope.UPDATE_USER_ERROR.phone} </label>
                                <input name="phone"type="text" class="form-control" minlength="10" maxlength="11" required="" value="${requestScope.USER_DETAIL.phone}" placeholder="Enter phone number">
                            </div>

                            <div class="input-field">
                                <label>Gender ${requestScope.UPDATE_USER_ERROR.gender} </label>
                                <select required="" name="gender">
                                    <option value="${requestScope.USER_DETAIL.gender}">${requestScope.USER_DETAIL.gender}</option>
                                    <option value="male">Male</option>
                                    <option value="female">Female</option>
                                    <option value="other">Other</option>
                                </select>
                            </div>

                            <div class="input-field">
                                <label>Address ${requestScope.UPDATE_USER_ERROR.address} </label>
                                <input type="text" id="example-email" name="address" class="form-control" value="${requestScope.USER_DETAIL.address}" placeholder="Enter address">
                            </div>

                            <div class="input-field">
                                <label>CitizenID ${requestScope.UPDATE_USER_ERROR.citizenID} </label>
                                <input type="text" name ="citizenID" class="form-control" placeholder="Enter your citizen ID" maxlength="12" required=""value="${requestScope.USER_DETAIL.citizenID}">
                            </div>

                            <div class="input-field">
                                <label>Password </label>
                                <input type="password" readonly="" name="password" class="form-control" placeholder="Enter your password" minlength="6" maxlength="21" required=""value="${requestScope.USER_DETAIL.password}">
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
                        ${requestScope.UPDATE_USER_ERROR.errorMessage}
                        ${requestScope.UPDATE_USER_SUCCESS} 
                        ${requestScope.UPDATE_PERMISSION_ERROR}
                        <%if (request.getParameter("type").equals("Customer") || request.getParameter("type").equals("Resident")) {%>
                        <div class="buttons">
                            <button class="sumbit" type="reset">
                                <span class="btnText">Reset</span>
                            </button>     
                            <button class="submit" type="submit" name="action" value="Update">
                                <span class="btnText">Update</span>
                                <i class="uil uil-navigator"></i>
                            </button>
                        </div>
                        <%} else {%>
                        <div class="buttons">
                            <button class="sumbit" type="reset">
                                <span class="btnText">Reset</span>
                            </button>     
                            <button class="nextBtn" type="button" >
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

                         <div id="default">
                            <%  ArrayList<PermissionDTO> permissionList = (ArrayList<PermissionDTO>) request.getAttribute("PERMISSION_LIST");
                                ArrayList<PermissionDTO> userPermissionList = (ArrayList<PermissionDTO>) request.getAttribute("USER_PERMISSION_LIST");
                                if (permissionList != null) {
                                    if (permissionList.size() > 0) {
                                        for (int i = 0; i < permissionList.size(); i++) {
                                            if (userPermissionList.contains(permissionList.get(i).getPermissionName()) && request.getAttribute("ROLE_NAME").equals("Board Manager")) {%>
                            <div class="fields" id="permissionColumn" style="display:flex; flex-direction: row; float:left; width:30%">
                                <label style="width:90%"><input type="checkbox" checked="" name="permissions" value="<%=permissionList.get(i).getPermissionID()%>">  <%=permissionList.get(i).getPermissionName()%></label>
                            </div>
                            <%} else if (userPermissionList.contains(permissionList.get(i).getPermissionName()) && !request.getAttribute("ROLE_NAME").equals("Board Manager") && request.getAttribute("ROLE_NAME").equals(permissionList.get(i).getRoleNamePriority()) ) {%>
                            <div class="fields" id="permissionColumn" style="display:flex; flex-direction: row; float:left; width:30%">
                                <label style="width:90%"><input type="checkbox" checked="" name="permissions" value="<%=permissionList.get(i).getPermissionID()%>">  <%=permissionList.get(i).getPermissionName()%></label>
                            </div>
                            <%} else if(!userPermissionList.contains(permissionList.get(i).getPermissionName()) && !request.getAttribute("ROLE_NAME").equals("Board Manager") && request.getAttribute("ROLE_NAME").equals(permissionList.get(i).getRoleNamePriority())){%>
                            <div class="fields" id="permissionColumn" style="display:flex; flex-direction: row; float:left; width:30%">
                                <label style="width:90%" ><input type="checkbox"  name="permissions" value="<%=permissionList.get(i).getPermissionID()%>">  <%=permissionList.get(i).getPermissionName()%></label>
                            </div>
                            <%}
                                        }
                                    }
                                }%>
                        </div> 
                        <div id="Board Manager" style="display:none">
                            <%  permissionList = (ArrayList<PermissionDTO>) request.getAttribute("PERMISSION_LIST");
                                userPermissionList = (ArrayList<PermissionDTO>) request.getAttribute("USER_PERMISSION_LIST");
                                if (permissionList != null) {
                                    if (permissionList.size() > 0) {
                                        for (int i = 0; i < permissionList.size(); i++) {

                                            if (userPermissionList.contains(permissionList.get(i).getPermissionName())) {%>
                            <div class="fields" id="permissionColumn" style="display:flex; flex-direction: row; float:left; width:30%">
                                <label style="width:90%"><input type="checkbox" checked="" name="permissions" value="<%=permissionList.get(i).getPermissionID()%>">  <%=permissionList.get(i).getPermissionName()%></label>
                            </div>
                            <%} else {%>
                            <div class="fields" id="permissionColumn" style="display:flex; flex-direction: row; float:left; width:30%">
                                <label style="width:90%" ><input type="checkbox"  name="permissions" value="<%=permissionList.get(i).getPermissionID()%>">  <%=permissionList.get(i).getPermissionName()%></label>
                            </div>
                            <%}

                                        }
                                    }
                                }%>
                        </div>
                        <div id="HR Manager" style="display:none">
                            <%  permissionList = (ArrayList<PermissionDTO>) request.getAttribute("PERMISSION_LIST");
                                userPermissionList = (ArrayList<PermissionDTO>) request.getAttribute("USER_PERMISSION_LIST");
                                if (permissionList != null) {
                                    if (permissionList.size() > 0) {
                                        for (int i = 0; i < permissionList.size(); i++) {
                                            if (permissionList.get(i).getRoleNamePriority().equals("HR Manager") && userPermissionList.contains(permissionList.get(i).getPermissionName())) {
                            %>
                            <div class="fields" id="permissionColumn" style="display:flex; flex-direction: row; float:left; width:30%">
                                <label style="width:90%"><input type="checkbox" checked="" name="permissions" value="<%=permissionList.get(i).getPermissionID()%>">  <%=permissionList.get(i).getPermissionName()%></label>
                            </div>
                            <%} else if (permissionList.get(i).getRoleNamePriority().equals("HR Manager")) {%>
                            <div class="fields" id="permissionColumn" style="display:flex; flex-direction: row; float:left; width:30%">
                                <label style="width:90%" ><input type="checkbox"  name="permissions" value="<%=permissionList.get(i).getPermissionID()%>">  <%=permissionList.get(i).getPermissionName()%></label>
                            </div>
                            <%}

                                        }
                                    }
                                }%>
                        </div>
                        <div id="Employee" style="display:none">
                            <%  permissionList = (ArrayList<PermissionDTO>) request.getAttribute("PERMISSION_LIST");
                                userPermissionList = (ArrayList<PermissionDTO>) request.getAttribute("USER_PERMISSION_LIST");
                                if (permissionList != null) {
                                    if (permissionList.size() > 0) {
                                        for (int i = 0; i < permissionList.size(); i++) {
                                            if (permissionList.get(i).getRoleNamePriority().equals("Employee") && userPermissionList.contains(permissionList.get(i).getPermissionName())) {
                            %>
                            <div class="fields" id="permissionColumn" style="display:flex; flex-direction: row; float:left; width:30%">
                                <label style="width:90%"><input type="checkbox" checked="" name="permissions" value="<%=permissionList.get(i).getPermissionID()%>">  <%=permissionList.get(i).getPermissionName()%></label>
                            </div>
                            <%} else if (permissionList.get(i).getRoleNamePriority().equals("Employee")) {%>
                            <div class="fields" id="permissionColumn" style="display:flex; flex-direction: row; float:left; width:30%">
                                <label style="width:90%" ><input type="checkbox"  name="permissions" value="<%=permissionList.get(i).getPermissionID()%>">  <%=permissionList.get(i).getPermissionName()%></label>
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
                            <button class="submit" type="submit" name="action" value="Update" >
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
                <span class="btnText"><a href="admin<%=backValue%>Page.jsp">Back to ${param.type} page</a></span>               
            </button> 
        </div>
        <%}%>
        <script src="js/addjavascript.js"></script>
        <script src="js/addUserJS.js"></script>
        <script>
             Console.log(employee);
        function showColor(element)
                                    {
                                        if (element.value == "true") {
                                            document.getElementById('status').style.backgroundColor = "#669c19";
                                        } else if (element.value == "false") {
                                            document.getElementById('status').style.backgroundColor = "#d3190d";
                                        }

                                    }
                                    function submitPermission()
                                    {
                                        if(document.getElementById('roleName').value == "Board Manager"){
                                            document.getElementById('Board Manager').style.display = "block";
                                            document.getElementById('HR Manager').style.display = "none";
                                            document.getElementById('HR Manager').innerHTML = "X";  
                                            document.getElementById('Employee').style.display = "none";
                                            document.getElementById('Employee').innerHTML = "X";                                          
                                            document.getElementById('default').style.display = "none";
                                            document.getElementById('default').innerHTML = "X";       
                                        } else if(document.getElementById('roleName').value == "HR Manager"){
                                            document.getElementById('Board Manager').style.display = "none";
                                            document.getElementById('Board Manager').innerHTML = "X";  
                                            document.getElementById('HR Manager').style.display = "block";
                                            document.getElementById('Employee').style.display = "none";
                                            document.getElementById('Employee').innerHTML = "X";                                           
                                            document.getElementById('default').style.display = "none";
                                            document.getElementById('default').innerHTML = "X"; 
                                        } else if(document.getElementById('roleName').value == "Employee"){
                                            document.getElementById('Board Manager').style.display = "none";
                                            document.getElementById('Board Manager').innerHTML = "X"; 
                                            document.getElementById('HR Manager').style.display = "none";
                                            document.getElementById('HR Manager').innerHTML = "X";  
                                            document.getElementById('Employee').style.display = "block"; 
                                            document.getElementById('default').style.display = "none";
                                            document.getElementById('default').innerHTML = "X"; 
                                        }
//                                        if (element.value == "Board Manager") {
//                                            document.getElementById('Board Manager').style.display = "block";
//                                            document.getElementById('HR Manager').style.display = "none";
//                                            document.getElementById('Employee').style.display = "none";
//                                            document.getElementById('default').style.display = "none";
//                                        } else if (element.value == "HR Manager") {
//                                            document.getElementById('Board Manager').style.display = "none";
//                                            document.getElementById('HR Manager').style.display = "block";
//                                            document.getElementById('Employee').style.display = "none";
//                                            document.getElementById('default').style.display = "none";
//                                        } else if (element.value == "Employee") {
//                                            document.getElementById('Board Manager').style.display = "none";
//                                            document.getElementById('HR Manager').style.display = "none";
//                                            document.getElementById('Employee').style.display = "block";
//                                            document.getElementById('default').style.display = "none";
//                                        }
                                    }
                                    function showPermission(element){
                                        if (element.value == "Board Manager") {
                                            document.getElementById('Board Manager').style.display = "block";
                                            document.getElementById('HR Manager').style.display = "none";
                                            document.getElementById('Employee').style.display = "none";
                                            document.getElementById('default').style.display = "none";
                                        } else if (element.value == "HR Manager") {
                                            document.getElementById('Board Manager').style.display = "none";
                                            document.getElementById('HR Manager').style.display = "block";
                                            document.getElementById('Employee').style.display = "none";
                                            document.getElementById('default').style.display = "none";
                                        } else if (element.value == "Employee") {
                                            document.getElementById('Board Manager').style.display = "none";
                                            document.getElementById('HR Manager').style.display = "none";
                                            document.getElementById('Employee').style.display = "block";
                                            document.getElementById('default').style.display = "none";
                                        }
                                    }
        </script>
    </body>
</html>

