<%-- 
    Document   : newjsp
    Created on : Jun 18, 2022, 1:59:07 AM
    Author     : Phi Long
--%>

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
    </head>
    <body>
        <!--        else if (("Customer Resident").contains(request.getParameter("type"))&&request.getAttribute("APARTMENT_LIST") == null) {
                        request.getRequestDispatcher("MainController?action=GetMaterial&require=Apartment&type="+request.getParameter("type")+"&redirect=adminAddUserPage.jsp").forward(request, response);
                    }-->
        <% if (request.getParameter("type") == null) {
                response.sendRedirect("adminMainPage.jsp");
            } else {
                String backValue = (request.getParameter("type")).replaceAll(" ", "");%>
        <div class="container">
            <header>Add New ${param.type}</header>

            <form action="MainController" method="POST" enctype="multipart/form-data">


                <div class="form first">
                    <div class="details address">
                        <span class="title">Contract details</span>

                        <div class="fields">
                            <div class="input-field">
                                <label>UserID  ${requestScope.ADD_CONTRACT_ERROR.userID}</label>
                                <input name="userID" value="${param.userID}" type="text" placeholder="Enter UserID" required>
                            </div>

                            <div class="input-field">
                                <label>ApartmentID  ${requestScope.ADD_CONTRACT_ERROR.apartmentID}</label>
                                <input name="apartmentID" value="${param.apartmentID}" type="text" placeholder="Enter apartmentID" required>
                            </div>

                            <div class="input-field">
                                <label>Date Sign  ${requestScope.ADD_CONTRACT_ERROR.dateSign}</label>
                                <input name="dateSign" value="${param.dateSign}" type="date" placeholder="Enter DateSign" required>
                            </div>

                            <div class="input-field">
                                <label>Contract Image  ${requestScope.ADD_CONTRACT_ERROR.contractImage}</label>
                                <input name="contractImage" id="contractImage" type="file" placeholder="Upload contract image" required accept="image/*">
                            </div>

                            <div class="input-field">
                                <label>Contract type</label>
                                <select name="contractType" onchange="showDiv(this)" value="${param.contractType}">
                                    <option value="buying">Buying</option>    
                                    <option value="amortization">Amortization</option>                                
                                    <option value="leasing">Leasing</option>
                                </select>
                            </div>
                            <div class="input-field"></div>
                        </div>
                    </div>

                    <div class="details family">
                        <span class="title">Sub Information For Contract</span>

                        <div class="fields" id="amortization" style="display:none">
                            <div class="input-field" >
                                <label>Month(s) Of Debt</label>
                                <select name="monthsOfDebt" value="${param.monthsOfDebt}">
                                    <option value="60">5 years (60 months)</option>    
                                    <option value="120">10 years (120 months)</option>                                
                                </select>
                            </div>
                            <div class="input-field" >
                                <label>Interest Rate ${requestScope.ADD_CONTRACT_ERROR.interestRate}</label>
                                <input name="interestRate" value="${param.interestRate}" type="number" step="0.01" placeholder="Enter interest rate">
                            </div>
                                    
                            <div class="input-field"></div> 
                        </div>        
                                  
                        <div class="fields" id="leasing" style="display:none">            
                            <div class="input-field">
                                <label>Expiry Date  ${requestScope.ADD_CONTRACT_ERROR.expiryDate}</label>
                                <input name="expiryDate" value="${param.expiryDate}" type="date" placeholder="Enter expiry date">
                            </div>

                            <div class="input-field"></div> 
                            <div class="input-field"></div>  
                        </div>
                        <input type="hidden" name="type" value="${param.type}"/>
                        ${requestScope.ADD_CONTRACT_ERROR.errorMessage}
                        ${requestScope.ADD_CONTRACT_SUCCESS}
                        <div class="buttons">   
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


            </form>
            <button class="backButton">
                <i class="uil uil-arrow-left"></i>
                <span class="btnText"><a href="admin<%=backValue%>Page.jsp">Back to ${param.type} page</a></span>               
            </button> 
        </div>
        <%}%>
        <script src="js/addjavascript.js"></script>
        <script src="js/addUserJS.js"></script>
        <script>function showDiv(element)
                                    {
                                        if (element.value == "leasing") {
                                            document.getElementById('amortization').style.display = 'none';
                                            document.getElementById('leasing').style.display = 'flex';
                                        } else if (element.value == "amortization") {
                                            document.getElementById('leasing').style.display = 'none';
                                            document.getElementById('amortization').style.display = 'flex';
                                        } else if (element.value == "buying") {
                                            document.getElementById('leasing').style.display = 'none';
                                            document.getElementById('amortization').style.display = 'none';
                                        }
                                    }</script>
    </body>
</html>

