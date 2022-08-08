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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
    </head>
    <body>
        <%if (request.getAttribute("DATA_VALUE") == null) {
                response.sendRedirect("MainController?action=GetMaterial&type=View Report&redirect=adminDashBoardPage.jsp&require=View Report");
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
                    <a href="adminDashBoardPage.jsp"class="active" >
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
                    <i class='bx bx-menu sidebarBtn'></i>
                    <span class="dashboard">Dashboard</span>
                </div>
                <form action="MainController"class="search-box">
                    <div>
<!--                        <input type="hidden" name="type" value="Board Manager">
                        <input class="search-box" style="width:96.5%"type="text" name="search"  placeholder="Search...." value="${param.search}">
                        <button type="submit" name="action" value="Search"><i class='bx bx-search' ></i> </button>-->
                    </div>
                </form>

                <div >
                    <!--<img src="images/profile.jpg" alt="">-->
                    <span class="admin_name">${sessionScope.LOGIN_USER.fullName}</span>

                </div>
            </nav>

            <%  ArrayList<Integer> dataValue = (ArrayList<Integer>) request.getAttribute("DATA_VALUE");
                if (dataValue != null) {
                    if (dataValue.size() > 0) {
            %>
            <input style="width:100%" id="Total" type="hidden" value="<%=dataValue.get(0)%>" readonly="readonly"/>
            <input style="width:100%" id="BoardManager" type="hidden" value="<%=dataValue.get(1)%>" readonly="readonly"/>
            <input style="width:100%" id="HRManager" type="hidden"  value="<%=dataValue.get(2)%>" readonly="readonly"/>
            <input style="width:100%" id="Employee" type="hidden"  value="<%=dataValue.get(3)%>" readonly="readonly"/>
            <input style="width:100%" id="Resident" type="hidden"  value="<%=dataValue.get(4)%>" readonly="readonly"/>
            <input style="width:100%" id="Customer" type="hidden"  value="<%=dataValue.get(5)%>" readonly="readonly"/>

            <input style="width:100%" id="TotalApartment" type="hidden"  value="<%=dataValue.get(6)%>" readonly="readonly"/>
            <input style="width:100%" id="Available" type="hidden"  value="<%=dataValue.get(7)%>" readonly="readonly"/>
            <input style="width:100%" id="Rented" type="hidden"  value="<%=dataValue.get(8)%>" readonly="readonly"/>
            <input style="width:100%" id="Maintenance" type="hidden"  value="<%=dataValue.get(9)%>" readonly="readonly"/>
            
            <input style="width:100%" id="TotalContract" type="hidden"  value="<%=dataValue.get(10)%>" readonly="readonly"/>
            <input style="width:100%" id="January" type="hidden"  value="<%=dataValue.get(11)%>" readonly="readonly"/>
            <input style="width:100%" id="Febuary" type="hidden"  value="<%=dataValue.get(12)%>" readonly="readonly"/>
            <input style="width:100%" id="March" type="hidden"  value="<%=dataValue.get(13)%>" readonly="readonly"/>
            <input style="width:100%" id="April" type="hidden"  value="<%=dataValue.get(14)%>" readonly="readonly"/>
            <input style="width:100%" id="May" type="hidden"  value="<%=dataValue.get(15)%>" readonly="readonly"/>
            <input style="width:100%" id="June" type="hidden"  value="<%=dataValue.get(16)%>" readonly="readonly"/>
            <input style="width:100%" id="July" type="hidden"  value="<%=dataValue.get(17)%>" readonly="readonly"/>
            <input style="width:100%" id="August" type="hidden"  value="<%=dataValue.get(18)%>" readonly="readonly"/>
            <input style="width:100%" id="September" type="hidden"  value="<%=dataValue.get(19)%>" readonly="readonly"/>
            <input style="width:100%" id="October" type="hidden"  value="<%=dataValue.get(20)%>" readonly="readonly"/>
            <input style="width:100%" id="November" type="hidden"  value="<%=dataValue.get(21)%>" readonly="readonly"/>
            <input style="width:100%" id="December" type="hidden"  value="<%=dataValue.get(22)%>" readonly="readonly"/>

            <%
                    }
                }%>
            <div class="home-content">
                <canvas id="humanResourceChart" style="width:50%;max-width:600px;margin-left:5%;margin-right:2%;float:left"></canvas>
                <canvas id="apartmentChart" style="width:50%;max-width:600px;margin-left:6%; "></canvas>
                </br>
                </br>
                <canvas id="contractChart" style="width:50%;max-width:1200px;margin-left:6.5%;"></canvas>
                </br><!-- comment -->
                <div></div>
            </div>
        </section>

        <script>
            //human resource
            var xValues = ["Board Manager", "HR Manager", "Employee", "Resident", "Employee"];
            var yValues = [document.getElementById("BoardManager").value, document.getElementById("HRManager").value, document.getElementById("Employee").value, document.getElementById("Resident").value, document.getElementById("Customer").value, 0, document.getElementById("Total").value];
            // var yValuesInt;
//            if((document.getElementById("YColumn")).value<=10){
//                yValues = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
//            }else{
//                var yValuesString = String ((document.getElementById("YColumn").value/10)).charAt(0);
//                var value = Number(yValuesString);
//                YValuesInt = Number(Number(document.getElementById("YColumn").value)/10)/value;
//                yValues = [0, YValuesInt, YValuesInt*2, YValuesInt*3, YValuesInt*4, YValuesInt*5, YValuesInt*6, YValuesInt*7, YValuesInt*8, YValuesInt*9, YValuesInt*10];
//            }
//            console.log("hi");
//            console.log(yValuesString);
//            console.log(value);
//            console.log(yValuesInt);
            var barColors = ["red", "green", "blue", "orange", "brown"];

            new Chart("humanResourceChart", {
                type: "bar",
                data: {
                    labels: xValues,
                    datasets: [{
                            backgroundColor: barColors,
                            data: yValues
                        }]
                },
                options: {
                    legend: {display: false},
                    title: {
                        display: true,
                        text: "Human resource of Elysium"
                    }
                }
            });

            //apartment
            var xValues1 = ["Available", "Have Owner", "Maintenace"];
            var yValues1 = [document.getElementById("Available").value, document.getElementById("Rented").value, document.getElementById("Maintenance").value, 0, document.getElementById("TotalApartment").value];
            var barColors = ["red", "green", "blue", "orange", "brown"];

            new Chart("apartmentChart", {
                type: "bar",
                data: {
                    labels: xValues1,
                    datasets: [{
                            backgroundColor: barColors,
                            data: yValues1
                        }]
                },
                options: {
                    legend: {display: false},
                    title: {
                        display: true,
                        text: "Apartment of Elysium"
                    }
                }
            });

            //contract
            var xValues2 = ['January', 'Febuary', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
            var yValues2 = [document.getElementById("January").value, document.getElementById("Febuary").value,
                document.getElementById("March").value, document.getElementById("April").value, 
                document.getElementById("May").value, document.getElementById("June").value,
                document.getElementById("July").value, document.getElementById("August").value,
                document.getElementById("September").value, document.getElementById("October").value, 
            document.getElementById("November").value, document.getElementById("December").value];
            var max =Number (document.getElementById("TotalContract").value);
            new Chart("contractChart", {
                type: "line",
                data: {
                    labels: xValues2,
                    datasets: [{
                            fill: false,
                            lineTension: 0,
                            backgroundColor: "rgba(0,0,255,1.0)",
                            borderColor: "rgba(0,0,255,0.1)",
                            data: yValues2
                        }]
                },
                options: {
                    legend: {display: false},
                    title: {
                        display: true,
                        text: "Contract signed in this year"
                    },
                    scales: {
                        yAxes: [{ticks: {min: 0, max:  max}}]
                    }
                    
                }
            });
        </script>
    </body>
</html>

