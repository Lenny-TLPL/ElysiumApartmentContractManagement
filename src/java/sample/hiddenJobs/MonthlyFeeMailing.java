/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.hiddenJobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import sample.utils.MailUtils;

/**
 *
 * @author Phi Long
 */
public class MonthlyFeeMailing implements Job{

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        String toMail="tranlephilong27@gmail.com";
        String subject = "Testing";
        String message = "This is for testing.";
        String user = "longtlpse160987@fpt.edu.vn";
        String password = "1q2w3e4r5t@@";
        MailUtils.sendMail(toMail, subject, message, user, password);
      
    }
    
}
