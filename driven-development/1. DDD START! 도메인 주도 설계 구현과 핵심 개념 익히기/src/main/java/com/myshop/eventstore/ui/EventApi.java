package com.myshop.eventstore.ui;

import com.myshop.eventstore.api.EventEntry;
import com.myshop.eventstore.api.EventStore;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class EventApi {
    private final EventStore eventStore;

    @GetMapping(value = "/api/events")
    public List<EventEntry> list(@RequestParam(name = "offset") Long offset, @RequestParam(name = "limit") Long limit) {
        return eventStore.get(offset, limit);
    }
}
