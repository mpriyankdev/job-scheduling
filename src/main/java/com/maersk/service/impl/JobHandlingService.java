package com.maersk.service.impl;

import com.maersk.consts.JobStatus;
import com.maersk.events.RequestEventPublisher;
import com.maersk.model.Job;
import com.maersk.repository.impl.JobHandlingRepository;
import com.maersk.service.IJobHandlingService;
import com.maersk.utility.JobSchedulingAlgo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class JobHandlingService implements IJobHandlingService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private JobHandlingRepository jobHandlingRepository;
    @Autowired
    private JobSchedulingAlgo sortingAlgo;
    @Autowired
    private RequestEventPublisher requestEventPublisher;


    @Override
    public void executeJob(final Job job) {

        LOGGER.info("Going to execute job-scheduling logic for jobId : {} ", job.getJobId());
        final List<Integer> sortedJob = sortingAlgo.sort(job.getJob());
        job.setJobFinishTime(OffsetDateTime.now());
        job.setJobStatus(JobStatus.COMPLETED);
        job.setElapsedTime(elapsedTime(job.getJobEnqueTime(), job.getJobFinishTime()));
        job.setJob(sortedJob);

        LOGGER.info("Publishing success event after job finishing for jobId : {} ", job.getJobId());
        requestEventPublisher.publish(job);


    }

    @Override
    public Job persistJobResult(final Job job) {

        LOGGER.info("Persisting job result for jobId : {} ", job.getJobId());
        return jobHandlingRepository.saveJobResult(job);
    }

    @Override
    public Job fetchJobResult(final String jobId) {
        LOGGER.info("Fetching jobResult for jobId : {} ", jobId);
        return jobHandlingRepository.fetchJobRequest(jobId);
    }

    @Override
    public List<Job> fetchAllJobs() {
        return jobHandlingRepository.fetchAllJobs();
    }

    private Long elapsedTime(final OffsetDateTime d1, final OffsetDateTime d2) {

        final long until = d1.until(d2, ChronoUnit.SECONDS);
        return until;
    }
}
