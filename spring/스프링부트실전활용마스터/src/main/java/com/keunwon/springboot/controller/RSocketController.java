package com.keunwon.springboot.controller;

import com.keunwon.springboot.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;

import static io.rsocket.metadata.WellKnownMimeType.MESSAGE_RSOCKET_ROUTING;
import static org.springframework.http.MediaType.*;
import static org.springframework.util.MimeTypeUtils.parseMimeType;

@RestController
public class RSocketController {
    private static final Logger log = LoggerFactory.getLogger(RSocketController.class);

    private final RSocketRequester rSocketRequester;

    public RSocketController(RSocketRequester.Builder builder) {
        rSocketRequester = builder
                .dataMimeType(APPLICATION_JSON)
                .metadataMimeType(parseMimeType(MESSAGE_RSOCKET_ROUTING.toString()))
                .tcp("localhost", 9000);
    }

    @PostMapping(value = "/items/request-response")
    public Mono<ResponseEntity<?>> addNewItemUsingRSocketRequestResponse(@RequestBody Item item) {
        return rSocketRequester
                .route("newItems.request-response")
                .data(item)
                .retrieveMono(Item.class)
                .map(saveItem -> ResponseEntity
                        .created(URI.create("/items/request-response"))
                        .body(saveItem));
    }

    @GetMapping(value = "/items/request-stream", produces = APPLICATION_NDJSON_VALUE)
    public Flux<Item> findItemsUsingRSocketRequestStream() {
        return rSocketRequester
                .route("newItems.request-stream")
                .retrieveFlux(Item.class)
                .delayElements(Duration.ofSeconds(1));
    }

    @PostMapping(value = "/items/fire-and-forget")
    public Mono<ResponseEntity<?>> addNewItemUsingRSocketFireAndForget(@RequestBody Item item) {
        return rSocketRequester
                .route("\"newItems.fire-add-forget")
                .data(item)
                .send()
                .then(Mono.just(
                        ResponseEntity.created(URI.create("/items/fire-and-forget")).build()));
    }

    @GetMapping(value = "/items", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<Item> liveUpdates() {
        return rSocketRequester
                .route("newItems.monitor")
                .retrieveFlux(Item.class);
    }
}
