package com.maersk.service;

import com.maersk.model.JobResponse;

import java.util.List;

public interface IRawRequestToJobResponseService {
    JobResponse convert(String jobId, List<Integer> rawInput);
}
