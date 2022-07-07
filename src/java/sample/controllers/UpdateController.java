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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.DAO.PermissionDAO;
import sample.DAO.RoleDAO;
import sample.DAO.UserDAO;
import sample.DAO.UserPermissionDAO;
import sample.DTO.UserDTO;
import sample.DTO.UserError;
import sample.utils.DateUtils;

/**
 *
 * @author Phi Long
 */
public class UpdateController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
    private static final String USER_DEBT = "User Debt";
    private static final String PERMISSION = "Permission";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String url = null;
        String type = request.getParameter("type");
        String redirect = request.getParameter("redirect");
        if (redirect != null) {
            url = redirect;
        }
//        else {
//            url = "ViewController?type="+request.getParameter("roleName");
////            if (("Board Manager HR Manager Employee Customer Resident").contains(type)) {
////                url = "adminUserDetailPage.jsp";
////            } else {
////                url = "admin" + type.replaceAll(" ", "") + "DetailPage.jsp";
////            }
//        }
        try {
            switch(type){
                case BOARD_MANAGER:
                    String userID = request.getParameter("userID");
                    String fullName = request.getParameter("fullName");
                    String gender = request.getParameter("gender");
                    String email = request.getParameter("email");
                    String phone = request.getParameter("phone");
                    String address = request.getParameter("address");
                    String citizenID = request.getParameter("citizenID");
                    Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
                    Boolean status = Boolean.parseBoolean(request.getParameter("status"));
                    String roleName = request.getParameter("roleName");
                    String[] permissions = request.getParameterValues("permissions");
                    boolean check = true;
                    String phoneRegex = "^\\d{11}$";

                    UserPermissionDAO userPermissionDao = new UserPermissionDAO();
                    PermissionDAO permissionDao = new PermissionDAO();
                    UserDAO userDao = new UserDAO();
                    RoleDAO roleDao = new RoleDAO();
                    UserError userError = new UserError();
                    if (fullName.length() > 60 || fullName.length() < 4) {
                        userError.setFullName("FullName needs to be between 4 and 60 characters.");
                        check = false;
                    }
                    if (userDao.checkDuplicateAdminV2(citizenID, userID)) {
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

                    if (!phone.matches(phoneRegex)) {
                        userError.setPhone("Invalid phone number");
                        check = false;
                    }
                    if(roleName == null){
                        userError.setRoleName("Invalid roleName");
                        check =false;
                    }else if(("HR Manager Employee").contains(roleName)){
                        ArrayList<Integer> permissionListWithPriority = permissionDao.getListPermissionIDWithPriority(roleName);
                        for (String permission : permissions) {
                            if(!permissionListWithPriority.contains(Integer.parseInt(permission))){
                                request.setAttribute("UPDATE_PERMISSION_ERROR", "Invalid permission for this role.");
                                check=false;
                                break;
                            }
                        }                   
                    }
                    
                    if (check) {
                        boolean checkAdd = userDao.updateUser(new UserDTO(userID, fullName, email, phone, address, birthday, citizenID, gender, null, null, status, roleDao.getUserRoleID(roleName)));
                        userPermissionDao.deleteUserPermission(userID);
                        for (String permisson : permissions) {
                            userPermissionDao.addUser(userID, Integer.parseInt(permisson));
                        }
                        if (checkAdd) {
                            request.setAttribute("UPDATE_USER_SUCCESS", "Update successfully.");
                        } else {
                            userError.setErrorMessage("Fail to update.");
                            request.setAttribute("UPDATE_USER_ERROR", userError);
                        }
                    } else {
                        userError.setErrorMessage("Fail to update.");
                        request.setAttribute("UPDATE_USER_ERROR", userError);
                    }
                    url = "ViewDetailController?roleID="+userDao.getUserByID(userID).getRoleID()+"&type="+roleDao.getUserRole(userDao.getUserByID(userID).getRoleID())+"&redirect=adminUserDetailPage.jsp";
                    break;
                default:
                    break;
            }
        }  catch (Exception e) {
            log("Error at UpdateController:" + e.toString());
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
