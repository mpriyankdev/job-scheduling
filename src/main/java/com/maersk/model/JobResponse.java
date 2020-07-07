package com.maersk.model;

import com.maersk.consts.JobStatus;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
@EqualsAndHashCode
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobResponse {

    private String jobId;
    private OffsetDateTime jobEnqueTime;
    private JobStatus jobStatus;
    private Long jobElapsedTime;
    private List<Integer> job;

}
