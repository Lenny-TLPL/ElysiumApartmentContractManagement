/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Phi Long
 */
@MultipartConfig
public class MainController extends HttpServlet {

    private static final String ERROR = "error404.jsp";
    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogOutController";
    private static final String ADD = "Add";
    private static final String ADD_CONTROLLER = "AddController";
    private static final String SEARCH = "Search";
    private static final String SEARCH_CONTROLLER = "SearchController";
    private static final String GET_MATERIAL = "GetMaterial";
    private static final String GET_MATERIAL_CONTROLLER = "GetMaterialController";
    private static final String VIEW_DETAIL = "View Detail";
    private static final String VIEW_DETAIL_CONTROLLER = "ViewDetailController";
    private static final String UPDATE = "Update";
    private static final String UPDATE_CONTROLLER = "UpdateController";
    private static final String UPDATE_STATUS = "Disable Enable";
    private static final String UPDATE_STATUS_CONTROLLER = "UpdateStatusController";

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
            String action = request.getParameter("action");
            if (SEARCH.equals(action)) {
                url = SEARCH_CONTROLLER;
            } else if (LOGIN.equals(action)) {
//                MailUtils.sendEmail();
//                MailUtils.sendMail("tranlephilong27@gmail.com", "Thank for buying our product","Your order at with total price of:  will soon be delivered right to your house", "longtlpse160987@fpt.edu.vn", "1q2w3e4r5t@@");
//                MailUtils.sendEmail("tranlephilong27@gmail.com", "Thank for buying our product","Your order at with total price of:  will soon be delivered right to your house");
                url = LOGIN_CONTROLLER;
            } else if (ADD.equals(action)) {
                url = ADD_CONTROLLER;
            } else if (LOGOUT.equals(action)) {
                url = LOGOUT_CONTROLLER;
            } else if (GET_MATERIAL.equals(action)) {
                url = GET_MATERIAL_CONTROLLER;
            } else if (VIEW_DETAIL.equals(action)) {
                url = VIEW_DETAIL_CONTROLLER;
            } else if (UPDATE.equals(action)){
                url = UPDATE_CONTROLLER;
            } else if (UPDATE_STATUS.contains(action)){
                url = UPDATE_STATUS_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at MainController:" + e.toString());
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
