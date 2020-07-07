package com.maersk.controller;

import com.maersk.events.RequestEventPublisher;
import com.maersk.model.Job;
import com.maersk.model.JobResponse;
import com.maersk.service.impl.RawRequestsHandlingService;
import com.maersk.utility.JobRequestToJobConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/job")
public class RawRequestsHandlingController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private RawRequestsHandlingService rawRequestsHandlingService;

    @Autowired
    private RequestEventPublisher requestEventPublisher;
    @Autowired
    private JobRequestToJobConverter jobRequestToJobConverter;

    @PostMapping
    public ResponseEntity<JobResponse> handleRawRequest(@RequestBody List<Integer> rawInput) {
        LOGGER.info("Handling raw request with size: {}", rawInput.size());
        final JobResponse response = rawRequestsHandlingService.dumpRawRequest(rawInput);
        final Job jobEvent = jobRequestToJobConverter.convert(response);

        LOGGER.info("Publishing event with id : {}", jobEvent.getJobId());
        requestEventPublisher.publish(jobEvent);


        return ResponseEntity.accepted().body(response);

    }
}
