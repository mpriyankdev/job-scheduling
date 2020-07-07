package com.maersk.utility;

import com.maersk.model.Job;
import com.maersk.model.JobResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JobRequestToJobConverter {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    public Job convert(final JobResponse jobResponse) {

        Job job = new Job(this);
        job.setJobId(jobResponse.getJobId());
        job.setJobEnqueTime(jobResponse.getJobEnqueTime());
        job.setJob(jobResponse.getJob());
        job.setElapsedTime(jobResponse.getJobElapsedTime());
        job.setJobStatus(jobResponse.getJobStatus());

        return job;


    }
}
