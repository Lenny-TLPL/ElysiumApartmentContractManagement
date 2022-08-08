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
import sample.DAO.ApartmentBuildingDAO;
import sample.DAO.ApartmentTypeDAO;
import sample.DAO.DistrictDAO;
import sample.DAO.MonthlyFeeDAO;
import sample.DAO.PermissionDAO;
import sample.DAO.ReportDAO;
import sample.DAO.RoleDAO;
import sample.DTO.ApartmentBuildingDTO;
import sample.DTO.ApartmentTypeDTO;
import sample.DTO.DistrictDTO;
import sample.DTO.MonthlyFeeDTO;
import sample.DTO.PermissionDTO;
import sample.DTO.RoleDTO;

/**
 *
 * @author Phi Long
 */
public class GetMaterialController extends HttpServlet {

    private static final String ERROR = "error404.jsp";
    private static String SUCCESS = "";
    private static final String ROLE = "Role";
    private static final String PERMISSION = "Permission";
    private static final String EMPLOYEE = "Employee";
    private static final String HR_MANAGER = "HR Manager";
    private static final String BOARD_MANAGER = "Board Manager";
    private static final String MONTHLY_FEE = "MonthlyFee";
    private static final String APARTMENT = "Apartment";
    private static final String APARTMENT_BUILDING = "Apartment Building";
    private static final String VIEW_REPORT = "View Report";

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
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
            SUCCESS = redirect + "?type=" + type;

            switch (require) {
                case PERMISSION:
                    PermissionDAO permissionDao = new PermissionDAO();
                    ArrayList<PermissionDTO> permissionList = null;
                    if (type.equals(BOARD_MANAGER)) {
                        permissionList = permissionDao.getListPermissionWithPriority("");
                    } else if (type.equals(HR_MANAGER)) {
                        permissionList = permissionDao.getListPermissionWithPriority(type);
                    } else if (type.equals(EMPLOYEE)) {
                        permissionList = permissionDao.getListPermissionWithPriority(type);
                    }

                    request.setAttribute("PERMISSION_LIST", permissionList);
                    url = SUCCESS;
                    break;
                case ROLE:
                    RoleDAO roleDao = new RoleDAO();
                    ArrayList<RoleDTO> roleList = roleDao.getListRole(search);
                    request.setAttribute("ROLE_LIST", roleList);
                    url = SUCCESS;
                    break;
                case APARTMENT:
                    ApartmentBuildingDAO apBuildingDao = new ApartmentBuildingDAO();
                    ApartmentTypeDAO apTypeDao = new ApartmentTypeDAO();
                    ArrayList<ApartmentBuildingDTO> buildingList = apBuildingDao.getApartmentBuildingList(search);
                    ArrayList<ApartmentTypeDTO> typeList = apTypeDao.getApartmentTypeList(search);
                    request.setAttribute("APARTMENT_BUILDING_LIST", buildingList);
                    request.setAttribute("APARTMENT_TYPE_LIST", typeList);
                    url = SUCCESS;
                    break;
                case APARTMENT_BUILDING:
                    DistrictDAO districtDao = new DistrictDAO();
                    ArrayList<DistrictDTO> districtList = districtDao.getDistrictList(search);
                    request.setAttribute("DISTRICT_LIST", districtList);
                    url = SUCCESS;
                    break;
                case VIEW_REPORT:
                    ReportDAO reportDao = new ReportDAO();
                    ArrayList<Integer> dataValue = new ArrayList<>();
                    int maxValueYColumn = ((reportDao.getUserQuantity()/10)+1)*10;
                    dataValue.add(maxValueYColumn);
//                    dataValue.add(reportDao.getUserQuantity());
                    dataValue.add(reportDao.getBoardManagerQuantity());
                    dataValue.add(reportDao.getHRManagerQuantity());
                    dataValue.add(reportDao.getEmployeeQuantity());
                    dataValue.add(reportDao.getResidentQuantity());
                    dataValue.add(reportDao.getCustomerQuantity());
                    dataValue.add(reportDao.getTotalApartment());
                    dataValue.add(reportDao.getTotalAvailableApartment());
                    dataValue.add(reportDao.getTotalRentedApartment());
                    dataValue.add(reportDao.getTotalMaintenanceApartment());
//                    dataValue.add(((reportDao.getTotalContractSignedThisYear()/10)+1)*10);
                    ArrayList<Integer> list = reportDao.getTotalContractSignedThisYear();
                    for (int i = 0; i < list.size(); i++) {
                        if(i==0){
                            dataValue.add(((list.get(i)/10)+1)*10);
                        }else{
                            dataValue.add(list.get(i));
                        }
                        
                    }
//                    dataValue.add(reportDao.getTotalContractSignedThisYear1());
//                    dataValue.add(reportDao.getTotalContractSignedThisYear2());
//                    dataValue.add(reportDao.getTotalContractSignedThisYear3());
//                    dataValue.add(reportDao.getTotalContractSignedThisYear4());
//                    dataValue.add(reportDao.getTotalContractSignedThisYear5());
//                    dataValue.add(reportDao.getTotalContractSignedThisYear6());
//                    dataValue.add(reportDao.getTotalContractSignedThisYear7());
//                    dataValue.add(reportDao.getTotalContractSignedThisYear8());
//                    dataValue.add(reportDao.getTotalContractSignedThisYear9());
//                    dataValue.add(reportDao.getTotalContractSignedThisYear10());
//                    dataValue.add(reportDao.getTotalContractSignedThisYear11());
//                    dataValue.add(reportDao.getTotalContractSignedThisYear12());
                    request.setAttribute("DATA_VALUE", dataValue);
                    url = SUCCESS;
                    break;
                case MONTHLY_FEE:
                    String userID = request.getParameter("userID");
                    String apartmentID = request.getParameter("apartmentID");
                    MonthlyFeeDAO monthlyFeeDao = new MonthlyFeeDAO();
                    MonthlyFeeDTO monthlyFee = monthlyFeeDao. getUserMonthlyFeeDetail(userID, apartmentID);
                    request.setAttribute("USER_MONTHLY_FEE", monthlyFee);
                    url=SUCCESS;
                    break;
                default:
                    break;
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
