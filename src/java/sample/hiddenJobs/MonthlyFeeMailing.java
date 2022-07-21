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
import sample.utils.MailUtils;

/**
 *
 * @author Phi Long
 */
public class MonthlyFeeMailing implements Job{

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try {
            String subject = "Monthly fee reminder";
            String message = "It's the begining of new month and looks like you still haven't pay monthly fee.\n"+"Go to http://localhost:8080/ElysiumApartmentContractManagement/login.jsp for more details. \n"+"If you had paid this month fee, you can skip this mail ";
            String user = "longtlpse16097@fpt.edu.vn";
            String password = "";
            
            MonthlyFeeDAO monthlyFeeDao = new MonthlyFeeDAO();
            ArrayList<String> emailList = monthlyFeeDao.getUserMailForMonthlyMailing();
            for (String toEmail : emailList) {
                MailUtils.sendMail(toEmail, subject, message, user, password);
            }        
        } catch (Exception e) {
            log("Error while sending mail: "+ e.toString());
        }
        
      
    }
    
}
