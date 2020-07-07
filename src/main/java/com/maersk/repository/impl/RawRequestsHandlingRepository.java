package com.maersk.repository.impl;


import com.maersk.repository.IRawRequestsHandlingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

@Repository
public class RawRequestsHandlingRepository implements IRawRequestsHandlingRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    @Qualifier("raw_requests")
    private ConcurrentMap<String, List<Integer>> rawRequests;

    @Override
    public List<Integer> dumpRawRequest(final String jobId, final List<Integer> rawInput) {
        LOGGER.info("Dumping rawInput request with jobId : {} and input-size : {} ", jobId, rawInput.size());
        return rawRequests.put(jobId, rawInput);
    }

    @Override
    public List<Integer> fetchRawRequest(final String jobId) {
        LOGGER.info("fetch raw-request received with jobId : {} ", jobId);
        return rawRequests.getOrDefault(jobId, Collections.emptyList());
    }

}
