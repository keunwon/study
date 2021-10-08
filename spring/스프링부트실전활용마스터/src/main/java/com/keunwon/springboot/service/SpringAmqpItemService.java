package com.keunwon.springboot.service;

import com.keunwon.springboot.Item;
import com.keunwon.springboot.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

//@Service
public class SpringAmqpItemService {
    private static final Logger log = LoggerFactory.getLogger(SpringAmqpItemService.class);

    private final ItemRepository itemRepository;

    public SpringAmqpItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @RabbitListener(
            ackMode = "MANUAL",
            bindings = @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "hacking-spring-boot"),
                    key = "new-items-spring-amqp"))
    public Mono<Void> processNewItemViaSpringAmqp(Item item) {
        log.debug("Consuming => " + item);
        return itemRepository.save(item).then();
    }
}
