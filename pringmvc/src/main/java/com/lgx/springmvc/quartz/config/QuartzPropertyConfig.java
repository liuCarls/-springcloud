package com.lgx.springmvc.quartz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class QuartzPropertyConfig {
    /**
     * 清算任务
     */
    @Value("${job.calculateTriggerCron}")
    private String calculateTriggerCron;

    public String getCalculateTriggerCron() {
        return calculateTriggerCron;
    }

    public void setCalculateTriggerCron(String   calculateTriggerCron) {
        this.calculateTriggerCron = calculateTriggerCron;
    }
}
