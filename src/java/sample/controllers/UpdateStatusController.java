/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.DAO.ApartmentBuildingDAO;
import sample.DAO.ApartmentDAO;
import sample.DAO.NotificationDAO;
import sample.DAO.PermissionDAO;
import sample.DAO.ServiceDAO;
import sample.DAO.UserDAO;
import sample.DTO.UserDTO;

/**
 *
 * @author Phi Long
 */
public class UpdateStatusController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "error404.jsp";
    private static final String CUSTOMER = "Customer";
    private static final String RESIDENT = "Resident";
    private static final String EMPLOYEE = "Employee";
    private static final String HR_MANAGER = "HR Manager";
    private static final String BOARD_MANAGER = "Board Manager";
    private static final String CONTRACT = "Contract";
    private static final String SERVICE = "Service";
    private static final String NOTIFICATION = "Notification";
    private static final String APARTMENT_TYPE = "Apartment Type";
    private static final String APARTMENT = "Apartment";
    private static final String APARTMENT_BUILDING = "Apartment Building";
    private static final String MONTHLY_FEE = "MonthlyFee";
    private static final String PERMISSION = "Permission";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String type = request.getParameter("type");
        String url = ERROR;
        HttpSession session = request.getSession();
        UserDTO loginUser =(UserDTO) session.getAttribute("LOGIN_USER");
//        if (("Board Manager HR Manager Employee Customer Resident").contains(type)) {
//            url = "admin" + type.replaceAll(" ", "") + "Page.jsp";
//        }
        try {
            switch (type) {
                case BOARD_MANAGER:
                    String userID = request.getParameter("userID");
                    Boolean status = Boolean.parseBoolean(request.getParameter("status"));
                    UserDAO userDao = new UserDAO();
                    Boolean checkUpdate = userDao.updateUserStatus(userID, !status);
                    if(userID.equals( loginUser.getUserID())){
                        response.sendRedirect("login.jsp");
                    }
                    if (checkUpdate) {
                        url = "admin" + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                case HR_MANAGER:
                    userID = request.getParameter("userID");
                    status = Boolean.parseBoolean(request.getParameter("status"));
                    userDao = new UserDAO();
                    checkUpdate = userDao.updateUserStatus(userID, !status);
                    if(userID.equals( loginUser.getUserID())){
                        response.sendRedirect("login.jsp");
                    }
                    if (checkUpdate) {
                        url = "admin" + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                case EMPLOYEE:
                    userID = request.getParameter("userID");
                    status = Boolean.parseBoolean(request.getParameter("status"));
                    userDao = new UserDAO();
                    checkUpdate = userDao.updateUserStatus(userID, !status);
                    if(userID.equals( loginUser.getUserID())){
                        response.sendRedirect("login.jsp");
                    }
                    if (checkUpdate) {
                        url = "admin" + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                case CUSTOMER:
                    userID = request.getParameter("userID");
                    status = Boolean.parseBoolean(request.getParameter("status"));
                    userDao = new UserDAO();
                    checkUpdate = userDao.updateUserStatus(userID, !status);
                    if (checkUpdate) {
                        url = "admin" + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                case RESIDENT:
                    userID = request.getParameter("userID");
                    status = Boolean.parseBoolean(request.getParameter("status"));
                    userDao = new UserDAO();
                    checkUpdate = userDao.updateUserStatus(userID, !status);
                    if (checkUpdate) {
                        url = "admin" + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                case SERVICE:
                    int serviceID = Integer.parseInt(request.getParameter("serviceID"));
                    status = Boolean.parseBoolean(request.getParameter("status"));
                    ServiceDAO serviceDao = new ServiceDAO();
                    checkUpdate = serviceDao.updateServiceStatus(serviceID, !status);
                    if(checkUpdate){
                        url = "admin" + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                case APARTMENT_BUILDING:
                    int buildingID = Integer.parseInt(request.getParameter("buildingID"));
                    status = Boolean.parseBoolean(request.getParameter("status"));
                    ApartmentBuildingDAO apBuildingDao = new ApartmentBuildingDAO();
                    checkUpdate = apBuildingDao.updateApartmentBuildingStatus(buildingID, !status);
                    if(checkUpdate){
                        url = "adminApartmentPage.jsp";
                    }
                    break;
                case APARTMENT:
                    String apartmentID = request.getParameter("apartmentID");
                    String statusString = request.getParameter("apartmentStatus");
                    if(statusString.equals("available")){
                        statusString = "maintenance";
                    }else if(statusString.equals("maintenance")){
                        statusString = "available";
                    }
                    ApartmentDAO apartmentDao = new ApartmentDAO();
                    checkUpdate = apartmentDao.updateApartmentStatus(apartmentID, statusString);
                    if(checkUpdate){
                        url = "admin" + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                case NOTIFICATION:
                    int notificationID = Integer.parseInt(request.getParameter("notiID"));
                    status = Boolean.parseBoolean(request.getParameter("notiStatus"));
                    NotificationDAO notiDao = new NotificationDAO();
                    checkUpdate = notiDao.updateNotificationStatus(notificationID,!status);
                    if(checkUpdate){
                        url = "admin" + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                case PERMISSION:
                    int permissionID = Integer.parseInt(request.getParameter("permissionID"));
                    status = Boolean.parseBoolean(request.getParameter("status"));
                    PermissionDAO permissionDao = new PermissionDAO();
                    checkUpdate = permissionDao.updatePermissionStatus(permissionID, !status);
                    if(checkUpdate){
                        url = "admin" + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            log("Error at UpdateStatusController:" + e.toString());
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
