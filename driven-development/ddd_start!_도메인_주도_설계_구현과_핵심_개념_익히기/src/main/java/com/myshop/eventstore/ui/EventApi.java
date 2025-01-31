package com.myshop.eventstore.ui;

import com.myshop.eventstore.api.EventEntry;
import com.myshop.eventstore.api.EventStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
@RestController
public class EventApi {
    private final EventStore eventStore;
    private final MessageSource messageSource;

    @GetMapping(value = "/api/events")
    public List<EventEntry> list(@RequestParam(name = "offset") Long offset, @RequestParam(name = "limit") Long limit) {
        return eventStore.get(offset, limit);
    }

    @GetMapping(value = "/api/messages", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> testRun() {
        log.info(messageSource.getMessage("required", null, Locale.KOREAN));
        log.info(messageSource.getMessage("required", null, Locale.ENGLISH));
        return ResponseEntity.ok("ok");
    }
}
