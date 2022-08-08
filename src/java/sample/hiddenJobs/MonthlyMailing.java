/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.hiddenJobs;

import static java.rmi.server.LogStream.log;
import java.util.ArrayList;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import sample.DAO.MonthlyFeeDAO;
import sample.DAO.PrivateNotificationDAO;
import sample.utils.MailUtils;

/**
 *
 * @author Phi Long
 */
public class MonthlyMailing implements Job {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try {
            String subject = "Monthly fee reminder";
            String message = "It's the begining of new month and looks like you still haven't pay monthly fee.\n" + "Go to http://localhost:8080/ElysiumApartmentContractManagement/login.jsp for more details. \n" + "If you had paid this month fee, you can skip this mail ";
            String user = "longtlpse160987@fpt.edu.vn";
            String password = "";

            PrivateNotificationDAO privateNotiDao = new PrivateNotificationDAO();
            MonthlyFeeDAO monthlyFeeDao = new MonthlyFeeDAO();
            //mail for monthly fee
            ArrayList<String> emailList = monthlyFeeDao.getUserMailForMonthlyMailing();
            for (String toEmail : emailList) {
                MailUtils.sendMail(toEmail, subject, message, user, password);
            }
            ArrayList<String> userIDlList = monthlyFeeDao.getUserIDForMonthlyMailing();
            for (String userID : userIDlList) {
                privateNotiDao.addPrivateNotification(subject, message, userID);
            }
            
            
        } catch (Exception e) {
            log("Error while sending mail: " + e.toString());
        }

    }

}
