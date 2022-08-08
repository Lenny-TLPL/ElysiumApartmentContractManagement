/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import sample.DAO.ApartmentBuildingDAO;
import sample.DAO.ApartmentDAO;
import sample.DAO.ApartmentTypeDAO;
import sample.DAO.BillingHistoryDAO;
import sample.DAO.ContractDAO;
import sample.DAO.DistrictDAO;
import sample.DAO.MonthlyFeeDAO;
import sample.DAO.NotificationDAO;
import sample.DAO.PermissionDAO;
import sample.DAO.PrivateNotificationDAO;
import sample.DAO.RoleDAO;
import sample.DAO.ServiceDAO;
import sample.DAO.UserDAO;
import sample.DAO.UserPermissionDAO;
import sample.DTO.ApartmentBuildingDTO;
import sample.DTO.ApartmentBuildingError;
import sample.DTO.ApartmentDTO;
import sample.DTO.ApartmentError;
import sample.DTO.ApartmentTypeError;
import sample.DTO.BillingHistoryDTO;
import sample.DTO.BillingHistoryError;
import sample.DTO.ContractDTO;
import sample.DTO.ContractError;
import sample.DTO.DistrictError;
import sample.DTO.NotificationError;
import sample.DTO.PermissionDTO;
import sample.DTO.PermissionError;
import sample.DTO.PrivateNotificationError;
import sample.DTO.ServiceDTO;
import sample.DTO.ServiceError;
import sample.DTO.UserDTO;
import sample.DTO.UserError;
import sample.utils.DateUtils;

/**
 *
 * @author Phi Long
 */
@MultipartConfig
public class AddController extends HttpServlet {

    private static final String CUSTOMER = "Customer";
    private static final String RESIDENT = "Resident";
    private static final String EMPLOYEE = "Employee";
    private static final String HR_MANAGER = "HR Manager";
    private static final String BOARD_MANAGER = "Board Manager";
    private static final String CONTRACT = "Contract";
    private static final String SERVICE = "Service";
    private static final String NOTIFICATION = "Notification";
    private static final String PRIVATE_NOTIFICATION = "Private Notification";
    private static final String APARTMENT_TYPE = "Apartment Type";
    private static final String APARTMENT = "Apartment";
    private static final String APARTMENT_BUILDING = "Apartment Building";
    private static final String DISTRICT = "District";
    private static final String BILLING_HISTORY = "Billing History";
    private static final String PERMISSION = "Permission";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String url = null;
        String type = request.getParameter("type");
        if (("Board Manager HR Manager Employee Customer Resident").contains(type)) {
            url = "adminAddUserPage.jsp";
        } else {
            url = "adminAdd" + type.replaceAll(" ", "") + "Page.jsp";
        }

        try {
            switch (type) {
                case BOARD_MANAGER:

                    String fullName = request.getParameter("fullName");
                    String gender = request.getParameter("gender");
                    String email = request.getParameter("email");
                    String phone = request.getParameter("phone");
                    String address = request.getParameter("address");
                    String citizenID = request.getParameter("citizenID");
                    Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
                    String password = request.getParameter("password");
                    String confirmPassword = request.getParameter("confirmPassword");
                    String[] permissions = request.getParameterValues("permissions");
                    boolean check = true;
                    String phoneRegex = "^\\d{11}$";
                    String phoneRegex2 = "^\\d{10}$";

                    UserPermissionDAO userPermissionDao = new UserPermissionDAO();
                    UserDAO userDao = new UserDAO();
                    RoleDAO roleDao = new RoleDAO();
                    UserError userError = new UserError();
                    if (fullName.length() > 60 || fullName.length() < 4 || fullName.trim().equals("")) {
                        userError.setFullName("FullName needs to be between 4 and 60 characters.");
                        check = false;
                    }
                    if (userDao.checkDuplicateAdmin(citizenID)) {
                        userError.setCitizenID("Duplicate citizenID");
                        check = false;
                    }
                    if (userDao.checkDuplicateEmailAdmin(email)) {
                        userError.setEmail("Duplicate email");
                        check = false;
                    }
                    if (userDao.checkDuplicatePhoneAdmin(phone)) {
                        userError.setPhone("Duplicate phone");
                        check = false;
                    }
                    if (!(citizenID.length() == 12)) {
                        userError.setCitizenID("Invalid citizenID");
                        check = false;
                    }
                    if (!DateUtils.checkValidDate(birthday)) {
                        userError.setBirthday("Invalid birthday");
                        check = false;
                    }
                    if (!confirmPassword.equals(password)) {
                        userError.setPassword("Invalid confirm");
                        check = false;
                    }
                    if (!phone.matches(phoneRegex) && !phone.matches(phoneRegex2)) {
                        userError.setPhone("Invalid phone number");
                        check = false;
                    }
                    if (check) {
                        boolean checkAdd = userDao.addUser(new UserDTO("", fullName, email, phone, address, birthday, citizenID, gender, password, null, true, roleDao.getUserRoleID(type)));
                        for (String permisson : permissions) {
                            userPermissionDao.addUser(userDao.getUserIDByCitizenID(type, citizenID), Integer.parseInt(permisson));
                        }
                        if (checkAdd) {
                            request.setAttribute("ADD_USER_SUCCESS", "New board manager has been added.");
                        } else {
                            userError.setErrorMessage("Fail to add new board manager.");
                            request.setAttribute("ADD_USER_ERROR", userError);
                        }
                    } else {
                        userError.setErrorMessage("Fail to add new board manager.");
                        request.setAttribute("ADD_USER_ERROR", userError);
                    }
                    break;
                case HR_MANAGER:

                    fullName = request.getParameter("fullName");
                    gender = request.getParameter("gender");
                    email = request.getParameter("email");
                    phone = request.getParameter("phone");
                    address = request.getParameter("address");
                    citizenID = request.getParameter("citizenID");
                    birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
                    password = request.getParameter("password");
                    confirmPassword = request.getParameter("confirmPassword");
                    permissions = request.getParameterValues("permissions");
                    check = true;
                    phoneRegex = "^\\d{11}$";
                    phoneRegex2 = "^\\d{10}$";

                    userPermissionDao = new UserPermissionDAO();
                    userDao = new UserDAO();
                    roleDao = new RoleDAO();
                    userError = new UserError();
                    if (fullName.length() > 60 || fullName.length() < 4 || fullName.trim().equals("")) {
                        userError.setFullName("FullName needs to be between 4 and 60 characters.");
                        check = false;
                    }
                    if (userDao.checkDuplicateAdmin(citizenID)) {
                        userError.setCitizenID("Duplicate citizenID");
                        check = false;
                    }
                    if (!(citizenID.length() == 12)) {
                        userError.setCitizenID("Invalid citizenID");
                        check = false;
                    }
                    if (userDao.checkDuplicatePhoneAdmin(phone)) {
                        userError.setPhone("Duplicate phone");
                        check = false;
                    }
                    if (userDao.checkDuplicateEmailAdmin(email)) {
                        userError.setEmail("Duplicate email");
                        check = false;
                    }
                    if (!DateUtils.checkValidDate(birthday)) {
                        userError.setBirthday("Invalid birthday");
                        check = false;
                    }
                    if (!confirmPassword.equals(password)) {
                        userError.setPassword("Invalid confirm");
                        check = false;
                    }
                    if (!phone.matches(phoneRegex) && !phone.matches(phoneRegex2)) {
                        userError.setPhone("Invalid phone number");
                        check = false;
                    }
                    if (check) {
                        boolean checkAdd = userDao.addUser(new UserDTO("", fullName, email, phone, address, birthday, citizenID, gender, password, null, true, roleDao.getUserRoleID(type)));
                        for (String permisson : permissions) {
                            userPermissionDao.addUser(userDao.getUserIDByCitizenID(type, citizenID), Integer.parseInt(permisson));
                        }
                        if (checkAdd) {
                            request.setAttribute("ADD_USER_SUCCESS", "New HR Manager has been added.");
                        } else {
                            userError.setErrorMessage("Fail to add new HR Manager.");
                            request.setAttribute("ADD_USER_ERROR", userError);
                        }
                    } else {
                        userError.setErrorMessage("Fail to add new HR Manager.");
                        request.setAttribute("ADD_USER_ERROR", userError);
                    }
                    break;
                case EMPLOYEE:

                    fullName = request.getParameter("fullName");
                    gender = request.getParameter("gender");
                    email = request.getParameter("email");
                    phone = request.getParameter("phone");
                    address = request.getParameter("address");
                    citizenID = request.getParameter("citizenID");
                    birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
                    password = request.getParameter("password");
                    confirmPassword = request.getParameter("confirmPassword");
                    permissions = request.getParameterValues("permissions");
                    check = true;
                    phoneRegex = "^\\d{11}$";
                    phoneRegex2 = "^\\d{10}$";

                    userPermissionDao = new UserPermissionDAO();
                    userDao = new UserDAO();
                    roleDao = new RoleDAO();
                    userError = new UserError();
                    if (fullName.length() > 60 || fullName.length() < 4 || fullName.trim().equals("")) {
                        userError.setFullName("FullName needs to be between 4 and 60 characters.");
                        check = false;
                    }
                    if (userDao.checkDuplicateAdmin(citizenID)) {
                        userError.setCitizenID("Duplicate citizenID");
                        check = false;
                    }
                    if (!(citizenID.length() == 12)) {
                        userError.setCitizenID("Invalid citizenID");
                        check = false;
                    }
                    if (userDao.checkDuplicatePhoneAdmin(phone)) {
                        userError.setPhone("Duplicate phone");
                        check = false;
                    }
                    if (userDao.checkDuplicateEmailAdmin(email)) {
                        userError.setEmail("Duplicate email");
                        check = false;
                    }
                    if (!DateUtils.checkValidDate(birthday)) {
                        userError.setBirthday("Invalid birthday");
                        check = false;
                    }
                    if (!confirmPassword.equals(password)) {
                        userError.setPassword("Invalid confirm");
                        check = false;
                    }
                    if (!phone.matches(phoneRegex) && !phone.matches(phoneRegex2)) {
                        userError.setPhone("Invalid phone number");
                        check = false;
                    }
                    if (check) {
                        boolean checkAdd = userDao.addUser(new UserDTO("", fullName, email, phone, address, birthday, citizenID, gender, password, null, true, roleDao.getUserRoleID(type)));
                        for (String permisson : permissions) {
                            userPermissionDao.addUser(userDao.getUserIDByCitizenID(type, citizenID), Integer.parseInt(permisson));
                        }
                        if (checkAdd) {
                            request.setAttribute("ADD_USER_SUCCESS", "New employee has been added.");
                        } else {
                            userError.setErrorMessage("Fail to add new employee.");
                            request.setAttribute("ADD_USER_ERROR", userError);
                        }
                    } else {
                        userError.setErrorMessage("Fail to add new emloyee.");
                        request.setAttribute("ADD_USER_ERROR", userError);
                    }
                    break;
                case CUSTOMER:

                    fullName = request.getParameter("fullName");
                    gender = request.getParameter("gender");
                    email = request.getParameter("email");
                    phone = request.getParameter("phone");
                    address = request.getParameter("address");
                    citizenID = request.getParameter("citizenID");
                    birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
                    password = request.getParameter("password");
                    confirmPassword = request.getParameter("confirmPassword");
                    Date dateSign = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateSign"));
                    Part contractImageFilePart = request.getPart("contractImage");
                    String apartmentID = request.getParameter("apartmentID");
                    String contractType = request.getParameter("contractType");
                    float interestRate = 0;
//                    Blob contractImage = imageFilePart.getInputStream();
                    Date expiryDate = null;
                    int monthsOfDebt = 0;
                    if (contractType.equals("amortization")) {
                        interestRate = Float.parseFloat(request.getParameter("interestRate"));
                        monthsOfDebt = Integer.parseInt(request.getParameter("monthsOfDebt"));
                        expiryDate = DateUtils.plusMonths(dateSign, monthsOfDebt);
                    } else if (contractType.equals("leasing")) {
                        expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("expiryDate"));
                    }
                    check = true;
                    phoneRegex = "^\\d{11}$";
                    phoneRegex2 = "^\\d{10}$";

                    ContractDAO contractDao = new ContractDAO();
                    ApartmentDAO apartmentDao = new ApartmentDAO();
                    MonthlyFeeDAO monthlyFeeDao = new MonthlyFeeDAO();
                    userDao = new UserDAO();
                    roleDao = new RoleDAO();
                    userError = new UserError();
                    ContractError contractError = new ContractError();
                    if (fullName.length() > 60 || fullName.length() < 4 || fullName.trim().equals("")) {
                        userError.setFullName("FullName needs to be between 4 and 60 characters.");
                        check = false;
                    }
                    if (userDao.checkDuplicateUser(citizenID)) {
                        userError.setCitizenID("Duplicate citizenID");
                        check = false;
                    }
                    if (!(citizenID.length() == 12)) {
                        userError.setCitizenID("Invalid citizenID");
                        check = false;
                    }
                    if (userDao.checkDuplicatePhoneUser(phone)) {
                        userError.setPhone("Duplicate phone");
                        check = false;
                    }
                    if (userDao.checkDuplicateEmailUser(email)) {
                        userError.setEmail("Duplicate email");
                        check = false;
                    }
                    if (!DateUtils.checkValidDate(birthday)) {
                        userError.setBirthday("Invalid birthday");
                        check = false;
                    }
                    if (!confirmPassword.equals(password)) {
                        userError.setPassword("Invalid confirm");
                        check = false;
                    }
                    if (!phone.matches(phoneRegex) && !phone.matches(phoneRegex2)) {
                        userError.setPhone("Invalid phone number");
                        check = false;
                    }
                    if (expiryDate != null) {
                        if (DateUtils.compareTwoDate(dateSign, expiryDate)) {
                            contractError.setDateSign("Invalid dateSign");
                            check = false;
                        }
                    }
                    if (!DateUtils.checkValidDate(dateSign) || !DateUtils.compareTwoDate(dateSign, birthday)) {
                        contractError.setDateSign("Invalid dateSign");
                        check = false;
                    }
                    if (("amortization leasing").contains(contractType)) {
                        if (DateUtils.checkValidDate(expiryDate) || !DateUtils.compareTwoDate(expiryDate, birthday) || DateUtils.compareTwoDate(dateSign, expiryDate)) {
                            contractError.setExpiryDate("Invalid expiryDate");
                            check = false;
                        }
                    }

                    if (contractImageFilePart == null) {
                        contractError.setContractImage("Invalid image");
                        check = false;
                    }
                    if (apartmentDao.getApartment(apartmentID) == null) {
                        contractError.setApartmentID("Invalid apartmentID");
                    } else if (!apartmentDao.getApartment(apartmentID).getApartmentStatus().equals("available")) {
                        contractError.setApartmentID("Apartment is not available");
                        check = false;
                    }
//                    if (contractType.equals("amortization") && DateUtils.subtractTwoDay(dateSign, expiryDate) != monthsOfDebt) {
//                        contractError.setExpiryDate("Invalid expiryDate");
//                        check = false;
//                    }
                    if (check) {
                        boolean checkAdd = userDao.addUser(new UserDTO("", fullName, email, phone, address, birthday, citizenID, gender, password, null, true, roleDao.getUserRoleID(type)));
                        boolean checkAddContract = contractDao.addContract(new ContractDTO(0, dateSign, null, userDao.getUserIDByCitizenID(type, citizenID), apartmentID, 0, expiryDate, monthsOfDebt, interestRate, contractType, true), contractImageFilePart);
                        if (checkAdd && checkAddContract) {
                            monthlyFeeDao.addMonthly(userDao.getUserIDByCitizenID(type, citizenID), apartmentID);
                            monthlyFeeDao.monthlyFeeCalculate(apartmentID);
                            request.setAttribute("ADD_USER_SUCCESS", "New customer has been added.");
                            request.setAttribute("ADD_CONTRACT_SUCCESS", "New contract has been added.");
                        } else {
                            userDao.deleteUser(userDao.getUserIDByCitizenID(type, citizenID), roleDao.getUserRoleID(type));
                            userError.setErrorMessage("Fail to add new customer.");
                            contractError.setErrorMessage("Fail to add new contract.");
                            request.setAttribute("ADD_USER_ERROR", userError);
                            request.setAttribute("ADD_CONTRACT_ERROR", contractError);
                        }
                    } else {
                        contractError.setErrorMessage("Fail to add new contract");
                        userError.setErrorMessage("Fail to add new customer.");
                        request.setAttribute("ADD_USER_ERROR", userError);
                        request.setAttribute("ADD_CONTRACT_ERROR", contractError);
                    }
                    break;
                case RESIDENT:

                    fullName = request.getParameter("fullName");
                    gender = request.getParameter("gender");
                    email = request.getParameter("email");
                    phone = request.getParameter("phone");
                    address = request.getParameter("address");
                    citizenID = request.getParameter("citizenID");
                    birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
                    password = request.getParameter("password");
                    confirmPassword = request.getParameter("confirmPassword");
                    dateSign = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateSign"));
                    contractImageFilePart = request.getPart("contractImage");
                    apartmentID = request.getParameter("apartmentID");
                    contractType = request.getParameter("contractType");
//                    Blob contractImage = imageFilePart.getInputStream();
                    interestRate = 0;
                    expiryDate = null;
                    monthsOfDebt = 0;
                    if (contractType.equals("amortization")) {
                        interestRate = Float.parseFloat(request.getParameter("interestRate"));
                        monthsOfDebt = Integer.parseInt(request.getParameter("monthsOfDebt"));
                        expiryDate = DateUtils.plusMonths(dateSign, monthsOfDebt);
                    } else if (contractType.equals("leasing")) {
                        expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("expiryDate"));
                    }
                    check = true;
                    phoneRegex = "^\\d{11}$";
                    phoneRegex2 = "^\\d{10}$";

                    contractDao = new ContractDAO();
                    apartmentDao = new ApartmentDAO();
                    monthlyFeeDao = new MonthlyFeeDAO();
                    userDao = new UserDAO();
                    roleDao = new RoleDAO();
                    userError = new UserError();
                    contractError = new ContractError();
                    if (fullName.length() > 60 || fullName.length() < 4 || fullName.trim().equals("")) {
                        userError.setFullName("FullName needs to be between 4 and 60 characters.");
                        check = false;
                    }
                    if (userDao.checkDuplicateUser(citizenID)) {
                        userError.setCitizenID("Duplicate citizenID");
                        check = false;
                    }
                    if (!(citizenID.length() == 12)) {
                        userError.setCitizenID("Invalid citizenID");
                        check = false;
                    }
                    if (userDao.checkDuplicatePhoneUser(phone)) {
                        userError.setPhone("Duplicate phone");
                        check = false;
                    }
                    if (userDao.checkDuplicateEmailUser(email)) {
                        userError.setEmail("Duplicate email");
                        check = false;
                    }
                    if (!DateUtils.checkValidDate(birthday)) {
                        userError.setBirthday("Invalid birthday");
                        check = false;
                    }
                    if (!confirmPassword.equals(password)) {
                        userError.setPassword("Invalid confirm");
                        check = false;
                    }
                    if (!phone.matches(phoneRegex) && !phone.matches(phoneRegex2)) {
                        userError.setPhone("Invalid phone number");
                        check = false;
                    }
                    if (expiryDate != null) {
                        if (DateUtils.compareTwoDate(dateSign, expiryDate)) {
                            contractError.setDateSign("Invalid dateSign");
                            check = false;
                        }
                    }
                    if (!DateUtils.checkValidDate(dateSign) || !DateUtils.compareTwoDate(dateSign, birthday)) {
                        contractError.setDateSign("Invalid dateSign");
                        check = false;
                    }
                    if (("amortization leasing").contains(contractType)) {
                        if (DateUtils.checkValidDate(expiryDate) || !DateUtils.compareTwoDate(expiryDate, birthday) || DateUtils.compareTwoDate(dateSign, expiryDate)) {
                            contractError.setExpiryDate("Invalid expiryDate");
                            check = false;
                        }
                    }

                    if (contractImageFilePart == null) {
                        contractError.setContractImage("Invalid image");
                        check = false;
                    }
                    if (apartmentDao.getApartment(apartmentID) == null) {
                        contractError.setApartmentID("Invalid apartmentID");
                    } else if (!apartmentDao.getApartment(apartmentID).getApartmentStatus().equals("available")) {
                        contractError.setApartmentID("Apartment is not available");
                        check = false;
                    }
//                    if (contractType.equals("amortization") && DateUtils.subtractTwoDay(dateSign, expiryDate) != monthsOfDebt) {
//                        contractError.setExpiryDate("Invalid expiryDate");
//                        check = false;
//                    }
                    if (check) {
                        boolean checkAdd = userDao.addUser(new UserDTO("", fullName, email, phone, address, birthday, citizenID, gender, password, null, true, roleDao.getUserRoleID(type)));
                        boolean checkAddContract = contractDao.addContract(new ContractDTO(0, dateSign, null, userDao.getUserIDByCitizenID(type, citizenID), apartmentID, 0, expiryDate, monthsOfDebt, interestRate, contractType, true), contractImageFilePart);
                        if (checkAdd && checkAddContract) {
                            monthlyFeeDao.addMonthly(userDao.getUserIDByCitizenID(type, citizenID), apartmentID);
                            monthlyFeeDao.monthlyFeeCalculate(apartmentID);
                            request.setAttribute("ADD_USER_SUCCESS", "New resident has been added.");
                            request.setAttribute("ADD_CONTRACT_SUCCESS", "New contract has been added.");
                        } else {
                            userDao.deleteUser(userDao.getUserIDByCitizenID(type, citizenID), roleDao.getUserRoleID(type));
                            userError.setErrorMessage("Fail to add new resident.");
                            contractError.setErrorMessage("Fail to add new contract.");
                            request.setAttribute("ADD_USER_ERROR", userError);
                            request.setAttribute("ADD_CONTRACT_ERROR", contractError);
                        }
                    } else {
                        contractError.setErrorMessage("Fail to add new contract.");
                        userError.setErrorMessage("Fail to add new resident.");
                        request.setAttribute("ADD_USER_ERROR", userError);
                        request.setAttribute("ADD_CONTRACT_ERROR", contractError);
                    }
                    break;

                case SERVICE:

                    String serviceName = request.getParameter("serviceName");
                    String description = request.getParameter("description");
                    float price = Float.parseFloat(request.getParameter("price"));
                    boolean status = Boolean.parseBoolean(request.getParameter("status"));
                    check = true;

                    ServiceDAO serviceDao = new ServiceDAO();
                    ServiceDTO service = null;
                    ServiceError serviceError = new ServiceError();

                    if (serviceName.length() > 60 || serviceName.length() < 4 || serviceName.trim().equals("")) {
                        serviceError.setServiceName("Name must be from 4 to 60 chars");
                        check = false;
                    }
                    if (serviceDao.getServiceByName(serviceName) != null) {
                        serviceError.setServiceName("Duplicate service name");
                        check = false;
                    }
                    if (price <= 0) {
                        serviceError.setPrice("Price must be >0");
                        check = false;
                    }
                    if (check) {
                        service = new ServiceDTO(0, serviceName, description, price, status);
                        boolean checkAdd = serviceDao.addService(service);
                        if (checkAdd) {
                            request.setAttribute("ADD_SERVICE_SUCCESS", "New service has been added.");
                        } else {
                            serviceError.setErrorMessage("Fail to add new service.");
                            request.setAttribute("ADD_SERVICE_ERROR", serviceError);
                        }
                    } else {
                        serviceError.setErrorMessage("Fail to add new service");
                        request.setAttribute("ADD_SERVICE_ERROR", serviceError);
                    }
                    break;
                case NOTIFICATION:

                    String notiHeader = request.getParameter("notiHeader");
                    String notiContent = request.getParameter("notiContent");
                    check = true;
                    NotificationError notiError = new NotificationError();
                    if (notiHeader.trim().equals("")) {
                        notiError.setNotiHeader("Header can not be null");
                        check = false;
                    }
                    if (notiContent.trim().equals("")) {
                        notiError.setNotiContent("Content can not be null");
                        check = false;
                    }

                    NotificationDAO notiDao = new NotificationDAO();

                    if (check) {
                        boolean checkAdd = notiDao.addNotification(notiHeader, notiContent);
                        if (checkAdd) {
                            request.setAttribute("ADD_NOTIFICATION_SUCCESS", "New notification has been added.");
                        } else {
                            request.setAttribute("ADD_NOTIFICATION_ERROR", "Fail to add new notification.");
                        }
                    } else {
                        request.setAttribute("ADD_NOTIFICATION_ERROR", "Fail to add new notification.");
                    }
                    break;
                case PRIVATE_NOTIFICATION:
                    notiHeader = request.getParameter("notiHeader");
                    notiContent = request.getParameter("notiContent");
                    String userID = request.getParameter("userID");
                    check = true;

                    PrivateNotificationError privateNotiError = new PrivateNotificationError();
                    PrivateNotificationDAO privateNotiDao = new PrivateNotificationDAO();
                    userDao = new UserDAO();

                    if (userDao.getUserByIDAndStatus(userID, true) == null) {
                        privateNotiError.setNotiID("Invalid userID");
                        check = false;
                    }
                    if (notiHeader.trim().equals("")) {
                        privateNotiError.setNotiHeader("Header can not be null");
                        check = false;
                    }
                    if (notiContent.trim().equals("")) {
                        privateNotiError.setNotiContent("Content can not be null");
                        check = false;
                    }
                    if (check) {
                        boolean checkAdd = privateNotiDao.addPrivateNotification(notiHeader, notiContent, userID);
                        if (checkAdd) {
                            request.setAttribute("ADD_PRIVATE_NOTIFICATION_SUCCESS", "New notification has been added.");
                        } else {
                            request.setAttribute("ADD_PRIVATE_NOTIFICATION_ERROR", "Fail to add new notification.");
                        }
                    } else {
                        privateNotiError.setErrorMessage("Fail to add new notification.");
                        request.setAttribute("ADD_PRIVATE_NOTIFICATION_ERROR", privateNotiError);
                    }
                    break;

                case PERMISSION:
//                    url = "adminAddPermissionPage.jsp";
                    String permissionName = request.getParameter("permissionName");
                    status = Boolean.parseBoolean(request.getParameter("status"));
                    String roleNamePriority = request.getParameter("rolePriority");
                    check = true;

                    PermissionDAO permissionDao = new PermissionDAO();
                    PermissionDTO permission = null;
                    PermissionError permissionError = new PermissionError();

                    if (permissionName.length() > 60 || permissionName.length() < 4 || permissionName.trim().equals("")) {
                        permissionError.setPermissionName("Name must be from 4 to 60 chars");
                        check = false;
                    }
                    if (permissionDao.getPermissionByName(permissionName) != null) {
                        permissionError.setPermissionName("Duplicate permission name");
                        check = false;
                    }
                    if (check) {
                        roleDao = new RoleDAO();
                        permission = new PermissionDTO(0, permissionName, roleNamePriority, status);
                        boolean checkAdd = permissionDao.addPermission(permission, roleDao.getUserRoleID(roleNamePriority));
                        if (checkAdd) {
                            request.setAttribute("ADD_PERMISSION_SUCCESS", "New permission has been added.");
                        } else {
                            permissionError.setErrorMessage("Fail to add new permission.");
                            request.setAttribute("ADD_PERMISSION_ERROR", permissionError);
                        }
                    } else {
                        permissionError.setErrorMessage("Fail to add new permission");
                        request.setAttribute("ADD_PERMISSION_ERROR", permissionError);
                    }
                    break;
                case CONTRACT:
                    userID = request.getParameter("userID");
                    dateSign = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateSign"));
                    contractImageFilePart = request.getPart("contractImage");
                    apartmentID = request.getParameter("apartmentID");
                    contractType = request.getParameter("contractType");
                    expiryDate = null;
                    monthsOfDebt = 0;
                    interestRate = 0;
                    if (contractType.equals("amortization")) {
                        interestRate = Float.parseFloat(request.getParameter("interestRate"));
                        monthsOfDebt = Integer.parseInt(request.getParameter("monthsOfDebt"));
                        expiryDate = DateUtils.plusMonths(dateSign, monthsOfDebt);
                    } else if (contractType.equals("leasing")) {
                        expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("expiryDate"));
                    }
                    check = true;

                    contractDao = new ContractDAO();
                    apartmentDao = new ApartmentDAO();
                    monthlyFeeDao = new MonthlyFeeDAO();
                    userDao = new UserDAO();
                    roleDao = new RoleDAO();
                    contractError = new ContractError();
                    UserDTO user = userDao.getUserByIDAndStatus(userID, true);

                    if (!userID.contains("ELY-") || user == null) {
                        contractError.setUserID("Invalid userID");
                        check = false;
                    }

                    if (expiryDate != null) {
                        if (DateUtils.compareTwoDate(dateSign, expiryDate)) {
                            contractError.setDateSign("Invalid expiryDate");
                            check = false;
                        }
                    }

                    if (user != null && userID.contains("ELY-")) {
                        if (!DateUtils.checkValidDate(dateSign) || !DateUtils.compareTwoDate(dateSign, user.getBirthDay())) {
                            contractError.setDateSign("Invalid dateSign");
                            check = false;
                        }

                        if (("amortization leasing").contains(contractType)) {
                            if (DateUtils.checkValidDate(expiryDate) || !DateUtils.compareTwoDate(expiryDate, user.getBirthDay()) || DateUtils.compareTwoDate(dateSign, expiryDate)) {
                                contractError.setExpiryDate("Invalid expiryDate");
                                check = false;
                            }
                        }
                    }

                    if (contractImageFilePart == null) {
                        contractError.setContractImage("Invalid image");
                        check = false;
                    }
                    if (apartmentDao.getApartment(apartmentID) == null) {
                        contractError.setApartmentID("Invalid apartmentID");
                    } else if (!apartmentDao.getApartment(apartmentID).getApartmentStatus().equals("available")) {
                        contractError.setApartmentID("Apartment is not available");
                        check = false;
                    }
//                    if (contractType.equals("amortization") && DateUtils.subtractTwoDay(dateSign, expiryDate) != monthsOfDebt) {
//                        contractError.setExpiryDate("Invalid expiryDate");
//                        check = false;
//                    }
                    if (check) {
                        boolean checkAddContract = contractDao.addContract(new ContractDTO(0, dateSign, null, userID, apartmentID, 0, expiryDate, monthsOfDebt, interestRate, contractType, true), contractImageFilePart);
                        if (checkAddContract) {
                            monthlyFeeDao.addMonthly(userID, apartmentID);
                            monthlyFeeDao.monthlyFeeCalculate(apartmentID);
                            if (contractType.equals("buying") || contractType.equals("amortization")) {
                                userDao.updateUserRole(roleDao.getUserRoleID("Resident"), userID);
                            }

                            request.setAttribute("ADD_USER_SUCCESS", "New resident has been added.");
                            request.setAttribute("ADD_CONTRACT_SUCCESS", "New contract has been added.");
                        } else {
                            contractError.setErrorMessage("Fail to add new contract.");
                            request.setAttribute("ADD_CONTRACT_ERROR", contractError);
                        }
                    } else {
                        contractError.setErrorMessage("Fail to add new contract.");
                        request.setAttribute("ADD_CONTRACT_ERROR", contractError);
                    }
                    break;
                case DISTRICT:
                    String districtName = request.getParameter("districtName");
                    DistrictDAO districtDao = new DistrictDAO();
                    DistrictError districtError = new DistrictError();
                    check = true;
                    if (districtName.trim().equals("")) {
                        districtError.setDistrictName("Name can not be null");
                        check = false;
                    }
                    if (districtDao.checkDuplicate(districtName)) {
                        districtError.setDistrictName("Duplicate district name.");
                        check = false;
                    }
                    if (check) {
                        boolean checkAddDistrict = districtDao.addDistrict(districtName);
                        if (checkAddDistrict) {
                            request.setAttribute("ADD_DISTRICT_SUCCESS", "New district has been added.");
                        } else {
                            request.setAttribute("ADD_DISTRICT_ERROR", "Fail to add new district.");
                        }
                    } else {
                        districtError.setErrorMessage("Fail to add new district.");
                        request.setAttribute("ADD_DISTRICT_ERROR", districtError);
                    }
                    break;
                case APARTMENT_BUILDING:
                    String buildingName = request.getParameter("buildingName");
                    int maxFloor = Integer.parseInt(request.getParameter("maxFloor"));
                    int maxApartment = Integer.parseInt(request.getParameter("maxApartment"));
                    int districtID = Integer.parseInt(request.getParameter("districtID"));
                    check = true;

                    ApartmentBuildingDAO apBuildingDao = new ApartmentBuildingDAO();
                    ApartmentBuildingError apBuildingError = new ApartmentBuildingError();

                    if (buildingName.trim().equals("")) {
                        apBuildingError.setBuildingName("Name can not be null");
                        check = false;
                    }
                    if (apBuildingDao.checkDuplicate(buildingName, districtID)) {
                        apBuildingError.setBuildingName("Duplicate building name");
                        check = false;
                    }
                    if (check) {
                        boolean checkAdd = apBuildingDao.addApartmentBuilding(buildingName, districtID, maxFloor, maxApartment);
                        if (checkAdd) {
                            request.setAttribute("ADD_BUILDING_SUCCESS", "New building has been added.");
                        } else {
                            request.setAttribute("ADD_BUILDING_ERROR", "Fail to add new building.");
                        }
                    } else {
                        apBuildingError.setErrorMessage("Fail to add new building.");
                        request.setAttribute("ADD_BUILDING_ERROR", apBuildingError);
                    }
                    break;
                case APARTMENT_TYPE:
                    String typeName = request.getParameter("typeName");
                    float buyingPrice = Float.parseFloat(request.getParameter("buyingPrice"));
                    float leasingPrice = Float.parseFloat(request.getParameter("leasingPrice"));
                    description = request.getParameter("description");

                    ApartmentTypeDAO typeDao = new ApartmentTypeDAO();
                    ApartmentTypeError typeError = new ApartmentTypeError();
                    check = true;

                    if (typeName.trim().equals("")) {
                        typeError.setTypeName("Name can not be null");
                        check = false;
                    }
                    if (typeDao.checkDuplicate(typeName)) {
                        typeError.setTypeName("Duplicate type name");
                        check = false;
                    }
                    if (buyingPrice == 0) {
                        typeError.setBuyingPrice("Buying price must be >0");
                        check = false;
                    }
                    if (leasingPrice == 0) {
                        typeError.setLeasingPrice("Leasing price must be >0");
                        check = false;
                    }
                    if (check) {
                        boolean checkAdd = typeDao.addApartmentType(typeName, buyingPrice, leasingPrice, description);
                        if (checkAdd) {
                            request.setAttribute("ADD_TYPE_SUCCESS", "New type has been added.");
                        } else {
                            request.setAttribute("ADD_TYPE_ERROR", "Fail to add new type.");
                        }
                    } else {
                        typeError.setErrorMessage("Fail to add new type .");
                        request.setAttribute("ADD_TYPE_ERROR", typeError);
                    }
                    break;
                case APARTMENT:
                    typeName = request.getParameter("typeName");
                    float area = Float.parseFloat(request.getParameter("area"));
                    buildingName = request.getParameter("buildingName");
                    int floor = Integer.parseInt(request.getParameter("floor"));
                    String apartmentStatus = request.getParameter("apartmentStatus");
                    check = true;

                    apBuildingDao = new ApartmentBuildingDAO();
                    apartmentDao = new ApartmentDAO();
                    ApartmentError apError = new ApartmentError();

                    ApartmentBuildingDTO apBuilding = apBuildingDao.getApartmentBuilding(buildingName);

                    if (!apBuilding.isStatus()) {
                        apError.setBuildingName("This building is full.");
                        check = false;
                    }

                    if (floor > apBuilding.getMaxFloor()) {
                        apError.setFloor("Invalid floor");
                        check = false;
                    }

                    if ((int) floor != floor) {
                        apError.setFloor("Floor is integer");
                        check = false;
                    }

                    if (check) {
                        ArrayList<ApartmentDTO> apList = apartmentDao.getApartmentListInABuilding(apBuilding.getBuildingID());
                        boolean checkAdd = true;
                        if (apList.size() == apBuilding.getMaxApartment()) {
                            apError.setBuildingName("Building is full");
                            apBuildingDao.updateApartmentBuildingStatus(apBuilding.getBuildingID(), !apBuilding.isStatus());
                        } else {
                            checkAdd = apartmentDao.addApartment(apartmentStatus, typeName, floor, buildingName, area);
                        }
                        if (checkAdd) {
                            apList = apartmentDao.getApartmentListInABuilding(apBuilding.getBuildingID());
                            if (apList.size() == apBuilding.getMaxApartment()) {
                                apBuildingDao.updateApartmentBuildingStatus(apBuilding.getBuildingID(), !apBuilding.isStatus());
                            }
                            request.setAttribute("ADD_APARTMENT_SUCCESS", "New apartment has been added.");
                        } else {
                            apError.setErrorMessage("Fail to add new apartment.");
                            request.setAttribute("ADD_APARTMENT_ERROR", apError);
                        }
                    } else {
                        apError.setErrorMessage("Fail to add new apartment .");
                        request.setAttribute("ADD_APARTMENT_ERROR", apError);
                    }

                    break;
                case BILLING_HISTORY:
                    userID = request.getParameter("userID");
                    apartmentID = request.getParameter("apartmentID");
                    float contractFee = 0;
                    float serviceFee = 0;
                    float electricityFee = 0;
                    float waterFee = 0;
                    float otherFee = 0;
                    float receivedValue = 0;
                    float change = 0;
                    if (request.getParameter("contractFee") != null) {
                        contractFee = Float.parseFloat(request.getParameter("contractFee"));
                    }
                    if (request.getParameter("serviceFee") != null) {
                        serviceFee = Float.parseFloat(request.getParameter("serviceFee"));
                    }
                    if (request.getParameter("electricityFee") != null) {
                        electricityFee = Float.parseFloat(request.getParameter("electricityFee"));
                    }
                    if (request.getParameter("waterFee") != null) {
                        waterFee = Float.parseFloat(request.getParameter("waterFee"));
                    }
                    if (request.getParameter("otherFee") != null && request.getParameter("otherFeeRadio") != null) {
                        otherFee = Float.parseFloat(request.getParameter("otherFee"));
                    }
                    if (request.getParameter("receivedValue") != null) {
                        receivedValue = Float.parseFloat(request.getParameter("receivedValue"));
                    }
                    if (request.getParameter("change") != null) {
                        change = Float.parseFloat(request.getParameter("change"));
                    }
                    check = true;

                    BillingHistoryError billError = new BillingHistoryError();
                    BillingHistoryDAO billDao = new BillingHistoryDAO();
                    monthlyFeeDao = new MonthlyFeeDAO();
                    userDao = new UserDAO();
                    apartmentDao = new ApartmentDAO();

                    if (userDao.getUserByID(userID) == null) {
                        billError.setUserID("Invalid userID");
                        check = false;
                    }
                    if (apartmentDao.getApartment(apartmentID) == null) {
                        billError.setApartmentID("Invalid apartmentID");
                        check = false;
                    }
                    if (monthlyFeeDao.getUserMonthlyFeeDetail(userID, apartmentID) == null) {
                        billError.setUserID("Invalid userID");
                        billError.setApartmentID("Invalid apartmentID");
                        check = false;
                    }
                    if (contractFee < 0) {
                        billError.setContractFee("Contract Fee must be >=0");
                        check = false;
                    } else if (contractFee == 0) {
                        contractFee = -1;
                    }
                    if (serviceFee < 0) {
                        billError.setServiceFee("Service Fee must be >=0");
                        check = false;
                    } else if (serviceFee == 0) {
                        serviceFee = -1;
                    }
                    if (waterFee < 0) {
                        billError.setWaterFee("Water Fee must be >=0");
                        check = false;
                    } else if (waterFee == 0) {
                        waterFee = -1;
                    }
                    if (electricityFee < 0) {
                        billError.setElectricityFee("Electricity Fee must be >=0");
                        check = false;
                    } else if (electricityFee == 0) {
                        electricityFee = -1;
                    }
                    if (otherFee < 0) {
                        billError.setOtherFee("Other Fee must be >=0");
                        check = false;
                    } else if (otherFee == 0) {
                        otherFee = -1;
                    }
                    if (receivedValue <= 0) {
                        billError.setReceivedValue("Received value is not enough");
                        check = false;
                    }
                    if (check) {
                        BillingHistoryDTO billingHistory = null;
                        if (otherFee > -1) {
                            billingHistory = new BillingHistoryDTO(0, null, userID, apartmentID, contractFee, serviceFee, waterFee, electricityFee, otherFee, receivedValue, change);
                            billDao.addBillingHistory(billingHistory);
                        } else {
                            billingHistory = new BillingHistoryDTO(0, null, userID, apartmentID, contractFee, serviceFee, waterFee, electricityFee, otherFee, receivedValue, change);
                            boolean checkAdd = monthlyFeeDao.updateMonthlyFee(userID, apartmentID, billingHistory);
                            if (checkAdd) {
                                billDao.addBillingHistory(billingHistory);
                                request.setAttribute("ADD_BILLING_SUCCESS", "New billing history has been added.");
                            } else {
                                billError.setErrorMessage("Fail to add new billing history");
                                request.setAttribute("ADD_BILLING_ERROR", billError);
                            }
                        }
                    } else {
                        billError.setErrorMessage("Fail to add new billing history");
                        request.setAttribute("ADD_BILLING_ERROR", billError);
                    }
                    break;

                default:
                    break;

            }
        } catch (Exception e) {
            log("Error at Add Controller : " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
