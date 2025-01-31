package com.myshop.intergration;

import com.myshop.eventstore.api.EventEntry;

public interface EventSender {
    void send(EventEntry event);
}
