package com.myshop.intergration.infra;

import com.myshop.eventstore.api.EventEntry;
import com.myshop.intergration.EventSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SysoutEventSender implements EventSender {
    @Override
    public void send(EventEntry event) {
        log.info("EventEntry: {}", event.toString());
    }
}
