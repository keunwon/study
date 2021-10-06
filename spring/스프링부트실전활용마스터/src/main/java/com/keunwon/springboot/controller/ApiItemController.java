package com.keunwon.springboot.controller;

import com.keunwon.springboot.Item;
import com.keunwon.springboot.repository.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
public class ApiItemController {
    private final ItemRepository itemRepository;

    public ApiItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping(value = "/api/items")
    public Flux<Item> findAll() {
        return itemRepository.findAll();
    }

    @GetMapping(value = "/api/items/{id}")
    public Mono<Item> findOne(@PathVariable String id) {
        return itemRepository.findById(id);
    }

    @PostMapping(value = "/api/items")
    public Mono<ResponseEntity<?>> addNewItem(@RequestBody Mono<Item> item) {
        return item.flatMap(itemRepository::save)
                .map(savedItem -> ResponseEntity.created(
                        URI.create("/api/items" + savedItem.getId()))
                        .body(item));
    }

    @PutMapping(value = "/api/items/{id}")
    public Mono<ResponseEntity<?>> updateItem(@RequestBody Mono<Item> item, @PathVariable String id) {
        return item.map(content -> new Item(id, content.getName(), content.getDescription(), content.getPrice()))
                .flatMap(itemRepository::save)
                .map(ResponseEntity::ok);
    }
}
