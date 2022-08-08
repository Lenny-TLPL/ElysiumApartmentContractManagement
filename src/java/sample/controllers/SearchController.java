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
import sample.DAO.ServiceDAO;
import sample.DAO.UserDAO;
import sample.DTO.ApartmentBuildingDTO;
import sample.DTO.ApartmentDTO;
import sample.DTO.ApartmentTypeDTO;
import sample.DTO.BillingHistoryDTO;
import sample.DTO.ContractDTO;
import sample.DTO.DistrictDTO;
import sample.DTO.MonthlyFeeDTO;
import sample.DTO.NotificationDTO;
import sample.DTO.PermissionDTO;
import sample.DTO.PrivateNotificationDTO;
import sample.DTO.ServiceDTO;
import sample.DTO.UserDTO;

/**
 *
 * @author Phi Long
 */
public class SearchController extends HttpServlet {

    private static final String ERROR = "error404.jsp";
    private static final String SUCCESS_ADMIN = "admin";
    private static final String SUCCESS_USER = "user";
    private static final String CUSTOMER = "Customer";
    private static final String RESIDENT = "Resident";
    private static final String EMPLOYEE = "Employee";
    private static final String HR_MANAGER = "HR Manager";
    private static final String BOARD_MANAGER = "Board Manager";
    private static final String CONTRACT = "Contract";
    private static final String SERVICE = "Service";
    private static final String NOTIFICATION = "Notification";
    private static final String APARTMENT = "Apartment";
    private static final String BILLING_HISTORY = "Billing History";
    private static final String MONTHLY_FEE = "MonthlyFee";
    private static final String PERMISSION = "Permission";
    private static final String NEWS="News";

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
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String roleName = session.getAttribute("LOGIN_USER_ROLE").toString();
            String type = request.getParameter("type");
            String search = request.getParameter("search");
            UserDAO dao = new UserDAO();
            UserDTO loginUser =(UserDTO) session.getAttribute("LOGIN_USER");
            ArrayList<UserDTO> list = null;
            switch (type) {
                case BOARD_MANAGER:
                    if (("Board Manager HR Manager Employee").contains(roleName)) {
                        dao = new UserDAO();
                        list = dao.getListUser(type, search);
                        request.setAttribute("USER_LIST", list);
                        url = SUCCESS_ADMIN + type.replaceAll(" ", "") + "Page.jsp";
                    } else if (("Customer Resident").contains(roleName)) {
                        url = SUCCESS_USER + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                case HR_MANAGER:
                    if (("Board Manager HR Manager Employee").contains(roleName)) {
                        dao = new UserDAO();
                        list = dao.getListUser(type, search);
                        request.setAttribute("USER_LIST", list);
                        url = SUCCESS_ADMIN + type.replaceAll(" ", "") + "Page.jsp";
                    } else if (("Customer Resident").contains(roleName)) {
                        url = SUCCESS_USER + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                case EMPLOYEE:
                    if (("Board Manager HR Manager Employee").contains(roleName)) {
                        dao = new UserDAO();
                        list = dao.getListUser(type, search);
                        request.setAttribute("USER_LIST", list);
                        url = SUCCESS_ADMIN + type.replaceAll(" ", "") + "Page.jsp";
                    } else if (("Customer Resident").contains(roleName)) {
                        url = SUCCESS_USER + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                case RESIDENT:
                    if (("Board Manager HR Manager Employee").contains(roleName)) {
                        dao = new UserDAO();
                        list = dao.getListUser(type, search);
                        request.setAttribute("USER_LIST", list);
                        url = SUCCESS_ADMIN + type.replaceAll(" ", "") + "Page.jsp";
                    } else if (("Customer Resident").contains(roleName)) {
                        url = SUCCESS_USER + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                case CUSTOMER:
                    if (("Board Manager HR Manager Employee").contains(roleName)) {
                        dao = new UserDAO();
                        list = dao.getListUser(type, search);
                        request.setAttribute("USER_LIST", list);
                        url = SUCCESS_ADMIN + type.replaceAll(" ", "") + "Page.jsp";
                    } else if (("Customer Resident").contains(roleName)) {
                        url = SUCCESS_USER + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                case SERVICE:
                    if (("Board Manager HR Manager Employee").contains(roleName)) {
                        ServiceDAO serviceDao = new ServiceDAO();
                        ArrayList<ServiceDTO> serviceList = serviceDao.getListService(search);
                        request.setAttribute("SERVICE_LIST", serviceList);
                        url = SUCCESS_ADMIN + type.replaceAll(" ", "") + "Page.jsp";
                    } else if (("Customer Resident").contains(roleName)) {
                        url = SUCCESS_USER + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                case CONTRACT:                    
                    if (("Board Manager HR Manager Employee").contains(roleName)) {
                        ContractDAO contractDao = new ContractDAO();
                        ArrayList<ContractDTO> contractList = contractDao.getListContract(search);
                        request.setAttribute("CONTRACT_LIST", contractList);
                        url = SUCCESS_ADMIN + type.replaceAll(" ", "") + "Page.jsp";
                    } else if (("Customer Resident").contains(roleName)) {
                        ContractDAO contractDao = new ContractDAO();
                        ArrayList<ContractDTO> contractList = contractDao.getListContractUser(search, loginUser.getUserID());
                        request.setAttribute("CONTRACT_LIST", contractList);
                        url = SUCCESS_USER + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                case NOTIFICATION:
                    if (("Board Manager HR Manager Employee").contains(roleName)) {
                        NotificationDAO notiDao = new NotificationDAO();
                        PrivateNotificationDAO privateNotiDao = new PrivateNotificationDAO();
                        ArrayList<NotificationDTO> notiList = notiDao.getListNotification(search);
                        ArrayList<PrivateNotificationDTO> privateNotiList = privateNotiDao.getListPrivateNotification(search);
                        request.setAttribute("NOTIFICATION_LIST", notiList);
                        request.setAttribute("PRIVATE_NOTIFICATION_LIST", privateNotiList);
                        url = SUCCESS_ADMIN + type.replaceAll(" ", "") + "Page.jsp";
                    } else if (("Customer Resident").contains(roleName)) {
                        PrivateNotificationDAO privateNotiDao = new PrivateNotificationDAO();
                        ArrayList<PrivateNotificationDTO> privateNotiList = privateNotiDao.getListPrivateNotification(loginUser.getUserID(), search);                 
                        request.setAttribute("PRIVATE_NOTIFICATION_LIST", privateNotiList);
                        url = SUCCESS_USER + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                case NEWS:
                    NotificationDAO notiDao = new NotificationDAO();
                        ArrayList<NotificationDTO> notiList = notiDao.getListNotificationUser(search);                    
                        request.setAttribute("NOTIFICATION_LIST", notiList);
                        url = SUCCESS_USER + type.replaceAll(" ", "") + "Page.jsp";
                    break;
                case PERMISSION:
                    if (("Board Manager HR Manager Employee").contains(roleName)) {
                        PermissionDAO permissionDao = new PermissionDAO();
                        ArrayList<PermissionDTO> permissionList = permissionDao.getListPermission(search);
                        request.setAttribute("PERMISSION_LIST", permissionList);
                        url = SUCCESS_ADMIN + type.replaceAll(" ", "") + "Page.jsp";
                    } else if (("Customer Resident").contains(roleName)) {
                        url = SUCCESS_USER + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                case BILLING_HISTORY:
                    if (("Board Manager HR Manager Employee").contains(roleName)) {
                        BillingHistoryDAO billDao = new BillingHistoryDAO();
                        ArrayList<BillingHistoryDTO> billList = billDao.getListBilling(search);
                        request.setAttribute("BILLING_HISTORY_LIST", billList);
                        url = SUCCESS_ADMIN + type.replaceAll(" ", "") + "Page.jsp";
                    } else if (("Customer Resident").contains(roleName)) {
                        BillingHistoryDAO billDao = new BillingHistoryDAO();
                        ArrayList<BillingHistoryDTO> billList = billDao.getListBilling(loginUser.getUserID(),search);
                        request.setAttribute("BILLING_HISTORY_LIST", billList);
                        url = SUCCESS_USER + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                case APARTMENT:
                    if (("Board Manager HR Manager Employee").contains(roleName)) {
                        ApartmentBuildingDAO apBuildingDao = new ApartmentBuildingDAO();
                        ApartmentTypeDAO apTypeDao = new ApartmentTypeDAO();
                        DistrictDAO districtDao = new DistrictDAO();
                        ApartmentDAO apDao = new ApartmentDAO();
                        ArrayList<ApartmentBuildingDTO> buildingList = apBuildingDao.getApartmentBuildingList(search);
                        ArrayList<ApartmentTypeDTO> typeList = apTypeDao.getApartmentTypeList(search);
                        ArrayList<DistrictDTO> districtList = districtDao.getDistrictList(search);
                        ArrayList<ApartmentDTO> apartmentList = apDao.getApartmentList(search);
                        request.setAttribute("APARTMENT_BUILDING_LIST", buildingList);
                        request.setAttribute("APARTMENT_TYPE_LIST", typeList);
                        request.setAttribute("DISTRICT_LIST", districtList);
                        request.setAttribute("APARTMENT_LIST", apartmentList);
                        url = SUCCESS_ADMIN + type.replaceAll(" ", "") + "Page.jsp";
                    } else if (("Customer Resident").contains(roleName)) {
                        ApartmentDAO apDao = new ApartmentDAO();
                        ArrayList<ApartmentDTO> apartmentList = apDao.getUserApartmentList(search,loginUser.getUserID());
                        request.setAttribute("APARTMENT_LIST", apartmentList);
                        url = SUCCESS_USER + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                case MONTHLY_FEE:
                    if (("Board Manager HR Manager Employee").contains(roleName)) {
                        MonthlyFeeDAO monthlyFeeDao = new MonthlyFeeDAO();
                        ArrayList<MonthlyFeeDTO> monthlyFeeList = monthlyFeeDao.getMonthlyFeeList(search);
                        request.setAttribute("MONTHLY_FEE_LIST", monthlyFeeList);
                        url = SUCCESS_ADMIN + type.replaceAll(" ", "") + "Page.jsp";
                    } else if (("Customer Resident").contains(roleName)) {
                        MonthlyFeeDAO monthlyFeeDao = new MonthlyFeeDAO();
                        ArrayList<MonthlyFeeDTO> monthlyFeeList = monthlyFeeDao.getUserMonthlyFeeList(loginUser.getUserID(),search);
                        request.setAttribute("MONTHLY_FEE_LIST", monthlyFeeList);
                        url = SUCCESS_USER + type.replaceAll(" ", "") + "Page.jsp";
                    }
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            log("Error at SearchController:" + e.toString());
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
