<%-- 
    Document   : newjsp
    Created on : Jun 18, 2022, 1:59:07 AM
    Author     : Phi Long
--%>


<%@page import="sample.DTO.ApartmentBuildingDTO"%>
<%@page import="sample.DTO.ApartmentTypeDTO"%>
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
        <%  if (request.getParameter("type") == null) {
                response.sendRedirect("adminMainPage.jsp");
            } else if (request.getAttribute("APARTMENT_BUILDING_LIST") == null || request.getAttribute("APARTMENT_TYPE_LIST") == null) {
                request.getRequestDispatcher("MainController?action=GetMaterial&require=Apartment&type=" + request.getParameter("type") + "&redirect=adminAddApartmentPage.jsp").forward(request, response);
            } else {%>
        <div class="container">
            <header>Add New ${param.type}</header>

            <form action="MainController" method="POST" enctype="multipart/form-data">
                <div class="form first">
                    <div class="details personal">
                        <span class="title">Apartment Details</span>

                        <div class="fields">
                            <div class="input-field">
                                <label>Apartment Type  ${requestScope.ADD_APARTMENT_ERROR.typeName}</label>
                                <select name="typeName">
                                    <%  ArrayList<ApartmentTypeDTO> typeList = (ArrayList<ApartmentTypeDTO>) request.getAttribute("APARTMENT_TYPE_LIST");
                                        if (typeList != null) {
                                            if (typeList.size() > 0) {
                                                for (int i = 0; i < typeList.size(); i++) {
                                    %>
                                    <option value="<%=typeList.get(i).getTypeName()%>"><%=typeList.get(i).getTypeName()%></option>
                                    <%
                                                }
                                            }

                                        }
                                    %>
                                </select>
                            </div>

                            <div class="input-field">
                                <label>Area  ${requestScope.ADD_APARTMENT_ERROR.area}</label>
                                <input type="number" name ="area" class="form-control" placeholder="Enter area" required value="${param.area}" step="0.01">
                            </div>    

                            <div class="input-field">
                                <label>Building Name  ${requestScope.ADD_APARTMENT_ERROR.buildingName}</label>
                                <select name="buildingName">
                                    <%  ArrayList<ApartmentBuildingDTO> buildingList = (ArrayList<ApartmentBuildingDTO>) request.getAttribute("APARTMENT_BUILDING_LIST");
                                        if (buildingList != null) {
                                            if (buildingList.size() > 0) {
                                                for (int i = 0; i < buildingList.size(); i++) {
                                                    if (buildingList.get(i).isStatus()) {
                                    %>
                                    <option value="<%=buildingList.get(i).getBuildingName()%>"><%=buildingList.get(i).getBuildingName()%></option>
                                    <%}
                                                }
                                            }

                                        }
                                    %>
                                </select>
                            </div>    

                            <div class="input-field">
                                <label>Floor  ${requestScope.ADD_APARTMENT_ERROR.floor}</label>
                                <input type="number" name ="floor" class="form-control" placeholder="Enter floor" required value="${param.floor}">
                            </div>

                            <div class="input-field">
                                <label>Status  ${requestScope.ADD_APARTMENT_ERROR.apartmentStatus}</label>
                                <select required="" name="apartmentStatus" value="${param.apartmentStatus}">
                                    <option value="available" >Available</option>
                                    <option value="maintenance" >Maintenance</option>
                                </select>
                            </div>

                            <div class="input-field"></div>

                        </div>
                    </div>

                    <div class="details ID">

                        <input type="hidden" name="type" value="${param.type}"/>
                        ${requestScope.ADD_APARTMENT_ERROR.errorMessage}
                        ${requestScope.ADD_APARTMENT_SUCCESS} 
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

