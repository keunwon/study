package com.keunwon.springboot.repository;

import com.keunwon.springboot.Item;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ItemRepository extends ReactiveCrudRepository<Item, String>, ReactiveQueryByExampleExecutor<Item> {
    Flux<Item> findByNameContaining(String partialName);

    // name 검색
    Flux<Item> findByNameContainingIgnoreCase(String partialName);

    // description 검색
    Flux<Item> findByDescriptionContainingIgnoreCase(String partialName);

    // name And description 검색
    Flux<Item> findByNameContainingAndDescriptionContainingAllIgnoreCase(String partialName, String partialDesc);

    // name OR description 검색
    Flux<Item> findByNameContainingOrDescriptionContainingAllIgnoreCase(String partialName, String partialDesc);
}
