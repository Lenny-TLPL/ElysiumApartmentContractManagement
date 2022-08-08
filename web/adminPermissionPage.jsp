
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
        <!--<title> Responsiive Admin Dashboard | CodingLab </title>-->
        <link rel="stylesheet" href="css/admincss.css">
        <!-- Boxicons CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <link rel="icon" type="image/png" sizes="21x21" href="images/logo1.png">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%if (request.getAttribute("PERMISSION_LIST") == null) {
                response.sendRedirect("MainController?action=Search&type=Permission&search=");
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
                    <a href="adminBoardManagerPage.jsp">
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
                    <a href="adminServicePage.jsp" >
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
                    <a href="adminApartmentPage.jsp">
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
                    <a href="adminPermissionPage.jsp" class="active">
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
                        <input type="hidden" name="type" value="Permission">
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
                        <div class="title" style="float:left">PERMISSION ${requestScope.UPDATE_PERMISSION_SUCCESS} ${requestScope.UPDATE_PERMISSION_ERROR.errorMessage}</div>
                        <a href="adminAddPermissionPage.jsp?type=Permission"style="float:right" >
                            <i class="bx  bx-plus-circle" >ADD</i>
                        </a>
                        <div class="title"></div>
                        <table border="1" id="table">
                            <thead>
                                <tr>
                                    <th style="width:4%">ID</th>
                                    <th>Name</th>                                                                  
                                    <th>Role Priority</th>
                                    <th>Status</th>
                                    <th>View Detail</th>
                                    <th>Update</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%  ArrayList<PermissionDTO> permissionList = (ArrayList<PermissionDTO>) request.getAttribute("PERMISSION_LIST");
                                    if (permissionList != null) {
                                        if (permissionList.size() > 0) {
                                            for (int i = 0; i < permissionList.size(); i++) {%>
                            <form action="MainController" method="POST">
                                <tr>
                                    <td> <input style="width:100%" type="text" name="permissionID" value="<%=permissionList.get(i).getPermissionID()%>" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="text" name="permissionName" value="<%=permissionList.get(i).getPermissionName()%>" /></td>
                                    <td>  

                                        <select required="" name="rolePriority" style="width:100%   ">
                                            <option value="<%=permissionList.get(i).getRoleNamePriority()%>"><%=permissionList.get(i).getRoleNamePriority()%></option>
                                            <option value="Board Manager">Board Manager</option>
                                            <option value="HR Manager">HR Manager</option>
                                            <option value="Employee">Employee</option>
                                        </select>
                                    </td>
                                <input type="hidden" name="status" value="<%=permissionList.get(i).isStatus()%>" readonly="readonly"/>
                                <input type="hidden" name="redirect" value="adminPermissionDetailPage.jsp" readonly="readonly"/>
                                <input type="hidden" name="type" value="Permission" readonly="readonly"/>
                                <%if (permissionList.get(i).isStatus()) {%>
                                <td> <input style="width:100%; background-color: #669c19" type="text"value="Active" readonly="readonly"/></td>
                                    <%} else {%>
                                <td> <input style="width:100%; background-color: #d3190d" type="text" value="Inactive" readonly="readonly"/></td>
                                    <%}%>
                                <td> <input style="width:100%" type="submit" name="action" value="View Detail" readonly="readonly"/></td> 
                                <td> <input style="width:100%" type="submit" name="action" value="Update" readonly="readonly"/></td> 
                                    <%if (permissionList.get(i).isStatus()) {%>
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

