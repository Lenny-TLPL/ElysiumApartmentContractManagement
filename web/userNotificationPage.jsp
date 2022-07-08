<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sample.DTO.PrivateNotificationDTO"%>
<%@page import="sample.DTO.NotificationDTO"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<!-- Designined by CodingLab | www.youtube.com/codinglabyt -->
<html lang="en" dir="ltr">
    <head>
        <meta charset="UTF-8">
        <title>Customer Notification</title>
        <link rel="stylesheet" href="css/admincss.css">
        <!-- Boxicons CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <link href="css/mystyle.css" rel="stylesheet">
        <link rel="icon" type="image/png" sizes="16x16" href="assets/images/logo1.png">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%if (request.getAttribute("NOTIFICATION_LIST") == null || request.getAttribute("PRIVATE_NOTIFICATION_LIST") == null) {
                response.sendRedirect("MainController?action=Search&type=Notification&search=");
            }%>
        <div class="sidebar">
            <div class="logo-details">
                <i class='bx bxl-c-plus-plus'></i>
                <!--      <img src="assets/images/logo1.png" style="width:10%" alt="homepage" class="dark-logo" />-->
                <span class="logo_name">
                    <a href="userMainPage.jsp">
                        <span style="color:#FFF; text-decoration: none;">ELYSIUM</span> 
                    </a>
                </span>
            </div>
            <ul class="nav-links">
                <li>
                    <a href="userPersonalInformationPage.jsp">
                        <i class='bx bx-grid-alt' ></i>
                        <span class="links_name">Personal Information</span>
                    </a>
                </li>
                <li>
                    <a href="MainController?type=Board Manager&action=Search&search=">
                        <i class='bx bx-user' ></i>
                        <span class="links_name">Contract</span>
                    </a>
                </li>
                <li>
                    <a href="MainController?type=HR Manager&action=Search&search=">
                        <i class='bx bx-user' ></i>
                        <span class="links_name">Payment Status</span>
                    </a>
                </li>
                <li>
                    <a href="MainController?type=Notification&action=Search&search=">
                        <i class='bx bx-user' ></i>
                        <span class="links_name">Notification</span>
                    </a>
                </li>
                <li>
                    <a href="MainController?type=Resident&action=Search&search=">
                        <i class='bx bx-user' ></i>
                        <span class="links_name">News</span>
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
                    <i class='bx bx-menu sidebarBtn'></i>
                    <span class="dashboard">Dashboard</span>
                </div>
                <form action="MainController"class="search-box">
                    <div>
                        <input type="hidden" name="type" value="Notification">
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
                        <div class="title">NOTIFICATION</div>
                                <%  ArrayList<NotificationDTO> notificationList = (ArrayList<NotificationDTO>) request.getAttribute("NOTIFICATION_LIST");
                                    if (notificationList != null) {
                                        if (notificationList.size() > 0) {
                                            for (int i = 0; i < notificationList.size(); i++) {%>
                                <div class="notification">
                                    <em class="date"><%= notificationList.get(i).getNotiDate() %> - </em>
                                    <input type="hidden" name="redirect" value="adminNotificationDetailPage.jsp" readonly="readonly"/>
                                <input type="hidden" name="type" value="Notification" readonly="readonly"/>
                                    <a href="MainController?action=View Detail&type=Notification&redirect=userNotificationDetailPage.jsp&notiID=<%=notificationList.get(i).getNotiID()%>" class="header"><%= notificationList.get(i).getNotiHeader() %></a>
                                </div>
                            <%}
                                    }
                                }%>

                            </tbody>
                        </table>
                    </div>                    
                </div>
                <br>
                <div class="sales-boxes">
                    <div class="recent-sales box">
                        <div class="title" style="float:left">PRIVATE NOTIFICATION</div>
                        <a href="#"style="float:right" >
                            <i class="bx  bx-plus-circle" >ADD</i>
                        </a>
                        <table border="1" id="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>USER ID</th> 
                                    <th>Header</th> 
                                    <th>Date</th>
                                    <th>Status</th>
                                    <th>View Detail</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%  ArrayList<PrivateNotificationDTO> PrivateNotiList = (ArrayList<PrivateNotificationDTO>) request.getAttribute("CONTRACT_LIST");
                                    if (PrivateNotiList != null) {
                                        if (PrivateNotiList.size() > 0) {
                                            for (int i = 0; i < PrivateNotiList.size(); i++) {%>
                            <form action="MainController" method="POST">
                                <tr>
                                    <td> <input style="width:100%" type="text" name="privateNotiID" value="<%=PrivateNotiList.get(i).getNotiID()%>" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="text" name="userID" value="<%=PrivateNotiList.get(i).getUserID()%>" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="text" name="privateNotiHeader" value="<%=PrivateNotiList.get(i).getNotiHeader()%>" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="date" name="privateNotiDate" value="<%=PrivateNotiList.get(i).getNotiDate()%>" readonly="readonly"/></td>
                                <input type="hidden" name="privateNotiStatus" value="<%=PrivateNotiList.get(i).isStatus()%>" readonly="readonly"/>
                                <%if (PrivateNotiList.get(i).isStatus()) {%>
                                <td> <input style="width:100%; background-color: #669c19" type="text"value="Active" readonly="readonly"/></td>
                                    <%} else {%>
                                <td> <input style="width:100%; background-color: #d3190d" type="text" value="Inactive" readonly="readonly"/></td>
                                    <%}%>
                                <td> <input style="width:100%" type="submit" name="action" value="View Detail" readonly="readonly"/></td> 
                                <%if (PrivateNotiList.get(i).isStatus()) {%>
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
