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
import sample.DAO.NotificationDAO;
import sample.DAO.PermissionDAO;
import sample.DAO.PrivateNotificationDAO;
import sample.DAO.RoleDAO;
import sample.DAO.ServiceDAO;
import sample.DAO.UserDAO;
import sample.DAO.UserPermissionDAO;
import sample.DTO.NotificationDTO;
import sample.DTO.PermissionDTO;
import sample.DTO.PermissionError;
import sample.DTO.PrivateNotificationDTO;
import sample.DTO.PrivateNotificationError;
import sample.DTO.ServiceDTO;
import sample.DTO.ServiceError;
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
            switch (type) {
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
                    if (roleName == null) {
                        userError.setRoleName("Invalid roleName");
                        check = false;
                    } else if (("HR Manager Employee").contains(roleName)) {
                        ArrayList<Integer> permissionListWithPriority = permissionDao.getListPermissionIDWithPriority(roleName);
                        for (String permission : permissions) {
                            if (!permissionListWithPriority.contains(Integer.parseInt(permission))) {
                                request.setAttribute("UPDATE_PERMISSION_ERROR", "Invalid permission for this role.");
                                check = false;
                                break;
                            }
                        }
                    }

                    if (check) {
                        boolean checkUpdate = userDao.updateUser(new UserDTO(userID, fullName, email, phone, address, birthday, citizenID, gender, null, null, status, roleDao.getUserRoleID(roleName)));
                        userPermissionDao.deleteUserPermission(userID);
                        for (String permisson : permissions) {
                            userPermissionDao.addUser(userID, Integer.parseInt(permisson));
                        }
                        if (checkUpdate) {
                            request.setAttribute("UPDATE_USER_SUCCESS", "Update successfully.");
                        } else {
                            userError.setErrorMessage("Fail to update.");
                            request.setAttribute("UPDATE_USER_ERROR", userError);
                        }
                    } else {
                        userError.setErrorMessage("Fail to update.");
                        request.setAttribute("UPDATE_USER_ERROR", userError);
                    }
                    url = "ViewDetailController?roleID=" + userDao.getUserByID(userID).getRoleID() + "&type=" + roleDao.getUserRole(userDao.getUserByID(userID).getRoleID()) + "&redirect=adminUserDetailPage.jsp";
                    break;
                case HR_MANAGER:
                    userID = request.getParameter("userID");
                    fullName = request.getParameter("fullName");
                    gender = request.getParameter("gender");
                    email = request.getParameter("email");
                    phone = request.getParameter("phone");
                    address = request.getParameter("address");
                    citizenID = request.getParameter("citizenID");
                    birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
                    status = Boolean.parseBoolean(request.getParameter("status"));
                    roleName = request.getParameter("roleName");
                    permissions = request.getParameterValues("permissions");
                    check = true;
                    phoneRegex = "^\\d{11}$";

                    userPermissionDao = new UserPermissionDAO();
                    permissionDao = new PermissionDAO();
                    userDao = new UserDAO();
                    roleDao = new RoleDAO();
                    userError = new UserError();
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
                    if (roleName == null) {
                        userError.setRoleName("Invalid roleName");
                        check = false;
                    } else if (("HR Manager Employee").contains(roleName)) {
                        ArrayList<Integer> permissionListWithPriority = permissionDao.getListPermissionIDWithPriority(roleName);
                        for (String permission : permissions) {
                            if (!permissionListWithPriority.contains(Integer.parseInt(permission))) {
                                request.setAttribute("UPDATE_PERMISSION_ERROR", "Invalid permission for this role.");
                                check = false;
                                break;
                            }
                        }
                    }

                    if (check) {
                        boolean checkUpdate = userDao.updateUser(new UserDTO(userID, fullName, email, phone, address, birthday, citizenID, gender, null, null, status, roleDao.getUserRoleID(roleName)));
                        userPermissionDao.deleteUserPermission(userID);
                        for (String permisson : permissions) {
                            userPermissionDao.addUser(userID, Integer.parseInt(permisson));
                        }
                        if (checkUpdate) {
                            request.setAttribute("UPDATE_USER_SUCCESS", "Update successfully.");
                        } else {
                            userError.setErrorMessage("Fail to update.");
                            request.setAttribute("UPDATE_USER_ERROR", userError);
                        }
                    } else {
                        userError.setErrorMessage("Fail to update.");
                        request.setAttribute("UPDATE_USER_ERROR", userError);
                    }
                    url = "ViewDetailController?roleID=" + userDao.getUserByID(userID).getRoleID() + "&type=" + roleDao.getUserRole(userDao.getUserByID(userID).getRoleID()) + "&redirect=adminUserDetailPage.jsp";
                    break;
                case EMPLOYEE:
                    userID = request.getParameter("userID");
                    fullName = request.getParameter("fullName");
                    gender = request.getParameter("gender");
                    email = request.getParameter("email");
                    phone = request.getParameter("phone");
                    address = request.getParameter("address");
                    citizenID = request.getParameter("citizenID");
                    birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
                    status = Boolean.parseBoolean(request.getParameter("status"));
                    roleName = request.getParameter("roleName");
                    permissions = request.getParameterValues("permissions");
                    check = true;
                    phoneRegex = "^\\d{11}$";

                    userPermissionDao = new UserPermissionDAO();
                    permissionDao = new PermissionDAO();
                    userDao = new UserDAO();
                    roleDao = new RoleDAO();
                    userError = new UserError();
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
                    if (roleName == null) {
                        userError.setRoleName("Invalid roleName");
                        check = false;
                    } else if (("HR Manager Employee").contains(roleName)) {
                        ArrayList<Integer> permissionListWithPriority = permissionDao.getListPermissionIDWithPriority(roleName);
                        for (String permission : permissions) {
                            if (!permissionListWithPriority.contains(Integer.parseInt(permission))) {
                                request.setAttribute("UPDATE_PERMISSION_ERROR", "Invalid permission for this role.");
                                check = false;
                                break;
                            }
                        }
                    }

                    if (check) {
                        boolean checkUpdate = userDao.updateUser(new UserDTO(userID, fullName, email, phone, address, birthday, citizenID, gender, null, null, status, roleDao.getUserRoleID(roleName)));
                        userPermissionDao.deleteUserPermission(userID);
                        for (String permisson : permissions) {
                            userPermissionDao.addUser(userID, Integer.parseInt(permisson));
                        }
                        if (checkUpdate) {
                            request.setAttribute("UPDATE_USER_SUCCESS", "Update successfully.");
                        } else {
                            userError.setErrorMessage("Fail to update.");
                            request.setAttribute("UPDATE_USER_ERROR", userError);
                        }
                    } else {
                        userError.setErrorMessage("Fail to update.");
                        request.setAttribute("UPDATE_USER_ERROR", userError);
                    }
                    url = "ViewDetailController?roleID=" + userDao.getUserByID(userID).getRoleID() + "&type=" + roleDao.getUserRole(userDao.getUserByID(userID).getRoleID()) + "&redirect=adminUserDetailPage.jsp";
                    break;
                case RESIDENT:
                    userID = request.getParameter("userID");
                    fullName = request.getParameter("fullName");
                    gender = request.getParameter("gender");
                    email = request.getParameter("email");
                    phone = request.getParameter("phone");
                    address = request.getParameter("address");
                    citizenID = request.getParameter("citizenID");
                    birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
                    status = Boolean.parseBoolean(request.getParameter("status"));
                    roleName = request.getParameter("roleName");
                    check = true;
                    phoneRegex = "^\\d{11}$";

                    userDao = new UserDAO();
                    roleDao = new RoleDAO();
                    userError = new UserError();
                    if (fullName.length() > 60 || fullName.length() < 4) {
                        userError.setFullName("FullName needs to be between 4 and 60 characters.");
                        check = false;
                    }
                    if (userDao.checkDuplicateUserV2(citizenID, userID)) {
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
                    if (roleName == null) {
                        userError.setRoleName("Invalid roleName");
                        check = false;
                    }
                    if (check) {
                        boolean checkUpdate = userDao.updateUser(new UserDTO(userID, fullName, email, phone, address, birthday, citizenID, gender, null, null, status, roleDao.getUserRoleID(roleName)));

                        if (checkUpdate) {
                            request.setAttribute("UPDATE_USER_SUCCESS", "Update successfully.");
                        } else {
                            userError.setErrorMessage("Fail to update.");
                            request.setAttribute("UPDATE_USER_ERROR", userError);
                        }
                    } else {
                        userError.setErrorMessage("Fail to update.");
                        request.setAttribute("UPDATE_USER_ERROR", userError);
                    }
                    url = "ViewDetailController?roleID=" + userDao.getUserByID(userID).getRoleID() + "&type=" + roleDao.getUserRole(userDao.getUserByID(userID).getRoleID()) + "&redirect=adminUserDetailPage.jsp";
                    break;
                case CUSTOMER:
                    userID = request.getParameter("userID");
                    fullName = request.getParameter("fullName");
                    gender = request.getParameter("gender");
                    email = request.getParameter("email");
                    phone = request.getParameter("phone");
                    address = request.getParameter("address");
                    citizenID = request.getParameter("citizenID");
                    birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
                    status = Boolean.parseBoolean(request.getParameter("status"));
                    roleName = request.getParameter("roleName");
                    check = true;
                    phoneRegex = "^\\d{11}$";

                    userDao = new UserDAO();
                    roleDao = new RoleDAO();
                    userError = new UserError();
                    if (fullName.length() > 60 || fullName.length() < 4) {
                        userError.setFullName("FullName needs to be between 4 and 60 characters.");
                        check = false;
                    }
                    if (userDao.checkDuplicateUserV2(citizenID, userID)) {
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
                    if (roleName == null) {
                        userError.setRoleName("Invalid roleName");
                        check = false;
                    }
                    if (check) {
                        boolean checkUpdate = userDao.updateUser(new UserDTO(userID, fullName, email, phone, address, birthday, citizenID, gender, null, null, status, roleDao.getUserRoleID(roleName)));

                        if (checkUpdate) {
                            request.setAttribute("UPDATE_USER_SUCCESS", "Update successfully.");
                        } else {
                            userError.setErrorMessage("Fail to update.");
                            request.setAttribute("UPDATE_USER_ERROR", userError);
                        }
                    } else {
                        userError.setErrorMessage("Fail to update.");
                        request.setAttribute("UPDATE_USER_ERROR", userError);
                    }
                    url = "ViewDetailController?roleID=" + userDao.getUserByID(userID).getRoleID() + "&type=" + roleDao.getUserRole(userDao.getUserByID(userID).getRoleID()) + "&redirect=adminUserDetailPage.jsp";
                    break;
                case SERVICE:
                    int serviceID = Integer.parseInt(request.getParameter("serviceID"));
                    String serviceName = request.getParameter("serviceName");
                    String description = request.getParameter("description");
                    float price = Float.parseFloat(request.getParameter("price"));
                    status = Boolean.parseBoolean(request.getParameter("status"));
                    check = true;

                    ServiceDAO serviceDao = new ServiceDAO();
                    ServiceDTO service = null;
                    ServiceError serviceError = new ServiceError();

                    if (serviceName.length() > 60 || serviceName.length() < 4) {
                        serviceError.setServiceName("Name must be from 4 to 60 chars");
                        check = false;
                    }

                    if (serviceDao.getServiceByName(serviceName) != null && serviceDao.getServiceByName(serviceName).getServiceID() != serviceID) {
                        serviceError.setServiceName("Duplicate service name");
                        check = false;
                    }
                    if (check) {
                        service = new ServiceDTO(serviceID, serviceName, description, price, status);
                        boolean checkUpdate = serviceDao.updateService(service);
                        if (checkUpdate) {
                            request.setAttribute("UPDATE_SERVICE_SUCCESS", "Update successfully.");
                        } else {
                            serviceError.setErrorMessage("Fail to update service.");
                            request.setAttribute("UPDATE_SERVICE_ERROR", serviceError);
                        }
                    } else {
                        serviceError.setErrorMessage("Fail to update service");
                        request.setAttribute("UPDATE_SERVICE_ERROR", serviceError);
                    }
                    url = "ViewDetailController?serviceID=" + serviceID + "&type=Service&redirect=adminServiceDetailPage.jsp";
                    break;
                case NOTIFICATION:
                    int notiID = Integer.parseInt(request.getParameter("notiID"));
                    String notiHeader = request.getParameter("notiHeader");
                    String notiContent = request.getParameter("notiContent");
                    status = Boolean.parseBoolean(request.getParameter("status"));
                    check = true;

                    NotificationDAO notiDao = new NotificationDAO();
                    NotificationDTO notification = new NotificationDTO(notiID, notiHeader, notiContent, null, status);
                    if (check) {
                        boolean checkUpdate = notiDao.updateNotification(notification);
                        if (checkUpdate) {
                            request.setAttribute("UPDATE_NOTIFICATION_SUCCESS", "Update successfuly.");
                        } else {
                            request.setAttribute("UPDATE_NOTIFICATION_ERROR", "Fail to update notification.");
                        }
                    } else {
                        request.setAttribute("UPDATE_NOTIFICATION_ERROR", "Fail to updates notification.");
                    }
                    url = "ViewDetailController?notiID=" + notiID + "&type=Notification&redirect=adminNotificationDetailPage.jsp";
                    break;
                case PRIVATE_NOTIFICATION:
                    notiID = Integer.parseInt(request.getParameter("notiID"));
                    notiHeader = request.getParameter("notiHeader");
                    notiContent = request.getParameter("notiContent");
                    userID = request.getParameter("userID");
                    check = true;

                    PrivateNotificationError privateNotiError = new PrivateNotificationError();
                    PrivateNotificationDAO privateNotiDao = new PrivateNotificationDAO();
                    PrivateNotificationDTO privateNotification = new PrivateNotificationDTO(notiID, notiHeader, notiContent, null, userID, true);
                    userDao = new UserDAO();

                    if (userDao.getUserByIDAndStatus(userID, true) == null) {
                        privateNotiError.setNotiID("Invalid userID");
                    }
                    if (check) {
                        boolean checkUpdate = privateNotiDao.updatePrivateNotification(privateNotification);
                        if (checkUpdate) {
                            request.setAttribute("UPDATE_PRIVATE_NOTIFICATION_SUCCESS", "Update successfully.");
                        } else {
                            request.setAttribute("UPDATE_PRIVATE_NOTIFICATION_ERROR", "Fail to update notification.");
                        }
                    } else {
                        privateNotiError.setErrorMessage("Fail to update notification.");
                        request.setAttribute("UPDATE_PRIVATE_NOTIFICATION_ERROR", privateNotiError);
                    }
                    url = "ViewDetailController?notiID=" + notiID + "&type=Private Notification&redirect=adminPrivateNotificationDetailPage.jsp";

                    break;
                case PERMISSION:
                    int permissionID = Integer.parseInt(request.getParameter("permissionID"));
                    String permissionName = request.getParameter("permissionName");
                    status = Boolean.parseBoolean(request.getParameter("status"));
                    String roleNamePriority = request.getParameter("rolePriority");
                    check = true;

                    permissionDao = new PermissionDAO();
                    PermissionDTO permission = null;
                    PermissionError permissionError = new PermissionError();

                    if (permissionName.length() > 60 || permissionName.length() < 4) {
                        permissionError.setPermissionName("Name must be from 4 to 60 chars");
                        check = false;
                    }
                    if (permissionDao.getPermissionByName(permissionName) != null && permissionDao.getPermissionByName(permissionName).getPermissionID() != permissionID) {
                        permissionError.setPermissionName("Duplicate permission name");
                        check = false;
                    }
                    if (check) {
                        roleDao = new RoleDAO();
                        permission = new PermissionDTO(permissionID, permissionName, roleNamePriority, status);
                        boolean checkAdd = permissionDao.updatePermission(permission, roleDao.getUserRoleID(roleNamePriority));
                        if (checkAdd) {
                            request.setAttribute("UPDATE_PERMISSION_SUCCESS", "Update succesfully.");
                        } else {
                            permissionError.setErrorMessage("Fail to update permission.");
                            request.setAttribute("UPDATE_PERMISSION_ERROR", permissionError);
                        }
                    } else {
                        permissionError.setErrorMessage("Fail to update permission");
                        request.setAttribute("UPDATE_PERMISSION_ERROR", permissionError);
                    }
                    url = "MainController?action=Search&type=Permission&search=";
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
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
