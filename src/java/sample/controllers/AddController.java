/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import sample.DAO.ApartmentDAO;
import sample.DAO.ContractDAO;
import sample.DAO.RoleDAO;
import sample.DAO.ServiceDAO;
import sample.DAO.UserDAO;
import sample.DAO.UserPermissionDAO;
import sample.DTO.ContractDTO;
import sample.DTO.ContractError;
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
    private static final String EMPLOYEE = "Empoyee";
    private static final String HR_MANAGER = "HR Manager";
    private static final String BOARD_MANAGER = "Board Manager";
    private static final String CONTRACT = "Contract";
    private static final String SERVICE = "Service";
    private static final String NOTIFICATION = "Notification";
    private static final String PRIVATE_NOTIFICATION = "Private Notification";
    private static final String APARTMENT = "Apartment";
    private static final String APARTMENT_BUILDING = "Apartment Building";
    private static final String DISTRICT = "District";
    private static final String BILLING_HISTORY = "Billing History";
    private static final String USER_DEBT = "User Debt";
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String type = request.getParameter("type");
        String url = null;
        try {
            switch (type) {
                case BOARD_MANAGER:
                    url = "adminAddUserPage.jsp";
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

                    UserPermissionDAO userPermissionDao = new UserPermissionDAO();
                    UserDAO userDao = new UserDAO();
                    RoleDAO roleDao = new RoleDAO();
                    UserError userError = new UserError();
                    if (fullName.length() > 60 || fullName.length() < 4) {
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
                    if (!DateUtils.checkValidDate(birthday)) {
                        userError.setBirthday("Invalid birthday");
                        check = false;
                    }
                    if (!confirmPassword.equals(password)) {
                        userError.setPassword("Invalid confirm");
                        check = false;
                    }
                    if (!phone.matches(phoneRegex)) {
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
                    url = "adminAddUserPage.jsp";
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

                    userPermissionDao = new UserPermissionDAO();
                    userDao = new UserDAO();
                    roleDao = new RoleDAO();
                    userError = new UserError();
                    if (fullName.length() > 60 || fullName.length() < 4) {
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
                    if (!DateUtils.checkValidDate(birthday)) {
                        userError.setBirthday("Invalid birthday");
                        check = false;
                    }
                    if (!confirmPassword.equals(password)) {
                        userError.setPassword("Invalid confirm");
                        check = false;
                    }
                    if (!phone.matches(phoneRegex)) {
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
                case EMPLOYEE:
                    url = "adminAddUserPage.jsp";
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

                    userPermissionDao = new UserPermissionDAO();
                    userDao = new UserDAO();
                    roleDao = new RoleDAO();
                    userError = new UserError();
                    if (fullName.length() > 60 || fullName.length() < 4) {
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
                    if (!DateUtils.checkValidDate(birthday)) {
                        userError.setBirthday("Invalid birthday");
                        check = false;
                    }
                    if (!confirmPassword.equals(password)) {
                        userError.setPassword("Invalid confirm");
                        check = false;
                    }
                    if (!phone.matches(phoneRegex)) {
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
                case CUSTOMER:
                    url = "adminAddUserPage.jsp";
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
//                    Blob contractImage = imageFilePart.getInputStream();
                    Date expiryDate = null;
                    int monthsOfDebt = 0;
                    if (contractType.equals("amortization")) {
                        expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("expiryDate"));
                        monthsOfDebt = Integer.parseInt(request.getParameter("monthsOfDebt"));
                    } else if (contractType.equals("leasing")) {
                        expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("expiryDate"));
                    }
                    check = true;
                    phoneRegex = "^\\d{11}$";

                    ContractDAO contractDao = new ContractDAO();
                    ApartmentDAO apartmentDao = new ApartmentDAO();
                    userDao = new UserDAO();
                    roleDao = new RoleDAO();
                    userError = new UserError();
                    ContractError contractError = new ContractError();
                    if (fullName.length() > 60 || fullName.length() < 4) {
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
                    if (!DateUtils.checkValidDate(birthday)) {
                        userError.setBirthday("Invalid birthday");
                        check = false;
                    }
                    if (!confirmPassword.equals(password)) {
                        userError.setPassword("Invalid confirm");
                        check = false;
                    }
                    if (!phone.matches(phoneRegex)) {
                        userError.setPhone("Invalid phone number");
                        check = false;
                    }
                    if (DateUtils.checkValidDate(dateSign) || !DateUtils.compareTwoDate(dateSign, birthday) || DateUtils.compareTwoDate(dateSign, expiryDate)) {
                        contractError.setDateSign("Invalid dateSign");
                        check = false;
                    }
                    if (("amortization leasing").contains(contractType)) {
                        if (DateUtils.checkValidDate(expiryDate) || !DateUtils.compareTwoDate(expiryDate, birthday) || DateUtils.compareTwoDate(dateSign, expiryDate)) {
                            contractError.setDateSign("Invalid expiryDate");
                            check = false;
                        }
                    }

                    if (contractImageFilePart == null) {
                        contractError.setContractImage("Invalid image");
                        check = false;
                    }
                    if (apartmentDao.getApartment(apartmentID, "available") == null) {
                        contractError.setApartmentID("Invalid apartmentID");
                        check = false;
                    }
                    if (contractType.equals("amortization") && DateUtils.subtractTwoDay(dateSign, expiryDate) != monthsOfDebt) {
                        contractError.setExpiryDate("Invalid expiryDate");
                        check = false;
                    }
                    if (check) {
                        boolean checkAdd = userDao.addUser(new UserDTO("", fullName, email, phone, address, birthday, citizenID, gender, password, null, true, roleDao.getUserRoleID(type)));
                        boolean checkAddContract = contractDao.addContract(new ContractDTO(0, dateSign, null, userDao.getUserIDByCitizenID(type, citizenID), apartmentID, 0, expiryDate, monthsOfDebt, 0, contractType, true), contractImageFilePart);
                        if (checkAdd && checkAddContract) {
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
                    url = "adminAddUserPage.jsp";
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
                    expiryDate = null;
                    monthsOfDebt = 0;
                    if (contractType.equals("amortization")) {
                        expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("expiryDate"));
                        monthsOfDebt = Integer.parseInt(request.getParameter("monthsOfDebt"));
                    } else if (contractType.equals("leasing")) {
                        expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("expiryDate"));
                    }
                    check = true;
                    phoneRegex = "^\\d{11}$";

                    contractDao = new ContractDAO();
                    apartmentDao = new ApartmentDAO();
                    userDao = new UserDAO();
                    roleDao = new RoleDAO();
                    userError = new UserError();
                    contractError = new ContractError();
                    if (fullName.length() > 60 || fullName.length() < 4) {
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
                    if (!DateUtils.checkValidDate(birthday)) {
                        userError.setBirthday("Invalid birthday");
                        check = false;
                    }
                    if (!confirmPassword.equals(password)) {
                        userError.setPassword("Invalid confirm");
                        check = false;
                    }
                    if (!phone.matches(phoneRegex)) {
                        userError.setPhone("Invalid phone number");
                        check = false;
                    }
                    if (DateUtils.checkValidDate(dateSign) || !DateUtils.compareTwoDate(dateSign, birthday) || DateUtils.compareTwoDate(dateSign, expiryDate)) {
                        contractError.setDateSign("Invalid dateSign");
                        check = false;
                    }
                    if (("amortization leasing").contains(contractType)) {
                        if (DateUtils.checkValidDate(expiryDate) || !DateUtils.compareTwoDate(expiryDate, birthday) || DateUtils.compareTwoDate(dateSign, expiryDate)) {
                            contractError.setDateSign("Invalid expiryDate");
                            check = false;
                        }
                    }

                    if (contractImageFilePart == null) {
                        contractError.setContractImage("Invalid image");
                        check = false;
                    }
                    if (apartmentDao.getApartment(apartmentID, "available") == null) {
                        contractError.setApartmentID("Invalid apartmentID");
                        check = false;
                    }
                    if (contractType.equals("amortization") && DateUtils.subtractTwoDay(dateSign, expiryDate) != monthsOfDebt) {
                        contractError.setExpiryDate("Invalid expiryDate");
                        check = false;
                    }
                    if (check) {
                        boolean checkAdd = userDao.addUser(new UserDTO("", fullName, email, phone, address, birthday, citizenID, gender, password, null, true, roleDao.getUserRoleID(type)));
                        boolean checkAddContract = contractDao.addContract(new ContractDTO(0, dateSign, null, userDao.getUserIDByCitizenID(type, citizenID), apartmentID, 0, expiryDate, monthsOfDebt, 0, contractType, true), contractImageFilePart);
                        if (checkAdd && checkAddContract) {
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
                    url = "adminAddServicePage.jsp";
                    String serviceName = request.getParameter("serviceName");
                    String description = request.getParameter("description");
                    float price = Float.parseFloat(request.getParameter("price"));
                    boolean status = Boolean.parseBoolean(request.getParameter("status"));
                    check = true;
                    
                    ServiceDAO serviceDao = new ServiceDAO();
                    ServiceDTO service = null;
                    ServiceError serviceError = new ServiceError();

                    if (serviceName.length() > 60 || serviceName.length() < 4) {
                        serviceError.setServiceName("Name must be from 4 to 60 chars");
                        check = false;
                    }
                    if ( serviceDao.getServiceByName(serviceName)!= null) {
                        serviceError.setServiceName("Duplicate service name");
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
                        serviceError.setErrorMessage("Fail to add new resident");
                        request.setAttribute("ADD_SERVICE_ERROR", serviceError);
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
