package com.consumer;

import com.consumer.model.Animal;
import com.consumer.producer.ClipProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

@SpringBootApplication
public class ConsumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumeApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(ClipProducer producer) {
        return args -> {
            //producer.async("clip4-listener", "Hello, Clip4 Listener");
            producer.async("clip4-animal", new Animal("puppy", 11));
        };
    }
}
