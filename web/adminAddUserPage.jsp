<%-- 
    Document   : newjsp
    Created on : Jun 18, 2022, 1:59:07 AM
    Author     : Phi Long
--%>

<!DOCTYPE html>
<!--=== Coding by CodingLab | www.codinglabweb.com === -->
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!----======== CSS ======== -->
        <link rel="stylesheet" href="css/addcss.css">

        <!----===== Iconscout CSS ===== -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

        <!--<title>Responsive Regisration Form </title>--> 
    </head>
    <body>
        <% if (request.getParameter("type") == null) {
                response.sendRedirect("adminMainPage.jsp");
            } else if (session.getAttribute("LOGIN_USER_PERMISSION") == null) {
                request.getRequestDispatcher("MainController?action=GetMaterial&require=permission&type");
            } else {%>
        <div class="container">
            <header>Add New ${param.type}</header>

            <form action="MainController" method="POST">
                <div class="form first">
                    <div class="details personal">
                        <span class="title">Personal Details</span>

                        <div class="fields">
                            <div class="input-field">
                                <label>Full Name ${requestScope.ADD_USER_ERROR.fullName}</label>
                                <input name="fullName" "type="text" class="form-control" placeholder="Enter your fullName" required="" value="${param.fullName}" minlength="4" maxlength="60">     
                            </div>

                            <div class="input-field">
                                <label>Date of Birth  ${requestScope.ADD_USER_ERROR.birthday}</label>
                                <input type="date" name ="birthday"class="form-control" placeholder="Enter your birthday" required pattern="\d{4}-\d{2}-\d{2}"value="${param.birthday}">
                            </div>

                            <div class="input-field">
                                <label>Email  ${requestScope.ADD_USER_ERROR.email}</label>
                                <input type="email" id="example-email" name="email" class="form-control" placeholder="Enter your email"value="${param.email}">
                            </div>

                            <div class="input-field">
                                <label>Phone Number  ${requestScope.ADD_USER_ERROR.phone}</label>
                                <input name="phone"type="text" class="form-control" placeholder="Your phone number" minlength="10" maxlength="11" required=""value="${param.phone}">
                            </div>

                            <div class="input-field">
                                <label>Gender  ${requestScope.ADD_USER_ERROR.gender}</label>
                                <select required="" name="gender" value="${param.gender}">
                                    <option>male</option>
                                    <option>female</option>
                                    <option>other</option>
                                </select>
                            </div>

                            <div class="input-field">
                                <label>Address  ${requestScope.ADD_USER_ERROR.address}</label>
                                <input type="text" id="example-email" name="address" class="form-control" placeholder="Enter your address"value="${param.address}">
                            </div>
                        </div>
                    </div>

                    <div class="details ID">
                        <div class="fields">
                            <div class="input-field">
                                <label>CitizenID  ${requestScope.ADD_USER_ERROR.citizenID}</label>
                                <input type="text" name ="citizenID" class="form-control" placeholder="Enter your citizen ID" maxlength="12" required=""value="${param.citizenID}">
                            </div>

                            <div class="input-field">
                                <label>Password </label>
                                <input type="password" name="password" class="form-control" placeholder="Enter your password" minlength="6" maxlength="21" required=""value="${param.password}">
                            </div>

                            <div class="input-field">
                                <label>Confirm Password  ${requestScope.ADD_USER_ERROR.password}</label>
                                <input type="password" name="confirmPassword" class="form-control" placeholder="Confirm your password" minlength="6" maxlength="21" required=""value="${param.confirmPassword}">
                            </div>
                        </div>
                        <input type="hidden" name="type" value="${param.type}"/>
                        ${requestScope.ADD_USER_ERROR.errorMessage}
                        ${requestScope.ADD_USER_SUCCESS} 
                        <div class="buttons">
                            <button class="sumbit" type="reset">
                                <span class="btnText">Reset</span>
                            </button>     
                            <button class="nextBtn">
                                <span class="btnText">Next</span>
                                <i class="uil uil-navigator"></i>
                            </button>
                        </div>

                    </div> 
                </div>
                <%if (request.getParameter("type").equals("Customer") || request.getParameter("type").equals("Resident")) {%>
                <div class="form second">
                    <div class="details address">
                        <span class="title">Contract with Elysium</span>

                        <div class="fields">
                            <div class="input-field">
                                <label>Address Type</label>
                                <input type="text" placeholder="Permanent or Temporary" required>
                            </div>

                            <div class="input-field">
                                <label>Nationality</label>
                                <input type="text" placeholder="Enter nationality" required>
                            </div>

                            <div class="input-field">
                                <label>State</label>
                                <input type="text" placeholder="Enter your state" required>
                            </div>

                            <div class="input-field">
                                <label>District</label>
                                <input type="text" placeholder="Enter your district" required>
                            </div>

                            <div class="input-field">
                                <label>Block Number</label>
                                <input type="number" placeholder="Enter block number" required>
                            </div>

                            <div class="input-field">
                                <label>Ward Number</label>
                                <input type="number" placeholder="Enter ward number" required>
                            </div>
                        </div>
                    </div>

                    <div class="details family">
                        <span class="title">Family Details</span>

                        <div class="fields">
                            <div class="input-field">
                                <label>Father Name</label>
                                <input type="text" placeholder="Enter father name" required>
                            </div>

                            <div class="input-field">
                                <label>Mother Name</label>
                                <input type="text" placeholder="Enter mother name" required>
                            </div>

                            <div class="input-field">
                                <label>Grandfather</label>
                                <input type="text" placeholder="Enter grandfther name" required>
                            </div>

                            <div class="input-field">
                                <label>Spouse Name</label>
                                <input type="text" placeholder="Enter spouse name" required>
                            </div>

                            <div class="input-field">
                                <label>Father in Law</label>
                                <input type="text" placeholder="Father in law name" required>
                            </div>

                            <div class="input-field">
                                <label>Mother in Law</label>
                                <input type="text" placeholder="Mother in law name" required>
                            </div>
                        </div>

                        <div class="buttons">
                            <div class="backBtn">
                                <i class="uil uil-navigator"></i>
                                <span class="btnText">Back</span>
                            </div>      
                            <button class="sumbit" type="reset">
                                <span class="btnText">Reset</span>

                            </button>       
                            <button class="submit" type="submit" name="action" value="Add">
                                <span class="btnText">Submit</span>
                                <i class="uil uil-navigator"></i>
                            </button> 
                        </div>
                    </div> 
                </div>
                <%} else {%>
                <div class="form second">
                    <div class="details address">
                        <span class="title">Permission</span>

                        <div class="fields">
                            <div class="input-field">
                                <label><input type="checkbox" name="html" value="html" checked> HTML</label><br />
                            </div>
                            <!--
                                                        <div class="input-field">
                                                            <label>Mother in Law</label>
                                                            <input type="file" id="avatar" name="avatar"accept="image/*">
                                                        </div>-->
                        </div>

                        <div class="buttons">
                            <div class="backBtn">
                                <i class="uil uil-navigator"></i>
                                <span class="btnText">Back</span>
                            </div>      
                            <button class="sumbit" type="reset">
                                <span class="btnText">Reset</span>
                            </button>       
                            <button class="submit" type="submit" name="action" value="Add">
                                <span class="btnText">Submit</span>
                                <i class="uil uil-navigator"></i>
                            </button> 
                        </div>
                    </div> 
                </div>
                <%}%>
            </form>
            <button class="backButton">
                <i class="uil uil-arrow-left"></i>
                <span class="btnText"><a href="adminDashBoardPage.jsp">Back to admin page</a></span>               
            </button> 
        </div>
        <%}%>
        <script src="js/addjavascript.js"></script>
    </body>
</html>

