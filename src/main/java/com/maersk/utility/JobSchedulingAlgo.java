package com.maersk.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobSchedulingAlgo {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    public List<Integer> sort(final List<Integer> job) {

        return job.parallelStream().sorted().collect(Collectors.toList());

    }
}
