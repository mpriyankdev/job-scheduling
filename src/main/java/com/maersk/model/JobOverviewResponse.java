package com.maersk.model;

import com.maersk.consts.JobStatus;
import lombok.*;

import java.time.OffsetDateTime;

@Builder
@EqualsAndHashCode
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOverviewResponse {

    private String jobId;
    private OffsetDateTime jobEnqueTime;
    private JobStatus jobStatus;
    private Long jobElapsedTime;

}
