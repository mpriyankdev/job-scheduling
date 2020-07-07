package com.maersk.repository;

import com.maersk.model.Job;

import java.util.List;

public interface IJobHandlingRepository {
    Job saveJobResult(Job job);

    Job fetchJobRequest(String jobId);

    List<Job> fetchAllJobs();
}
