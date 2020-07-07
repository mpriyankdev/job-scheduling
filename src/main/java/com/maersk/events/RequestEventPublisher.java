package com.maersk.events;

import com.maersk.model.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class RequestEventPublisher implements ApplicationEventPublisherAware {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(final Job event) {
        LOGGER.info("Publishing event with id : {} in state : {} ", event.getJobId(), event.getJobStatus());
        applicationEventPublisher.publishEvent(event);
    }
}
