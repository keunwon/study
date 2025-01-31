package com.streams.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ClipConsumer {

    @KafkaListener(id = "clip4-to-listener-id", topics = "clip4-to")
    public void listener(String message) {
        System.out.println("clip4-to message = " + message);
    }
}
