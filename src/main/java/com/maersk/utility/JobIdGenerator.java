package com.maersk.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JobIdGenerator {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    public String generateRequestId() {
        return UUID.randomUUID().toString();
    }
}
