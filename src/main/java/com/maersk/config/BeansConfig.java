package com.maersk.config;

import com.maersk.model.Job;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Configuration
public class BeansConfig {

    @Bean(name = "raw_requests")
    public ConcurrentMap<String, List<Integer>> rawRequests() {
        return new ConcurrentHashMap<>();
    }

    @Bean(name = "job_requests")
    public ConcurrentMap<String, Job> jobRequests() {
        return new ConcurrentHashMap<>();
    }

}
