package com.example.demo.gonggongdata.abandoned_animal.quartz;

import com.example.demo.gonggongdata.abandoned_animal.service.AbandonedAnimalService_j;
import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

@Log4j2
public class MyJob_j implements Job {

    private AbandonedAnimalService_j abandonedAnimalService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContext");
            this.abandonedAnimalService = (AbandonedAnimalService_j) applicationContext.getBean(AbandonedAnimalService_j.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("********** Executing my job... **********");
        this.abandonedAnimalService.doJob();
    }
}
