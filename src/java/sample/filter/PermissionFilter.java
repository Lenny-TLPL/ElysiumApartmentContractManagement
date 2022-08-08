/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package sample.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.DAO.PermissionDAO;
import sample.DAO.UserDAO;
import sample.DTO.UserDTO;

/**
 *
 * @author Phi Long
 */
public class PermissionFilter implements Filter {

    private static final boolean debug = true;
    private static final String CUS = "Customer";
    private static final String RES = "Resident";
    private static final String HR = "HR Manager";
    private static final String EM = "Employee";
    private static final String BM = "Board Manager";
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String ERROR_PAGE = "permissionError.html";
    private static ArrayList<Object> adminFunction;
    private static ArrayList<String> USER_FUNCTION;
    private static ArrayList<String> ADMIN_FUNCTION;
    private static ArrayList<String> ADMIN_MANAGE_APARTMENT;
    private static ArrayList<String> ADMIN_MANAGE_CONTRACT;
    private static ArrayList<String> ADMIN_MANAGE_NOTIFICATION;
    private static ArrayList<String> ADMIN_MANAGE_PERMISSION;
    private static ArrayList<String> ADMIN_MANAGE_BOARD_MANAGER;
    private static ArrayList<String> ADMIN_MANAGE_HR_MANAGER;
    private static ArrayList<String> ADMIN_MANAGE_EMPLOYEE;
    private static ArrayList<String> ADMIN_MANAGE_CUSTOMER;
    private static ArrayList<String> ADMIN_MANAGE_RESIDENT;
    private static ArrayList<String> ADMIN_MANAGE_SERVICE;
    private static ArrayList<String> ADMIN_MANAGE_BILLING_HISTORY;
    private static ArrayList<String> ADMIN_MANAGE_MONTHLY_FEE;
    private static ArrayList<String> ADMIN_VIEW_REPORT;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public PermissionFilter() {
        adminFunction = new ArrayList<>();
        USER_FUNCTION = new ArrayList<>();
        USER_FUNCTION.add("login.jsp");
        USER_FUNCTION.add("MainController");
        USER_FUNCTION.add("LoginController");
        USER_FUNCTION.add("userMainPage.jsp");
        USER_FUNCTION.add("userContractPage.jsp");
        USER_FUNCTION.add("userContractDetailPage.jsp");
        USER_FUNCTION.add("userApartmentPage.jsp");
        USER_FUNCTION.add("userNotificationDetailPage.jsp");
        USER_FUNCTION.add("userNotificationPage.jsp");
        USER_FUNCTION.add("userNewsDetailPage.jsp");
        USER_FUNCTION.add("userNewsPage.jsp");
        USER_FUNCTION.add("userMonthlyFeePage.jsp");
        USER_FUNCTION.add("userPersonalInformationPage.jsp");
        USER_FUNCTION.add("userBillingHistoryPage.jsp");

        ADMIN_FUNCTION = new ArrayList<>();
        ADMIN_MANAGE_APARTMENT = new ArrayList<>();
        ADMIN_MANAGE_BILLING_HISTORY = new ArrayList<>();
        ADMIN_MANAGE_BOARD_MANAGER = new ArrayList<>();
        ADMIN_MANAGE_HR_MANAGER = new ArrayList<>();
        ADMIN_MANAGE_EMPLOYEE = new ArrayList<>();
        ADMIN_MANAGE_CUSTOMER = new ArrayList<>();
        ADMIN_MANAGE_RESIDENT = new ArrayList<>();
        ADMIN_MANAGE_CONTRACT = new ArrayList<>();
        ADMIN_MANAGE_MONTHLY_FEE = new ArrayList<>();
        ADMIN_MANAGE_NOTIFICATION = new ArrayList<>();
        ADMIN_MANAGE_SERVICE = new ArrayList<>();
        ADMIN_VIEW_REPORT = new ArrayList<>();
        ADMIN_MANAGE_PERMISSION = new ArrayList<>();
        adminFunction.add(ADMIN_FUNCTION);
        adminFunction.add(ADMIN_VIEW_REPORT);
        adminFunction.add(ADMIN_MANAGE_BOARD_MANAGER);
        adminFunction.add(ADMIN_MANAGE_HR_MANAGER);
        adminFunction.add(ADMIN_MANAGE_EMPLOYEE);
        adminFunction.add(ADMIN_MANAGE_CUSTOMER);
        adminFunction.add(ADMIN_MANAGE_RESIDENT);
        adminFunction.add(ADMIN_MANAGE_CONTRACT);
        adminFunction.add(ADMIN_MANAGE_SERVICE);
        adminFunction.add(ADMIN_MANAGE_APARTMENT);
        adminFunction.add(ADMIN_MANAGE_NOTIFICATION);
        adminFunction.add(ADMIN_MANAGE_BILLING_HISTORY);
        adminFunction.add(ADMIN_MANAGE_MONTHLY_FEE);
        adminFunction.add(ADMIN_MANAGE_PERMISSION);

        //general
        ADMIN_FUNCTION.add("adminMainPage.jsp");
        ADMIN_FUNCTION.add("login.jsp");
        ADMIN_FUNCTION.add("MainController");
        ADMIN_FUNCTION.add("LoginController");

        ADMIN_MANAGE_APARTMENT.add("adminAddApartmentBuildingPage.jsp");
        ADMIN_MANAGE_APARTMENT.add("adminAddApartmentPage.jsp");
        ADMIN_MANAGE_APARTMENT.add("adminAddApartmentTypePage.jsp");
        ADMIN_MANAGE_APARTMENT.add("adminAddDistrictPage.jsp");
        ADMIN_MANAGE_APARTMENT.add("adminApartmentPage.jsp");

        ADMIN_MANAGE_CONTRACT.add("adminAddContractPage.jsp");
        ADMIN_MANAGE_CONTRACT.add("adminContractDetailPage.jsp");
        ADMIN_MANAGE_CONTRACT.add("adminContractPage.jsp");

        ADMIN_MANAGE_PERMISSION.add("adminAddPermissionPage.jsp");

        ADMIN_MANAGE_NOTIFICATION.add("adminAddNotificationPage.jsp");
        ADMIN_MANAGE_NOTIFICATION.add("adminAddPrivateNotificationPage.jsp");
        ADMIN_MANAGE_NOTIFICATION.add("adminNotificationDetailPage.jsp");
        ADMIN_MANAGE_NOTIFICATION.add("adminNotificationPage.jsp");
        ADMIN_MANAGE_NOTIFICATION.add("adminPrivateNotificationDetailPage.jsp");

        ADMIN_MANAGE_SERVICE.add("adminAddServicePage.jsp");
        ADMIN_MANAGE_SERVICE.add("adminServiceDetailPage.jsp");
        ADMIN_MANAGE_SERVICE.add("adminServicePage.jsp");

        ADMIN_FUNCTION.add("adminAddUserPage.jsp");
        ADMIN_FUNCTION.add("adminUserDetailPage.jsp");

        ADMIN_MANAGE_BOARD_MANAGER.add("adminBoardManagerPage.jsp");
        ADMIN_MANAGE_BOARD_MANAGER.add("adminAddUserPage.jsp");
        ADMIN_MANAGE_BOARD_MANAGER.add("adminUserDetailPage.jsp");

        ADMIN_MANAGE_BILLING_HISTORY.add("adminBillingHistoryPage.jsp");
        ADMIN_MANAGE_BILLING_HISTORY.add("adminAddBillingHistoryPage.jsp");

        ADMIN_MANAGE_RESIDENT.add("adminResidentPage.jsp");
        ADMIN_MANAGE_RESIDENT.add("adminAddUserPage.jsp");
        ADMIN_MANAGE_RESIDENT.add("adminUserDetailPage.jsp");

        ADMIN_MANAGE_CUSTOMER.add("adminCustomerPage.jsp");
        ADMIN_MANAGE_CUSTOMER.add("adminAddUserPage.jsp");
        ADMIN_MANAGE_CUSTOMER.add("adminUserDetailPage.jsp");

        ADMIN_VIEW_REPORT.add("adminDashBoardPage.jsp");

        ADMIN_MANAGE_EMPLOYEE.add("adminEmployeePage.jsp");
        ADMIN_MANAGE_EMPLOYEE.add("adminAddUserPage.jsp");
        ADMIN_MANAGE_CUSTOMER.add("adminUserDetailPage.jsp");

        ADMIN_MANAGE_HR_MANAGER.add("adminHRManagerPage.jsp");
        ADMIN_MANAGE_HR_MANAGER.add("adminAddUserPage.jsp");
        ADMIN_MANAGE_HR_MANAGER.add("adminUserDetailPage.jsp");

        ADMIN_MANAGE_MONTHLY_FEE.add("adminMonthlyFeePage.jsp");

        ADMIN_MANAGE_PERMISSION.add("adminPermissionDetailPage.jsp");
        ADMIN_MANAGE_PERMISSION.add("adminPermissionPage.jsp");

    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("Filter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("Filter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

//        if (debug) {
//            log("AuthenticationFilter:doFilter()");
//        }
//        
//        doBeforeProcessing(request, response);
//        
//        Throwable problem = null;
//        try {
//            chain.doFilter(request, response);
//        } catch (Throwable t) {
//            // If an exception is thrown somewhere down the filter chain,
//            // we still want to execute our after processing, and then
//            // rethrow the problem after that.
//            problem = t;
//            t.printStackTrace();
//        }
//        
//        doAfterProcessing(request, response);
//
//        // If there was a problem, we want to rethrow it if it is
//        // a known type, otherwise log it.
//        if (problem != null) {
//            if (problem instanceof ServletException) {
//                throw (ServletException) problem;
//            }
//            if (problem instanceof IOException) {
//                throw (IOException) problem;
//            }
//            sendProcessingError(problem, response);
//        }
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            String uri = req.getRequestURI();
            int index = uri.lastIndexOf("/");
            String resource = uri.substring(index + 1);
            if (uri.contains(".jpg") || uri.contains(".gif") || uri.contains(".png") || uri.endsWith(".css") || uri.endsWith(".js")) {
                chain.doFilter(request, response);
            } else {
                if (uri.contains("login.jsp") || uri.contains("MainController") || uri.contains("LoginController") || uri.contains(ERROR_PAGE) || uri.contains("UpdateController") || uri.contains("GetMaterialController") || uri.contains("SearchController") || uri.contains("ViewController")  || uri.endsWith("/")) {
//                    req.getRequestDispatcher(resource).forward(request, response);
                    chain.doFilter(request, response);
                } else {
                    index = uri.lastIndexOf("/");
                    resource = uri.substring(index + 1);
                    HttpSession session = req.getSession();
                    if (session == null || session.getAttribute("LOGIN_USER") == null) {
                        res.sendRedirect(LOGIN_PAGE);
                    } else {
                        UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                        String roleName = (String) session.getAttribute("LOGIN_USER_ROLE");
                        ArrayList<Integer> userPermission = (ArrayList<Integer>) session.getAttribute("LOGIN_USER_PERMISSION");
                        PermissionDAO permissionDao = new PermissionDAO();
                        UserDAO userDao = new UserDAO();
                        ArrayList<Integer> availablePermission = permissionDao.getListAvailablePermission();
                        if (userDao.getUserByID(loginUser.getUserID()).isStatus() == false) {
                            res.sendRedirect(LOGIN_PAGE);
                        } else {
                            if ((EM + " " + HR + " " + BM).contains(roleName) && ADMIN_MANAGE_APARTMENT.contains(resource) && userPermission.contains(adminFunction.indexOf(ADMIN_MANAGE_APARTMENT)) && availablePermission.contains(adminFunction.indexOf(ADMIN_MANAGE_APARTMENT))) {
                                chain.doFilter(request, response);
                            } else if ((EM + " " + HR + " " + BM).contains(roleName) && ADMIN_MANAGE_BILLING_HISTORY.contains(resource) && userPermission.contains(adminFunction.indexOf(ADMIN_MANAGE_BILLING_HISTORY)) && availablePermission.contains(adminFunction.indexOf(ADMIN_MANAGE_BILLING_HISTORY))) {
                                chain.doFilter(request, response);
                            } else if ((EM + " " + HR + " " + BM).contains(roleName) && ADMIN_MANAGE_BOARD_MANAGER.contains(resource) && userPermission.contains(adminFunction.indexOf(ADMIN_MANAGE_BOARD_MANAGER)) && availablePermission.contains(adminFunction.indexOf(ADMIN_MANAGE_BOARD_MANAGER))) {
                                chain.doFilter(request, response);
                            } else if ((EM + " " + HR + " " + BM).contains(roleName) && ADMIN_MANAGE_CONTRACT.contains(resource) && userPermission.contains(adminFunction.indexOf(ADMIN_MANAGE_CONTRACT)) && availablePermission.contains(adminFunction.indexOf(ADMIN_MANAGE_CONTRACT))) {
                                chain.doFilter(request, response);
                            } else if ((EM + " " + HR + " " + BM).contains(roleName) && ADMIN_MANAGE_CUSTOMER.contains(resource) && userPermission.contains(adminFunction.indexOf(ADMIN_MANAGE_CUSTOMER)) && availablePermission.contains(adminFunction.indexOf(ADMIN_MANAGE_CUSTOMER))) {
                                chain.doFilter(request, response);
                            } else if ((EM + " " + HR + " " + BM).contains(roleName) && ADMIN_MANAGE_EMPLOYEE.contains(resource) && userPermission.contains(adminFunction.indexOf(ADMIN_MANAGE_EMPLOYEE)) && availablePermission.contains(adminFunction.indexOf(ADMIN_MANAGE_EMPLOYEE))) {
                                chain.doFilter(request, response);
                            } else if ((EM + " " + HR + " " + BM).contains(roleName) && ADMIN_MANAGE_HR_MANAGER.contains(resource) && userPermission.contains(adminFunction.indexOf(ADMIN_MANAGE_HR_MANAGER)) && availablePermission.contains(adminFunction.indexOf(ADMIN_MANAGE_HR_MANAGER))) {
                                chain.doFilter(request, response);
                            } else if ((EM + " " + HR + " " + BM).contains(roleName) && ADMIN_MANAGE_MONTHLY_FEE.contains(resource) && userPermission.contains(adminFunction.indexOf(ADMIN_MANAGE_MONTHLY_FEE)) && availablePermission.contains(adminFunction.indexOf(ADMIN_MANAGE_MONTHLY_FEE))) {
                                chain.doFilter(request, response);
                            } else if ((EM + " " + HR + " " + BM).contains(roleName) && ADMIN_MANAGE_NOTIFICATION.contains(resource) && userPermission.contains(adminFunction.indexOf(ADMIN_MANAGE_NOTIFICATION)) && availablePermission.contains(adminFunction.indexOf(ADMIN_MANAGE_NOTIFICATION))) {
                                chain.doFilter(request, response);
                            } else if ((EM + " " + HR + " " + BM).contains(roleName) && ADMIN_MANAGE_PERMISSION.contains(resource) && userPermission.contains(adminFunction.indexOf(ADMIN_MANAGE_PERMISSION)) && availablePermission.contains(adminFunction.indexOf(ADMIN_MANAGE_PERMISSION))) {
                                chain.doFilter(request, response);
                            } else if ((EM + " " + HR + " " + BM).contains(roleName) && ADMIN_MANAGE_RESIDENT.contains(resource) && userPermission.contains(adminFunction.indexOf(ADMIN_MANAGE_RESIDENT)) && availablePermission.contains(adminFunction.indexOf(ADMIN_MANAGE_RESIDENT))) {
                                chain.doFilter(request, response);
                            } else if ((EM + " " + HR + " " + BM).contains(roleName) && ADMIN_MANAGE_SERVICE.contains(resource) && userPermission.contains(adminFunction.indexOf(ADMIN_MANAGE_SERVICE)) && availablePermission.contains(adminFunction.indexOf(ADMIN_MANAGE_SERVICE))) {
                                chain.doFilter(request, response);
                            } else if ((EM + " " + HR + " " + BM).contains(roleName) && ADMIN_VIEW_REPORT.contains(resource) && userPermission.contains(adminFunction.indexOf(ADMIN_VIEW_REPORT)) && availablePermission.contains(adminFunction.indexOf(ADMIN_VIEW_REPORT))) {
                                chain.doFilter(request, response);
                            } else if ((EM + " " + HR + " " + BM).contains(roleName) && ADMIN_FUNCTION.contains(resource)) {
                                chain.doFilter(request, response);
                            } else if ((RES + " " + CUS).contains(roleName) && USER_FUNCTION.contains(resource)) {
                                chain.doFilter(request, response);
                            } else {
                                res.sendRedirect(ERROR_PAGE);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log("Error at Filter : " + e.toString());
        } finally {
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("Filter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("Filter()");
        }
        StringBuffer sb = new StringBuffer("Filter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
