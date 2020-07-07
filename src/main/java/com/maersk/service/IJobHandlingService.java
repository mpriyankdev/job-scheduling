package com.maersk.service;

import com.maersk.model.Job;

import java.util.List;

public interface IJobHandlingService {
    void executeJob(Job job);

    Job persistJobResult(Job job);

    Job fetchJobResult(String jobId);

    List<Job> fetchAllJobs();
}
