package com.maersk.model;

import com.maersk.consts.JobStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.time.OffsetDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
@Setter
public class Job extends ApplicationEvent {

    private String jobId;
    private OffsetDateTime jobEnqueTime;
    private OffsetDateTime jobFinishTime;
    private Long elapsedTime;
    private JobStatus jobStatus;
    private List<Integer> job;


    public Job(Object source) {
        super(source);
    }

    public Job(Object source, String jobId, OffsetDateTime jobEnqueTime, OffsetDateTime jobFinishTime, Long elapsedTime, JobStatus jobStatus, List<Integer> job) {
        super(source);
        this.jobId = jobId;
        this.jobEnqueTime = jobEnqueTime;
        this.jobFinishTime = jobFinishTime;
        this.elapsedTime = elapsedTime;
        this.jobStatus = jobStatus;
        this.job = job;
    }
}
