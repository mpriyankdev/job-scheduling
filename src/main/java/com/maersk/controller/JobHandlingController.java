package com.maersk.controller;

import com.maersk.model.Job;
import com.maersk.model.JobOverviewResponse;
import com.maersk.model.JobResponse;
import com.maersk.service.impl.JobHandlingService;
import com.maersk.utility.JobToJobResponseConverter;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api("")
@RestController
public class JobHandlingController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private JobHandlingService jobHandlingService;

    @Autowired
    private JobToJobResponseConverter jobToJobResponseConverter;

    @GetMapping("/status")
    public ResponseEntity<JobResponse> handleJobStatusCheck(@RequestParam("jobId") final String jobId) {
        LOGGER.info("jobStatusCheck request for jobId : {}", jobId);

        final Job jobResult = jobHandlingService.fetchJobResult(jobId);
        final JobResponse response = jobToJobResponseConverter.convert(jobResult);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/overview")
    public ResponseEntity<List<JobOverviewResponse>> handleAllJobOverviewRequest() {

        LOGGER.info("Overview request received");
        final List<JobOverviewResponse> jobOverviewResponses = jobHandlingService.fetchAllJobs()
                .stream()
                .map(x -> JobOverviewResponse.builder().jobId(x.getJobId()).jobElapsedTime(x.getElapsedTime()).jobEnqueTime(x.getJobEnqueTime()).jobStatus(x.getJobStatus()).build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(jobOverviewResponses);

    }
}
