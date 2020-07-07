package com.maersk.repository;

import java.util.List;

public interface IRawRequestsHandlingRepository {
    List<Integer> dumpRawRequest(String jobId, List<Integer> rawInput);

    List<Integer> fetchRawRequest(String jobId);
}
