package com.consumer.producer;

import com.consumer.model.Animal;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.TopicCollection;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class ClipProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, Animal> kafkaJsonTemplate;
    private final AdminClient adminClient;

    public ClipProducer(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, Animal> kafkaJsonTemplate, KafkaAdmin kafkaAdmin) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaJsonTemplate = kafkaJsonTemplate;
        this.adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
    }

    public void async(String topic, String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new KafkaSendCallback<>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Success to send message.");
            }

            @Override
            public void onFailure(KafkaProducerException ex) {
                ProducerRecord<Object, Object> record = ex.getFailedProducerRecord();
                System.out.println("Fail to send message. record=" + record);
            }
        });
    }

    public void async(String topic, Animal animal) {
        kafkaJsonTemplate.send(topic, animal);
    }

    public void deleteAllByTopic() throws ExecutionException, InterruptedException {
        Map<String, TopicListing> topics = adminClient.listTopics().namesToListings().get();
        List<String> names = new ArrayList<>(topics.keySet());

        adminClient.deleteTopics(names);
    }
}
