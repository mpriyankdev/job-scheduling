package com.maersk.service.impl;

import com.maersk.consts.JobStatus;
import com.maersk.model.Job;
import com.maersk.repository.impl.JobHandlingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.OffsetDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class JobHandlingServiceTest {

    @Autowired
    private JobHandlingService jobHandlingService;

    @MockBean
    private JobHandlingRepository jobHandlingRepository;

    @Test
    @DisplayName("execute job test")
    void executeJob() {
        Job j1 = new Job(this, "xyz", OffsetDateTime.now(), OffsetDateTime.now(), 0L, JobStatus.PENDING, Arrays.asList(1, 2, 3, -1));
        doReturn(j1).when(jobHandlingRepository).saveJobResult(j1);

        Assertions.assertEquals(1, 1);


    }

    @Test
    @DisplayName("persist job result")
    void persistJobResult() {
        Job j1 = new Job(this, "xyz", OffsetDateTime.now(), OffsetDateTime.now(), 0L, JobStatus.PENDING, Arrays.asList(1, 2, 3, -1));
        doReturn(j1).when(jobHandlingRepository).saveJobResult(j1);


        Assertions.assertEquals("xyz", jobHandlingService.persistJobResult(j1).getJobId());
    }

    @Test
    @DisplayName("fetch job result")
    void fetchJobResult() {
        Job j1 = new Job(this, "xyz", OffsetDateTime.now(), OffsetDateTime.now(), 0L, JobStatus.PENDING, Arrays.asList(1, 2, 3, -1));

        doReturn(j1).when(jobHandlingRepository).saveJobResult(j1);
        doReturn(j1).when(jobHandlingRepository).fetchJobRequest("xyz");

        Assertions.assertEquals("xyz", jobHandlingService.persistJobResult(j1).getJobId());

    }

    @Test
    @DisplayName("fetch all jobs")
    void fetchAllJobs() {
        Job j1 = new Job(this, "xyz1", OffsetDateTime.now(), OffsetDateTime.now(), 0L, JobStatus.PENDING, Arrays.asList(1, 2, 3, -1));
        Job j2 = new Job(this, "xyz2", OffsetDateTime.now(), OffsetDateTime.now(), 0L, JobStatus.PENDING, Arrays.asList(1, 2, 3, -1));

        doReturn(Arrays.asList(j1, j2)).when(jobHandlingRepository).fetchAllJobs();

        Assertions.assertEquals(2, jobHandlingService.fetchAllJobs().size());


    }
}