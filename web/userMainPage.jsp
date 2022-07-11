<!-- <%-- 
    Document   : residentInformationPage
    Created on : Jun 24, 2022, 4:42:06 PM
    Author     : HPMT233
--%> -->

<!DOCTYPE html>
<!-- Designined by CodingLab | www.youtube.com/codinglabyt -->
<html lang="en" dir="ltr">
    <head>
        <meta charset="UTF-8">
        <!--<title> Responsiive Admin Dashboard | CodingLab </title>-->
        <link rel="stylesheet" href="css/admincss.css">
        <!-- Boxicons CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <link rel="icon" type="image/png" sizes="16x16" href="assets/images/logo1.png">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div class="sidebar">
            <div class="logo-details">
                <i class='bx bxl-c-plus-plus'></i>
                <!--<img src="assets/images/logo1.png" style="width:10%" alt="homepage" class="dark-logo" />-->
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
                    <a href="MainController?action=Search&type=Notification&search=">
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
                    <span class="dashboard">Personal Information</span>
                </div>
                <div class="search-box">
                    <input type="text" placeholder="Search...">
                    <i class='bx bx-search' ></i>
                </div>
                <div >
                    <!--<img src="images/profile.jpg" alt="">-->
                    <span class="admin_name">${sessionScope.LOGIN_USER.fullName}</span>

                </div>
            </nav>
            <nav style="height:100%">
                <h1 style="margin: auto">
                    <i class='bx bxl-c-plus-plus'></i>
                    <!--      <img src="assets/images/logo1.png" style="width:10%" alt="homepage" class="dark-logo" />-->
                    <span class="logo_name">
                        <span>ELYSIUM</span> 
                        <p>WELCOME BACK.</p>
                        <p>WE MISS YOU SO MUCH!!</p>

                    </span>
                </h1>
            </nav>
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


