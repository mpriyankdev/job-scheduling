package com.maersk.events;

import com.maersk.consts.JobStatus;
import com.maersk.model.Job;
import com.maersk.service.impl.JobHandlingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class RequestEventListener implements ApplicationListener<Job> {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private JobHandlingService jobHandlingService;

    @Autowired
    private RequestEventPublisher requestEventPublisher;

    @Override
    @Async("threadPoolTaskExecutor")
    public void onApplicationEvent(final Job job) {


        if (job.getJobStatus().equals(JobStatus.PENDING)) {

            LOGGER.info("Received job request with id : {} in state : {} ", job.getJobId(), job.getJobStatus());
            jobHandlingService.executeJob(job);

            try {
                //mimicking delay..if at all it was sync. call , the user will get blocked for 10secs per call
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            requestEventPublisher.publish(job);

        }


        if (job.getJobStatus().equals(JobStatus.COMPLETED)) {

            LOGGER.info("Received job request with id : {} in state : {} ", job.getJobId(), job.getJobStatus());
            jobHandlingService.persistJobResult(job);
        }

    }
}
