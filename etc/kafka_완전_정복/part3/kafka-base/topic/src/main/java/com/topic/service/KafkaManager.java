package com.topic.service;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaManager {
    private final KafkaAdmin kafkaAdmin;
    private final AdminClient adminClient;

    public KafkaManager(KafkaAdmin kafkaAdmin) {
        this.kafkaAdmin = kafkaAdmin;
        this.adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
    }

    public void describeTopicConfigs() throws ExecutionException, InterruptedException {
        Collection<ConfigResource> resources = List.of(
                new ConfigResource(ConfigResource.Type.TOPIC, "clip4-listener"));

        DescribeConfigsResult result = adminClient.describeConfigs(resources);
        System.out.println(result.all().get());
    }

    public void changeConfig() throws ExecutionException, InterruptedException {
        ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, "clip4-listener");
        Map<ConfigResource, Collection<AlterConfigOp>> ops = new HashMap<>();
        ops.put(resource, List.of(new AlterConfigOp(new ConfigEntry(TopicConfig.RETENTION_MS_CONFIG, "6000"), AlterConfigOp.OpType.SET)));

        adminClient.incrementalAlterConfigs(ops);
        describeTopicConfigs();
    }

    public void deleteRecords() throws ExecutionException, InterruptedException {
        TopicPartition tp = new TopicPartition("clip4-listener", 0);
        Map<TopicPartition, RecordsToDelete> target = new HashMap<>();
        target.put(tp, RecordsToDelete.beforeOffset(1));

        DeleteRecordsResult deleteRecordsResult = adminClient.deleteRecords(target);
        Map<TopicPartition, KafkaFuture<DeletedRecords>> result = deleteRecordsResult.lowWatermarks();

        for (Map.Entry<TopicPartition, KafkaFuture<DeletedRecords>> entry : result.entrySet()) {
            System.out.printf(
                    "topic: %s, partition: %d, value: %s%n",
                    entry.getKey().topic(), entry.getKey().partition(), entry.getValue().get().lowWatermark());
        }
    }

    public void findAllConsumerGroup() throws ExecutionException, InterruptedException {
        ListConsumerGroupsResult result = adminClient.listConsumerGroups();
        Collection<ConsumerGroupListing> groups2 = result.all().get();
        Collection<ConsumerGroupListing> groups = result.valid().get();

        for (ConsumerGroupListing group : groups) {
            System.out.println(group);
        }
    }

    public void deleteConsumerGroup() throws ExecutionException, InterruptedException {
        adminClient.deleteConsumerGroups(List.of("clip3-id", "clip3-request-id")).all().get();
    }

    public void findAllOffsets() throws ExecutionException, InterruptedException {
        Map<TopicPartition, OffsetSpec> target = new HashMap<>();
        target.put(new TopicPartition("clip4-listener", 0), OffsetSpec.latest());

        ListOffsetsResult result = adminClient.listOffsets(target);

        for (TopicPartition tp : target.keySet()) {
            System.out.println("topic = " + tp.topic() + ", partition= " + tp.partition() + ", offsets = " + result.partitionResult(tp).get());
        }
    }
}
