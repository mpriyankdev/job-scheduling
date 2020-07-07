package com.maersk.utility;

import com.maersk.model.Job;
import com.maersk.model.JobResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class JobToJobResponseConverter implements Converter<Job, JobResponse> {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());


    @Override
    public JobResponse convert(final Job job) {
        JobResponse response = JobResponse.builder().jobId(job.getJobId()).jobStatus(job.getJobStatus()).jobEnqueTime(job.getJobEnqueTime()).jobElapsedTime(job.getElapsedTime()).job(job.getJob()).build();
        return response;
    }
}
