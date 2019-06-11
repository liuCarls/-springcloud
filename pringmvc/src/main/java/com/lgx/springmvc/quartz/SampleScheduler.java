//package com.lgx.springmvc.quartz;
//
//import org.quartz.JobDetail;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
////@Configuration
//public class SampleScheduler {
//    @Bean
//    public JobDetail sampleJobDetail() {
//        return JobBuilder.newJob(SampleJob.class).withIdentity("sampleJob")
//                .usingJobData("name", "World").storeDurably().build();
//    }
//}
