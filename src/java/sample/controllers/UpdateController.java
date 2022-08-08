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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import sample.DAO.ContractDAO;
import sample.DAO.DistrictDAO;
import sample.DAO.MonthlyFeeDAO;
import sample.DAO.NotificationDAO;
import sample.DAO.PermissionDAO;
import sample.DAO.PrivateNotificationDAO;
import sample.DAO.RoleDAO;
import sample.DAO.ServiceDAO;
import sample.DAO.UserDAO;
import sample.DAO.UserPermissionDAO;
import sample.DTO.ContractDTO;
import sample.DTO.ContractError;
import sample.DTO.DistrictError;
import sample.DTO.NotificationDTO;
import sample.DTO.NotificationError;
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
    private static final String MONTHLY_FEE = "MonthlyFee";
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
                    String password = request.getParameter("password");
                    String[] permissions = request.getParameterValues("permissions");
                    boolean check = true;
                    String phoneRegex = "^\\d{11}$";
                    String phoneRegex2 = "^\\d{10}$";

                    UserPermissionDAO userPermissionDao = new UserPermissionDAO();
                    PermissionDAO permissionDao = new PermissionDAO();
                    UserDAO userDao = new UserDAO();
                    RoleDAO roleDao = new RoleDAO();
                    UserError userError = new UserError();
                    if (fullName.length() > 60 || fullName.length() < 4 || fullName.trim().equals("")) {
                        userError.setFullName("FullName needs to be between 4 and 60 characters.");
                        check = false;
                    }
                    if (userDao.checkDuplicatePhoneAdminV2(phone, userID)) {
                        userError.setPhone("Duplicate phone");
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
                    if (userDao.checkDuplicateEmailAdminV2(email, userID)) {
                        userError.setEmail("Duplicate email");
                        check = false;
                    }
                    if (!DateUtils.checkValidDate(birthday)) {
                        userError.setBirthday("Invalid birthday");
                        check = false;
                    }

                    if (!phone.matches(phoneRegex) && !phone.matches(phoneRegex2)) {
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
                        boolean checkUpdate = userDao.updateUser(new UserDTO(userID, fullName, email, phone, address, birthday, citizenID, gender, password, null, status, roleDao.getUserRoleID(roleName)));
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
                    password = request.getParameter("password");
                    permissions = request.getParameterValues("permissions");
                    check = true;
                    phoneRegex = "^\\d{11}$";
                    phoneRegex2 = "^\\d{10}$";

                    userPermissionDao = new UserPermissionDAO();
                    permissionDao = new PermissionDAO();
                    userDao = new UserDAO();
                    roleDao = new RoleDAO();
                    userError = new UserError();
                    if (fullName.length() > 60 || fullName.length() < 4 || fullName.trim().equals("")) {
                        userError.setFullName("FullName needs to be between 4 and 60 characters.");
                        check = false;
                    }
                    if (userDao.checkDuplicatePhoneAdminV2(phone, userID)) {
                        userError.setPhone("Duplicate phone");
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
                    if (userDao.checkDuplicateEmailAdminV2(email, userID)) {
                        userError.setEmail("Duplicate email");
                        check = false;
                    }
                    if (!DateUtils.checkValidDate(birthday)) {
                        userError.setBirthday("Invalid birthday");
                        check = false;
                    }

                    if (!phone.matches(phoneRegex) && !phone.matches(phoneRegex2)) {
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
                        boolean checkUpdate = userDao.updateUser(new UserDTO(userID, fullName, email, phone, address, birthday, citizenID, gender, password, null, status, roleDao.getUserRoleID(roleName)));
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
                    password = request.getParameter("password");
                    permissions = request.getParameterValues("permissions");
                    check = true;
                    phoneRegex = "^\\d{11}$";
                    phoneRegex2 = "^\\d{10}$";

                    userPermissionDao = new UserPermissionDAO();
                    permissionDao = new PermissionDAO();
                    userDao = new UserDAO();
                    roleDao = new RoleDAO();
                    userError = new UserError();
                    if (fullName.length() > 60 || fullName.length() < 4 || fullName.trim().equals("")) {
                        userError.setFullName("FullName needs to be between 4 and 60 characters.");
                        check = false;
                    }
                    if (userDao.checkDuplicatePhoneAdminV2(phone, userID)) {
                        userError.setPhone("Duplicate phone");
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

                    if (userDao.checkDuplicateEmailAdminV2(email, userID)) {
                        userError.setEmail("Duplicate email");
                        check = false;
                    }
                    if (!DateUtils.checkValidDate(birthday)) {
                        userError.setBirthday("Invalid birthday");
                        check = false;
                    }

                    if (!phone.matches(phoneRegex) && !phone.matches(phoneRegex2)) {
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
                        boolean checkUpdate = userDao.updateUser(new UserDTO(userID, fullName, email, phone, address, birthday, citizenID, gender, password, null, status, roleDao.getUserRoleID(roleName)));
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
                    password = request.getParameter("password");
                    check = true;
                    phoneRegex = "^\\d{11}$";
                    phoneRegex2 = "^\\d{10}$";

                    userDao = new UserDAO();
                    roleDao = new RoleDAO();
                    userError = new UserError();
                    if (fullName.length() > 60 || fullName.length() < 4 || fullName.trim().equals("")) {
                        userError.setFullName("FullName needs to be between 4 and 60 characters.");
                        check = false;
                    }
                    if (userDao.checkDuplicatePhoneUserV2(phone, userID)) {
                        userError.setPhone("Duplicate phone");
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
                    if (userDao.checkDuplicateEmailUserV2(email, userID)) {
                        userError.setEmail("Duplicate email");
                        check = false;
                    }
                    if (!DateUtils.checkValidDate(birthday)) {
                        userError.setBirthday("Invalid birthday");
                        check = false;
                    }

                    if (!phone.matches(phoneRegex) && !phone.matches(phoneRegex2)) {
                        userError.setPhone("Invalid phone number");
                        check = false;
                    }
                    if (roleName == null) {
                        userError.setRoleName("Invalid roleName");
                        check = false;
                    }
                    if (check) {
                        boolean checkUpdate = userDao.updateUser(new UserDTO(userID, fullName, email, phone, address, birthday, citizenID, gender, password, null, status, roleDao.getUserRoleID(roleName)));

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
                    password = request.getParameter("password");
                    check = true;
                    phoneRegex = "^\\d{11}$";
                    phoneRegex2 = "^\\d{10}$";

                    userDao = new UserDAO();
                    roleDao = new RoleDAO();
                    userError = new UserError();
                    if (fullName.length() > 60 || fullName.length() < 4 || fullName.trim().equals("")) {
                        userError.setFullName("FullName needs to be between 4 and 60 characters.");
                        check = false;
                    }
                    if (userDao.checkDuplicatePhoneUserV2(phone, userID)) {
                        userError.setPhone("Duplicate phone");
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
                    if (userDao.checkDuplicateEmailUserV2(email, userID)) {
                        userError.setEmail("Duplicate email");
                        check = false;
                    }
                    if (!DateUtils.checkValidDate(birthday)) {
                        userError.setBirthday("Invalid birthday");
                        check = false;
                    }

                    if (!phone.matches(phoneRegex) && !phone.matches(phoneRegex2)) {
                        userError.setPhone("Invalid phone number");
                        check = false;
                    }
                    if (roleName == null) {
                        userError.setRoleName("Invalid roleName");
                        check = false;
                    }
                    if (check) {
                        boolean checkUpdate = userDao.updateUser(new UserDTO(userID, fullName, email, phone, address, birthday, citizenID, gender, password, null, status, roleDao.getUserRoleID(roleName)));

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

                    if (serviceName.length() > 60 || serviceName.length() < 4 || serviceName.trim().equals("")) {
                        serviceError.setServiceName("Name must be from 4 to 60 chars");
                        check = false;
                    }
                    if (price <= 0) {
                        serviceError.setPrice("Price must be >0");
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

                    NotificationError notiError = new NotificationError();
                    if (notiHeader.trim().equals("")) {
                        notiError.setNotiHeader("Header can not be null");
                        check = false;
                    }
                    if (notiContent.trim().equals("")) {
                        notiError.setNotiContent("Content can not be null");
                        check = false;
                    }

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

                    if (notiHeader.trim().equals("")) {
                        privateNotiError.setNotiHeader("Header can not be null");
                        check = false;
                    }
                    if (notiContent.trim().equals("")) {
                        privateNotiError.setNotiContent("Content can not be null");
                        check = false;
                    }

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

                    if (permissionName.length() > 60 || permissionName.length() < 4 || permissionName.trim().equals("")) {
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
                case DISTRICT:
                    String districtName = request.getParameter("districtName");
                    int districtID = Integer.parseInt(request.getParameter("districtID"));
                    DistrictDAO districtDao = new DistrictDAO();
                    DistrictError districtError = new DistrictError();
                    check = true;
                    if (districtName.trim().equals("")) {
                        districtError.setDistrictName("Name can not be null");
                        check = false;
                    }
                    if (districtDao.checkDuplicate(districtName, districtID)) {
                        districtError.setDistrictName("Duplicate district name.");
                        check = false;
                    }
                    if (check) {
                        boolean checkUpdateDistrict = districtDao.updateDistrict(districtName, districtID);
                        if (checkUpdateDistrict) {
                            request.setAttribute("UPDATE_DISTRICT_SUCCESS", "Update successfully");
                        } else {
                            districtError.setErrorMessage("Fail to update.");
                            request.setAttribute("UPDATE_DISTRICT_ERROR", districtError);
                        }
                    } else {
                        districtError.setErrorMessage("Fail to update.");
                        request.setAttribute("UPDATE_DISTRICT_ERROR", districtError);
                    }
                    url = "MainController?action=Search&type=Apartment&search=";
                    break;
                case MONTHLY_FEE:
                    int monthlyFeeID = Integer.parseInt(request.getParameter("monthlyFeeID"));
                    float contractFee = Float.parseFloat(request.getParameter("contractFee"));
                    float serviceFee = Float.parseFloat(request.getParameter("serviceFee"));
                    float waterFee = Float.parseFloat(request.getParameter("waterFee"));
                    float electricityFee = Float.parseFloat(request.getParameter("electricityFee"));
                    MonthlyFeeDAO monthlyFeeDao = new MonthlyFeeDAO();
                    check = true;

                    if (contractFee < 0 || serviceFee < 0 || waterFee < 0 || electricityFee < 0) {
                        check = false;
                    }
                    if (check) {
                        boolean checkUpdateMonthlyFee = monthlyFeeDao.updateMonthlyFee(contractFee, serviceFee, waterFee, electricityFee, monthlyFeeID);
                        if (checkUpdateMonthlyFee) {
                            request.setAttribute("UPDATE_MONTHLY_FEE_SUCCESS", "Update successfully");
                        } else {
                            request.setAttribute("UPDATE_MONTHLY_FEE_ERROR", "Update Error");
                        }
                    } else {
                        request.setAttribute("UPDATE_MONTHLY_FEE_ERROR", "Update Error");
                    }
                    url = "MainController?action=Search&type=MonthlyFee&search=";
                    break;
                case CONTRACT:
                    int contractID = Integer.parseInt(request.getParameter("contractID"));
                    status = Boolean.parseBoolean(request.getParameter("status"));
                    userID = request.getParameter("userID");
                    Date dateSign = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateSign"));
                    Part contractImageFilePart = request.getPart("contractImage");
                    String apartmentID = request.getParameter("apartmentID");
                    String contractType = request.getParameter("contractType");
                    Date expiryDate = null;
                    Float value = Float.parseFloat(request.getParameter("value"));
                    int monthsOfDebt = 0;
                    float interestRate = 0;
                    if (contractType.equals("amortization")) {
                        interestRate = Float.parseFloat(request.getParameter("interestRate"));
                        monthsOfDebt = Integer.parseInt(request.getParameter("monthsOfDebt"));
                        expiryDate = DateUtils.plusMonths(dateSign, monthsOfDebt);
                    } else if (contractType.equals("leasing")) {
                        expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("expiryDate"));
                    }
                    check = true;

                    ContractDAO contractDao = new ContractDAO();
                    userDao = new UserDAO();
                    roleDao = new RoleDAO();
                    ContractError contractError = new ContractError();
                    UserDTO user = userDao.getUserByIDAndStatus(userID, true);

                    if (!userID.contains("ELY-") || user == null) {
                        contractError.setUserID("Invalid userID");
                        check = false;
                    }

                    if (expiryDate != null) {
                        if (DateUtils.compareTwoDate(dateSign, expiryDate)) {
                            contractError.setDateSign("Invalid expiryDate");
                            check = false;
                        }
                    }

                    if (user != null && userID.contains("ELY-")) {
                        if (!DateUtils.checkValidDate(dateSign) || !DateUtils.compareTwoDate(dateSign, user.getBirthDay())) {
                            contractError.setDateSign("Invalid dateSign");
                            check = false;
                        }

                        if (("amortization leasing").contains(contractType)) {
                            if (!DateUtils.compareTwoDate(expiryDate, user.getBirthDay()) || DateUtils.compareTwoDate(dateSign, expiryDate)) {
                                contractError.setExpiryDate("Invalid expiryDate");
                                check = false;
                            }
                        }
                    }

                    
                  

//                    if (contractType.equals("amortization") && DateUtils.subtractTwoDay(dateSign, expiryDate) != monthsOfDebt) {
//                        contractError.setExpiryDate("Invalid expiryDate");
//                        check = false;
//                    }
                    if (value < 0) {
                        contractError.setValue("Invalid value");
                        check = false;
                    }
                    if (check) {
                        boolean checkUpdateContract = false;
                        if (contractImageFilePart.getSize()<=0) {
                            checkUpdateContract = contractDao.updateContract(new ContractDTO(contractID, dateSign, null, userID, apartmentID, value, expiryDate, monthsOfDebt, interestRate, contractType, status));
                        } else {
                            checkUpdateContract = contractDao.updateContract(new ContractDTO(contractID, dateSign, null, userID, apartmentID, value, expiryDate, monthsOfDebt, interestRate, contractType, status), contractImageFilePart);
                        }
                        if (checkUpdateContract) {
                            if (contractType.equals("buying") || contractType.equals("amortization")) {
                                userDao.updateUserRole(roleDao.getUserRoleID("Resident"), userID);
                            }
                            request.setAttribute("UPDATE_CONTRACT_SUCCESS", "Update contract successfully.");
                        } else {
                            contractError.setErrorMessage("Fail to update contract.");
                            request.setAttribute("UPDATE_CONTRACT_ERROR", contractError);
                        }
                    } else {
                        contractError.setErrorMessage("Fail to update contract.");
                        request.setAttribute("UPDATE_CONTRACT_ERROR", contractError);
                    }
                    url = "ViewDetailController?contractID=" + contractID + "&type=Contract&redirect=adminContractDetailPage.jsp";
                    break;
                case "User":
                    userID = request.getParameter("userID");
                    fullName = request.getParameter("fullName");
                    gender = request.getParameter("gender");
                    email = request.getParameter("email");
                    phone = request.getParameter("phone");
                    address = request.getParameter("address");
                    citizenID = request.getParameter("citizenID");
                    birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
                    Date dateJoin = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateJoin"));
                    roleName = request.getParameter("roleName");
                    password = request.getParameter("password");
                    status = Boolean.parseBoolean(request.getParameter("status"));
                    
                    String confirmPassword = request.getParameter("password");
                    check = true;
                    phoneRegex = "^\\d{11}$";
                    phoneRegex2 = "^\\d{10}$";

                    userDao = new UserDAO();
                    roleDao = new RoleDAO();
                    userError = new UserError();
                    if (fullName.length() > 60 || fullName.length() < 4 || fullName.trim().equals("")) {
                        userError.setFullName("FullName needs to be between 4 and 60 characters.");
                        check = false;
                    }
                    if (userDao.checkDuplicatePhoneUserV2(phone, userID)) {
                        userError.setPhone("Duplicate phone");
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
                    if (userDao.checkDuplicateEmailUserV2(email, userID)) {
                        userError.setEmail("Duplicate email");
                        check = false;
                    }
                    if (!DateUtils.checkValidDate(birthday)) {
                        userError.setBirthday("Invalid birthday");
                        check = false;
                    }

                    if (!phone.matches(phoneRegex) && !phone.matches(phoneRegex2)) {
                        userError.setPhone("Invalid phone number");
                        check = false;
                    }
                    if (!confirmPassword.equals(password)) {
                        userError.setPassword("Invalid confirm");
                        check = false;
                    }
                    if (check) {
                        boolean checkUpdate = userDao.updateUser(new UserDTO(userID, fullName, email, phone, address, birthday, citizenID, gender, password, null, status, roleDao.getUserRoleID(roleName)));

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
                    HttpSession session = request.getSession();
                    UserDTO loginUser = userDao.checkLogin(userID, password);
                    session.setAttribute("LOGIN_USER", loginUser);
                    session.setAttribute("LOGIN_USER_ROLE", roleName);
                    url = "ViewDetailController?roleID=" + userDao.getUserByID(userID).getRoleID() + "&type=" + roleDao.getUserRole(userDao.getUserByID(userID).getRoleID()) + "&redirect=userPersonalInformationPage.jsp";
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
