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
import sample.DAO.ContractDAO;
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
            String user = "longtlpse16097@fpt.edu.vn";
            String password = "";

            PrivateNotificationDAO privateNotiDao = new PrivateNotificationDAO();
            ContractDAO contractDao = new ContractDAO();
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
            //mail for almost due contract
            ArrayList<String> emailList1 = contractDao.getListUserEMailAlmostDueContract(15);
            ArrayList<String> emailList2 = contractDao.getListUserEMailAlmostDueContract(4);
            ArrayList<String> userIDList1 = contractDao.getListUserIDAlmostDueContract(15);
            ArrayList<String> userIDList2 = contractDao.getListUserIDAlmostDueContract(4);

            for (String toEmail : emailList1) {
                subject = "Due contract reminder";
                message = "It seems like your contract is about to end within 15 days from now please take your time and check";
                MailUtils.sendMail(toEmail, subject, message, user, password);
            }
            
            for (String toEmail : emailList2) {
                subject = "Due contract reminder";
                message = "It seems like your contract is about to end within 4 days from now please take your time and check";
                MailUtils.sendMail(toEmail, subject, message, user, password);
            }
            
            for (String toEmail : userIDList1) {
                subject = "Due contract reminder";
                message = "It seems like your contract is about to end within 15 days from now please take your time and check";
                MailUtils.sendMail(toEmail, subject, message, user, password);
            }
            
            for (String toEmail : userIDList2) {
                subject = "Due contract reminder";
                message = "It seems like your contract is about to end within 4 days from now please take your time and check";
                MailUtils.sendMail(toEmail, subject, message, user, password);
            }
            
        } catch (Exception e) {
            log("Error while sending mail: " + e.toString());
        }

    }

}
