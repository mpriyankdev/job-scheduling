package com.maersk.controller;

import com.maersk.consts.JobStatus;
import com.maersk.model.Job;
import com.maersk.model.JobResponse;
import com.maersk.service.impl.JobHandlingService;
import com.maersk.utility.JobToJobResponseConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JobHandlingControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JobHandlingService jobHandlingService;

    @MockBean
    private JobToJobResponseConverter jobToJobResponseConverter;

    @Test
    @DisplayName("GET Job Status Check")
    void handleJobStatusCheck() throws Exception {
        List<Integer> input = Arrays.asList(5, 4, 3, 2, 1);
        Job j1 = new Job(this, "xyz", OffsetDateTime.now(), OffsetDateTime.now(), 0L, JobStatus.PENDING, input);
        JobResponse jobResponse = JobResponse.builder().jobId("xyz").jobElapsedTime(10L).jobEnqueTime(OffsetDateTime.now()).jobStatus(JobStatus.PENDING).job(input).build();

        doReturn(j1).when(jobHandlingService).fetchJobResult("xyz");
        doReturn(jobResponse).when(jobToJobResponseConverter).convert(j1);

        mvc.perform(get("/status").queryParam("jobId", "xyz"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jobId", is("xyz")));
    }

    @Test
    @DisplayName("GET Job Overview")
    void handleAllJobOverviewRequest() throws Exception {

        List<Integer> input = Arrays.asList(5, 4, 3, 2, 1);
        Job j1 = new Job(this, "xyz1", OffsetDateTime.now(), OffsetDateTime.now(), 0L, JobStatus.PENDING, input);
        Job j2 = new Job(this, "xyz2", OffsetDateTime.now(), OffsetDateTime.now(), 0L, JobStatus.PENDING, input);

        JobResponse jobResponse1 = JobResponse.builder().jobId("xyz1").jobElapsedTime(0L).jobEnqueTime(OffsetDateTime.now()).jobStatus(JobStatus.PENDING).job(input).build();
        JobResponse jobResponse2 = JobResponse.builder().jobId("xyz2").jobElapsedTime(0L).jobEnqueTime(OffsetDateTime.now()).jobStatus(JobStatus.PENDING).job(input).build();
        doReturn(Arrays.asList(j1, j2)).when(jobHandlingService).fetchAllJobs();

        mvc.perform(get("/overview")).andExpect(status().isOk());





    }
}