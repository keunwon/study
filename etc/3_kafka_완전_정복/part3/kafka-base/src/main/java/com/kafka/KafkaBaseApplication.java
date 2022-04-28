package com.kafka;

import com.kafka.producer.ChipProducer;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.admin.TopicListing;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class KafkaBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaBaseApplication.class, args);
    }

    //@Bean
    public ApplicationRunner runner(AdminClient adminClient) {
        return args -> {
            Map<String, TopicListing> topics = adminClient.listTopics().namesToListings().get();
            for (String name : topics.keySet()) {
                TopicListing topicListing = topics.get(name);
                System.out.println(topicListing);

                Map<String, TopicDescription> descriptionMap = adminClient.describeTopics(Collections.singleton(name)).all().get();
                System.out.println(descriptionMap);

                if (!topicListing.isInternal()) {
                    adminClient.deleteTopics(List.of(name));
                }
            }
        };
    }

    @Bean
    public ApplicationRunner runner(ChipProducer producer) {
        return args -> {
            producer.async("clip3", "Hello Clip3-async");
            producer.sync("clip3", "Hello Clip3-async");
            producer.routingSend("clip3", "Hello, Clip3-routing");

            producer.routingSendBytes("clip3-bytes", "Hello, clip3-bytes".getBytes(StandardCharsets.UTF_8));
            producer.replyingSend("clip3-request", "Ping Clip3");
        };
    }
}
