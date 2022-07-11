/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.scheduler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import sample.hiddenJobs.MonthlyFeeMailing;

/**
 *
 * @author Phi Long
 */
public class QuartzScheduler implements ServletContextListener {

//    Scheduler scheduler1;
//    Scheduler scheduler2;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
//            JobDetail job1 = JobBuilder.newJob(MonthlyFeeMailing.class)
//                    .withIdentity("job1", "group1").build();
//
//            Trigger trigger1 = TriggerBuilder.newTrigger()
//                    .withIdentity("cronTrigger1", "group1")
//                    .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?"))
//                    .build();
//
//            scheduler1 = new StdSchedulerFactory().getScheduler();
//            scheduler1.start();
//            scheduler1.scheduleJob(job1, trigger1);

//            JobDetail job2 = JobBuilder.newJob(Job2.class)
//                    .withIdentity("job2", "group2").build();
//
//            Trigger trigger2 = TriggerBuilder.newTrigger()
//                    .withIdentity("cronTrigger2", "group2")
//                    .withSchedule(CronScheduleBuilder.cronSchedule(new CronExpression("0/45 * * * * ?")))
//                    .build();
//
//            scheduler2 = new StdSchedulerFactory().getScheduler();
//            scheduler2.start();
//            scheduler2.scheduleJob(job2, trigger2);
//            Thread.sleep(100000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        try {
//            scheduler1.shutdown();
//            scheduler2.shutdown();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
