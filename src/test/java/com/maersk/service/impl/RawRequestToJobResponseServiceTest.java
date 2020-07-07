package com.maersk.service.impl;

import com.maersk.consts.JobStatus;
import com.maersk.model.JobResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

class RawRequestToJobResponseServiceTest {

    @Autowired
    private RawRequestToJobResponseService rawRequestToJobResponseService;

    @Test
    @DisplayName("rawRequest to jobResponse")
    @Disabled
    void convert() {
        List<Integer> input = Arrays.asList(5, 4, 3, 2, 1);
        JobResponse jobResponse = JobResponse.builder().jobId("xyz").jobElapsedTime(10L).jobEnqueTime(OffsetDateTime.now()).jobStatus(JobStatus.PENDING).job(input).build();

        Assertions.assertEquals("xyz", rawRequestToJobResponseService.convert("xyz", input).getJobId());
        
    }
}