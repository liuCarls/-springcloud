package com.lgx.springmvc.quartz.config;

import org.quartz.Job;
import org.quartz.SchedulerException;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

public class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory
        implements ApplicationContextAware {
    private transient AutowireCapableBeanFactory beanFactory;
    //Spring提供了一种机制让你可以获取ApplicationContext。就是
//    ApplicationContextAware接口。
    //对于一个实现了ApplicationContextAware接口的类，Spring会实例化它的同时
    //调用它的setApplicationContext接口，将该bean所属上下文传递给它。

//    @Override
//    public Job newJob(TriggerFiredBundle triggerFiredBundle) throws SchedulerException {
//        return null;
//    }

    @Override
    public void setApplicationContext(final ApplicationContext context) {
        beanFactory = context.getAutowireCapableBeanFactory();
    }

    @Override
    protected Object createJobInstance(final TriggerFiredBundle bundle)
            throws Exception {
        final Object job = super.createJobInstance(bundle);
        beanFactory.autowireBean(job);
        return job;
    }
}
