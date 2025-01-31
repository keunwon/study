package com.topic;

import com.topic.service.ClipConsumer;
import com.topic.service.KafkaManager;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class TopicApplication {

    public static void main(String[] args) {
        SpringApplication.run(TopicApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(KafkaManager manager, KafkaTemplate<String, String> kafkaTemplate, ClipConsumer consumer) {
        return args -> {
            manager.describeTopicConfigs();
            manager.changeConfig();
            //manager.deleteRecords();
            manager.findAllConsumerGroup();
            //manager.deleteConsumerGroup();
            manager.findAllOffsets();

            kafkaTemplate.send("clip1-listener", "Hello, Listener.");
            consumer.seek();
        };
    }
}
