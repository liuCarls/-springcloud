package com.lgx.springmvc.quartz;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 自定义任务 Job
 */
@Service
public class Job1Demo extends QuartzJobBean {
    public void sayHello() {
        System.out.println(new Date() + " -> Hello, 我是任务 1");
    }
//    @Value("${test.job.name}")
    private String name;

    public void setName(String name) {
        this.name = name;
    }
    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        // 传入的参数
        JobDataMap params = context.getJobDetail().getJobDataMap();
        System.out.println(String.format("Hello %s!", this.name));
    }
}
/**
 * 方式一：https://www.cnblogs.com/shizhijie/p/8243934.html
 * 通过Spring配置文件applicationContext.xml中配置了
 * 要注入的bean, 定时任务, 任务执行周期时间等
 * < !-- 任务1配置 -->
 *     <bean name="job1" class="com.quartz.demo.Job1Demo"/>
 *     <bean id="jobDetail_1" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
 *         < !-- 执行的类 -->
 *         <property name="targetObject">
 *             <ref bean="job1" />
 *         </property>
 *         < !-- 类中的方法 -->
 *         <property name="targetMethod">
 *             <value>sayHello</value>
 *         </property>
 *     </bean>
 *     <bean id="cronTrigger_1" class="org.springframework.scheduling.quartz.CronTriggerBean">
 *         <property name="jobDetail">
 *             <ref bean="jobDetail_1" />
 *         </property>
 *         < !-- 每一秒钟执行一次 -->
 *         <property name="cronExpression">
 *             <value>0/1 * * * * ?</value>
 *         </property>
 *     </bean>
 *      < !-- 总配置 -->
 *     <bean class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
 *         < !-- 添加触发器 -->
 *         <property name="triggers">
 *             <list>
 *                 <ref bean="cronTrigger_1" />
 *                 <ref bean="cronTrigger_2" />
 *             </list>
 *         </property>
 *     </bean>
 *     方式二.
 */
