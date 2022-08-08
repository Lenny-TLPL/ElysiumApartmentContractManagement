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
            } else {%>
        <div class="container">
            <header>Add New ${param.type}</header>

            <form action="MainController" method="POST" enctype="multipart/form-data" name = "addBillingHistoryForm">
                <div class="form first">
                    <div class="details personal">
                        <span class="title">Billing Details</span>

                        <div class="fields">
                            <div class="input-field">
                                <label>UserID ${requestScope.ADD_BILLING_HISTORY_ERROR.userID}</label>
                                <input readonly="" name="userID" type="text" class="form-control" placeholder="Enter userID" required="" value="${requestScope.USER_MONTHLY_FEE.userID}" minlength="4" maxlength="60">     
                            </div>

                            <div class="input-field">
                                <label>ApartmentID ${requestScope.ADD_BILLING_HISTORY_ERROR.BillName}</label>
                                <input readonly="" name="apartmentID" type="text" class="form-control" placeholder="Enter apartmentID" required="" value="${requestScope.USER_MONTHLY_FEE.apartmentID}" minlength="4" maxlength="60">     
                            </div>

                            <div class="input-field">                                
                            </div>

                            <div class="fields">
                                <label>Value  ${requestScope.ADD_BILLING_HISTORY_ERROR.value}</label>
                                <br/>
                                <label style="width:100%"><input  onchange="calculate('contractFee')" type="radio" name="contractFee" value="${requestScope.USER_MONTHLY_FEE.contractFee}">Contract Fee:  ${requestScope.USER_MONTHLY_FEE.contractFee}</label>
                                <label style="width:100%"><input  onchange="calculate('waterFee')" type="radio" name="waterFee" value="${requestScope.USER_MONTHLY_FEE.waterFee}">Water Fee:  ${requestScope.USER_MONTHLY_FEE.waterFee}</label>
                                <label style="width:100%"><input  onchange="calculate('electricityFee')" type="radio" name="electricityFee" value="${requestScope.USER_MONTHLY_FEE.electricityFee}">Electrictiy Fee:  ${requestScope.USER_MONTHLY_FEE.electricityFee}</label>
                                <label style="width:100%"><input  onchange="calculate('serviceFee')" type="radio" name="serviceFee" value="${requestScope.USER_MONTHLY_FEE.serviceFee}">Service Fee:  ${requestScope.USER_MONTHLY_FEE.serviceFee}</label>
                                <label style="width:100%"><input  onchange="calculate('otherFee')" type="radio" name="otherFeeRadio">Other Fee: <input style="display: none" oninput="calculate()" type="number" name="otherFee" value="" step="0.01"></label>                                
                            </div>

                            <div class="input-field">
                                <label>Received Value  ${requestScope.ADD_BILLING_HISTORY_ERROR.receivedValue}</label>
                                <input oninput="calculate()" type="number" name ="receivedValue" class="form-control" placeholder="Enter received value" required value="${param.price}" step="0.01">
                            </div>

                            <div class="input-field">
                                <label>Change  ${requestScope.ADD_BILLING_HISTORY_ERROR.change}</label>
                                <input name="change" readonly="" type="number" class="form-control" placeholder="" required value="${param.price}" step="0.01">
                            </div>

                            <div class="input-field">

                            </div>


                        </div>
                    </div>

                    <div class="details ID">

                        <input type="hidden" name="type" value="${param.type}"/>
                        ${requestScope.ADD_BILLING_ERROR.errorMessage}
                        ${requestScope.ADD_BILLING_SUCCESS} 
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
                <span class="btnText"><a href="adminMonthlyFeePage.jsp">Back to monthly fee page</a></span>               
            </button> 
            <button class="backButton">
                <i class="uil uil-arrow-left"></i>
                <span class="btnText"><a href="adminBillingHistoryPage.jsp">Back to ${param.type} page</a></span>               
            </button> 
        </div>
        <%}%>
        <script src="js/addjavascript.js"></script>
        <script>
            // function radioButtonChecked(idName){
            //     document.forms.addBillingHistoryForm.contractFee.checked=false;
            //     document.forms.addBillingHistoryForm.electricityFee.checked=false;
            //     document.forms.addBillingHistoryForm.waterFee.checked=false;
            //     document.forms.addBillingHistoryForm.serviceFee.checked=false;
            //     document.forms.addBillingHistoryForm.otherFee.checked=false;
            //     document.forms.addBillingHistoryForm.(idName).checked=true;
            // }
                                    function calculate(idName) {
                                        if(idName==("contractFee")){
                                            document.forms.addBillingHistoryForm.contractFee.checked=false;
                                        document.forms.addBillingHistoryForm.electricityFee.checked=false;
                                        document.forms.addBillingHistoryForm.waterFee.checked=false;
                                        document.forms.addBillingHistoryForm.serviceFee.checked=false;
                                        document.forms.addBillingHistoryForm.otherFeeRadio.checked=false;
                                        document.forms.addBillingHistoryForm.contractFee.checked=true;
                                        document.forms.addBillingHistoryForm.otherFee.style="display: none;";
                                        }else if (idName==("serviceFee")){
                                            document.forms.addBillingHistoryForm.contractFee.checked=false;
                                        document.forms.addBillingHistoryForm.electricityFee.checked=false;
                                        document.forms.addBillingHistoryForm.waterFee.checked=false;
                                        document.forms.addBillingHistoryForm.serviceFee.checked=false;
                                        document.forms.addBillingHistoryForm.otherFeeRadio.checked=false;
                                        document.forms.addBillingHistoryForm.serviceFee.checked=true;
                                        document.forms.addBillingHistoryForm.otherFee.style="display: none;";
                                        }else if (idName==("waterFee")){
                                            document.forms.addBillingHistoryForm.contractFee.checked=false;
                                        document.forms.addBillingHistoryForm.electricityFee.checked=false;
                                        document.forms.addBillingHistoryForm.waterFee.checked=false;
                                        document.forms.addBillingHistoryForm.serviceFee.checked=false;
                                        document.forms.addBillingHistoryForm.otherFeeRadio.checked=false;
                                        document.forms.addBillingHistoryForm.waterFee.checked=true;
                                        document.forms.addBillingHistoryForm.otherFee.style="display: none;";
                                        }else if (idName==("electricityFee")){
                                            document.forms.addBillingHistoryForm.contractFee.checked=false;
                                        document.forms.addBillingHistoryForm.electricityFee.checked=false;
                                        document.forms.addBillingHistoryForm.waterFee.checked=false;
                                        document.forms.addBillingHistoryForm.serviceFee.checked=false;
                                        document.forms.addBillingHistoryForm.otherFeeRadio.checked=false;
                                        document.forms.addBillingHistoryForm.electricityFee.checked=true;
                                        document.forms.addBillingHistoryForm.otherFee.style="display: none;";
                                        }else if (idName==("otherFee")){
                                        document.forms.addBillingHistoryForm.contractFee.checked=false;
                                        document.forms.addBillingHistoryForm.electricityFee.checked=false;
                                        document.forms.addBillingHistoryForm.waterFee.checked=false;
                                        document.forms.addBillingHistoryForm.serviceFee.checked=false;
                                        document.forms.addBillingHistoryForm.otherFeeRadio.checked=false;
                                        document.forms.addBillingHistoryForm.otherFeeRadio.checked=true;
                                        document.forms.addBillingHistoryForm.otherFee.style="display: incline-block;";
                                        }
                                        var value = Number(0);
                                        console.log(value);
                                        if (document.forms.addBillingHistoryForm.contractFee.checked) {
                                            console.log(value);
                                            value += Number(document.forms.addBillingHistoryForm.contractFee.value);
                                        }
                                        console.log(value);
                                        if (document.forms.addBillingHistoryForm.electricityFee.checked) {
                                            value += Number(document.forms.addBillingHistoryForm.electricityFee.value);
                                            console.log(value);
                                        }
                                        if (document.forms.addBillingHistoryForm.waterFee.checked) {
                                            value += Number(document.forms.addBillingHistoryForm.waterFee.value);
                                            console.log(value);
                                        }
                                        if (document.forms.addBillingHistoryForm.serviceFee.checked) {
                                            value += Number(document.forms.addBillingHistoryForm.serviceFee.value);
                                            console.log(value);
                                        }
                                        if (document.forms.addBillingHistoryForm.otherFee != null) {
                                            value += Number(document.forms.addBillingHistoryForm.otherFee.value);
                                            console.log(value);
                                        }
                                        if (document.forms.addBillingHistoryForm.receivedValue != null && document.forms.addBillingHistoryForm.receivedValue.value != 0) {
                                            document.forms.addBillingHistoryForm.change.value = Number(document.forms.addBillingHistoryForm.receivedValue.value) - value;
                                        }
                                    }
        </script>
    </body>
</html>

