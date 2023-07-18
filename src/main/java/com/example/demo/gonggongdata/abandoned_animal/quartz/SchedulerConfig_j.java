package com.example.demo.gonggongdata.abandoned_animal.quartz;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class SchedulerConfig_j {

    @Bean
    public JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(MyJob_j.class);
        jobDetailFactory.setDescription("Invoke my job every minute");
        jobDetailFactory.setDurability(true);

        jobDetailFactory.setApplicationContextJobDataKey("applicationContext");
        return jobDetailFactory;
    }

    @Bean
    public SimpleTriggerFactoryBean trigger(JobDetail jobDetail) {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(jobDetail);
        // trigger.setRepeatInterval(1_800_000); // Run every 30 minute
        // trigger.setRepeatInterval(3_600_000); // Run every 1 hour
        trigger.setRepeatInterval(21_600_00);
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return trigger;
    }
}
