package com.maersk.service;

import com.maersk.model.JobResponse;

import java.util.List;

public interface IRawRequestsHandlingService {
    JobResponse dumpRawRequest(List<Integer> rawInput);
}
