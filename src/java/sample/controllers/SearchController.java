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
import sample.DAO.ContractDAO;
import sample.DAO.ServiceDAO;
import sample.DAO.UserDAO;
import sample.DTO.ContractDTO;
import sample.DTO.ServiceDTO;
import sample.DTO.UserDTO;

/**
 *
 * @author Phi Long
 */
public class SearchController extends HttpServlet {
    private static final String ERROR = "add.jsp";
    private static final String SUCCESS= "admin";
    private static final String CUSTOMER = "Customer";
    private static final String RESIDENT = "Resident";
    private static final String EMPLOYEE = "Employee";
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
            String type = request.getParameter("type");
            String search = request.getParameter("search");
            UserDAO dao = new UserDAO();
            ArrayList<UserDTO> list = null;
            switch(type){
                case BOARD_MANAGER:
                    dao = new UserDAO();
                    list = dao.getListUser(type,search);
                    request.setAttribute("USER_LIST", list);
                    url = SUCCESS+type.replaceAll(" ", "")+"Page.jsp";
                    break;
                case HR_MANAGER:
                    dao = new UserDAO();
                    list = dao.getListUser(type,search);
                    request.setAttribute("USER_LIST", list);
                    url = SUCCESS+type.replaceAll(" ", "")+"Page.jsp";
                    break;
                case EMPLOYEE:
                    dao = new UserDAO();
                    list = dao.getListUser(type,search);
                    request.setAttribute("USER_LIST", list);
                    url = SUCCESS+type.replaceAll(" ", "")+"Page.jsp";
                    break;
                case RESIDENT:
                    dao = new UserDAO();
                    list = dao.getListUser(type,search);
                    request.setAttribute("USER_LIST", list);
                    url = SUCCESS+type.replaceAll(" ", "")+"Page.jsp";
                    break;
                case CUSTOMER:
                    dao = new UserDAO();
                    list = dao.getListUser(type,search);
                    request.setAttribute("USER_LIST", list);
                    url = SUCCESS+type.replaceAll(" ", "")+"Page.jsp";
                    break;
                case SERVICE:
                    ServiceDAO serviceDao = new ServiceDAO();
                    ArrayList<ServiceDTO> serviceList = serviceDao.getListService(search);
                    request.setAttribute("SERVICE_LIST", serviceList);
                    url = SUCCESS+type.replaceAll(" ", "")+"Page.jsp";
                    break;
                case CONTRACT:
                    ContractDAO contractDao = new ContractDAO();
                    ArrayList<ContractDTO> contractList = contractDao.getListContract(search);
                    request.setAttribute("CONTRACT_LIST", contractList);
                    url = SUCCESS+type.replaceAll(" ", "")+"Page.jsp";
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
