package com.maersk.repository.impl;

import com.maersk.model.Job;
import com.maersk.repository.IJobHandlingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Repository
public class JobHandlingRepository implements IJobHandlingRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    @Qualifier("job_requests")
    private ConcurrentMap<String, Job> jobRequests;

    @Override
    public Job saveJobResult(final Job job) {
        LOGGER.info("saving jobResult with id : {} ", job.getJobId());
        return jobRequests.put(job.getJobId(), job);

    }

    @Override
    public Job fetchJobRequest(final String jobId) {

        LOGGER.info("Fetching job result with id : {} ", jobId);
        return jobRequests.getOrDefault(jobId, new Job(this));
    }

    @Override
    public List<Job> fetchAllJobs() {
        LOGGER.info("fetch all jobs request received");
        return jobRequests.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

}
