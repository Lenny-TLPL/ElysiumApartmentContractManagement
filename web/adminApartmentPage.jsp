<%@page import="sample.DTO.ApartmentTypeDTO"%>
<%@page import="sample.DTO.ApartmentBuildingDTO"%>
<%@page import="sample.DTO.DistrictDTO"%>
<%@page import="sample.DTO.ApartmentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--=== Coding by CodingLab | www.codinglabweb.com === -->
<html lang="en">
    <head>
        <!--        <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">-->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<title> Responsiive Admin Dashboard | CodingLab </title>-->
        <link rel="stylesheet" href="css/admincss.css">
        <!-- Boxicons CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <link rel="icon" type="image/png" sizes="21x21" href="images/logo1.png">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%if (request.getAttribute("APARTMENT_LIST") == null && request.getAttribute("APARTMENT_BUILDING_LIST") == null && request.getAttribute("APARTMENT_TYPE_LIST") == null && request.getAttribute("DISTRICT_LIST") == null) {
                response.sendRedirect("MainController?action=Search&type=Apartment&search=");
            }%>
        <div class="sidebar">
            <div class="logo-details">
<!--                <i class='bx bxl-c-plus-plus'></i>-->
                <!--      <img src="assets/images/logo1.png" style="width:10%" alt="homepage" class="dark-logo" />-->
                <span class="logo_name">
                    <a href="adminMainPage.jsp" style="text-decoration: none;display: flex; justify-content: center">
                        <img src="images/logo1.png" style="width:21%;" alt="homepage" class="dark-logo" />
                        <span style="color:#FFF; text-decoration: none;margin-bottom: auto; margin-top: 20px;margin-right: 80px">ELYSIUM</span> 
                    </a>
                </span>
            </div>
            <ul class="nav-links">
                <li>
                    <a href="adminDashBoardPage.jsp" >
                        <i class='bx bx-grid-alt' ></i>
                        <span class="links_name">Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="adminBoardManagerPage.jsp" >
                        <i class='bx bx-user' ></i>
                        <span class="links_name">Board Manager</span>
                    </a>
                </li>
                <li>
                    <a href="adminHRManagerPage.jsp">
                        <i class='bx bx-user' ></i>
                        <span class="links_name">HR Manager</span>
                    </a>
                </li>
                <li>
                    <a href="adminEmployeePage.jsp">
                        <i class='bx bx-user' ></i>
                        <span class="links_name">Employee</span>
                    </a>
                </li>
                <li>
                    <a href="adminResidentPage.jsp">
                        <i class='bx bx-user' ></i>
                        <span class="links_name">Resident</span>
                    </a>
                </li>
                <li>
                    <a href="adminCustomerPage.jsp">
                        <i class='bx bx-user' ></i>
                        <span class="links_name">Customer</span>
                    </a>
                </li>
                <li>
                    <a href="adminServicePage.jsp">
                        <i class='bx bx-book-alt' ></i>
                        <span class="links_name">Service</span>
                    </a>
                </li>
                <li>
                    <a href="adminContractPage.jsp">
                        <i class='bx bx-book-alt' ></i>
                        <span class="links_name">Contract</span>
                    </a>
                </li>
                <li>
                    <a href="adminApartmentPage.jsp" class="active">
                        <i class='bx bx-book-alt' ></i>
                        <span class="links_name">Apartment</span>
                    </a>
                </li>
                <li>
                    <a href="adminNotificationPage.jsp">
                        <i class='bx bx-message' ></i>
                        <span class="links_name">Notification</span>
                    </a>
                </li>
                <li>
                    <a href="adminMonthlyFeePage.jsp">
                        <i class='bx bx-coin-stack' ></i>
                        <span class="links_name">Monthly fee</span>
                    </a>
                </li>
                <li>
                    <a href="adminBillingHistoryPage.jsp">
                        <i class='bx bx-coin-stack' ></i>
                        <span class="links_name">Billing history</span>
                    </a>
                </li>
                <li>
                    <a href="adminPermissionPage.jsp">
                        <i class='bx bx-key' ></i>
                        <span class="links_name">Permission</span>
                    </a>
                </li>
                <li class="log_out">
                    <a href="MainController?action=Logout">
                        <i class='bx bx-log-out'></i>
                        <span class="links_name">Log out</span>
                    </a>
                </li>
            </ul>
        </div>
        <section class="home-section">
            <nav>
                <div class="sidebar-button">
                    <!--                    <i class='bx bx-menu sidebarBtn'></i>
                                        <span class="dashboard">Dashboard</span>-->
                </div>
                <form action="MainController"class="search-box">
                    <div>
                        <input type="hidden" name="type" value="Apartment">
                        <input class="search-box" style="width:96.5%"type="text" name="search"  placeholder="Search...." value="${param.search}">
                        <button type="submit" name="action" value="Search"><i class='bx bx-search' ></i> </button>
                    </div>
                </form>

                <div >
                    <!--<img src="images/profile.jpg" alt="">-->
                    <span class="admin_name">${sessionScope.LOGIN_USER.fullName}</span>

                </div>
            </nav>
            <div class="home-content">
                <div class="sales-boxes">
                    <div class="recent-sales box">
                        <div class="title" style="float:left">APARTMENT LOCATION  ${requestScope.UPDATE_DISTRICT_SUCCESS} ${requestScope.UPDATE_DISTRICT_ERROR.errorMessage} ${requestScope.UPDATE_DISTRICT_ERROR.districtName}</div>
                        <a href="adminAddDistrictPage.jsp?type=District"style="float:right" >
                            <i class="bx  bx-plus-circle" >ADD</i>
                        </a>
                        <table border="1" id="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>NAME</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%  ArrayList<DistrictDTO> districtList = (ArrayList<DistrictDTO>) request.getAttribute("DISTRICT_LIST");
                                    if (districtList != null) {
                                        if (districtList.size() > 0) {
                                            for (int i = 0; i < districtList.size(); i++) {%>
                            <form action="MainController" method="POST">
                                <tr>
                                    <td> <input style="width:100%" type="text" name="districtID" value="<%=districtList.get(i).getDistrictID()%>" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="text" name="districtName" value="<%=districtList.get(i).getDistrictName()%>" /></td>
                                    <td> <input style="width:100%" type="submit" name="action" value="Update" readonly="readonly"/></td>
                                <input style="width:100%" type="hidden" name="type" value="District" />

                                </tr>  
                            </form>
                            <%}
                                    }
                                }%>

                            </tbody>
                        </table>
                    </div>                    
                </div>
            </div>
            <div class="home-content">
                <div class="sales-boxes">
                    <div class="recent-sales box">
                        <div class="title" style="float:left">APARTMENT BUILDING</div>
                        <a href="adminAddApartmentBuildingPage.jsp?type=Apartment Building"style="float:right" >
                            <i class="bx  bx-plus-circle" >ADD</i>
                        </a>
                        <table border="1" id="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>DistrictName</th>
                                    <th>Max Floor</th>
                                    <th>Max Apartment</th>
                                    <th>Status</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%!int districtID = 0;%>
                                <%  ArrayList<ApartmentBuildingDTO> buildingList = (ArrayList<ApartmentBuildingDTO>) request.getAttribute("APARTMENT_BUILDING_LIST");
                                    if (buildingList != null) {
                                        if (buildingList.size() > 0) {
                                            for (int i = 0; i < buildingList.size(); i++) {%>
                            <form action="MainController" method="POST">
                                <tr>
                                    <td> <input style="width:100%" type="text" name="buildingID" value="<%=buildingList.get(i).getBuildingID()%>" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="text" name="buildingName" value="<%=buildingList.get(i).getBuildingName()%>"/></td>
                                    <input style="width:100%" type="hidden" name="districtName" value="<%=buildingList.get(i).getDistrictName()%>" readonly="readonly"/>
                                    <td><select  style="width:100%"  name="districtID">
                                            
                                            <option value="<%=buildingList.get(i).getDistrictName()%>"><%=buildingList.get(i).getDistrictName()%></option>
                                            <%
                                                if (districtList != null) {
                                                    if (districtList.size() > 0) {
                                                        for (int j = 0; j < districtList.size(); j++) {
                                            %>
                                            <option value="<%=districtList.get(j).getDistrictID()%>"><%=districtList.get(j).getDistrictName()%></option>
                                            <%
                                                        }
                                                    }

                                                }
                                            %>
                                        </select></td>
                                    <td> <input style="width:100%" type="number" name="maxFloor" value="<%=buildingList.get(i).getMaxFloor()%>"/></td>
                                    <td> <input style="width:100%" type="number" name="maxApartment" value="<%=buildingList.get(i).getMaxApartment()%>" /></td>
                                <input type="hidden" name="status" value="<%=buildingList.get(i).isStatus()%>" readonly="readonly"/>
                                <input type="hidden" name="type" value="Apartment Building" readonly="readonly"/>
                                <%if (buildingList.get(i).isStatus() == true) {%>
                                <td> <input style="width:100%; background-color: #669c19" type="text"value="Available" readonly="readonly"/></td>
                                    <%} else {%>
                                <td> <input style="width:100%; background-color: #d3190d" type="text" value="Full" readonly="readonly"/></td>
                                    <%}%>                     
                                    <%if (buildingList.get(i).isStatus()) {%>
                                <td> <input style="width:100%" type="submit" name="action" value="Disable" readonly="readonly"/></td>
                                    <%} else {%>
                                <td> <input style="width:100%" type="submit" name="action" value="Enable" readonly="readonly"/></td>
                                    <%}%>
                                </tr>  
                            </form>
                            <%}
                                    }
                                }%>

                            </tbody>
                        </table>
                    </div>                    
                </div>
            </div>                              
            <div class="home-content">
                <div class="sales-boxes">
                    <div class="recent-sales box">
                        <div class="title" style="float:left">APARTMENT TYPE</div>
                        <a href="adminAddApartmentTypePage.jsp?type=Apartment Type"style="float:right" >
                            <i class="bx  bx-plus-circle" >ADD</i>
                        </a>
                        <table border="1" id="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Buying Price</th>
                                    <th>Leasing Price</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%  ArrayList<ApartmentTypeDTO> typeList = (ArrayList<ApartmentTypeDTO>) request.getAttribute("APARTMENT_TYPE_LIST");
                                    if (typeList != null) {
                                        if (typeList.size() > 0) {
                                            for (int i = 0; i < typeList.size(); i++) {%>
                            <form action="MainController" method="POST">
                                <tr>
                                    <td> <input style="width:100%" type="text" name="typeID" value="<%=typeList.get(i).getTypeID()%>" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="text" name="typeName" value="<%=typeList.get(i).getTypeName()%>" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="number" name="buyingPrice" value="<%=typeList.get(i).getBuyingPrice()%>" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="number" name="leasingPrice" value="<%=typeList.get(i).getLeasingPrice()%>" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="submit" name="action" value="View Detail" readonly="readonly"/></td>
                                </tr>  
                            </form>
                            <%}
                                    }
                                }%>

                            </tbody>
                        </table>
                    </div>                    
                </div>
            </div>
            <div class="home-content">
                <div class="sales-boxes">
                    <div class="recent-sales box">
                        <div class="title" style="float:left">APARTMENT</div>
                        <a href="adminAddApartmentPage.jsp?type=Apartment"style="float:right" >
                            <i class="bx  bx-plus-circle" >ADD</i>
                        </a>
                        <table border="1" id="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Type</th>
                                    <th>Area</th>
                                    <th>Floor</th>
                                    <th>Building</th>
                                    <th>UserID</th>
                                    <th>Status</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%  ArrayList<ApartmentDTO> apartmentList = (ArrayList<ApartmentDTO>) request.getAttribute("APARTMENT_LIST");
                                    if (apartmentList != null) {
                                        if (apartmentList.size() > 0) {
                                            for (int i = 0; i < apartmentList.size(); i++) {%>
                            <form action="MainController" method="POST">
                                <tr>
                                    <td> <input style="width:100%" type="text" name="apartmentID" value="<%=apartmentList.get(i).getApartmentID()%>" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="text" name="apartmentType" value="<%=apartmentList.get(i).getTypeName()%>" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="text" name="area" value="<%=apartmentList.get(i).getArea()%>" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="number" name="floor" value="<%=apartmentList.get(i).getFloor()%>" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="text" name="buildingName" value="<%=apartmentList.get(i).getBuildingName()%>" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="text" name="userID" value="<%=apartmentList.get(i).getUserID()%>" readonly="readonly"/></td>
                                <input type="hidden" name="apartmentStatus" value="<%=apartmentList.get(i).getApartmentStatus()%>" readonly="readonly"/>
                                <input type="hidden" name="type" value="Apartment" readonly="readonly"/>
                                <%if (("available").contains(apartmentList.get(i).getApartmentStatus())) {%>
                                <td> <input style="width:100%; background-color: #669c19" type="text"value="<%=apartmentList.get(i).getApartmentStatus()%>" readonly="readonly"/></td>
                                    <%} else {%>
                                <td> <input style="width:100%; background-color: #d3190d" type="text" value="<%=apartmentList.get(i).getApartmentStatus()%>" readonly="readonly"/></td>
                                    <%}%>
                                <td> <input style="width:100%" type="submit" name="action" value="View Detail" readonly="readonly"/></td> 

                                <%if (("available").contains(apartmentList.get(i).getApartmentStatus())) {%>
                                <td> <input style="width:100%" type="submit" name="action" value="Disable" readonly="readonly"/></td>
                                    <%} else if (("maintenance").contains(apartmentList.get(i).getApartmentStatus())) {%>
                                <td> <input style="width:100%" type="submit" name="action" value="Enable" readonly="readonly"/></td>
                                    <%} else {%>                                
                                    <%}%>
                                </tr>
                            </form>
                            <%}
                                    }
                                }%>

                            </tbody>
                        </table>
                    </div>                    
                </div>
            </div>
        </section>

        <script>
            let sidebar = document.querySelector(".sidebar");
            let sidebarBtn = document.querySelector(".sidebarBtn");
            sidebarBtn.onclick = function () {
                sidebar.classList.toggle("active");
                if (sidebar.classList.contains("active")) {
                    sidebarBtn.classList.replace("bx-menu", "bx-menu-alt-right");
                } else
                    sidebarBtn.classList.replace("bx-menu-alt-right", "bx-menu");
            }
        </script>

    </body>
</html>

