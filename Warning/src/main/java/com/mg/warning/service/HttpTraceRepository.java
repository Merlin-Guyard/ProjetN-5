package com.mg.warning.service;

import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class HttpTraceRepository {

    @Configuration
    @Profile("actuator-endpoints")
    public class HttpTraceActuatorConfiguration {

        @Bean
        public InMemoryHttpTraceRepository httpTraceRepository() {
            return new InMemoryHttpTraceRepository();
        }

    }
}
