package com.myshop.eventstore.ui;

import com.myshop.eventstore.api.EventEntry;
import com.myshop.eventstore.api.EventStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class EventApi {
    private final EventStore eventStore;

    @GetMapping(value = "/api/events")
    public List<EventEntry> list(@RequestParam(name = "offset") Long offset, @RequestParam(name = "limit") Long limit) {
        return eventStore.get(offset, limit);
    }

    @GetMapping(value = "/api/go")
    public List<Point> point() {
        return Arrays.asList(new Point(1, 1), new Point(2, 2), new Point(3, 3));
    }
}
