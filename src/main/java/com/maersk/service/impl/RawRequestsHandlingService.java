package com.maersk.service.impl;

import com.maersk.model.JobResponse;
import com.maersk.repository.impl.RawRequestsHandlingRepository;
import com.maersk.service.IRawRequestsHandlingService;
import com.maersk.utility.JobIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RawRequestsHandlingService implements IRawRequestsHandlingService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private RawRequestsHandlingRepository rawRequestsHandlingRepository;

    @Autowired
    private JobIdGenerator jobIdGenerator;

    @Autowired
    private RawRequestToJobResponseService rawRequestToJobResponseService;

    @Override
    public JobResponse dumpRawRequest(List<Integer> rawInput) {

        if (rawInput == null || rawInput.isEmpty()) {
            LOGGER.info("Received empty or invalid input ");
            throw new RuntimeException("input is empty or invalid");
        }

        final String jobId = jobIdGenerator.generateRequestId();
        final List<Integer> numbers = rawRequestsHandlingRepository.dumpRawRequest(jobId, rawInput);
        return rawRequestToJobResponseService.convert(jobId, rawInput);


    }


}
