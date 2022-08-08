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
                        <span class="title">Service Details</span>

                        <div class="fields">
                            <div class="input-field">
                                <label>Service Name ${requestScope.ADD_SERVICE_ERROR.serviceName}</label>
                                <input name="serviceName" type="text" class="form-control" placeholder="Enter service name" required="" value="${param.serviceName}" minlength="4" maxlength="60">     
                            </div>

                            <div class="input-field">
                                <label>Price  ${requestScope.ADD_SERVICE_ERROR.price}</label>
                                <input type="number" name ="price" class="form-control" placeholder="Enter service price" required value="${param.price}" step="0.01">
                            </div>
                            
                            <div class="input-field">
                                <label>Status  ${requestScope.ADD_SERVICE_ERROR.status}</label>
                                <select required="" name="status" value="${param.status}">
                                    <option value="true" >Enable</option>
                                    <option value="false" >Disable</option>
                                </select>
                            </div>

                            <div class="input-field">
                                <label>Description  ${requestScope.ADD_SERVICE_ERROR.description}</label>
                                <textarea style="width: 838px;height: 160px;" class="form-control" name="description" rows="6" cols="80" maxlength="1400">${param.description}</textarea>
                            </div>
                        </div>
                    </div>

                    <div class="details ID">
                       
                        <input type="hidden" name="type" value="${param.type}"/>
                        ${requestScope.ADD_SERVICE_ERROR.errorMessage}
                        ${requestScope.ADD_SERVICE_SUCCESS} 
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
                <span class="btnText"><a href="adminServicePage.jsp">Back to ${param.type} page</a></span>               
            </button> 
        </div>
        <%}%>
        <script src="js/addjavascript.js"></script>
        <script src="js/addUserJS.js"></script>
    </body>
</html>

