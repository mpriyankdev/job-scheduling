package com.maersk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maersk.consts.JobStatus;
import com.maersk.model.Job;
import com.maersk.model.JobResponse;
import com.maersk.service.impl.RawRequestsHandlingService;
import com.maersk.utility.JobRequestToJobConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RawRequestsHandlingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RawRequestsHandlingService rawRequestsHandlingService;

    @MockBean
    private JobRequestToJobConverter jobRequestToJobConverter;

    @Test
    @DisplayName("POST Create a Job")
    void handleRawRequest() throws Exception {
        List<Integer> input = Arrays.asList(5, 4, 3, 2, 1);
        JobResponse jobResponse = JobResponse.builder().jobId("xyz").jobElapsedTime(10L).jobEnqueTime(OffsetDateTime.now()).jobStatus(JobStatus.PENDING).job(input).build();
        Job j1 = new Job(this, "xyz", OffsetDateTime.now(), OffsetDateTime.now(), 0L, JobStatus.PENDING, input);

        doReturn(jobResponse).when(rawRequestsHandlingService).dumpRawRequest(input);
        doReturn(j1).when(jobRequestToJobConverter).convert(jobResponse);

        ObjectMapper mapper = new ObjectMapper();
        final byte[] content = mapper.writeValueAsBytes(input);


        mockMvc.perform(post("/job").content(content).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());


    }
}