<%-- 
    Document   : newjsp
    Created on : Jun 18, 2022, 1:59:07 AM
    Author     : Phi Long
--%>


<%@page import="sample.DTO.ServiceDTO"%>
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
            } else if (request.getAttribute("SERVICE_DETAIL") == null) {
                response.sendRedirect("MainController?action=Search&type=Service&search=");
            } else {%>
        <div class="container">
            <header>${requestScope.SERVICE_DETAIL.serviceName}</header>

            <form action="MainController" method="POST" enctype="multipart/form-data">
                <div class="form first">
                    <div class="details personal">
                        <span class="title">Service Details</span>

                        <div class="fields">
                            <div class="input-field">
                                <label>Service Name ${requestScope.UPDATE_SERVICE_ERROR.serviceName}</label>
                                <input name="serviceName" type="text" class="form-control" placeholder="Enter service name" required="" value="${requestScope.SERVICE_DETAIL.serviceName}" minlength="4" maxlength="60">     
                            </div>

                            <div class="input-field">
                                <label>Price ${requestScope.UPDATE_SERVICE_ERROR.price}</label>
                                <input type="number" name ="price" class="form-control" placeholder="Enter service price" required value="${requestScope.SERVICE_DETAIL.price}">
                            </div>
                            <%if (((ServiceDTO) request.getAttribute("SERVICE_DETAIL")).isStatus()) {%>
                            <div class="input-field">
                                <label>Status ${requestScope.UPDATE_SERVICE_ERROR.status}  </label>
                                <select id="status" required="" name="status" onchange="showColor(this)" style="color:#000; background-color: #669c19 " >
                                    <option value="true" style="color:#000; background-color: #669c19 ">Active</option>
                                    <option value="false" style="color:#000; background-color: #d3190d ">Inactive</option>
                                </select>
                            </div>
                            <%} else {%>
                            <div class="input-field">
                                <label>Status ${requestScope.UPDATE_SERVICE_ERROR.status} </label>
                                <select id="status" required="" name="status" onchange="showColor(this)" style="color:#000; background-color: #d3190d " >
                                    <option value="false" style="color:#000; background-color: #d3190d ">Inactive</option>
                                    <option value="true" style="color:#000; background-color: #669c19 ">Active</option>
                                </select>
                            </div>
                            <%}%>

                            <div class="input-field">
                                <label>Description ${requestScope.UPDATE_SERVICE_ERROR.description}</label>
                                <textarea style="width: 838px;height: 160px;" class="form-control" name="description" rows="6" cols="80" maxlength="1400" >${requestScope.SERVICE_DETAIL.description}</textarea>
                            </div>
                        </div>
                    </div>

                    <div class="details ID">
                        <input type="hidden" name="serviceID" value="${requestScope.SERVICE_DETAIL.serviceID}"/>
                        <input type="hidden" name="type" value="${param.type}"/>
                        ${requestScope.UPDATE_SERVICE_ERROR.errorMessage}
                        ${requestScope.UPDATE_SERVICE_SUCCESS} 
                        <div class="buttons">
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
            </form>
            <button class="backButton">
                <i class="uil uil-arrow-left"></i>
                <span class="btnText"><a href="adminServicePage.jsp">Back to ${param.type} page</a></span>               
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

