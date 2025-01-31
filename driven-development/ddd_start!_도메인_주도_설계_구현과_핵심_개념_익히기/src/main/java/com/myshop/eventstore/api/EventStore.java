package com.myshop.eventstore.api;

import java.util.List;

public interface EventStore {
    void save(EventEntry event);

    List<EventEntry> get(long offset, long limit);
}
