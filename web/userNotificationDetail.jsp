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
                    <a href="adminDashBoardPage.jsp">
                        <i class='bx bx-grid-alt' ></i>
                        <span class="links_name">Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="MainController?type=Board Manager&action=Search&search=">
                        <i class='bx bx-user' ></i>
                        <span class="links_name">Board Manager</span>
                    </a>
                </li>
                <li>
                    <a href="MainController?type=HR Manager&action=Search&search=">
                        <i class='bx bx-user' ></i>
                        <span class="links_name">HR Manager</span>
                    </a>
                </li>
                <li>
                    <a href="MainController?type=Employee&action=Search&search=">
                        <i class='bx bx-user' ></i>
                        <span class="links_name">Employee</span>
                    </a>
                </li>
                <li>
                    <a href="MainController?type=Resident&action=Search&search=">
                        <i class='bx bx-user' ></i>
                        <span class="links_name">Resident</span>
                    </a>
                </li>
                <li>
                    <a href="MainController?type=Customer&action=Search&search=">
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
                    <a href="#" class="active">
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
                <li></li>
                <li>
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
                        <%  NotificationDTO noti = (NotificationDTO) request.getAttribute("NOTIFICATION");
                            if (noti != null) {
                        %>
                                <div class="title"><%= noti.getNotiHeader() %></div>
                                <div class="notification">
                                    <span><%= noti.getNotiContent() %></span>
                                </div>
                        <%
                            }
                        %>
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

