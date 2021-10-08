package com.keunwon.springboot.service;

import com.keunwon.springboot.Item;
import com.keunwon.springboot.repository.ItemRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Controller
public class RSocketService {

    private final ItemRepository itemRepository;
    private final Sinks.Many<Item> itemSink;

    public RSocketService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        this.itemSink = Sinks.many().multicast().directBestEffort();
    }

    @MessageMapping("newItems.request-response")
    public Mono<Item> processNewItemsViaRSocketRequestResponse(Item item) {
        return itemRepository.save(item)
                .doOnNext(itemSink::tryEmitNext);
    }

    @MessageMapping("newItems.request-stream")
    public Flux<Item> findItemsViaRSocketRequestStream() {
        return itemRepository.findAll()
                .doOnNext(itemSink::tryEmitNext);
    }

    @MessageMapping("newItems.fire-add-forget")
    public Mono<Void> processNewItemsViaRSocketFireAndForget(Item item) {
        return itemRepository.save(item)
                .doOnNext(itemSink::tryEmitNext)
                .then();
    }

    @MessageMapping("newItem.monitor")
    public Flux<Item> monitorNewItems() {
        return itemSink.asFlux();
    }
}
