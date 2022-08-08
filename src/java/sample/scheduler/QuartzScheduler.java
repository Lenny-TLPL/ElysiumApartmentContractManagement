/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.scheduler;

import static java.rmi.server.LogStream.log;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import sample.hiddenJobs.DueContractMailing;
import sample.hiddenJobs.ManageDueContract;
import sample.hiddenJobs.MonthlyFeeCalculate;
import sample.hiddenJobs.MonthlyMailing;

/**
 *
 * @author Phi Long
 */
public class QuartzScheduler implements ServletContextListener {

    Scheduler manageDueContractScheduler;
    Scheduler monthlyFeeCalculateScheduler;
    Scheduler monthlyMailingScheduler;
    Scheduler dueContractMailingScheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            //run everyday
            //manage due contract
            JobDetail manageDueContract = JobBuilder.newJob(ManageDueContract.class)
                    .withIdentity("manageDueContract", "group1").build();

            Trigger trigger1 = TriggerBuilder.newTrigger()
                    .withIdentity("cronTrigger1", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 ? * *"))
                    .build();

            manageDueContractScheduler = new StdSchedulerFactory().getScheduler();
            manageDueContractScheduler.start();
            manageDueContractScheduler.scheduleJob(manageDueContract, trigger1);

            
            //run every first day of every month
            //monthly fee calculate
            JobDetail monthlyFeeCalculate = JobBuilder.newJob(MonthlyFeeCalculate.class)
                    .withIdentity("monthlyFeeCalculate", "group2").build();

            Trigger trigger2 = TriggerBuilder.newTrigger()
                    .withIdentity("cronTrigger2", "group2")
                    .withSchedule(CronScheduleBuilder.cronSchedule(new CronExpression("0 0 0 1 * ?")))
                    .build();

            monthlyFeeCalculateScheduler = new StdSchedulerFactory().getScheduler();
            monthlyFeeCalculateScheduler.start();
            monthlyFeeCalculateScheduler.scheduleJob(monthlyFeeCalculate, trigger2);
            
            
            //run every first day of everymonth
            //monthly fee mailing
            JobDetail monthlyMailing = JobBuilder.newJob(MonthlyMailing.class)
                    .withIdentity("monthlyMailing", "group3").build();

            Trigger trigger3 = TriggerBuilder.newTrigger()
                    .withIdentity("cronTrigger3", "group3")
                    .withSchedule(CronScheduleBuilder.cronSchedule(new CronExpression("0 0 0 1 * ?")))
                    .build();

            monthlyMailingScheduler = new StdSchedulerFactory().getScheduler();
            monthlyMailingScheduler.start();
            monthlyMailingScheduler.scheduleJob(monthlyMailing, trigger3);
            
            //run everyday
            //due contract mailing
            JobDetail dueContractMailing = JobBuilder.newJob(DueContractMailing.class)
                    .withIdentity("dueContractMailing", "group4").build();

            Trigger trigger4 = TriggerBuilder.newTrigger()
                    .withIdentity("cronTrigger4", "group4")
                    .withSchedule(CronScheduleBuilder.cronSchedule(new CronExpression("0 48 0 ? * *")))
                    .build();

            dueContractMailingScheduler = new StdSchedulerFactory().getScheduler();
            dueContractMailingScheduler.start();
            dueContractMailingScheduler.scheduleJob(dueContractMailing, trigger4);
        } catch (Exception e) {
             log("Error at QuartzScheduler: " + e.toString());
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            manageDueContractScheduler.shutdown();
            monthlyFeeCalculateScheduler.shutdown();
            monthlyMailingScheduler.shutdown();
            dueContractMailingScheduler.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
