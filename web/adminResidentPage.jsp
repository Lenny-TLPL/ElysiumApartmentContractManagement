<%@page import="java.util.ArrayList"%>
<%@page import="sample.DTO.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <%if (request.getAttribute("USER_LIST") == null) {
                response.sendRedirect("MainController?action=Search&type=Resident&search=");
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
                    <a href="adminDashBoardPage.jsp">
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
                    <a href="adminResidentPage.jsp"class="active">
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
                        <input type="hidden" name="type" value="Resident">
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
                        <div class="title" style="float:left">RESIDENT</div>
                        <a href="adminAddUserPage.jsp?type=Resident"style="float:right" >
                            <i class="bx  bx-plus-circle" >ADD</i>
                        </a>
                        <div class="title"></div>
                        <table border="1" id="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>FullName</th>
                                    <th>Phone</th>                                    
                                    <th>Citizen ID</th>                                    
                                    <th>DateJoin</th>
                                    <th>Status</th>
                                    <th>View Detail</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%  ArrayList<UserDTO> userList = (ArrayList<UserDTO>) request.getAttribute("USER_LIST");
                                    if (userList != null) {
                                        if (userList.size() > 0) {
                                            for (int i = 0; i < userList.size(); i++) {%>
                            <form action="MainController" method="POST">
                                <tr>
                                    <td> <input style="width:100%" type="text" name="userID" value="<%=userList.get(i).getUserID()%>" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="text" name="fullName" value="<%=userList.get(i).getFullName()%>" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="text" name="phone" value="<%=userList.get(i).getPhone()%>" readonly="readonly" maxlength="11" required=""/></td>
                                    <td> <input style="width:100%" type="text" name="citizenID" value="<%=userList.get(i).getCitizenID()%>" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="date" name="dateJoin" value="<%=userList.get(i).getDateJoin()%>" readonly="readonly"/></td>
                                    <input type="hidden" name="status" value="<%=userList.get(i).isStatus()%>" readonly="readonly"/>
                                    <input type="hidden" name="roleID" value="<%=userList.get(i).getRoleID()%>" readonly="readonly"/>
                                    <input type="hidden" name="redirect" value="adminUserDetailPage.jsp" readonly="readonly"/>
                                    <input type="hidden" name="type" value="Resident" readonly="readonly"/>
                                    <%if(userList.get(i).isStatus()){%>
                                    <td> <input style="width:100%; background-color: #669c19" type="text"value="Active" readonly="readonly"/></td>
                                    <%}else{%>
                                    <td> <input style="width:100%; background-color: #d3190d" type="text" value="Inactive" readonly="readonly"/></td>
                                    <%}%>
                                    <td> <input style="width:100%" type="submit" name="action" value="View Detail" readonly="readonly"/></td> 
                                    <%if(userList.get(i).isStatus()){%>
                                    <td> <input style="width:100%" type="submit" name="action" value="Disable" readonly="readonly"/></td>
                                    <%}else{%>
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

