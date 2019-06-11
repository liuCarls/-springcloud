package com.lgx.springmvc.quartz.config;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
//@DisallowConcurrentExecution
public class CalculateJob implements Job {
//    @Resource
//    private CalculateService calculateService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }

    //CalculateService是配置在spring容器中的，而CalculateJob则是配置在Quartz数据库中，由
//    org.springframework.scheduling.quartz.SpringBeanJobFactory
    // 运行时调用
//    protected ObjectcreateJobInstance(TriggerFiredBundle bundle) throws Exception;
//    方法创建的。
// 所以我们等会把 Quartz中的job自动注入spring容器托管的对象
}
