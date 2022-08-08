<%-- 
    Document   : newjsp
    Created on : Jun 18, 2022, 1:59:07 AM
    Author     : Phi Long
--%>


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
            } else {%>
        <div class="container">
            <header>Add New ${param.type}</header>

            <form action="MainController" method="POST" enctype="multipart/form-data">
                <div class="form first">
                    <div class="details personal">
                        <span class="title">District Details</span>

                        <div class="fields">
                            <div class="input-field" style="width:100%">
                                <label>District Name ${requestScope.ADD_DISTRICT_ERROR.districtName}</label>
                                <input name="districtName" type="text" class="form-control" placeholder="Enter district name" required="" value="${param.districtName}" minlength="4" maxlength="60">     
                            </div>

                            <div class="input-field">
                                
                            </div>
                            
                            <div class="input-field">
                                
                            </div>

                            <div class="input-field">
                               
                            </div>
                        </div>
                    </div>

                    <div class="details ID">
                       
                        <input type="hidden" name="type" value="${param.type}"/>
                        ${requestScope.ADD_DISTRICT_ERROR.errorMessage}
                        ${requestScope.ADD_DISTRICT_SUCCESS} 
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
                <span class="btnText"><a href="adminApartmentPage.jsp">Back to apartment page</a></span>               
            </button> 
        </div>
        <%}%>
        <script src="js/addjavascript.js"></script>
        <script src="js/addUserJS.js"></script>
    </body>
</html>

