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
import sample.DAO.PrivateNotificationDAO;
import sample.utils.MailUtils;

/**
 *
 * @author Phi Long
 */
public class DueContractMailing implements Job {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try {
            ContractDAO contractDao = new ContractDAO();
            String subject = null;
            String message = null;
            String user = "longtlpse160987@fpt.edu.vn";
            String password = "";
            PrivateNotificationDAO privateNotiDao = new PrivateNotificationDAO();
            //mail for almost due contract
            // due within 15 days
            ArrayList<String> emailListFor15Days = contractDao.getListUserEMailAlmostDueContract(15);
            //due within 4 days
            ArrayList<String> emailListFor4Days = contractDao.getListUserEMailAlmostDueContract(4);
            
            ArrayList<String> userIDListFor15Days = contractDao.getListUserIDAlmostDueContract(15);
            ArrayList<String> userIDListFor4Days = contractDao.getListUserIDAlmostDueContract(4);

            for (String toEmail : emailListFor15Days) {
                subject = "Due contract reminder";
                message = "It seems like your contract is about to end within 15 days from now please take your time and check";
                MailUtils.sendMail(toEmail, subject, message, user, password);
            }
            
            for (String toEmail : emailListFor4Days) {
                subject = "Due contract reminder";
                message = "It seems like your contract is about to end within 4 days from now please take your time and check";
                MailUtils.sendMail(toEmail, subject, message, user, password);
            }
            
            for (String userID : userIDListFor15Days) {
                subject = "Due contract reminder";
                message = "It seems like your contract is about to end within 15 days from now please take your time and check";
                privateNotiDao.addPrivateNotification(subject, message, userID);
            }
            
            for (String userID : userIDListFor4Days) {
                subject = "Due contract reminder";
                message = "It seems like your contract is about to end within 4 days from now please take your time and check";
                privateNotiDao.addPrivateNotification(subject, message, userID);
            }
        } catch (Exception e) {
            log("Error while sending mail: " + e.toString());
        }
    }
    
}
