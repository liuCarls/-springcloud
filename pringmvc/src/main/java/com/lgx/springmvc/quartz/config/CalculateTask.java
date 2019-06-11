package com.lgx.springmvc.quartz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import javax.annotation.Resource;

//@Configuration
public class CalculateTask {
    //时间配置文件
    @Resource
    private QuartzPropertyConfig zKQuartzConfig;

    @Bean(name="calculate")
    public JobDetailFactoryBean jobDetailFactoryBean(){
        //生成JobDetail
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(CalculateJob.class);  //设置对应的Job
        factory.setGroup("calculateGroup");
        factory.setName("calculateJob");
        return factory;
    }


    @Bean(name="calculateTrigger")
    public CronTriggerFactoryBean cronTriggerFactoryBean(){
        CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
        //设置JobDetail
        stFactory.setJobDetail(jobDetailFactoryBean().getObject());
        stFactory.setStartDelay(1000);
        stFactory.setName("calculateTrigger");
        stFactory.setGroup("calculateGroup");
        stFactory.setCronExpression(zKQuartzConfig.getCalculateTriggerCron());  //定时任务时间配置
        return stFactory;
    }
}
