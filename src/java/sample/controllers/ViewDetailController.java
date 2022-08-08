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
import javax.servlet.http.HttpSession;
import sample.DAO.ContractDAO;
import sample.DAO.NotificationDAO;
import sample.DAO.PermissionDAO;
import sample.DAO.PrivateNotificationDAO;
import sample.DAO.RoleDAO;
import sample.DAO.ServiceDAO;
import sample.DAO.UserDAO;
import sample.DAO.UserPermissionDAO;
import sample.DTO.ContractDTO;
import sample.DTO.NotificationDTO;
import sample.DTO.PermissionDTO;
import sample.DTO.PrivateNotificationDTO;
import sample.DTO.ServiceDTO;
import sample.DTO.UserDTO;

/**
 *
 * @author Phi Long
 */
public class ViewDetailController extends HttpServlet {

    private static final String ERROR = "error404.jsp";

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
    private static final String MONTHLY_FEE = "MonthlyFee";
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
                case HR_MANAGER:
                    userID = request.getParameter("userID");
                    roleID = Integer.parseInt(request.getParameter("roleID"));

                    userDao = new UserDAO();
                    permissionDao = new PermissionDAO();
                    roleDao = new RoleDAO();

                    user = userDao.getUserDetailByUserIDAndRoleID(userID, roleID);
                    userPermission = userDao.getListLoginUserPermission(userID);
                    permissionList = permissionDao.getListPermissionWithPriority("");
                    request.setAttribute("USER_DETAIL", user);
                    request.setAttribute("ROLE_NAME", roleDao.getUserRole(roleID));
                    request.setAttribute("USER_PERMISSION_LIST", userPermission);
                    request.setAttribute("PERMISSION_LIST", permissionList);
                    url = SUCCESS;
                    break;
                case EMPLOYEE:
                    userID = request.getParameter("userID");
                    roleID = Integer.parseInt(request.getParameter("roleID"));

                    userDao = new UserDAO();
                    permissionDao = new PermissionDAO();
                    roleDao = new RoleDAO();

                    user = userDao.getUserDetailByUserIDAndRoleID(userID, roleID);
                    userPermission = userDao.getListLoginUserPermission(userID);
                    permissionList = permissionDao.getListPermissionWithPriority("");
                    request.setAttribute("USER_DETAIL", user);
                    request.setAttribute("ROLE_NAME", roleDao.getUserRole(roleID));
                    request.setAttribute("USER_PERMISSION_LIST", userPermission);
                    request.setAttribute("PERMISSION_LIST", permissionList);
                    url = SUCCESS;
                    break;
                case RESIDENT:
                    userID = request.getParameter("userID");
                    roleID = Integer.parseInt(request.getParameter("roleID"));

                    userDao = new UserDAO();
                    permissionDao = new PermissionDAO();
                    roleDao = new RoleDAO();

                    user = userDao.getUserDetailByUserIDAndRoleID(userID, roleID);
                    userPermission = userDao.getListLoginUserPermission(userID);
                    permissionList = permissionDao.getListPermissionWithPriority("");
                    request.setAttribute("USER_DETAIL", user);
                    request.setAttribute("ROLE_NAME", roleDao.getUserRole(roleID));
                    request.setAttribute("USER_PERMISSION_LIST", userPermission);
                    request.setAttribute("PERMISSION_LIST", permissionList);
                    url = SUCCESS;
                    break;
                case CUSTOMER:
                    userID = request.getParameter("userID");
                    roleID = Integer.parseInt(request.getParameter("roleID"));

                    userDao = new UserDAO();
                    permissionDao = new PermissionDAO();
                    roleDao = new RoleDAO();

                    user = userDao.getUserDetailByUserIDAndRoleID(userID, roleID);
                    userPermission = userDao.getListLoginUserPermission(userID);
                    permissionList = permissionDao.getListPermissionWithPriority("");
                    request.setAttribute("USER_DETAIL", user);
                    request.setAttribute("ROLE_NAME", roleDao.getUserRole(roleID));
                    request.setAttribute("USER_PERMISSION_LIST", userPermission);
                    request.setAttribute("PERMISSION_LIST", permissionList);
                    url = SUCCESS;
                    break;
                case NOTIFICATION:
                    int notiID = Integer.parseInt(request.getParameter("notiID"));

                    NotificationDAO notificationDao = new NotificationDAO();
                    NotificationDTO noti = notificationDao.getNotificationByID(notiID);
                    request.setAttribute("NOTIFICATION_DETAIL", noti);
                    url = SUCCESS;
                    break;

                case PRIVATE_NOTIFICATION:
                    notiID =Integer.parseInt(request.getParameter("notiID")) ;
                    HttpSession session = request.getSession();
                    String roleName = (String) session.getAttribute("LOGIN_USER_ROLE");
                    
                    if (("Resident Customer").contains(roleName)) {
                        notiID = Integer.parseInt(request.getParameter("notiID"));
                    }

                    PrivateNotificationDAO privateNotificationDao = new PrivateNotificationDAO();
                    PrivateNotificationDTO privateNoti = privateNotificationDao.getPrivateNotificationByID(notiID);
                    privateNoti.setStatus(true);
                    privateNotificationDao.updatePrivateNotificationUser(privateNoti);
                    request.setAttribute("PRIVATE_NOTIFICATION_DETAIL", privateNoti);
                    url = SUCCESS;
                    break;

                case SERVICE:
                    int serviceID = Integer.parseInt(request.getParameter("serviceID"));

                    ServiceDAO serviceDao = new ServiceDAO();
                    ServiceDTO service = serviceDao.getServiceByID(serviceID);
                    request.setAttribute("SERVICE_DETAIL", service);
                    url = SUCCESS;
                    break;
                case CONTRACT:
                    int contractID = Integer.parseInt(request.getParameter("contractID"));

                    ContractDAO contractDao = new ContractDAO();
                    ContractDTO contract = contractDao.getContractDetail(contractID);
                    String contractImage = contractDao.getContractImage(contractID);
                    request.setAttribute("CONTRACT_DETAIL", contract);
                    request.setAttribute("CONTRACT_IMAGE", contractImage);
                    url = SUCCESS;
                    break;
                case PERMISSION:
                    int permissionID = Integer.parseInt(request.getParameter("permissionID"));

                    UserPermissionDAO uPermissionDao = new UserPermissionDAO();
                    ArrayList<String> userList = uPermissionDao.getListUsersHaveGivenPermission(permissionID);

                    request.setAttribute("PERMISSION_DETAIL_LIST", userList);
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
