/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.DAO.PermissionDAO;
import sample.DAO.RoleDAO;
import sample.DAO.UserDAO;
import sample.DTO.PermissionDTO;
import sample.DTO.UserDTO;

/**
 *
 * @author Phi Long
 */
public class ViewDetailController extends HttpServlet {

    private static final String ERROR = "error404.jsp";

    private static final String CUSTOMER = "Customer";
    private static final String RESIDENT = "Resident";
    private static final String EMPLOYEE = "Empoyee";
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
        try {
            String SUCCESS = request.getParameter("redirect");
            String type = request.getParameter("type");
            switch (type) {
                case BOARD_MANAGER:
                    String userID = request.getParameter("userID");
                    int roleID = Integer.parseInt(request.getParameter("roleID"));

                    UserDAO userDao = new UserDAO();
                    PermissionDAO permissionDao = new PermissionDAO();
                    RoleDAO roleDao = new RoleDAO();

                    UserDTO user = userDao.getUserDetailByUserIDAndRoleID(userID, roleID);
                    ArrayList<String> userPermission = userDao.getListLoginUserPermission(userID);
                    ArrayList<PermissionDTO> permissionList = permissionDao.getListPermissionWithPriority("");
                    request.setAttribute("USER_DETAIL", user);
                    request.setAttribute("ROLE_NAME", roleDao.getUserRole(roleID));
                    request.setAttribute("USER_PERMISSION_LIST", userPermission);
                    request.setAttribute("PERMISSION_LIST", permissionList);
                    url = SUCCESS;
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            log("Error at ViewDetailController:" + e.toString());
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
