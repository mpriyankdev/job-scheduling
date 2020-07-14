package com.maersk.service.impl;

import com.maersk.consts.JobStatus;
import com.maersk.model.JobResponse;
import com.maersk.repository.impl.RawRequestsHandlingRepository;
import com.maersk.utility.JobIdGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
class RawRequestsHandlingServiceTest {

    @Autowired
    private RawRequestsHandlingService rawRequestsHandlingService;

    @MockBean
    private RawRequestsHandlingRepository rawRequestsHandlingRepository;

    @MockBean
    private JobIdGenerator jobIdGenerator;

    @Mock
    private RawRequestToJobResponseService rawRequestToJobResponseService;

    @Test
    @DisplayName("dumping raw request")
    void dumpRawRequest() {
        List<Integer> input = Arrays.asList(5, 4, 3, 2, 1);
        JobResponse jobResponse = JobResponse.builder().jobId("xyz").jobElapsedTime(10L).jobEnqueTime(OffsetDateTime.now()).jobStatus(JobStatus.PENDING).job(input).build();

        doReturn("xyz").when(jobIdGenerator).generateRequestId();
        doReturn(input).when(rawRequestsHandlingRepository).dumpRawRequest("xyz", input);

        doReturn(jobResponse).when(rawRequestToJobResponseService).convert("xyz", input);

        Assertions.assertEquals("xyz", rawRequestsHandlingService.dumpRawRequest(input).getJobId());
    }
}