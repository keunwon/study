package com.myshop.common.event;

import com.myshop.eventstore.api.EventEntry;
import com.myshop.eventstore.api.EventStore;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EventStoreHandler implements EventHandler<Object> {
    private final EventStore eventStore;

    @EventListener(classes = Event.class)
    @Override
    public void handler(Object event) {
        eventStore.save((EventEntry) event);
    }
}
