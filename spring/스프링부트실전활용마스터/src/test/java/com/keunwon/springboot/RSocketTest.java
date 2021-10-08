package com.keunwon.springboot;

import com.keunwon.springboot.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureWebTestClient
public class RSocketTest {

    @Autowired WebTestClient webTestClient;
    @Autowired ItemRepository itemRepository;

    @Test
    void verifyRemoteOperationsThroughRSocketRequestResponse() throws InterruptedException {
        itemRepository.deleteAll()
                .as(StepVerifier::create)
                .verifyComplete();

        webTestClient.post().uri("/items/request-response")
                .bodyValue(new Item("Alf alarm clock", "nothing important", 19.99))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Item.class)
                .value(item -> {
                    assertThat(item.getId()).isNotNull();
                    assertThat(item.getName()).isEqualTo("Alf alarm clock");
                    assertThat(item.getDescription()).isEqualTo("nothing important");
                    assertThat(item.getPrice()).isEqualTo(19.99);
                });

        Thread.sleep(500);

        List<Item> list = itemRepository.findAll().collectList().block();

        itemRepository.findAll()
                .as(StepVerifier::create)
                .expectNextMatches(item -> {
                    assertThat(item.getId()).isNotNull();
                    assertThat(item.getName()).isEqualTo("Alf alarm clock");
                    assertThat(item.getDescription()).isEqualTo("nothing important");
                    assertThat(item.getPrice()).isEqualTo(19.99);
                    return true;
                })
                .verifyComplete();
    }

    @Test
    void verifyRemoteOperationsThroughRSocketRequestStream() {
        itemRepository.deleteAll().block();

        List<Item> items = IntStream.rangeClosed(1, 3)
                .mapToObj(i -> new Item("name - " + i, "description" + i, i))
                .collect(toList());

        itemRepository.saveAll(items).blockLast();

        webTestClient.get().uri("/items/request-stream")
                .accept(MediaType.APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Item.class)
                .getResponseBody()
                .as(StepVerifier::create)
                .expectNextMatches(itemPredicate("1"))
                .expectNextMatches(itemPredicate("2"))
                .expectNextMatches(itemPredicate("3"))
                .verifyComplete();
    }

    private Predicate<Item> itemPredicate(String num) {
        return item -> {
            assertThat(item.getName()).startsWith("name");
            assertThat(item.getName()).endsWith(num);
            assertThat(item.getDescription()).startsWith("description");
            assertThat(item.getDescription()).endsWith(num);
            assertThat(item.getPrice()).isPositive();
            return true;
        };
    }

    @Test
    void verifyRemoteOperationsThroughRSocketFireAndForget() throws InterruptedException {
        itemRepository.deleteAll()
                .as(StepVerifier::create)
                .verifyComplete();

        webTestClient.post().uri("/items/fire-and-forget")
                .bodyValue(new Item("Alf alarm clock", "nothing important", 19.99))
                .exchange()
                .expectStatus().isCreated()
                .expectBody().isEmpty();

        Thread.sleep(500);

        itemRepository.findAll()
                .as(StepVerifier::create)
                .expectNextMatches(item -> {
                    assertThat(item.getId()).isNotNull();
                    assertThat(item.getName()).isEqualTo("Alf alarm clock");
                    assertThat(item.getDescription()).isEqualTo("nothing important");
                    assertThat(item.getPrice()).isEqualTo(19.99);
                    return true;
                })
                .verifyComplete();
    }
}
