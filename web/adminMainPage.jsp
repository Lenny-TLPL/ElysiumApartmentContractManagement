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
        <div class="sidebar">
            <div class="logo-details">
<!--                <i class='bx bxl-c-plus-plus'></i>-->
<!--                <img src="images/logo1.png" style="width:21%" alt="homepage" class="dark-logo" />-->
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
<!--            <nav>
                <div class="sidebar-button">
                    <i></i>
                    <span class="dashboard"></span>
                </div>
                <div class="search-box">
                                        <input type="text" placeholder="Search...">
                                        <i class='bx bx-search' ></i>
                </div>
                <div >
                                        <img src="images/profile.jpg" alt="">
                                        <span class="admin_name">Admin</span>

                </div>
            </nav>

            <div class="home-content">
                <div class="overview-boxes">
                                        <div class="box">
                                            <div class="right-side">
                                                <div class="box-topic">Total Order</div>
                                                <div class="number">40,876</div>
                                                <div class="indicator">
                                                    <i class='bx bx-up-arrow-alt'></i>
                                                    <span class="text">Up from yesterday</span>
                                                </div>
                                            </div>
                                            <i class='bx bx-cart-alt cart'></i>
                                        </div>
                                        <div class="box">
                                            <div class="right-side">
                                                <div class="box-topic">Total Sales</div>
                                                <div class="number">38,876</div>
                                                <div class="indicator">
                                                    <i class='bx bx-up-arrow-alt'></i>
                                                    <span class="text">Up from yesterday</span>
                                                </div>
                                            </div>
                                            <i class='bx bxs-cart-add cart two' ></i>
                                        </div>
                                        <div class="box">
                                            <div class="right-side">
                                                <div class="box-topic">Total Profit</div>
                                                <div class="number">$12,876</div>
                                                <div class="indicator">
                                                    <i class='bx bx-up-arrow-alt'></i>
                                                    <span class="text">Up from yesterday</span>
                                                </div>
                                            </div>
                                            <i class='bx bx-cart cart three' ></i>
                                        </div>
                                        <div class="box">
                                            <div class="right-side">
                                                <div class="box-topic">Total Return</div>
                                                <div class="number">11,086</div>
                                                <div class="indicator">
                                                    <i class='bx bx-down-arrow-alt down'></i>
                                                    <span class="text">Down From Today</span>
                                                </div>
                                            </div>
                                            <i class='bx bxs-cart-download cart four' ></i>
                                        </div>
                </div>

                <div class="sales-boxes">
                    <div class="recent-sales box">
                                                <div class="title">Recent Sales</div>
                                                <div class="sales-details">
                                                    <ul class="details">
                                                        <li class="topic">Date</li>
                                                        <li><a href="#">02 Jan 2021</a></li>
                                                        <li><a href="#">02 Jan 2021</a></li>
                                                        <li><a href="#">02 Jan 2021</a></li>
                                                        <li><a href="#">02 Jan 2021</a></li>
                                                        <li><a href="#">02 Jan 2021</a></li>
                                                        <li><a href="#">02 Jan 2021</a></li>
                                                        <li><a href="#">02 Jan 2021</a></li>
                                                    </ul>
                                                    <ul class="details">
                                                        <li class="topic">Customer</li>
                                                        <li><a href="#">Alex Doe</a></li>
                                                        <li><a href="#">David Mart</a></li>
                                                        <li><a href="#">Roe Parter</a></li>
                                                        <li><a href="#">Diana Penty</a></li>
                                                        <li><a href="#">Martin Paw</a></li>
                                                        <li><a href="#">Doe Alex</a></li>
                                                        <li><a href="#">Aiana Lexa</a></li>
                                                        <li><a href="#">Rexel Mags</a></li>
                                                        <li><a href="#">Tiana Loths</a></li>
                                                    </ul>
                                                    <ul class="details">
                                                        <li class="topic">Sales</li>
                                                        <li><a href="#">Delivered</a></li>
                                                        <li><a href="#">Pending</a></li>
                                                        <li><a href="#">Returned</a></li>
                                                        <li><a href="#">Delivered</a></li>
                                                        <li><a href="#">Pending</a></li>
                                                        <li><a href="#">Returned</a></li>
                                                        <li><a href="#">Delivered</a></li>
                                                        <li><a href="#">Pending</a></li>
                                                        <li><a href="#">Delivered</a></li>
                                                    </ul>
                                                    <ul class="details">
                                                        <li class="topic">Total</li>
                                                        <li><a href="#">$204.98</a></li>
                                                        <li><a href="#">$24.55</a></li>
                                                        <li><a href="#">$25.88</a></li>
                                                        <li><a href="#">$170.66</a></li>
                                                        <li><a href="#">$56.56</a></li>
                                                        <li><a href="#">$44.95</a></li>
                                                        <li><a href="#">$67.33</a></li>
                                                        <li><a href="#">$23.53</a></li>
                                                        <li><a href="#">$46.52</a></li>
                                                    </ul>
                                                </div>
                                                <div class="button">
                                                    <a href="#">See All</a>
                                                </div>
                    <h1>
                        <i class='bx bxl-c-plus-plus'></i>
                              <img src="assets/images/logo1.png" style="width:10%" alt="homepage" class="dark-logo" />
                        <span class="logo_name">
                                <span>ELYSIUM</span> 
                                <p>WE ARE FAMILY.</p>
                                <p>WE GROW STRONG TOGETHER.</p>
                                
                        </span>
                    </h1>
                    
                    </div>
                    
                                        <div class="top-sales box">
                                            <div class="title">Top Seling Product</div>
                                            <ul class="top-sales-details">
                                                <li>
                                                    <a href="#">
                                                        <img src="images/sunglasses.jpg" alt="">
                                                        <span class="product">Vuitton Sunglasses</span>
                                                    </a>
                                                    <span class="price">$1107</span>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <img src="images/jeans.jpg" alt="">
                                                        <span class="product">Hourglass Jeans </span>
                                                    </a>
                                                    <span class="price">$1567</span>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                         <img src="images/nike.jpg" alt="">
                                                        <span class="product">Nike Sport Shoe</span>
                                                    </a>
                                                    <span class="price">$1234</span>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <img src="images/scarves.jpg" alt="">
                                                        <span class="product">Hermes Silk Scarves.</span>
                                                    </a>
                                                    <span class="price">$2312</span>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <img src="images/blueBag.jpg" alt="">
                                                        <span class="product">Succi Ladies Bag</span>
                                                    </a>
                                                    <span class="price">$1456</span>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <img src="images/bag.jpg" alt="">
                                                        <span class="product">Gucci Womens's Bags</span>
                                                    </a>
                                                    <span class="price">$2345</span>
                                                <li>
                                                    <a href="#">
                                                        <img src="images/addidas.jpg" alt="">
                                                        <span class="product">Addidas Running Shoe</span>
                                                    </a>
                                                    <span class="price">$2345</span>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <img src="images/shirt.jpg" alt="">
                                                        <span class="product">Bilack Wear's Shirt</span>
                                                    </a>
                                                    <span class="price">$1245</span>
                                                </li>
                                            </ul>
                                        </div>
                </div>
            </div>-->
                    <nav style="height:100%">
                    <h1 style="margin: auto">
                        <i class='bx bxl-c-plus-plus'></i>
                        <!--      <img src="assets/images/logo1.png" style="width:10%" alt="homepage" class="dark-logo" />-->
                        <span class="logo_name">
                                <span>ELYSIUM</span> 
                                <p>WE ARE FAMILY.</p>
                                <p>WE GROW STRONG TOGETHER.</p>
                                
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

