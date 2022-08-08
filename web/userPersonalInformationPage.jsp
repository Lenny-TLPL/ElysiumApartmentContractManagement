<%-- 
    Document   : residentPersonalInformationPage
    Created on : Jun 24, 2022, 4:56:42 PM
    Author     : HPMT233
--%>

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
        <link href="css/resident_pi_style.css" rel="stylesheet" type="text/css"/>
        <!-- Boxicons CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <link rel="icon" type="image/png" sizes="21x21" href="images/logo1.png">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null) {
                response.sendRedirect("login.jsp");
            }
        %>
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
                    <a href="userPersonalInformationPage.jsp" class="active">
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
                    <a href="userMonthlyFeePage.jsp">
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
                    <a href="userNotificationPage.jsp">
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
                    <i class='bx bx-menu sidebarBtn'></i>
                    <span class="dashboard">Personal Information</span>
                </div>


                <div >
                    <img src="images/profile.jpg" alt="">
                    <span class="admin_name">${sessionScope.LOGIN_USER.fullName}</span>

                </div>
            </nav>

            <div class="home-content">
                <div class="sales-boxes">
                    <div class="recent-sales box">
                        <div class="title" style="float:left">Personal Details ${requestScope.UPDATE_USER_ERROR.errorMessage}
                                ${requestScope.UPDATE_USER_SUCCESS} </div>                       
                        <div class="container-xl px-4 mt-4"> 
                            <%-- TESTING --%>
                            <div class="form">
                                <form action="MainController" method="POST">
                                    <input type="hidden" name="type" class="form-control" value="User" />
                                    <input type="hidden" name="status" class="form-control" value="${sessionScope.LOGIN_USER.status}" />
                                    <input type="hidden" name="roleName" class="form-control" value="${sessionScope.LOGIN_USER_ROLE}"/> 
                                    <span class="title">&nbsp;</span>
                                    <div class="form-row">
                                        <div class="form-group col-md-4">
                                            <label>UserID </label>
                                            <input type="text" name="userID" class="form-control" value="${sessionScope.LOGIN_USER.userID}" readonly="">     
                                        </div>
                                        <div class="form-group col-md-4">
                                            <label>Full Name </label>
                                            <input type="text" name="fullName" class="form-control" value="${sessionScope.LOGIN_USER.fullName}" >     
                                        </div>

                                        <div class="form-group col-md-4">
                                            <label>Date of Birth  </label>
                                            <input type="date" name="birthday" class="form-control" value="${sessionScope.LOGIN_USER.birthDay}" >
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-4">
                                            <label>Email  </label>
                                            <input type="email" name="email" class="form-control" value="${sessionScope.LOGIN_USER.email}" >
                                        </div>


                                        <div class="form-group col-md-4">
                                            <label>Phone Number  </label>
                                            <input type="text" name="phone" class="form-control" value="${sessionScope.LOGIN_USER.phone}" >
                                        </div>

                                        <div class="form-group col-md-4">
                                            <label>Gender  </label>
                                            <input type="text" name="gender" class="form-control" value="${sessionScope.LOGIN_USER.gender}" readonly>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-4">
                                            <label>Address  </label>
                                            <input type="text" name="address" class="form-control" value="${sessionScope.LOGIN_USER.address}" >
                                        </div>   

                                        <div class="form-group col-md-4">
                                            <label>CitizenID </label>
                                            <input type="text" name="citizenID" class="form-control" value="${sessionScope.LOGIN_USER.citizenID}" readonly>
                                        </div>  
                                        <div class="form-group col-md-4">
                                            <label>Date Join </label>
                                            <input type="date" name="dateJoin" class="form-control" value="${sessionScope.LOGIN_USER.dateJoin}" readonly>
                                        </div>  
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-4">
                                            <label>Password </label>
                                            <input type="password" name="password" class="form-control" value="${sessionScope.LOGIN_USER.password}" >
                                        </div>  
                                        <div class="form-group col-md-4">
                                            <label>Confirm Password </label>
                                            <input type="password" name="confirmPassword" class="form-control" value="${sessionScope.LOGIN_USER.password}" >

                                        </div> 
                                    </div>
                                    <div class="form-row"  >
                                        <div class="col-auto my-1">
                                            <button type="submit" name="action" value="Update" class="btn btn-dark">Edit</button>
                                        </div>
                                    </div>
                                </form
                                
                            </div>                           
                        </div>
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

