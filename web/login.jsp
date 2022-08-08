<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--=== Coding by CodingLab | www.codinglabweb.com === -->
<html lang="en">
    <head>
        <!--        <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">-->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito+Sans:200,300,400,700,900|Oswald:400,700"> 
        <link rel="stylesheet" href="fonts/icomoon/style.css">

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">
        <link rel="stylesheet" href="css/jquery-ui.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="css/mediaelementplayer.css">
        <link rel="stylesheet" href="css/animate.css">
        <link rel="stylesheet" href="fonts/flaticon/font/flaticon.css">
        <link rel="stylesheet" href="css/fl-bigmug-line.css">
        <link href="css/login_stye.css" rel="stylesheet" type="text/css"/>
        <link rel="icon" type="image/png" sizes="21x21" href="images/logo1.png">

        <title>Login Page</title>

    </head>
    <body>
        <section class="h-100 gradient-form" style="background-color: #eee;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-xl-10">
                        <div class="card rounded-3 text-black">
                            <div class="row g-0">
                                <div class="col-lg-6">
                                    <div class="card-body p-md-5 mx-md-4">

                                        <div class="text-center">
                                            <img src="images/logo4.png"
                                                 style="width: 150px;" alt="logo">
                                            <h4 class="mt-1 mb-5 pb-1">Welcome to Elysium</h4>
                                        </div>

                                        <form action="MainController" method="POST">
                                            <p>Please login to your account</p>

                                            <div class="form-outline mb-4">
                                                <input name="userID" type="text" id="form2Example11" class="form-control" required=""
                                                       placeholder="UserID  " value="${param.userID}" />                            
                                            </div>

                                            <div class="form-outline mb-4">
                                                <input name="password" type="password" id="form2Example22" class="form-control" required=""
                                                       placeholder="Password" value="${param.password}" />
                                            </div>
                                            ${requestScope.LOGIN_ERROR}
                                            <div class="text-center pt-1 mb-5 pb-1">                           
                                                <button  class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit" value="Login" name="action">Login</button> 
<!--                                                <a class="text-muted" href="#!">Forgot password?</a>-->
                                            </div>  
                                            </br>
                                            <div class="d-flex align-items-center justify-content-center pb-4">
                                                <p class="mb-0 me-2">Hotline: 0971407775 </br>Email: ElysiumApartmentCo@gmail.com</p> 
                                            </div>

                                        </form>

                                    </div>
                                </div>
                                <div class="col-lg-6 d-flex align-items-center gradient-custom-2">
                                    <div class="text-white px-3 py-4 p-md-5 mx-md-4">
                                        <h4 class="mb-4">We are more than just a company</h4>
                                        <p class="small mb-0"> Here at Elysium we always seek the best for our customers. We are one big family. We do not provide houses, we provide homes.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
