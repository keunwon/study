package com.consumer.controller;

import com.consumer.model.Animal;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    private final KafkaTemplate<String, Animal> kafkaJsonTemplate;

    public KafkaController(KafkaTemplate<String, Animal> kafkaJsonTemplate) {
        this.kafkaJsonTemplate = kafkaJsonTemplate;
    }

    @GetMapping(path = "/")
    public String main() {
        kafkaJsonTemplate.send("clip4-animal", new Animal("puppy", 11));
        return "suc";
    }
}
