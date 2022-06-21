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
import sample.DAO.RoleDAO;
import sample.DAO.UserDAO;
import sample.DTO.UserDTO;

/**
 *
 * @author Phi Long
 */
public class LoginController extends HttpServlet {
    private static final String ERROR = "login.jsp";
    private static final String CUS = "Customer";
    private static final String RES ="Resident";
    private static final String HR = "HR Manager";
    private static final String EM ="Employee";
    private static final String BM = "Board Manager";
    private static final String USER_PAGE = "index.jsp";
    private static final String ADMIN_PAGE = "adminMainPage.jsp";
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
            String userID=request.getParameter("userID");
            String password=request.getParameter("password");
            UserDAO dao=new UserDAO();
            RoleDAO roleDao=new RoleDAO();
            UserDTO loginUser=new UserDTO();
            loginUser=dao.checkLogin(userID, password);
            if (loginUser!=null)
            {
                HttpSession session=request.getSession();
                session.setAttribute("LOGIN_USER", loginUser);
                String roleName=roleDao.getUserRole(loginUser.getRoleID());
                session.setAttribute("LOGIN_USER_ROLE", roleName);
                if (CUS.equals(roleName)||RES.equals(roleName))
                {
                    url=USER_PAGE;
                }
                else if (BM.equals(roleName)||HR.equals(roleName)||EM.equals(roleName))
                {
                    url=ADMIN_PAGE;
                }
                else request.setAttribute("LOGIN_ERROR", "Role not supported");
            }
            else
            {
                request.setAttribute("LOGIN_ERROR", "Incorrect userID or password!");
            }
        } catch (Exception e) {
            log("Error at LoginController :" + e.toString());
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
