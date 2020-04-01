package com.lgx.test.quartz;

import org.quartz.*;

public class HelloJob implements Job {
    public HelloJob() {
    }
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        /*
        在Job执行时，JobExecutionContext中的JobDataMap为我们提供了很
        多的便利。它是JobDetail中的JobDataMap和Trigger中的JobDataMap的并集，
        但是如果存在相同的数据，则后者会覆盖前者的值。
         */
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
//        JobDataMap dataMap = context.getMergedJobDataMap();
        String jobSays = dataMap.getString("jobSays");
        System.err.println("Hello!  com.lgx.test.quartz.HelloJob is executing.");
    }
}
