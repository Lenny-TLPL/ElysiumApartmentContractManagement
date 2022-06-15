<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="utf-8">
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
                            <img src=""
                              style="width: 185px;" alt="logo">
                            <h4 class="mt-1 mb-5 pb-1">We are Elysium</h4>
                          </div>
                         
                            <form action="MainController" method="POST">
                            <p>Please login to your account</p>
          
                            <div class="form-outline mb-4">
                                <input name="userID" type="text" id="form2Example11" class="form-control" required=""
                                       placeholder="UserID" value="${param.userID}" />                            
                            </div>
          
                            <div class="form-outline mb-4">
                                <input name="password" type="password" id="form2Example22" class="form-control" required=""
                                placeholder="Password" value="${param.password}" />
                            </div>
                            ${requestScope.LOGIN_ERROR}
                            <div class="text-center pt-1 mb-5 pb-1">                           
                                <button  class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit" value="Login" name="action">Login</button> 
                              <a class="text-muted" href="#!">Forgot password?</a>
                            </div>  
                            
                            <div class="d-flex align-items-center justify-content-center pb-4">
                                <p class="mb-0 me-2">Hotline</p>
                            </div>
                            
                          </form>
          
                        </div>
                      </div>
                      <div class="col-lg-6 d-flex align-items-center gradient-custom-2">
                        <div class="text-white px-3 py-4 p-md-5 mx-md-4">
                          <h4 class="mb-4">We are more than just a company</h4>
                          <p class="small mb-0">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                            exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
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