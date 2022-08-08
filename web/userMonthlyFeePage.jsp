<%@page import="sample.DTO.MonthlyFeeDTO"%>
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
        <%if (request.getAttribute("MONTHLY_FEE_LIST") == null) {
                response.sendRedirect("MainController?action=Search&type=MonthlyFee&search=");
            }%>
        <div class="sidebar">
            <div class="logo-details">
<!--                <i class='bx bxl-c-plus-plus'></i>-->
                <!--      <img src="assets/images/logo1.png" style="width:10%" alt="homepage" class="dark-logo" />-->
                <span class="logo_name">
                    <a href="userMainPage.jsp" style="text-decoration: none;display: flex; justify-content: center">
                        <img src="images/logo1.png" style="width:21%;" alt="homepage" class="dark-logo" />
                        <span style="color:#FFF; text-decoration: none;margin-bottom: auto; margin-top: 20px;margin-right: 80px">ELYSIUM</span> 
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
                    <a href="userContractPage.jsp">
                        <i class='bx bx-user' ></i>
                        <span class="links_name">Contract</span>
                    </a>
                </li>
                <li>
                    <a href="userApartmentPage.jsp">
                        <i class='bx bx-user' ></i>
                        <span class="links_name">Apartment</span>
                    </a>
                </li>
                <li>
                    <a href="userMonthlyFeePage.jsp"class="active">
                        <i class='bx bx-user' ></i>
                        <span class="links_name">Monthly Fee</span>
                    </a>
                </li>
                <li>
                    <a href="userBillingHistoryPage.jsp">
                        <i class='bx bx-user' ></i>
                        <span class="links_name">Billing History</span>
                    </a>
                </li>
                <li>
                    <a href="userNotificationPage.jsp" >
                        <i class='bx bx-user' ></i>
                        <span class="links_name">Notification</span>
                    </a>
                </li>
                <li>
                    <a href="userNewsPage.jsp">
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
<!--                    <i class='bx bx-menu sidebarBtn'></i>
                    <span class="dashboard">Dashboard</span>-->
                </div>
                <form action="MainController"class="search-box">
                    <div>
                        <input type="hidden" name="type" value="MonthlyFee">
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
                        <div class="title" style="float:left">MONTHLY FEE</div>
                        <a href="adminAddServicePage.jsp?type=Service"style="float:right" >
<!--                            <i class="bx  bx-plus-circle" >ADD</i>-->
                        </a>
                        <div class="title"></div>
                        <table border="1" id="table">
                            <thead>
                                <tr>
                                    <th>UserID</th>                                                                  
                                    <th>ApartmentID</th>
                                    <th>WaterFee</th>
                                    <th>ElectricityFee</th>
                                    <th>ContractFee</th>
                                    <th>ServiceFee</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%  ArrayList<MonthlyFeeDTO> monthlyFeeList = (ArrayList<MonthlyFeeDTO>) request.getAttribute("MONTHLY_FEE_LIST");
                                    if (monthlyFeeList != null) {
                                        if (monthlyFeeList.size() > 0) {
                                            for (int i = 0; i < monthlyFeeList.size(); i++) {%>
                            <form action="MainController" method="POST">
                                <tr>
                                    <input style="width:100%" type="hidden" name="monthlyFeeID" value="<%=monthlyFeeList.get(i).getMonthlyFeeID()%>" readonly="readonly"/>
                                    <td> <input style="width:100%" type="text" name="userID" value="<%=monthlyFeeList.get(i).getUserID()%>" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="text" name="apartmentID" value="<%=monthlyFeeList.get(i).getApartmentID()%>" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="number" name="waterFee" value="<%=monthlyFeeList.get(i).getWaterFee()%>" step="0.01" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="number" name="electricityFee" value="<%=monthlyFeeList.get(i).getElectricityFee()%>" step="0.01" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="number" name="contractFee" value="<%=monthlyFeeList.get(i).getContractFee()%>" step="0.01" readonly="readonly"/></td>
                                    <td> <input style="width:100%" type="number" name="serviceFee" value="<%=monthlyFeeList.get(i).getServiceFee()%>" step="0.01" readonly="readonly"/></td>
                                <!--<input type="hidden" name="status" value="<%=monthlyFeeList.get(i).isStatus()%>" readonly="readonly"/>-->
                                <input type="hidden" name="redirect" value="adminMonthlyFeeDetailPage.jsp" readonly="readonly"/>
                                <input type="hidden" name="type" value="MonthlyFee" readonly="readonly"/>
                                
                                
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

