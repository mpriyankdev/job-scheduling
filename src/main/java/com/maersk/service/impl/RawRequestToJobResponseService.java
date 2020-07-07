package com.maersk.service.impl;

import com.maersk.consts.JobStatus;
import com.maersk.model.JobResponse;
import com.maersk.service.IRawRequestToJobResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class RawRequestToJobResponseService implements IRawRequestToJobResponseService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public JobResponse convert(final String jobId, final List<Integer> rawInput) {

        LOGGER.info("Creating JobResponse for jobId : {}", jobId);
        JobResponse response = JobResponse.builder().jobId(jobId).jobElapsedTime(-1L).jobEnqueTime(OffsetDateTime.now()).jobStatus(JobStatus.PENDING).job(rawInput).build();
        return response;

    }
}
