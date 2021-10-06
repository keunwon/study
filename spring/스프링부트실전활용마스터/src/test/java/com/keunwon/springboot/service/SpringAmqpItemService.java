package com.keunwon.springboot.service;

import com.keunwon.springboot.Item;
import com.keunwon.springboot.repository.ItemRepository;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SpringAmqpItemService {

    private final ItemRepository itemRepository;

    public SpringAmqpItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @RabbitListener(
            ackMode = "MANUAL",
            bindings = @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange("hacking-spring-boot"),
                    key = "new-items-spring-amqp"))
    public Mono<Void> processNewItemsViaSpringAmqp(Item item) {
        return itemRepository.save(item).then();
    }
}
