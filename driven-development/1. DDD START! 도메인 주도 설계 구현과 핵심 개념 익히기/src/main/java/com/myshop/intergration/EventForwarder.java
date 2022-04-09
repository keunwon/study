package com.myshop.intergration;

import com.myshop.eventstore.api.EventEntry;
import com.myshop.eventstore.api.EventStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventForwarder {
    private static final int DEFAULT_LIMIT_SIZE = 100;

    private final EventStore eventStore;
    private final OffsetStore offsetStore;
    private final EventSender eventSender;

    @Scheduled(initialDelay = 1000L, fixedDelay = 1000L)
    public void getAndSend() {
        long nextOffset = getNextOffset();
        List<EventEntry> events = eventStore.get(nextOffset, DEFAULT_LIMIT_SIZE);

        if (CollectionUtils.isEmpty(events)) { return; }

        int processedCount = sendEvent(events);
        if (0 < processedCount) {
            saveNextOffset(nextOffset + processedCount);
        }
    }

    private int sendEvent(List<EventEntry> events) {
        int processedCount = 0;

        try {
            for (EventEntry event : events) {
                eventSender.send(event);
                processedCount++;
            }
        } catch (Exception e) {
            log.info("이벤트 정보 저장 실패", e);
        }
        return processedCount;
    }

    private void saveNextOffset(long nextOffset) {
        offsetStore.update(nextOffset);
    }

    private long getNextOffset() {
        return offsetStore.get();
    }
}
