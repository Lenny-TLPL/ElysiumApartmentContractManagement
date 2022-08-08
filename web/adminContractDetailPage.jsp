<%-- 
    Document   : newjsp
    Created on : Jun 18, 2022, 1:59:07 AM
    Author     : Phi Long
--%>

<%@page import="sample.DTO.ContractDTO"%>
<%@page import="sample.DTO.PermissionDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--=== Coding by CodingLab | www.codinglabweb.com === -->
<html lang="en">
    <head>
        <!--        <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">-->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!----======== CSS ======== -->
        <link rel="stylesheet" href="css/addcss.css">

        <!----===== Iconscout CSS ===== -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
        <link rel="icon" type="image/png" sizes="21x21" href="images/logo1.png">

        <!--<title>Responsive Regisration Form </title>--> 
        <style>
            #myImg {
                border-radius: 5px;
                cursor: pointer;
                transition: 0.3s;
            }

            #myImg:hover {
                opacity: 0.7;
            }

            /* The Modal (background) */
            .modal {
                display: none; /* Hidden by default */
                position: fixed; /* Stay in place */
                z-index: 1; /* Sit on top */
                padding-top: 100px; /* Location of the box */
                left: 0;
                top: 0;
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                overflow: auto; /* Enable scroll if needed */
                background-color: rgb(0,0,0); /* Fallback color */
                background-color: rgba(0,0,0,0.9); /* Black w/ opacity */
            }

            /* Modal Content (image) */
            .modal-content {
                margin: auto;
                display: block;
                width: 80%;
                max-width: 700px;
            }

            /* Caption of Modal Image */
            #caption {
                margin: auto;
                display: block;
                width: 80%;
                max-width: 700px;
                text-align: center;
                color: #ccc;
                padding: 10px 0;
                height: 150px;
            }

            /* Add Animation */
            .modal-content, #caption {
                -webkit-animation-name: zoom;
                -webkit-animation-duration: 0.6s;
                animation-name: zoom;
                animation-duration: 0.6s;
            }

            @-webkit-keyframes zoom {
                from {
                    -webkit-transform:scale(0)
                }
                to {
                    -webkit-transform:scale(1)
                }
            }

            @keyframes zoom {
                from {
                    transform:scale(0)
                }
                to {
                    transform:scale(1)
                }
            }

            /* The Close Button */
            .close {
                position: absolute;
                top: 15px;
                right: 35px;
                color: #f1f1f1;
                font-size: 40px;
                font-weight: bold;
                transition: 0.3s;
            }

            .close:hover,
            .close:focus {
                color: #bbb;
                text-decoration: none;
                cursor: pointer;
            }

            /* 100% Image Width on Smaller Screens */
            @media only screen and (max-width: 700px){
                .modal-content {
                    width: 100%;
                }
            }
        </style>
    </head>
    <body>
        <!--        else if (("Customer Resident").contains(request.getParameter("type"))&&request.getAttribute("APARTMENT_LIST") == null) {
                        request.getRequestDispatcher("MainController?action=GetMaterial&require=Apartment&type="+request.getParameter("type")+"&redirect=adminAddUserPage.jsp").forward(request, response);
                    }-->
        <% if (request.getParameter("type") == null) {
                response.sendRedirect("adminMainPage.jsp");
            } else if (request.getAttribute("CONTRACT_DETAIL") == null) {
                response.sendRedirect("MainController?action=Search&type=Contract&search=");
            } else {%>
        <div class="container" style="height: 780px">
            <header>${param.type} Detail</header>

            <form action="MainController" method="POST" enctype="multipart/form-data" style="height:580px">


                <div class="form first">
                    <div class="details address">

                        <div class="fields">
                            <div class="input-field">
                                <label>UserID  ${requestScope.UPDATE_CONTRACT_ERROR.userID}</label>
                                <input name="userID" value="${requestScope.CONTRACT_DETAIL.userID}" type="text" readonly="" placeholder="Enter userID" required >
                            </div>

                            <div class="input-field">
                                <label>ApartmentID  ${requestScope.UPDATE_CONTRACT_ERROR.apartmentID}</label>
                                <input name="apartmentID" value="${requestScope.CONTRACT_DETAIL.apartmentID}" readonly="" type="text" placeholder="Enter apartmentID" required>
                            </div>

                            <div class="input-field">
                                <label>Date Sign  ${requestScope.UPDATE_CONTRACT_ERROR.dateSign}</label>
                                <input name="dateSign" value="${param.dateSign}" type="date" placeholder="Enter DateSign" required >
                            </div>

                            <%if (((ContractDTO) request.getAttribute("CONTRACT_DETAIL")).isStatus()) {%>
                            <div class="input-field">
                                <label>Status  </label>
                                <select id="status" required="" name="status" onchange="showColor(this)" style="color:#000; background-color: #669c19 " >
                                    <option value="true" style="color:#000; background-color: #669c19 ">Valid</option>
<!--                                    <option value="false" style="color:#000; background-color: #d3190d ">Invalid</option>-->
                                </select>
                            </div>
                            <%} else {%>
                            <div class="input-field">
                                <label>Status  </label>
                                <select id="status" required="" name="status" onchange="showColor(this)" style="color:#000; background-color: #d3190d " >
                                    <option value="false" style="color:#000; background-color: #d3190d ">Invalid</option>
<!--                                    <option value="true" style="color:#000; background-color: #669c19 ">Valid</option>-->
                                </select>
                            </div>
                            <%}%>
                            <%if (("buying amortization").contains(((ContractDTO) request.getAttribute("CONTRACT_DETAIL")).getContractType())) {%>
                            <div class="input-field">
                                <label>Value ($)  ${requestScope.UPDATE_CONTRACT_ERROR.value}</label>
                                <input name="value" value="${requestScope.CONTRACT_DETAIL.value}" type="number" placeholder="Enter value" required >
                            </div>
                            <%} else {%>
                            <div class="input-field">
                                <label>Value($/month)   ${requestScope.UPDATE_CONTRACT_ERROR.value}</label>
                                <input name="value" value="${requestScope.CONTRACT_DETAIL.value}" type="number" placeholder="Enter value" required >
                            </div>
                            <%}%>
                            <div class="input-field">
                                <label>Contract Type  ${requestScope.UPDATE_CONTRACT_ERROR.contractType}</label>
                                <input name="contractType" value="${requestScope.CONTRACT_DETAIL.contractType}" type="text" placeholder="Enter contractType" required readonly="">
                            </div>




                            <%if (((ContractDTO) request.getAttribute("CONTRACT_DETAIL")).getContractType().equals("amortization")) {%>
                            <div class="input-field" id="amortization" >
                                <label>Month(s) Of Debt</label>
                                <select name="monthsOfDebt" value="${requestScope.CONTRACT_DETAIL.monthsOfDebt}">
                                    <%if (((ContractDTO) request.getAttribute("CONTRACT_DETAIL")).getMonthsOfDebt() == 120) {%>           
                                    <option value="120">10 years (120 months)</option> 
                                    <option value="60">5 years (60 months)</option>    
                                    <%} else {%>
                                    <option value="60">5 years (60 months)</option>    
                                    <option value="120">10 years (120 months)</option>   
                                    <%}%>
                                </select>
                            </div>

                            <div class="input-field" id="leasing" >
                                <label>Expiry Date  ${requestScope.UPDATE_CONTRACT_ERROR.expiryDate}</label>
                                <input name="expiryDate" value="${requestScope.CONTRACT_DETAIL.expiryDate}"  type="date" placeholder="Enter expiry date">
                            </div>   

                            <div class="input-field" id="leasing" >
                                <label>Interest Rate (%) ${requestScope.UPDATE_CONTRACT_ERROR.interestRate}</label>
                                <input name="interestRate" value="${requestScope.CONTRACT_DETAIL.interestRate}" type="number" placeholder="Enter interestRate">
                            </div>
                            <%  } else if (((ContractDTO) request.getAttribute("CONTRACT_DETAIL")).getContractType().equals("leasing")) {%>

                            <div class="input-field" id="leasing" >
                                <label>Expiry Date  ${requestScope.ADD_CONTRACT_ERROR.expiryDate}</label>
                                <input name="expiryDate" value="${requestScope.CONTRACT_DETAIL.expiryDate}" type="date" placeholder="Enter expiry date">
                            </div>
                            <div class="input-field"></div>
                            <div class="input-field"></div>
                            <%} else {
                                }%>
                            <div class="input-field">
                                <img id="myImg" style="width:264px; height: 148px" src="data:image/jpg;base64,${requestScope.CONTRACT_IMAGE}" alt="Contract Image" style="width:100%;max-width:300px">
<!--                                <img src="data:image/jpg;base64,${requestScope.CONTRACT_IMAGE}" width="240" height="300"/>-->
                                <label>Contract Image  ${requestScope.ADD_CONTRACT_ERROR.contractImage}</label>
                                <input name="contractImage" id="contractImage" type="file" placeholder="Upload contract image"  accept="image/*">
                            </div>

                                <input type="hidden" name="type" value="Contract"/>
                                <input type="hidden" name="contractID" value="${requestScope.CONTRACT_DETAIL.contractID}"/>


                        </div>
                    </div>

                    <div class="details family" >

                        <div class="buttons">    
                            <button class="sumbit" type="reset">
                                <span class="btnText">Reset</span>
                            </button>       
                            <button class="submit" type="submit" name="action" value="Update">
                                <span class="btnText">Update</span>
                                <i class="uil uil-navigator"></i>
                            </button> 
                        </div>
                    </div> 
                </div>
            </form>
            <div id="myModal" class="modal">
                <span class="close">&times;</span>
                <img class="modal-content" id="img01">
                <div id="caption"></div>
            </div>
            <button class="backButton">
                <i class="uil uil-arrow-left"></i>
                <span class="btnText"><a href="adminContractPage.jsp">Back to ${param.type} page</a></span>               
            </button> 
        </div>
        <%}%>
        <script src="js/addjavascript.js"></script>
        <script src="js/addUserJS.js"></script>
        <script>
// Get the modal
                                    var modal = document.getElementById("myModal");

// Get the image and insert it inside the modal - use its "alt" text as a caption
                                    var img = document.getElementById("myImg");
                                    var modalImg = document.getElementById("img01");
                                    var captionText = document.getElementById("caption");
                                    img.onclick = function () {
                                        modal.style.display = "block";
                                        modalImg.src = this.src;
                                        captionText.innerHTML = this.alt;
                                    }

// Get the <span> element that closes the modal
                                    var span = document.getElementsByClassName("close")[0];

// When the user clicks on <span> (x), close the modal
                                    span.onclick = function () {
                                        modal.style.display = "none";
                                    }
        </script>
    </body>
</html>

