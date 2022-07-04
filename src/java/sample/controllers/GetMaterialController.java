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
import sample.DTO.PermissionDTO;

/**
 *
 * @author Phi Long
 */
public class GetMaterialController extends HttpServlet {
    private static final String ERROR = "error404.jsp";
    private static String SUCCESS = "";
    private static final String PERMISSION = "Permission";
    private static final String EMPLOYEE = "Employee";
    private static final String HR_MANAGER = "HR Manager";
    private static final String BOARD_MANAGER = "Board Manager";
    private static final String APARTMENT = "Apartment";
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
            String require = request.getParameter("require");
            String redirect = request.getParameter("redirect");
            String type = request.getParameter("type");
            SUCCESS = redirect+"?type="+type;
            
            switch(require){
                case PERMISSION:
                    PermissionDAO permissionDao = new PermissionDAO();
                    ArrayList<PermissionDTO> permissionList =null;
                    if(type.equals(BOARD_MANAGER)){
                        permissionList = permissionDao.getListPermissionWithPriority("");
                    } else if(type.equals(HR_MANAGER)){
                        permissionList = permissionDao.getListPermissionWithPriority(type);
                    } else if(type.equals(EMPLOYEE)){
                        permissionList = permissionDao.getListPermissionWithPriority(type);
                    } 
                   
                    request.setAttribute("PERMISSION_LIST",permissionList);
                    url = SUCCESS;
//                case APARTMENT:
//                    ApartmentDao apartmentDao = new 
            }
        } catch (Exception e) {
            log("Error at GetMaterialController:" + e.toString());
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
