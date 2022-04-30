package com.monitor;

import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;

@SpringBootApplication
public class MonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, String> kafkaTemplate) {
        return args -> {
            Map<MetricName, ? extends Metric> metrics = kafkaTemplate.metrics();
        };
    }
}
