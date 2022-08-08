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

/**
 *
 * @author Phi Long
 */
public class MonthlyFeeCalculate implements Job {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try {
            MonthlyFeeDAO monthlyFeeDao = new MonthlyFeeDAO();
            ArrayList<String> apartmentIDList = monthlyFeeDao.getActiveMonthlyFeeApartmentIDList();
            for (String apartmentID : apartmentIDList) {
                monthlyFeeDao.monthlyFeeCalculate(apartmentID);
            }
        } catch (Exception e) {
             log("Error while calculate monthly fee: " + e.toString());
        } 
    }

}
