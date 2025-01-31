package com.consumer.configuration;

import com.consumer.model.Animal;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListenerConfigurer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.retry.support.RetryTemplateBuilder;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaJsonListenerContainerConfiguration implements KafkaListenerConfigurer {
    private final LocalValidatorFactoryBean validator;

    public KafkaJsonListenerContainerConfiguration(LocalValidatorFactoryBean validator) {
        this.validator = validator;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Animal>> kafkaJsonContainerFactory(
            KafkaTemplate<String, Animal> kafkaJsonTemplate
    ) {
        ConcurrentKafkaListenerContainerFactory<String, Animal> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(animalConsumerFactory());

        /*factory.setRetryTemplate(customizedRetryTemplate());
        factory.setRecoveryCallback(context -> {
            ConsumerRecord record = (ConsumerRecord) context.getAttribute("record");
            System.out.println("Recovery callback. message= " + record.value());
            return Optional.empty();
        });*/
        //factory.setErrorHandler(new SeekToCurrentErrorHandler(new DeadLetterPublishingRecoverer(kafkaJsonTemplate)));

        factory.setCommonErrorHandler(new DefaultErrorHandler(
                new DeadLetterPublishingRecoverer(kafkaJsonTemplate, (consumerRecord, e) -> {
                    System.out.println("Recovery callback. message=" + consumerRecord.value());
                    System.out.println("classname= " + e.getClass().getName());
                    return new TopicPartition(consumerRecord.topic() + ".DLT", consumerRecord.partition());
                }))
        );
        return factory;
    }

    private RetryTemplate customizedRetryTemplate() {
        return new RetryTemplateBuilder()
                .fixedBackoff(5_000)
                .customPolicy(retryPolicy())
                .build();
    }

    private RetryPolicy retryPolicy() {
        Map<Class<? extends Throwable>, Boolean> exceptions = new HashMap<>();
        exceptions.put(ListenerExecutionFailedException.class, false);

        return new SimpleRetryPolicy(3, exceptions);
    }

    public ConsumerFactory<String, Animal> animalConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                props(),
                new StringDeserializer(),
                new JsonDeserializer<>(Animal.class)
        );
    }

    private Map<String, Object> props() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return props;
    }

    @Override
    public void configureKafkaListeners(KafkaListenerEndpointRegistrar registrar) {
        registrar.setValidator(validator);
    }
}
