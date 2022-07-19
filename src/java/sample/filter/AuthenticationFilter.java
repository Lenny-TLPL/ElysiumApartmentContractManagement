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
import sample.DTO.UserDTO;

/**
 *
 * @author Phi Long
 */
public class AuthenticationFilter implements Filter {
    
    private static final boolean debug = true;
    private static final String CUS = "Customer";
    private static final String RES = "Resident";
    private static final String HR = "HR Manager";
    private static final String EM = "Employee";
    private static final String BM = "Board Manager";
    private static final String LOGIN_PAGE="login.jsp";
    private static ArrayList<String> USER_FUNCTION;
    private static ArrayList<String> ADMIN_FUNCTION;
    
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public AuthenticationFilter() {
        USER_FUNCTION = new ArrayList<>();
        USER_FUNCTION.add("login.jsp");
        USER_FUNCTION.add("MainController");
        USER_FUNCTION.add("LoginController");
        USER_FUNCTION.add("userMainPage.jsp");
        USER_FUNCTION.add("userNotificationDetailPage.jsp");
        USER_FUNCTION.add("userNotificationPage.jsp");
        USER_FUNCTION.add("userPersonalInformationPage.jsp");
        
        ADMIN_FUNCTION = new ArrayList<>();
        ADMIN_FUNCTION.add("login.jsp");
        ADMIN_FUNCTION.add("MainController");
        ADMIN_FUNCTION.add("LoginController");
        ADMIN_FUNCTION.add("adminAddApartmentBuildingPage.jsp");
        ADMIN_FUNCTION.add("adminAddApartmentPage.jsp");
        ADMIN_FUNCTION.add("adminAddApartmentTypePage.jsp");
        ADMIN_FUNCTION.add("adminAddContractPage.jsp");
        ADMIN_FUNCTION.add("adminAddDistrictPage.jsp");
        ADMIN_FUNCTION.add("adminAddNotificationPage.jsp");
        ADMIN_FUNCTION.add("adminAddPermissionPage.jsp");
        ADMIN_FUNCTION.add("adminAddPrivateNotificationPage.jsp");
        ADMIN_FUNCTION.add("adminAddServicePage.jsp");
        ADMIN_FUNCTION.add("adminAddUserPage.jsp");
        ADMIN_FUNCTION.add("adminApartmentPage.jsp");
        ADMIN_FUNCTION.add("adminBillingHistoryPage.jsp");
        ADMIN_FUNCTION.add("adminBoardManagerPage.jsp");
        ADMIN_FUNCTION.add("adminContractDetailPage.jsp");
        ADMIN_FUNCTION.add("adminContractPage.jsp");
        ADMIN_FUNCTION.add("adminCustomerPage.jsp");
        ADMIN_FUNCTION.add("adminDashBoardPage.jsp");
        ADMIN_FUNCTION.add("adminEmployeePage.jsp");
        ADMIN_FUNCTION.add("adminHRManagerPage.jsp");
        ADMIN_FUNCTION.add("adminMainPage.jsp");
        ADMIN_FUNCTION.add("adminMonthlyFeePage.jsp");
        ADMIN_FUNCTION.add("adminNotificationDetailPage.jsp");
        ADMIN_FUNCTION.add("adminNotificationPage.jsp");
        ADMIN_FUNCTION.add("adminPermissionDetailPage.jsp");
        ADMIN_FUNCTION.add("adminPermissionPage.jsp");
        ADMIN_FUNCTION.add("adminPrivateNotificationDetailPage.jsp");
        ADMIN_FUNCTION.add("adminResidentPage.jsp");
        ADMIN_FUNCTION.add("adminServiceDetailPage.jsp");
        ADMIN_FUNCTION.add("adminServicePage.jsp");
        ADMIN_FUNCTION.add("adminUserDetailPage.jsp");
        
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
            if (uri.contains(".jpg") || uri.contains(".gif") || uri.contains(".png")) {
                chain.doFilter(request, response);
            } else {
                if (uri.contains("login.jsp") || uri.contains("MainController") || uri.contains("LoginController")) {
                    chain.doFilter(request, response);
                }else{
                    int index = uri.lastIndexOf("/");
                    String resource = uri.substring(index + 1);
                    HttpSession session = req.getSession();
                    if (session == null || session.getAttribute("LOGIN_USER") == null) {
                        res.sendRedirect(LOGIN_PAGE);
                    } else {
                        UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                        String roleName =(String) session.getAttribute("LOGIN_USER_ROLE");
                        ArrayList<String> permission = (ArrayList<String>) session.getAttribute("LOGIN_USER_PERMISSION");
                        if ((EM+" "+HR+" "+BM).contains(roleName) && ADMIN_FUNCTION.contains(resource)) {
                            chain.doFilter(request, response);
                        } else if ((RES+" "+CUS).contains(roleName) && USER_FUNCTION.contains(resource)) {
                            chain.doFilter(request, response);
                        } else {
                            res.sendRedirect(LOGIN_PAGE);
                        }
                    }
                }
            }
        } catch (Exception e) {
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
