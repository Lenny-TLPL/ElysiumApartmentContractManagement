/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.DAO.RoleDAO;
import sample.DAO.UserDAO;
import sample.DTO.UserDTO;
import sample.DTO.UserError;
import sample.utils.DateUtils;

/**
 *
 * @author Phi Long
 */
public class AddController extends HttpServlet {

    private static final String ERROR = "adminAddUserPage.jsp";
    private static final String SUCCESS = "adminAddUserPage.jsp";
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
        String url = ERROR;
        String type = request.getParameter("type");
        try {
            switch (type) {
                case HR_MANAGER:
                case EMPLOYEE:
                case CUSTOMER:
                    String fullName = request.getParameter("fullName");
                    String gender = request.getParameter("gender");
                    String email = request.getParameter("email");
                    String phone = request.getParameter("phone");
                    String address = request.getParameter("address");
                    String citizenID = request.getParameter("citizenID");                  
                    Date birthday =new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
                    String password = request.getParameter("password");
                    String confirmPassword = request.getParameter("confirmPassword");
                    boolean check = true;
                    String phoneRegex = "^\\d{11}$";

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
                    if (!(citizenID.length()==12)) {
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
                        if (checkAdd) {
                            url = SUCCESS;
                            request.setAttribute("ADD_USER_SUCCESS", "New customer has been added.");
                        }else {
                            userError.setErrorMessage("Fail to add new user.");
                            request.setAttribute("ADD_USER_ERROR", userError);
                        }
                    } else {
                        userError.setErrorMessage("Fail to add new user.");
                        request.setAttribute("ADD_USER_ERROR", userError);
                    }
                    break;
                case RESIDENT:
                    fullName = request.getParameter("fullName");
                    gender = request.getParameter("gender");
                    email = request.getParameter("email");
                    phone = request.getParameter("phone");
                    address = request.getParameter("address");
                    citizenID = request.getParameter("citizenID");                  
                    birthday =new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
                    password = request.getParameter("password");
                    confirmPassword = request.getParameter("confirmPassword");
                    check = true;
                    phoneRegex = "^\\d{11}$";

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
                    if (!(citizenID.length()==12)) {
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
                        if (checkAdd) {
                            url = SUCCESS;
                            request.setAttribute("ADD_USER_SUCCESS", "New customer has been added.");
                        }else {
                            userError.setErrorMessage("Fail to add new user.");
                            request.setAttribute("ADD_USER_ERROR", userError);
                        }
                    } else {
                        userError.setErrorMessage("Fail to add new user.");
                        request.setAttribute("ADD_USER_ERROR", userError);
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
