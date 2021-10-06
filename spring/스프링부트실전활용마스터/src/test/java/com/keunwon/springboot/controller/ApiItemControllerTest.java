package com.keunwon.springboot.controller;

import com.keunwon.springboot.Item;
import com.keunwon.springboot.repository.ItemRepository;
import com.keunwon.springboot.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@AutoConfigureRestDocs
@WebFluxTest(controllers = ApiItemController.class)
class ApiItemControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private InventoryService inventoryService;

    @MockBean
    private ItemRepository itemRepository;

    @Test
    void findingAllItems() {
        when(itemRepository.findAll()).thenReturn(
                Flux.just(new Item("item-1", "Alf alarm clock", "nothing I really need", 19.99))
        );

        webTestClient.get().uri("/api/items")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("findAll", preprocessResponse(prettyPrint())));
    }

    @Test
    void postNewItem() {
        when(itemRepository.save(any())).thenReturn(
                Mono.just(new Item("1", "Alf alarm clock", "nothing important", 19.99))
        );

        webTestClient.post().uri("/api/items")
                .bodyValue(new Item("Alf alarm clock", "nothing important", 19.99))
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .consumeWith(document("post-new-item", preprocessResponse(prettyPrint())));
    }
}